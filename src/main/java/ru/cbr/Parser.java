package ru.cbr;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Parser {

    public Parser() {
    }

    public List<DataFirstImpl> parse() {
        List<WebElement> elements = ((List<WebElement>) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(
                "return Array.from(document.querySelectorAll(\"*\"))" +
                        ".filter(e=>e.getBoundingClientRect().height > 0 " +
                        "&& e.getBoundingClientRect().width > 0 " +
                        "&& e.getAttribute('class') !== null " +
                        "&& e.childElementCount <= 1 " +
                        "&& e.getAttributeNames().length >=1);"));
        return null;
    }
}
