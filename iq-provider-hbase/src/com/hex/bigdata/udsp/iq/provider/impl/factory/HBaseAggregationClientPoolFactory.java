package com.hex.bigdata.udsp.iq.provider.impl.factory;

import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseDatasource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * Created by PC on 2018/8/17.
 */
public class HBaseAggregationClientPoolFactory {
    private GenericObjectPool pool;

    public HBaseAggregationClientPoolFactory(GenericObjectPool.Config config, HBaseDatasource datasource) {
        HBaseAggregationClientFactory factory = new HBaseAggregationClientFactory (datasource);
        pool = new GenericObjectPool (factory, config);
    }

    public AggregationClient getAggregationClient() throws Exception {
        return (AggregationClient) pool.borrowObject ();
    }

    public void releaseAggregationClient(AggregationClient client) {
        try {
            pool.returnObject (client);
        } catch (Exception e) {
            if (client != null) {
                try {
                    client.close ();
                } catch (IOException e1) {
                    e.printStackTrace ();
                }
            }
        }
    }

    public void closePool() {
        if (pool != null) {
            try {
                pool.close ();
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
    }
}

class HBaseAggregationClientFactory extends BasePoolableObjectFactory {

    private Configuration conf;

    public HBaseAggregationClientFactory(HBaseDatasource datasource) {
        conf = HBaseConfiguration.create ();
        for (Property property : datasource.getProperties ()) {
            conf.set (property.getName (), property.getValue ());
        }
        conf.set ("hbase.zookeeper.quorum", datasource.gainZkQuorum ());
        conf.set ("hbase.zookeeper.property.clientPort", datasource.gainZkPort ());
        if (StringUtils.isNotBlank (datasource.gainRpcTimeout ())){
            conf.set ("hbase.rpc.timeout", datasource.gainRpcTimeout ());}
        if (StringUtils.isNotBlank (datasource.gainClientRetriesNumber ())) {
            conf.set ("hbase.client.retries.number", datasource.gainClientRetriesNumber ());
        }
        if (StringUtils.isNotBlank (datasource.gainClientPause ())) {
            conf.set ("hbase.client.pause", datasource.gainClientPause ());
        }
        if (StringUtils.isNotBlank (datasource.gainZkRecoveryRetry ())) {
            conf.set ("zookeeper.recovery.retry", datasource.gainZkRecoveryRetry ());
        }
        if (StringUtils.isNotBlank (datasource.gainZkRecoveryRetryIntervalmill ())) {
            conf.set ("zookeeper.recovery.retry.intervalmill", datasource.gainZkRecoveryRetryIntervalmill ());
        }
        if (StringUtils.isNotBlank (datasource.gainClientOperationTimeout ())) {
            conf.set ("hbase.client.operation.timeout", datasource.gainClientOperationTimeout ());
        }
//        if (StringUtils.isNotBlank(datasource.gainRegionserverLeasePeriod()))
//            conf.set("hbase.regionserver.lease.period", datasource.gainRegionserverLeasePeriod()); // 已被弃用
        if (StringUtils.isNotBlank (datasource.gainClientScannerTimeoutPeriod ())) {
            conf.set ("hbase.client.scanner.timeout.period", datasource.gainClientScannerTimeoutPeriod ());
        }
        if (StringUtils.isNotBlank (datasource.gainHBaseRootdir ())) {
            conf.set ("hbase.rootdir", datasource.gainHBaseRootdir ());
        }
        if (StringUtils.isNotBlank (datasource.gainZookeeperZnodeParent ())) {
            conf.set ("zookeeper.znode.parent", datasource.gainZookeeperZnodeParent ());
        }

        /*
        以下是HBase开启Kerberos认证后需要的配置
         */
        if (StringUtils.isNotBlank (datasource.gainKerberosPrincipal ())
                && StringUtils.isNotBlank (datasource.gainKerberosKeytab ())) {
            if (StringUtils.isNotBlank (datasource.gainHbaseSecurityAuthentication ())) {
                conf.set ("hbase.security.authentication", datasource.gainHbaseSecurityAuthentication ());
            }
            if (StringUtils.isNotBlank (datasource.gainHadoopSecurityAuthentication ())) {
                conf.set ("hadoop.security.authentication", datasource.gainHadoopSecurityAuthentication ());
            }
            if (StringUtils.isNotBlank (datasource.gainHbaseMasterKerberosPrincipal ())) {
                conf.set ("hbase.master.kerberos.principal", datasource.gainHbaseMasterKerberosPrincipal ());
            }
            if (StringUtils.isNotBlank (datasource.gainHbaseRegionserverKerberosPrincipal ())) {
                conf.set ("hbase.regionserver.kerberos.principal", datasource.gainHbaseRegionserverKerberosPrincipal ());
            }
            UserGroupInformation.setConfiguration (conf);
            try {
                UserGroupInformation.loginUserFromKeytab (datasource.gainKerberosPrincipal (), datasource.gainKerberosKeytab ());
            } catch (IOException e) {
                throw new RuntimeException (e);
            }
        }
    }

    @Override
    public Object makeObject() throws Exception {
        return new AggregationClient (conf);
    }

    @Override
    public void destroyObject(Object obj) throws Exception {
        if (obj instanceof AggregationClient) {
            ((AggregationClient) obj).close ();
        }
    }

    @Override
    public boolean validateObject(Object obj) {
        if (obj instanceof AggregationClient) {
            return false;
        }
        return false;
    }
}
