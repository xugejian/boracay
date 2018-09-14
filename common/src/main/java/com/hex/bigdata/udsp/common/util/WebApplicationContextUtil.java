package com.hex.bigdata.udsp.common.util;

import com.hex.goframe.framework.MvcDispatcherServlet;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

/**
 * Created by JunjieM on 2018-9-11.
 */
public class WebApplicationContextUtil {
    private static WebApplicationContext wac = null;

    public WebApplicationContextUtil() {
    }

    public static WebApplicationContext getContext() {
        if (wac == null) {
            wac = MvcDispatcherServlet.getInstance().getWebApplicationContext();
        }
        return wac;
    }

    public static Object getBean(String beanId) {
        return getContext().getBean(beanId);
    }

    public static Object getBean(Class clazz) {
        return getContext().getBean(clazz);
    }

    public static Object getMessage(String property) {
        return getContext().getMessage(property, (Object[]) null, Locale.CHINESE);
    }
}

