package com.hex.bigdata.udsp.constant;

/**
 * 请求方式
 */
public enum ConsumerType {
    SYNC("同步", "SYNC"),
    ASYNC("异步", "ASYNC");

    private String value;
    private String name;

    private ConsumerType(String name, String value) {
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
