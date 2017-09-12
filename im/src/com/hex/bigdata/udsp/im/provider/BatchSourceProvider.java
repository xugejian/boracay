package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface BatchSourceProvider extends SourceProvider, BatchProvider {

    String outputSQL(Model model);

    boolean createSourceEngineSchema(Model model) throws Exception;

    boolean dropSourceEngineSchema(Model model) throws Exception;

}
