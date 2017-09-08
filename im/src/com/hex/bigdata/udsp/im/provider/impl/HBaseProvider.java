package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.HBaseWrapper;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.HBaseProvider")
public class HBaseProvider extends HBaseWrapper implements RealtimeTargetProvider, BatchTargetProvider {
    private static Logger logger = LogManager.getLogger(HBaseProvider.class);

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        // HBase无法获取字段信息
        return null;
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }

    @Override
    public boolean createEngineSchema(Model model) throws Exception {
        return false;
    }

    @Override
    public boolean dropEngineSchema(Model model) throws Exception {
        return false;
    }
}
