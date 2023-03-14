package ru.cbr.customcorpusword2vec.ui.model;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.INDArrayIndex;
import org.nd4j.linalg.indexing.NDArrayIndex;
import ru.cbr.customcorpusword2vec.Word2VecExample;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NS {

    private final TokenizerFactory tokenizerFactory;
    private final MultiLayerNetwork net;
    private final WordVectors wordVectors;

    public NS() throws IOException {
        tokenizerFactory = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
        net = MultiLayerNetwork.load(new File(ClassLoader.getSystemResource("txt/DomElementModel.net").getFile()), true);
        wordVectors = WordVectorSerializer.readWord2VecModel(new File(ClassLoader.getSystemResource("txt/DomWordVector.txt").getFile()));
    }

    public void update(List<Category> cats, String text) {
        DataSet testNews = prepareTestData(text);
        INDArray fet = testNews.getFeatures();
        INDArray predicted;
        try {
            predicted = net.output(fet, false);
        } catch (IllegalArgumentException e) {
            System.out.println("exc in NS.update(..): " + e.getMessage());
            return;
        }
        long[] arrsiz = predicted.shape();

        for (int i = 0; i < arrsiz[1]; i++) {
            cats.get(i).valueProperty().setValue(predicted.slice(0).getRow(i).sumNumber().toString());
        }
    }

    // One news story gets transformed into a dataset with one element.
    @SuppressWarnings("DuplicatedCode")
    private DataSet prepareTestData(String i_news) {
        List<String> news = new ArrayList<>(1);
        int[] category = new int[1];
        news.add(i_news);

        List<List<String>> allTokens = new ArrayList<>(news.size());
        int maxLength = 0;
        for (String s : news) {
            List<String> tokens = tokenizerFactory.create(s).getTokens();
            List<String> tokensFiltered = new ArrayList<>();
            for (String t : tokens) {
                if (wordVectors.hasWord(t)) tokensFiltered.add(t);
            }
            allTokens.add(tokensFiltered);
            maxLength = Math.max(maxLength, tokensFiltered.size());
        }

        INDArray features = Nd4j.create(news.size(), wordVectors.lookupTable().layerSize(), maxLength);
        INDArray labels = Nd4j.create(news.size(), 2, maxLength);    //labels: Crime, Politics, Bollywood, Business&Development
        INDArray featuresMask = Nd4j.zeros(news.size(), maxLength);
        INDArray labelsMask = Nd4j.zeros(news.size(), maxLength);

        int[] temp = new int[2];
        for (int i = 0; i < news.size(); i++) {
            List<String> tokens = allTokens.get(i);
            temp[0] = i;
            for (int j = 0; j < tokens.size() && j < maxLength; j++) {
                String token = tokens.get(j);
                INDArray vector = wordVectors.getWordVectorMatrix(token);
                features.put(new INDArrayIndex[]{NDArrayIndex.point(i),
                                NDArrayIndex.all(),
                                NDArrayIndex.point(j)},
                        vector);

                temp[1] = j;
                featuresMask.putScalar(temp, 1.0);
            }
            int idx = category[i];
            int lastIdx = Math.min(tokens.size(), maxLength);
            try {
                labels.putScalar(new int[]{i, idx, lastIdx - 1}, 1.0);
                labelsMask.putScalar(new int[]{i, lastIdx - 1}, 1.0);
            } catch (IndexOutOfBoundsException e) {
                if (!e.getMessage().equals("0"))
                    throw e;
            }
        }

        return new DataSet(features, labels, featuresMask, labelsMask);
    }

}
