package com.hex.bigdata.udsp.common.constant;

/**
 * 是或否
 */
public enum YesOrNo {
    YES("是", "0"),
    NO("否", "1");

    private String value;
    private String name;

    private YesOrNo(String name, String value) {
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
