package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrHBaseDatasource;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrHBaseProvider")
public class SolrHBaseProvider implements RealtimeTargetProvider, BatchTargetProvider {

    static {
        // 解决winutils.exe不存在的问题
        try {
            File workaround = new File(".");
            System.getProperties().put("hadoop.home.dir",
                    workaround.getAbsolutePath());
            new File("./bin").mkdirs();
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger logger = LoggerFactory.getLogger(SolrHBaseProvider.class);
    private static Map<String, HBaseConnectionPoolFactory> dataSourcePool;

    private synchronized HBaseConnectionPoolFactory getDataSource(SolrHBaseDatasource datasource) {
        String dsId = datasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, HBaseConnectionPoolFactory>();
        }
        HBaseConnectionPoolFactory factory = dataSourcePool.get(dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config();
            config.lifo = true;
            config.minIdle = 1;
            config.maxActive = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new HBaseConnectionPoolFactory(config, datasource.getZkQuorum(), datasource.getZkPort());
            dataSourcePool.put(dsId, factory);
        }
        return factory;
    }

    private HConnection getConnection(SolrHBaseDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }

    private void release(SolrHBaseDatasource datasource, HConnection conn) {
        getDataSource(datasource).releaseConnection(conn);
    }

    private SolrServer getSolrServer(String collectionName, SolrHBaseDatasource datasource) {
        if (StringUtils.isBlank(collectionName)) {
            throw new IllegalArgumentException("collection name不能为空");
        }
        String[] tempServers = datasource.getSolrServers().split(",");
        String[] servers = new String[tempServers.length];
        for (int i = 0; i < tempServers.length; i++) {
            servers[i] = "http://" + tempServers[i] + "/solr/" + collectionName;
        }
        SolrServer solrServer = null;
        try {
            solrServer = new LBHttpSolrServer(servers);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return solrServer;
    }

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
    public void create() {

    }

    @Override
    public void drop() {

    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }
}
