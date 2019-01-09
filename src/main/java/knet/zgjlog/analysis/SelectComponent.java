package knet.zgjlog.analysis;

import javax.swing.*;

/**
 * @Description:
 * @author: HU
 * @date: 2018/11/30 15:24
 */
public class SelectComponent {
    //排序方法
    private static String[] sortType = {"使用总体趋势分析", "检索关键词分析", "学科偏好分析", "高下载文献主题分析 ", "资源类型阅读偏好", "主要应用群体"};
    //下拉组合框
    private static JComboBox comboBox;
    //复选框面板
    private static JCheckBox[] checkBoxes;
    private static JPanel checkBoxPanel;

    //获取JComboBox组合框
    public static JComboBox getComboBox() {
        comboBox = new JComboBox();
        comboBox.setEditable(false);

        //为组合框添加Item
        for (int i = 0; i < sortType.length; i++) {
            comboBox.addItem(sortType[i]);
        }
        return comboBox;
    }
}
