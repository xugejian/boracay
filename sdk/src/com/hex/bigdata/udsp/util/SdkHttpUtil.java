package com.hex.bigdata.udsp.util;

import com.hex.bigdata.udsp.common.api.model.BaseRequest;
import com.hex.bigdata.udsp.common.util.JSONUtil;

import java.nio.charset.Charset;

public class SdkHttpUtil {

    /**
     * 调用UDSP
     *
     * @param request
     * @param url
     */
    public static <T> T requestUdsp(BaseRequest request, String url, Class<T> clazz) {
        return requestUdsp (request, url, clazz, Charset.forName ("UTF-8"));
    }

    /**
     * 调用UDSP
     *
     * @param request
     * @param url
     */
    public static <T> T requestUdsp(BaseRequest request, String url, Class<T> clazz, Charset charset) {
        return HttpUtils.requestPost (url, JSONUtil.parseObj2JSON (request), null, clazz, charset, null, null);
    }
}
