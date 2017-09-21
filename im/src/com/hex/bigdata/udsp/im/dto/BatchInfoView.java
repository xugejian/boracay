package com.hex.bigdata.udsp.im.dto;

/**
 * Created by JunjieM on 2017-9-21.
 */
public class BatchInfoView {
    private String id; // 主键
    private String host; // 主机
    private String status; // 准备构建、正在构建、构建成功、构建失败
    private String startTime; // 开始的时间
    private String endTime; // 结束的时间
    private String updateTime; // 更新的时间
    private int percent; // 进度
    private String message; // 信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
