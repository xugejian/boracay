package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.iq.provider.model.IqDatasource;
import org.apache.commons.lang.StringUtils;

/**
 * HBase的数据源配置
 */
public class HBaseDatasource extends IqDatasource {

    public HBaseDatasource(Datasource datasource) {
        super (datasource);
    }

    public String gainZkQuorum() {
        String value = gainProperty ("hbase.zk.quorum").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("hbase.zk.quorum不能为空");
        }
        return value;
    }

    public String gainZkPort() {
        String value = gainProperty ("hbase.zk.port").getValue ();
        if (StringUtils.isBlank (value)) {
            value = "2181";
        }
        return value;
    }

    public String gainRpcTimeout() {
        return gainProperty ("hbase.rpc.timeout").getValue ();
    }

    public String gainClientRetriesNumber() {
        return gainProperty ("hbase.client.retries.number").getValue ();
    }

    public String gainClientPause() {
        return gainProperty ("hbase.client.pause").getValue ();
    }

    public String gainZkRecoveryRetry() {
        return gainProperty ("zookeeper.recovery.retry").getValue ();
    }

    public String gainZkRecoveryRetryIntervalmill() {
        return gainProperty ("zookeeper.recovery.retry.intervalmill").getValue ();
    }

    public String gainClientOperationTimeout() {
        return gainProperty ("hbase.client.operation.timeout").getValue ();
    }

    // 已被弃用
    @Deprecated
    public String gainRegionserverLeasePeriod() {
        return gainProperty ("hbase.regionserver.lease.period").getValue ();
    }

    public String gainClientScannerTimeoutPeriod() {
        return gainProperty ("hbase.client.scanner.timeout.period").getValue ();
    }

    public String gainHbaseSecurityAuthentication() {
        return gainProperty ("hbase.security.authentication").getValue ();
    }

    public String gainHadoopSecurityAuthentication() {
        return gainProperty ("hadoop.security.authentication").getValue ();
    }

    public String gainHbaseMasterKerberosPrincipal() {
        return gainProperty ("hbase.master.kerberos.principal").getValue ();
    }

    public String gainHbaseRegionserverKerberosPrincipal() {
        return gainProperty ("hbase.regionserver.kerberos.principal").getValue ();
    }

    public String gainKerberosPrincipal() {
        return gainProperty ("kerberos.principal").getValue ();
    }

    public String gainKerberosKeytab() {
        return gainProperty ("kerberos.keytab").getValue ();
    }

    public String gainHBaseRootdir() {
        return gainProperty ("hbase.rootdir").getValue ();
    }

    public String gainZookeeperZnodeParent() {
        return gainProperty ("zookeeper.znode.parent").getValue ();
    }
}
