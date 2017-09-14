package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JunjieM on 2017-9-11.
 */
public abstract class Wrapper {
    protected static final String DATABASE_AND_TABLE_SEP = "\\.";
    protected static final String HIVE_ENGINE_DATABASE_NAME = "UDSP";
    protected static final String HIVE_ENGINE_TABLE_SEP = "_";
    protected static final String HIVE_ENGINE_SOURCE_TABLE_PREFIX = HIVE_ENGINE_DATABASE_NAME
            + ".E" + HIVE_ENGINE_TABLE_SEP + "S" + HIVE_ENGINE_TABLE_SEP;
    protected static final String HIVE_ENGINE_TARGET_TABLE_PREFIX = HIVE_ENGINE_DATABASE_NAME
            + ".E" + HIVE_ENGINE_TABLE_SEP + "T" + HIVE_ENGINE_TABLE_SEP;

    protected String getHiveEngineSourceTablePrefix(DataType type) {
        return HIVE_ENGINE_SOURCE_TABLE_PREFIX + type.getValue() + HIVE_ENGINE_TABLE_SEP;
    }

    protected String getHiveEngineTargetTablePrefix(DataType type) {
        return HIVE_ENGINE_TARGET_TABLE_PREFIX + type.getValue() + HIVE_ENGINE_TABLE_SEP;
    }

    protected String getSourceTableName(String id) {
        return HIVE_ENGINE_SOURCE_TABLE_PREFIX + id;
    }

    protected String getTargetTableName(String id) {
        return HIVE_ENGINE_TARGET_TABLE_PREFIX + id;
    }

    protected String getDataType(DataType type, String length) {
        String dataType = DataType.STRING.getValue();
        if (StringUtils.isBlank(length)) {
            dataType = type.getValue();
        } else {
            if (DataType.STRING == type || DataType.INT == type || DataType.SMALLINT == type
                    || DataType.BIGINT == type || DataType.BOOLEAN == type || DataType.DOUBLE == type
                    || DataType.FLOAT == type || DataType.TINYINT == type || DataType.TIMESTAMP == type) {
                dataType = type.getValue();
            } else if (DataType.CHAR == type || DataType.VARCHAR == type || DataType.DECIMAL == type) {
                dataType = type.getValue() + "(" + length + ")";
            }
        }
        return dataType;
    }
}
