package com.hex.bigdata.udsp.im.provider.engine.batch;

/**
 * Created by JunjieM on 2017-9-4.
 */
public interface BatchEngine {

    // 增量插入
    void insertInto();

    // 全量覆盖
    void insertOverwrite();

}
