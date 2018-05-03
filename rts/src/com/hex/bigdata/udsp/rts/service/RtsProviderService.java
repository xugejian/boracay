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
import com.hex.bigdata.udsp.common.util.PropertyUtil;
import com.hex.bigdata.udsp.rts.dto.RtsConsumerRequestView;
import com.hex.bigdata.udsp.rts.dto.RtsMatedataColsView;
import com.hex.bigdata.udsp.rts.dto.RtsProducerRequestView;
import com.hex.bigdata.udsp.rts.executor.Executor;
import com.hex.bigdata.udsp.rts.executor.model.*;
import com.hex.bigdata.udsp.rts.model.RtsConsumer;
import com.hex.bigdata.udsp.rts.model.RtsMatedata;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
import com.hex.bigdata.udsp.rts.model.RtsProducer;
import com.hex.bigdata.udsp.rts.util.CommonUtil;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tomnic on 2017/3/7.
 */
@Service
public class RtsProviderService extends BaseService {
    private static final String RTS_IMPL_CLASS = "RTS_IMPL_CLASS";

    @Autowired
    private ComPropertiesService comPropertiesService;
    @Autowired
    private RtsMatedataService rtsMatedataService;
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private RtsMatedataColService rtsMatedataColService;
    @Autowired
    private RtsConsumerService rtsConsumerService;
    @Autowired
    private RtsProducerService rtsProducerService;
    @Autowired
    private GFDictMapper gfDictMapper;

    /**
     * 获取消费者应用信息
     *
     * @param requestView
     * @return
     */
    public ConsumerResponse consumer(RtsConsumerRequestView requestView) {

        String consumerId = requestView.getConsumerId();
        int timeout = requestView.getTimeout();

        timeout = timeout == 0 ? 1000 : timeout;
        //获取消费者
        RtsConsumer rtsConsumer = this.rtsConsumerService.select(consumerId);
        //获取元数据信息及数据列信息
        RtsMatedata rtsMatedata = this.rtsMatedataService.select(rtsConsumer.getMdId());

        List<RtsMatedataCol> rtsMatedataCols = this.rtsMatedataColService.selectByMdId(rtsMatedata.getPkId());
        RtsMatedataColsView rtsMatedataColsView = new RtsMatedataColsView(rtsMatedata, rtsMatedataCols);

        //获取数据源
        ComDatasource comDatasource = this.comDatasourceService.select(rtsMatedata.getDsId());
        //获取数据源配置
        List<ComProperties> comPropertieList = this.comPropertiesService.selectList(comDatasource.getPkId());
        //获取消费者配置
        List<ComProperties> consumerComPropertieList = this.comPropertiesService.selectList(rtsConsumer.getPkId());
        //合并消费者和数据源参数
        comPropertieList = PropertyUtil.mergeProperties(comPropertieList, consumerComPropertieList);

        ComProperties comProperties = new ComProperties();
        comProperties.setName("group.id");
        comProperties.setValue(rtsConsumer.getGroupId());
        consumerComPropertieList.add(comProperties);

        //获取消费者数据源配置
        ConsumerDatasource datasource = new ConsumerDatasource(DatasourceUtil.getDatasource(comDatasource, comPropertieList));
        datasource.setGroupId(rtsConsumer.getGroupId());

        //获取消费者元数据信息
        ConsumerMatedata consumerMatedata = getConsumerMatedata(rtsMatedataColsView, datasource);

        ConsumerApplication consumerApplication = getConsumerApplication(rtsConsumer, consumerMatedata);

        ConsumerRequest consumerRequest = new ConsumerRequest(consumerApplication, timeout);

        Executor provider = getProviderImpl(datasource);

        ConsumerResponse consumerResponse = provider.pull(consumerRequest);
        //设置返回列信息
        consumerResponse.setColumns(this.putColumnIntoMap(rtsMatedataCols));
        if (consumerResponse.getRecords() != null && consumerResponse.getRecords().size() > 0) {
            consumerResponse.setStatus(Status.SUCCESS);
            consumerResponse.setStatusCode(StatusCode.SUCCESS);
        }
        return consumerResponse;
    }

    /**
     * 元数据列信息插入到Map
     *
     * @param rtsMatedataCols
     * @return
     */
    private LinkedHashMap<String, String> putColumnIntoMap(List<RtsMatedataCol> rtsMatedataCols) {
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        for (RtsMatedataCol rtsMatedataCol : rtsMatedataCols) {
            columnMap.put(rtsMatedataCol.getName(), rtsMatedataCol.getType());
        }
        return columnMap;
    }

    /**
     * 获取生产者应用信息
     *
     * @param requestView
     * @return
     */
    public ProducerResponse producer(RtsProducerRequestView requestView) {
        String producerId = requestView.getProducerId();
        //获取生产者
        RtsProducer rtsProducer = this.rtsProducerService.select(producerId);
        //获取元数据信息及数据列信息
        RtsMatedata rtsMatedata = this.rtsMatedataService.select(rtsProducer.getMdId());

        List<RtsMatedataCol> rtsMatedataCols = this.rtsMatedataColService.selectByMdId(rtsMatedata.getPkId());
        RtsMatedataColsView rtsMatedataColsView = new RtsMatedataColsView(rtsMatedata, rtsMatedataCols);

        //获取数据源
        ComDatasource comDatasource = this.comDatasourceService.select(rtsMatedata.getDsId());
        //获取数据源配置
        List<ComProperties> comPropertieList = this.comPropertiesService.selectList(comDatasource.getPkId());
        //获取生产者配置
        List<ComProperties> producerComPropertieList = this.comPropertiesService.selectList(rtsProducer.getPkId());
        //合并生产者和数据源参数
        comPropertieList = PropertyUtil.mergeProperties(comPropertieList, producerComPropertieList);

        //获取生产者数据源配置
        ProducerDatasource datasource = new ProducerDatasource(DatasourceUtil.getDatasource(comDatasource, comPropertieList));
        //获取生产者元数据信息
        ProducerMatedata producerMatedata = getProducerMatedata(rtsMatedataColsView, datasource);

        ProducerApplication producerApplication = getProducerApplication(rtsProducer, producerMatedata);


        List<Map<String, String>> messageDatas = requestView.getListMap();

        ProducerRequest producerRequest = new ProducerRequest(producerApplication, messageDatas);

        Executor provider = getProviderImpl(datasource);

        ProducerResponse response = provider.push(producerRequest);

        return response;
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(Datasource datasource) {
        Executor provider = getProviderImpl(datasource);
        return provider.testDatasource(datasource);
    }

    /**
     * 得到生产接口的实例
     *
     * @param datasource
     * @return
     */
    private Executor getProviderImpl(Datasource datasource) {
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey(RTS_IMPL_CLASS, datasource.getType());
            implClass = gfDict.getDictName();
        }
        return (Executor) ObjectUtil.newInstance(implClass);
    }

    private ConsumerMatedata getConsumerMatedata(RtsMatedataColsView matedataColsView, ConsumerDatasource datasource) {
        ConsumerMatedata consumerMatedata = new ConsumerMatedata();
        consumerMatedata.setDatasource(datasource);
        RtsMatedata rtsMatedata = matedataColsView.getRtsMatedata();
        consumerMatedata.setName(rtsMatedata.getName());
        consumerMatedata.setDescribe(rtsMatedata.getDescribe());
        consumerMatedata.setNote(rtsMatedata.getNote());
        consumerMatedata.setTopic(rtsMatedata.getTopic());
        consumerMatedata.setColumns(CommonUtil.getColumns(matedataColsView.getRtsMatedataColList()));
        return consumerMatedata;
    }

    private ConsumerApplication getConsumerApplication(RtsConsumer rtsConsumer, ConsumerMatedata matedata) {
        ConsumerApplication consumerApplication = new ConsumerApplication();
        consumerApplication.setMatedata(matedata);
        consumerApplication.setName(rtsConsumer.getName());
        consumerApplication.setDescribe(rtsConsumer.getDescribe());
        consumerApplication.setName(rtsConsumer.getNote());
        return consumerApplication;
    }

    private ProducerMatedata getProducerMatedata(RtsMatedataColsView matedataColsView, ProducerDatasource datasource) {
        ProducerMatedata producerMatedata = new ProducerMatedata();
        producerMatedata.setDatasource(datasource);
        RtsMatedata rtsMatedata = matedataColsView.getRtsMatedata();
        producerMatedata.setName(rtsMatedata.getName());
        producerMatedata.setDescribe(rtsMatedata.getDescribe());
        producerMatedata.setNote(rtsMatedata.getNote());
        producerMatedata.setTopic(rtsMatedata.getTopic());
        producerMatedata.setColumns(CommonUtil.getColumns(matedataColsView.getRtsMatedataColList()));
        return producerMatedata;
    }

    private ProducerApplication getProducerApplication(RtsProducer rtsProducer, ProducerMatedata matedata) {
        ProducerApplication producerApplication = new ProducerApplication();
        producerApplication.setName(rtsProducer.getName());
        producerApplication.setDescribe(rtsProducer.getDescribe());
        producerApplication.setNote(rtsProducer.getNote());
        producerApplication.setMatedata(matedata);
        return producerApplication;
    }
}
