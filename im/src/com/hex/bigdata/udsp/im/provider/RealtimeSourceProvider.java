package com.hex.bigdata.udsp.im.provider;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface RealtimeSourceProvider extends SourceProvider {

    List<Map<String, String>> inputData();

}
