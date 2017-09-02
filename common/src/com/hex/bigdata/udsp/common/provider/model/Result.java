package com.hex.bigdata.udsp.common.provider.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by junjiem on 2017-3-3.
 */
public class Result {
    private Map<String, Object> result = new HashMap<String, Object>();

    public Result() {
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return result.entrySet();
    }

    public Result(Map<String, Object> result) {
        this.result = result;
    }

    public Object get(String key) {
        return result.get(key);
    }

    public String getString(String key) {
        Object oval = this.get(key);
        return (oval instanceof String) ? (String) oval : null;
    }

    public Integer getInteger(String key) {
        Object oval = this.get(key);
        return (oval instanceof Integer) ? (Integer) oval : null;
    }

    public Double getDouble(String key) {
        Object oval = this.get(key);
        return (oval instanceof Double) ? (Double) oval : null;
    }

    public Float getFloat(String key) {
        Object oval = this.get(key);
        return (oval instanceof Float) ? (Float) oval : null;
    }

    public Long getLong(String key) {
        Object oval = this.get(key);
        return (oval instanceof Long) ? (Long) oval : null;
    }

    public Short getShort(String key) {
        Object oval = this.get(key);
        return (oval instanceof Short) ? (Short) oval : null;
    }

    public Boolean getBoolean(String key) {
        Object oval = this.get(key);
        return (oval instanceof Boolean) ? (Boolean) oval : null;
    }

    public void set(Map<String, Object> result) {
        this.result = result;
    }

    public void put(String key, Object obj) {
        this.result.put(key, obj);
    }

    public void putAll(Map<String, ? extends Object> result) {
        if (this.result == null) {
            this.result = new HashMap<String, Object>();
        }
        this.result.putAll(result);
    }
}
