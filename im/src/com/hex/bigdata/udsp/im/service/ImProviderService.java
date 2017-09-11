package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchProvider;
import com.hex.bigdata.udsp.im.provider.SourceProvider;
import com.hex.bigdata.udsp.im.provider.TargetProvider;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-6.
 */
@Service
public class ImProviderService {

    @Autowired
    private GFDictMapper gfDictMapper;

    public List<MetadataCol> getCloumnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        TargetProvider provider = getTargetProvider(datasource);
        return provider.columnInfo(metadata);
    }


    public List<MetadataCol> getCloumnInfo(Model model) {
        Datasource datasource = model.getSourceDatasource();
        SourceProvider provider = getSourceProvider(datasource);
        return provider.columnInfo(model);
    }

    public boolean createEngineSchema(Model model) throws Exception {
        boolean status = false;

        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        Datasource tDs = model.getTargetMetadata().getDatasource();
        String tDsType = tDs.getType();

        BatchProvider sBatchProvider = getBatchProvider(sDs);
        status = sBatchProvider.createEngineSchema(model);

        if (sDsType.equals(tDsType) || !status) {
            return status;
        }

        BatchProvider tBatchProvider = getBatchProvider(tDs);
        status = tBatchProvider.createEngineSchema(model);

        return status;
    }

    public boolean dropEngineSchema(Model model) throws Exception {
        boolean status = false;

        Datasource sDs = model.getSourceDatasource();
        String sDsType = sDs.getType();
        Datasource tDs = model.getTargetMetadata().getDatasource();
        String tDsType = tDs.getType();

        BatchProvider sBatchProvider = getBatchProvider(sDs);
        status = sBatchProvider.dropEngineSchema(model);

        if (sDsType.equals(tDsType) || !status) {
            return status;
        }

        BatchProvider tBatchProvider = getBatchProvider(tDs);
        status = tBatchProvider.dropEngineSchema(model);

        return status;
    }

    public boolean createSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        TargetProvider provider = getTargetProvider(datasource);
        return provider.createSchema(metadata);
    }

    public boolean dropSchema(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        TargetProvider provider = getTargetProvider(datasource);
        return provider.dropSchema(metadata);
    }

    private BatchProvider getBatchProvider(Datasource datasource) {
        String implClass = getImplClass(datasource);
        return (BatchProvider) WebApplicationContextUtil.getBean(implClass);
    }

    private SourceProvider getSourceProvider(Datasource datasource) {
        String implClass = getImplClass(datasource);
        return (SourceProvider) WebApplicationContextUtil.getBean(implClass);
    }

    private TargetProvider getTargetProvider(Datasource datasource) {
        String implClass = getImplClass(datasource);
        return (TargetProvider) WebApplicationContextUtil.getBean(implClass);
    }

    private String getImplClass(Datasource datasource) {
        String type = datasource.getType();
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey("IM_IMPL_CLASS", type);
            implClass = gfDict.getDictName();
        }
        return implClass;
    }

}
