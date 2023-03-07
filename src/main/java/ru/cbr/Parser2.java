package ru.cbr;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Parser2 {

    public static List<String> parse() {

        ArrayList<String> elements = new ArrayList<>();

        WebDriverManager driverManager = WebDriverManager.firefoxdriver();
        driverManager.cachePath(Paths.get(System.getProperty("user.dir"), "src", "main", "resources").toString());
        driverManager.setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        FirefoxDriver driver = new FirefoxDriver(options);
        WebDriverRunner.setWebDriver(driver);
        List<String> pages = Arrays.asList("https://passport.yandex.ru", "https://ya.ru", "https://market.yandex.ru/");
        pages.forEach(p->{
            Selenide.open(p);

            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Object o = null;
            if (WebDriverRunner.hasWebDriverStarted()) {
                o = ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(
                        "let elements = Array.from(document.querySelectorAll(\"*\")).filter(e=>e.getBoundingClientRect().height > 0 && e.getBoundingClientRect().width > 0 && e.getAttribute('class') !== null && e.childElementCount <= 1 && e.getAttributeNames().length >=1);\n" +
                                "let result = [];\n" +
                                "   elements.forEach(e => result.push(\n" +
                                "   e.tagName.toLowerCase() + ' ' +  e.getAttribute('class').toLowerCase()));\n" +
                                "   return result");
            }
            elements.addAll((Collection<? extends String>) o);

        });
        return elements;
    }
}
