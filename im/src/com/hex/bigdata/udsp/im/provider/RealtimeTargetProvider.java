package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface RealtimeTargetProvider extends TargetProvider, RealtimeProvider {

    void inputData(Model model);

}
