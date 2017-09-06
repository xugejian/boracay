package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.util.model.FileFormat;
import com.hex.bigdata.udsp.im.provider.util.model.RowFormat;
import com.hex.bigdata.udsp.im.provider.util.model.TableColumn;
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
public class HiveProvider implements BatchSourceProvider, BatchTargetProvider {
    private static Logger logger = LogManager.getLogger(HiveProvider.class);

    private List<MetadataCol> getColumns(HiveDatasource datasource, String sql) {
        List<MetadataCol> metadataCols = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection(datasource);
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
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return metadataCols;
    }

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        HiveModel hiveModel = new HiveModel(datasource.getPropertyMap());
        String sql = JdbcUtil.getCloumnInfoSql(hiveModel);
        if (sql == null) return null;
        return getColumns(hiveDatasource, sql);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String sql = "SELECT * FROM " + fullTbName + " WHERE 1=0";
        return getColumns(hiveDatasource, sql);
    }

    @Override
    public boolean create(Metadata metadata) throws SQLException {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = null;
        List<TableColumn> partitions = null;
        boolean isExternal = false;
        boolean ifNotExists = false;
        RowFormat rowFormat = null;
        String fileFormat = FileFormat.HIVE_FILE_FORMAT_PARQUET;
        String sql = HiveSqlUtil.createTable(isExternal, ifNotExists, fullTbName,
                columns, tableComment, partitions, rowFormat, fileFormat);
        int status = JdbcUtil.getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 1 ? true : false;
    }

    @Override
    public boolean drop(Metadata metadata) throws SQLException {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        boolean ifExists = false;
        String sql = HiveSqlUtil.dropTable(ifExists, fullTbName);
        int status = JdbcUtil.getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 1 ? true : false;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public String inputSQL() {
        return null;
    }

}
