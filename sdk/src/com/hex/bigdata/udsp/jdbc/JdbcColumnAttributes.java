package com.hex.bigdata.udsp.jdbc;

/**
 * Created by JunjieM on 2018-7-26.
 */
public class JdbcColumnAttributes {
    public int precision = 0;
    public int scale = 0;

    public JdbcColumnAttributes() {
    }

    public JdbcColumnAttributes(int precision, int scale) {
        this.precision = precision;
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "(" + precision + "," + scale + ")";
    }
}
