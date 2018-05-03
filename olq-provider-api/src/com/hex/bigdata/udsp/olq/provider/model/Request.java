package com.hex.bigdata.udsp.olq.provider.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;

/**
 * Created by junjiem on 2017-2-17.
 */
public class Request {
    private Datasource datasource;
    private String querySql;

    public Request() {
    }

    public Request(Datasource datasource, String querySql) {
        this.datasource = datasource;
        this.querySql = querySql;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }
}
