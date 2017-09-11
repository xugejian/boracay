package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;

/**
 * Created by JunjieM on 2017-9-8.
 */
public interface BatchProvider extends Provider {

    boolean createEngineSchema(Model model) throws Exception;

    boolean dropEngineSchema(Model model) throws Exception;

}
