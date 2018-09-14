package com.hex.bigdata.udsp.common.model;

import com.hex.bigdata.udsp.common.constant.ExcelProperty;

import java.util.List;

/**
 * Created by PC on 2017/6/6.
 */
public class ComUploadExcelContent {
    //类型，分为有固定配置栏（fixed）和无固定配置栏的(unFixed)
    private String type;
    //对应实体类名
    private  String className;

    private List<ExcelProperty> excelProperties;

    private List<ComExcelProperties> comExcelPropertiesList;

    private List<ComExcelParam> comExcelParams;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ComExcelProperties> getComExcelPropertiesList() {
        return comExcelPropertiesList;
    }

    public void setComExcelPropertiesList(List<ComExcelProperties> comExcelPropertiesList) {
        this.comExcelPropertiesList = comExcelPropertiesList;
    }

    public List<ComExcelParam> getComExcelParams() {
        return comExcelParams;
    }

    public void setComExcelParams(List<ComExcelParam> comExcelParams) {
        this.comExcelParams = comExcelParams;
    }

    public List<ExcelProperty> getExcelProperties() {
        return excelProperties;
    }

    public void setExcelProperties(List<ExcelProperty> excelProperties) {
        this.excelProperties = excelProperties;
    }
}
