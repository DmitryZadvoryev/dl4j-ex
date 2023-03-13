package ru.cbr.customcorpusword2vec.ui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CategoryLabel extends JLabel {

    public CategoryLabel(String text, Font font) {
        super(text);
        setFont(font);
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }

}
