package com.hex.bigdata.udsp.im.provider.engine.realtime;

/**
 * Created by JunjieM on 2017-9-4.
 */
public interface RealtimeEngine {

    // 匹配更新
    void matchingUpdate();

    // 更新、插入（匹配的更新，不匹配的增量插入）
    void updateInsert();

    // 增量插入
    void insertInto();

}
