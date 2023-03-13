package ru.cbr.customcorpusword2vec.ui.view;

import ru.cbr.customcorpusword2vec.ui.model.NS;
import ru.cbr.customcorpusword2vec.ui.viewmodel.MainFrameModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class MainFrame extends JFrame {
    private final MainFrameModel model;
    private final DefaultListModel<HistoryItemPanel> historyModel = new DefaultListModel<>();

    public MainFrame(java.util.List<String> categoryNames, NS ns) {
        model = new MainFrameModel(this, ns);
        jScrollPane2 = new JScrollPane();   // wraps input below
        jTextArea = new JTextArea();        // input
        jButton1 = new JButton();           // grab $0
        jButton2 = new JButton();           // note - add the result in the list
        jButton3 = new JButton();           // save
        jScrollPane1 = new JScrollPane();   // wraps history (list below)
        jList1 = new JList<>();             // history
        jPanel1 = new CategoriesPanel(categoryNames);    // panel with categories

        jTextArea.setColumns(20);
        jTextArea.setFont(new Font("Consolas", 0, 12)); // NOI18N
        jTextArea.setLineWrap(true);
        jTextArea.setRows(2);
        jTextArea.setTabSize(4);
        jTextArea.setToolTipText("type text or grab $0 by driver");
        jTextArea.setWrapStyleWord(true);
        jTextArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jTextArea.setDragEnabled(true);
        jTextArea.setName("input"); // NOI18N
        jTextArea.getDocument().addDocumentListener(new DocumentListener() {
            // <editor-fold defaultstate="collapsed" desc="model.onTextChanged(..)">
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                model.onTextChanged(documentEvent);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                model.onTextChanged(documentEvent);
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) { }// </editor-fold>
        });
        jScrollPane2.setViewportView(jTextArea);

        Font buttonFont = new Font("Consolas", 0, 12); // NOI18N

        jButton1.setFont(buttonFont);
        jButton1.setText("$0");
        jButton1.addActionListener(model::onClick_$0);

        jButton2.setFont(buttonFont);
        jButton2.setText("note");
        jButton2.addActionListener(model::onClick_note);

        jButton3.setFont(buttonFont);
        jButton3.setText("save");
        jButton3.addActionListener(model::onClick_save);

        jList1.setModel(historyModel);
//        jList1.setModel(new AbstractListModel<String>() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public String getElementAt(int i) { return strings[i]; }
//        });
        jScrollPane1.setViewportView(jList1);

        jPanel1.setFont(new Font("Consolas", 0, 12));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 48, Short.MAX_VALUE)
                                                .addComponent(jButton3))
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );

        setTitle("Control's type identifier");
        setMinimumSize(new Dimension(400, 160));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    // Variables declaration - do not modify
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JList<HistoryItemPanel> jList1;
    private CategoriesPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea;
    // End of variables declaration


    // <editor-fold defaultstate="collapsed" desc="Control getters">
    public JButton getjButton1() {
        return jButton1;
    }

    public JButton getjButton2() {
        return jButton2;
    }

    public JButton getjButton3() {
        return jButton3;
    }

    public JList<HistoryItemPanel> getjList1() {
        return jList1;
    }

    public CategoriesPanel getjPanel1() {
        return jPanel1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public JTextArea getTextArea() {
        return jTextArea;
    }

    public DefaultListModel<HistoryItemPanel> getHistoryModel() {
        return historyModel;
    }

    // </editor-fold>
}

