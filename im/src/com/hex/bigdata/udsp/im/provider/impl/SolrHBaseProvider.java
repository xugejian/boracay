package com.hex.bigdata.udsp.im.provider.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrHBaseWrapper;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrHBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrHBaseWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrHBaseProvider")
public class SolrHBaseProvider extends SolrHBaseWrapper implements RealtimeTargetProvider {
    private static Logger logger = LoggerFactory.getLogger(SolrHBaseProvider.class);
    @Autowired
    private SolrProvider solrProvider;
    @Autowired
    private HBaseProvider hbaseProvider;
    private List<MetadataCol> getColumns(String collectionName, String solrServers) {
        return solrProvider.getColumns(collectionName, solrServers);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        SolrHBaseDatasource solrHBaseDatasource = new SolrHBaseDatasource(datasource.getPropertyMap());
        String collectionName = metadata.getTbName();
        List<MetadataCol> metadataCols = new ArrayList<>();
        // 获取Solr的字段信息
        SolrServer solrServer = getSolrServer(collectionName, solrHBaseDatasource);
        metadataCols.addAll(getColumns(collectionName,  datasource.getPropertyMap().get("solr.servers").getValue()));

        // HBase无法获取字段信息
//        metadataCols.addAll(null);
        metadataCols.addAll(getColumns(solrServer));
        // HBase无法获取字段信息
     //   metadataCols.addAll(null);
        return metadataCols;
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        return solrProvider.createSchema(metadata) && hbaseProvider.createSchema(metadata);
        // TODO ... 创建HBase表
        // TODO ... 创建Solr表
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {

        return solrProvider.dropSchema(metadata) && hbaseProvider.dropSchema(metadata);
        // TODO ... 删除Solr表
        // TODO ... 删除HBase表
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws Exception {
        return false;
    }

    @Override
    public void inputData() {

    }
}
