package com.hex.bigdata.udsp.olq.model;

import java.io.Serializable;

public class OLQApplicationParam  implements Serializable {

    private int seq;

    private String pkId;

    private String paramName;

    private String paramDesc;

    private String defaultValue;

    private String isNeed;

    private String olqAppId;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getIsNeed() {
        return isNeed;
    }

    public void setIsNeed(String isNeed) {
        this.isNeed = isNeed;
    }

    public String getOlqAppId() {
        return olqAppId;
    }

    public void setOlqAppId(String olqAppId) {
        this.olqAppId = olqAppId;
    }

}