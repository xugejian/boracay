package com.hex.bigdata.udsp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Created by JunjieM on 2018-7-26.
 */
public class UdspJdbcTest {
    private static Logger logger = LogManager.getLogger(UdspJdbcTest.class);

    public static void main(String[] args) throws Exception {
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

        rs = stmt.executeQuery("select count(1) as num from T_GF_FUNCATION t");
        rsmd = rs.getMetaData();
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                logger.info("colName:" + rsmd.getColumnLabel(i));
                logger.info("colValue:" + rs.getString(i));
            }
        }

        System.exit(0);
    }
}
