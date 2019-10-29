package com.hex.bigdata.udsp.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunjieM on 2018-7-26.
 */
public class UdspResultSetMetaData implements ResultSetMetaData {
    private final List<String> columnNames;
    private final List<String> columnTypes;

    public UdspResultSetMetaData(List<String> columnNames, List<String> columnTypes) {
        this.columnNames = columnNames;
        this.columnTypes = columnTypes;
    }

    public String getCatalogName(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public String getColumnClassName(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public int getColumnCount() throws SQLException {
        return columnNames.size();
    }

    public int getColumnDisplaySize(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public String getColumnLabel(int column) throws SQLException {
        return columnNames.get(toZeroIndex(column));
    }

    public String getColumnName(int column) throws SQLException {
        return columnNames.get(toZeroIndex(column));
    }

    public int getColumnType(int column) throws SQLException {
        String type = columnTypes.get(toZeroIndex(column));
        return JdbcColumn.hiveTypeToSqlType(type);
    }

    public String getColumnTypeName(int column) throws SQLException {
        return columnTypes.get(toZeroIndex(column));
    }

    public int getPrecision(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public int getScale(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public String getSchemaName(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public String getTableName(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public boolean isAutoIncrement(int column) throws SQLException {
        // Hive doesn't have an auto-increment concept
        return false;
    }

    public boolean isCaseSensitive(int column) throws SQLException {
        // we need to convert the Hive type to the SQL type name
        String type = columnTypes.get(toZeroIndex(column));
        if ("string".equalsIgnoreCase(type)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCurrency(int column) throws SQLException {
        // Hive doesn't support a currency type
        return false;
    }

    public boolean isDefinitelyWritable(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public int isNullable(int column) throws SQLException {
        // Hive doesn't have the concept of not-null
        return ResultSetMetaData.columnNullable;
    }

    public boolean isReadOnly(int column) throws SQLException {
        return true;
    }

    public boolean isSearchable(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public boolean isSigned(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public boolean isWritable(int column) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException("Method not supported");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException("Method not supported");
    }

    protected int toZeroIndex(int column) throws SQLException {
        if (columnTypes == null) {
            throw new SQLException(
                    "Could not determine column type name for ResultSet");
        }
        if (column < 1 || column > columnTypes.size()) {
            throw new SQLException("Invalid column value: " + column);
        }
        return column - 1;
    }
}
