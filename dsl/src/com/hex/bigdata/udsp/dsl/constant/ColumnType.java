package com.hex.bigdata.udsp.dsl.constant;

/**
 * 字段类型
 */
public enum ColumnType {
    STRING("字符串类型", "STRING"),
    NUMBER("数值类型", "NUMBER");

    private String value;
    private String name;

    private ColumnType(String name, String value) {
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
