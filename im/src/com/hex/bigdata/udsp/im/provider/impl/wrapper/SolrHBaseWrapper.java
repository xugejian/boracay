package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class SolrHBaseWrapper extends Wrapper implements BatchTargetProvider, RealtimeTargetProvider {

    private static Logger logger = LoggerFactory.getLogger(SolrHBaseWrapper.class);

}
