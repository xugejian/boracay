package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.JdbcWrapper;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.MysqlDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MysqlModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.util.MysqlSqlUtil;
import com.hex.bigdata.udsp.im.provider.util.model.TableColumn;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.MysqlProvider")
public class MysqlProvider extends JdbcWrapper implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(MysqlProvider.class);

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getDatasource();
        MysqlDatasource mysqlDatasource = new MysqlDatasource(datasource.getPropertyMap());
        MysqlModel mysqlModel = new MysqlModel(model.getPropertyMap());
        return getColumnInfo(mysqlDatasource, mysqlModel);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        MysqlDatasource mysqlDatasource = new MysqlDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        return getMetadataColsByTbName(mysqlDatasource, fullTbName);
    }

    @Override
    protected MetadataCol getMetadataColBySql(ResultSetMetaData md, int i) throws SQLException {
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
    protected List<MetadataCol> getMetadataColsByTbName(ResultSet rs) throws SQLException {
        List<MetadataCol> metadataCols = new ArrayList<>();
        short i = 0;
        while (rs.next()){
            MetadataCol metadataCol = new MetadataCol();
            metadataCol.setSeq(i++);
            metadataCol.setName(rs.getString("Field"));
            String type =rs.getString("Type");
            metadataCol.setType(getColType(type.split("\\(")[0]));
            metadataCol.setLength(getColLength(type));
            metadataCol.setDescribe(rs.getString("Comment"));
            if("PRI".equals(rs.getString("Key"))){
                metadataCol.setPrimary(true);
            }else{
                metadataCol.setPrimary(false);
            }
            metadataCol.setStored(true);
            metadataCol.setIndexed(false);
            metadataCols.add(metadataCol);
        }
        return metadataCols;
    }

    public static String getColLength(String type){
        if(type.indexOf("(") == -1){
            return null;
        }else{
            return type.substring(type.indexOf("(")+1, type.indexOf(")"));
        }
    }

    public static DataType getColType(String type){
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type){
            case "VARCHAR":
                dataType = DataType.VARCHAR;
                break;
            case "BLOB":
            case "TEXT":
                dataType = DataType.STRING;
                break;
            case "DECIMAL":
                dataType = DataType.DECIMAL;
                break;
            case "CHAR":
                dataType = DataType.CHAR;
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
            case "DOUBLE":
                dataType = DataType.DOUBLE;
                break;
            case "TIMESTAMP":
                dataType = DataType.TIMESTAMP;
                break;
            default:
                dataType = null;
        }
        return dataType;
    }

    @Override
    protected String getSqlByTbName(String fullTbName) {
        return "show full columns from "+ fullTbName;
    }

    @Override
    public boolean createTable(Metadata metadata) throws SQLException {
        Datasource datasource = metadata.getDatasource();
        MysqlDatasource mysqlDatasource = new MysqlDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = null;
        boolean ifNotExists = false;
        String sql = MysqlSqlUtil.createTable(ifNotExists, fullTbName, columns, tableComment);
        int status = getExecuteUpdateStatus(mysqlDatasource, sql);
        return status == 1 ? true : false;
    }

    @Override
    public boolean createHiveTable(Metadata metadata) {
        // TODO ...
        return false;
    }

    @Override
    public boolean dropTable(Metadata metadata) throws SQLException {
        Datasource datasource = metadata.getDatasource();
        MysqlDatasource mysqlDatasource = new MysqlDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        boolean ifExists = false;
        String sql = MysqlSqlUtil.dropTable(ifExists, fullTbName);
        int status = getExecuteUpdateStatus(mysqlDatasource, sql);
        return status == 1 ? true : false;
    }

    @Override
    public boolean dropHiveTable(Metadata metadata) {
        // TODO ...
        return false;
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
