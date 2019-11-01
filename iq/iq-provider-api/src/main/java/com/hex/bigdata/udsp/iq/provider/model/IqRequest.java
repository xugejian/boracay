package com.hex.bigdata.udsp.iq.provider.model;

/**
 * Created by junjiem on 2017-3-2.
 */
public class IqRequest {
    private Application application;

    public IqRequest() {
    }

    public IqRequest(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        if (application == null)
            throw new IllegalArgumentException("application不能为空");
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
