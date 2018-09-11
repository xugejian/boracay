package com.hex.bigdata.udsp.mm.model;

import java.io.Serializable;

public class MmAppReturnParam implements Serializable {

    private String pkId;

    private int seq; // 字段顺序

    private String name; // 字段名称

    private String describe; // 字段说明

    private String appId; // 应用Id

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}