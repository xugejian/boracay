package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;

/**
 * HBase的数据源配置
 */
public class HBaseDatasource extends Datasource {
    private int maxNum = 65535;
    private String fqDataType = "dsv";
    private String dsvSeprator = "\\007";
    private String familyName = "f";
    private String qulifierName = "q";


    public HBaseDatasource(List<Property> properties) {
        super(properties);
    }

    public HBaseDatasource(Map<String, Property> propertieMap) {
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


    public int getMaxNum() {
        String value = getProperty("hbase.max.data.size").getValue();
        if (StringUtils.isNotBlank(value)) {
            try {
                maxNum = Integer.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("hbase.max.data.size为一整数");
            }
        }
        return maxNum;
    }

    public String getFqDataType(){
        String value = getProperty("hbase.fq.data.type").getValue();
        if (StringUtils.isNotBlank(value)) {
            fqDataType = value;
        }
        return fqDataType;
    }

    public String getDsvSeprator(){
        String value = getProperty("hbase.fq.dsv.seprator").getValue();
        if (StringUtils.isNotBlank(value)) {
            dsvSeprator = value;
        }
        return dsvSeprator;
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
}
