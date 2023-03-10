package ru.cbr.customcorpusword2vec;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Word2VecExample {

    public static final Path build = Paths.get(System.getProperty("user.dir"), "build");
    public static final Path txt = Paths.get(build.toString(), "resources", "main", "txt");
    public static final Path all = build.resolve("all.txt");
    public static final Path train = Paths.get(txt.toString(), "train");
    public static final Path test = Paths.get(txt.toString(), "test");

    public static void main(String[] args) {

        List<String> parse = PageParser.parse()
                .stream()
                .distinct()
                .map(e -> e
                        .replaceAll("[\\_\\-]", " ")
                        .replaceAll("\\s+", " ")
                )
                .collect(Collectors.toList());

        List<String> buttons = parse.stream()
                .filter(e -> e.contains("but") || e.contains("btn"))
                .collect(Collectors.toList());
        Collections.shuffle(buttons);
        List<String> others = parse.stream()
                .filter(e -> !e.contains("but") && !e.contains("btn"))
                .limit(buttons.size())
                .collect(Collectors.toList());
        Collections.shuffle(others);

        try {
            //Все элементы для подготовки данных
            FileUtils.writeLines(all.toFile(), parse);
            //кнопки
            FileUtils.writeLines(train.resolve("1.txt").toFile(), buttons.subList(0, buttons.size() / 2));
            FileUtils.writeLines(test.resolve("1.txt").toFile(), buttons.subList(buttons.size() / 2, buttons.size()));
            //other
            FileUtils.writeLines(train.resolve("0.txt").toFile(), others.subList(0, others.size() / 2));
            FileUtils.writeLines(test.resolve("0.txt").toFile(), others.subList(others.size() / 2, others.size()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
