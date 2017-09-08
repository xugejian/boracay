package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Metadata;

/**
 * Created by JunjieM on 2017-9-8.
 */
public interface BatchProvider extends Provider {

    boolean createEngineSchema(Metadata metadata) throws Exception;

    boolean dropEngineSchema(Metadata metadata) throws Exception;

}
