package com.hex.bigdata.udsp.rts.dto;

import com.hex.bigdata.udsp.rts.model.RtsMatedata;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomnic on 2017/2/28.
 */
public class RtsMatedataColsView implements Serializable {
    /**
     * 基础信息对象
     */
    private RtsMatedata rtsMatedata;
    /**
     * 字段对象list
     */
    private List<RtsMatedataCol> rtsMatedataColList;

    public RtsMatedataColsView(){

    }

    public RtsMatedataColsView(RtsMatedata rtsMatedata, List<RtsMatedataCol> rtsMatedataColList) {
        this.rtsMatedata = rtsMatedata;
        this.rtsMatedataColList = rtsMatedataColList;
    }

    public RtsMatedata getRtsMatedata() {
        return rtsMatedata;
    }

    public void setRtsMatedata(RtsMatedata rtsMatedata) {
        this.rtsMatedata = rtsMatedata;
    }

    public List<RtsMatedataCol> getRtsMatedataColList() {
        return rtsMatedataColList;
    }

    public void setRtsMatedataColList(List<RtsMatedataCol> rtsMatedataColList) {
        this.rtsMatedataColList = rtsMatedataColList;
    }
}
