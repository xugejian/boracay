package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface TargetProvider extends Provider {

    List<MetadataCol> columnInfo(Metadata metadata);

    boolean create(Metadata metadata) throws Exception;

    boolean drop(Metadata metadata) throws Exception;
}
