package com.hex.bigdata.udsp.consumer;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return this.host.equalsIgnoreCase((String) obj);
    }

}