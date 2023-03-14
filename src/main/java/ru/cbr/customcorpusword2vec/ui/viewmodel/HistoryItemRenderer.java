package ru.cbr.customcorpusword2vec.ui.viewmodel;

import ru.cbr.customcorpusword2vec.ui.view.HistoryItemPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HistoryItemRenderer extends JLabel implements ListCellRenderer<HistoryItemPanel> {
    private final ListModel<HistoryItemPanel> historyModel;

    public HistoryItemRenderer(ListModel<HistoryItemPanel> historyModel) {
        this.historyModel = historyModel;
        setOpaque(true);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setFont(new Font("Consolas", Font.PLAIN, 10));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends HistoryItemPanel> jList,
                                                  HistoryItemPanel historyItem,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setText("<html>" + (historyModel.getSize() - index) + ")  " + historyItem.toHtmlString());

        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
//        JList.DropLocation dropLocation = jList.getDropLocation();
//        if (dropLocation != null
//                && !dropLocation.isInsert()
//                && dropLocation.getIndex() == index) {
//
//            background = Color.BLUE;
//            foreground = Color.WHITE;

            // check if this cell is selected
//        } else if (isSelected) {
//            background = Color.RED;
//            foreground = Color.WHITE;

            // unselected, and not the DnD drop location
//        } else {
            background = index % 2 == 0 ? Color.WHITE : Color.getHSBColor(0.14f, 0.03f, 1);
            foreground = Color.BLACK;
//        }

        setBackground(background);
        setForeground(foreground);

        return this;
    }

}
