package com.hex.bigdata.udsp.rc.model;

import java.io.Serializable;

public class RcUserService implements Serializable {
    private String pkId; // 主键
    private String userId; // 用户ID
    private String serviceId; // 服务ID
    private String ipSection; // IP段
    private int maxSyncNum; // 执行队列大小（同步）
    private int maxAsyncNum; // 执行队列大小（异步）
    private int maxSyncWaitNum; //等待队列大小（同步）
    private int maxAsyncWaitNum; // 等待队列大小（异步）
    private long maxSyncWaitTimeout = 0; // 最大等待时间（同步/毫秒）
    private long maxAsyncWaitTimeout = 0; // 最大等待时间（异步/毫秒）
    private long maxSyncExecuteTimeout = 0; // 最大运行时间（同步/毫秒）
    private long maxAsyncExecuteTimeout = 0; // 最大运行时间（异步/毫秒）
    private String delFlg; // 逻辑删除标志
    private String crtUser;
    private String crtTime;
    private String uptUser;
    private String uptTime;
    private String alarmType; // 告警方式
    private String dateType; // 时间窗口的日期类型
    private String startTime; // 时间窗口的开始时间
    private String endTime; // 时间窗口的结束时间

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getIpSection() {
        return ipSection;
    }

    public void setIpSection(String ipSection) {
        this.ipSection = ipSection;
    }

    public int getMaxSyncNum() {
        return maxSyncNum;
    }

    public void setMaxSyncNum(int maxSyncNum) {
        this.maxSyncNum = maxSyncNum;
    }

    public int getMaxAsyncNum() {
        return maxAsyncNum;
    }

    public void setMaxAsyncNum(int maxAsyncNum) {
        this.maxAsyncNum = maxAsyncNum;
    }

    public int getMaxSyncWaitNum() {
        return maxSyncWaitNum;
    }

    public void setMaxSyncWaitNum(int maxSyncWaitNum) {
        this.maxSyncWaitNum = maxSyncWaitNum;
    }

    public int getMaxAsyncWaitNum() {
        return maxAsyncWaitNum;
    }

    public void setMaxAsyncWaitNum(int maxAsyncWaitNum) {
        this.maxAsyncWaitNum = maxAsyncWaitNum;
    }

    public long getMaxSyncWaitTimeout() {
        return maxSyncWaitTimeout;
    }

    public void setMaxSyncWaitTimeout(long maxSyncWaitTimeout) {
        this.maxSyncWaitTimeout = maxSyncWaitTimeout;
    }

    public long getMaxAsyncWaitTimeout() {
        return maxAsyncWaitTimeout;
    }

    public void setMaxAsyncWaitTimeout(long maxAsyncWaitTimeout) {
        this.maxAsyncWaitTimeout = maxAsyncWaitTimeout;
    }

    public long getMaxSyncExecuteTimeout() {
        return maxSyncExecuteTimeout;
    }

    public void setMaxSyncExecuteTimeout(long maxSyncExecuteTimeout) {
        this.maxSyncExecuteTimeout = maxSyncExecuteTimeout;
    }

    public long getMaxAsyncExecuteTimeout() {
        return maxAsyncExecuteTimeout;
    }

    public void setMaxAsyncExecuteTimeout(long maxAsyncExecuteTimeout) {
        this.maxAsyncExecuteTimeout = maxAsyncExecuteTimeout;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getUptUser() {
        return uptUser;
    }

    public void setUptUser(String uptUser) {
        this.uptUser = uptUser;
    }

    public String getUptTime() {
        return uptTime;
    }

    public void setUptTime(String uptTime) {
        this.uptTime = uptTime;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
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
}