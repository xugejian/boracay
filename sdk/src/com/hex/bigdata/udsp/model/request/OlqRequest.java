package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.common.api.model.BaseRequest;
import com.hex.bigdata.udsp.common.api.model.Page;

@Deprecated
public class OlqRequest extends BaseRequest {

    private Page page;
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
