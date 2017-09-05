package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.HiveModel;
import com.hex.bigdata.udsp.im.provider.impl.model.OracleDatasource;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class OracleProvider implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(OracleProvider.class);
    private static Map<String, BasicDataSource> dataSourcePool;

    private synchronized BasicDataSource getDataSource(OracleDatasource datasource) {
        String dsId = datasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, BasicDataSource>();
        }
        BasicDataSource dataSource = dataSourcePool.get(dsId);
        if (dataSource == null) {
            dataSource = new BasicDataSource();

            //Class.forName(datasource.getDriverClass());
            if (StringUtils.isNotBlank(datasource.getDriverClass()))
                dataSource.setDriverClassName(datasource.getDriverClass());
            if (StringUtils.isNotBlank(datasource.getJdbcUrl()))
                dataSource.setUrl(datasource.getJdbcUrl());
            if (StringUtils.isNotBlank(datasource.getUsername()))
                dataSource.setUsername(datasource.getUsername());
            if (StringUtils.isNotBlank(datasource.getPassword()))
                dataSource.setPassword(datasource.getPassword());
            if (StringUtils.isNotBlank(datasource.getInitialSize()))
                dataSource.setInitialSize(Integer.valueOf(datasource.getInitialSize()));// 数据库初始化时，创建的连接个数
            if (StringUtils.isNotBlank(datasource.getMinIdle()))
                dataSource.setMinIdle(Integer.valueOf(datasource.getMinIdle()));// 最小空闲连接数
            if (StringUtils.isNotBlank(datasource.getMaxIdle()))
                dataSource.setMaxIdle(Integer.valueOf(datasource.getMaxIdle()));// 数据库最大连接数
            if (StringUtils.isNotBlank(datasource.getMaxActive()))
                dataSource.setMaxActive(Integer.valueOf(datasource.getMaxActive()));// 设置最大并发数
            if (StringUtils.isNotBlank(datasource.getMaxWait()))
                dataSource.setMaxWait(Integer.valueOf(datasource.getMaxWait()));// 最长等待时间，单位毫秒
            if (StringUtils.isNotBlank(datasource.getValidationQuery()))
                dataSource.setValidationQuery(datasource.getValidationQuery()); // 验证链接的SQL语句，必须能返回一行及以上数据
            if (StringUtils.isNotBlank(datasource.getValidationQueryTimeout()))
                dataSource.setValidationQueryTimeout(Integer.valueOf(datasource.getValidationQueryTimeout())); // 自动验证连接的时间
            if (StringUtils.isNotBlank(datasource.getTimeBetweenEvictionRunsMillis()))
                dataSource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(datasource.getTimeBetweenEvictionRunsMillis())); // N毫秒检测一次是否有死掉的线程
            if (StringUtils.isNotBlank(datasource.getMinEvictableIdleTimeMillis()))
                dataSource.setMinEvictableIdleTimeMillis(Integer.valueOf(datasource.getMinEvictableIdleTimeMillis()));// 空闲连接N毫秒中后释放
            if (StringUtils.isNotBlank(datasource.getTestWhileIdle()))
                dataSource.setTestWhileIdle(Boolean.valueOf(datasource.getTestWhileIdle()));
            if (StringUtils.isNotBlank(datasource.getTestOnBorrow()))
                dataSource.setTestOnBorrow(Boolean.valueOf(datasource.getTestOnBorrow()));
            if (StringUtils.isNotBlank(datasource.getTestOnReturn()))
                dataSource.setTestOnReturn(Boolean.valueOf(datasource.getTestOnReturn()));

            dataSourcePool.put(dsId, dataSource);
        }
        return dataSource;
    }

    private Connection getConnection(OracleDatasource datasource) throws SQLException {
        Connection conn = null;
        BasicDataSource dataSource = getDataSource(datasource);
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }

    private List<MetadataCol> getColumns(OracleDatasource datasource, String sql) {
        List<MetadataCol> metadataCols = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection(datasource);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            metadataCols = new ArrayList<>();
            if (columnCount >= 1) {
                for (int i = 1; i <= columnCount; i++) {
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

                    // TODO ......

                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return metadataCols;
    }

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        HiveModel hiveModel = new HiveModel(datasource.getPropertyMap());
        String dbName = hiveModel.getDatabaseName();
        String tbName = hiveModel.getTableName();
        String sql = hiveModel.getSql();
        if (StringUtils.isBlank(sql)) {
            if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName))
                sql = "SELECT * FROM " + dbName + "." + tbName + " WHERE 1=0";
            else
                return null;
        }
        return getColumns(oracleDatasource, sql);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String sql = "SELECT * FROM " + fullTbName + " WHERE 1=0";
        return getColumns(oracleDatasource, sql);
    }

    @Override
    public void create() {

    }

    @Override
    public void drop() {

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
}
