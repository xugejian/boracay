package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.constant.BuildMode;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelFilterCol;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-13.
 */
public abstract class BatchTargetWrapper extends Wrapper implements BatchTargetProvider {
    @Override
    public String inputSQL(Model model) {
        String id = model.getId();
        Metadata metadata = model.getTargetMetadata();
        String fullTbName = metadata.getTbName();
        List<ModelMapping> modelMappings = model.getModelMappings();
        boolean isOverwrite = (BuildMode.INSERT_OVERWRITE == model.getBuildMode() ? true : false);
        Datasource sDs = model.getSourceDatasource();
        String sDsId = sDs.getId();
        Datasource eDs = model.getEngineDatasource();
        String eDsId = eDs.getId();
        Datasource tDs = metadata.getDatasource();
        String tDsId = tDs.getId();

        String selectSql = null;
        String selectTableName = getSourceTableName(id);
        String insertTableName = getTargetTableName(id);
        if (sDsId.equals(eDsId) && tDsId.equals(eDsId)) { // 源、引擎、目标的数据源相同
            HiveModel hiveModel = new HiveModel(model);
            selectSql = hiveModel.getSql();
            selectTableName = hiveModel.getDatabaseName() + DATABASE_AND_TABLE_SEP + hiveModel.getTableName();
            insertTableName = fullTbName;
        } else if (sDsId.equals(eDsId)) { // 源、引擎的数据源相同
            HiveModel hiveModel = new HiveModel(model);
            selectSql = hiveModel.getSql();
            selectTableName = hiveModel.getDatabaseName() + DATABASE_AND_TABLE_SEP + hiveModel.getTableName();
            insertTableName = getTargetTableName(id);
        } else if (tDsId.equals(eDsId)) { // 目标、引擎的数据源相同
            selectTableName = getSourceTableName(id);
            insertTableName = fullTbName;
        }

        if (StringUtils.isNotBlank(selectSql)) {
            for (ModelMapping mapping : modelMappings) mapping.setName("UDSP_VIEW." + mapping.getName());
            return HiveSqlUtil.insert2(isOverwrite, insertTableName, getInsertColumns(modelMappings, metadata), null,
                    getSelectColumns(modelMappings, metadata), selectSql, getWhereProperties(model.getModelFilterCols()));
        } else {
            return HiveSqlUtil.insert(isOverwrite, insertTableName, getInsertColumns(modelMappings, metadata), null,
                    getSelectColumns(modelMappings, metadata), selectTableName, getWhereProperties(model.getModelFilterCols()));
        }
    }

    @Override
    public boolean dropTargetEngineSchema(Model model) throws SQLException {
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        String id = model.getId();
        String tableName = getTargetTableName(id);
        String sql = HiveSqlUtil.dropTable(true, tableName);
        return JdbcUtil.executeUpdate(eHiveDs, sql) >= 0 ? true : false;
    }

    private List<WhereProperty> getWhereProperties(List<ModelFilterCol> modelFilterCols) {
        if (modelFilterCols == null || modelFilterCols.size() == 0)
            return null;
        List<WhereProperty> whereProperties = new ArrayList<>();
        for (ModelFilterCol filterCol : modelFilterCols) {
            WhereProperty whereProperty = new WhereProperty();
            whereProperty.setName(filterCol.getName());
            whereProperty.setValue(filterCol.getValue());
            whereProperty.setType(filterCol.getType());
            whereProperty.setOperator(filterCol.getOperator());
        }
        return whereProperties;
    }

    protected abstract List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata);

    protected abstract List<String> getInsertColumns(List<ModelMapping> modelMappings, Metadata metadata);
}
