package com.hex.bigdata.udsp.rc.constant;

/**
 * Created by JunjieM on 2019-11-6.
 */
public enum DateWindowType {
    ALL("全部日期", "ALL"),
    MON_FRI("周一至周五", "MON-FRI"),
    WEEKEND("周末", "WEEKEND");

    private String value;
    private String name;

    private DateWindowType(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
