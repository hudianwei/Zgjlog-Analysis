package knet.zgjlog.analysis;
import javax.swing.*;
import java.text.ParseException;

/**
 * @Description:
 * @author: HU
 * @date: 2018/11/30 14:43
 */
public class AnalysisFrame extends JFrame {
    //private static JMenuBar menuBar;

    public AnalysisFrame() throws ParseException {
        // 添加菜单
       /* initMenu();
        setJMenuBar(menuBar);*/

        // 设置内容面板
        AnalysisPanel analysisPanel = new AnalysisPanel();
        setContentPane(analysisPanel);
        setLocation(200, 200);
        pack();
    }

    private void initMenu() {
       /*  menuBar = new JMenuBar();
        JMenu dataSource=new JMenu("设置数据源");
        dataSource.addMenuListener(new ExDialog(null,""));
       // 一级菜单
        JMenu filemenu = new JMenu("File");
        JMenu editmenu = new JMenu("Edit");
        JMenu aboutmenu = new JMenu("About");

        // 二级菜单
        JMenuItem newitem = new JMenuItem("New...");
        JMenuItem closeitem = new JMenuItem("Close...");
        JMenuItem setitem = new JMenuItem("Setting...");

        // 添加二级菜单
        filemenu.add(newitem);
        filemenu.addSeparator();
        filemenu.add(closeitem);
        filemenu.addSeparator();
        editmenu.add(setitem);
        editmenu.addSeparator();

        // 添加一级菜单
        menuBar.add(filemenu);
        menuBar.add(editmenu);
        menuBar.add(aboutmenu);
      menuBar.add(dataSource);*/
    }
}
