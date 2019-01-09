package knet.zgjlog.analysis;

import javax.swing.*;
import java.awt.*;

/**
 * @Description:
 * @author: HU
 * @date: 2018/11/30 15:23
 */
public class TextComponent {

    //定义文本框
    public static JTextArea sortText;
    public static JTextArea resultText;
    //public  static  JTable
    //定义文本属性
    private static final int TEXT_ROWS = 10;
    private static final int TEXT_COLUMNS = 100;
    private static final Font songFont = new Font("宋体", Font.PLAIN, 16);

    //JText文本框 属性初始化
    public TextComponent() {
    }

    //获取排结果文本框-滚动窗格
    public static JScrollPane getResultTextPanel() {
        resultText = setText(resultText);
        return new JScrollPane(resultText);
    }

    //设置文本框属性
    public static JTextArea setText(JTextArea textArea) {
        //初始化文本框
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);

        //设置字体格式
        textArea.setFont(songFont);

        //设置自动换行
        textArea.setLineWrap(true);

        return textArea;
    }

    public static JTable getJTable() {
        JTable jTable = new JTable();
        return jTable;

    }
}
