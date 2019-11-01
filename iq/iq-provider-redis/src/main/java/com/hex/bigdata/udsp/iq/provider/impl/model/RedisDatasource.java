package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.iq.provider.model.IqDatasource;
import org.apache.commons.lang.StringUtils;

/**
 * Created by PC on 2017/6/28.
 */
public class RedisDatasource extends IqDatasource {

    public RedisDatasource(Datasource datasource) {
        super (datasource);
    }

    public String gainIp() {
        String value = gainProperty ("redis.connection.ip").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("redis.connection.ip不能为空");
        }
        return value;
    }


    public int gainPort() {
        String value = gainProperty ("redis.connection.port").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("redis.connection.port不能为空");
        }
        return Integer.valueOf (value);
    }

    public String gainUserName() {
        return gainProperty ("redis.connection.user").getValue ();

    }

    public int gainMaxIdle() {
        String value = gainProperty ("redis.max.idle").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("redis.max.idle不能为空");
        }
        return Integer.valueOf (value);
    }


    public int gainMaxWait() {
        String value = gainProperty ("redis.max.wait").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("redis.max.wait不能为空");
        }
        return Integer.valueOf (value);
    }

    public int gainTimeOut() {
        String value = gainProperty ("redis.max.timeOut").getValue ();
        if (StringUtils.isBlank (value)) {
            return 600000;
        }
        return Integer.valueOf (value);
    }

    public boolean isTestOnBrrow() {
        String value = gainProperty ("redis.test.on.brrow").getValue ();
        if (StringUtils.isBlank (value)) {
            return false;
        }
        return Boolean.valueOf (value);
    }

    public int gainMaxTotal() {
        String value = gainProperty ("redis.max.total").getValue ();
        if (StringUtils.isBlank (value)) {
            return 10;
        }
        return Integer.valueOf (value);
    }

    public String gainPassword() {
        return gainProperty ("redis.connection.password").getValue ();
    }

    public String gainSeprator() {
        String value = gainProperty ("redis.seprator").getValue ();
        if (StringUtils.isBlank (value)) {
            value = "\\007";
        }
        return value;
    }
}
