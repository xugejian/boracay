package com.hex.bigdata.udsp.dsl.model;

/**
 * 字段类
 */
public class Column {
    private String name; // 字段名称（别名）
    private Aggregate aggregate; // 聚合类型

    public Column() {
    }

    public Column(String name) {
        this.name = name;
    }

    public Column(String name, Aggregate aggregate) {
        this.name = name;
        this.aggregate = aggregate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Aggregate getAggregate() {
        return aggregate;
    }

    public void setAggregate(Aggregate aggregate) {
        this.aggregate = aggregate;
    }
}
