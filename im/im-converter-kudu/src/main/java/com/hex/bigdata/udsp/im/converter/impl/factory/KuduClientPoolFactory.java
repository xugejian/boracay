package com.hex.bigdata.udsp.im.converter.impl.factory;

import com.hex.bigdata.udsp.im.converter.impl.model.KuduDatasource;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;

import java.util.List;

/**
 * Created by PC on 2018/2/26.
 */
public class KuduClientPoolFactory {
    private GenericObjectPool pool;

    public KuduClientPoolFactory(GenericObjectPool.Config config, KuduDatasource datasource) {
        KuduClientFactory factory = new KuduClientFactory(datasource);
        pool = new GenericObjectPool(factory, config);
    }

    public KuduClient getClient() throws Exception {
        return (KuduClient) pool.borrowObject();
    }

    public void releaseClient(KuduClient client) {
        try {
            pool.returnObject(client);
        } catch (Exception e) {
            if (client != null) {
                try {
                    client.shutdown();
                } catch (KuduException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void closePool() {
        if (pool != null) {
            try {
                pool.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class KuduClientFactory extends BasePoolableObjectFactory {

    private KuduClient.KuduClientBuilder clientBuilder;

    private List<String> masterAddresses;
    private Integer bossCount;
    private Long defaultAdminOperationTimeoutMs;
    private Long defaultOperationTimeoutMs;
    private Long defaultSocketReadTimeoutMs;
    private boolean disableStatistics;
    private Integer workerCount;

    public KuduClientFactory(KuduDatasource datasource) {
        masterAddresses = datasource.gainMasterAddresses();
        bossCount = datasource.gainBossCount();
        clientBuilder = new KuduClient.KuduClientBuilder(masterAddresses);
        if (bossCount != null) {
            clientBuilder = clientBuilder.bossCount (bossCount);
        }
        defaultAdminOperationTimeoutMs = datasource.gainDefaultAdminOperationTimeoutMs();
        if (defaultAdminOperationTimeoutMs != null) {
            clientBuilder = clientBuilder.defaultAdminOperationTimeoutMs (defaultAdminOperationTimeoutMs);
        }
        defaultOperationTimeoutMs = datasource.gainDefaultOperationTimeoutMs();
        if (defaultOperationTimeoutMs != null) {
            clientBuilder = clientBuilder.defaultOperationTimeoutMs (defaultOperationTimeoutMs);
        }
        defaultSocketReadTimeoutMs = datasource.gainDefaultSocketReadTimeoutMs();
        if (defaultSocketReadTimeoutMs != null) {
            clientBuilder = clientBuilder.defaultSocketReadTimeoutMs (defaultSocketReadTimeoutMs);
        }
        disableStatistics = datasource.gainDisableStatistics();
        if (disableStatistics) {
            clientBuilder = clientBuilder.disableStatistics ();
        }
        workerCount = datasource.gainWorkerCount();
        if (workerCount != null) {
            clientBuilder = clientBuilder.workerCount (workerCount);
        }
    }

    @Override
    public Object makeObject() throws Exception {
        return clientBuilder.build();
    }

    @Override
    public void destroyObject(Object obj) throws Exception {
        if (obj instanceof KuduClient) {
            ((KuduClient) obj).shutdown();
        }
    }

    @Override
    public boolean validateObject(Object obj) {
        if (obj == null) {
            return false;
        }
        return true;
    }
}
