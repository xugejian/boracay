package com.hex.bigdata.udsp.model.request;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:11:12
 */
@Deprecated
public class OlqRequest extends UdspRequest {
    /**
     * 执行sql语句
     */
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
