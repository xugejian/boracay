package com.hex.bigdata.udsp.olq.utils;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/13
 * TIME:15:54
 */
public class OLQCommUtil {
    /**
     * 元数据列信息插入到Map
     * @param rsmd
     * @return
     */
    public static LinkedHashMap<String,String> putColumnIntoMap(ResultSetMetaData rsmd){
        LinkedHashMap<String,String> columnMap = new LinkedHashMap<>();
        try {
            for (int i = 1; i <= rsmd.getColumnCount() ; i++) {
                columnMap.put(rsmd.getColumnName(i),rsmd.getColumnTypeName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnMap;
    }
}
