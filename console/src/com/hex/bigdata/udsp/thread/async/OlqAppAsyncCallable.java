package com.hex.bigdata.udsp.thread.async;

import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.service.OlqSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.concurrent.Callable;

public class OlqAppAsyncCallable implements Callable<OLQResponse>{

    private String consumeId;
    private OlqSyncService olqSyncService;
    private Current mcCurrent;
    private String appId;
    private String sql;
    private String fileName;

    public OlqAppAsyncCallable(String consumeId, Current mcCurrent, String appId, String sql, String fileName) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.consumeId = consumeId;
        this.mcCurrent = mcCurrent;
        this.appId = appId;
        this.sql = sql;
        this.fileName = fileName;
    }

    @Override
    public OLQResponse call() throws Exception {
        return this.olqSyncService.asyncStart(this.consumeId, this.appId,this.sql, this.fileName, this.mcCurrent.getUserName());
    }
}
