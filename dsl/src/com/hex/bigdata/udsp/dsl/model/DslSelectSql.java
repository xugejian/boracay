package com.hex.bigdata.udsp.dsl.model;

/**
 * Created by JunjieM on 2019-8-22.
 */
public class DslSelectSql {

    private String sourceSql; // 真实的Select SQL语句
    private String fakeSql; // 伪的Select SQL语句（子的Select SQL部分用${subSelectSql}代替）
    private DslSql dslSql; // 子的Select SQL对象

    public String getSourceSql() {
        return sourceSql;
    }

    public void setSourceSql(String sourceSql) {
        this.sourceSql = sourceSql;
    }

    public String getFakeSql() {
        return fakeSql;
    }

    public void setFakeSql(String fakeSql) {
        this.fakeSql = fakeSql;
    }

    public DslSql getDslSql() {
        return dslSql;
    }

    public void setDslSql(DslSql dslSql) {
        this.dslSql = dslSql;
    }
}
