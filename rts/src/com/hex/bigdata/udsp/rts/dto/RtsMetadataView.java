package com.hex.bigdata.udsp.rts.dto;

import com.hex.bigdata.udsp.rts.model.RtsMetadata;

import java.io.Serializable;

/**
 * Created by tomnic on 2017/2/28.
 */
public class RtsMetadataView extends RtsMetadata implements Serializable {

    private String crtTimeStart;
    private String crtTimeEnd;
    private String uptTimeStart;
    private String uptTimeEnd;
    private String dsName;
    private String dsType;

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

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getDsType() {
        return dsType;
    }

    public void setDsType(String dsType) {
        this.dsType = dsType;
    }
}
