package com.hex.bigdata.udsp.common.constant;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/17
 * TIME:15:23
 */
public enum  UdspErrorCode {

    QXBZ("权限不足", "000001"),
    SFYZSB("UDSP用户身份验证失败", "000002"),
    DDDLYM("等待队列已满", "000003");

    private String value;
    private String name;

    private UdspErrorCode(String name, String value) {
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
