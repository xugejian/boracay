package com.hex.bigdata.udsp.common.constant;

/**
 * 数据源类型
 */
public enum DatasourceMode {
    IQ("交互查询", "IQ"),
    OLQ("联机查询", "OLQ"),
    RTS("实时流", "RTS"),
    IM("交互建模", "IM");

    private String value;
    private String name;

    private DatasourceMode(String name, String value) {
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
