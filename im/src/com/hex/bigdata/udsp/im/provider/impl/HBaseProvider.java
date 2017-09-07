package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.HBaseWrapper;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
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
    public String outputSQL() {
        return null;
    }

    @Override
    public void outputData() {

    }

    @Override
    public boolean createTable(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public boolean createHiveTable(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public boolean dropTable(Metadata metadata) throws Exception {
        return false;
    }

    @Override
    public boolean dropHiveTable(Metadata metadata) throws Exception {
        return false;
    }
}
