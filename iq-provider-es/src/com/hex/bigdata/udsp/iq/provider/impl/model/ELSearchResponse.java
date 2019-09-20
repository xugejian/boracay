package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class ELSearchResponse {
    /**
     * 请求耗时（单位毫秒）
     */
    private long took;

    /**
     * 请求是否超时，true为超时；false为没有超时
     */
    private boolean timed_out;

    /**
     * 分片计算信息
     */
    private JSONObject _shards;

    /**
     * 查询记录信息
     */
    private ELOuterHits hits;

    public long getTook() {
        return took;
    }

    public void setTook(long took) {
        this.took = took;
    }

    public boolean isTimed_out() {
        return timed_out;
    }

    public void setTimed_out(boolean timed_out) {
        this.timed_out = timed_out;
    }

    @JSONField(name = "_shards")
    public JSONObject get_shards() {
        return _shards;
    }

    @JSONField(name = "_shards")
    public void set_shards(JSONObject _shards) {
        this._shards = _shards;
    }

    public ELOuterHits getHits() {
        return hits;
    }

    public void setHits(ELOuterHits hits) {
        this.hits = hits;
    }
}
