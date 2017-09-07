package com.hex.bigdata.metadata.db.jdbcinfo;

import com.hex.bigdata.metadata.db.Helper;

/**
 * Created by junjiem on 2016-6-29.
 */
public interface JdbcInfoHelper extends Helper {

    String getCurrentDbNameSql();

    String getDatabasesSql();

    String getTablesSql(String dbName);

    String getColumnsSql(String dbName, String tbName);
}
