package com.hex.bigdata.udsp.jdbc;

import java.sql.SQLException;

/**
 * Jdbc Uri Parse Exception
 */
public class JdbcUriParseException extends SQLException {

    private static final long serialVersionUID = 0;

    /**
     * @param cause (original exception)
     */
    public JdbcUriParseException(Throwable cause) {
        super(cause);
    }

    /**
     * @param msg (exception message)
     */
    public JdbcUriParseException(String msg) {
        super(msg);
    }

    /**
     * @param msg   (exception message)
     * @param cause (original exception)
     */
    public JdbcUriParseException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
