package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrDatasource extends Datasource {

    public SolrDatasource(Datasource datasource) {
        super (datasource);
    }

    public String gainSolrServers() {
        String value = gainProperty ("solr.servers").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("solr.servers不能为空");
        }
        return value;
    }

    public String gainSolrZkHost() {
        String value = gainProperty ("solr.zkHost").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("solr.zkHost不能为空");
        }
        return value;
    }

    public String gainSolrSecurityAuthentication() {
        return gainProperty ("solr.security.authentication").getValue ();
    }

    public String gainSolrJavaSecurityKrb5Conf() {
        return gainProperty ("solr.java.security.krb5.conf").getValue ();
    }

    public String gainSolrJavaSecurityAuthLoginConfig() {
        return gainProperty ("solr.java.security.auth.login.config").getValue ();
    }
}