package com.hex.bigdata.udsp.common.constant;

/**
 * Created by PC on 2017/6/5.
 */
public enum ServiceMode {
    SINGLE("单机", "SINGLE"),
    CLUSTER("集群", "CLUSTER");

    private String value;
    private String name;

    private ServiceMode(String name, String value) {
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
