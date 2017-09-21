package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.sql.SQLException;

/**
 * Created by JunjieM on 2017-9-13.
 */
public abstract class BatchWrapper extends BatchTargetWrapper implements BatchSourceProvider {
    @Override
    public boolean dropSourceEngineSchema(Model model) throws SQLException {
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        String id = model.getId();
        String tableName = getSourceTableName(id);
        String sql = HiveSqlUtil.dropTable(true, tableName);
        return JdbcUtil.executeUpdate(eHiveDs, sql);
    }
}
