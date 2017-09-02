package com.hex.bigdata.udsp.olq.dto;

import com.hex.bigdata.udsp.olq.model.OLQApplication;
import com.hex.bigdata.udsp.olq.model.OLQApplicationParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/26
 * TIME:10:52
 */
public class OLQApplicationView extends OLQApplication {

    /**
     * 数据源名称
     */
    private String dsName;

    /**
     * 创建时间-开始时间
     */
    private String crtTimeStart;


    /**
     * 创建时间-结束时间
     */
    private String crtTimeEnd;


    /**
     * 更新时间-开始时间
     */
    private String uptTimeStart;


    /**
     * 更新时间-结束时间
     */
    private String uptTimeEnd;

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
