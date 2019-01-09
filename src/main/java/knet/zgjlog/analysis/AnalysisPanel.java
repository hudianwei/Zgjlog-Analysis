package knet.zgjlog.analysis;

import com.eltima.components.ui.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static knet.zgjlog.analysis.SetGridBagConstraints.setConstraints;

/**
 * @Description:
 * @author: HU
 * @date: 2018/11/30 15:18
 */
public class AnalysisPanel extends JPanel {
    /**
     * 一个主程序对应一个面板内容，所以用静态域
     */
    private static JPanel jpanelQuery;//查询面板
    //private static JPanel jpanelFeatures;//功能面板如导出Excel
    private static JPanel jpanelResult;//结果面板
    private static JPanel jpanelDataSource;
    private static JTable jtableResult;
    private static JComboBox jcomboSelect;
    private static DatePicker jbuttonDatePickerStart;
    private static DatePicker jbuttonDatePickerEnd;
    private static JButton jbuttonAnalysis;
    private static JButton jbuttonMapping;
    private static JButton jbuttonOutExcel;
    private static JButton jbuttonDataSource;

    public AnalysisPanel() throws ParseException {
        // 选择项初始化
        jcomboSelect = SelectComponent.getComboBox();
        //设置默认时间
       /* SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
        Date date = dateformat.parse("2017-01-01");*/
        //设置当前时间减去一年
        Date date = DateUtils.cutDate(new Date(), -1);
        jbuttonDatePickerStart = DatePickerComponent.getDatePicker(date);
        jbuttonDatePickerEnd = DatePickerComponent.getDatePicker(new Date());
        // 设置数据源
        jbuttonDataSource = ButtonComponent.getDataSourceButton();
        //查询数据
        jbuttonAnalysis = new JButton("查询");
        jbuttonAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startTime = jbuttonDatePickerStart.getText();
                String endTime = jbuttonDatePickerEnd.getText();
                Object analysisTitle = jcomboSelect.getSelectedItem();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                try {
                    Date startDate = dateFormat.parse(startTime);
                    Date endDate = dateFormat.parse(endTime);
                    Date dateNow = new Date();
                    if (startDate.after(endDate)) {
                        JOptionPane.showMessageDialog(null, "起始时间不能超过结束时间!", "消息!", 1);
                    } else if (endDate.after(dateNow)) {
                        JOptionPane.showMessageDialog(null, "结束时间不能超过当前时间!", "消息!", 1);
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

            }
        });

        //导出Excel
        jbuttonOutExcel = ButtonComponent.getOutExcelButton();
        //绘制图像
        jbuttonMapping = ButtonComponent.getMappingButton();
        //Table初始化
        jtableResult = TextComponent.getJTable();
        // 设置面板
        init_panel();
        init_frame();
    }

    //JPanel面板初始化
    private static void init_panel() {
        jpanelDataSource = new JPanel();
        jpanelQuery = new JPanel();
        //jpanelFeatures = new JPanel();
        jpanelResult = new JPanel();

        //设置面板边框
        jpanelDataSource.setBorder(BorderFactory.createEtchedBorder());
        jpanelQuery.setBorder(BorderFactory.createEtchedBorder());
        //jpanelFeatures.setBorder(BorderFactory.createEtchedBorder());
        //jpanelMenu.setBorder(BorderFactory.createEtchedBorder());
        jpanelResult.setBorder(BorderFactory.createEtchedBorder());
    }

    //frame初始化
    private void init_frame() {

        //添加布局
        setLayout(new GridBagLayout());
        add(jpanelDataSource, setConstraints(0, 0, 1, 1, 1, 0));
        add(jpanelQuery, setConstraints(0, 1, 1, 1, 1, 0));
        //add(jpanelFeatures, setConstraints(0, 2, 1, 1, 1, 0));
        // add(jpanelMenu, setConstraints(0, 0, 1, 3, 1, 0));
        add(jpanelResult, setConstraints(0, 2, 5, 5, 4, 1));
        //数据处理
        jpanelDataSource.setLayout(new GridLayout());
        jpanelDataSource.add(jbuttonDataSource);
        //查询面板
        jpanelQuery.setLayout(new GridLayout());
        jpanelQuery.add(jcomboSelect, setConstraints(0, 0, 1, 1, 0, 0));
        jpanelQuery.add(jbuttonDatePickerStart, setConstraints(1, 0, 1, 1, 0, 0));
        //jpanelQuery.add(new JLabel("开始--结束"));
        jpanelQuery.add(jbuttonDatePickerEnd, setConstraints(2, 0, 1, 1, 0, 0));
        jpanelQuery.add(jbuttonAnalysis, setConstraints(3, 0, 1, 1, 0, 0));
        //功能面板
        //jpanelFeatures.setLayout(new GridLayout());
        //jpanelFeatures.add(jbuttonOutExcel, setConstraints(0, 0, 1, 1, 0, 1));
       // jpanelFeatures.add(jbuttonMapping, setConstraints(1, 1, 1, 1, 0, 1));

        //菜单面板

     /* jpanelMenu.setLayout(new GridBagLayout());
        jpanelMenu.add(jcomboSelect, setConstraints(0, 0, 1, 1, 0, 1));
        jpanelMenu.add(datePicker, setConstraints(1, 0, 1, 1, 0, 1));
        jpanelMenu.add(jbuttonAnalysis, setConstraints(2, 0, 1, 1, 1, 1));
        jpanelMenu.add(jbuttonOutExcel, setConstraints(3, 0, 1, 1, 1, 1));
        jpanelMenu.add(jbuttonMapping, setConstraints(4, 0, 1, 1, 1, 1));*/
        //结果面板
        jpanelResult.setLayout(new GridBagLayout());
        jpanelResult.add(jtableResult, setConstraints(0, 0, 0, 0, 1, 1));
    }


}
