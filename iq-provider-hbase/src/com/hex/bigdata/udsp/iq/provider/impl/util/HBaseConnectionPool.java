package com.hex.bigdata.udsp.iq.provider.impl.util;

import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseDatasource;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2019-7-4.
 */
public class HBaseConnectionPool {
    static {
        // 解决winutils.exe不存在的问题
        try {
            File workaround = new File(".");
            System.getProperties().put("hadoop.home.dir",
                    workaround.getAbsolutePath());
            new File("./bin").mkdirs();
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Connection> pool;

    public static Connection getConnection(HBaseDatasource datasource) {
        String dsId = datasource.getId ();
        if (pool == null) {
            pool = new HashMap<> ();
        }
        Connection conn = pool.remove (dsId);
        if (conn == null) {
            Configuration conf = HBaseConfiguration.create ();
            for (Property property : datasource.getProperties ()) {
                conf.set (property.getName (), property.getValue ());
            }
            conf.set ("hbase.zookeeper.quorum", datasource.gainZkQuorum ());
            conf.set ("hbase.zookeeper.property.clientPort", datasource.gainZkPort ());
            if (StringUtils.isNotBlank (datasource.gainRpcTimeout ())) {
                conf.set ("hbase.rpc.timeout", datasource.gainRpcTimeout ());
            }
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
            try {
                conn = ConnectionFactory.createConnection (conf);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        if (conn != null && !conn.isClosed () && !conn.isAborted ()) {
            pool.put (dsId, conn);
        }
        return conn;
    }
}
