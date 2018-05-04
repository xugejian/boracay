package com.hex.bigdata.udsp.common.constant;

/**
 * 状态
 */
public enum Status {
    SUCCESS("成功", "SUCCESS"),
    DEFEAT("失败", "DEFEAT"),
    TIMEOUT("超时", "TIMEOUT"),
    KILLED("杀死", "KILLED"),
    RUNING("运行","RUNING"),
    OTHER("没有","OTHER");

    private String value;
    private String name;

    private Status(String name, String value) {
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
