package com.hex.bigdata.udsp.model.request;

/**
 * 实时流消费者请求
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/16
 * TIME:9:10
 */
public class RtsConsumerRequest extends UdspRequest {

    /**
     * 单位毫秒ms
     * 超时时间
     */
    private long timeout;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

}
