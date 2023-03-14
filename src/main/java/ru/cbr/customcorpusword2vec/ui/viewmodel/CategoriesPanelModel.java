package ru.cbr.customcorpusword2vec.ui.viewmodel;

import ru.cbr.customcorpusword2vec.ui.model.Category;
import ru.cbr.customcorpusword2vec.ui.view.CategoriesPanel;
import ru.cbr.customcorpusword2vec.ui.view.CategoryLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CategoriesPanelModel {
    private static final Font MAX_PERC_FONT = new Font("Consolas", Font.BOLD, 12);
    private static final Font STANDARD_FONT = new Font("Consolas", Font.PLAIN, 12);

    private final CategoriesPanel panel;

    public CategoriesPanelModel(List<String> categories, CategoriesPanel panel) {
        this.panel = panel;
        categories.forEach(catName -> {
            Category cat = new Category(catName);
            CategoryLabel catLabel = new CategoryLabel(catName, STANDARD_FONT);
            cat.percentProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        catLabel.setText(cat.toString());
                    });
            cat.isMaxProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            catLabel.setFont(MAX_PERC_FONT);
                            catLabel.setForeground(Color.getHSBColor(0.32f, 1, 0.4f)); // dark green
                        } else {
                            catLabel.setFont(STANDARD_FONT);
                            catLabel.setForeground(Color.BLACK);
                        }
                    });
            panel.add(catLabel);
        });
    }

    public void cloneLabelsTo(CategoriesPanel targetPanel) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel) {
                JLabel origin = (JLabel) c;
                JLabel clone = new JLabel(origin.getText());
                clone.setFont(origin.getFont());
                targetPanel.add(clone);
            }
        }
    }

    public boolean hasMaxPercFont(JLabel catLabel) {
        return catLabel.getFont().equals(MAX_PERC_FONT);
    }

}
