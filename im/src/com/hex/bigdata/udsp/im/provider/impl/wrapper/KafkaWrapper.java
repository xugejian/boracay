package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class KafkaWrapper extends Wrapper {
    private static Logger logger = LogManager.getLogger(KafkaWrapper.class);

    protected DataType getType(Object value) {
        DataType type = DataType.STRING;
        if (value instanceof Integer) {
            type = DataType.INT;
        } else if (value instanceof BigDecimal) {
            type = DataType.DECIMAL;
        } else if (value instanceof Long) {
            type = DataType.BIGINT;
        } else if (value instanceof BigInteger) {
            type = DataType.BIGINT;
        } else if (value instanceof Double) {
            type = DataType.DOUBLE;
        } else if (value instanceof Float) {
            type = DataType.FLOAT;
        } else if (value instanceof Short) {
            type = DataType.SMALLINT;
        } else if (value instanceof Boolean) {
            type = DataType.BOOLEAN;
        } else if (value instanceof Timestamp) {
            type = DataType.TIMESTAMP;
        }
        return type;
    }
}
