package knet.zgjlog.analysis;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description:
 * @author: HU
 * @date: 2018/12/3 11:46
 */
public class DataConfigJPanel extends JDialog {
    private Logger logger = Logger.getLogger(DataConfigJPanel.class);

    //第一个参数为拥有者，第二个参数为显示的String内容
    public DataConfigJPanel(JFrame owner, String content) {
        super(owner, "配置", true);
        JPanel jp1 = new JPanel(new GridLayout(7, 2, 20, 10));//9行2列 水平间距20 垂直间距10
        //第一行
        JLabel jl1 = new JLabel("服务器：");
        jl1.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf1 = new JTextField(30);
        jtf1.setText("192.168.105.79");
        jp1.add(jl1);
        jp1.add(jtf1);
        //第二行
        JLabel jl2 = new JLabel("服务器账号：");
        jl2.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf2 = new JTextField(30);
        jtf2.setText("root");
        jp1.add(jl2);
        jp1.add(jtf2);
        //第三行
        JLabel jl3 = new JLabel("服务器密码：");
        jl3.setHorizontalAlignment(SwingConstants.RIGHT);
        JPasswordField jtf3 = new JPasswordField(30);
        jtf3.setText("P@$$W0rd1113");
        jp1.add(jl3);
        jp1.add(jtf3);
        //第四行
        JLabel jl4 = new JLabel("服务器端口号：");
        jl4.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf4 = new JTextField(30);
        jtf4.setText("22");
        jp1.add(jl4);
        jp1.add(jtf4);
        //第五行
        JLabel jl5 = new JLabel("日志存储目录(服务)：");
        jl5.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf5 = new JTextField(30);
        // jtf8.setText("E:\\zgjlogrecdata\\");
        jtf5.setText("/usr/local/apps/odatax-web-tomcat/logfiles/");
        jp1.add(jl5);
        jp1.add(jtf5);
        //第六行
        JLabel jl6 = new JLabel("日志存储目录(本地)：");
        jl6.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField jtf6 = new JTextField(30);
        jtf6.setText("E:\\zgjloganalysisdata");
        jp1.add(jl6);
        jp1.add(jtf6);
        add(jp1);
        JButton ok = new JButton("开始执行");
        jp1.add(ok);
        JProgressBar jprogressSort = ProgressComponent.getProgressBar();
        jp1.add(jprogressSort);
        add(jp1);
        //点击ok时，关闭对话框
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            jprogressSort.setValue(10);
                            //连接远程服务器
                            DownLoadLogData.Serverconnect(jtf2.getText(), String.valueOf(jtf3.getPassword()), jtf1.getText(), jtf4.getText());
                            jprogressSort.setValue(20);
                            //从服务上下载日志到本地
                            DownLoadLogData.downLoad(jtf6.getText(), jtf5.getText());
                            jprogressSort.setValue(50);
                            //处理日志数据


                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        //ok.addActionListener(e -> setVisible(false));
        setLayout(new FlowLayout());//流式布局
        setSize(500, 400);//大小
        setLocationRelativeTo(null);//居中
        setVisible(true);
    }

   /* @Override
    public void menuSelected(MenuEvent e) {
        JOptionPane.showMessageDialog(null, "不能为空！", "消息!", 1);
    }*/


}
