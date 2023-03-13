package ru.cbr.customcorpusword2vec.ui;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.cbr.customcorpusword2vec.Word2VecExample;
import ru.cbr.customcorpusword2vec.ui.model.NS;
import ru.cbr.customcorpusword2vec.ui.view.MainFrame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

        startDriver();
        NS ns = new NS();
        new MainFrame(categories(), ns)
                .setVisible(true);
    }

    public static void startDriver() {
        WebDriverManager driverManager = WebDriverManager.firefoxdriver();
        driverManager.cachePath(Paths.get(System.getProperty("user.dir"), "src", "main", "resources").toString());
        driverManager.setup();
        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(true);
        FirefoxDriver driver = new FirefoxDriver(options);
        WebDriverRunner.setWebDriver(driver);
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

