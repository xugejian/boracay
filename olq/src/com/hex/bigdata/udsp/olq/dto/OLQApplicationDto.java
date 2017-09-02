package com.hex.bigdata.udsp.olq.dto;

import com.hex.bigdata.udsp.olq.model.OLQApplication;
import com.hex.bigdata.udsp.olq.model.OLQApplicationParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/28
 * TIME:21:05
 */
public class OLQApplicationDto implements Serializable{

    /**
     * 应用信息
     */
    private OLQApplication olqApplication;

    /**
     * 应用参数信息
     */
    private List<OLQApplicationParam> params;


    public OLQApplication getOlqApplication() {
        return olqApplication;
    }

    public void setOlqApplication(OLQApplication olqApplication) {
        this.olqApplication = olqApplication;
    }

    public List<OLQApplicationParam> getParams() {
        return params;
    }

    public void setParams(List<OLQApplicationParam> params) {
        this.params = params;
    }
}
