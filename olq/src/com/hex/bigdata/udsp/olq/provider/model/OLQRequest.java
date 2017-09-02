package com.hex.bigdata.udsp.olq.provider.model;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.olq.model.OLQQuerySql;

/**
 * Created by junjiem on 2017-2-17.
 */
public class OLQRequest {
    /**
     * 数据源信息
     */
    private Datasource datasource;

    /**
     * 查询SQL对象
     */
    private OLQQuerySql olqQuerySql;

    public OLQRequest() {
    }

    public OLQRequest(Datasource datasource, OLQQuerySql olqQuerySql) {
        this.datasource = datasource;
        this.olqQuerySql = olqQuerySql;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public OLQQuerySql getOlqQuerySql() {
        return olqQuerySql;
    }

    public void setOlqQuerySql(OLQQuerySql olqQuerySql) {
        this.olqQuerySql = olqQuerySql;
    }
}
