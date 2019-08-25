package com.hex.bigdata.udsp.iq.provider.model.dsl;

import com.hex.bigdata.udsp.dsl.model.Component;
import com.hex.bigdata.udsp.iq.provider.model.Metadata;

public class IqDslRequest {

    private Metadata metadata;
    private Component component;

    public IqDslRequest() {
    }

    public IqDslRequest(Metadata metadata, Component component) {
        this.component = component;
        this.metadata = metadata;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
