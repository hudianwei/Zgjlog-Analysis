package knet.zgjlog.analysis;

import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

/**
 * @Description:从数据库服务器拿到rec数据到本地。分windows服务器和linux服务器
 * @author: HU
 * @date: 2018/12/4 13:15
 */
public class DownLoadLogData {
    private static ChannelSftp channelSftpThis;
    private  static Logger logger = Logger.getLogger(DownLoadLogData.class);
    /**
     * @Description: 通过ssh连接服务器，使用sftp
     * @Param: [user, passwd, host, port]
     * @Return: boolean
     * @Author: HU
     * @Date: 2018/12/4 16:04
     */
    public static ChannelSftp Serverconnect(String user, String passwd, String host, String port) {
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, Integer.valueOf(port));
            session.setPassword(passwd);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftpThis = channelSftp;
        } catch (JSchException e) {
            e.printStackTrace();
            System.out.println("connect error !");
            logger.error("connect error:" + e.getMessage());
        }
        return channelSftp;
    }

    /**
     * @Description: 关闭sftp
     * @Param: []
     * @Return: void
     * @Author: HU
     * @Date: 2018/12/4 16:09
     */
    public void disconnect() {

        if (channelSftpThis != null) {
            if (channelSftpThis.isConnected()) {
                channelSftpThis.disconnect();
            } else if (channelSftpThis.isClosed()) {
                System.out.println("sftp is closed already!");
                logger.error("sftp is closed already!");
            }
        }
    }

    /**
     * 上传文件 流 本地文件路径 remotePath 服务器路径
     */
    public void upload(File file, String remotName, String remotePath) throws Exception {
        try {
            // File file = new File(localPath);
            if (file.isFile()) {
                // System.out.println("localFile : " + file.getAbsolutePath());
                String rpath = remotePath; // 服务器需要创建的路径
                try {
                    createDir(rpath);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("创建路径失败：" + rpath);
                }
                // this.sftp.rm(file.getName());
                channelSftpThis.cd(remotePath);
                channelSftpThis.put(new FileInputStream(file), remotName);
            }
        } catch (FileNotFoundException e) {

            throw new Exception("上传文件没有找到");
        } catch (SftpException e) {
            throw new Exception("上传ftp服务器错误");
        }
    }

    public static void downLoad(String localpath, String serverpath) throws Exception {
        try {

            //windows环境和linux环境获取目录下的文件方式不一样
            //  if (IsWindowOrLinux.isWindowsOrLinux().equals("windows")) {
            Vector vector = channelSftpThis.ls(serverpath);
            for (Object object : vector) {
                if (object instanceof ChannelSftp.LsEntry) {
                    String fileName = ((com.jcraft.jsch.ChannelSftp.LsEntry) object).getFilename();
                    if (".".equals(fileName) || "..".equals(fileName)) {
                        continue;
                    }
                    //在windows环境下创建文件夹
                    // String windownsPath=localpath+"\\logdata"+"\\"+fileName;
                    String windownsPath = "";
                    String[] paths = new String[]{localpath, "logdata", fileName};
                    for (String path : paths) {
                        windownsPath += path + "\\";
                        File fileLocal = new File(windownsPath);
                        //如果文件不存在，则新建一个
                        if (!fileLocal.exists()) {
                            try {
                                fileLocal.mkdir();
                                fileLocal.createNewFile();
                            } catch (Exception e) {
                                e.printStackTrace();
                                logger.error("创建路径失败：" + windownsPath);
                                throw new Exception("创建路径失败：" + windownsPath);

                            }
                        }
                    }
                    Vector vectorLog = channelSftpThis.ls(serverpath + fileName);
                    for (Object log : vectorLog) {
                        String fileNameLog = ((com.jcraft.jsch.ChannelSftp.LsEntry) log).getFilename();
                        if (".".equals(fileNameLog) || "..".equals(fileNameLog)) {
                            continue;
                        }
                        channelSftpThis.get(serverpath + "/" + fileName + "/" + fileNameLog, windownsPath);
                        logger.info("获取的日志文件:"+fileNameLog);
                    }
                }

            }

           /* } else if (IsWindowOrLinux.isWindowsOrLinux().equals("linux")) {
            }*/

        } catch (Exception e) {
            throw new Exception("获取文件失败!" + e.getMessage());
        }
    }

    /**
     * 创建一个文件目录
     */
    public static void createDir(String createpath) throws Exception {
        try {
            if (isDirExist(createpath)) {
                channelSftpThis.cd(createpath);
                return;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString())) {
                    channelSftpThis.cd(filePath.toString());
                } else {
                    // 建立目录
                    channelSftpThis.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    channelSftpThis.cd(filePath.toString());
                }
            }
            channelSftpThis.cd(createpath);
        } catch (SftpException e) {
            throw new Exception("创建路径错误：" + createpath);
        }
    }

    /**
     * 判断目录是否存在
     */
    public static boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = channelSftpThis.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

}
