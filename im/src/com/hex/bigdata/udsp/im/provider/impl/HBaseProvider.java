package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.constant.UpdateMode;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.KafkaModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.KafkaUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.HBaseWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.HBaseProvider")
public class HBaseProvider extends HBaseWrapper implements RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(HBaseProvider.class);

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        // HBase无法获取字段信息，返回null
        return null;
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        HBaseMetadata hBaseMetadata = new HBaseMetadata(metadata);
        return createHTable(hBaseMetadata);
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        HBaseDatasource hBaseDatasource = new HBaseDatasource(datasource.getPropertyMap());
        return dropHTable(hBaseDatasource, metadata.getTbName());
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws Exception {
        Metadata metadata = model.getTargetMetadata();
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        String id = model.getId();
        HBaseMetadata hbaseMetadata = new HBaseMetadata(metadata);
        String fullTbName = hbaseMetadata.getTbName();
        String tableName = getTargetTableName(id);
        List<ModelMapping> modelMappings = model.getModelMappings();
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                getTargetColumns(modelMappings, hbaseMetadata), "目标的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, getSerDeProperties(modelMappings, hbaseMetadata),
                getTblProperties(fullTbName));
        return JdbcUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
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
    public boolean checkSchemaExists(Metadata metadata) throws Exception {
        HBaseDatasource datasource = new HBaseDatasource(metadata.getDatasource().getPropertyMap());
        HBaseAdmin admin = getHBaseAdmin(datasource);
        String tableName = metadata.getTbName();
        TableName hbaseTableName = TableName.valueOf(tableName);
        return admin.isTableAvailable(hbaseTableName);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        HBaseDatasource hBaseDatasource = new HBaseDatasource(datasource.getProperties());
        HConnection conn = null;
        try {
            conn = getConnection(hBaseDatasource);
            if (conn != null && !conn.isAborted()) {
                canConnection = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(hBaseDatasource, conn);
        }
        return canConnection;
    }
}
