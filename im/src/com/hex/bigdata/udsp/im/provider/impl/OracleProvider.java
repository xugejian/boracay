package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.metadata.db.ClientFactory;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.constant.DatasourceType;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HiveMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.OracleDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.OracleModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.impl.util.OracleSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.OracleProvider")
public class OracleProvider extends JdbcWrapper implements RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(OracleProvider.class);

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = null;
        String sql = OracleSqlUtil.createTable(fullTbName, columns, tableComment);
        return executeUpdate(oracleDatasource, sql) == 1 ? true : false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String sql = OracleSqlUtil.dropTable(fullTbName);
        return executeUpdate(oracleDatasource, sql) == 1 ? true : false;
    }

    @Override
    public String inputSQL() {
        return null;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }

    @Override
    public boolean createEngineSchema(Model model) throws Exception {
        boolean status = false;
        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        Metadata md = model.getTargetMetadata();
        Datasource tDs = md.getDatasource();
        String tDsType = tDs.getType();
        Datasource eDs = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(eDs.getPropertyMap());
        String id = model.getId();
        // 作为源
        if (DatasourceType.HIVE.getValue().equals(sDsType)) {
            HiveModel hiveModel = new HiveModel(model.getPropertyMap());
            String fullTbName = hiveModel.getDatabaseName() + DATABASE_AND_TABLE_SEP + hiveModel.getTableName();
            String tableName = getSourceTableName(hiveModel.getDatabaseName(), hiveModel.getTableName(), id);
            List<ModelMapping> modelMappings = model.getModelMappings();
            List<TableColumn> columns = new ArrayList<>();
            for (ModelMapping mapping : modelMappings) {
                String dataType = ""; // TODO ...  mapping.getType()、 mapping.getLength()
                columns.add(new TableColumn(mapping.getName(), dataType, mapping.getDescribe()));
            }
            List<TblProperty> tblProperties = new ArrayList<>();
            HiveDatasource hiveDs = new HiveDatasource(sDs.getPropertyMap());
            tblProperties.add(new TblProperty("mapred.jdbc.driver.class", hiveDs.getDriverClass()));
            tblProperties.add(new TblProperty("mapred.jdbc.url", hiveDs.getJdbcUrl()));
            if (StringUtils.isNotBlank(hiveDs.getUsername()))
                tblProperties.add(new TblProperty("mapred.jdbc.username", hiveDs.getUsername()));
            if (StringUtils.isNotBlank(hiveDs.getPassword()))
                tblProperties.add(new TblProperty("mapred.jdbc.password", hiveDs.getPassword()));
            tblProperties.add(new TblProperty("mapred.jdbc.input.table.name", fullTbName));
            tblProperties.add(new TblProperty("mapred.jdbc.output.table.name", fullTbName));
            tblProperties.add(new TblProperty("mapred.jdbc.hive.lazy.split", "false"));
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    columns, "源的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, tblProperties);
            status = executeUpdate(eHiveDs, sql) >= 0 ? true : false;
            if (!status) return status;
        }
        // 作为目标
        if (DatasourceType.HIVE.getValue().equals(tDsType)) {
            HiveMetadata hiveMd = new HiveMetadata(md.getPropertyMap());
            String fullTbName = hiveMd.getTbName();
            String tableName = getTargetTableName(fullTbName, id);
            List<ModelMapping> modelMappings = model.getModelMappings();
            List<TableColumn> columns = new ArrayList<>();
            for (ModelMapping mapping : modelMappings) {
                MetadataCol metadataCol = mapping.getMetadataCol();
                String dataType = ""; // TODO ...  metadataCol.getType()、 metadataCol.getLength()
                columns.add(new TableColumn(metadataCol.getName(), dataType, metadataCol.getDescribe()));
            }
            List<TblProperty> tblProperties = new ArrayList<>();
            HiveDatasource hiveDs = new HiveDatasource(tDs.getPropertyMap());
            tblProperties.add(new TblProperty("mapred.jdbc.driver.class", hiveDs.getDriverClass()));
            tblProperties.add(new TblProperty("mapred.jdbc.url", hiveDs.getJdbcUrl()));
            if (StringUtils.isNotBlank(hiveDs.getUsername()))
                tblProperties.add(new TblProperty("mapred.jdbc.username", hiveDs.getUsername()));
            if (StringUtils.isNotBlank(hiveDs.getPassword()))
                tblProperties.add(new TblProperty("mapred.jdbc.password", hiveDs.getPassword()));
            tblProperties.add(new TblProperty("mapred.jdbc.input.table.name", fullTbName));
            tblProperties.add(new TblProperty("mapred.jdbc.output.table.name", fullTbName));
            tblProperties.add(new TblProperty("mapred.jdbc.hive.lazy.split", "false"));
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    columns, "目标的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, tblProperties);
            status = executeUpdate(eHiveDs, sql) >= 0 ? true : false;
        }
        return status;
    }

    @Override
    public boolean dropEngineSchema(Model model) throws Exception {
        boolean status = false;
        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        Metadata md = model.getTargetMetadata();
        Datasource tDs = md.getDatasource();
        String tDsType = tDs.getType();
        Datasource eDs = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(eDs.getPropertyMap());
        String id = model.getId();
        // 作为源
        if (DatasourceType.HIVE.getValue().equals(sDsType)) {
            HiveModel hiveModel = new HiveModel(model.getPropertyMap());
            String tableName = getSourceTableName(hiveModel.getDatabaseName(), hiveModel.getTableName(), id);
            String sql = HiveSqlUtil.dropTable(true, tableName);
            status = executeUpdate(eHiveDs, sql) >= 0 ? true : false;
            if (!status) return status;
        }
        // 作为目标
        if (DatasourceType.HIVE.getValue().equals(tDsType)) {
            HiveMetadata hiveMd = new HiveMetadata(md.getPropertyMap());
            String tableName = getTargetTableName(hiveMd.getTbName(), id);
            String sql = HiveSqlUtil.dropTable(true, tableName);
            status = executeUpdate(eHiveDs, sql) >= 0 ? true : false;
        }
        return status;
    }

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
            // TODO ...

            metadataCols.add(mdCol);
        }
        return metadataCols;
    }
}
