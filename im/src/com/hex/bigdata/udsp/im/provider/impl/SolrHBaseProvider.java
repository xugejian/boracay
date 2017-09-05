package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.model.Column;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrHBaseProvider implements RealtimeTargetProvider, BatchTargetProvider {
    @Override
    public void create() {

    }

    @Override
    public void drop() {

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
