package com.hex.bigdata.udsp.rts.dto;

import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
import com.hex.bigdata.udsp.rts.model.RtsProducer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomnic on 2017/3/1.
 */
public class RtsProducerView extends RtsProducer implements Serializable {
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
     * 数据源名称
     */
    private String dsName;

    /**
     * 元数据名称
     */
    private String mdName;

    /**
     * 数据源类型
     */
    private String dsType;

    /**
     * 主题
     */
    private String mdTopic;

    /**
     *
     */
    private List<RtsMatedataCol> columns;

    public RtsProducerView(){}
    public RtsProducerView(RtsProducer rtsProducer,List<RtsMatedataCol> columns) {
//        this.setPkId(rtsProducer.getPkId());
//        this.setMdId(rtsProducer.getMdId());
//        this.setName(rtsProducer.getName());
//        this.setDescribe(rtsProducer.getDescribe());
//        this.setNote(rtsProducer.getNote());
//        this.setDelFlg(rtsProducer.getDelFlg());
//        this.setCrtUser(rtsProducer.getCrtUser());
//        this.setCrtTime(rtsProducer.getCrtTime());
//        this.setUptUser(rtsProducer.getUptUser());
//        this.setUptTime(rtsProducer.getUptTime());
        this.columns = columns;
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

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getDsType() {
        return dsType;
    }

    public void setDsType(String dsType) {
        this.dsType = dsType;
    }

    public String getMdTopic() {
        return mdTopic;
    }

    public void setMdTopic(String mdTopic) {
        this.mdTopic = mdTopic;
    }
}
