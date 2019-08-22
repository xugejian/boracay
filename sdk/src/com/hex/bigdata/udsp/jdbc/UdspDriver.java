package com.hex.bigdata.udsp.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * UDSP JDBC Driver
 */
public class UdspDriver implements Driver {

    static {
        try {
            DriverManager.registerDriver(new UdspDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Is this driver JDBC compliant?
     */
    private static final boolean JDBC_COMPLIANT = false;

    /**
     * Property key for the UDSP service name.
     */
    private static final String SERVICE_PROPERTY_KEY = "SERVICE";

    /**
     * Property key for the UDSP Server host.
     */
    private static final String HOST_PROPERTY_KEY = "HOST";

    /**
     * Property key for the UDSP Server port.
     */
    private static final String PORT_PROPERTY_KEY = "PORT";

    /**
     * As per JDBC 3.0 Spec (section 9.2)
     * "If the Driver implementation understands the URL, it will return a Connection object;
     * otherwise it returns null"
     *
     * @param url
     * @param info
     * @return
     * @throws SQLException
     */
    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return acceptsURL(url) ? new UdspConnection(url, info) : null;
    }

    /**
     * 检查给定的url是否为有效格式。
     * <p>
     * 当前的uri格式是:jdbc:udsp://[host[:port]]
     *
     * @param url
     * @return
     * @throws SQLException
     */
    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return Pattern.matches(Utils.URL_PREFIX + ".*", url);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        if (info == null) {
            info = new Properties();
        }

        if ((url != null) && url.startsWith(Utils.URL_PREFIX)) {
            info = parseURLforPropertyInfo(url, info);
        }

        DriverPropertyInfo hostProp = new DriverPropertyInfo(HOST_PROPERTY_KEY,
                info.getProperty(HOST_PROPERTY_KEY, ""));
        hostProp.required = false;
        hostProp.description = "Hostname of UDSP Server";

        DriverPropertyInfo portProp = new DriverPropertyInfo(PORT_PROPERTY_KEY,
                info.getProperty(PORT_PROPERTY_KEY, ""));
        portProp.required = false;
        portProp.description = "Port number of UDSP Server";

        DriverPropertyInfo dbProp = new DriverPropertyInfo(SERVICE_PROPERTY_KEY,
                info.getProperty(SERVICE_PROPERTY_KEY, Utils.DEFAULT_SERVICE_NAME));
        dbProp.required = false;
        dbProp.description = "Service name";

        DriverPropertyInfo[] dpi = new DriverPropertyInfo[3];

        dpi[0] = hostProp;
        dpi[1] = portProp;
        dpi[2] = dbProp;

        return dpi;
    }

    /**
     * Takes a url in the form of jdbc:udsp://[hostname]:[port]/[service_name] and parses it. Everything after jdbc:udsp// is optional.
     * <p>
     * The output from Utils.parseUrl() is massaged for the needs of getPropertyInfo
     *
     * @param url
     * @param defaults
     * @return
     * @throws SQLException
     */
    private Properties parseURLforPropertyInfo(String url, Properties defaults) throws SQLException {
        Properties urlProps = (defaults != null) ? new Properties(defaults) : new Properties();

        if (url == null || !url.startsWith(Utils.URL_PREFIX)) {
            throw new SQLException("Invalid connection url: " + url);
        }

        JdbcConnectionParams params = null;
        try {
            params = Utils.parseURL(url);
        } catch (JdbcUriParseException e) {
            throw new SQLException(e);
        }
        String host = params.getHost();
        if (host == null) {
            host = "";
        }
        String port = Integer.toString(params.getPort());
        if (host.equals("")) {
            port = "";
        } else if (port.equals("0") || port.equals("-1")) {
            port = Utils.DEFAULT_PORT;
        }
        String serviceName = params.getServiceName();
        urlProps.put(HOST_PROPERTY_KEY, host);
        urlProps.put(PORT_PROPERTY_KEY, port);
        urlProps.put(SERVICE_PROPERTY_KEY, serviceName);

        return urlProps;
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return JDBC_COMPLIANT;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException("Method not supported");
    }
}
