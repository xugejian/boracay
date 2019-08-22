package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.metadata.db.ClientFactory;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.im.converter.RealtimeTargetConverter;
import com.hex.bigdata.udsp.im.converter.impl.util.MysqlSqlUtil;
import com.hex.bigdata.udsp.im.converter.impl.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.converter.model.TableColumn;
import com.hex.bigdata.udsp.im.converter.model.ValueColumn;
import com.hex.bigdata.udsp.im.converter.model.WhereProperty;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
//@Component("com.hex.bigdata.udsp.im.converter.impl.MysqlConverter")
public class MysqlConverter extends JdbcWrapper implements RealtimeTargetConverter {
    private static Logger logger = LogManager.getLogger (MysqlConverter.class);

    @Override
    protected List<String> createSchemaSqls(String tableName, List<TableColumn> columns, String tableComment) {
        String[] sqls = {MysqlSqlUtil.createTable (false, tableName, columns, tableComment)};
        return Arrays.asList (sqls);
    }

    @Override
    protected String dropSchemaSql(String tableName) {
        return MysqlSqlUtil.dropTable (true, tableName);
    }

    @Override
    protected List<String> addColumnSqls(String tableName, List<TableColumn> addColumns) {
        String[] sqls = {MysqlSqlUtil.addColumns (tableName, addColumns)};
        return Arrays.asList (sqls);
    }

    @Override
    protected DataType getColType(String type) {
        switch (type.toUpperCase ()) {
            case "VARCHAR":
                return DataType.VARCHAR;
            case "BLOB":
            case "TEXT":
                return DataType.STRING;
            case "DECIMAL":
                return DataType.DECIMAL;
            case "CHAR":
                return DataType.CHAR;
            case "INT":
                return DataType.INT;
            case "BIGINT":
                return DataType.BIGINT;
            case "TINYINT":
                return DataType.TINYINT;
            case "DOUBLE":
                return DataType.DOUBLE;
            case "TIMESTAMP":
                return DataType.TIMESTAMP;
            default:
                return DataType.STRING;
        }
    }

    @Override
    protected List<Column> getColumns(Connection conn, String dbName, String tbName) throws SQLException {
        return ClientFactory.createMetaClient (AcquireType.JDBCAPI, DBType.MYSQL, conn)
                .getColumns (dbName, tbName);
    }

    @Override
    protected String insertSql(String tableName, List<ValueColumn> valueColumns) {
        return MysqlSqlUtil.insert (tableName, valueColumns);
    }

    @Override
    protected String updateSql(String tableName, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) {
        return MysqlSqlUtil.update (tableName, valueColumns, whereProperties);
    }
}