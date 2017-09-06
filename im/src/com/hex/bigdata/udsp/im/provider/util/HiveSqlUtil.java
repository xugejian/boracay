package com.hex.bigdata.udsp.im.provider.util;

import com.hex.bigdata.udsp.im.provider.util.model.RowFormat;
import com.hex.bigdata.udsp.im.provider.util.model.TableColumn;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-6.
 */
public class HiveSqlUtil {
    /**
     * 创建常规表
     *
     * @param isExternal
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param rowFormat
     * @param fileFormat
     * @return
     */
    public static String createTable(boolean isExternal, boolean ifNotExists,
                                     String tableName, List<TableColumn> columns, String tableComment,
                                     List<TableColumn> partitions, RowFormat rowFormat, String fileFormat) {
        return "CREATE" + getExternal(isExternal) + " TABLE"
                + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions) + getRowFormat(rowFormat)
                + getFileFormat(fileFormat);
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

    private static String getFileFormat(String fileFormat) {
        String sql = "";
        if (fileFormat != null && !fileFormat.trim().equals("")) {
            sql = " STORED AS " + fileFormat;
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

    private static String getExternal(boolean isExternal) {
        String sql = "";
        if (isExternal) {
            sql = " EXTERNAL";
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
        if (columns != null && columns.size() != 0) {
            sql = " (";
            for (int i = 0; i < columns.size(); i++) {
                column = columns.get(i);
                colName = column.getColName();
                dataType = column.getDataType();
                colComment = column.getColComment();
                if (StringUtils.isNoneBlank(colName) && StringUtils.isNoneBlank(dataType)) {
                    if (i == 0) {
                        sql += colName + " " + dataType;
                    } else {
                        sql += ", " + colName + " " + dataType;
                    }
                    if (StringUtils.isNoneBlank(colComment)) {
                        sql += " COMMENT '" + colComment + "'";
                    }
                }
            }
            sql += ")";
        }
        return sql;
    }

    private static String getTableComment(String tableComment) {
        String sql = "";
        if (tableComment != null && !tableComment.trim().equals("")) {
            sql = " COMMENT '" + tableComment + "'";
        }
        return sql;
    }

    private static String getPartitions(List<TableColumn> partitions) {
        String sql = "";
        if (partitions != null && partitions.size() != 0) {
            sql = " PARTITIONED BY " + getColumns(partitions);
        }
        return sql;
    }

    private static String getRowFormat(RowFormat rowFormat) {
        String sql = "";
        if (rowFormat != null) {
            sql = " ROW FORMAT DELIMITED";
            String fieldsTerminated = rowFormat.getFieldsTerminated();
            String fieldsEscaped = rowFormat.getFieldsEscaped();
            String linesTerminated = rowFormat.getLinesTerminated();
            if (fieldsTerminated != null && !fieldsTerminated.trim().equals("")) {
                sql += " FIELDS TERMINATED BY '" + fieldsTerminated + "'";
                if (fieldsEscaped != null && !fieldsEscaped.trim().equals("")) {
                    sql += " ESCAPED BY '" + fieldsEscaped + "'";
                }
            }
            if (linesTerminated != null && !linesTerminated.trim().equals("")) {
                sql += " LINES TERMINATED BY '" + linesTerminated + "'";
            }
        }
        return sql;
    }
}
