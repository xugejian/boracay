package com.hex.bigdata.udsp.common.constant;

/**
 * 请求类型
 */
public enum RequestType {
    INNER("内部请求", "0"),
    OUTER("外部请求", "1");

    private String value;
    private String name;

    private RequestType(String name, String value) {
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
