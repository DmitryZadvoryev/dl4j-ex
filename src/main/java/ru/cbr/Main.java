package ru.cbr;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
       /* WebDriverManager driverManager = WebDriverManager.edgedriver();
        driverManager.cachePath(Paths.get(System.getProperty("user.dir"),"src","main","resources").toString());
        driverManager.setup();*/
        System.setProperty("webdriver.chrome.driver", Paths.get(System.getProperty("user.dir"),"src","main","resources", "yandex").resolve("yandexdriver").toString());
        Selenide.open("ya.ru");
    }
}