package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.MysqlDatasource;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.util.MysqlSqlUtil;
import com.hex.bigdata.udsp.im.provider.util.model.TableColumn;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.MysqlProvider")
public class MysqlProvider implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(MysqlProvider.class);

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getDatasource();
        MysqlDatasource mysqlDatasource = new MysqlDatasource(datasource.getPropertyMap());
        HiveModel hiveModel = new HiveModel(datasource.getPropertyMap());
        String sql = JdbcUtil.getCloumnInfoSql(hiveModel);
        if (sql == null) return null;
        return JdbcUtil.getColumns(mysqlDatasource, sql);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        MysqlDatasource mysqlDatasource = new MysqlDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String sql = "SELECT * FROM " + fullTbName + " WHERE 1=0";
        return JdbcUtil.getColumns(mysqlDatasource, sql);
    }

    @Override
    public boolean create(Metadata metadata) throws SQLException {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = null;
        boolean ifNotExists = false;
        String sql = MysqlSqlUtil.createTable(ifNotExists, fullTbName, columns, tableComment);
        int status = JdbcUtil.getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 1 ? true : false;
    }

    @Override
    public boolean drop(Metadata metadata) throws SQLException {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        boolean ifExists = false;
        String sql = MysqlSqlUtil.dropTable(ifExists, fullTbName);
        int status = JdbcUtil.getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 1 ? true : false;
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
