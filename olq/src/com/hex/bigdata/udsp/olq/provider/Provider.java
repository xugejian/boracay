package com.hex.bigdata.udsp.olq.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.olq.provider.model.OLQRequest;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponseFetch;

/**
 * Created by junjiem on 2017-2-15.
 */
public interface Provider {

    /**
     * 执行
     *
     * @param consumeId
     * @param request
     * @return
     */
    OLQResponse execute(String consumeId, OLQRequest request);

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    boolean testDatasource(Datasource datasource);

    /**
     * 执行（结果集一批批抽取）
     *
     * @param request
     * @return
     */
    OLQResponseFetch executeFetch(String consumeId, OLQRequest request);
}
