package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.ValueColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-6.
 */
public class MysqlSqlUtil {

    /**
     * 创建表
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @return
     */
    public static String createTable(boolean ifNotExists, String tableName,
                                     List<TableColumn> columns, String tableComment) {
        return "CREATE TABLE " + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment);
    }

    /**
     * 删除表
     *
     * @param ifExists
     * @param tableName
     * @return
     */
    public static String dropTable(boolean ifExists, String tableName) {
        return "DROP TABLE" + getIfExists(ifExists) + " " + tableName;
    }

    /**
     * 更新
     *
     * @param tableName
     * @param valueColumns
     * @param whereProperties
     * @return
     */
    public static String update(String tableName, List<ValueColumn> valueColumns,
                                List<WhereProperty> whereProperties) {
        return "UPDATE " + tableName + getSetValues(valueColumns) + getWhere(whereProperties);
    }

    /**
     * 插入
     *
     * @param tableName
     * @param valueColumns
     * @return
     */
    public static String insert(String tableName, List<ValueColumn> valueColumns) {
        return "INSERT INTO " + tableName + getIntoNames(valueColumns) + " VALUES " + getIntoValues(valueColumns);
    }

    private static String getIntoValues(List<ValueColumn> valueColumns) {
        String sql = "";
        DataType dataType = null;
        String value = null;
        int count = 0;
        if (valueColumns != null && valueColumns.size() != 0) {
            sql = " (";
            for (ValueColumn column : valueColumns) {
                dataType = column.getDataType();
                value = column.getValue();
                sql += (count == 0 ? "" : ", ");
                sql += getValue(dataType, value);
            }
            sql += ")";
        }
        return sql;
    }

    private static String getIntoNames(List<ValueColumn> valueColumns) {
        String sql = "";
        String colName = null;
        int count = 0;
        if (valueColumns != null && valueColumns.size() != 0) {
            sql = " (";
            for (ValueColumn column : valueColumns) {
                colName = column.getColName();
                sql += (count == 0 ? "" : ", ");
                sql += colName;
            }
            sql += ")";
        }
        return sql;
    }

    private static String getWhere(List<WhereProperty> whereProperties) {
        String sql = "";
        String name = null;
        String value = null;
        DataType dataType = null;
        Operator operator = null;
        int count = 0;
        if (whereProperties != null && whereProperties.size() != 0) {
            sql = "\n WHERE ";
            for (WhereProperty whereProperty : whereProperties) {
                name = whereProperty.getName();
                value = whereProperty.getValue();
                dataType = whereProperty.getType();
                operator = whereProperty.getOperator();
                if (StringUtils.isBlank(name) || StringUtils.isBlank(value) || operator == null)
                    continue;
                sql += (count == 0 ? "" : " AND ");
                sql += name + " = " + SqlUtil.getCondition(value, dataType, operator);
                count++;
            }
        }
        return sql;
    }

    private static String getSetValues(List<ValueColumn> valueColumns) {
        String sql = "";
        if (valueColumns != null && valueColumns.size() != 0) {
            sql = "\n SET " + getValues(valueColumns);
        }
        return sql;
    }

    private static String getValues(List<ValueColumn> valueColumns) {
        String sql = "";
        String colName = null;
        DataType dataType = null;
        String value = null;
        int count = 0;
        if (valueColumns != null && valueColumns.size() != 0) {
            for (ValueColumn column : valueColumns) {
                colName = column.getColName();
                dataType = column.getDataType();
                value = column.getValue();
                sql += (count == 0 ? "\n" : "\n,");
                sql += colName + " = " + getValue(dataType, value);
                count++;
            }
        }
        return sql;
    }

    private static String getValue(DataType dataType, String value) {
        if (DataType.STRING == dataType || DataType.VARCHAR == dataType || DataType.CHAR == dataType
                || DataType.TIMESTAMP == dataType || DataType.BOOLEAN == dataType) {
            value = "'" + value + "'";
        }
        return value;
    }

    private static String getIfExists(boolean ifExists) {
        String sql = "";
        if (ifExists) {
            sql = " IF EXISTS";
        }
        return sql;
    }

    private static String getIfNotExists(boolean ifNotExists) {
        String sql = "";
        if (ifNotExists) {
            sql = " IF NOT EXISTS";
        }
        return sql;
    }

    private static String getColumns(List<TableColumn> columns) {
        String sql = "";
        String colName = "";
        String dataType = "";
        String colComment = "";
        String length = "";
        int count = 0;
        if (columns != null && columns.size() != 0) {
            sql = "\n (";
            for (TableColumn column : columns) {
                colName = column.getColName();
                dataType = column.getDataType();
                colComment = column.getColComment();
                length = column.getLength();
                if (StringUtils.isBlank(colName) || StringUtils.isBlank(dataType))
                    continue;
                dataType = getColType(dataType, length);
                sql += (count == 0 ? "\n" : "\n,");
                sql += colName + " " + dataType;
                if (column.isPrimaryKey() && !"STRING".equals(column.getDataType())) { //类型不能指定为pk
                    sql += " PRIMARY KEY ";
                }
                if (StringUtils.isNoneBlank(colComment)) {
                    sql += " COMMENT '" + colComment + "'";
                }
                count++;
            }
            sql += "\n)";
        }
        sql = sql.replaceAll("STRING", "BLOB");
        return sql;
    }

    private static String getColType(String dataType, String length) {
        if ("VARCHAR".equals(dataType)) {
            dataType = "VARCHAR(" + length + ")";
        } else if ("CHAR".equals(dataType)) {
            dataType = "CHAR(" + length + ")";
        } else if ("DECIMAL".equals(dataType)) {
            dataType = "DECIMAL(" + length + ")";
        }
        return dataType;
    }

    private static String getTableComment(String tableComment) {
        String sql = "";
        if (tableComment != null && !tableComment.trim().equals("")) {
            sql = "\n COMMENT '" + tableComment + "'";
        }
        return sql;
    }
}
