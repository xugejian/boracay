package com.hex.bigdata.udsp.thread.async;

import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.service.OlqSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.concurrent.Callable;

public class OlqAsyncCallable implements Callable<OLQResponse> {

    private String consumeId;
    private Current mcCurrent;
    private String dsId;
    private String sql;
    private Page page;
    private String fileName;

    private OlqSyncService olqSyncService;

    public OlqAsyncCallable(String consumeId, Current mcCurrent, String dsId, String sql, Page page, String fileName) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.consumeId = consumeId;
        this.mcCurrent = mcCurrent;
        this.dsId = dsId;
        this.sql = sql;
        this.page = page;
        this.fileName = fileName;
    }

    @Override
    public OLQResponse call() throws Exception {
        return  this.olqSyncService.asyncStart(this.consumeId, this.dsId, this.sql, this.page, this.fileName, this.mcCurrent.getUserName());
    }
}
