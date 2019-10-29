package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.metadata.db.ClientFactory;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.im.converter.RealtimeTargetConverter;
import com.hex.bigdata.udsp.im.converter.impl.util.ImpalaSqlUtil;
import com.hex.bigdata.udsp.im.converter.impl.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.converter.model.FileFormat;
import com.hex.bigdata.udsp.im.converter.model.TableColumn;
import com.hex.bigdata.udsp.im.converter.model.ValueColumn;
import com.hex.bigdata.udsp.im.converter.model.WhereProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
//@Component("com.hex.bigdata.udsp.im.converter.impl.ImpalaConverter")
public class ImpalaConverter extends JdbcWrapper implements RealtimeTargetConverter {
    private static Logger logger = LogManager.getLogger (ImpalaConverter.class);

    @Override
    protected List<String> createSchemaSqls(String tableName, List<TableColumn> columns, String tableComment) {
        String[] sqls = {ImpalaSqlUtil.createTable (false, tableName, columns, tableComment,
                FileFormat.HIVE_FILE_FORMAT_PARQUET)};
        return Arrays.asList (sqls);
    }

    @Override
    protected String dropSchemaSql(String tableName) {
        return ImpalaSqlUtil.dropTable (true, tableName);
    }

    @Override
    protected List<String> addColumnSqls(String tableName, List<TableColumn> addColumns) {
        String[] sqls = {ImpalaSqlUtil.addColumns (tableName, addColumns)};
        return Arrays.asList (sqls);
    }

    @Override
    protected DataType getColType(String type) {
        switch (type.toUpperCase ()) {
            case "VARCHAR":
                return DataType.VARCHAR;
            case "STRING":
                return DataType.STRING;
            case "DECIMAL":
                return DataType.DECIMAL;
            case "CHAR":
                return DataType.CHAR;
            case "FLOAT":
                return DataType.FLOAT;
            case "DOUBLE":
                return DataType.DOUBLE;
            case "TIMESTAMP":
            case "DATE":
                return DataType.TIMESTAMP;
            case "INT":
                return DataType.INT;
            case "BIGINT":
                return DataType.BIGINT;
            case "TINYINT":
                return DataType.TINYINT;
            case "SMALLINT":
                return DataType.SMALLINT;
            case "BOOLEAN":
                return DataType.BOOLEAN;
            default:
                return DataType.STRING;
        }
    }

    @Override
    protected List<Column> getColumns(Connection conn, String dbName, String tbName) throws SQLException {
        return ClientFactory.createMetaClient (AcquireType.JDBCAPI, DBType.IMPALA, conn)
                .getColumns (dbName, tbName);
    }

    @Override
    protected String insertSql(String tableName, List<ValueColumn> valueColumns) {
        return ImpalaSqlUtil.insert (tableName, valueColumns);
    }

    @Override
    protected String updateSql(String tableName, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) {
        return ImpalaSqlUtil.update (tableName, valueColumns, whereProperties);
    }
}