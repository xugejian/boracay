package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.im.provider.RealtimeSourceProvider;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-4.
 */
public class KafkaProvider implements RealtimeSourceProvider {

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        return null;
    }

    @Override
    public List<Map<String, String>> inputData() {
        return null;
    }

}
