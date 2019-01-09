package knet.zgjlog.analysis;

import java.awt.*;

/**
 * @Description:
 * @author: HU
 * @date: 2018/12/3 14:58
 */
public class SetGridBagConstraints {
    //设置布局管理器
    public static GridBagConstraints setConstraints(int gx, int gy, int gh, int gw, int wx, int wy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gx;
        constraints.gridy = gy;
        constraints.gridheight = gh;
        constraints.gridwidth = gw;
        constraints.weightx = wx;
        constraints.weighty = wy;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }
}
