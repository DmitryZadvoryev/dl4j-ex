package ru.cbr;

import au.com.bytecode.opencsv.CSVWriter;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        Object o = ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(
                "function fullCssPath(el){\n" +
                        "  var names = [];\n" +
                        "  while (el.parentNode){\n" +
                        "    if (el.id){\n" +
                        "      names.unshift('#'+el.id);\n" +
                        "      break;\n" +
                        "    }else{\n" +
                        "      if (el==el.ownerDocument.documentElement) names.unshift(el.tagName);\n" +
                        "      else{\n" +
                        "        for (var c=1,e=el;e.previousElementSibling;e=e.previousElementSibling,c++);\n" +
                        "        names.unshift(el.tagName+\":nth-child(\"+c+\")\");\n" +
                        "      }\n" +
                        "      el=el.parentNode;\n" +
                        "    }\n" +
                        "  }\n" +
                        "  return names.join(\" > \");\n" +
                        "};" +
                        "let elements = Array.from(document.querySelectorAll(\"*\")).filter(e=>e.getBoundingClientRect().height > 0 && e.getBoundingClientRect().width > 0 && e.getAttribute('class') !== null && e.childElementCount <= 1 && e.getAttributeNames().length >=1);\n" +
                        "let result = [];\n" +
                        "elements.forEach(e => result.push({element : e, attributeNames: e.getAttributeNames(), text: e.innerText, height: Math.round(e.getBoundingClientRect().height), width: Math.round(e.getBoundingClientRect().width), childElementCount: e.childElementCount, fullCss: fullCssPath(e)}));\n" +
                        "return result");

        List<DataFirstImpl> dataFirsts = new ArrayList<>();

        for (int i = 0; i < ((ArrayList) o).size(); i++) {
            Map<String, Object> item = (Map<String, Object>) ((ArrayList) o).get(i);

            String text = (String) item.get("text");
            long height = (long) item.get("height");
            long width = (long) item.get("width");
            String fullCss = (String) item.get("fullCss");
            long childCount = (long) item.get("childElementCount");
            DataFirstImpl element = new DataFirstImpl(text, height, width, fullCss, childCount);
            ArrayList<String> attributeNames = (ArrayList<String>) item.get("attributeNames");
            WebElement webElement = (WebElement) item.get("element");
            attributeNames.stream().forEach(a -> {
                element.addAttrValue(a, webElement.getAttribute(a));
                element.addHasAttrValue("has_" + a, true);
            });
            element.addTag(webElement.getTagName().toLowerCase(), true);
            dataFirsts.add(element);
        }

        File file = Paths.get(System.getProperty("user.dir"), "build", "csv").resolve("test1.csv").toFile();
        file.mkdir();
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(dataFirsts.stream().map(e -> e.toCsv().split(",")).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}