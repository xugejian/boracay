package com.hex.bigdata.udsp.iq.provider.model;

import com.hex.bigdata.udsp.common.api.model.Base;
import com.hex.bigdata.udsp.common.api.model.Property;

import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-3-2.
 */
public class Application extends Base {
    private String name;

    private String describe;

    private String note;

    private int maxNum;  // 最大返回值

    private List<QueryColumn> queryColumns; // 查询字段集合

    private List<ReturnColumn> returnColumns; // 返回字段集合

    private List<OrderColumn> orderColumns; // 排序字段集合

    private Metadata metadata; // 元数据

    public Application() {
    }

    public Application(List<Property> properties) {
        super(properties);
    }

    public Application(Map<String, Property> propertyMap) {
        super(propertyMap);
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

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public List<QueryColumn> getQueryColumns() {
        if (queryColumns == null)
            throw new IllegalArgumentException("queryColumns不能为空");
        return queryColumns;
    }

    public void setQueryColumns(List<QueryColumn> queryColumns) {
        this.queryColumns = queryColumns;
    }

    public List<ReturnColumn> getReturnColumns() {
        if (returnColumns == null)
            throw new IllegalArgumentException("returnColumns不能为空");
        return returnColumns;
    }

    public void setReturnColumns(List<ReturnColumn> returnColumns) {
        this.returnColumns = returnColumns;
    }

    public List<OrderColumn> getOrderColumns() {
        return orderColumns;
    }

    public void setOrderColumns(List<OrderColumn> orderColumns) {
        this.orderColumns = orderColumns;
    }


    public Metadata getMetadata() {
        if (metadata == null)
            throw new IllegalArgumentException("metadata不能为空");
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
