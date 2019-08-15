package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.iq.provider.model.Metadata;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2018/8/20.
 */
public class SolrHBaseMetadata extends HBaseMetadata {
    public SolrHBaseMetadata() {
    }

    public SolrHBaseMetadata(List<Property> properties) {
        super (properties);
    }

    public SolrHBaseMetadata(Map<String, Property> propertyMap) {
        super (propertyMap);
    }

    public SolrHBaseMetadata(Metadata metadata) {
        super (metadata);
    }

    /**
     * Solr主键字段
     *
     * @return
     */
    public String gainSolrPrimaryKey() {
        String value = gainProperty ("solr.primary.key").getValue ();
        if (StringUtils.isBlank (value)) {
            value = "id";
        }
        return value;
    }

    public String gainHBaseNamespace() {
        return gainProperty ("hbase.namespace").getValue ();
    }

    /**
     * HBase真实表名称
     *
     * @return
     */
    public String gainHBaseTableName() {
        String hbaseNamespace = gainHBaseNamespace ();
        if (StringUtils.isNotBlank (hbaseNamespace) && !"default".equalsIgnoreCase (hbaseNamespace)) {
            return hbaseNamespace + ":" + getTbName ();
        }
        return getTbName ();
    }
}
