package com.hex.bigdata.udsp.olq.provider.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2018/1/11.
 */
public class JdbcDatasource extends Datasource {

    public JdbcDatasource(List<Property> properties) {
        super (properties);
    }

    public JdbcDatasource(Map<String, Property> propertieMap) {
        super (propertieMap);
    }

    public JdbcDatasource(Datasource datasource) {
        super (datasource);
    }

    public String gainDriverClass() {
        String value = gainProperty ("driver.class").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("driver.class不能为空");
        }
        return value;
    }

    public String gainJdbcUrl() {
        String value = gainProperty ("jdbc.url").getValue ();
        if (StringUtils.isBlank (value)) {
            throw new IllegalArgumentException ("jdbc.url不能为空");
        }
        return value;
    }

    public String gainUsername() {
        return gainProperty ("username").getValue ();
    }

    public String gainPassword() {
        return gainProperty ("password").getValue ();
    }

    public String gainInitialSize() {
        return gainProperty ("initial.size").getValue ();
    }

    public String gainMinIdle() {
        return gainProperty ("min.idle").getValue ();
    }

    public String gainMaxIdle() {
        return gainProperty ("max.idle").getValue ();
    }

    public String gainMaxWait() {
        return gainProperty ("max.wait").getValue ();
    }

    public String gainMaxActive() {
        return gainProperty ("max.active").getValue ();
    }

    @Deprecated
    public String gainValidationQueryTimeout() {
        return gainProperty ("validation.query.timeout").getValue ();
    }

    public String gainTimeBetweenEvictionRunsMillis() {
        return gainProperty ("time.between.eviction.runs.millis").getValue ();
    }

    public String gainMinEvictableIdleTimeMillis() {
        return gainProperty ("min.evictable.idle.time.millis").getValue ();
    }

    public String gainValidationQuery() {
        return gainProperty ("validation.query").getValue ();
    }

    public String gainTestWhileIdle() {
        return gainProperty ("test.while.idle").getValue ();
    }

    public String gainTestOnBorrow() {
        return gainProperty ("test.on.borrow").getValue ();
    }

    public String gainTestOnReturn() {
        return gainProperty ("test.on.return").getValue ();
    }

//    public String gainPoolPreparedStatements() {
//        return gainProperty ("pool.prepared.statements").getValue ();
//    }

//    public String gainMaxOpenPreparedStatements() {
//        return gainProperty ("max.open.prepared.statements").getValue ();
//    }

    public String gainRemoveAbandoned() {
        return gainProperty ("remove.abandoned").getValue ();
    }

    public String gainRemoveAbandonedTimeout() {
        return gainProperty ("remove.abandoned.timeout").getValue ();
    }

    public int gainMaxSize() {
        String value = gainProperty ("max.data.size").getValue ();
        if (StringUtils.isBlank (value)) {
            value = "65535";
        }
        return Integer.valueOf (value);
    }

    public boolean gainMaxSizeAlarm() {
        String value = gainProperty ("max.data.size.alarm").getValue ();
        if (StringUtils.isBlank (value)) {
            return true;
        }
        return Boolean.valueOf (value);
    }
}
