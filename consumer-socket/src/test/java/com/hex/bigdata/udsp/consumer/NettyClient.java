package com.hex.bigdata.udsp.consumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.*;

public class NettyClient implements Client {
    /**
     * 共享定时器
     **/
    public static final Timer timer = new HashedWheelTimer();
    public static final int DEFAULT_HEARTBEAT_PERIOD = 3000;
    private Channel ch;
    private RemotingUrl url;
    private NettyClientInitializer init;

    public NettyClient(RemotingUrl url, Channel ch, NettyClientInitializer init) {
        this.ch = ch;
        this.url = url;
        this.init = init;
    }

    @Override
    public void startHeartBeat() throws Exception {
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                try {
                    // 发送心跳请求
                } catch (Throwable t) {
                    // t.printStackTrace();
                } finally {
                    timer.newTimeout(this, DEFAULT_HEARTBEAT_PERIOD, TimeUnit.SECONDS);
                }
            }
        }, DEFAULT_HEARTBEAT_PERIOD, TimeUnit.SECONDS);
    }

    @Override
    public boolean isConnected() {
        return ch.isActive();
    }

    @Override
    public void close() {
        ch.close();
    }

    @Override
    public void sendRequest(ByteBuf msg) throws Exception {
        ch.writeAndFlush(msg);
    }

    @Override
    public String send(String msg) throws Exception {
        byte[] reqByte = msg.getBytes(CharsetUtil.UTF_8);
        ByteBuf buf = Unpooled.directBuffer(reqByte.length);
        buf.writeBytes(reqByte);
        init.setLathc(new CountDownLatch(1));
        ch.writeAndFlush(buf); // 发送请求
        return init.getResult(); // 获取响应
    }

}