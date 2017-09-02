package com.hex.bigdata.udsp.rts.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.rts.provider.model.*;

/**
 * Created by junjiem on 2017-2-20.
 */
public interface Provider {
    void initProducerDataSource(ProducerDatasource producerDsConfig);

    void closeProducerDataSource(ProducerDatasource producerDsConfig);

    // 推
    ProducerResponse push(ProducerRequest producerRequest);

    void initConsumerDataSource(ConsumerDatasource consumerDsConfig);

    void closeConsumerDataSource(ConsumerDatasource consumerDsConfig);

    // 拉
    ConsumerResponse pull(ConsumerRequest consumerRequest);

    boolean testDatasource(Datasource datasource);
}
