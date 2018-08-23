package com.hex.bigdata.udsp.rts.executor.model;

/**
 * Created by junjiem on 2017-2-20.
 */
public class ConsumerRequest {

    private Application application;
    private long timeout;

    public ConsumerRequest(Application application, long timeout) {
        this.application = application;
        this.timeout = timeout;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
