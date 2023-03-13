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
        super(new FlowLayout());
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

}
