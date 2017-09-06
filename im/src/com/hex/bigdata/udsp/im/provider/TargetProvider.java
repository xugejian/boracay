package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface TargetProvider extends Provider {

    List<MetadataCol> columnInfo(Metadata metadata);

    void create();

    void drop();
}
