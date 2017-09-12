package com.hex.bigdata.udsp.im.provider.impl.util.model;

public class JavaDataType {
	public static final String JAVA_DATA_TYPE_BIGINTEGER = "java.math.BigInteger";
	public static final String JAVA_DATA_TYPE_BYTE = "byte[]";
	public static final String JAVA_DATA_TYPE_BOOLEAN = "java.lang.Boolean";
	public static final String JAVA_DATA_TYPE_STRING = "java.lang.String";
	public static final String JAVA_DATA_TYPE_DATE = "java.sql.Date";
	public static final String JAVA_DATA_TYPE_BIGDECIMAL = "java.math.BigDecimal";
	public static final String JAVA_DATA_TYPE_DOUBLE = "java.lang.Double";
	public static final String JAVA_DATA_TYPE_LONG = "java.lang.Long";
	public static final String JAVA_DATA_TYPE_FLOAT = "java.lang.Float";
	public static final String JAVA_DATA_TYPE_INTEGER = "java.lang.Integer";
	public static final String JAVA_DATA_TYPE_SHORT = "java.lang.Short";
	public static final String JAVA_DATA_TYPE_TIMESTAMP = "java.sql.Timestamp";
	public static final String JAVA_DATA_TYPE_INT = "int";

	public static String getJavaTypeForImpalaType(String ImpalaType) {
		if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_BIGINT)) {
			return JAVA_DATA_TYPE_BIGINTEGER;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_BINARY)) {
			return JAVA_DATA_TYPE_BYTE;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_BOOLEAN)) {
			return JAVA_DATA_TYPE_BOOLEAN;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_STRING)
				|| ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_VARCHAR)
				|| ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_CHAR)) {
			return JAVA_DATA_TYPE_STRING;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_DATE)) {
			return JAVA_DATA_TYPE_DATE;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_DECIMAL)) {
			return JAVA_DATA_TYPE_BIGDECIMAL;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_DOUBLE)) {
			return JAVA_DATA_TYPE_DOUBLE;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_INT)) {
			return JAVA_DATA_TYPE_LONG;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_FLOAT)) {
			return JAVA_DATA_TYPE_FLOAT;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_SMALLINT)) {
			return JAVA_DATA_TYPE_INTEGER;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_TINYINT)) {
			return JAVA_DATA_TYPE_SHORT;
		} else if (ImpalaType.equals(ImpalaDataType.IMPALA_DATA_TYPE_TIMESTAMP)) {
			return JAVA_DATA_TYPE_TIMESTAMP;
		}
		return null;
	}

	public static String getJavaTypeForHiveType(String javaType) {
		if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_BIGINT)) {
			return JAVA_DATA_TYPE_LONG;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_BOOLEAN)) {
			return JAVA_DATA_TYPE_BOOLEAN;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_STRING)
				|| javaType.equals(HiveDataType.HIVE_DATA_TYPE_BINARY)
				|| javaType.equals(HiveDataType.HIVE_DATA_TYPE_ARRAY)
				|| javaType.equals(HiveDataType.HIVE_DATA_TYPE_MAP)
				|| javaType.equals(HiveDataType.HIVE_DATA_TYPE_STRUCT)) {
			return JAVA_DATA_TYPE_STRING;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_DECIMAL)) {
			return JAVA_DATA_TYPE_BIGDECIMAL;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_DOUBLE)
				|| javaType.equals(HiveDataType.HIVE_DATA_TYPE_FLOAT)) {
			return JAVA_DATA_TYPE_DOUBLE;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_INT)) {
			return JAVA_DATA_TYPE_INT;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_SMALLINT)) {
			return JAVA_DATA_TYPE_SHORT;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_TINYINT)) {
			return JAVA_DATA_TYPE_BYTE;
		} else if (javaType.equals(HiveDataType.HIVE_DATA_TYPE_TIMESTAMP)) {
			return JAVA_DATA_TYPE_TIMESTAMP;
		}
		return null;
	}
}
