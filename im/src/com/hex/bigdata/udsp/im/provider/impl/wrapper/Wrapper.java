package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JunjieM on 2017-9-11.
 */
public abstract class Wrapper {
    protected static final String DATABASE_AND_TABLE_SEP = ".";
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

    protected String getSourceTableName(String dbName, String tbName, String id) {
        String tableName = HIVE_ENGINE_SOURCE_TABLE_PREFIX + id;
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_SOURCE_TABLE_PREFIX + dbName + HIVE_ENGINE_TABLE_SEP + tbName + HIVE_ENGINE_TABLE_SEP + id;
        } else if (StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_SOURCE_TABLE_PREFIX + tbName + HIVE_ENGINE_TABLE_SEP + id;
        }
        return tableName;
    }

    protected String getTargetTableName(String fullTbName, String id) {
        String[] strs = fullTbName.split(DATABASE_AND_TABLE_SEP);
        String dbName = null;
        String tbName = null;
        if (strs.length == 1) {
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
        } else if (strs.length == 2) {
            dbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[1];
        }
        String tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + id;
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + dbName + HIVE_ENGINE_TABLE_SEP + tbName + HIVE_ENGINE_TABLE_SEP + id;
        } else if (StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + tbName + HIVE_ENGINE_TABLE_SEP + id;
        }
        return tableName;
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
