package com.hex.bigdata.udsp.consumer.service;

import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.DateUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.mc.model.McConsumeData;
import com.hex.bigdata.udsp.mc.service.McConsumeDataService;
import com.hex.bigdata.udsp.rc.model.RcService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2019-11-4.
 */
@Service
public class ConsumeDataService {

    private static Logger logger = LogManager.getLogger (ConsumeDataService.class);

    private static final FastDateFormat format = FastDateFormat.getInstance ("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private McConsumeDataService mcConsumeDataService;

    public void writeDataToDb(Request request, Response response) {
        List<Map<String, String>> records = response.getRecords ();
        if (StatusCode.SUCCESS.getValue ().equals (response.getStatusCode ())
                && records != null && records.size () != 0) {
            McConsumeData mcConsumeData = new McConsumeData ();
            mcConsumeData.setUserName (request.getUdspUser ());
            mcConsumeData.setServiceName (request.getServiceName ());
            mcConsumeData.setAppType (request.getAppType ());
            mcConsumeData.setAppName (request.getAppName ());
            mcConsumeData.setSaveTime (format.format (new Date ()));
            mcConsumeData.setRequestContent (JSONUtil.parseObj2JSON (request));
            mcConsumeData.setResponseContent (JSONUtil.parseList2JSON (records));
            mcConsumeDataService.insert (mcConsumeData);
        }
    }

}
