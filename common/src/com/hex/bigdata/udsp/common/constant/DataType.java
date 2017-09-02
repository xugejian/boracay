package com.hex.bigdata.udsp.common.constant;

/**
 * Created by junjiem on 2017-2-18.
 */
public enum DataType {
    STRING("STRING", "STRING"),
    DECIMAL("DECIMAL", "DECIMAL"),
    VARCHAR("VARCHAR", "VARCHAR"),
    CHAR("CHAR", "CHAR"),
    INT("INT", "INT"),
    DOUBLE("DOUBLE", "DOUBLE"),
    BOOLEAN("BOOLEAN", "BOOLEAN"),
    TINYINT("TINYINT", "TINYINT"),
    FLOAT("FLOAT", "FLOAT"),
    TIMESTAMP("TIMESTAMP", "TIMESTAMP"),
    SMALLINT("SMALLINT", "SMALLINT"),
    BIGINT("BIGINT", "BIGINT");

    private String value;
    private String name;

    private DataType(String name, String value) {
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
