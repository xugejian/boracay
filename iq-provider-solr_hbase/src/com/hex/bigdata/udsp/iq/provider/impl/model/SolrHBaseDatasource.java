package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import org.apache.commons.lang.StringUtils;

/**
 * Solr+HBase的数据源配置
 */
public class SolrHBaseDatasource extends HBaseDatasource {

    public SolrHBaseDatasource(Datasource datasource) {
        super (datasource);
    }

    public String gainSolrServers() {
        String value = gainProperty ("solr.servers").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("solr.servers不能为空");
        }
        return value;
    }
}
