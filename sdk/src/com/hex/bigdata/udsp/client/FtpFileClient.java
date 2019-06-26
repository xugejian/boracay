package com.hex.bigdata.udsp.client;

import com.hex.bigdata.udsp.config.SdkFtpClientConfig;
import com.hex.bigdata.udsp.util.SdkFtpHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class FtpFileClient {

    private static Logger logger = LogManager.getLogger (FtpFileClient.class);

    private SdkFtpHelper sdkFtpHelper;

    private FtpFileClient() {
    }

    private FtpFileClient(String hostName, int port, String ftpUserName, String ftpPassword) {
        try {
            sdkFtpHelper = new SdkFtpHelper (hostName, port, ftpUserName, ftpPassword);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    private FtpFileClient(SdkFtpHelper sdkFtpHelper) {
        this.sdkFtpHelper = sdkFtpHelper;
    }

    public SdkFtpHelper getSdkFtpHelper() {
        return sdkFtpHelper;
    }

    public void setSdkFtpHelper(SdkFtpHelper sdkFtpHelper) {
        this.sdkFtpHelper = sdkFtpHelper;
    }

    /**
     * 创建自定义的FtpFileClient
     *
     * @param hostName
     * @param port
     * @param ftpUserName
     * @param ftpPassword
     * @return
     */
    public static FtpFileClient createFtpClient(String hostName, int port, String ftpUserName, String ftpPassword) {
        return new FtpFileClient (hostName, port, ftpUserName, ftpPassword);
    }

    /**
     * 创建默认的FtpFileClient
     *
     * @return
     */
    public static FtpFileClient createFtpClient() {
        return new FtpFileClient (new SdkFtpHelper ());
    }

    /**
     * 检查文件是否达到
     * 本方法通过检查标志文件是否生成来判断文件是否传输完成、判断文件是否达到
     *
     * @param filePath
     * @return
     */
    public boolean checkFileArrived(String filePath) throws Exception {
        if (StringUtils.isBlank (filePath)) {
            throw new IllegalArgumentException ("传入参数为空！");
        }
        int index = filePath.lastIndexOf (".");
        filePath = filePath.substring (0, index);
        filePath = filePath + SdkFtpClientConfig.getLogFilePostfix ();
        return checkFileExist (filePath);
    }

    /**
     * 下载文件到指定目录，并重新命名文件
     *
     * @param filePath        下载文件ftp服务器全路径
     * @param targetFileName  目标文件名
     * @param targetDirectory 下载目标文件夹
     * @throws Exception
     */
    public void downloadFile(String filePath, String targetFileName, String targetDirectory) throws Exception {
        if (StringUtils.isBlank (filePath)) {
            throw new IllegalArgumentException ("参数filePath不能为空");
        }
        if (StringUtils.isBlank (targetDirectory)) {
            throw new IllegalArgumentException ("参数targetDirectory不能为空");
        }
        try {
            File downloadFile = new File (filePath);
            String downloadFileName = downloadFile.getName ();
            String remotePath = downloadFile.getParent ();
            remotePath = remotePath.replaceAll ("\\\\", "\\/");
            sdkFtpHelper.connectFTPServer ();
            sdkFtpHelper.downloadFile (remotePath, downloadFileName, targetDirectory, targetFileName);
        } finally {
            sdkFtpHelper.closeFTPClient ();
        }
    }

    /**
     * 下载文件到目标文件夹
     *
     * @param filePath
     * @param targetDirectory
     */
    public void downloadFile(String filePath, String targetDirectory) throws Exception {
        this.downloadFile (filePath, null, targetDirectory);
    }

    /**
     * 从FTP服务器获取文件流
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public InputStream downloadFile(String filePath) throws Exception {
        if (StringUtils.isBlank (filePath)) {
            throw new IllegalArgumentException ("参数filePath不能为空");
        }
        sdkFtpHelper.connectFTPServer ();
        return sdkFtpHelper.downloadFile (filePath);
    }

    /**
     * 从FTP服务器获取文件流
     *
     * @param filePath
     * @param output
     * @return
     * @throws IOException
     */
    public boolean downloadFile(String filePath, OutputStream output) throws Exception {
        if (StringUtils.isBlank (filePath)) {
            throw new IllegalArgumentException ("参数filePath不能为空");
        }
        if (output == null) {
            throw new IllegalArgumentException ("参数output不能为空");
        }
        try {
            sdkFtpHelper.connectFTPServer ();
            return sdkFtpHelper.downloadFile (filePath, output);
        } finally {
            sdkFtpHelper.closeFTPClient ();
        }
    }

    /**
     * 检查文件是否存在
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    private boolean checkFileExist(String filePath) throws Exception {
        try {
            sdkFtpHelper.connectFTPServer ();
            File file = new File (filePath);
            String fileName = file.getName ();
            String parentPath = file.getParent ();
            parentPath = parentPath.replaceAll ("\\\\", "\\/");
            List<String> nameList = sdkFtpHelper.getListFileName (parentPath);
            if (nameList == null || nameList.size () == 0) {
                return false;
            }
            for (String item : nameList) {
                if (filePath.equals (item)) {
                    return true;
                }
            }
            return false;
        } finally {
            sdkFtpHelper.closeFTPClient ();
        }
    }

    /**
     * 关闭FTP连接
     *
     * @throws Exception
     */
    public void closeFTPClient() throws Exception {
        sdkFtpHelper.closeFTPClient ();
    }
}
