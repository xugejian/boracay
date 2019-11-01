package com.hex.bigdata.udsp.rts.dto;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.rts.model.RtsConsumer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomnic on 2017/3/1.
 */
public class RtsConsumerProsView implements Serializable {

    private RtsConsumer rtsConsumer;
    private List<ComProperties> comPropertiesList;

    public List<ComProperties> getComPropertiesList() {
        return comPropertiesList;
    }

    public void setComPropertiesList(List<ComProperties> comPropertiesList) {
        this.comPropertiesList = comPropertiesList;
    }

    public RtsConsumer getRtsConsumer() {
        return rtsConsumer;
    }

    public void setRtsConsumer(RtsConsumer rtsConsumer) {
        this.rtsConsumer = rtsConsumer;
    }
}
