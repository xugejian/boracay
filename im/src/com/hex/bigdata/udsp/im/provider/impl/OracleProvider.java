package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.metadata.db.ClientFactory;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.constant.UpdateMode;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.OracleDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.KafkaModel;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.KafkaUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.OracleSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.JdbcWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.util.ImUtil;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
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
@Component("com.hex.bigdata.udsp.im.provider.impl.OracleProvider")
public class OracleProvider extends JdbcWrapper implements RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(OracleProvider.class);

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String tableComment = metadata.getDescribe();
        List<TableColumn> columns = ImUtil.convertToTableColumnList(metadata.getMetadataCols());
        List<String> sqls = new ArrayList<>();
        sqls.add(OracleSqlUtil.createTable(fullTbName, columns));
        sqls.add(OracleSqlUtil.commentTable(fullTbName, tableComment));
        sqls.addAll(OracleSqlUtil.createColComment(fullTbName, columns));
        sqls.add(OracleSqlUtil.createPrimaryKey(fullTbName, columns));
        return JdbcUtil.executeUpdate(oracleDatasource, sqls) == 0 ? true : false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String sql = OracleSqlUtil.dropTable(fullTbName);
        return JdbcUtil.executeUpdate(oracleDatasource, sql) == 0 ? true : false;
    }

    @Override
    public void inputData(Model model) {
        String sDsType = model.getSourceDatasource().getType();
        UpdateMode updateMode = model.getUpdateMode();
        // 源是Kafka
        if (DatasourceType.KAFKA.getValue().equals(sDsType)) {
            KafkaModel kafkaModel = new KafkaModel(model);
            List<KafkaStream<byte[], byte[]>> streams = KafkaUtil.outputData(kafkaModel);
            for (KafkaStream<byte[], byte[]> stream : streams) {
                ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
                while (iterator.hasNext()) {
                    String message = new String(iterator.next().message());
                    logger.debug("kafka接收的信息为：" + message);
                    // TODO ... 实时数据处理
                    if (UpdateMode.MATCHING_UPDATE == updateMode) { // 匹配更新

                    } else if (UpdateMode.UPDATE_INSERT == updateMode) { // 更新插入

                    } else { // 增量插入

                    }
                }
            }
        }
    }

    @Override
    protected DataType getColType(String type) {
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type) {
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

    @Override
    protected List<Column> getColumns(Connection conn, String dbName, String tbName) throws SQLException {
        // 方式一：通过JDBCAPI方式获取字段信息
        // 通过JDBC的API接口获取，可以拿到字段名、类型、长度、注释、主键、索引、分区等信息
        return ClientFactory.createMetaClient(AcquireType.JDBCAPI, DBType.ORACLE, conn)
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
