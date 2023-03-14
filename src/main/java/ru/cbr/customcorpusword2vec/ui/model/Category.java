package ru.cbr.customcorpusword2vec.ui.model;

import javafx.beans.property.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Category implements Comparable<Category> {
    /**
     * Например, "button" или "other"
     */
    private final String name;
    /**
     * Значение {@link #value} максималоное
     * среди всех в {@link Categories#LIST}
     */
    private final BooleanProperty isMax;
    /**
     * Строковое значение выходного сигнала НС.
     * Например, "6.23443"
     */
    private final StringProperty value;
    private BigDecimal bigDecimalValue;
    /**
     * Процентное представление. Например, 89.32
     */
    private final ObjectProperty<BigDecimal> percent;

    public Category(String name) {
        this.name = name;
        isMax = new SimpleBooleanProperty(false);
        value = new SimpleStringProperty("0");
        bigDecimalValue = new BigDecimal("0");
        percent = new SimpleObjectProperty<>(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));

        value.addListener((observable, oldValue, newValue) -> {
            bigDecimalValue = new BigDecimal(newValue).setScale(2, RoundingMode.HALF_UP);
        });
        Categories.LIST.add(this);
    }

    private static final BigDecimal HUNDRED = new BigDecimal("100");

    /**
     * Пересчитывает свой процент {@link #percent}
     * @param sum сумма всех выходных сигналов НС
     */
    public void reCalcPercent(BigDecimal sum) {
        try {
            percent.set(bigDecimalValue().multiply(HUNDRED).divide(sum, RoundingMode.HALF_UP));
        } catch (ArithmeticException e) {
            System.out.println("exc in Category.reCalcPercent(..): " + e.getMessage());
        }
    }


    //
    // Setters
    //

    public void setIsMax(boolean isMax) {
        this.isMax.set(isMax);
    }


    //
    // Getters
    //

    public BigDecimal bigDecimalValue() {
        return bigDecimalValue;
    }

    public BigDecimal percent() {
        return percent.getValue().setScale(2, RoundingMode.HALF_UP);
    }

    public ObjectProperty<BigDecimal> percentProperty() {
        return percent;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public boolean isIsMax() {
        return isMax.get();
    }

    public BooleanProperty isMaxProperty() {
        return isMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(value.toString(), category.value.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value.toString());
    }

    @Override
    public int compareTo(Category category) {
        return bigDecimalValue().compareTo(category.bigDecimalValue());
    }

    @Override
    public String toString() {
        return name + ": " + percent(); // без '%'
    }

}
