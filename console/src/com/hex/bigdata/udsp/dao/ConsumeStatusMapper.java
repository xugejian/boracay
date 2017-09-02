package com.hex.bigdata.udsp.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncCacheMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Created by PC on 2017/5/26.
 */
@Repository
public class ConsumeStatusMapper extends SyncCacheMapper<Long> {
    /**
     * 消费状态允许查询的间隔时间（毫秒）
     */
    @Value("${consume.status.allow.interval.time.ms:1000}")
    private long intervalTimeMs;

    /**
     * 插入消费状态时间
     *
     * @param id
     * @return
     */
    public boolean insertConsumeStatusTime(String id) {
        return insertTimeout(id, System.currentTimeMillis(), intervalTimeMs);
    }

    /**
     * 检查消费状态时间是否允许放行
     *
     * @param id
     * @return true:放行,false:不放行
     */
    public boolean checkConsumeStatusTime(String id) {
        Long time = select(id);
        if (time != null) {
            return false;
        }
        return true;
    }
}
