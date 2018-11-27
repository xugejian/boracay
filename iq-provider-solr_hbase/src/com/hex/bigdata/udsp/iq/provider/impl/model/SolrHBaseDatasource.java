package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;

/**
 * Solr+HBase的数据源配置
 */
public class SolrHBaseDatasource extends Datasource {

    public SolrHBaseDatasource(List<Property> properties) {
        super (properties);
    }

    public SolrHBaseDatasource(Map<String, Property> propertieMap) {
        super (propertieMap);
    }

    public String getZkQuorum() {
        String value = getProperty ("hbase.zk.quorum").getValue ();
        if (StringUtils.isBlank (value))
            throw new IllegalArgumentException ("hbase.zk.quorum不能为空");
        return value;
    }

    public String getZkPort() {
        return getProperty ("hbase.zk.port").getValue ();
    }

    public String getRpcTimeout() {
        return getProperty ("hbase.rpc.timeout").getValue ();
    }

    public String getClientRetriesNumber() {
        return getProperty ("hbase.client.retries.number").getValue ();
    }

    public String getClientPause() {
        return getProperty ("hbase.client.pause").getValue ();
    }

    public String getZkRecoveryRetry() {
        return getProperty ("zookeeper.recovery.retry").getValue ();
    }

    public String getZkRecoveryRetryIntervalmill() {
        return getProperty ("zookeeper.recovery.retry.intervalmill").getValue ();
    }

    public String getClientOperationTimeout() {
        return getProperty ("hbase.client.operation.timeout").getValue ();
    }

    // 已被弃用
    @Deprecated
    public String getRegionserverLeasePeriod() {
        return getProperty ("hbase.regionserver.lease.period").getValue ();
    }

    public String getClientScannerTimeoutPeriod() {
        return getProperty ("hbase.client.scanner.timeout.period").getValue ();
    }

    public String getHbaseSecurityAuthentication() {
        return getProperty ("hbase.security.authentication").getValue ();
    }

    public String getHadoopSecurityAuthentication() {
        return getProperty ("hadoop.security.authentication").getValue ();
    }

    public String getHbaseMasterKerberosPrincipal() {
        return getProperty ("hbase.master.kerberos.principal").getValue ();
    }

    public String getHbaseRegionserverKerberosPrincipal() {
        return getProperty ("hbase.regionserver.kerberos.principal").getValue ();
    }

    public String getKerberosPrincipal() {
        return getProperty ("kerberos.principal").getValue ();
    }

    public String getKerberosKeytab() {
        return getProperty ("kerberos.keytab").getValue ();
    }

    public String getSolrServers() {
        String value = getProperty ("solr.servers").getValue ();
        if (StringUtils.isBlank (value))
            throw new IllegalArgumentException ("solr.servers不能为空");
        return value;
    }

    public int getMaxNum() {
        String value = getProperty ("max.data.size").getValue ();
        if (StringUtils.isBlank (value)) {
            value = "65536";
        }
        return Integer.valueOf (value);
    }
}
