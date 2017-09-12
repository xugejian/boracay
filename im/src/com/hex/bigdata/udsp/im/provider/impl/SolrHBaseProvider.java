package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrHBaseWrapper;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrHBaseDatasource;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.solr.client.solrj.SolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrHBaseProvider")
public class SolrHBaseProvider extends SolrHBaseWrapper implements RealtimeTargetProvider, BatchTargetProvider {
    private static Logger logger = LoggerFactory.getLogger(SolrHBaseProvider.class);

    private List<MetadataCol> getColumns(SolrServer solrServer) {

        // TODO ..... 获取Solr字段信息

        return null;
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        SolrHBaseDatasource solrHBaseDatasource = new SolrHBaseDatasource(datasource.getPropertyMap());
        String collectionName = metadata.getTbName();
        String hbaseTbName = collectionName;
        List<MetadataCol> metadataCols = new ArrayList<>();

        // 获取Solr的字段信息
        SolrServer solrServer = getSolrServer(collectionName, solrHBaseDatasource);
        metadataCols.addAll(getColumns(solrServer));

        // HBase无法获取字段信息
        metadataCols.addAll(null);

        return metadataCols;
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        // TODO ... 创建HBase表
        // TODO ... 创建Solr表
        return false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        // TODO ... 删除Solr表
        // TODO ... 删除HBase表
        return false;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws Exception {
        return false;
    }

    @Override
    public boolean dropTargetEngineSchema(Model model) throws Exception {
        return false;
    }

    @Override
    public void outputData() {

    }
}
