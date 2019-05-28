package com.hex.bigdata.udsp.im.converter.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.im.constant.BuildMode;
import com.hex.bigdata.udsp.im.constant.MetadataType;
import com.hex.bigdata.udsp.im.converter.impl.wrapper.PairHBaseWrapper;
import com.hex.bigdata.udsp.im.converter.model.Metadata;
import com.hex.bigdata.udsp.im.converter.model.MetadataCol;
import com.hex.bigdata.udsp.im.converter.model.Model;
import com.hex.bigdata.udsp.im.converter.model.RealtimeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunjieM on 2019-5-22.
 */
public class PairHBaseConverter extends PairHBaseWrapper {
    private static Logger logger = LoggerFactory.getLogger (PairHBaseConverter.class);

    private HBaseConverter hbaseConverter = (HBaseConverter) ObjectUtil.newInstance ("com.hex.bigdata.udsp.im.converter.impl.HBaseConverter");

    @Override
    public boolean testDatasource(Datasource datasource) {
        return hbaseConverter.testDatasource (getActiveDatasource (datasource))
                && hbaseConverter.testDatasource (getStandbyDatasource (datasource));
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        // HBase无法获取字段信息，返回null
        return null;
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        Metadata activeMetadata = getActiveMetadata (metadata);
        hbaseConverter.createSchema (activeMetadata);
        try {
            hbaseConverter.createSchema (getStandbyMetadata (metadata));
        } catch (Exception e) {
            // 回滚删除hbase（主）对应的表 删除失败怎么办？暂时不考虑
            hbaseConverter.dropSchema (activeMetadata);
            throw new Exception (e);
        }
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        hbaseConverter.dropSchema (getActiveMetadata (metadata));
        hbaseConverter.dropSchema (getStandbyMetadata (metadata));
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        return hbaseConverter.checkSchema (getActiveMetadata (metadata))
                && hbaseConverter.checkSchema (getStandbyMetadata (metadata));
    }

    @Override
    public void addColumns(Metadata metadata, List<MetadataCol> addMetadataCols) throws Exception {
        hbaseConverter.addColumns (getActiveMetadata (metadata), addMetadataCols);
        hbaseConverter.addColumns (getStandbyMetadata (metadata), addMetadataCols);
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        Model activeModel = getActiveModel (model);
        hbaseConverter.createTargetEngineSchema (activeModel);
//        try {
//            hbaseConverter.createTargetEngineSchema (getStandbyModel (model));
//        } catch (Exception e) {
//            // 删除回滚hbase（主）对应的目标引擎表 删除失败怎么办？暂时不考虑
//            hbaseConverter.dropTargetEngineSchema (activeModel);
//            throw new Exception (e);
//        }
    }

    /**
     * 重构dropTargetEngineSchema
     *
     * @param model
     * @throws SQLException
     */
    @Override
    public void dropTargetEngineSchema(Model model) throws SQLException {
//        hbaseConverter.dropTargetEngineSchema (getStandbyModel (model));
        hbaseConverter.dropTargetEngineSchema (getActiveModel (model));
    }

    /**
     * 重构buildRealtime
     *
     * @param key
     * @param model
     * @throws Exception
     */
    @Override
    public void buildBatch(String key, Model model) throws Exception {
        /*
        由于HBase做全量覆盖时，主表通过清空表数据来实现，而replication功能却没法将备表也清空，
        所以这里需要判断是否做全量覆盖则手动先清空备表数据。
         */
        if (BuildMode.INSERT_OVERWRITE == model.getBuildMode ()) {
            if (MetadataType.EXTERNAL == model.getTargetMetadata ().getType ()) { // 外表
                throw new IllegalArgumentException ("目标元数据中的表是外表，不支持全量构建策略！");
            }
            String tableName = model.getTargetMetadata ().getTbName ();
            logger.info ("清空HBase备表" + tableName + "的数据【START】");
            hbaseConverter.emptyDatas (getStandbyMetadata (model.getTargetMetadata ())); // 清空备表的数据
            logger.info ("清空HBase备表" + tableName + "的数据【END】");
        }

        hbaseConverter.buildBatch (key, getActiveModel (model));
        //hbaseConverter.buildBatch (getActiveKey (key), getActiveModel (model));
        /*
        由于HBase的Hive外链表需要和HBase在同一个环境，所以这里主备集群不能使用相同的Hive，
        解决办法：利用HBase自带的Replication功能实现主备同步。
         */
        //hbaseConverter.buildBatch (getStandbyKey (key), getStandbyModel (model));
    }

    /**
     * 重构buildRealtime
     *
     * @param key
     * @param model
     * @return
     */
    @Override
    public RealtimeResponse buildRealtime(String key, Model model) {
        RealtimeResponse activeRealtimeResponse = hbaseConverter.buildRealtime (key, getActiveModel (model));
        /*
        这里同上面buildBatch利用HBase自带的Replication功能实现主备同步。
         */
//        RealtimeResponse standbyRealtimeResponse = hbaseConverter.buildRealtime (key, getStandbyModel (model));
//        RealtimeResponse realtimeResponse = new RealtimeResponse ();
//        realtimeResponse.setCountNum (activeRealtimeResponse.getCountNum () + standbyRealtimeResponse.getCountNum ());
//        realtimeResponse.setMessage ("ACTIVE:" + activeRealtimeResponse.getMessage () + ", STANDBY:" + standbyRealtimeResponse.getMessage ());
//        realtimeResponse.setFilterNum (activeRealtimeResponse.getFilterNum () + standbyRealtimeResponse.getFilterNum ());
//        realtimeResponse.setParseFailedNum (activeRealtimeResponse.getParseFailedNum () + standbyRealtimeResponse.getParseFailedNum ());
//        realtimeResponse.setStoreSucceedNum (activeRealtimeResponse.getStoreSucceedNum () + standbyRealtimeResponse.getStoreSucceedNum ());
//        realtimeResponse.setStoreFailedNum (activeRealtimeResponse.getStoreFailedNum () + standbyRealtimeResponse.getStoreFailedNum ());
//        return realtimeResponse;
        return activeRealtimeResponse;
    }

    private Datasource getActiveDatasource(Datasource datasource) {
        Datasource ds = new Datasource (datasource);
        Property zkQuorum = datasource.getPropertyMap ().get ("active.hbase.zk.quorum");
        ds.getPropertyMap ().put ("hbase.zk.quorum", zkQuorum);
        return ds;
    }

    private Datasource getStandbyDatasource(Datasource datasource) {
        Datasource ds = new Datasource (datasource);
        Property zkQuorum = datasource.getPropertyMap ().get ("standby.hbase.zk.quorum");
        ds.getPropertyMap ().put ("hbase.zk.quorum", zkQuorum);
        return ds;
    }

    private Metadata getActiveMetadata(Metadata metadata) {
        Metadata md = new Metadata (metadata);
        Datasource datasource = getActiveDatasource (metadata.getDatasource ());
        md.setDatasource (datasource);
        return md;
    }

    private Metadata getStandbyMetadata(Metadata metadata) {
        Metadata md = new Metadata (metadata);
        Datasource datasource = getStandbyDatasource (metadata.getDatasource ());
        md.setDatasource (datasource);
        return md;
    }

    private Model getActiveModel(Model model) {
        Model activeModel = new Model (model);
        activeModel.setId ("ACTIVE" + HIVE_ENGINE_TABLE_SEP + model.getId ());
        // 如果参数中有group.id则说明是实时运行，由于相同组消费是抢占式的所以这里需要将配置的group.id参数需要修改
        Property groupId = model.getPropertyMap ().get ("group.id");
        if (groupId != null) {
            groupId.setValue ("ACTIVE" + HIVE_ENGINE_TABLE_SEP + groupId.getValue ());
        }
        Metadata metadata = getActiveMetadata (model.getTargetMetadata ());
        activeModel.setTargetMetadata (metadata);
        return activeModel;
    }

    private Model getStandbyModel(Model model) {
        Model standbyModel = new Model (model);
        standbyModel.setId ("STANDBY" + HIVE_ENGINE_TABLE_SEP + model.getId ());
        // 如果参数中有group.id则说明是实时运行，由于相同组消费是抢占式的所以这里需要将配置的group.id参数需要修改
        Property groupId = model.getPropertyMap ().get ("group.id");
        if (groupId != null) {
            groupId.setValue ("STANDBY" + HIVE_ENGINE_TABLE_SEP + groupId.getValue ());
        }
        Metadata metadata = getStandbyMetadata (model.getTargetMetadata ());
        standbyModel.setTargetMetadata (metadata);
        return standbyModel;
    }

    private String getActiveKey(String key) {
        return "ACTIVE" + HIVE_ENGINE_TABLE_SEP + key;
    }

    private String getStandbyKey(String key) {
        return "STANDBY" + HIVE_ENGINE_TABLE_SEP + key;
    }

}
