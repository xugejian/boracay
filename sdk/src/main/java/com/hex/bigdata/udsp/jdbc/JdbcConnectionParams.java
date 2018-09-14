package com.hex.bigdata.udsp.jdbc;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Jdbc Connection Params
 */
public class JdbcConnectionParams {

    static final String AUTH_USER = "user";
    static final String AUTH_PASSWD = "password";

    private String host = null; // 主机名
    private int port; // 端口
    private String serviceName = Utils.DEFAULT_SERVICE_NAME; // 服务名
    private Map<String, String> dataVars = new LinkedHashMap<String, String>(); // 数据参数集
    private Map<String, String> udspVars = new LinkedHashMap<String, String>(); // UDSP参数集
    private Map<String, String> sessionVars = new LinkedHashMap<String, String>(); // 会话参数集

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Map<String, String> getSessionVars() {
        return sessionVars;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSessionVars(Map<String, String> sessionVars) {
        this.sessionVars = sessionVars;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Map<String, String> getUdspVars() {
        return udspVars;
    }

    public void setUdspVars(Map<String, String> udspVars) {
        this.udspVars = udspVars;
    }

    public Map<String, String> getDataVars() {
        return dataVars;
    }

    public void setDataVars(Map<String, String> dataVars) {
        this.dataVars = dataVars;
    }
}
