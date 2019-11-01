package com.hex.bigdata.udsp.consumer;

import java.util.List;

/**
 * 长连接客户端工厂接口类
 **/
public interface ClientFactory {
    /**
     * 获取或初始化客户端
     *
     * @param url
     * @return
     * @throws Exception
     */
    Client get(final RemotingUrl url) throws Exception;

    /**
     * 获取所有客户端
     *
     * @return
     * @throws Exception
     */
    List<Client> getAllClients() throws Exception;

    /**
     * 删除客户端
     *
     * @param url
     * @throws Exception
     */
    void remove(final RemotingUrl url) throws Exception;

}