package com.hex.bigdata.udsp.consumer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JunjieM on 2018-7-26.
 */
public class NettyMain {
    private static Logger logger = LoggerFactory.getLogger(NettyMain.class);

    public static void main(String[] args) throws Exception {
        RemotingUrl url = new RemotingUrl();
        url.setHost("localhost");
        url.setPort(9089);
        ClientFactory factory = new NettyClientFactory();
        Client client = factory.get(url);
        String req = "{\n" +
                "\"sql\":\"select * from T_GF_FUNCATION t\",\n" +
                "\"entity\":\"start\",\n" +
                "\"serviceName\":\"dev_oracle\",\n" +
                "\"token\":\"000000\",\n" +
                "\"type\":\"sync\",\n" +
                "\"udspUser\":\"admin\"\n" +
                "}";
        logger.info("request:\n" + req);
        if (client.isConnected()) {
            String rsp = client.send(req);
            logger.info("response1:\n" + rsp);
        }
        factory.remove(url);
        if (client.isConnected()) {
            String rsp = client.send(req);
            logger.info("response2:\n" + rsp);
        }
        factory.remove(url);
        logger.info("END");
    }
}
