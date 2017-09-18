package com.hex.bigdata.udsp.model;

import com.hex.bigdata.udsp.im.constant.RealtimeStatus;

import java.io.Serializable;

/**
 * 实时任务的节点信息
 */
public class RealtimeNodeInfo implements Serializable {
    private String host; // 主机
    private RealtimeStatus status; // 开始启动、正在运行、启动失败、开始停止、停止成功、停止失败、运行失败
    private String runTime; // 开始运行的时间
    private String endTime; // 任务停止的时间
    private String updateTime; // 更新信息的时间
    private String message; // 信息

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public RealtimeStatus getStatus() {
        return status;
    }

    public void setStatus(RealtimeStatus status) {
        this.status = status;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
