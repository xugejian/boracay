package com.hex.bigdata.udsp.iq.dto;

import com.hex.bigdata.udsp.iq.model.IqAppOrderCol;
import com.hex.bigdata.udsp.iq.model.IqAppQueryCol;
import com.hex.bigdata.udsp.iq.model.IqAppReturnCol;
import com.hex.bigdata.udsp.iq.model.IqApplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by junjiem on 2017-2-28.
 */
public class IqApplicationPropsView implements Serializable {
    private IqApplication iqApplication;
    private List<IqAppQueryCol> iqAppQueryColList;
    private List<IqAppReturnCol> iqAppReturnColList;
    private List<IqAppOrderCol> iqAppOrderColList;

    public IqApplication getIqApplication() {
        return iqApplication;
    }

    public void setIqApplication(IqApplication iqApplication) {
        this.iqApplication = iqApplication;
    }

    public List<IqAppQueryCol> getIqAppQueryColList() {
        return iqAppQueryColList;
    }

    public void setIqAppQueryColList(List<IqAppQueryCol> iqAppQueryColList) {
        this.iqAppQueryColList = iqAppQueryColList;
    }

    public List<IqAppReturnCol> getIqAppReturnColList() {
        return iqAppReturnColList;
    }

    public void setIqAppReturnColList(List<IqAppReturnCol> iqAppReturnColList) {
        this.iqAppReturnColList = iqAppReturnColList;
    }

    public List<IqAppOrderCol> getIqAppOrderColList() {
        return iqAppOrderColList;
    }

    public void setIqAppOrderColList(List<IqAppOrderCol> iqAppOrderColList) {
        this.iqAppOrderColList = iqAppOrderColList;
    }
}
