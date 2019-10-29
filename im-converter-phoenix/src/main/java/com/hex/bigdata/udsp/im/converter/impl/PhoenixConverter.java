package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.metadata.db.ClientFactory;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.im.converter.RealtimeTargetConverter;
import com.hex.bigdata.udsp.im.converter.impl.model.JdbcDatasource;
import com.hex.bigdata.udsp.im.converter.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.converter.impl.util.PhoenixSqlUtil;
import com.hex.bigdata.udsp.im.converter.impl.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.converter.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 * Created by PC on 2018/10/8.
 */
public class PhoenixConverter extends JdbcWrapper implements RealtimeTargetConverter {
    private static Logger logger = LogManager.getLogger(PhoenixConverter.class);

    @Override
    protected List<String> createSchemaSqls(String tableName, List<TableColumn> columns, String tableComment) {
        String[] sqls = {PhoenixSqlUtil.createTable(true, tableName, columns)};
        return Arrays.asList(sqls);
    }

    @Override
    protected String dropSchemaSql(String tableName) {
        return PhoenixSqlUtil.dropTable(true, tableName);
    }

    @Override
    protected List<String> addColumnSqls(String tableName, List<TableColumn> addColumns) {
        String[] sqls = {PhoenixSqlUtil.addColumns(tableName, addColumns)};
        return Arrays.asList(sqls);
    }

    @Override
    protected DataType getColType(String type) {
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type) {
            case "VARCHAR":
                dataType = DataType.VARCHAR;
                break;
            case "DECIMAL":
                dataType = DataType.DECIMAL;
                break;
            case "CHAR":
                dataType = DataType.CHAR;
                break;
            case "FLOAT":
            case "UNSIGNED_FLOAT":
                dataType = DataType.FLOAT;
                break;
            case "DOUBLE":
            case "UNSIGNED_DOUBLE":
                dataType = DataType.DOUBLE;
                break;
            case "TIME":
            case "UNSIGNED_TIME":
            case "DATE":
            case "UNSIGNED_DATE":
            case "TIMESTAMP":
            case "UNSIGNED_TIMESTAMP":
                dataType = DataType.TIMESTAMP;
                break;
            case "INTEGER":
            case "UNSIGNED_INT":
                dataType = DataType.INT;
                break;
            case "BIGINT":
            case "UNSIGNED_LONG":
                dataType = DataType.BIGINT;
                break;
            case "TINYINT":
            case "UNSIGNED_TINYINT":
                dataType = DataType.TINYINT;
                break;
            case "SMALLINT":
            case "UNSIGNED_SMALLINT":
                dataType = DataType.SMALLINT;
                break;
            case "BOOLEAN":
                dataType = DataType.BOOLEAN;
                break;
            default:
                dataType = DataType.STRING;
        }
        return dataType;
    }

    @Override
    protected List<Column> getColumns(Connection conn, String dbName, String tbName) throws SQLException {
        // Phoenix通过JDBCAPI方式获取字段集合时库名和表名大小写敏感
        return ClientFactory.createMetaClient(AcquireType.JDBCAPI, DBType.PHOENIX, conn)
                .getColumns(StringUtils.isBlank(dbName) ? dbName : dbName.toUpperCase(),
                        StringUtils.isBlank(tbName) ? tbName : tbName.toUpperCase());
    }

    /**
     * 增量插入
     * 注：没有相同主键时数据插入，有相同主键时数据更新
     */
    @Override
    protected void insertInto(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns) throws Exception {
        JdbcDatasource jdbcDatasource = new JdbcDatasource(metadata.getDatasource());
        JdbcUtil.executeUpdate(jdbcDatasource, PhoenixSqlUtil.upsert(metadata.getTbName(), valueColumns));
    }

    @Override
    @Deprecated
    protected String insertSql(String tableName, List<ValueColumn> valueColumns) {
        return null;
    }

    /**
     * 更新、插入
     * 注：没有相同主键时数据插入，有相同主键时数据更新
     */
    @Override
    protected void updateInsert(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        insertInto(metadata, modelMappings, valueColumns);
    }

    @Override
    @Deprecated
    protected String updateSql(String tableName, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) {
        return null;
    }

    /**
     * 匹配更新
     * 注：没有相同主键时数据插入，有相同主键时数据更新
     */
    @Override
    protected void matchingUpdate(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        insertInto(metadata, modelMappings, valueColumns);
    }
}
