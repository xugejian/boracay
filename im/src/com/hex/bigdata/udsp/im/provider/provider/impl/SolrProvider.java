package com.hex.bigdata.udsp.im.provider.provider.impl;

import com.hex.bigdata.udsp.im.provider.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.model.Column;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrProvider implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    @Override
    public void create() {

    }

    @Override
    public void drop() {

    }

    @Override
    public String inputSQL() {
        return null;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }

    @Override
    public List<Column> columnInfo() {
        return null;
    }
}
