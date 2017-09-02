package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;

/**
 * Solr+HBase的数据源配置
 */
public class SolrHBaseDatasource extends Datasource {
    private String zkQuorum;
    private String zkPort;
    private String solrServers;

    private int maxNum = 65535;
    private String seprator = "\\007";
    private String familyName = "f";
    private String qulifierName = "q";

    public SolrHBaseDatasource(List<Property> properties) {
        super(properties);
    }

    public SolrHBaseDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public String getZkQuorum() {
        String value = getProperty("hbase.zk.quorum").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("hbase.zk.quorum不能为空");
        return value;
    }

    public String getZkPort() {
        String value = getProperty("hbase.zk.port").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("hbase.zk.port不能为空");
        return value;
    }

    public String getSolrServers() {
        String value = getProperty("solr.servers").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("solr.servers不能为空");
        return value;
    }


    public String getSeprator(){
        String value = getProperty("hbase.fqSep").getValue();
        if (StringUtils.isNotBlank(value)) {
            seprator = value;
        }
        return seprator;
    }

    public byte[] getFamilyName(){
        String value = getProperty("hbase.family.name").getValue();
        if (StringUtils.isNotBlank(value)) {
            familyName = value;
        }
        return Bytes.toBytes(familyName);
    }

    public byte[] getQulifierName(){
        String value = getProperty("hbase.qulifier.name").getValue();
        if (StringUtils.isNotBlank(value)) {
            qulifierName = value;
        }
        return Bytes.toBytes(qulifierName);
    }

    public int getMaxNum() {
        String value = getProperty("max.data.size").getValue();
        if (StringUtils.isNotBlank(value)) {
            try {
                maxNum = Integer.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("max.data.size为一整数");
            }
        }
        return maxNum;
    }
}
