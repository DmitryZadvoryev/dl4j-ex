package ru.cbr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        List<String> one = Arrays.asList("1", "2", "3");
        List<String> asd = new ArrayList<>();
        asd.addAll(Arrays.asList("1","2", "3"));
        asd.addAll(0,one);
    }
}
