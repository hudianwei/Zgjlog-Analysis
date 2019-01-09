package knet.zgjlog.analysis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description:
 * @author: HU
 * @date: 2018/11/30 15:22
 */
public class ButtonComponent {
    private static JButton analysisButton;
    private static JButton mappingButton;
    private static JButton outExcelButton;
    private static JButton DataSourceButton;

    public static JButton getAnalysisButton( String start,String end) {
        analysisButton = new JButton("查询");
       analysisButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, start, "消息!", 1);
           }
       });
        return analysisButton;
    }

    public static JButton getMappingButton() {
        mappingButton = new JButton("绘制图像");
        return mappingButton;
    }

    public static JButton getOutExcelButton() {
        outExcelButton = new JButton("导出Excel");
        return outExcelButton;
    }

    public static JButton getDataSourceButton() {
        DataSourceButton = new JButton("设置数据源");
        DataSourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataConfigJPanel(null, "");
            }
        });
        // DataSourceButton.addActionListener(new ExDialog(null,""));
        return DataSourceButton;
    }
}

