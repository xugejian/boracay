package com.hex.bigdata.udsp.rts.dto;

import com.hex.bigdata.udsp.rts.model.RtsConsumer;

import java.io.Serializable;

/**
 * Created by tomnic on 2017/3/1.
 */
public class RtsConsumerView extends RtsConsumer implements Serializable {

    private String crtTimeStart;
    private String crtTimeEnd;
    private String uptTimeStart;
    private String uptTimeEnd;
    private String mdName;
    private String mdDesc;
    private String mdTopic;

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

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getMdTopic() {
        return mdTopic;
    }

    public void setMdTopic(String mdTopic) {
        this.mdTopic = mdTopic;
    }

    public String getMdDesc() {
        return mdDesc;
    }

    public void setMdDesc(String mdDesc) {
        this.mdDesc = mdDesc;
    }
}
