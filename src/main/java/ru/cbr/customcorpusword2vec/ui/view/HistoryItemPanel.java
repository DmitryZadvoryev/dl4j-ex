package ru.cbr.customcorpusword2vec.ui.view;

import ru.cbr.customcorpusword2vec.ui.model.Categories;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HistoryItemPanel extends JPanel {

    public HistoryItemPanel(String text, CategoriesPanel refCatsPanel) {
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel1 = new JLabel();                        // time + duration
        jPanel1 = refCatsPanel.clone();                // a panel with category labels

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(246, 246, 246));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(2);
        jTextArea1.setTabSize(4);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setText(text);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText(
                DateTimeFormatter.ofPattern("dd.MM.uuuu HH.mm.ss").format(Categories.getLastChange())); // "10.01.2023 17:34:55 (28ms)"

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 388, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 23, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }// </editor-fold>


    // Variables declaration - do not modify
    private final JLabel jLabel1;
    private final CategoriesPanel jPanel1;
    private final JScrollPane jScrollPane1;
    private final JTextArea jTextArea1;
    // End of variables declaration

    //
    // GETTERS
    //


    public JTextArea getjTextArea1() {
        return jTextArea1;
    }

    public String toHtmlString() {
        return "<FONT size=2 color=gray>" + jLabel1.getText() + "</FONT>" +
                "<br>" +
                "<FONT size=3>" + jTextArea1.getText() + "</FONT>" +
                "<br>" + jPanel1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryItemPanel that = (HistoryItemPanel) o;
        return Objects.equals(jTextArea1.getText(), that.jTextArea1.getText()) && Objects.equals(jLabel1.getText(), that.jLabel1.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(jLabel1.getText(), jTextArea1.getText());
    }

}
