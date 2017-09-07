package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.metadata.db.util.JdbcUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by junjiem on 2016-6-23.
 */
public abstract class BaseClient implements Client {
    protected String currDbName;
    protected Connection conn;
    protected Helper helper;

    public BaseClient(Connection conn, DBType dbType) throws SQLException {
        this.conn = conn;
        this.helper = getHelper(dbType);
    }

    @Override
    public String getDbName() {
        return this.currDbName;
    }

    @Override
    public Client use(String dbName) {
        this.currDbName = dbName;
        return this;
    }

    @Override
    public List<Database> getDatabases() throws SQLException {
        return helper.getDatabases();
    }

    @Override
    public List<Column> getColumns(String dbName, String tbName) throws SQLException {
        return helper.getColumns(dbName, tbName);
    }

    ;

    @Override
    public List<Column> getColumns(String tbName) throws SQLException {
        return this.getColumns(this.getDbName(), tbName);
    }

    @Override
    public List<Table> getTables(String dbName) throws SQLException {
        return helper.getTables(dbName);
    }

    ;

    @Override
    public List<Table> getTables() throws SQLException {
        return this.getTables(this.getDbName());
    }

    @Override
    public Table getTable(String dbName, String tbName) throws SQLException {
        return helper.getTable(dbName, tbName);
    }

    ;

    @Override
    public Table getTable(String tbName) throws SQLException {
        return this.getTable(this.getDbName(), tbName);
    }

    @Override
    public Database getDatabase(String dbName) throws SQLException {
        return helper.getDatabase(dbName);
    }

    @Override
    public Database getDatabase() throws SQLException {
        return helper.getDatabase(this.getDbName());
    }

    @Override
    public void close() {
        JdbcUtil.close(this.conn);
    }
}
