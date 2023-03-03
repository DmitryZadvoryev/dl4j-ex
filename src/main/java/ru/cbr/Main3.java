package ru.cbr;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.FileRecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.nd4j.linalg.dataset.DataSet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Main3 {

    public static void main(String[] args) throws IOException, InterruptedException {

        int timestamp = LocalDateTime.now().getNano();
        Path csv = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "csv");
        Path output = csv.resolve("output_" + timestamp + ".csv");
        Path input = csv.resolve("input.csv");

        CSVRecordReader recordReader = new CSVRecordReader(',');
        recordReader.initialize(new FileSplit(csv.resolve(input).toFile()));
        /*RecordReaderDataSetIterator iterator = new RecordREader(recordReader,128);


         DataSet next = iterator.next();

*/

        /*try (RecordReader recordReader = new FileRecordReader()) {
            recordReader.initialize(new FileSplit(csv.resolve(input).toFile()));
            int kek = 0;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        //new RecordReaderDataSetIterator()

        /*System.setProperty("hadoop.home.dir", "C:\\hadoop-3.2.2");

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");
        sparkConf.setAppName("Transform test");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sc.textFile(input.toString());
        JavaRDD<List<Writable>> reports = lines.map(new StringToWritablesFunction(new CSVRecordReader( ',')));

        int labelIndex = 5;         //Labels: a single integer representing the class index in column number 5
        int numLabelClasses = 10;   //10 classes for the label
        JavaRDD<DataSet> rddDataSetClassification = reports.map(new DataVecDataSetFunction(labelIndex, numLabelClasses, false));*/
    }
}
