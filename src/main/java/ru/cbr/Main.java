package ru.cbr;

import au.com.bytecode.opencsv.CSVWriter;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.writable.Writable;
import org.datavec.spark.transform.SparkTransformExecutor;
import org.datavec.spark.transform.misc.StringToWritablesFunction;
import org.datavec.spark.transform.misc.WritablesToStringFunction;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.cbr.DataFirstImpl.tagKeys;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager driverManager = WebDriverManager.firefoxdriver();
        driverManager.cachePath(Paths.get(System.getProperty("user.dir"), "src", "main", "resources").toString());
        driverManager.setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        FirefoxDriver driver = new FirefoxDriver(options);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open("http://ya.ru");

        Thread.sleep(5_000);

        List<String[]> data = Parser.parse()
                .stream()
                .map(DataFirstImpl::toArray)
                .collect(Collectors.toList());

        List<String> otherFields = Arrays.asList("tag", /*"text",*/ "heightOfElement", "weightOfElement"/*, "fullCss", "childCount"*/);
        List<String> columnNames = new ArrayList<>(3/*tagAttrKeys.size() + otherFields.size()*/);
        columnNames.addAll(otherFields);
        //columnNames.addAll(1, tagAttrKeys);

        Path csv = Paths.get(System.getProperty("user.dir"), "build");
        Path input = csv.resolve("input.csv");
        int timestamp = LocalDateTime.now().getNano();

        try (CSVWriter writer = new CSVWriter(new FileWriter(input.toFile()), ',')) {
            writer.writeAll(Collections.singletonList(columnNames.toArray(String[]::new)));
            writer.writeAll(data);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать данные в CSV-файл");
        }

        int numLinesToSkip = 1;
        String delimiter = ",";
        Path output = csv.resolve("output_" + timestamp + ".csv");
        Schema inputDataSchema = new Schema.Builder()
                .addColumnCategorical("tag", tagKeys)
                // .addColumnsString(tagAttrKeys.toArray(String[]::new))
                //.addColumnsString("text")
                .addColumnLong("heightOfElement")
                .addColumnLong("weightOfElement")
                //.addColumnsString("fullCss")
                //.addColumnLong("childCount")
                .build();

        TransformProcess tp = new TransformProcess.Builder(inputDataSchema)
                .categoricalToInteger("tag")
                .build();

        int numActions = tp.getActionList().size();
        for (int i = 0; i < numActions; i++) {
            System.out.println("\n\n=============================");
            System.out.println("---Schema after step " + i + " (" + tp.getActionList().get(i) + ")---");
            System.out.println(tp.getSchemaAfterStep(i));
        }

        System.setProperty("hadoop.home.dir", "C:\\hadoop-3.2.2");

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");
        sparkConf.setAppName("Transform test");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sc.textFile(input.toString());
        JavaRDD<List<Writable>> reports = lines.map(new StringToWritablesFunction(new CSVRecordReader(/*numLinesToSkip,*/ ',')));
        JavaRDD<List<Writable>> processed = SparkTransformExecutor.execute(reports, tp);
        // convert Writable back to string for export
        JavaRDD<String> toSave = processed.map(new WritablesToStringFunction(","));

        try (CSVWriter writer = new CSVWriter(new FileWriter(output.toFile()), ',')) {
            writer.writeAll(toSave.collect().stream().map(e -> e.split(",")).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать данные в CSV-файл");
        }
    }
}