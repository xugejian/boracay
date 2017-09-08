package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.util.model.FileFormat;
import com.hex.bigdata.udsp.im.provider.util.model.RowFormat;
import com.hex.bigdata.udsp.im.provider.util.model.TableColumn;
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
@Component("com.hex.bigdata.udsp.im.provider.impl.HiveProvider")
public class HiveProvider extends JdbcWrapper implements BatchSourceProvider, BatchTargetProvider {
    private static Logger logger = LogManager.getLogger(HiveProvider.class);

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getSourceDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        HiveModel hiveModel = new HiveModel(model.getPropertyMap());
        return getColumnInfo(hiveDatasource, hiveModel);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        return getMetadataColsByTbName(hiveDatasource, fullTbName);
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
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
        int status = getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 1 ? true : false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        boolean ifExists = false;
        String sql = HiveSqlUtil.dropTable(ifExists, fullTbName);
        int status = getExecuteUpdateStatus(hiveDatasource, sql);
        return status == 1 ? true : false;
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
        short i = 0;
        List<MetadataCol> metadataCols = new ArrayList<>();
        while (rs.next()){
            MetadataCol metadataCol = new MetadataCol();
            metadataCol.setSeq(i++);
            metadataCol.setName(rs.getString("col_name"));
            String type =rs.getString("data_type");
            metadataCol.setType(getColType(type.split("\\(")[0]));
//            metadataCol.setLength();
            metadataCol.setDescribe(rs.getString("comment"));
            metadataCols.add(metadataCol);
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
    protected String getSqlByTbName(String fullTbName) {
//        "SELECT * FROM " + fullTbName + " WHERE 1=0";
        return "describe " + fullTbName;
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
    public boolean createEngineSchema(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public boolean dropEngineSchema(Metadata metadata) throws Exception {
        return false;
    }
}
