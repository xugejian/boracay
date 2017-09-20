package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
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
        TableColumn column = null;
        String colName = "";
        String dataType = "";
        String colComment = "";
        String length = "";
        if (columns != null && columns.size() != 0) {
            sql = "\n (";
            for (int i = 0; i < columns.size(); i++) {
                column = columns.get(i);
                colName = column.getColName();
                dataType = column.getDataType();
                colComment = column.getColComment();
                length = column.getLength();
                if (StringUtils.isNoneBlank(colName) && StringUtils.isNoneBlank(dataType)) {
                    dataType = getColType(dataType, length);
                    if (i == 0) {
                        sql += "\n" + colName + " " + dataType;
                    } else {
                        sql += "\n, " + colName + " " + dataType;
                    }
                    if (column.isPrimaryKey() && !"STRING".equals(column.getDataType())) { //类型不能指定为pk
                        sql += " PRIMARY KEY ";
                    }
                    if (StringUtils.isNoneBlank(colComment)) {
                        sql += " COMMENT '" + colComment + "'";
                    }
                }
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
