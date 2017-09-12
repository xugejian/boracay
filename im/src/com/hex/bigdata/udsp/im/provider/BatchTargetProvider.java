package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface BatchTargetProvider extends TargetProvider, BatchProvider {

    String outputSQL();

    boolean createTargetEngineSchema(Model model) throws Exception;

    boolean dropTargetEngineSchema(Model model) throws Exception;
}
