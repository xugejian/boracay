package com.hex.bigdata.udsp.common.aggregator.model;

import com.hex.bigdata.udsp.common.aggregator.constant.H2DataType;

/**
 * Created by JunjieM on 2019-8-20.
 */
public class H2DataColumn {
    private String colName; // 字段名
    private H2DataType dataType; // 类型
    private String length; //长度

    public H2DataColumn() {
    }

    public H2DataColumn(String colName, H2DataType dataType) {
        this.colName = colName;
        this.dataType = dataType;
    }

    public H2DataColumn(String colName, H2DataType dataType, String length) {
        this.colName = colName;
        this.dataType = dataType;
        this.length = length;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public H2DataType getDataType() {
        return dataType;
    }

    public void setDataType(H2DataType dataType) {
        this.dataType = dataType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
