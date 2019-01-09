package knet.zgjlog.analysis;

import javax.swing.*;
import java.awt.*;

/**
 * @Description:
 * @author: HU
 * @date: 2018/11/30 15:26
 */
public class ProgressComponent {
    private static JProgressBar progressBar;

    public ProgressComponent() {

    }

    public static JProgressBar getProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        //计算某项操作的百分比，并显示出来
        progressBar.setStringPainted(true);
        //progressBar.setBackground(Color.cyan);
        return progressBar;
    }

    //更新进度条——线程安全
    public void setProgress() {
    }
}
