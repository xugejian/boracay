package com.hex.bigdata.udsp.jdbc.netty;

public interface Client {
    /**
     * 启动心跳检测
     *
     * @throws Exception
     */
    public void startHeartBeat() throws Exception;

    /**
     * 发送请求并获取响应
     *
     * @param msg
     * @return
     * @throws Exception
     */
    public String send(String msg) throws Exception;

    /**
     * 是否断开连接
     *
     * @return
     */
    public boolean isConnected();

    /**
     * 断开连接
     */
    public void close();

}