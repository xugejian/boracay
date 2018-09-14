package com.hex.bigdata.udsp.common.model;

/**
 * excel通用参数
 * Created by PC on 2017/6/6.
 */
public class ComExcelParam {
    private  int rowNum;

    private int cellNum;

    private String name;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComExcelParam(int rowNum, int cellNum, String name) {
        this.rowNum = rowNum;
        this.cellNum = cellNum;
        this.name = name;
    }
}
