package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JunjieM on 2017-9-6.
 */
public class JdbcDatasource extends Datasource {

    public JdbcDatasource(Datasource datasource) {
        super(datasource);
    }

    public String gainDriverClass() {
        String value = gainProperty("driver.class").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("driver.class不能为空");
        }
        return value;
    }

    public String gainJdbcUrl() {
        String value = gainProperty("jdbc.url").getValue();
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException ("jdbc.url不能为空");
        }
        return value;
    }

    public String gainUsername() {
        return gainProperty("username").getValue();
    }

    public String gainPassword() {
        return gainProperty("password").getValue();
    }

    public String gainInitialSize() {
        return gainProperty("initial.size").getValue();
    }

    public String gainMinIdle() {
        return gainProperty("min.idle").getValue();
    }

    public String gainMaxIdle() {
        return gainProperty("max.idle").getValue();
    }

    public String gainMaxWait() {
        return gainProperty("max.wait").getValue();
    }

    public String gainMaxActive() {
        return gainProperty("max.active").getValue();
    }

    public String gainValidationQueryTimeout() {
        return gainProperty("validation.query.timeout").getValue();
    }

    public String gainTimeBetweenEvictionRunsMillis() {
        return gainProperty("time.between.eviction.runs.millis").getValue();
    }

    public String gainMinEvictableIdleTimeMillis() {
        return gainProperty("min.evictable.idle.time.millis").getValue();
    }

    public String gainValidationQuery() {
        return gainProperty("validation.query").getValue();
    }

    public String gainTestWhileIdle() {
        return gainProperty("test.while.idle").getValue();
    }

    public String gainTestOnBorrow() {
        return gainProperty("test.on.borrow").getValue();
    }

    public String gainTestOnReturn() {
        return gainProperty("test.on.return").getValue();
    }

//    public String gainPoolPreparedStatements() {
//        return gainProperty("pool.prepared.statements").getValue();
//    }

//    public String gainMaxOpenPreparedStatements() {
//        return gainProperty("max.open.prepared.statements").getValue();
//    }

    public String gainRemoveAbandoned() {
        return gainProperty("remove.abandoned").getValue();
    }

    public String gainRemoveAbandonedTimeout() {
        return gainProperty("remove.abandoned.timeout").getValue();
    }

    public String gainRemarksReporting() {
        return gainProperty("remarks.reporting").getValue();
    }

    public String gainUserInformationSchema() {
        return gainProperty("user.information.schema").getValue();
    }

}
