package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.JdbcWrapper;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.OracleDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.OracleModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.util.OracleSqlUtil;
import com.hex.bigdata.udsp.im.provider.util.model.TableColumn;
import com.hex.bigdata.udsp.im.util.ImUtil;
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
@Component("com.hex.bigdata.udsp.im.provider.impl.OracleProvider")
public class OracleProvider extends JdbcWrapper implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(OracleProvider.class);

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        OracleModel oracleModel = new OracleModel(model.getPropertyMap());
        return getColumnInfo(oracleDatasource, oracleModel);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        return getMetadataColsByTbName(oracleDatasource, fullTbName);
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
            metadataCol.setType(getColType(rs.getString("Type")));
            metadataCol.setLength(getColLength(rs.getString("Precision"),rs.getString("Scale") ,rs.getString("Length")));
            metadataCol.setDescribe(rs.getString("Remark"));
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

    public static DataType getColType(String type){
        DataType dataType = null;
        switch (type){
            case "VARCHAR":
            case "VARCHAR2":
            case "NVARCHAR2":
                dataType = DataType.VARCHAR;
                break;
            case "CLOB":
            case "LONG":
            case "BLOB":
                dataType = DataType.STRING;
                break;
            case "NUMBER":
                dataType = DataType.DECIMAL;
                break;
            case "CHAR":
            case "NCHAR":
                dataType = DataType.CHAR;
                break;
            case "BINARY_FLOAT":
                dataType = DataType.FLOAT;
                break;
            case "BINARY_DOUBLE":
                dataType = DataType.DOUBLE;
                break;
            case "TIMESTAMP":
            case "TIMESTAMP(6)":
            case "DATE":
                dataType = DataType.TIMESTAMP;
                break;
            default:
                dataType = null;
        }
        return dataType;
    }

    public static String getColLength(String precision, String scale, String length){
        if(StringUtils.isEmpty(precision)){
            return length;
        }else if(StringUtils.isNotEmpty(precision) && StringUtils.isNotEmpty(scale)){
            return precision+","+scale;
        }else{
            return precision;
        }
    }

    @Override
    protected String getSqlByTbName(String fullTbName) {
        return "SELECT COLS.TABLE_NAME   as tbName," +
                "       COLS.COLUMN_NAME  as Field," +
                "       COLS.DATA_TYPE    as Type," +
                "       COLS.DATA_PRECISION    as Precision," +
                "       COLS.DATA_SCALE    as Scale," +
                "       COLS.DATA_LENGTH  as Length," +
                "       decode(con.constraint_type,'P',1,0)     as Key," +
                "       COLS.COLUMN_ID    as seq," +
                "       com.comments as Remark" +
                "  FROM USER_TAB_COLS COLS" +
                "  inner join user_col_comments com" +
                "  on com.TABLE_NAME = COLS.TABLE_NAME" +
                "  and com.COLUMN_NAME = COLS.COLUMN_NAME" +
                "  left join (select col.table_name,col.column_name,con.constraint_type" +
                "  from user_constraints con,  user_cons_columns col " +
                "  where con.constraint_name = col.constraint_name " +
                "  and con.constraint_type='P') con" +
                "  on con.table_name = COLS.table_name" +
                "  and con.column_name = COLS.column_name" +
                "  where COLS.table_name = upper('"+ fullTbName+"')";
    }

    @Override
    public boolean createTable(Metadata metadata) throws SQLException {
        Datasource datasource = metadata.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = ImUtil.convertToTableColumnList(metadata.getMetadataCols());;
        String sql = OracleSqlUtil.createTable(fullTbName, columns, tableComment);
        logger.info(sql);
        int status = getExecuteUpdateStatus(oracleDatasource, sql);
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
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String sql = OracleSqlUtil.dropTable(fullTbName);
        int status = getExecuteUpdateStatus(hiveDatasource, sql);
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
