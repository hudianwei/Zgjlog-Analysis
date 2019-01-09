package knet.zgjlog.analysis;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

/**
 * @Description:
 * @author: HU
 * @date: 2018/11/30 14:39
 */
public class AnalysisMain {
    public static void main(String[] args) {
        // 设置外观
        SelectUI.beautyeye();

        EventQueue.invokeLater(() -> {
            JFrame frame = null;
            try {
                frame = new AnalysisFrame();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            frame.setTitle("最高人民检察院数字图书馆-应用分析");
            frame.setSize(1200, 700); //设置窗口的大小
            frame.setLocation(300, 200);//设置窗口的初始位置
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
