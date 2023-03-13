package ru.cbr.customcorpusword2vec.ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public class Categories {
    public static final ObservableList<Category> LIST = FXCollections.synchronizedObservableList(FXCollections.observableArrayList());
    private static LocalDateTime lastChange;

    public static void reCalcPercentsAndFindMax() {
        // сумма выходных сигналов НС
        BigDecimal sum = LIST.stream()
                .peek(cat -> cat.setIsMax(false))
                .map(Category::bigDecimalValue)
                .reduce(new BigDecimal("0"), BigDecimal::add);
        LIST.stream()
                .peek(cat -> cat.reCalcPercent(sum))
                .max(Category::compareTo)
                .ifPresent(cat -> cat.setIsMax(true));
        lastChange = LocalDateTime.now();
    }

    public static LocalDateTime getLastChange() {
        return lastChange;
    }

}
