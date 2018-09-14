package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.im.converter.*;
import com.hex.bigdata.udsp.im.converter.model.Metadata;
import com.hex.bigdata.udsp.im.converter.model.MetadataCol;
import com.hex.bigdata.udsp.im.converter.model.Model;
import com.hex.bigdata.udsp.im.converter.model.RealtimeResponse;
import com.hex.bigdata.udsp.im.model.RealtimeNodeInfo;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-6.
 */
@Service
public class ImConverterService {
    private static Logger logger = LogManager.getLogger(ImConverterService.class);
    private static final String IM_IMPL_CLASS = "IM_IMPL_CLASS";
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private GFDictMapper gfDictMapper;
    @Autowired
    private RealtimeNodeService realtimeNodeService;

    /**
     * 获取字段信息
     *
     * @param metadata
     * @return
     */
    public List<MetadataCol> getCloumnInfo(Metadata metadata) {
        return getTargetConverterImpl(metadata.getDatasource()).columnInfo(metadata);
    }

    /**
     * 检查Schema是否存在
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public boolean checkSchema(Metadata metadata) throws Exception {
        return getTargetConverterImpl(metadata.getDatasource()).checkSchema(metadata);
    }

    /**
     * 获取字段信息
     *
     * @param model
     * @return
     */
    public List<MetadataCol> getCloumnInfo(Model model) {
        return getSourceConverterImpl(model.getSourceDatasource()).columnInfo(model);
    }

    /**
     * 创建引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    public void createEngineSchema(Model model) throws Exception {
        logger.debug("创建引擎Schema【START】");
        BatchSourceConverter batchSourceProvider = getBatchSourceConverterImpl(model.getSourceDatasource());
        BatchTargetConverter batchTargetProvider = getBatchTargetConverterImpl(model.getTargetMetadata().getDatasource());
        batchSourceProvider.createSourceEngineSchema(model);
        try {
            batchTargetProvider.createTargetEngineSchema(model);
        } catch (Exception e) {
            batchSourceProvider.dropSourceEngineSchema(model); // 回滚
            throw new Exception(e);
        }
        logger.debug("创建引擎Schema【END】");
    }

    /**
     * 删除引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    public void dropEngineSchema(Model model) throws Exception {
        logger.debug("删除引擎Schema【START】");
        getBatchSourceConverterImpl(model.getSourceDatasource()).dropSourceEngineSchema(model);
        getBatchTargetConverterImpl(model.getTargetMetadata().getDatasource()).dropTargetEngineSchema(model);
        logger.debug("删除引擎Schema【END】");
    }

    /**
     * 创建Schema
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public void createSchema(Metadata metadata) throws Exception {
        logger.debug("创建Schema【SATRT】");
        getTargetConverterImpl(metadata.getDatasource()).createSchema(metadata);
        logger.debug("创建Schema【END】");
    }

    /**
     * 删除Schema
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public void dropSchema(Metadata metadata) throws Exception {
        logger.debug("删除Schema【START】");
        getTargetConverterImpl(metadata.getDatasource()).dropSchema(metadata);
        logger.debug("删除Schema【END】");
    }

    /**
     * 构建实时任务
     *
     * @param model
     */
    public void buildRealtime(String key, Model model) throws Exception {
        logger.debug("构建实时任务【开始】");
        RealtimeResponse realtimeResponse = getRealtimeTargetConverterImpl(model.getTargetMetadata().getDatasource()).buildRealtime(key, model);
        long countNum = realtimeResponse.getCountNum();
        String message = realtimeResponse.getMessage();
        if (countNum > 0 || StringUtils.isNotBlank(message)) {
            // 数据量信息处理
            if (countNum > 0) {
                RealtimeNodeInfo realtimeNodeInfo = realtimeNodeService.select(key);
                long consumerNum = realtimeNodeInfo.getConsumerNum() + countNum;
                long meetNum = realtimeNodeInfo.getMeetNum() + (countNum - realtimeResponse.getParseFailedNum() - realtimeResponse.getFilterNum());
                long storeNum = realtimeNodeInfo.getStoreNum() + realtimeResponse.getStoreSucceedNum();
                logger.debug("消费条数：" + consumerNum + "，筛选条数：" + meetNum + "，存储条数：" + storeNum);
                realtimeNodeService.running(key, consumerNum, meetNum, storeNum);
            }
            // 错误信息处理
            if (StringUtils.isNoneBlank(message)) {
                logger.warn(message);
                realtimeNodeService.running(key, format.format(new Date()) + ": " + message);
            }
        }
        logger.debug("构建实时任务【结束】");
    }

    /**
     * 构建批量任务
     *
     * @param model
     */
    public void buildBatch(String key, Model model) throws Exception {
        logger.debug("构建批量任务【开始】");
        getBatchTargetConverterImpl(model.getTargetMetadata().getDatasource()).buildBatch(key, model);
        logger.debug("构建批量任务【结束】");
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(Datasource datasource) {
        logger.debug("测试数据源【START】");
        boolean status = getConverterImpl(datasource).testDatasource(datasource);
        logger.debug("测试数据源【END】");
        return status;
    }

    /**
     * 添加字段
     *
     * @param metadata
     * @param addMetadataCols
     * @throws Exception
     */
    public void addColumns(Metadata metadata, List<MetadataCol> addMetadataCols) throws Exception {
        logger.debug("添加字段【START】");
        getTargetConverterImpl(metadata.getDatasource()).addColumns(metadata, addMetadataCols);
        logger.debug("添加字段【END】");
    }

    private BatchSourceConverter getBatchSourceConverterImpl(Datasource datasource) {
        return (BatchSourceConverter) ObjectUtil.newInstance(getImplClass(datasource));
    }

    private BatchTargetConverter getBatchTargetConverterImpl(Datasource datasource) {
        return (BatchTargetConverter) ObjectUtil.newInstance(getImplClass(datasource));
    }

    private RealtimeTargetConverter getRealtimeTargetConverterImpl(Datasource datasource) {
        return (RealtimeTargetConverter) ObjectUtil.newInstance(getImplClass(datasource));
    }

    private SourceConverter getSourceConverterImpl(Datasource datasource) {
        return (SourceConverter) ObjectUtil.newInstance(getImplClass(datasource));
    }

    private TargetConverter getTargetConverterImpl(Datasource datasource) {
        return (TargetConverter) ObjectUtil.newInstance(getImplClass(datasource));
    }

    private Converter getConverterImpl(Datasource datasource) {
        return (Converter) ObjectUtil.newInstance(getImplClass(datasource));
    }

    private String getImplClass(Datasource datasource) {
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey(IM_IMPL_CLASS, datasource.getType());
            implClass = gfDict.getDictName();
        }
        return implClass;
    }
}
