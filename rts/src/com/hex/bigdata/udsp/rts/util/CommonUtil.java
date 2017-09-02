package com.hex.bigdata.udsp.rts.util;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
import com.hex.bigdata.udsp.rts.provider.model.Column;
import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tomnic on 2017/3/6.
 */
public class CommonUtil {
    /**
     * 获取生产者配置参数
     * 生产者配置参数继承 数据源配置参数
     * @param producerProperties
     * @param dsProperties
     * @return
     */
    public static List<ComProperties> getProducerProperties(List<ComProperties> producerProperties, List<ComProperties> dsProperties){
        Map<String,ComProperties> resultMap=new HashedMap();
        for (ComProperties item:dsProperties){
            resultMap.put(item.getName(),item);
        }
        for (ComProperties item:producerProperties){
            resultMap.put(item.getName(),item);
        }
        return new ArrayList(resultMap.values());
    }

    /**
     *  RtsMatedataCol转 RtsMatedataCol
     * @param rtsMatedataCols
     * @return
     */
    public static List<Column> getColumns(List<RtsMatedataCol> rtsMatedataCols) {
        List<Column> columns=new ArrayList<>();
        for (RtsMatedataCol item:rtsMatedataCols){
            Column column=new Column();
            column.setName(item.getName());
            column.setDescribe(item.getDescribe());
            column.setSeq(item.getSeq());
        }
        return columns;
    }
}
