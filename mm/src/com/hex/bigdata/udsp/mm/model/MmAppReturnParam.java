package com.hex.bigdata.udsp.mm.model;

import java.io.Serializable;

public class MmAppReturnParam implements Serializable {
    /**
     * 主键
     */
    private String pkId;

    /**
     * 字段顺序
     */
    private int seq;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段说明
     */
    private String describe;

    /**
     * 应用Id
     */
    private String appId;

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