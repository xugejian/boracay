package com.hex.bigdata.udsp.mc.model;

import java.io.Serializable;

/**
 * 并发数控制实体
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/10
 * TIME:14:29
 */
public class RunQueue implements Serializable {

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 异步最大并发数
     */
    private int maxNum;

    /**
     * 当前并发数
     */
    private int currentNum;

    /**
     * SYNC,同步
     * ASYNC,异步
     */
    private String syncType;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }
}
