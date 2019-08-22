package com.hex.bigdata.udsp.common.aggregator.constant;

/**
 * Created by JunjieM on 2019-8-20.
 */
public enum H2DataType {
    VARCHAR("VARCHAR", "VARCHAR"),
    CHAR("CHAR", "CHAR"),
    DECIMAL("DECIMAL", "DECIMAL"),
    INT("INT", "INT"),
    DOUBLE("DOUBLE", "DOUBLE"),
    FLOAT("FLOAT", "FLOAT"),
    BOOLEAN("BOOLEAN", "BOOLEAN"),
    TINYINT("TINYINT", "TINYINT"),
    TIMESTAMP("TIMESTAMP", "TIMESTAMP"),
    SMALLINT("SMALLINT", "SMALLINT"),
    BIGINT("BIGINT", "BIGINT");

    private String value;
    private String name;

    private H2DataType(String name, String value) {
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
