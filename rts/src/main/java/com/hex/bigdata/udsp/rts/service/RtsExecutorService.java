package com.hex.bigdata.udsp.rts.service;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.DatasourceUtil;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.rts.executor.Executor;
import com.hex.bigdata.udsp.rts.executor.model.*;
import com.hex.bigdata.udsp.rts.model.RtsConsumer;
import com.hex.bigdata.udsp.rts.model.RtsMetadata;
import com.hex.bigdata.udsp.rts.model.RtsMetadataCol;
import com.hex.bigdata.udsp.rts.model.RtsProducer;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tomnic on 2017/3/7.
 */
@Service
public class RtsExecutorService extends BaseService {

    private static Logger logger = LogManager.getLogger(RtsExecutorService.class);

    private static final String RTS_IMPL_CLASS = "RTS_IMPL_CLASS";

    @Autowired
    private ComPropertiesService comPropertiesService;
    @Autowired
    private RtsMetadataService rtsMetadataService;
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private RtsMetadataColService rtsMetadataColService;
    @Autowired
    private RtsConsumerService rtsConsumerService;
    @Autowired
    private RtsProducerService rtsProducerService;
    @Autowired
    private GFDictMapper gfDictMapper;

    /**
     * 获取消费者应用信息
     *
     * @param consumerId
     * @param timeout
     * @return
     */
    public ConsumerResponse consumer(String consumerId, long timeout) {
        timeout = (timeout == 0 ? 1000 : timeout);
        RtsConsumer rtsConsumer = rtsConsumerService.select(consumerId);
        RtsMetadata rtsMetadata = rtsMetadataService.select(rtsConsumer.getMdId());
        List<RtsMetadataCol> rtsMetadataCols = rtsMetadataColService.selectByMdId(rtsMetadata.getPkId());
        ComDatasource comDatasource = comDatasourceService.select(rtsMetadata.getDsId());
        List<ComProperties> datasourceProperties = comPropertiesService.selectList(comDatasource.getPkId());
        List<ComProperties> applicationProperties = comPropertiesService.selectList(rtsConsumer.getPkId());
        datasourceProperties.addAll(applicationProperties);
        Datasource datasource = new Datasource(DatasourceUtil.getDatasource(comDatasource, datasourceProperties));
        Metadata metadata = getMetadata(datasource, rtsMetadata, rtsMetadataCols);
        Application application = getApplication(rtsConsumer, metadata);
        ConsumerRequest consumerRequest = new ConsumerRequest(application, timeout);
        ConsumerResponse consumerResponse = getExecutorImpl(datasource).pull(consumerRequest);
        consumerResponse.setColumns(putColumnIntoMap(rtsMetadataCols));
        if (consumerResponse.getRecords() != null && consumerResponse.getRecords().size() > 0) {
            consumerResponse.setStatus(Status.SUCCESS);
            consumerResponse.setStatusCode(StatusCode.SUCCESS);
        }
        return consumerResponse;
    }

    private LinkedHashMap<String, String> putColumnIntoMap(List<RtsMetadataCol> rtsMetadataCols) {
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        for (RtsMetadataCol rtsMetadataCol : rtsMetadataCols) {
            columnMap.put(rtsMetadataCol.getName(), rtsMetadataCol.getType());
        }
        return columnMap;
    }

    /**
     * 获取生产者应用信息
     *
     * @param producerId
     * @param messageDatas
     * @return
     */
    public ProducerResponse producer(String producerId, List<Map<String, String>> messageDatas) {
        RtsProducer rtsProducer = rtsProducerService.select(producerId);
        RtsMetadata rtsMetadata = rtsMetadataService.select(rtsProducer.getMdId());
        List<RtsMetadataCol> rtsMetadataCols = rtsMetadataColService.selectByMdId(rtsMetadata.getPkId());
        ComDatasource comDatasource = comDatasourceService.select(rtsMetadata.getDsId());
        List<ComProperties> datasourceProperties = comPropertiesService.selectList(comDatasource.getPkId());
        List<ComProperties> applicationProperties = comPropertiesService.selectList(rtsProducer.getPkId());
        datasourceProperties.addAll(applicationProperties);
        Datasource datasource = new Datasource(DatasourceUtil.getDatasource(comDatasource, datasourceProperties));
        Metadata metadata = getMetadata(datasource, rtsMetadata, rtsMetadataCols);
        Application producerApplication = getApplication(rtsProducer, metadata);
        ProducerRequest producerRequest = new ProducerRequest(producerApplication, messageDatas);
        return getExecutorImpl(datasource).push(producerRequest);
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(com.hex.bigdata.udsp.common.api.model.Datasource datasource) {
        return getExecutorImpl(datasource).testDatasource(datasource);
    }

    /**
     * 得到生产接口的实例
     *
     * @param datasource
     * @return
     */
    private Executor getExecutorImpl(com.hex.bigdata.udsp.common.api.model.Datasource datasource) {
        return (Executor) ObjectUtil.newInstance(getImplClass(datasource));
    }

    private String getImplClass(com.hex.bigdata.udsp.common.api.model.Datasource datasource) {
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey(RTS_IMPL_CLASS, datasource.getType());
            implClass = gfDict.getDictName();
        }
        return implClass;
    }

    private Application getApplication(RtsConsumer rtsConsumer, Metadata metadata) {
        Application application = new Application();
        application.setMetadata(metadata);
        application.setName(rtsConsumer.getName());
        application.setDescribe(rtsConsumer.getDescribe());
        application.setName(rtsConsumer.getNote());
        return application;
    }

    private Metadata getMetadata(Datasource datasource, RtsMetadata rtsMetadata, List<RtsMetadataCol> rtsMetadataCols) {
        Metadata metadata = new Metadata();
        metadata.setDatasource(datasource);
        metadata.setName(rtsMetadata.getName());
        metadata.setDescribe(rtsMetadata.getDescribe());
        metadata.setNote(rtsMetadata.getNote());
        metadata.setTopic(rtsMetadata.getTopic());
        metadata.setColumns(getColumns(rtsMetadataCols));
        return metadata;
    }

    private Application getApplication(RtsProducer rtsProducer, Metadata metadata) {
        Application producerApplication = new Application();
        producerApplication.setName(rtsProducer.getName());
        producerApplication.setDescribe(rtsProducer.getDescribe());
        producerApplication.setNote(rtsProducer.getNote());
        producerApplication.setMetadata(metadata);
        return producerApplication;
    }

    private List<MetadataCol> getColumns(List<RtsMetadataCol> rtsMetadataCols) {
        List<MetadataCol> columns = new ArrayList<>();
        for (RtsMetadataCol col : rtsMetadataCols) {
            MetadataCol column = new MetadataCol();
            column.setName(col.getName());
            column.setDescribe(col.getDescribe());
            column.setSeq(col.getSeq());
        }
        return columns;
    }
}
