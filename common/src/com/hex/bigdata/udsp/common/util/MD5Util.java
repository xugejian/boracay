package com.hex.bigdata.udsp.common.util;

import com.hex.goframe.util.Util;

/**
 * Created by PC on 2017/5/26.
 */
public class MD5Util {
    public static String MD5_32(String str) {
        return Util.MD5(str);
    }

    public static String MD5_16(String str) {
        return Util.MD5(str).substring(8, 24);
    }
}
