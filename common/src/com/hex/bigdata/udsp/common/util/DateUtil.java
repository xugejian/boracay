package com.hex.bigdata.udsp.common.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/8
 * TIME:11:00
 */
public class DateUtil {

    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 用给定的long值获取“yyyy-MM-dd HH:mm:ss”格式的时间字符串
     *
     * @param timestamp
     * @return
     */
    public static String getDateString(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        Date time = calendar.getTime();
        return format.format(time);
    }

}
