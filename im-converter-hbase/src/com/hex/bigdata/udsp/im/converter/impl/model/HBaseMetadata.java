package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.im.converter.model.Metadata;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class HBaseMetadata extends Metadata {
    public HBaseMetadata() {
    }

    public HBaseMetadata(List<Property> properties) {
        super(properties);
    }

    public HBaseMetadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public HBaseMetadata(Metadata metadata) {
        super(metadata);
    }

    public int gainRegionNum() {
        String value = gainProperty("hbase.region.num").getValue();
        if (StringUtils.isBlank(value) || !StringUtils.isNumeric(value)) {
            throw new IllegalArgumentException ("hbase.region.num不能为空且必须是整数");
        }
        return Integer.valueOf(value);
    }

    public String gainCompression() {
        String value = gainProperty("hbase.compression").getValue();
        if (StringUtils.isBlank(value)) {
            //throw new IllegalArgumentException("hbase.compression不能为空");
            value = "snappy";
        }
        return value;
    }

    public String gainMethod() {
        String value = gainProperty("hbase.method").getValue();
        if (StringUtils.isBlank(value)) {
            //throw new IllegalArgumentException("hbase.method不能为空");
            value = "table_att";
        }
        return value;
    }

    public String gainSplitPolicy() {
        String value = gainProperty("hbase.split.policy").getValue();
        if (StringUtils.isBlank(value)) {
            //throw new IllegalArgumentException("hbase.split.policy不能为空");
            value = "org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy";
        }
        return value;
    }

    public String gainFamily() {
        String value = gainProperty("hbase.family").getValue();
        if (StringUtils.isBlank(value)) {
            //throw new IllegalArgumentException("hbase.family不能为空");
            value = "f";
        }
        return value;
    }

    public String gainQualifier() {
        String value = gainProperty("hbase.qualifier").getValue();
        if (StringUtils.isBlank(value)) {
            //throw new IllegalArgumentException("hbase.qualifier不能为空");
            value = "q";
        }
        return value;
    }

    public String gainFqDataType() {
        String value = gainProperty("hbase.fq.data.type").getValue();
        if (StringUtils.isBlank(value)) {
            //throw new IllegalArgumentException("hbase.fq.data.type不能为空");
            value = "dsv";
        }
        return value;
    }

    public String gainFqDsvSeparator() {
        String value = gainProperty("hbase.fq.dsv.separator").getValue();
        if (StringUtils.isBlank(value)) {
            //throw new IllegalArgumentException("hbase.fq.dsv.separator不能为空");
            value = "\\007";
        }
        return value;
    }

    public String gainFamilyReplicationScope() {
        return gainProperty("hbase.family.replication.scope").getValue();
    }

}
