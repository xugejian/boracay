package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.mc.dao.McConsumeDataMapper;
import com.hex.bigdata.udsp.mc.model.McConsumeData;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.goframe.service.BaseService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * 消费数据服务
 */
@Service
public class McConsumeDataService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger (McConsumeDataService.class);

    @Autowired
    private McConsumeDataMapper mcConsumeDataMapper;

    private static final FastDateFormat ymdFormat = FastDateFormat.getInstance ("yyyy-MM-dd");
    private static final FastDateFormat ymdhmsFormat = FastDateFormat.getInstance ("yyyy-MM-dd HH:mm:ss");

    /**
     * 保留最近N天的消费数据
     */
    @Value("${keep.consume.data.period:7}")
    private int keepConsumeDataPeriod;

    @Transactional
    public boolean insert(McConsumeData mcConsumeData) {
        return mcConsumeDataMapper.insert ( mcConsumeData);
    }

    /**
     * 清空过期的消费数据
     */
    public boolean cleanOutmodedConsumeData() {
        Calendar calendar = Calendar.getInstance ();
        calendar.add (Calendar.DATE, -1 * keepConsumeDataPeriod);
        String date = ymdhmsFormat.format (calendar.getTime ());
        logger.info ("清空" + date + "和其之前的消费日志记录！");
        return mcConsumeDataMapper.clean (date);
    }
}
