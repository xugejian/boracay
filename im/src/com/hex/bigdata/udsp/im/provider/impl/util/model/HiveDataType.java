package com.hex.bigdata.udsp.im.provider.impl.util.model;

public class HiveDataType {

	public static final String HIVE_DATA_TYPE_TINYINT = "TINYINT";
	public static final String HIVE_DATA_TYPE_SMALLINT = "SMALLINT";
	public static final String HIVE_DATA_TYPE_INT = "INT";
	public static final String HIVE_DATA_TYPE_BIGINT = "BIGINT";
	public static final String HIVE_DATA_TYPE_FLOAT = "FLOAT";
	public static final String HIVE_DATA_TYPE_DOUBLE = "DOUBLE";
	public static final String HIVE_DATA_TYPE_DECIMAL = "DECIMAL";
	public static final String HIVE_DATA_TYPE_BOOLEAN = "BOOLEAN";
	public static final String HIVE_DATA_TYPE_STRING = "STRING";
	public static final String HIVE_DATA_TYPE_TIMESTAMP = "TIMESTAMP";
	public static final String HIVE_DATA_TYPE_BINARY = "BINARY";
	public static final String HIVE_DATA_TYPE_ARRAY = "ARRAY";
	public static final String HIVE_DATA_TYPE_MAP = "MAP";
	public static final String HIVE_DATA_TYPE_STRUCT = "STRUCT";

	public static String getDecimal(int precision) {
		return HIVE_DATA_TYPE_DECIMAL + "(" + precision + ")";
	}

	public static String getDecimal(int precision, int scale) {
		return HIVE_DATA_TYPE_DECIMAL + "(" + precision + "," + scale + ")";
	}

	public static String getHiveTypeForJavaType(String javaType) {
		if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_LONG)) {
			return HIVE_DATA_TYPE_BIGINT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_BOOLEAN)) {
			return HIVE_DATA_TYPE_BOOLEAN;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_STRING)) {
			return HIVE_DATA_TYPE_STRING;
			//return HIVE_DATA_TYPE_BINARY;
			//return HIVE_DATA_TYPE_ARRAY;
			//return HIVE_DATA_TYPE_MAP;
			//return HIVE_DATA_TYPE_STRUCT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_BIGDECIMAL)) {
			return HIVE_DATA_TYPE_DECIMAL;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_DOUBLE)) {
			return HIVE_DATA_TYPE_DOUBLE;
			//return HIVE_DATA_TYPE_FLOAT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_INT)) {
			return HIVE_DATA_TYPE_INT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_SHORT)) {
			return HIVE_DATA_TYPE_SMALLINT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_BYTE)) {
			return HIVE_DATA_TYPE_TINYINT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_TIMESTAMP)) {
			return HIVE_DATA_TYPE_TIMESTAMP;
		}
		return null;
	}

}
