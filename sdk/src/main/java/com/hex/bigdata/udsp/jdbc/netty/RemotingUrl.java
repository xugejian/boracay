package com.hex.bigdata.udsp.jdbc.netty;

import org.apache.commons.lang.StringUtils;

public class RemotingUrl {
    /**
     * 主机
     **/
    private String host;

    /**
     * 端口
     **/
    private int port;

    /**
     * 超时时间,默认3s
     **/
    private int connectionTimeout = 3000;

    private String serviceName;

    private String username;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RemotingUrl{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", connectionTimeout=" + connectionTimeout +
                ", serviceName='" + serviceName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        String str = toString();
        final int prime = 31;
        return prime + (StringUtils.isBlank(str) ? 0 : str.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

}