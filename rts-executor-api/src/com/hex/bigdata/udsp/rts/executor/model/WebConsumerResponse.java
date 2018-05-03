package com.hex.bigdata.udsp.rts.executor.model;

import com.hex.bigdata.udsp.common.api.model.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/3/31
 * TIME:13:41
 */
public class WebConsumerResponse extends ConsumerResponse {

    private List<Map<String,Object>> recordsMap;//记录map

    public List<Map<String, Object>> getRecordsMap() {
        List<Result> records=this.getRecords();
        if (records==null||records.size()==0){
            return null;
        }
        List<Map<String, Object>> tempMap=new ArrayList<>();
        for (Result result:records){
            tempMap.add(result.getResult());
        }
        recordsMap=tempMap;
        return recordsMap;
    }

    public void setRecordsMap(List<Map<String, Object>> recordsMap) {
        this.recordsMap = recordsMap;
    }
}
