package com.hex.bigdata.udsp.im.converter.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.im.converter.model.TableColumn;
import com.hex.bigdata.udsp.im.converter.model.ValueColumn;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by PC on 2018/10/8.
 */
public class PhoenixSqlUtil {

    /**
     * 建表
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @return
     */
    public static String createTable(boolean ifNotExists, String tableName,
                                     List<TableColumn> columns) {
        return "CREATE TABLE " + getIfNotExists(ifNotExists) + " " + tableName
                + " (" + getColumns(columns) + ")";
    }

    /**
     * 删表
     *
     * @param ifExists
     * @param tableName
     * @return
     */
    public static String dropTable(boolean ifExists, String tableName) {
        return "DROP TABLE" + getIfExists(ifExists) + " " + tableName;
    }

    /**
     * 删除所有数据
     *
     * @param tableName
     * @return
     */
    public static String deleteAll(String tableName) {
        return "DELETE FROM " + tableName;
    }

    /**
     * 添加多个字段
     *
     * @param tableName
     * @param columns
     * @return
     */
    public static String addColumns(String tableName, List<TableColumn> columns) {
        return "ALTER TABLE " + tableName + " ADD " + getColumns(columns);
    }

    /**
     * 更新插入
     *
     * @param tableName
     * @param valueColumns
     * @return
     */
    public static String upsert(String tableName, List<ValueColumn> valueColumns) {
        return "UPSERT INTO " + tableName + SqlUtil.getIntoNames(valueColumns)
                + " VALUES " + SqlUtil.getIntoValues(valueColumns);
    }

    private static String getIfNotExists(boolean ifNotExists) {
        String sql = "";
        if (ifNotExists) {
            sql = " IF NOT EXISTS";
        }
        return sql;
    }

    private static String getIfExists(boolean ifExists) {
        String sql = "";
        if (ifExists) {
            sql = " IF EXISTS";
        }
        return sql;
    }

    private static String getColumns(List<TableColumn> columns) {
        String sql = "";
        String colName = "";
        String dataType = "";
        String length = "";
        int count = 0;
        if (columns != null && columns.size() != 0) {
            sql = "\n ";
            for (TableColumn column : columns) {
                colName = column.getColName();
                dataType = column.getDataType();
                length = column.getLength();
                if (StringUtils.isBlank(colName) || StringUtils.isBlank(dataType))
                    continue;
                sql += (count == 0 ? "\n" : "\n,") + colName + " " + getColType(dataType, length);
                if (column.isPrimaryKey()) {
                    sql += " PRIMARY KEY";
                }
                count++;
            }
            sql += "\n";
        }
        return sql;
    }

    private static String getColType(String dataType, String length) {
        if (DataType.VARCHAR.getValue().equals(dataType)) {
            dataType = "VARCHAR(" + length + ")";
        } else if (DataType.CHAR.getValue().equals(dataType)) {
            dataType = "CHAR(" + length + ")";
        } else if (DataType.DECIMAL.getValue().equals(dataType)) {
            dataType = "DECIMAL(" + length + ")";
        } else if (DataType.STRING.getValue().equals(dataType)) {
            dataType = "VARCHAR(65535)";
        }
        return dataType;
    }
}
