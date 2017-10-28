package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.im.provider.impl.factory.JdbcConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import com.hex.bigdata.udsp.im.service.BatchJobService;
import com.hex.bigdata.udsp.im.thread.HiveGetLogThread;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.dbcp.DelegatingStatement;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hive.jdbc.HiveStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-22.
 */
public class HiveJdbcUtil {
    private static Logger logger = LogManager.getLogger(HiveJdbcUtil.class);
    private static Map<String, HiveStatement> hiveStatementPool = new HashMap<>();
    private static Map<String, JdbcConnectionPoolFactory> jdbcConnectionPool = new HashMap<>();

    public static synchronized HiveStatement getHiveStatement(String key) {
        return hiveStatementPool.get(key);
    }

    public static synchronized void removeHiveStatement(String key) {
        hiveStatementPool.remove(key);
    }

    public static synchronized void putHiveStatement(String key, HiveStatement statement) {
        hiveStatementPool.put(key, statement);
    }

//    public static synchronized JdbcConnectionPoolFactory getJdbcConnectionFactory(JdbcDatasource datasource) {
//        String dsId = datasource.getId();
//        if (jdbcConnectionPool == null) {
//            jdbcConnectionPool = new HashMap<>();
//        }
//        JdbcConnectionPoolFactory factory = jdbcConnectionPool.get(dsId);
//        if (factory == null) {
//            GenericObjectPool.Config config = new GenericObjectPool.Config();
//            config.lifo = true;
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getMinIdle()))
//                config.minIdle = Integer.valueOf(datasource.getMinIdle());// 最小空闲连接数
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getMaxIdle()))
//                config.maxIdle = Integer.valueOf(datasource.getMaxIdle());// 数据库最大连接数
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getMaxActive()))
//                config.maxActive = Integer.valueOf(datasource.getMaxActive());// 设置最大并发数
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getMaxWait()))
//                config.maxWait = Integer.valueOf(datasource.getMaxWait());// 最长等待时间，单位毫秒
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getTimeBetweenEvictionRunsMillis()))
//                config.timeBetweenEvictionRunsMillis = Integer.valueOf(datasource.getTimeBetweenEvictionRunsMillis()); // N毫秒检测一次是否有死掉的线程
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getMinEvictableIdleTimeMillis()))
//                config.minEvictableIdleTimeMillis = Integer.valueOf(datasource.getMinEvictableIdleTimeMillis());// 空闲连接N毫秒中后释放
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getTestWhileIdle()))
//                config.testWhileIdle = Boolean.valueOf(datasource.getTestWhileIdle());
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getTestOnBorrow()))
//                config.testOnBorrow = Boolean.valueOf(datasource.getTestOnBorrow());
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(datasource.getTestOnReturn()))
//                config.testOnReturn = Boolean.valueOf(datasource.getTestOnReturn());
//            factory = new JdbcConnectionPoolFactory(config, datasource);
//        }
//        jdbcConnectionPool.put(dsId, factory);
//        return factory;
//    }
//
//    public static Connection getConnection(JdbcDatasource datasource) {
//        try {
//            return getJdbcConnectionFactory(datasource).getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static Connection getConnection(HiveDatasource datasource) {
        Connection conn = null;
        try {
            Class.forName(datasource.getDriverClass());
            String username = datasource.getUsername();
            String password = datasource.getPassword();
            if (StringUtils.isBlank(username)) username = "";
            if (StringUtils.isBlank(password)) password = "";
            conn = DriverManager.getConnection(datasource.getJdbcUrl(), username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean executeUpdate(String key, HiveDatasource datasource, String updateSql) throws SQLException {
        logger.info("HIVE EXECUTE UPDATE SQL [START]");
        logger.info("UPDATE SQL: \n" + updateSql);
        BatchJobService batchService = (BatchJobService) WebApplicationContextUtil.getBean("batchJobService");
        Connection conn = null;
        Statement stmt = null;
        HiveStatement hiveStmt = null;
        int rs = -1;
        try {
            /**
             * 通过DBCP方式获取Statement
             *
             * DelegatingStatement转成HiveStatement
             */
            conn = JdbcUtil.getConnection(datasource);
            stmt = conn.createStatement();
            //hiveStmt = (HiveStatement) ((DelegatingStatement) ((DelegatingStatement) stmt).getDelegate()).getDelegate();
            hiveStmt = (HiveStatement) ((DelegatingStatement) stmt).getInnermostDelegate();

//            /**
//             * 通过传统的方式获取Statement
//             */
//            conn = getConnection(datasource);
//            stmt = conn.createStatement();
//            hiveStmt = (HiveStatement) stmt;

            // 加入缓存池
            putHiveStatement(key, hiveStmt);

            // 状态改为“正在构建”
            batchService.building(key, 0);

            // 启动 Hive Jdbc 日志监控
            HiveGetLogThread thread = new HiveGetLogThread(key, 500L, 10);
            thread.start();

            rs = hiveStmt.executeUpdate(updateSql);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        } finally {
            //com.hex.bigdata.metadata.db.util.JdbcUtil.close(hiveStmt);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(stmt);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
            removeHiveStatement(key);
            logger.info("HIVE EXECUTE UPDATE SQL [END]");
        }
        return rs == 0 ? true : false;
    }
}
