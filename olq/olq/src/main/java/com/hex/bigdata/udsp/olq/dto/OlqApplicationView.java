package com.hex.bigdata.udsp.olq.dto;

import com.hex.bigdata.udsp.olq.model.OlqApplication;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/26
 * TIME:10:52
 */
public class OlqApplicationView extends OlqApplication {

    private String dsId;
    private String dsName;
    private String crtTimeStart;
    private String crtTimeEnd;
    private String uptTimeStart;
    private String uptTimeEnd;

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getCrtTimeStart() {
        return crtTimeStart;
    }

    public void setCrtTimeStart(String crtTimeStart) {
        this.crtTimeStart = crtTimeStart;
    }

    public String getCrtTimeEnd() {
        return crtTimeEnd;
    }

    public void setCrtTimeEnd(String crtTimeEnd) {
        this.crtTimeEnd = crtTimeEnd;
    }

    public String getUptTimeStart() {
        return uptTimeStart;
    }

    public void setUptTimeStart(String uptTimeStart) {
        this.uptTimeStart = uptTimeStart;
    }

    public String getUptTimeEnd() {
        return uptTimeEnd;
    }

    public void setUptTimeEnd(String uptTimeEnd) {
        this.uptTimeEnd = uptTimeEnd;
    }
}
