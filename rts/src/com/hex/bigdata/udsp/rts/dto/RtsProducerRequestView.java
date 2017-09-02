package com.hex.bigdata.udsp.rts.dto;

import com.hex.bigdata.udsp.rts.provider.model.Column;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tomnic on 2017/3/7.
 */
public class RtsProducerRequestView  implements Serializable {

    private String producerId;

    private List<List<Column>> producerData;

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public List<List<Column>> getProducerData() {
        return producerData;
    }

    public List<Map<String, String>> getListMap(){
        List<Map<String, String>> listMap=new ArrayList<>();
        for (List<Column> columns:producerData){
            Map<String, String> temp=new HashMap<>();
            for (Column item:columns){
                temp.put(item.getName(),item.getValue());
            }
            Date now=new Date();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            temp.put("time",df.format(now));
            listMap.add(temp);
        }
        return listMap;
    }
    public void setProducerData(List<List<Column>> producerData) {
        this.producerData = producerData;
    }
}
