package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.metadata.db.ClientFactory;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.metadata.db.util.JdbcUtil;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.constant.DatasourceType;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.FileFormat;
import com.hex.bigdata.udsp.im.provider.impl.util.model.RowFormat;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.util.ImUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.HiveProvider")
public class HiveProvider extends JdbcWrapper {
    private static Logger logger = LogManager.getLogger(HiveProvider.class);

    @Override
    protected MetadataCol getMetadataCols(ResultSetMetaData md, int i) throws SQLException {
        logger.debug("-----------------------------------------------------------");
        logger.debug("getCatalogName:" + md.getCatalogName(i));
        logger.debug("getSchemaName:" + md.getSchemaName(i));
        logger.debug("getTableName:" + md.getTableName(i));
        logger.debug("getColumnClassName:" + md.getColumnClassName(i));
        logger.debug("getColumnName:" + md.getColumnName(i));
        logger.debug("getColumnLabel:" + md.getColumnLabel(i));
        logger.debug("getColumnDisplaySize:" + md.getColumnDisplaySize(i));
        logger.debug("getColumnType:" + md.getColumnType(i));
        logger.debug("getColumnTypeName:" + md.getColumnTypeName(i));
        logger.debug("getPrecision:" + md.getPrecision(i));
        logger.debug("getScale:" + md.getScale(i));

        MetadataCol metadataCol = new MetadataCol();
        // TODO ...

        return metadataCol;
    }

    @Override
    protected List<MetadataCol> getMetadataCols(Connection conn, String dbName, String tbName) throws SQLException {
        List<MetadataCol> metadataCols = null;
        // 方式一：通过JDBCAPI方式获取字段信息
        // 通过JDBC的API接口获取，可以拿到字段名、类型、长度、注释、主键、索引、分区等信息
        List<Column> columns = ClientFactory.createMetaClient(AcquireType.JDBCAPI, DBType.HIVE, conn)
                .getColumns(dbName, tbName);
//        // 方式二：通过JDBCINFO方式获取字段信息
//        // 通过select * from dbName.tbName获取，只能拿到字段名、类型、长度等信息
//        List<Column> columns = ClientFactory.createMetaClient(AcquireType.JDBCINFO, DBType.HIVE, conn)
//                .getColumns(dbName, tbName);
//        // 方式三：通过JDBCAPI方式获取字段信息
//        // 查询元数据表，可以获取最为详细的字段信息
//        List<Column> columns = ClientFactory.createMetaClient(AcquireType.JDBCSQL, DBType.HIVE, conn)
//                .getColumns(dbName, tbName);
        metadataCols = new ArrayList<>();
        MetadataCol mdCol = null;
        for (Column col : columns) {
            mdCol = new MetadataCol();
            mdCol.setSeq((short)col.getSeq());
            mdCol.setName(col.getName());
            mdCol.setDescribe(col.getComment());
            mdCol.setType(getColType(col.getType()));
            mdCol.setLength(col.getLength());
            mdCol.setPrimary(col.getPrimaryKeyN() == 1 ? true : false);
            metadataCols.add(mdCol);
        }
        return metadataCols;
    }

    public static DataType getColType(String type){
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type){
            case "VARCHAR":
                dataType = DataType.VARCHAR;
                break;
            case "STRING":
                dataType = DataType.STRING;
                break;
            case "DECIMAL":
                dataType = DataType.DECIMAL;
                break;
            case "CHAR":
                dataType = DataType.CHAR;
                break;
            case "FLOAT":
                dataType = DataType.FLOAT;
                break;
            case "DOUBLE":
                dataType = DataType.DOUBLE;
                break;
            case "TIMESTAMP":
            case "DATE":
                dataType = DataType.TIMESTAMP;
                break;
            case "INT":
                dataType = DataType.INT;
                break;
            case "BIGINT":
                dataType = DataType.BIGINT;
                break;
            case "TINYINT":
                dataType = DataType.TINYINT;
                break;
            case "SMALLINT":
                dataType = DataType.SMALLINT;
                break;
            default:
                dataType = null;
        }
        return dataType;
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = ImUtil.convertToTableColumnList(metadata.getMetadataCols());
        List<TableColumn> partitions = new ArrayList<TableColumn>();
        TableColumn tableColumn = new TableColumn("date","DATE","分区字段"); //todo hive分区字段
        partitions.add(tableColumn);
        boolean isExternal = false;
        boolean ifNotExists = false;
        RowFormat rowFormat = null;
        String fileFormat = FileFormat.HIVE_FILE_FORMAT_PARQUET;
        String sql = HiveSqlUtil.createTable(isExternal, ifNotExists, fullTbName,
                columns, tableComment, partitions, rowFormat, fileFormat);
        int status = getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 0 ? true : false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        boolean ifExists = false;
        String sql = HiveSqlUtil.dropTable(ifExists, fullTbName);
        int status = getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 0 ? true : false;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public String inputSQL() {
        return null;
    }

    @Override
    public boolean createEngineSchema(Model model) throws Exception {
        boolean status = false;
        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        // 作为源
        if (DatasourceType.HIVE.getValue().equals(sDsType)) {
            // TODO ...

        }
        Datasource tDs = model.getTargetMetadata().getDatasource();
        String tDsType = tDs.getType();
        // 作为目标
        if (DatasourceType.HIVE.getValue().equals(tDsType)) {
            // TODO ...
        }
        return status;
    }

    @Override
    public boolean dropEngineSchema(Model model) throws Exception {
        boolean status = false;
        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        // 作为源
        if (DatasourceType.HIVE.getValue().equals(sDsType)) {
            // TODO ...
        }
        Datasource tDs = model.getTargetMetadata().getDatasource();
        String tDsType = tDs.getType();
        // 作为目标
        if (DatasourceType.HIVE.getValue().equals(tDsType)) {
            // TODO ...
        }
        return status;
    }
}
