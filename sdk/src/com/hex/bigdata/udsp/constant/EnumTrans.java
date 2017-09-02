package com.hex.bigdata.udsp.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/20
 * TIME:9:07
 */
public class EnumTrans {
    /**
     * 错误码转换为ErrorCode枚举类型
     * @param errorCodeStr
     * @return
     */
    public static ErrorCode transErrorCode(String errorCodeStr) {
        if (StringUtils.isBlank(errorCodeStr)){
            return null;
        }
        for(ErrorCode errorCode:ErrorCode.values()){
            if (errorCodeStr.equals(errorCode.getValue())){
                return errorCode;
            }
        }
        return null;
    }

    /**
     * 任务运行状态码转化为StatusCode枚举类型
     * @param statusCodeStr
     * @return
     */
    public static StatusCode transStatusCode(String statusCodeStr) {
        if (StringUtils.isBlank(statusCodeStr)){
            return null;
        }
        for(StatusCode statusCode:StatusCode.values()){
            if (statusCodeStr.equals(statusCode.getValue())){
                return statusCode;
            }
        }
        return null;
    }

}
