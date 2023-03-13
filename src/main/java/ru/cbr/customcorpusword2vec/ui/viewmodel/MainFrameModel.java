package ru.cbr.customcorpusword2vec.ui.viewmodel;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import ru.cbr.customcorpusword2vec.ui.model.Categories;
import ru.cbr.customcorpusword2vec.ui.model.NS;
import ru.cbr.customcorpusword2vec.ui.view.HistoryItemPanel;
import ru.cbr.customcorpusword2vec.ui.view.MainFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFrameModel {
    private final java.util.List<HistoryItemPanel> history;
    private final MainFrame mainFrame;
    private final NS ns;

    public MainFrameModel(MainFrame mainFrame, NS ns) {
        this.history = Collections.synchronizedList(new ArrayList<>());
        this.mainFrame = mainFrame;
        this.ns = ns;
    }

    public List<HistoryItemPanel> getHistory() {
        return history;
    }

    public void onTextChanged(DocumentEvent e) {
        SwingUtilities.invokeLater(() -> {
            ns.update(Categories.LIST, mainFrame.getTextArea().getText());
            Categories.reCalcPercentsAndFindMax();
        });
    }

    /**
     * Обработчик нажатия кнопки "$0" {@link MainFrame#getjButton1()}.
     * @implNote
     * В случае наличия драйвера получает текущий выделенный в DOM'е
     * элемент и выводит его текст в {@link MainFrame#getTextArea()}
     */
    public void onClick_$0(ActionEvent e) {
        mainFrame.getTextArea().setEditable(false);
        SwingUtilities.invokeLater(() -> {
            if (WebDriverRunner.hasWebDriverStarted()) {
                Object text$0 = ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(
                        "return $0.tagName.toLowerCase() + ' ' +  $0.getAttribute('class').toLowerCase()");
                mainFrame.getTextArea().setText((String) text$0);
            }
        });
        mainFrame.getTextArea().setEditable(true);
    }

    /**
     * Обработчик нажатия кнопки "note" {@link MainFrame#getjButton2()}.
     * @implNote
     * Из текущего текста и панели категорий создает {@link HistoryItemPanel}
     * и добавляет его в {@link #history}
     */
    public void onClick_note(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            HistoryItemPanel item = new HistoryItemPanel(mainFrame.getTextArea().getText(), mainFrame.getjPanel1());
            mainFrame.getHistoryModel().add(0, item);
        });
    }

    /**
     * Обработчик нажатия кнопки "save" {@link MainFrame#getjButton3()}.
     * @implNote
     * Сохраняет данные из {@link MainFrame#getjList1()} в текстовый файл
     */
    public void onClick_save(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            //todo
        });
    }

}
