package ru.cbr.customcorpusword2vec.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.cbr.customcorpusword2vec.ui.model.NS;
import ru.cbr.customcorpusword2vec.ui.view.MainFrame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
//                UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(TestPercentage.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        DefaultTokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
//        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
//        MultiLayerNetwork net = MultiLayerNetwork.load(Word2VecExample.build.resolve("DomElementModel.net").toFile(), true);
//        Word2Vec wordVectors = WordVectorSerializer.readWord2VecModel(Word2VecExample.build.resolve("DomWordVector.txt").toFile());

        NS ns = new NS();
        new MainFrame(categories(), ns)
                .setVisible(true);
        startDriver();
    }

    public static void startDriver() {
        WebDriverManager driverManager = WebDriverManager.firefoxdriver();
        driverManager.cachePath(Paths.get(System.getProperty("user.dir"), "src", "main", "resources").toString());
        driverManager.setup();
        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(true);
        FirefoxDriver driver = new FirefoxDriver(options);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open("http://ya.ru");
    }

    private static List<String> categories() {
        try (BufferedReader brCategories = new BufferedReader(new InputStreamReader(
                ClassLoader.getSystemResourceAsStream("txt/categories.txt")))) {
            String temp;
            List<String> labels = new ArrayList<>();
            while (brCategories.ready() && (temp = brCategories.readLine()) != null) {
                labels.add(temp.split(",")[1]);
            }
            return labels;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

