package knet.zgjlog.analysis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description:
 * @author: HU
 * @date: 2018/12/8 12:24
 */
public class DataConfigJPanelOld extends JDialog {
    //第一个参数为拥有者，第二个参数为显示的String内容
    public DataConfigJPanelOld(JFrame owner, String content) {
        super(owner, "配置", true);
        JPanel jp1 = new JPanel(new GridLayout(9, 2, 20, 10));//9行2列 水平间距20 垂直间距10
        //第一行
        JLabel jl1 = new JLabel("服务器：");
        jl1.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf1 = new JTextField(20);
        jtf1.setText("192.168.105.71");
        jp1.add(jl1);
        jp1.add(jtf1);
        //第二行
        JLabel jl2 = new JLabel("服务器账号：");
        jl2.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf2 = new JTextField(20);
        jtf2.setText("root");
        jp1.add(jl2);
        jp1.add(jtf2);
        //第三行
        JLabel jl3 = new JLabel("服务器密码：");
        jl3.setHorizontalAlignment(SwingConstants.RIGHT);
        JPasswordField jtf3 = new JPasswordField(20);
        jtf3.setText("sa");
        jp1.add(jl3);
        jp1.add(jtf3);
        //第四行
        JLabel jl4 = new JLabel("服务器端口号：");
        jl4.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf4 = new JTextField(20);
        jtf4.setText("22");
        jp1.add(jl4);
        jp1.add(jtf4);
        //第五行
        JLabel jl5 = new JLabel("日志数据库：");
        jl5.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf5 = new JTextField(20);
        jtf5.setText("ZGJLOG");
        jp1.add(jl5);
        jp1.add(jtf5);
        //第六行
        JLabel jl6 = new JLabel("kbase数据库用户名：");
        jl6.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf6 = new JTextField(20);
        jtf6.setText("DBOWN");
        jp1.add(jl6);
        jp1.add(jtf6);
        //第七行
        JLabel jl7 = new JLabel("kbase数据库密码：");
        jl7.setHorizontalAlignment(SwingConstants.RIGHT);
        JPasswordField jtf7 = new JPasswordField(20);
        jp1.add(jl7);
        jp1.add(jtf7);
        //第八行
        JLabel jl8 = new JLabel("导出Rec路径(服务器上)：");
        jl8.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf8 = new JTextField(20);
        // jtf8.setText("E:\\zgjlogrecdata\\");
        jtf8.setText("E:\\rec");
        jp1.add(jl8);
        jp1.add(jtf8);
        //第九行
        JLabel jl9 = new JLabel("数据存储路径(本地)：");
        jl9.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf9 = new JTextField(20);
        jtf9.setText("E:\\zgjloganalysisdata");
        jp1.add(jl9);
        jp1.add(jtf9);
        add(jp1);
        //第十行
        JPanel jp2 = new JPanel(new GridLayout(1, 1, 20, 10));
        JLabel jl10 = new JLabel("(数据需要通过导出Rec，然后进行处理，可能耗时较长!)");
        jl10.setHorizontalAlignment(SwingConstants.CENTER);
        jp2.add(jl10);
        add(jp2);
        JPanel jp21 = new JPanel(new GridLayout(1, 1, 20, 10));
        JLabel jl71 = new JLabel("(设置路径的时候确保该目录有读写的权限!)");
        jl71.setHorizontalAlignment(SwingConstants.CENTER);
        jp21.add(jl71);
        add(jp21);

        JPanel jp3 = new JPanel(new GridLayout(1, 1, 20, 10));
        JButton ok = new JButton("开始执行");
        //点击ok时，关闭对话框
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //连接kbase数据库
                    ToKbase.kbaseConnection(jtf1.getText(), jtf6.getText(), String.valueOf(jtf7.getPassword()));
                    //得到服务器的操作系统类型Windows?Linux?
                    //连接远程服务器
                    DownLoadLogData.Serverconnect(jtf2.getText(), String.valueOf(jtf3.getPassword()), jtf1.getText(), jtf4.getText());
                    //从kbase数据库中导出Rec
                    ToKbase.getRec(jtf5.getText(), jtf8.getText());
                    //从服务上下载日志到本地
                    DownLoadLogData.downLoad(jtf9.getText(), jtf8.getText());
                    //处理日志数据

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        //ok.addActionListener(e -> setVisible(false));
        jp3.add(ok);
        add(jp3);

        JPanel jp4 = new JPanel(new GridLayout(1, 1, 20, 10));
        JProgressBar jprogressSort = ProgressComponent.getProgressBar();
        jp4.add(jprogressSort);
        add(jp4);
        setLayout(new FlowLayout());//流式布局
        setSize(321, 500);//大小
        setLocationRelativeTo(null);//居中
        setVisible(true);
    }

   /* @Override
    public void menuSelected(MenuEvent e) {
        JOptionPane.showMessageDialog(null, "不能为空！", "消息!", 1);
    }*/


}
