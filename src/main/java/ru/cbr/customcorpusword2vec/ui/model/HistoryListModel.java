package ru.cbr.customcorpusword2vec.ui.model;

import ru.cbr.customcorpusword2vec.ui.view.HistoryItemPanel;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class HistoryListModel extends DefaultListModel<HistoryItemPanel> {
    private final Map<Integer, Integer> uniqueItems = new HashMap<>();
    @Override
    public void add(int index, HistoryItemPanel item) {
        int itemHash = item.getjTextArea1().getText().hashCode();
        if (!uniqueItems.containsKey(itemHash)) {
            super.add(index, item);
            uniqueItems.put(itemHash, uniqueItems.size() + 1);
        }
    }

    public Map<Integer, Integer> getUniqueItems() {
        return uniqueItems;
    }

}
