package com.hex.bigdata.udsp.common.constant;

/**
 * 消费实体
 */
public enum ConsumerEntity {
    START("开始消费", "START"),
    STOP("停止消费", "STOP"),
    STATUS("查看状态", "STATUS");

    private String value;
    private String name;

    private ConsumerEntity(String name, String value) {
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
