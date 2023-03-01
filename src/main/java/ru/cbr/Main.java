package ru.cbr;

import au.com.bytecode.opencsv.CSVWriter;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.collection.ListStringRecordReader;
import org.datavec.api.split.InputSplit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static ru.cbr.DataFirstImpl.*;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        WebDriverManager driverManager = WebDriverManager.firefoxdriver();
        driverManager.cachePath(Paths.get(System.getProperty("user.dir"), "src", "main", "resources").toString());
        driverManager.setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        FirefoxDriver driver = new FirefoxDriver(options);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open("http://ya.ru");
        List<DataFirstImpl> dataList = Parser.parse();

        List<String> otherFields = Arrays.asList("text", "haveText", "height", "weight", "fullCss", "childCount");
        List<String> columnNames = new ArrayList<>(tagKeys.size() + tagAttrKeys.size() + otherFields.size());
        columnNames.addAll(tagKeys);
        columnNames.addAll(tagAttrKeys);
        columnNames.addAll(otherFields);
        List<String[]> data = dataList.stream().map(DataFirstImpl::toArray).collect(Collectors.toList());

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(Paths.get(System.getProperty("user.dir"), "build", "test.csv").toFile(), true), ',');
            writer.writeAll(Collections.singletonList(columnNames.toArray(String[]::new)));
            writer.writeAll(data);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать данные в CSV-файл");
        }
    }
}