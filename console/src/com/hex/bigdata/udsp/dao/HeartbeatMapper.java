package com.hex.bigdata.udsp.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncCacheMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.model.HeartbeatInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Created by PC on 2017/5/10.
 */
@Repository
public class HeartbeatMapper extends SyncCacheMapper<HeartbeatInfo> {

//    @Autowired
//    @Qualifier("ehCache")
//    private Cache<HeartbeatInfo> cache;
//
//    protected Cache<HeartbeatInfo> getCache() {
//        return this.cache;
//    }

}
