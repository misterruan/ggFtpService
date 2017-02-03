package com.ytx.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by rock on 2016/12/28.
 * 用于ftp的方式下载文件工具类
 */
public class DownloadFileByFtpUtils {
    private static Logger logger = LoggerFactory.getLogger(DownloadFileByFtpUtils.class);

    private static FTPClient ftpClient = new FTPClient();
    //系统编码方式
    private static String encoding = System.getProperty("file.encoding");

    /**
     * Description: 从FTP服务器下载文件
     *
     * @Version1.0
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param fileNameList
     *            要下载的文件名列表
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String url, int port, String username,
                                   String password, String remotePath, List<String> fileNameList,
                                   String localPath) throws IOException {
        boolean result = false;
        try {
            int reply;
            ftpClient.setControlEncoding(encoding);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.connect(url, port);
            // 登录
            ftpClient.login(username, password);
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.error("DownloadFileByFtpUtils#downFile:FTP server refused connection.");
                return result;
            }
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(encoding),"iso-8859-1"));
            // 获取文件列表
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                //遍历文件名列表
                for (String fileName : fileNameList) {
                    if (ff.getName().equals(fileName)) {
                        File localFile = new File(localPath + "/" + ff.getName());
                        if(localFile.exists()){
                            //本地已经存在同名文件
                            result = true;
                        }else {
                            OutputStream is = new FileOutputStream(localFile);
                            ftpClient.retrieveFile(ff.getName(), is);
                            is.flush();
                            is.close();
                        }
                    }
                }
            }
            ftpClient.logout();
            result = true;
        }  finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }





}
