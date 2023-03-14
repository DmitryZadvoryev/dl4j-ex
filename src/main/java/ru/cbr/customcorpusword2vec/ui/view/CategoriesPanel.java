package ru.cbr.customcorpusword2vec.ui.view;

import ru.cbr.customcorpusword2vec.ui.viewmodel.CategoriesPanelModel;

import javax.swing.*;
import java.awt.*;

public class CategoriesPanel extends JPanel {
    private final CategoriesPanelModel model;

    /**
     * Для создания исходной панели и наполнения ее лэйблами
     * с перечисленными категориями
     * @param categories например, ["button", "other"]
     */
    public CategoriesPanel(java.util.List<String> categories) {
        super(new FlowLayout(FlowLayout.LEADING));
        model = new CategoriesPanelModel(categories, this); // заполняет Categories.LIST
    }

    /**
     * Для создания клона (новой панели с таким же набором лэйблов)
     */
    private CategoriesPanel(CategoriesPanelModel model) {
        super(new FlowLayout());
        this.model = model;
    }

    public CategoriesPanel clone() {
        CategoriesPanel clone = new CategoriesPanel(model);
        model.cloneLabelsTo(clone);
        return clone;
    }

    /**
     * @return "button: 25.46  <FONT color=#036600>other: 74.54</FONT>"
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (Component c : getComponents()) {
            if (c instanceof JLabel) {
                JLabel catLabel = (JLabel) c;
                boolean isMax = model.hasMaxPercFont(catLabel);
                String text = catLabel.getText(); // например, "button: 25.46"
                buf.append(isMax ? String.format("<FONT color=#036600>%s</FONT>", text) : text);
            }
            buf.append("  ");
        }
        return buf.toString();
    }
}
