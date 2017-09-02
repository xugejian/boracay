package com.hex.bigdata.udsp.model;

import java.io.Serializable;

/**
 * 服务状态
 * Created by PC on 2017/5/10.
 */
public class HeartbeatInfo implements Serializable {
    /**
     * 心跳时间
     */
    private long time;
    /**
     * MAC
     */
    private String mac;
    /**
     * IP
     */
    private String ip;
    /**
     * 主机名
     */
    private String host;

    public HeartbeatInfo() {
    }

    public HeartbeatInfo(String mac, String ip, String host, long time) {
        this.time = time;
        this.mac = mac;
        this.ip = ip;
        this.host = host;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
