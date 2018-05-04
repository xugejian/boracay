package com.hex.bigdata.udsp.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by PC on 2017/5/26.
 */
public class MD5Util {
    public static String MD5_32(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String MD5_16(String str) {
        return DigestUtils.md5Hex(str).substring(8, 24);
    }
}
