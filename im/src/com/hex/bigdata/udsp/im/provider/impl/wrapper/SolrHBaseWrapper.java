package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.util.model.ValueColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class SolrHBaseWrapper extends Wrapper implements BatchTargetProvider, RealtimeTargetProvider {

    private static Logger logger = LoggerFactory.getLogger(SolrHBaseWrapper.class);


    @Override
    protected void insertInto(Datasource datasource, String tableName, List<ValueColumn> valueColumns) {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateInsert(Datasource datasource, String tableName, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void matchingUpdate(Datasource datasource, String tableName, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<String> getInsertColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
