package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;
import kafka.consumer.KafkaStream;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface RealtimeSourceProvider extends SourceProvider, RealtimeProvider {

    List<KafkaStream<byte[], byte[]>> outputData(Model model);

}
