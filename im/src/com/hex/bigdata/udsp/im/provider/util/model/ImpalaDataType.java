package com.hex.bigdata.udsp.im.provider.util.model;

/**
 * Impala数据类型
 * 
 * @author junjiem
 * 
 */
public class ImpalaDataType {

	public static final String IMPALA_DATA_TYPE_TINYINT = "TINYINT";
	public static final String IMPALA_DATA_TYPE_SMALLINT = "SMALLINT";
	public static final String IMPALA_DATA_TYPE_INT = "INT";
	public static final String IMPALA_DATA_TYPE_BIGINT = "BIGINT";
	public static final String IMPALA_DATA_TYPE_BOOLEAN = "BOOLEAN";
	public static final String IMPALA_DATA_TYPE_FLOAT = "FLOAT";
	public static final String IMPALA_DATA_TYPE_DOUBLE = "DOUBLE";
	public static final String IMPALA_DATA_TYPE_DECIMAL = "DECIMAL";
	public static final String IMPALA_DATA_TYPE_STRING = "STRING";
	public static final String IMPALA_DATA_TYPE_CHAR = "CHAR";
	public static final String IMPALA_DATA_TYPE_VARCHAR = "VARCHAR";
	public static final String IMPALA_DATA_TYPE_TIMESTAMP = "TIMESTAMP";
	public static final String IMPALA_DATA_TYPE_BINARY = "BINARY";
	public static final String IMPALA_DATA_TYPE_ARRAY = "ARRAY";
	public static final String IMPALA_DATA_TYPE_DATETIME = "DATETIME";
	public static final String IMPALA_DATA_TYPE_REAL = "REAL";
	public static final String IMPALA_DATA_TYPE_INTEGER = "INTEGER";
	public static final String IMPALA_DATA_TYPE_MAP = "MAP";
	public static final String IMPALA_DATA_TYPE_STRUCT = "STRUCT";
	public static final String IMPALA_DATA_TYPE_DATE = "DATE";

	public static String getVarchar(int maxLength) {
		return IMPALA_DATA_TYPE_VARCHAR + "(" + maxLength + ")";
	}

	public static String getChar(int maxLength) {
		return IMPALA_DATA_TYPE_CHAR + "(" + maxLength + ")";
	}

	public static String getDecimal(int precision) {
		return IMPALA_DATA_TYPE_DECIMAL + "(" + precision + ")";
	}

	public static String getDecimal(int precision, int scale) {
		return IMPALA_DATA_TYPE_DECIMAL + "(" + precision + "," + scale + ")";
	}

	public static String getImpalaTypeForJavaType(String javaType) {
		if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_BIGINTEGER)) {
			return IMPALA_DATA_TYPE_BIGINT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_BYTE)) {
			return IMPALA_DATA_TYPE_BINARY;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_BOOLEAN)) {
			return IMPALA_DATA_TYPE_BOOLEAN;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_STRING)) {
			return IMPALA_DATA_TYPE_STRING;
			// return IMPALA_DATA_TYPE_VARCHAR;
			// return IMPALA_DATA_TYPE_CHAR;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_DATE)) {
			return IMPALA_DATA_TYPE_DATE;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_BIGDECIMAL)) {
			return IMPALA_DATA_TYPE_DECIMAL;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_DOUBLE)) {
			return IMPALA_DATA_TYPE_DOUBLE;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_LONG)) {
			return IMPALA_DATA_TYPE_INT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_FLOAT)) {
			return IMPALA_DATA_TYPE_FLOAT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_INTEGER)) {
			return IMPALA_DATA_TYPE_SMALLINT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_SHORT)) {
			return IMPALA_DATA_TYPE_TINYINT;
		} else if (javaType.equals(JavaDataType.JAVA_DATA_TYPE_TIMESTAMP)) {
			return IMPALA_DATA_TYPE_TIMESTAMP;
		}
		return null;
	}

	public static String getDataType(String colType, String colLength) {
		String dataType = "";
		if (colLength == null || colLength.trim().equals("")) {
			if (colType == null || colType.trim().equals("")) {
				dataType = "STRING";
			} else {
				dataType = colType;
			}
		} else {
			colType = colType.trim();
			colLength = colLength.trim();
			if (colType.equals(IMPALA_DATA_TYPE_DECIMAL)
					|| colType.equals(IMPALA_DATA_TYPE_VARCHAR)
					|| colType.equals(IMPALA_DATA_TYPE_CHAR)) {
				dataType = colType + "(" + colLength + ")";
			} else {
				dataType = colType;
			}
		}
		return dataType;
	}

}
