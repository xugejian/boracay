
package com.hex.bigdata.udsp.jdbc;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;


/**
 * Column metadata.
 */
public class JdbcColumn {
  private final String columnName;
  private final String tableName;
  private final String tableCatalog;
  private final String type;
  private final String comment;
  private final int ordinalPos;

  JdbcColumn(String columnName, String tableName, String tableCatalog
          , String type, String comment, int ordinalPos) {
    this.columnName = columnName;
    this.tableName = tableName;
    this.tableCatalog = tableCatalog;
    this.type = type;
    this.comment = comment;
    this.ordinalPos = ordinalPos;
  }

  public String getColumnName() {
    return columnName;
  }

  public String getTableName() {
    return tableName;
  }

  public String getTableCatalog() {
    return tableCatalog;
  }

  public String getType() {
    return type;
  }

  static String columnClassName(int columnType, JdbcColumnAttributes columnAttributes)
      throws SQLException {
    // according to hiveTypeToSqlType possible options are:
    switch(columnType) {
      case Types.BOOLEAN:
        return Boolean.class.getName();
      case Types.CHAR:
      case Types.VARCHAR:
        return String.class.getName();
      case Types.TINYINT:
        return Byte.class.getName();
      case Types.SMALLINT:
        return Short.class.getName();
      case Types.INTEGER:
        return Integer.class.getName();
      case Types.BIGINT:
        return Long.class.getName();
      case Types.DATE:
        return Date.class.getName();
      case Types.FLOAT:
        return Float.class.getName();
      case Types.DOUBLE:
        return Double.class.getName();
      case  Types.TIMESTAMP:
        return Timestamp.class.getName();
      case Types.DECIMAL:
        return BigInteger.class.getName();
      case Types.BINARY:
        return byte[].class.getName();
      case Types.JAVA_OBJECT:
      case Types.ARRAY:
      case Types.STRUCT:
        return String.class.getName();
      default:
        throw new SQLException("Invalid column type: " + columnType);
    }
  }

  public static int hiveTypeToSqlType(String type) throws SQLException {
    if ("string".equalsIgnoreCase(type)) {
      return Types.VARCHAR;
    } else if ("varchar".equalsIgnoreCase(type)) {
      return Types.VARCHAR;
    } else if ("char".equalsIgnoreCase(type)) {
      return Types.CHAR;
    } else if ("float".equalsIgnoreCase(type)) {
      return Types.FLOAT;
    } else if ("double".equalsIgnoreCase(type)) {
      return Types.DOUBLE;
    } else if ("boolean".equalsIgnoreCase(type)) {
      return Types.BOOLEAN;
    } else if ("tinyint".equalsIgnoreCase(type)) {
      return Types.TINYINT;
    } else if ("smallint".equalsIgnoreCase(type)) {
      return Types.SMALLINT;
    } else if ("int".equalsIgnoreCase(type)) {
      return Types.INTEGER;
    } else if ("bigint".equalsIgnoreCase(type)) {
      return Types.BIGINT;
    } else if ("date".equalsIgnoreCase(type)) {
      return Types.DATE;
    } else if ("timestamp".equalsIgnoreCase(type)) {
      return Types.TIMESTAMP;
    } else if ("decimal".equalsIgnoreCase(type)) {
      return Types.DECIMAL;
    } else if ("binary".equalsIgnoreCase(type)) {
      return Types.BINARY;
    } else if ("map".equalsIgnoreCase(type)) {
      return Types.JAVA_OBJECT;
    } else if ("array".equalsIgnoreCase(type)) {
      return Types.ARRAY;
    } else if ("struct".equalsIgnoreCase(type)) {
      return Types.STRUCT;
    }
    throw new SQLException("Unrecognized column type: " + type);
  }

  static int columnDisplaySize(int columnType, JdbcColumnAttributes columnAttributes)
      throws SQLException {
    // according to hiveTypeToSqlType possible options are:
    switch(columnType) {
    case Types.BOOLEAN:
      return columnPrecision(columnType, columnAttributes);
    case Types.CHAR:
    case Types.VARCHAR:
      return columnPrecision(columnType, columnAttributes);
    case Types.BINARY:
      return Integer.MAX_VALUE; // hive has no max limit for binary
    case Types.TINYINT:
    case Types.SMALLINT:
    case Types.INTEGER:
    case Types.BIGINT:
      return columnPrecision(columnType, columnAttributes) + 1; // allow +/-
    case Types.DATE:
      return 10;
    case Types.TIMESTAMP:
      return columnPrecision(columnType, columnAttributes);

    // see http://download.oracle.com/javase/6/docs/api/constant-values.html#java.lang.Float.MAX_EXPONENT
    case Types.FLOAT:
      return 24; // e.g. -(17#).e-###
    // see http://download.oracle.com/javase/6/docs/api/constant-values.html#java.lang.Double.MAX_EXPONENT
    case Types.DOUBLE:
      return 25; // e.g. -(17#).e-####
    case Types.DECIMAL:
      return columnPrecision(columnType, columnAttributes) + 2;  // '-' sign and '.'
    case Types.JAVA_OBJECT:
    case Types.ARRAY:
    case Types.STRUCT:
      return Integer.MAX_VALUE;
    default:
      throw new SQLException("Invalid column type: " + columnType);
    }
  }

  static int columnPrecision(int columnType, JdbcColumnAttributes columnAttributes)
      throws SQLException {
    // according to hiveTypeToSqlType possible options are:
    switch(columnType) {
    case Types.BOOLEAN:
      return 1;
    case Types.CHAR:
    case Types.VARCHAR:
      if (columnAttributes != null) {
        return columnAttributes.precision;
      }
      return Integer.MAX_VALUE; // hive has no max limit for strings
    case Types.BINARY:
      return Integer.MAX_VALUE; // hive has no max limit for binary
    case Types.TINYINT:
      return 3;
    case Types.SMALLINT:
      return 5;
    case Types.INTEGER:
      return 10;
    case Types.BIGINT:
      return 19;
    case Types.FLOAT:
      return 7;
    case Types.DOUBLE:
      return 15;
    case Types.DATE:
      return 10;
    case Types.TIMESTAMP:
      return 29;
    case Types.DECIMAL:
      return columnAttributes.precision;
    case Types.JAVA_OBJECT:
    case Types.ARRAY:
    case Types.STRUCT:
      return Integer.MAX_VALUE;
    default:
      throw new SQLException("Invalid column type: " + columnType);
    }
  }

  static int columnScale(int columnType, JdbcColumnAttributes columnAttributes)
      throws SQLException {
    // according to hiveTypeToSqlType possible options are:
    switch(columnType) {
    case Types.BOOLEAN:
    case Types.CHAR:
    case Types.VARCHAR:
    case Types.TINYINT:
    case Types.SMALLINT:
    case Types.INTEGER:
    case Types.BIGINT:
    case Types.DATE:
    case Types.BINARY:
      return 0;
    case Types.FLOAT:
      return 7;
    case Types.DOUBLE:
      return 15;
    case  Types.TIMESTAMP:
      return 9;
    case Types.DECIMAL:
      return columnAttributes.scale;
    case Types.JAVA_OBJECT:
    case Types.ARRAY:
    case Types.STRUCT:
      return 0;
    default:
      throw new SQLException("Invalid column type: " + columnType);
    }
  }

  public Integer getNumPrecRadix() {
    if (type.equalsIgnoreCase("tinyint")) {
      return 10;
    } else if (type.equalsIgnoreCase("smallint")) {
      return 10;
    } else if (type.equalsIgnoreCase("int")) {
      return 10;
    } else if (type.equalsIgnoreCase("bigint")) {
      return 10;
    } else if (type.equalsIgnoreCase("float")) {
      return 10;
    } else if (type.equalsIgnoreCase("double")) {
      return 10;
    } else if (type.equalsIgnoreCase("decimal")) {
      return 10;
    } else { // anything else including boolean and string is null
      return null;
    }
  }

  public String getComment() {
    return comment;
  }

  public int getOrdinalPos() {
    return ordinalPos;
  }
}
