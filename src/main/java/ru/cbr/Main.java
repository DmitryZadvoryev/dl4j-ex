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
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.CollectionSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
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

    public static void main(String[] args) throws InterruptedException, IOException {
        /*WebDriverManager driverManager = WebDriverManager.firefoxdriver();
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

        List<String> otherFields = Arrays.asList("tag", "text", "heightOfElement", "weightOfElement", "fullCss", "childCount");
        List<String> columnNames = new ArrayList<>(tagAttrKeys.size() + otherFields.size());
        columnNames.addAll(otherFields);
        columnNames.addAll(1, tagAttrKeys);*/

        Path csvInRes = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "csv");
        Path csv = Paths.get(System.getProperty("user.dir"), "build");
        Path input = csv.resolve("input.csv");
        int timestamp = LocalDateTime.now().getNano();

      /*  try (CSVWriter writer = new CSVWriter(new FileWriter(input.toFile()), ',')) {
            writer.writeAll(data);
        } catch (IOException e) {
            throw new RuntimeException("???? ?????????????? ???????????????? ???????????? ?? CSV-????????");
        }*/

        Path output = csv.resolve("output_" + timestamp + ".csv");
        Schema inputDataSchema = new Schema.Builder()
                .addColumnString("tag")
                .addColumnsString(tagAttrKeys.toArray(String[]::new))
                .addColumnsString("text")
                .addColumnLong("heightOfElement")
                .addColumnLong("weightOfElement")
                .addColumnsString("fullCss")
                .addColumnLong("childCount")
                .build();

        TransformProcess tp = new TransformProcess.Builder(inputDataSchema)
                .removeColumns(tagAttrKeys.toArray(String[]::new))
                .removeColumns("text", "heightOfElement", "weightOfElement", "fullCss", "childCount")
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
        JavaRDD<String> lines = sc.textFile(csvInRes.resolve("input.csv").toString());
        JavaRDD<List<Writable>> reports = lines.map(new StringToWritablesFunction(new CSVRecordReader( ',')));

        JavaRDD<List<Writable>> processed = SparkTransformExecutor.execute(reports, tp);

        // convert Writable back to string for export
        JavaRDD<String> toSave = processed.map(new WritablesToStringFunction(","));

        SentenceIterator iter = new CollectionSentenceIterator(toSave.collect());
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());
        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(2)
                .iterations(5)
                .layerSize(100)
                .seed(42)
                .windowSize(20)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();
        vec.fit();
        WordVectors wordVectors = WordVectorSerializer.fromTableAndVocab(vec.lookupTable(), vec.getVocab());


       /* try (CSVWriter writer = new CSVWriter(new FileWriter(output.toFile()), ',')) {
            writer.writeAll(toSave.collect().stream().map(e -> e.split(",")).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException("???? ?????????????? ???????????????? ???????????? ?? CSV-????????");
        }*/
    }
}
