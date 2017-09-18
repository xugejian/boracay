package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-6.
 */
public class OracleSqlUtil {

    /**
     * 创建表
     *
     * @param tableName
     * @param columns
     * @param tableComment
     * @return
     */
    public static String createTable(String tableName, List<TableColumn> columns, String tableComment) {
        String sql = "CREATE TABLE " + tableName + getColumns(columns) ;//+ ";";
//        sql += "\n" + commentTable(tableName, tableComment) + ";";
//        TableColumn column = null;
//        String colName = "";
//        String colComment = "";
//        if (columns != null && columns.size() != 0) {
//            for (int i = 0; i < columns.size(); i++) {
//                column = columns.get(i);
//                colName = column.getColName();
//                colComment = column.getColComment();
//                if (StringUtils.isNoneBlank(colName) && StringUtils.isNoneBlank(colComment)) {
//                    sql += "\n" + commentColumn(tableName, colName, colComment) + ";";
//                }
//            }
//        }
        return sql;
    }

    public static  List<String>  createColComment(String tableName, List<TableColumn> columns) {
        TableColumn column = null;
        String colName = "";
        String colComment = "";
        List<String> commentSqls = new ArrayList<>();
        if (columns != null && columns.size() != 0) {
            for (int i = 0; i < columns.size(); i++) {
                column = columns.get(i);
                colName = column.getColName();
                colComment = column.getColComment();
                if (StringUtils.isNoneBlank(colName) && StringUtils.isNoneBlank(colComment)) {
                    commentSqls.add(commentColumn(tableName, colName, colComment));
                }
            }
        }
        return commentSqls;
    }

    /**
     * 删除表
     *
     * @param tableName
     * @return
     */
    public static String dropTable(String tableName) {
        return "DROP TABLE " + tableName;
    }


    public static String commentColumn(String tableName, String colName, String colComment) {
        return "COMMENT ON COLUMN " + tableName + "." + colName + " IS '" + colComment + "'";
    }

    public static String commentTable(String tableName, String tableComment) {
        return "COMMENT ON TABLE " + tableName + " IS '" + tableComment + "'";
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
                    if("VARCHAR".equals(dataType)){
                        dataType += "("+ column.getLength()+")";
                    }
                    if (i == 0) {
                        sql += "\n" + colName + " " + dataType;
                    } else {
                        sql += "\n, " + colName + " " + dataType;
                    }
//                    if (StringUtils.isNoneBlank(colComment)) {
//                        sql += " COMMENT '" + colComment + "'";
//                    }
                }
            }
            sql += "\n)";
        }
        sql = sql.replaceAll("STRING","BLOB");
        return sql;
    }

    public static String createPrimaryKey(String tableName, List<TableColumn> columns){
        if(StringUtils.isEmpty(tableName)){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<>();
        for(TableColumn col : columns){
            if(col.isPrimaryKey() && !"STRING".equals(col.getDataType())){
                list.add(col.getColName());
            }
        }
        if(list.size()<=0){
            return "";
        }else{
            return "alter table "+ tableName + " add constraint primaryKey primary key ("+ list.toString().replaceAll("(\\[|\\])","") + ")";
        }
    }
}
