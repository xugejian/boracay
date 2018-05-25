package com.hex.bigdata.udsp.common.util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/13
 * TIME:15:54
 */
public class StatementUtil {

    // 线程安全的HashMap
    private static Map<String, Statement> statementPool = new ConcurrentHashMap<>();

    public static Statement getStatement(String key) {
        return statementPool.get(key);
    }

    public static Statement removeStatement(String key) {
        return statementPool.remove(key);
    }

    public static void putStatement(String key, Statement statement) {
        statementPool.put(key, statement);
    }

    /**
     * 杀死正在执行的SQL
     */
    public static void cancel(String consumeId) throws SQLException {
        Statement stmt = removeStatement(consumeId);
        if (stmt != null) {
            stmt.cancel();
            stmt.close();
        }
    }
}
