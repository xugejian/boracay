package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface SourceProvider extends Provider {

    List<MetadataCol> columnInfo(Model model);

}
