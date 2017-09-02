package com.hex.bigdata.udsp.iq.service;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.iq.dao.IqMetadataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 2017/5/17.
 */
@Service
public class IqDataResourceService {

    @Autowired
    private IqMetadataMapper iqMetadataMapper;

    public boolean checkDatasourceUsed(String pkId){
        return iqMetadataMapper.selectListByDsid(pkId).size() > 0;
    }

    /**
     * 检查数据源列中是否有被引用，有则返回状态和被引用的数据源名称
     * @param comDatasources
     * @return
     */
    public Map checkDatasourcesUsed(ComDatasource[] comDatasources){
        Map resultMap = new HashMap<String,String>(2);
        resultMap.put("result","false");
        for(ComDatasource datasource : comDatasources){
            if(iqMetadataMapper.selectListByDsid(datasource.getPkId()).size() > 0){
                resultMap.put("name",datasource.getName());
                resultMap.put("result","true");
                break;
            }
        }
        return resultMap;
    }
}
