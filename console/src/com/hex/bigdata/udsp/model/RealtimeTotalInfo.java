package com.hex.bigdata.udsp.model;

import com.hex.bigdata.udsp.im.constant.RealtimeStatus;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MqModel;

import java.io.Serializable;

/**
 * 实时任务的汇总信息
 */
public class RealtimeTotalInfo implements Serializable {
    private String startHost; // 发起启动命令的节点
    private String stopHost; // 发起停止命令的节点
    private RealtimeStatus status; // 准备启动、开始启动、正在运行、启动失败、准备停止、开始停止、停止成功、停止失败、运行失败
    private String startTime; // 准备启动的时间
    private String runTime; // 开始运行的时间
    private String stopTime; // 准备停止的时间
    private String endTime; // 任务停止的时间
    private String updateTime; // 更新信息的时间
    private MqModel model; // MQ模型

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStartHost() {
        return startHost;
    }

    public void setStartHost(String startHost) {
        this.startHost = startHost;
    }

    public String getStopHost() {
        return stopHost;
    }

    public void setStopHost(String stopHost) {
        this.stopHost = stopHost;
    }

    public RealtimeStatus getStatus() {
        return status;
    }

    public void setStatus(RealtimeStatus status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public MqModel getModel() {
        return model;
    }

    public void setModel(MqModel model) {
        this.model = model;
    }
}
