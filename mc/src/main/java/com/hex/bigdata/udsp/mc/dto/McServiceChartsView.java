package com.hex.bigdata.udsp.mc.dto;

import java.io.Serializable;

/**
 * Created by PC on 2017/3/23.
 */
public class McServiceChartsView implements Serializable {

    private String pkId;

    private String name;

    private String describe;

    private String type;

    private String appId;

    private String delFlg;

    private String crtUser;

    private String crtTime;

    private String uptUser;

    private String uptTime;

    /**
     * 请求成功次数
     */
    private int requestSuccessCount;
    /**
     * 请求失败次数
     */
    private int requestFailedCount;

    /**
     * 请求总次数
     */
    private int requestTotalCount;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public int getRequestSuccessCount() {
        return requestSuccessCount;
    }

    public void setRequestSuccessCount(int requestSuccessCount) {
        this.requestSuccessCount = requestSuccessCount;
    }

    public int getRequestFailedCount() {
        return requestFailedCount;
    }

    public void setRequestFailedCount(int requestFailedCount) {
        this.requestFailedCount = requestFailedCount;
    }

    public int getRequestTotalCount() {
        return requestSuccessCount + requestFailedCount;
    }

    public void setRequestTotalCount(int requestTotalCount) {
        this.requestTotalCount = requestTotalCount;
    }
}
