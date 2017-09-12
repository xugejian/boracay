package com.hex.bigdata.udsp.im.provider.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.SolrModel;
import com.hex.bigdata.udsp.im.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.rest.SolrSchemaRestApi;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrProvider")
public class SolrProvider extends SolrWrapper implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(SolrProvider.class);

    private List<MetadataCol> getColumns(String collectionName, String solrServers) {
        if(StringUtils.isEmpty(collectionName) || StringUtils.isEmpty(solrServers)){
            return null;
        }
        String response = "";
        String[] addresses = solrServers.split(",");
        for(String solrServer : addresses){
            String url = "http://"+solrServer+"/solr/"+collectionName+"/schema/fields";
            response = SolrUtil.sendGet(url,"");
            if(StringUtils.isEmpty(response)){
                continue;
            }else{
                break;
            }
        }
        JSONObject rs = JSONObject.parseObject(response);
        JSONArray fields = (JSONArray)rs.get("fields");
        List<MetadataCol> metadataCols = new ArrayList<>();
        MetadataCol mdCol = null;
        for(int i=0;i<fields.size();i++){
            mdCol = new MetadataCol();
            mdCol.setSeq((short)i);
            mdCol.setName((String)fields.getJSONObject(i).get("name"));
            mdCol.setDescribe((String)fields.getJSONObject(i).get("name"));
            mdCol.setType(getColType((String)fields.getJSONObject(i).get("type")));
            mdCol.setLength("");
            mdCol.setIndexed((boolean)fields.getJSONObject(i).get("indexed"));
            mdCol.setStored((boolean)fields.getJSONObject(i).get("stored"));
            metadataCols.add(mdCol);
        }
        return metadataCols;
    }


    public static DataType getColType(String type){
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type){
            case "STRING":
                dataType = DataType.STRING;
                break;
            case "INT":
                dataType = DataType.INT;
                break;
            case "FLOAT":
                dataType = DataType.FLOAT;
                break;
            case "DOUBLE":
                dataType = DataType.DOUBLE;
                break;
            case "DATE":
                dataType = DataType.TIMESTAMP;
                break;
            case "BOOLEAN":
                dataType = DataType.BOOLEAN;
                break;
            default:
                dataType = DataType.STRING;
        }
        return dataType;
    }

    public static void main(String[] args) {
//        String response = SolrUtil.sendGet("http://hadoop03:8983/solr/hello/schema/fields","");
//        JSONObject rs = JSONObject.parseObject(response);
//        Object field = rs.get("fields");
//        System.out.print(response);

    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        Map<String, Property> propertyMap = datasource.getPropertyMap();
//        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        String collectionName = metadata.getTbName();
//        SolrServer solrServer = getConnection(solrDatasource, collectionName);
        return getColumns(collectionName, propertyMap.get("solr.servers").getValue());
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        SolrUtil.uploadSolrConfig(metadata);
        Datasource datasource = metadata.getDatasource();
        Map<String, Property> dsPropertyMap = datasource.getPropertyMap();
        String solrServers = dsPropertyMap.get("solr.servers").getValue();
        String[] addresses = solrServers.split(",");
        String response = "";
        Map<String, Property> mdPropertyMap = metadata.getPropertyMap();
        for(String solrServer : addresses){
            String url = "http://"+solrServer+"/solr/admin/collections";
            String param = "action=CREATE"+"&name="+metadata.getTbName()+"&replicationFactor="+mdPropertyMap.get("solr.replicas").getValue()+
                    "&numShards="+mdPropertyMap.get("solr.shards").getValue()+ "&maxShardsPerNode="+mdPropertyMap.get("solr.max.shards.per.node").getValue()+
                    "&collection.configName="+metadata.getTbName();
            response = SolrUtil.sendGet(url, param);
            if(StringUtils.isEmpty(response)){
                continue;
            }else{
                break;
            }
        }
        return true;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        SolrUtil.deleteZnode(metadata);
        Datasource datasource = metadata.getDatasource();
        Map<String, Property> dsPropertyMap = datasource.getPropertyMap();
        String solrServers = dsPropertyMap.get("solr.servers").getValue();
        String[] addresses = solrServers.split(",");
        String response = "";
        for(String solrServer : addresses){
            String url = "http://"+solrServer+"/solr/admin/collections";
            String param = "action=DELETE"+"&name="+metadata.getTbName();
            try {
                SolrUtil.sendGet(url, param);
            }catch (Exception e){
                continue;
            }
            break;
        }
        return true;
    }

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getSourceDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        SolrModel solrModel = new SolrModel(datasource.getPropertyMap());
        String collectionName = solrModel.getCollectionName();
        SolrServer solrServer = getConnection(solrDatasource, collectionName);
        return null; //getColumns(solrServer);
    }


    @Override
    public String inputSQL() {
        return null;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }

    @Override
    public boolean createEngineSchema(Model model) throws Exception {
        return false;
    }

    @Override
    public boolean dropEngineSchema(Model model) throws Exception {
        return false;
    }
}
