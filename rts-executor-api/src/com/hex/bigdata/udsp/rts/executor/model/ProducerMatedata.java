package com.hex.bigdata.udsp.rts.executor.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomnic on 2017/3/3.
 */
public class ProducerMatedata implements Serializable {

    private ProducerDatasource datasource;

    private String name;

    private String describe;

    private String note;

    private String topic;

    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public ProducerDatasource getDatasource() {
        return datasource;
    }

    public void setDatasource(ProducerDatasource datasource) {
        this.datasource = datasource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTopic() {
        if (StringUtils.isBlank(topic))
            throw new IllegalArgumentException("topic不能为空");
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


}
