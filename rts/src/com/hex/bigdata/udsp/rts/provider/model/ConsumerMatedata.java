package com.hex.bigdata.udsp.rts.provider.model;

import com.hex.bigdata.udsp.rts.dto.RtsMatedataColsView;
import com.hex.bigdata.udsp.rts.model.RtsMatedata;
import com.hex.bigdata.udsp.rts.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomnic on 2017/3/3.
 */
public class ConsumerMatedata  implements Serializable {

    /**
     * 消费者数据源
     */
    private ConsumerDatasource datasource;

    private String name;

    private String describe;

    private String note;

    private String topic;

    public ConsumerMatedata(RtsMatedataColsView matedataColsView, ConsumerDatasource datasource) {
        this.datasource = datasource;

        RtsMatedata rtsMatedata=matedataColsView.getRtsMatedata();
        this.name=rtsMatedata.getName();
        this.describe=rtsMatedata.getDescribe();
        this.note=rtsMatedata.getNote();
        this.topic=rtsMatedata.getTopic();
        this.columns= CommonUtil.getColumns(matedataColsView.getRtsMatedataColList());
    }
    /**
     * 元数据数据列
     */
    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public ConsumerDatasource getDatasource() {
        return datasource;
    }

    public void setDatasource(ConsumerDatasource datasource) {
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
