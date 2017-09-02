package com.hex.bigdata.udsp.constant;

/**
 * 操作符
 */
public enum ClientType {
    IQ("交互查询", "IQ"),
    OLQ("联机查询", "OLQ"),
    MM("模型调用", "MM"),
    RTS_CONSUMER("实时流生产者", "RTS_CONSUMER"),
    RTS_PRODUCER("实时流消费者", "RTS_PRODUCER");
    private String value;
    private String name;
    private ClientType(String name, String value) {
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
