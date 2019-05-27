package com.hex.bigdata.udsp.im.converter.impl.model;

import com.hex.bigdata.udsp.im.converter.model.Model;

/**
 * Created by JunjieM on 2017-9-6.
 */
public class JdbcModel extends Model {

    public JdbcModel(Model model) {
        super(model);
    }

    public String gainDatabaseName() {
        return gainProperty("database.name").getValue();
    }

    public String gainTableName() {
        return gainProperty("table.name").getValue();
    }

    public String gainSelectSql() {
        return gainProperty("select.sql").getValue();
    }
}
