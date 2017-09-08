package com.hex.bigdata.udsp.im.provider.util;

import com.hex.bigdata.udsp.im.provider.util.model.RowFormat;
import com.hex.bigdata.udsp.im.provider.util.model.SerDeProperty;
import com.hex.bigdata.udsp.im.provider.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.util.model.TblProperty;
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

    /**
     * @param isExternal
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param storageHandlerClass
     * @param serDeProperties
     * @param tblProperties
     * @return
     */
    public static String createStorageHandlerTable(boolean isExternal, boolean ifNotExists,
                                                   String tableName, List<TableColumn> columns, String tableComment,
                                                   List<TableColumn> partitions, String storageHandlerClass,
                                                   List<SerDeProperty> serDeProperties, List<TblProperty> tblProperties) {
        return "CREATE" + getExternal(isExternal) + " TABLE"
                + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions)
                + getStoredBy(storageHandlerClass)
                + getSerDeProperties(serDeProperties)
                + getTblProperties(tblProperties);
    }

    private static String getStoredBy(String storageHandlerClass) {
        return "\n STORED BY '" + storageHandlerClass + "'";
    }

    private static String getSerDeProperties(List<SerDeProperty> serDeProperties) {
        String sql = "";
        SerDeProperty property = null;
        if (serDeProperties != null && serDeProperties.size() != 0) {
            sql = "\n WITH SERDEPROPERTIES (";
            for (int i = 0; i < serDeProperties.size(); i++) {
                property = serDeProperties.get(i);
                if (i == 0)
                    sql += "\n'" + property.getKey() + "' = '" + property.getValue() + "'";
                else
                    sql += "\n ,'" + property.getKey() + "' = '" + property.getValue() + "'";
            }
            sql += "\n)";
        }
        return sql;
    }

    private static String getTblProperties(List<TblProperty> tblPropertiess) {
        String sql = "";
        TblProperty property = null;
        if (tblPropertiess != null && tblPropertiess.size() != 0) {
            sql = "\n TBLPROPERTIES (";
            for (int i = 0; i < tblPropertiess.size(); i++) {
                property = tblPropertiess.get(i);
                if (i == 0)
                    sql += "\n'" + property.getKey() + "' = '" + property.getValue() + "'";
                else
                    sql += "\n ,'" + property.getKey() + "' = '" + property.getValue() + "'";
            }
            sql += "\n)";
        }
        return sql;
    }

    private static String getFileFormat(String fileFormat) {
        String sql = "";
        if (fileFormat != null && !fileFormat.trim().equals("")) {
            sql = "\n STORED AS " + fileFormat;
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
            sql = "\n (";
            for (int i = 0; i < columns.size(); i++) {
                column = columns.get(i);
                colName = column.getColName();
                dataType = column.getDataType();
                colComment = column.getColComment();
                if (StringUtils.isNoneBlank(colName) && StringUtils.isNoneBlank(dataType)) {
                    if (i == 0) {
                        sql += "\n" + colName + " " + dataType;
                    } else {
                        sql += "\n, " + colName + " " + dataType;
                    }
                    if (StringUtils.isNoneBlank(colComment)) {
                        sql += " COMMENT '" + colComment + "'";
                    }
                }
            }
            sql += "\n)";
        }
        return sql;
    }

    private static String getTableComment(String tableComment) {
        String sql = "";
        if (tableComment != null && !tableComment.trim().equals("")) {
            sql = "\n COMMENT '" + tableComment + "'";
        }
        return sql;
    }

    private static String getPartitions(List<TableColumn> partitions) {
        String sql = "";
        if (partitions != null && partitions.size() != 0) {
            sql = "\n PARTITIONED BY " + getColumns(partitions);
        }
        return sql;
    }

    private static String getRowFormat(RowFormat rowFormat) {
        String sql = "";
        if (rowFormat != null) {
            sql = "\n ROW FORMAT DELIMITED";
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
