package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.MD5Util;
import com.hex.bigdata.udsp.consumer.dao.CacheMapper;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2019-8-28.
 */
@Service
public class CacheService {
    private static Logger logger = LogManager.getLogger (CacheService.class);

    @Autowired
    private CacheMapper cacheMapper;

    public Response select(Request request) {
        return cacheMapper.select (getId (request));
    }

    public boolean insert(Request request, Response response, long timeout){
        return cacheMapper.insertTimeout (getId (request), response, timeout * 1000);
    }

    private String getId(Request request) {
        Map<String, Object> map = new HashMap<> ();
        map.put ("appType", request.getAppType ());
        map.put ("appId", request.getAppId ());
        map.put ("type", request.getType ());
        map.put ("entity", request.getEntity ());
        map.put ("page", request.getPage ());
        map.put ("sql", request.getSql ());
        map.put ("data", request.getData ());
        return MD5Util.MD5_16 (JSONUtil.parseMap2JSON (map));
    }

}
