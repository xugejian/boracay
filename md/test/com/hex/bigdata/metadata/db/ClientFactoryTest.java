package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.metadata.db.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientFactoryTest {

    public static Connection getMysql() {
        DBType dbType = DBType.MYSQL;
        String host = "goupwith.mysql.rds.aliyuncs.com";
        String port = "3306";
        String dbName = "dcp";
        String username = "edh";
        String password = "edh159357";
        return JdbcUtil.newConnection(dbType, host, port, dbName, username, password);
    }

    public static Connection getOracle() {
        DBType dbType = DBType.ORACLE;
        String host = "dev.goupwith.com";
        String port = "1521";
        String dbName = "sks";
        String username = "etl";
        String password = "etl";
        return JdbcUtil.newConnection(dbType, host, port, dbName, username, password);
    }

    public static Connection getImpala() {
        DBType dbType = DBType.IMPALA;
        String host = "192.168.1.61";
        String port = "21050";
        String dbName = "default";
        String username = "";
        String password = "";
        return JdbcUtil.newConnection(dbType, host, port, dbName, username, password);
    }

    public static Connection getHive() {
        DBType dbType = DBType.HIVE;
        String host = "192.168.1.61";
        String port = "10000";
        String dbName = "default";
        String username = "";
        String password = "";
        return JdbcUtil.newConnection(dbType, host, port, dbName, username, password);
    }

    @Test
    public void test() throws SQLException {
        Connection conn = getHive();
        //Connection conn = getImpala();
        //Connection conn = getOracle();
        //Connection conn = getMysql();

        Client client = ClientFactory.createMetaClient(AcquireType.JDBCINFO, DBType.HIVE, conn);

        List<Database> dbs = client.getDatabases();

        List<Table> tbs = client.getTables("sdata");

        List<Column> cols = client.getColumns("sdata","txt_s00_bifm02");

    }

    @Test
    public void test2() throws SQLException {
        //Connection conn = getHive();
        //Connection conn = getImpala();
        Connection conn = getOracle();
        //Connection conn = getMysql();

        Client client = ClientFactory.createMetaClient(AcquireType.JDBCAPI, DBType.ORACLE, conn);

        List<Database> dbs = client.getDatabases();

        List<Table> tbs = client.getTables("sdata");

        List<Column> cols = client.getColumns("sdata","txt_s00_bifm02");

    }


}