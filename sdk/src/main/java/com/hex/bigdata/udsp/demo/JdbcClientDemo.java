package com.hex.bigdata.udsp.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Created by JunjieM on 2018-7-26.
 */
public class JdbcClientDemo {
    private static Logger logger = LogManager.getLogger(JdbcClientDemo.class);

    /**
     * 通过UDSP的JDBC查询
     *
     * @throws Exception
     */
    public void query() throws Exception {
        // jdbc:udsp://[hostname]:[port]/[service_name]
        String jdbcUrl = "jdbc:udsp://localhost:9089/dev_oracle";
        String username = "admin";
        String password = "000000";
        Class.forName("com.hex.bigdata.udsp.jdbc.UdspDriver");
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from T_GF_FUNCATION t");
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                logger.info("colName:" + rsmd.getColumnLabel(i));
                logger.info("colValue:" + rs.getString(i));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        JdbcClientDemo demo = new JdbcClientDemo();
        demo.query();
        System.exit(0);
    }
}
