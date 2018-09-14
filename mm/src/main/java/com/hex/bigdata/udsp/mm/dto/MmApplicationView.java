package com.hex.bigdata.udsp.mm.dto;

import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import com.hex.bigdata.udsp.mm.model.MmAppReturnParam;
import com.hex.bigdata.udsp.mm.model.MmApplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/12
 * TIME:14:42
 */
public class MmApplicationView extends MmApplication implements Serializable {
    /**
     * 创建时间查询时间范围-开始时间
     */
    private String crtTimeStart;
    /**
     * 创建时间查询时间范围-结束时间
     */
    private String crtTimeEnd;
    /**
     * 更新时间查询时间范围-开始时间
     */
    private String uptTimeStart;
    /**
     * 更新时间查询时间范围-结束时间
     */
    private String uptTimeEnd;

    /**
     * 执行参数list
     */
    private List<MmAppExecuteParam> executeParams;

    /**
     *返回字段list
     */
    private List<MmAppReturnParam> returnParams;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 模型厂商
     */
    private String contractor;

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

    public List<MmAppExecuteParam> getExecuteParams() {
        return executeParams;
    }

    public void setExecuteParams(List<MmAppExecuteParam> executeParams) {
        this.executeParams = executeParams;
    }

    public List<MmAppReturnParam> getReturnParams() {
        return returnParams;
    }

    public void setReturnParams(List<MmAppReturnParam> returnParams) {
        this.returnParams = returnParams;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }
}
