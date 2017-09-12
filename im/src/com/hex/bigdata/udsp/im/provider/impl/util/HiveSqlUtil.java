package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.im.provider.impl.util.model.*;
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
     * 创建StorageHandler表
     *
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

    /**
     * 查询表
     *
     * @param selectColumns
     * @param tableName
     * @param whereProperties
     * @return
     */
    public static String select(List<String> selectColumns, String tableName, List<WhereProperty> whereProperties) {
        return "SELECT " + getSelectColumns(selectColumns) + " FROM "
                + tableName + getWhere(whereProperties);
    }

    /**
     * 插入表
     *
     * @param isOverwrite
     * @param tableName
     * @param insertColumns
     * @param partitionColumns
     * @return
     */
    public static String insert(boolean isOverwrite, String tableName, List<String> insertColumns, List<String> partitionColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + tableName + getInsertColumns(insertColumns)
                + getPartitionKey(partitionColumns);
    }

    private static String getPartitionKey(List<String> columns) {
        String sql = "";
        if (columns != null && columns.size() != 0) {
            sql = " PARTITION (";
            if (columns != null && columns.size() != 0) {
                for (int i = 0; i < columns.size(); i++) {
                    if (i == 0) {
                        sql += columns.get(i);
                    } else {
                        sql += ", " + columns.get(i);
                    }
                }
            }
            sql += ")";
        }
        return sql;
    }

    private static String getWhere(List<WhereProperty> whereProperties) {
        String sql = "";
        WhereProperty whereProperty = null;
        String name = null;
        String value = null;
        DataType type = null;
        Operator operator = null;
        String str = null;
        if (whereProperties != null && whereProperties.size() != 0) {
            sql = " WHERE ";
            for (int i = 0; i < whereProperties.size(); i++) {
                whereProperty = whereProperties.get(i);
                name = whereProperty.getName();
                value = whereProperty.getValue();
                type = whereProperty.getType();
                operator = whereProperty.getOperator();
                if (StringUtils.isBlank(name) || StringUtils.isBlank(value) || operator == null) {
                    continue;
                }
                if (DataType.INT == type || DataType.TINYINT == type || DataType.DOUBLE == type
                        || DataType.DECIMAL == type || DataType.BIGINT == type || DataType.FLOAT == type
                        || DataType.SMALLINT == type) {
                    if (Operator.EQ == operator) {
                        str = name + " = " + value;
                    } else if (Operator.NE == operator) {
                        str = name + " != " + value;
                    } else if (Operator.GE == operator) {
                        str = name + " >= " + value;
                    } else if (Operator.GT == operator) {
                        str = name + " > " + value;
                    } else if (Operator.LE == operator) {
                        str = name + " <= " + value;
                    } else if (Operator.LT == operator) {
                        str = name + " < " + value;
                    } else if (Operator.IN == operator) {
                        str = name + " IN (" + value + ")";
                    } else if (Operator.LK == operator) {
                        str = name + " LIKE '%" + value + "%'";
                    } else if (Operator.RLIKE == operator) {
                        str = name + " LIKE '" + value + "%'";
                    }
                } else {
                    if (Operator.EQ == operator) {
                        str = name + " = '" + value + "'";
                    } else if (Operator.NE == operator) {
                        str = name + " != '" + value + "'";
                    } else if (Operator.GE == operator) {
                        str = name + " >= '" + value + "'";
                    } else if (Operator.GT == operator) {
                        str = name + " > '" + value + "'";
                    } else if (Operator.LE == operator) {
                        str = name + " <= '" + value + "'";
                    } else if (Operator.LT == operator) {
                        str = name + " < '" + value + "'";
                    } else if (Operator.IN == operator) {
                        String[] strs = value.split(",");
                        for (int j = 0; j < strs.length; j++) {
                            if (j == 0) {
                                value = "'" + strs[j] + "'";
                            } else {
                                value += ",'" + strs[j] + "'";
                            }
                        }
                        str = name + " IN (" + value + ")";
                    } else if (Operator.LK == operator) {
                        str = name + " LIKE '%" + value + "%'";
                    } else if (Operator.RLIKE == operator) {
                        str = name + " LIKE '" + value + "%'";
                    }
                }
                sql += (i == 0 ? str : " AND " + str);
            }
        }
        return sql;
    }

    private static String getInsertColumns(List<String> columns) {
        String sql = " ";
        if (columns != null && columns.size() != 0) {
            sql = " (";
            for (int i = 0; i < columns.size(); i++) {
                if (i == 0) {
                    sql += columns.get(i);
                } else {
                    sql += ", " + columns.get(i);
                }
            }
            sql += ")";
        }
        return sql;
    }

    private static String getSelectColumns(List<String> columns) {
        String sql = " * ";
        if (columns != null && columns.size() != 0) {
            for (int i = 0; i < columns.size(); i++) {
                if (i == 0) {
                    sql = " " + columns.get(i);
                } else {
                    sql += ", " + columns.get(i);
                }
            }
        }
        return sql;
    }

    private static String getOverwrite(boolean isOverwrite) {
        String sql = " INTO";
        if (isOverwrite) {
            sql = " OVERWRITE";
        }
        return sql;
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
