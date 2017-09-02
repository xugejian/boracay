package com.hex.bigdata.udsp.common.util;

import com.hex.goframe.util.shell.StreamGobbler;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellUtil {
    public static final String SHELL_RESULT = "SHELL_RESULT";
    public static final String SHELL_ERR_RESULT = "SHELL_ERR_RESULT";
    public static final String SHELL_INF_RESULT = "SHELL_INF_RESULT";
    public static final String SHELL_PID = "SHELL_PID";
    public static final String INVOKE_STATUS = "INVOKE_STATUS";
    private static Logger logger = LoggerFactory.getLogger(ShellUtil.class);

    public ShellUtil() {
    }

    public static Map exec(String shell, String charset) {
        HashMap retMap = new HashMap();

        try {
            Process e = null;
            e = Runtime.getRuntime().exec(shell);
            retMap.put("SHELL_PID", e.toString());
            logger.info("shell pid: " + e.toString());
            if(e != null) {
                StreamGobbler errorStream = new StreamGobbler(e.getErrorStream(), charset);
                errorStream.start();
                StreamGobbler inputStream = new StreamGobbler(e.getInputStream(), charset);
                inputStream.start();
                int exitCode = e.waitFor();
                logger.info("返回码：" + exitCode);
                logger.info("错误流：" + errorStream.getContent());
                logger.info("正确流：" + inputStream.getContent());
                retMap.put("SHELL_RESULT", String.valueOf(exitCode));
                retMap.put("SHELL_ERR_RESULT", errorStream.getContent());
                retMap.put("SHELL_INF_RESULT", inputStream.getContent());
                if("0".equals(String.valueOf(exitCode))) {
                    retMap.put("INVOKE_STATUS", Boolean.valueOf(true));
                } else {
                    retMap.put("INVOKE_STATUS", Boolean.valueOf(false));
                }
            }
        } catch (Exception var7) {
            // var7.printStackTrace();
            retMap.put("INVOKE_STATUS", Boolean.valueOf(false));
        }

        return retMap;
    }

    public static Map exec(String shell) {
        return exec(shell, "UTF-8");
    }
}
