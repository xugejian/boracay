package com.hex.bigdata.udsp.jdbc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    private CountDownLatch lathc = new CountDownLatch(1); // 闭锁

    private String result; // 结果

    /**
     * 获取响应结果
     *
     * @return
     */
    public String getResult() {
        if (lathc == null) {
            return null;
        }
        try {
            lathc.await(); // 开启等待会等待服务器返回结果之后再执行下面的代码
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public CountDownLatch getLathc() {
        return lathc;
    }

    /**
     * 重置同步锁
     *
     * @param lathc
     */
    public void setLathc(CountDownLatch lathc) {
        this.lathc = lathc;
    }

    /**
     * 建立连接时，发送消息
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("正在连接服务端... ");
    }

    /**
     * 收到消息时，发送信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        logger.debug("客户端接收信息:\n" + msg.toString(CharsetUtil.UTF_8));
//        result = msg.toString(CharsetUtil.UTF_8);
        byte[] reqByte = new byte[msg.readableBytes()];
        msg.readBytes(reqByte);
        result = new String(reqByte, CharsetUtil.UTF_8);
        if (lathc != null) {
            lathc.countDown();
        }
    }

    /**
     * 发生异常关闭链路
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("客户端连接关闭! ");
        ctx.close();
    }
}
