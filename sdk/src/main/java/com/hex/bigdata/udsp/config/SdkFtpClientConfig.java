package com.hex.bigdata.udsp.config;

import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Properties;

public class SdkFtpClientConfig {
    private static String hostname;
    private static int port = 21;
    private static String username;
    private static String password;
    private static String rootpath = "~";
    private static String dataFilePostfix;
    private static String logFilePostfix;
    private static String defaultDirectory;

    static {
        loadConf("ftp.config.properties");
    }

    public static void loadConf(String configFilePath) {
        InputStream in = null;
        try {
            in = SdkFtpClientConfig.class.getClassLoader().getResourceAsStream(
                    configFilePath);
            Properties props = new Properties();
            props.load(in);
            hostname = props.getProperty("udsp.ftp.hostname");
            String portStr = props.getProperty("udsp.ftp.port");
            if (StringUtils.isNotBlank(portStr)) port = Integer.valueOf(portStr);
            username = props.getProperty("udsp.ftp.username");
            password = props.getProperty("udsp.ftp.password");
            rootpath = props.getProperty("ftp.rootpath");
            dataFilePostfix = props.getProperty("udsp.ftp.datafile.postfix");
            logFilePostfix = props.getProperty("udsp.ftp.logfile.postfix");
            defaultDirectory = props.getProperty("udsp.ftp.download.default.directory");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getHostname() {
        return hostname;
    }

    public static void setHostname(String hostname) {
        SdkFtpClientConfig.hostname = hostname;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        SdkFtpClientConfig.port = port;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SdkFtpClientConfig.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SdkFtpClientConfig.password = password;
    }

    public static String getRootpath() {
        return rootpath;
    }

    public static void setRootpath(String rootpath) {
        SdkFtpClientConfig.rootpath = rootpath;
    }

    public static String getDataFilePostfix() {
        return dataFilePostfix;
    }

    public static void setDataFilePostfix(String dataFilePostfix) {
        SdkFtpClientConfig.dataFilePostfix = dataFilePostfix;
    }

    public static String getLogFilePostfix() {
        return logFilePostfix;
    }

    public static void setLogFilePostfix(String logFilePostfix) {
        SdkFtpClientConfig.logFilePostfix = logFilePostfix;
    }
}
