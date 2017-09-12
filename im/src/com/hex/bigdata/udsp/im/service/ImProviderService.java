package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.*;
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
        BatchSourceProvider batchSourceProvider = getBatchSourceProvider(sDs);
        status = batchSourceProvider.createSourceEngineSchema(model);
        if (!status) return status;
        Datasource tDs = model.getTargetMetadata().getDatasource();
        BatchTargetProvider batchTargetProvider = getBatchTargetProvider(tDs);
        status = batchTargetProvider.createTargetEngineSchema(model);
        return status;
    }

    public boolean dropEngineSchema(Model model) throws Exception {
        boolean status = false;
        Datasource sDs = model.getSourceDatasource();
        BatchSourceProvider batchSourceProvider = getBatchSourceProvider(sDs);
        status = batchSourceProvider.dropSourceEngineSchema(model);
        if (!status) return status;
        Datasource tDs = model.getTargetMetadata().getDatasource();
        BatchTargetProvider batchTargetProvider = getBatchTargetProvider(tDs);
        status = batchTargetProvider.dropTargetEngineSchema(model);
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

    private BatchSourceProvider getBatchSourceProvider(Datasource datasource) {
        String implClass = getImplClass(datasource);
        return (BatchSourceProvider) WebApplicationContextUtil.getBean(implClass);
    }

    private BatchTargetProvider getBatchTargetProvider(Datasource datasource) {
        String implClass = getImplClass(datasource);
        return (BatchTargetProvider) WebApplicationContextUtil.getBean(implClass);
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

    public boolean createTable(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        String implClass = getImplClass(datasource);
        TargetProvider provider = (TargetProvider) WebApplicationContextUtil.getBean(implClass);
        return provider.createSchema(metadata);
    }

    public boolean dropTable(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        String implClass = getImplClass(datasource);
        TargetProvider provider = (TargetProvider) WebApplicationContextUtil.getBean(implClass);
        return provider.dropSchema(metadata);
    }
}
