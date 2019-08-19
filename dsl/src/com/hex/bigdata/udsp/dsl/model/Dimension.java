package com.hex.bigdata.udsp.dsl.model;

import com.hex.bigdata.udsp.dsl.constant.ColumnType;
import com.hex.bigdata.udsp.dsl.constant.ComparisonOperator;

import java.util.List;

/**
 * 维度类
 */
public class Dimension extends Component {
    private String columnName; // 字段名称
    private ColumnType columnType; // 字段类型
    private ComparisonOperator compOper; // 比较操作符
    private List<String> values; // 数值集合

    public Dimension() {
    }

    public Dimension(String columnName, ComparisonOperator compOper) {
        this.columnName = columnName;
        this.compOper = compOper;
    }

    public Dimension(String columnName, ComparisonOperator compOper, ColumnType columnType, List<String> values) {
        this.columnName = columnName;
        this.compOper = compOper;
        this.columnType = columnType;
        this.values = values;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ComparisonOperator getCompOper() {
        return compOper;
    }

    public void setCompOper(ComparisonOperator compOper) {
        this.compOper = compOper;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
