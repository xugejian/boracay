package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.constant.UpdateMode;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrHBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.KafkaModel;
import com.hex.bigdata.udsp.im.provider.impl.util.KafkaUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrHBaseWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrHBaseProvider")
public class SolrHBaseProvider extends SolrHBaseWrapper {
    private static Logger logger = LoggerFactory.getLogger(SolrHBaseProvider.class);

    @Autowired
    private SolrProvider solrProvider;
    @Autowired
    private HBaseProvider hbaseProvider;

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        String collectionName = metadata.getTbName();
        Datasource datasource = metadata.getDatasource();
        String solrServers = datasource.getPropertyMap().get("solr.servers").getValue();
        return solrProvider.getColumns(collectionName, solrServers);
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        return hbaseProvider.createSchema(metadata) && solrProvider.createSchema(metadata);
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        return solrProvider.dropSchema(metadata) && hbaseProvider.dropSchema(metadata);
    }

    @Override
    public boolean checkSchemaExists(Metadata metadata) throws Exception {
        return solrProvider.checkSchemaExists(metadata) && hbaseProvider.checkSchemaExists(metadata);
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws Exception {
        String id = model.getId();
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        return hbaseProvider.createTargetEngineSchema(hBaseModel) && solrProvider.createTargetEngineSchema(solrModel);
    }

    @Override
    public boolean dropTargetEngineSchema(Model model) throws Exception {
        String id = model.getId();
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        return hbaseProvider.dropTargetEngineSchema(solrModel) && solrProvider.dropTargetEngineSchema(hBaseModel);
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
    public void inputSQL(Model model) throws SQLException {
        String id = model.getId();
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        hbaseProvider.inputSQL(hBaseModel);
        solrProvider.inputSQL(solrModel);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        return hbaseProvider.testDatasource(datasource) && solrProvider.testDatasource(datasource);
    }
}
