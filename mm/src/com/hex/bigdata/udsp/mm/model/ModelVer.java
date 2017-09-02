package com.hex.bigdata.udsp.mm.model;

import java.io.Serializable;

public class ModelVer implements Serializable {
    private String pkId;

    private String modelName;

    private Long verNum;

    private String verNote;

    private String crtTime;

    private String verName;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getVerNum() {
        return verNum;
    }

    public void setVerNum(Long verNum) {
        this.verNum = verNum;
    }

    public String getVerNote() {
        return verNote;
    }

    public void setVerNote(String verNote) {
        this.verNote = verNote;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }
}