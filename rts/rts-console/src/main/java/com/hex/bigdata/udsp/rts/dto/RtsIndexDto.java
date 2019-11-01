package com.hex.bigdata.udsp.rts.dto;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/12
 * TIME:9:49
 */
public class RtsIndexDto {
    private int rowIndex;

    public RtsIndexDto(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
