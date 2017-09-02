package com.hex.bigdata.udsp.constant;

/**
 * 状态码
 */
public enum StatusCode {
    SUCCESS("成功", "000000"),
    DEFEAT("失败", "000001"),
    TIMEOUT("超时", "000002"),
    KILLED("杀死", "000003"),
    RUNING("运行", "000004"),
    OTHER("其他", "000005");

    private String value;
    private String name;

    private StatusCode(String name, String value) {
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
