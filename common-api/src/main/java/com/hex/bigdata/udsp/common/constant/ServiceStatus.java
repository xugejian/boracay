package com.hex.bigdata.udsp.common.constant;

/**
 * 服务状态
 */
public enum ServiceStatus {
    START("启用", "0"),
    STOP("停用", "1");

    private String value;
    private String name;

    private ServiceStatus(String name, String value) {
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
