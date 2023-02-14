package ru.cbr;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Driver {

    public static EdgeDriver instance;

    public static EdgeDriver getInstance() {
        if (instance == null) {
            WebDriverManager driverManager = WebDriverManager.edgedriver();
            driverManager.cachePath(Paths.get(System.getProperty("user.dir"),"src","main","resources").toString());
            driverManager.setup();
            //System.setProperty("webdriver.chrome.driver", Paths.get(System.getProperty("user.dir"),"src","main","resources").toString());
            //Configuration.browser = "chrome";
            instance = new EdgeDriver();
        }
        return instance;
    }

}
