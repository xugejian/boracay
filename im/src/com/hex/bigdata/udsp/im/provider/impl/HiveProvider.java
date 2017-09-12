package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.metadata.db.ClientFactory;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcProviderUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.FileFormat;
import com.hex.bigdata.udsp.im.provider.impl.util.model.RowFormat;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.util.ImUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.HiveProvider")
public class HiveProvider extends JdbcWrapper {
    private static Logger logger = LogManager.getLogger(HiveProvider.class);

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = ImUtil.convertToTableColumnList(metadata.getMetadataCols());
        List<TableColumn> partitions = new ArrayList<TableColumn>();
        TableColumn tableColumn = new TableColumn("date", "DATE", "分区字段"); //todo hive分区字段
        partitions.add(tableColumn);
        boolean isExternal = false;
        boolean ifNotExists = false;
        RowFormat rowFormat = null;
        String fileFormat = FileFormat.HIVE_FILE_FORMAT_PARQUET;
        String sql = HiveSqlUtil.createTable(isExternal, ifNotExists, fullTbName,
                columns, tableComment, partitions, rowFormat, fileFormat);
        //  int status = getExecuteUpdateStatus(hiveDatasource, sql);
        //   return status == 0 ? true : false;
        return JdbcProviderUtil.executeUpdate(hiveDatasource, sql) == 1 ? true : false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        boolean ifExists = false;
        String sql = HiveSqlUtil.dropTable(ifExists, fullTbName);
        // int status = getExecuteUpdateStatus(hiveDatasource, sql);
        // return status == 0 ? true : false;
        return JdbcProviderUtil.executeUpdate(hiveDatasource, sql) == 1 ? true : false;
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
    protected DataType getColType(String type) {
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type) {
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
    protected List<Column> getColumns(Connection conn, String dbName, String tbName) throws SQLException {
        // 方式一：通过JDBCAPI方式获取字段信息
        // 通过JDBC的API接口获取，可以拿到字段名、类型、长度、注释、主键、索引、分区等信息
        return ClientFactory.createMetaClient(AcquireType.JDBCAPI, DBType.HIVE, conn)
                .getColumns(dbName, tbName);
//        // 方式二：通过JDBCINFO方式获取字段信息
//        // 通过select * from dbName.tbName获取，只能拿到字段名、类型、长度等信息
//        return ClientFactory.createMetaClient(AcquireType.JDBCINFO, DBType.HIVE, conn)
//                .getColumns(dbName, tbName);
//        // 方式三：通过JDBCAPI方式获取字段信息
//        // 查询元数据表，可以获取最为详细的字段信息
//        return ClientFactory.createMetaClient(AcquireType.JDBCSQL, DBType.HIVE, conn)
//                .getColumns(dbName, tbName);
    }
}
