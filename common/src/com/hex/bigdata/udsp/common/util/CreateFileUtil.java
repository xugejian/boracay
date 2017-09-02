package com.hex.bigdata.udsp.common.util;

import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by PC on 2017/4/18.
 */
public class CreateFileUtil {
    public static final String TEMP_DOWNLOAD = "TEMP_DOWNLOAD";
    public static final String DATA_FILE_SUFFIX = ".dat";
    public static final String FLG_FILE_SUFFIX = ".log";
    public static final String DATA_FILE_DELIMITER = "\036";
    public static final String FLG_FILE_DELIMITER = "|";
    public static final String FILE_ENCODING = "UTF-8";

    // 临时文件本地存储目录
    private static String tempfileLocalDir;

    static {
        FTPClientConfig.loadConf("goframe/udsp/udsp.config.properties");

        InputStream in = null;
        try {
            in = FTPClientConfig.class.getClassLoader().getResourceAsStream(
                    "goframe/udsp/udsp.config.properties");
            Properties props = new Properties();
            props.load(in);
            tempfileLocalDir = props.getProperty("temp.file.local.dir");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成分隔符格式文件
     *
     * @param delimiter
     * @param encoding
     * @param isPrintHeader
     * @return
     */
    public static void createDelimiterFile(List<Map<String, String>> records, String delimiter, String encoding, boolean isPrintHeader, String fileName) {
        // 生成数据文件
        String fileSizeAndNum = createDataFile(fileName, records, delimiter, encoding, isPrintHeader);
        // 生成标记文件
        createFlgFile(fileName, fileSizeAndNum);
    }

    /**
     * 生成分隔符格式文件
     *
     * @param delimiter
     * @param encoding
     * @param isPrintHeader
     * @return
     */
    public static void createDelimiterFile(ResultSet resultSet, String delimiter, String encoding, boolean isPrintHeader, String fileName) {
        if (StringUtils.isBlank(encoding)) {
            encoding = FILE_ENCODING;
        }
        /*
        生成数据文件
         */
        String fileSizeAndNum = createDataFile(fileName, resultSet, delimiter, encoding, isPrintHeader);
        // 生成标记文件
        createFlgFile(fileName, fileSizeAndNum);
    }

    /**
     * 生成标记文件
     *
     * @param fileName
     * @param fileSize
     * @param fileNum
     */
    private static void createFlgFile(String fileName, long fileSize, long fileNum) {
        String localFlgFilePath = getLocalFlgFilePath(fileName);
        FileOutputStream localFlgFile = null;
        try {
            localFlgFile = new FileOutputStream(new File(localFlgFilePath));
            StringBuffer sb = new StringBuffer();
            sb.append(getFlgData(getDataFileName(fileName), fileSize, fileNum)).append(Util.getLineSeparator());
            IOUtils.write(sb.toString(), localFlgFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                localFlgFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成标记文件
     *
     * @param fileName
     * @param fileSizeAndNum
     */
    private static void createFlgFile(String fileName, String fileSizeAndNum) {
        String localFlgFilePath = getLocalFlgFilePath(fileName);
        FileOutputStream localFlgFile = null;
        try {
            localFlgFile = new FileOutputStream(new File(localFlgFilePath));
            StringBuffer sb = new StringBuffer();
            sb.append(getFlgData(getDataFileName(fileName), fileSizeAndNum)).append(Util.getLineSeparator());
            IOUtils.write(sb.toString(), localFlgFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                localFlgFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成数据文件
     *
     * @param fileName
     * @param records
     * @param delimiter
     * @param encoding
     * @param isPrintHeader
     * @return
     */
    private static String createDataFile(String fileName, List<Map<String, String>> records, String delimiter, String encoding, boolean isPrintHeader) {
        if (StringUtils.isBlank(encoding)) encoding = FILE_ENCODING;
        String localDataFilePath = getLocalDataFilePath(fileName);
        FileOutputStream localDataFile = null;
        long fileSize = 0;
        long fileNum = 0;
        try {
            localDataFile = new FileOutputStream(new File(localDataFilePath));
            StringBuffer sb = new StringBuffer();
            List<String> headerList = getHeaderList(records);

            // 文件头
            byte[] bytes;
            if (isPrintHeader) {
                sb.append(getFileHead(headerList, delimiter)).append(Util.getLineSeparator());
                bytes = sb.toString().getBytes(encoding);
                fileSize += bytes.length;
                IOUtils.write(bytes, localDataFile);
                fileNum++;
            }

            // 文件体
            for (Map<String, String> record : records) {
                sb = new StringBuffer();
                sb.append(getFileData(record, headerList, delimiter)).append(Util.getLineSeparator());
                bytes = sb.toString().getBytes(encoding);
                fileSize += bytes.length;
                IOUtils.write(bytes, localDataFile);
                fileNum++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                localDataFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileSize + FLG_FILE_DELIMITER + fileNum;
    }

    /**
     * 生成数据文件
     *
     * @param fileName
     * @param resultSet
     * @param delimiter
     * @param encoding
     * @param isPrintHeader
     * @return
     */
    private static String createDataFile(String fileName, ResultSet resultSet, String delimiter, String encoding, boolean isPrintHeader) {
        String localDataFilePath = getLocalDataFilePath(fileName);
        FileOutputStream localDataFile = null;
        long fileSize = 0;
        long fileNum = 0;
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            localDataFile = new FileOutputStream(new File(localDataFilePath));
            StringBuffer sb = new StringBuffer();
            List<String> headerList = getHeaderList(metaData);

            // 文件头
            byte[] bytes;
            if (isPrintHeader) {
                sb.append(getFileHead(headerList, delimiter)).append(Util.getLineSeparator());
                bytes = sb.toString().getBytes(encoding);
                fileSize += bytes.length;
                IOUtils.write(bytes, localDataFile);
                fileNum++;
            }

            // 文件体
            while (resultSet.next()) {
                sb = new StringBuffer();
                sb.append(getFileData(resultSet, columnCount, delimiter)).append(Util.getLineSeparator());
                bytes = sb.toString().getBytes(encoding);
                fileSize += bytes.length;
                IOUtils.write(bytes, localDataFile);
                fileNum++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                localDataFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileSize + FLG_FILE_DELIMITER + fileNum;
    }

    /**
     * @param records
     * @param isPrintHeader
     * @return
     */
    public static void createDelimiterFile(List<Map<String, String>> records, boolean isPrintHeader, String localFileName) {
        createDelimiterFile(records, DATA_FILE_DELIMITER, FILE_ENCODING, isPrintHeader, localFileName);
    }

    /**
     * @param resultSet
     * @param isPrintHeader
     * @return
     */
    public static void createDelimiterFile(ResultSet resultSet, boolean isPrintHeader, String localFileName) {
        createDelimiterFile(resultSet, DATA_FILE_DELIMITER, FILE_ENCODING, isPrintHeader, localFileName);
    }

    /**
     * 获取表头字段列表
     *
     * @param records
     * @return
     */
    private static List<String> getHeaderList(List<Map<String, String>> records) {
        List<String> headerList = null;
        if (records.size() >= 1) {
            headerList = new ArrayList<>();
            for (Map.Entry<String, String> entry : records.get(0).entrySet()) {
                headerList.add(entry.getKey());
            }
        }
        return headerList;
    }

    /**
     * 获取表头字段列表
     *
     * @param metaData
     * @return
     */
    private static List<String> getHeaderList(ResultSetMetaData metaData) {
        List<String> headerList = null;
        int columnCount = 0;
        try {
            columnCount = metaData.getColumnCount();
            if (columnCount >= 1) {
                headerList = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    headerList.add(metaData.getColumnLabel(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return headerList;
    }

    /**
     * 获取文件头字符串
     *
     * @param headerList
     * @param delimiter
     * @return
     */
    private static String getFileHead(List<String> headerList, String delimiter) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < headerList.size(); i++) {
            if (i == 0) {
                sb.append(headerList.get(i));
            } else {
                sb.append(delimiter).append(headerList.get(i));
            }
        }
        return sb.toString();
    }

    /**
     * 获取单行数据的字符串
     *
     * @param record
     * @param headerList
     * @param delimiter
     * @return
     */
    private static String getFileData(Map<String, String> record, List<String> headerList, String delimiter) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < headerList.size(); i++) {
            if (i == 0) {
                sb.append(record.get(headerList.get(i)));
            } else {
                sb.append(delimiter).append(record.get(headerList.get(i)));
            }
        }
        return sb.toString();
    }

    /**
     * 获取单行数据的字符串
     *
     * @param resultSet
     * @param columnCount
     * @param delimiter
     * @return
     */
    private static String getFileData(ResultSet resultSet, int columnCount, String delimiter) {
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 1; i <= columnCount; i++) {
                if (i == 1) {
                    sb.append(resultSet.getString(i));
                } else {
                    sb.append(delimiter).append(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取标记文件数据的字符串
     *
     * @param fileName
     * @param fileSize
     * @param fileNum
     * @return
     */
    private static String getFlgData(String fileName, long fileSize, long fileNum) {
        StringBuffer sb = new StringBuffer();
        sb.append(fileName).append(FLG_FILE_DELIMITER)//
                .append(fileSize).append(FLG_FILE_DELIMITER)//
                .append(fileNum).append(FLG_FILE_DELIMITER)//
                .append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        return sb.toString();
    }

    /**
     * 获取标记文件数据的字符串
     *
     * @param fileName
     * @param fileSizeAndNum
     * @return
     */
    private static String getFlgData(String fileName, String fileSizeAndNum) {
        StringBuffer sb = new StringBuffer();
        sb.append(fileName).append(FLG_FILE_DELIMITER)//
                .append(fileSizeAndNum).append(FLG_FILE_DELIMITER)//
                .append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        return sb.toString();
    }

    /**
     * 获取服务器路径
     *
     * @param dirName
     * @return
     */
    private static String getDirPath(String dirName) {
        // 根据不同系统替换文件分隔符
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        // 判断是否以seprator结尾
        if (!dirPath.endsWith(seprator)) {
            dirPath += seprator;
        }
        dirPath += dirName + seprator;
        // 判断是否存在，不存在则创建
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        return dirPath;
    }

    public static String getLocalDirPath() {
        if(StringUtils.isBlank(tempfileLocalDir)){
            return getDirPath(TEMP_DOWNLOAD);
        }
        File file = new File(tempfileLocalDir);
        if (!file.exists()) {
            FileUtil.mkdir(tempfileLocalDir);
        }
        return tempfileLocalDir;
    }

    public static String getFileName() {
        return Util.uuid() + "_" + DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
    }

    public static String getFtpFileDir(String userName) {
        if ("admin".equals(userName)) userName = "udsp" + userName;
        String ftpDirPath1 = FTPClientConfig.getRootpath() + "/" + userName + "/" + FTPClientConfig.getUsername();
        String ftpDirPath2 = ftpDirPath1 + "/" + DateUtil.format(new Date(), "yyyyMMdd");
        FTPHelper ftpHelper = new FTPHelper();
        try {
            ftpHelper.connectFTPServer();
            ftpHelper.makeDirectory(ftpDirPath1);
            ftpHelper.makeDirectory(ftpDirPath2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ftpHelper.closeFTPClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ftpDirPath2;
    }

    public static String getFtpDataFilePath(String fileName, String userName) {
        return getFtpFileDir(userName) + getDataFileName(fileName);
    }

    public static String getFtpFlgFilePath(String fileName, String userName) {
        return getFtpFileDir(userName) + getFlgFileName(fileName);
    }

    public static String getLocalDataFilePath(String fileName) {
        return getLocalDirPath() + getDataFileName(fileName);
    }

    public static String getLocalFlgFilePath(String fileName) {
        return getLocalDirPath() + getFlgFileName(fileName);
    }

    public static String getDataFileName(String fileName) {
        return fileName + DATA_FILE_SUFFIX;
    }

    public static String getFlgFileName(String fileName) {
        return fileName + FLG_FILE_SUFFIX;
    }
}
