package com.hex.bigdata.udsp.olq.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.olq.model.OLQQuerySql;
import com.hex.bigdata.udsp.olq.provider.model.OLQRequest;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponseFetch;

/**
 * Created by junjiem on 2017-2-15.
 */
public interface Provider {
    /**
     * 初始化
     *
     * @param datasource
     */
    void init(Datasource datasource);

    /**
     * 执行
     *
     * @param request
     * @return
     */
    OLQResponse execute(OLQRequest request);

    /**
     * 关闭
     *
     * @param datasource
     */
    void close(Datasource datasource);

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
    OLQResponseFetch executeFetch(OLQRequest request);

    /**
     * 获取分页SQL语句
     * @param sql
     * @param page
     * @return
     */
    OLQQuerySql getPageSql(String sql, Page page);
}
