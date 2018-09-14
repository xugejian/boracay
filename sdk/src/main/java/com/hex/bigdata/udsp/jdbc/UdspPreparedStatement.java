package com.hex.bigdata.udsp.jdbc;

import com.hex.bigdata.udsp.jdbc.netty.Client;
import com.hex.bigdata.udsp.jdbc.netty.ClientFactory;
import com.hex.bigdata.udsp.jdbc.netty.RemotingUrl;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

/**
 * UDSP JDBC Prepared Statement
 */
public class UdspPreparedStatement extends UdspStatement implements PreparedStatement {
    private final String sql;

    /**
     * 保存SQL参数{paramLoc:paramValue}
     */
    private final HashMap<Integer, String> parameters = new HashMap<Integer, String>();

    public UdspPreparedStatement(UdspConnection connection, ClientFactory factory, Client client,
                                 RemotingUrl url, String sql) {
        super(connection, factory, client, url);
        this.sql = sql;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return super.executeQuery(updateSql(sql, parameters));
    }

    /**
     * 使用{@link PreparedStatement}的setXXX方法设置的参数更新SQL字符串
     *
     * @param sql
     * @param parameters
     * @return updated SQL string
     */
    private String updateSql(final String sql, HashMap<Integer, String> parameters) {
        if (!sql.contains("?")) {
            return sql;
        }

        StringBuffer newSql = new StringBuffer(sql);

        int paramLoc = 1;
        while (getCharIndexFromSqlByParamLocation(sql, '?', paramLoc) > 0) {
            // 检查用户是否设置了需要的参数
            if (parameters.containsKey(paramLoc)) {
                int tt = getCharIndexFromSqlByParamLocation(newSql.toString(), '?', 1);
                newSql.deleteCharAt(tt);
                newSql.insert(tt, parameters.get(paramLoc));
            }
            paramLoc++;
        }

        return newSql.toString();

    }

    /**
     * 通过参数位置从SQL字符串获取给定字符的索引
     * </br> 如果返回-1，则没有找到
     *
     * @param sql
     * @param cchar
     * @param paramLoc
     * @return
     */
    private int getCharIndexFromSqlByParamLocation(final String sql, final char cchar, final int paramLoc) {
        int signalCount = 0;
        int charIndex = -1;
        int num = 0;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '\'' || c == '\\') // record the count of char "'" and char "\"
            {
                signalCount++;
            } else if (c == cchar && signalCount % 2 == 0) { // check if the ? is really the parameter
                num++;
                if (num == paramLoc) {
                    charIndex = i;
                    break;
                }
            }
        }
        return charIndex;
    }

    @Override
    public int executeUpdate() throws SQLException {
        super.executeUpdate(updateSql(sql, parameters));
        return 0;
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        this.parameters.put(parameterIndex, "NULL");
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        this.parameters.put(parameterIndex, "" + x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        this.parameters.put(parameterIndex, "" + x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        this.parameters.put(parameterIndex, "" + x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        this.parameters.put(parameterIndex, "" + x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        this.parameters.put(parameterIndex, "" + x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        this.parameters.put(parameterIndex, "" + x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        this.parameters.put(parameterIndex, "" + x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        x = x.replace("'", "\\'");
        this.parameters.put(parameterIndex, "'" + x + "'");
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        this.parameters.put(parameterIndex, x.toString());
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        this.parameters.put(parameterIndex, x.toString());
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void clearParameters() throws SQLException {
        this.parameters.clear();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        if (x == null) {
            setNull(parameterIndex, Types.NULL);
        } else if (x instanceof String) {
            setString(parameterIndex, (String) x);
        } else if (x instanceof Short) {
            setShort(parameterIndex, ((Short) x).shortValue());
        } else if (x instanceof Integer) {
            setInt(parameterIndex, ((Integer) x).intValue());
        } else if (x instanceof Long) {
            setLong(parameterIndex, ((Long) x).longValue());
        } else if (x instanceof Float) {
            setFloat(parameterIndex, ((Float) x).floatValue());
        } else if (x instanceof Double) {
            setDouble(parameterIndex, ((Double) x).doubleValue());
        } else if (x instanceof Boolean) {
            setBoolean(parameterIndex, ((Boolean) x).booleanValue());
        } else if (x instanceof Byte) {
            setByte(parameterIndex, ((Byte) x).byteValue());
        } else if (x instanceof Character) {
            setString(parameterIndex, x.toString());
        } else if (x instanceof Timestamp) {
            setString(parameterIndex, x.toString());
        } else if (x instanceof BigDecimal) {
            setString(parameterIndex, x.toString());
        } else {
            // Can't infer a type.
            throw new SQLException(MessageFormat.format(
                    "Can''t infer the SQL type to use for an instance of {0}. Use setObject() with an explicit Types value to specify the type to use.",
                    x.getClass().getName()));
        }
    }

    @Override
    public boolean execute() throws SQLException {
        return super.execute(updateSql(sql, parameters));
    }

    @Override
    public void addBatch() throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        this.parameters.put(parameterIndex, "NULL");
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        String str = new Scanner(x, "UTF-8").useDelimiter("\\A").next();
        this.parameters.put(parameterIndex, str);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        throw new SQLException("Method not supported");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLException("Method not supported");
    }
}
