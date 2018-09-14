package com.hex.bigdata.udsp.common.util;

import java.util.UUID;

/**
 * Created by JunjieM on 2018-9-7.
 */
public class UUIDUtil {

    public static String consumeId(String key) {
        if (key == null) key = "";
        return MD5Util.MD5(key + "|" + uuid());
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
