package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.im.provider.impl.factory.SolrConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrHBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class SolrWrapper extends BatchWrapper {
    private static Logger logger = LogManager.getLogger(SolrWrapper.class);
    private static Map<String, SolrConnectionPoolFactory> dataSourcePool;

    protected synchronized SolrConnectionPoolFactory getDataSource(SolrDatasource datasource, String collectionName) {
        String dsId = datasource.getId() + ":" + collectionName;
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, SolrConnectionPoolFactory>();
        }
        SolrConnectionPoolFactory factory = dataSourcePool.get(dsId);
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
            factory = new SolrConnectionPoolFactory(config, datasource.getSolrServers(), collectionName);
            dataSourcePool.put(dsId, factory);
        }
        return factory;
    }

    protected SolrServer getSolrServer(String collectionName, SolrHBaseDatasource datasource) {
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

    protected SolrServer getConnection(SolrDatasource datasource, String collectionName) {
        try {
            return getDataSource(datasource, collectionName).getConnection();
        } catch (Exception e) {
            return null;
        }
    }

    protected void release(SolrDatasource datasource, String collectionName, SolrServer solrServer) {
        getDataSource(datasource, collectionName).releaseConnection(solrServer);
    }

    protected String getSourcePrimaryKey(List<ModelMapping> modelMappings) {
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            if (metadataCol.isPrimary()) {
                return mapping.getName();
            }
        }
        return null;
    }

    protected String getTargetPrimaryKey(List<ModelMapping> modelMappings) {
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            if (metadataCol.isPrimary()) {
                return metadataCol.getName();
            }
        }
        return null;
    }

    protected List<TblProperty> getTblProperties(SolrDatasource datasource, String pkName, String collectionName) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("solr.url", datasource.getSolrUrl() + "/solr")); // zookeeper地址、端口和目录
        tblProperties.add(new TblProperty("solr.query", "*:*")); // Solr查询语句
        tblProperties.add(new TblProperty("solr.cursor.batch.size", "1000")); // 批量大小
        tblProperties.add(new TblProperty("solr.primary.key", pkName)); // Solr Collection 主键字段名
        tblProperties.add(new TblProperty("is.solrcloud", "1")); // 0：单机模式，1：集群模式，Default：0
        tblProperties.add(new TblProperty("collection.name", collectionName)); // Solr Collection Name
        return tblProperties;
    }

    protected List<TableColumn> getTargetColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            String dataType = getDataType(metadataCol.getType(), metadataCol.getLength());
            columns.add(new TableColumn(metadataCol.getName(), dataType, metadataCol.getDescribe()));
        }
        return columns;
    }

    protected List<TableColumn> getSourceColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            String dataType = getDataType(mapping.getType(), mapping.getLength());
            columns.add(new TableColumn(mapping.getName(), dataType, mapping.getDescribe()));
        }
        return columns;
    }

    @Override
    protected List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        if (modelMappings == null || modelMappings.size() == 0)
            return null;
        List<java.lang.String> selectColumns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings)
            selectColumns.add(mapping.getName());
        return selectColumns;
    }

    @Override
    protected List<String> getInsertColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        if (modelMappings == null || modelMappings.size() == 0)
            return null;
        List<java.lang.String> insertColumns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings)
            insertColumns.add(mapping.getMetadataCol().getName());
        return insertColumns;
    }
}
