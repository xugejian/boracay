package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.metadata.db.util.JdbcUtil;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.constant.DatasourceType;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HiveMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.JdbcMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.JdbcModel;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MysqlModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcProviderUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class JdbcWrapper extends Wrapper implements BatchSourceProvider, BatchTargetProvider {
    private static Logger logger = LogManager.getLogger(JdbcWrapper.class);
    private static Map<String, BasicDataSource> dataSourcePool;

    protected static final String HIVE_ENGINE_STORAGE_HANDLER_CLASS = "com.hex.hive.jdbc.JdbcStorageHandler";

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getSourceDatasource();
        JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource.getPropertyMap());
        MysqlModel mysqlModel = new MysqlModel(model.getPropertyMap());
        return getColumnInfo(jdbcDatasource, mysqlModel);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String[] strs = fullTbName.split(DATABASE_AND_TABLE_SEP);
        String dbName = null;
        String tbName = null;
        if (strs.length == 1) {
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
        } else if (strs.length == 2) {
            dbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[1];
        }
        return getMetadataCols(jdbcDatasource, dbName, tbName);
    }

    private List<MetadataCol> getMetadataCols(JdbcDatasource datasource, String dbName, String tbName) {
        List<MetadataCol> metadataCols = null;
        Connection conn = null;
        try {
            conn = JdbcProviderUtil.getConnection(datasource);
            metadataCols = getMetadataCols(conn, dbName, tbName);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        } finally {
            JdbcUtil.close(conn);
        }
        return metadataCols;
    }

    private List<MetadataCol> getColumnInfo(JdbcDatasource datasource, JdbcModel model) {
        String dbName = model.getDatabaseName();
        String tbName = model.getTableName();
        String sql = model.getSql();
        if (StringUtils.isNotBlank(sql)) {
            sql = "SELECT * FROM (" + sql + ") UDSP_VIEW WHERE 1=0";
            return getMetadataCols(datasource, sql);
        }
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            return getMetadataCols(datasource, dbName, tbName);
        }
        return null;
    }

    protected List<MetadataCol> getMetadataCols(JdbcDatasource datasource, String querySql) {
        List<MetadataCol> metadataCols = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcProviderUtil.getConnection(datasource);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            metadataCols = new ArrayList<>();
            if (columnCount >= 1) {
                for (int i = 1; i <= columnCount; i++) {
                    metadataCols.add(getMetadataCols(md, i));
                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
            JdbcUtil.close(conn);
        }
        return metadataCols;
    }

    protected boolean createEngineSchema(Model model, DatasourceType dsType) throws Exception {
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
        if (dsType.getValue().equals(sDsType)) {
            JdbcModel jdbcModel = new JdbcModel(model.getPropertyMap());
            String fullTbName = jdbcModel.getDatabaseName() + DATABASE_AND_TABLE_SEP + jdbcModel.getTableName();
            String tableName = getSourceTableName(jdbcModel.getDatabaseName(), jdbcModel.getTableName(), id);
            JdbcDatasource jdbcDs = new JdbcDatasource(sDs.getPropertyMap());
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getSourceColumns(model.getModelMappings()), "源的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(jdbcDs, fullTbName));
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
            if (!status) return status;
        }
        // 作为目标
        if (dsType.getValue().equals(tDsType)) {
            HiveMetadata hiveMetadata = new HiveMetadata(md.getPropertyMap());
            String fullTbName = hiveMetadata.getTbName();
            String tableName = getTargetTableName(fullTbName, id);
            JdbcDatasource jdbcDs = new JdbcDatasource(tDs.getPropertyMap());
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getTargetColumns(model.getModelMappings()), "目标的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(jdbcDs, fullTbName));
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
        }
        return status;
    }

    protected boolean dropEngineSchema(Model model, DatasourceType dsType) throws Exception {
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
        if (dsType.getValue().equals(sDsType)) {
            JdbcModel jdbcModel = new JdbcModel(model.getPropertyMap());
            String tableName = getSourceTableName(jdbcModel.getDatabaseName(), jdbcModel.getTableName(), id);
            String sql = HiveSqlUtil.dropTable(true, tableName);
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
            if (!status) return status;
        }
        // 作为目标
        if (dsType.getValue().equals(tDsType)) {
            String tableName = getTargetTableName(md.getTbName(), id);
            String sql = HiveSqlUtil.dropTable(true, tableName);
            status = JdbcProviderUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
        }
        return status;
    }

    private List<TableColumn> getTargetColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            String dataType = getDataType(metadataCol.getType(), metadataCol.getLength());
            columns.add(new TableColumn(metadataCol.getName(), dataType, metadataCol.getDescribe()));
        }
        return columns;
    }

    private List<TableColumn> getSourceColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            String dataType = getDataType(mapping.getType(), mapping.getLength());
            columns.add(new TableColumn(mapping.getName(), dataType, mapping.getDescribe()));
        }
        return columns;
    }

    private String getDataType(DataType type, String length) {
        String dataType = DataType.STRING.getValue();
        if (StringUtils.isBlank(length)) {
            dataType = type.getValue();
        } else {
            if (DataType.STRING == type || DataType.INT == type || DataType.SMALLINT == type
                    || DataType.BIGINT == type || DataType.BOOLEAN == type || DataType.DOUBLE == type
                    || DataType.FLOAT == type || DataType.TINYINT == type || DataType.TIMESTAMP == type) {
                dataType = type.getValue();
            } else if (DataType.CHAR == type || DataType.VARCHAR == type || DataType.DECIMAL == type) {
                dataType = type.getValue() + "(" + length + ")";
            }
        }
        return dataType;
    }

    private List<TblProperty> getTblProperties(JdbcDatasource datasource, String tableName) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("mapred.jdbc.driver.class", datasource.getDriverClass()));
        tblProperties.add(new TblProperty("mapred.jdbc.url", datasource.getJdbcUrl()));
        if (StringUtils.isNotBlank(datasource.getUsername()))
            tblProperties.add(new TblProperty("mapred.jdbc.username", datasource.getUsername()));
        if (StringUtils.isNotBlank(datasource.getPassword()))
            tblProperties.add(new TblProperty("mapred.jdbc.password", datasource.getPassword()));
        tblProperties.add(new TblProperty("mapred.jdbc.input.table.name", tableName));
        tblProperties.add(new TblProperty("mapred.jdbc.output.table.name", tableName));
        tblProperties.add(new TblProperty("mapred.jdbc.hive.lazy.split", "false"));
        return tblProperties;
    }

    private String getSourceTableName(String dbName, String tbName, String id) {
        String tableName = HIVE_ENGINE_SOURCE_TABLE_PREFIX + id;
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_SOURCE_TABLE_PREFIX + dbName + HIVE_ENGINE_TABLE_SEP + tbName + HIVE_ENGINE_TABLE_SEP + id;
        } else if (StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_SOURCE_TABLE_PREFIX + tbName + HIVE_ENGINE_TABLE_SEP + id;
        }
        return tableName;
    }

    private String getTargetTableName(String fullTbName, String id) {
        String[] strs = fullTbName.split(DATABASE_AND_TABLE_SEP);
        String dbName = null;
        String tbName = null;
        if (strs.length == 1) {
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
        } else if (strs.length == 2) {
            dbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[1];
        }
        String tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + id;
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + dbName + HIVE_ENGINE_TABLE_SEP + tbName + HIVE_ENGINE_TABLE_SEP + id;
        } else if (StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + tbName + HIVE_ENGINE_TABLE_SEP + id;
        }
        return tableName;
    }

    protected abstract MetadataCol getMetadataCols(ResultSetMetaData md, int i) throws SQLException;

    protected abstract List<MetadataCol> getMetadataCols(Connection conn, String dbName, String tbName) throws SQLException;
}
