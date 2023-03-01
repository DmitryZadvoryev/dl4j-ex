package ru.cbr;

import au.com.bytecode.opencsv.CSVWriter;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.spark.SparkConf;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.metadata.ColumnMetaData;
import org.datavec.api.transform.schema.Schema;
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

import static ru.cbr.DataFirstImpl.tagAttrKeys;
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

        List<DataFirstImpl> dataList = Parser.parse();

        List<String> otherFields = Arrays.asList("text", "height", "weight", "fullCss", "childCount");
        List<String> columnNames = new ArrayList<>(tagKeys.size() + tagAttrKeys.size() + otherFields.size());
        columnNames.addAll(tagKeys);
        columnNames.addAll(tagAttrKeys);
        columnNames.addAll(otherFields);
        List<String[]> data = dataList.stream().map(DataFirstImpl::toArray).collect(Collectors.toList());

        String input = "input.csv";
        Path csv = Paths.get(System.getProperty("user.dir"), "build");
        int timestamp = LocalDateTime.now().getNano();

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(Paths.get(System.getProperty("user.dir"), "build").resolve(input).toFile(), true), ',');
            writer.writeAll(Collections.singletonList(columnNames.toArray(String[]::new)));
            writer.writeAll(data);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать данные в CSV-файл");
        }

        int numLinesToSkip = 0;
        String delimiter = ",";
        Path output = csv.resolve("output_" + timestamp + ".csv");

        Schema inputDataSchema = new Schema.Builder()
                .addColumnsString(tagKeys.toArray(String[]::new))
                .addColumnsString(tagAttrKeys.toArray(String[]::new))
                .addColumnsString("text")
                .addColumnsLong("height", "weight")
                .addColumnsString("fullCss")
                .addColumnLong("childCount")
                .build();

        TransformProcess tp = new TransformProcess.Builder(inputDataSchema)
                .build();

        int numActions = tp.getActionList().size();
        for(int i =0; i < numActions; i++){
            System.out.println("\n\n=============================");
            System.out.println("---Schema after step " + i + " (" + tp.getActionList().get(i) + ")---");
            System.out.println(tp.getSchemaAfterStep(i));
        }

        SparkConf
    }
}