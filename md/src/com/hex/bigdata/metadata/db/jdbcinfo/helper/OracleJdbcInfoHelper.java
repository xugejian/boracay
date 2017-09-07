package com.hex.bigdata.metadata.db.jdbcinfo.helper;

import com.hex.bigdata.metadata.db.jdbcinfo.BaseJdbcInfoHelper;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by junjiem on 2016-6-29.
 */
public class OracleJdbcInfoHelper extends BaseJdbcInfoHelper {

    public OracleJdbcInfoHelper(Connection conn) {
        super(conn);
    }

    @Override
    public String getDatabasesSql() {
        return null;
    }

    @Override
    public String getTablesSql(String dbName) {
        //ParameterUtil.isEmpty(dbName, "Oracle数据库的库名");
        return "select table_name from user_tables";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        //ParameterUtil.isEmpty(dbName, "Oracle数据库的库名");
        return "select * from " + tbName + " where 1=0";
    }

    @Override
    protected String getDbType() {
        return DBType.ORACLE.getValue();
    }

    @Override
    public List<ColumnType> getColumnTypes() throws SQLException {
        return null;
    }
}
