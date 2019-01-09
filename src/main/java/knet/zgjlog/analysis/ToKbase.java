package knet.zgjlog.analysis;

import com.jcraft.jsch.ChannelSftp;
import com.kbase.jdbc.Connection;
import com.kbase.jdbc.ConnectionImpl;
import com.kbase.jdbc.PreparedStatement;
import com.kbase.jdbc.ResultSetImpl;

import javax.swing.*;
import java.io.File;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:连接kbase数据库生成rec文件
 * @author: HU
 * @date: 2018/12/4 8:37
 */
public class ToKbase {
    private static Connection connection;

    public static void kbaseConnection(String ip, String username, String password) {

        if (DataUtil.isBlank(username) || DataUtil.isBlank(ip)) {
            JOptionPane.showMessageDialog(null, "ip,用户名不能为空！", "消息!", 1);
            return;
        }
        String driverName = "com.kbase.jdbc.Driver";
        String url = String.format("jdbc:kbase://%s", ip);
        try {
            // 注册驱动
            Class.forName(driverName);
            // 建立连接 并返回
            connection = (Connection) DriverManager.getConnection(url, username, password);
            if (((ConnectionImpl) connection).getError() < 0) {
                JOptionPane.showMessageDialog(null, "kbase数据库连接失败!", "消息!", 1);
                return;
            }

        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.getMessage());
        }
    }

    //通过kbase获取操作系统
    public static void getSystemType() throws SQLException {
        try {


            String sql = "select TABLENAME,TABLEPATH,ISVIEW from SYS_HOTSTAR_SYSTEM  where DBASE=''";

            PreparedStatement pst = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new SQLException("执行SQL失败" + e.getMessage());
        }
    }


    public static void getRec(String database, String recpath) throws Exception {

        //查询服务器上是否存在该目录,如果不存在则创建,
        //if (IsWindowOrLinux.isWindowsOrLinux().equals("windows")) {
            //windows系统特殊，因为没有ssh协议需要通过freesshd软件搭建ssh服务器，目录在搭建服务器时被提前设置好
       /* } else if (IsWindowOrLinux.isWindowsOrLinux().equals("linux")) {
            if (!isDirExist(recpath)) {
                createDir(recpath);
            }
        }*/
        try {

            String sql = "select TABLENAME,TABLEPATH,ISVIEW from SYS_HOTSTAR_SYSTEM  where DBASE='" + database + "'";
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<String> tableList = new ArrayList<>();
            while (rs.next()) {
                tableList.add(rs.getString("TABLENAME"));
            }
            for (String table : tableList) {
                String recDataPath = recpath + "\\" + table + ".txt";
                connection.createStatement().execute("select * from " + table + " into " + recDataPath + "");
            }
            // PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("select * from UU_STREAM into 'E:\\text1.txt'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
