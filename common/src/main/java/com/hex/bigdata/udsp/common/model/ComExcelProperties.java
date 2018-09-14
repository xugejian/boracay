package com.hex.bigdata.udsp.common.model;

import com.hex.bigdata.udsp.common.constant.ExcelProperty;

import java.util.List;

/**
 * excel通用配置栏
 * Created by PC on 2017/6/6.
 */
public class ComExcelProperties implements Comparable<ComExcelProperties>{
    //名称
    private String name;
    //对应实体类名
    private String className;
    //开始行
    private int startRow;
    //结束行
    private int endRow;
    //配置栏中对应序号
    private int sortNum;

    private List<ExcelProperty> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    @Override
    public int compareTo(ComExcelProperties comExcelProperties) {
        return this.getSortNum() - comExcelProperties.getSortNum() ;
    }

    public List<ExcelProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ExcelProperty> properties) {
        this.properties = properties;
    }

    public ComExcelProperties(String name, String className, int startRow, int endRow, int sortNum, List<ExcelProperty> properties) {
        this.name = name;
        this.className = className;
        this.startRow = startRow;
        this.endRow = endRow;
        this.sortNum = sortNum;
        this.properties = properties;
    }
}
