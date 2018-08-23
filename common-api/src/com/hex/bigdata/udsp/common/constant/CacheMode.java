package com.hex.bigdata.udsp.common.constant;

/**
 * 缓存模式
 */
public enum CacheMode {
    NONE("无缓存", "NONE"),
    LOCAL("本地缓存", "LOCAL"),
    EHCACHE("EhCache缓存", "EHCACHE"),
    REDIS("Redis缓存", "REDIS");

    private String value;
    private String name;

    private CacheMode(String name, String value) {
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
