package com.hex.bigdata.udsp.rts.provider.model;

import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-2-20.
 */
public class ProducerRequest {

    private ProducerApplication producerApplication;

    private List<Map<String, String>> messageDatas;

    public ProducerRequest(){
        super();
    }

    public ProducerRequest(ProducerApplication producerApplication, List<Map<String, String>> messageDatas) {
        this.producerApplication = producerApplication;
        this.messageDatas = messageDatas;
    }

    public ProducerApplication getProducerApplication() {
        return producerApplication;
    }

    public void setProducerApplication(ProducerApplication producerApplication) {
        this.producerApplication = producerApplication;
    }

    public List<Map<String, String>> getMessageDatas() {
        return messageDatas;
    }

    public void setMessageDatas(List<Map<String, String>> messageDatas) {
        this.messageDatas = messageDatas;
    }
}
