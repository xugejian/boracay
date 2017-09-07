package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Metadata;

/**
 * Created by JunjieM on 2017-9-6.
 */
public abstract class TargetWrapper implements TargetProvider {

    @Override
    public boolean create(Metadata metadata) throws Exception {
        if (!createTable(metadata)) {
            return false;
        }
        if (!dropHiveTable(metadata) || !createHiveTable(metadata)) {
            dropTable(metadata);
            return false;
        }
        return true;
    }

    public abstract boolean createTable(Metadata metadata) throws Exception;

    public abstract boolean createHiveTable(Metadata metadata) throws Exception;

    @Override
    public boolean drop(Metadata metadata) throws Exception {
        if (!dropHiveTable(metadata)) {
            return false;
        }
        if (!dropTable(metadata)) {
            createHiveTable(metadata);
            return false;
        }
        return true;
    }

    public abstract boolean dropTable(Metadata metadata) throws Exception;

    public abstract boolean dropHiveTable(Metadata metadata) throws Exception;
}
