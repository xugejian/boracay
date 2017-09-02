package com.hex.bigdata.udsp.common.constant;

/**
 * 统计函数
 */
public enum Stats {
    NONE("无", "NONE"),
    COUNT("计数", "COUNT"),
    SUM("合计", "DESC"),
    MIN("最小", "ASC"),
    MAX("最大", "DESC"),
    AVG("平均", "ASC"),
    CONCAT("合并", "CONCAT");

    private String value;
    private String name;

    private Stats(String name, String value) {
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
