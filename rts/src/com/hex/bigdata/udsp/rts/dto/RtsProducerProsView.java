package com.hex.bigdata.udsp.rts.dto;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.rts.model.RtsProducer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomnic on 2017/3/1.
 */
public class RtsProducerProsView implements Serializable {

    private RtsProducer rtsProducer;
    private List<ComProperties> comPropertiesList;

    public List<ComProperties> getComPropertiesList() {
        return comPropertiesList;
    }

    public void setComPropertiesList(List<ComProperties> comPropertiesList) {
        this.comPropertiesList = comPropertiesList;
    }

    public RtsProducer getRtsProducer() {
        return rtsProducer;
    }

    public void setRtsProducer(RtsProducer rtsProducer) {
        this.rtsProducer = rtsProducer;
    }
}
