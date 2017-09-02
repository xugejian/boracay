package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/6/28.
 */
public class RedisDataSource extends Datasource {
    private String ip;
    private int port;
    private String userName;
    private String password;
    private int max_idle;
    private int max_total;
    private int max_wait;
    private int timeOut;
    private boolean test_on_brrow;
    private String seprator = "\\007";
    private int maxNum = 65535;

    public RedisDataSource(List<Property> properties) {
        super(properties);
    }

    public RedisDataSource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public RedisDataSource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getIp() {
        String value = getProperty("redis.connection.ip").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.connection.ip不能为空");
        return value;
    }


    public int getPort() {
        String value = getProperty("redis.connection.port").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.connection.port不能为空");
        return Integer.valueOf(value);
    }

    public String getUserName() {
        String value = getProperty("redis.connection.user").getValue();
        if (StringUtils.isBlank(value))
            return null;
        return value;
    }

    public int getMax_idle() {
        String value = getProperty("redis.max.idle").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.max.idle不能为空");
        return Integer.valueOf(value);
    }


    public int getMax_wait() {
        String value = getProperty("redis.max.wait").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.max.wait不能为空");
        return Integer.valueOf(value);
    }

    public int getTimeOut() {
        String value = getProperty("redis.max.timeOut").getValue();
        if (StringUtils.isBlank(value))
            return 600000;
        return Integer.valueOf(value);
    }

    public boolean isTest_on_brrow() {
        String value = getProperty("redis.test.on.brrow").getValue();
        if (StringUtils.isBlank(value))
            return false;
        return Boolean.valueOf(value);
    }

    public int getMax_total() {
        String value = getProperty("redis.max.total").getValue();
        if (StringUtils.isBlank(value))
            return 10;
        return Integer.valueOf(value);
    }

    public String getPassword() {
        String value = getProperty("redis.connection.password").getValue();
        if (StringUtils.isBlank(value))
            return null;
        return value;
    }

    public String getSeprator(){
        String value = getProperty("redis.seprator").getValue();
        if (StringUtils.isNotBlank(value))
            seprator = value;
        return seprator;
    }

    public int getMaxNum() {
        String value = getProperty("redis.max.data.size").getValue();
        if (StringUtils.isNotBlank(value)) {
            try {
                maxNum = Integer.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("redis.max.data.size为一整数");
            }
        }
        return maxNum;
    }
}
