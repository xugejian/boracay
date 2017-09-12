package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseAdminPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import com.hex.bigdata.udsp.im.provider.impl.util.model.SerDeProperty;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class HBaseWrapper extends Wrapper {
    static {
        // 解决winutils.exe不存在的问题
        try {
            File workaround = new File(".");
            System.getProperties().put("hadoop.home.dir",
                    workaround.getAbsolutePath());
            new File("./bin").mkdirs();
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger logger = LogManager.getLogger(HBaseWrapper.class);
    private static Map<String, HBaseConnectionPoolFactory> dataSourcePool;
    private static Map<String, HBaseAdminPoolFactory> hbaseAdminPool;

    private static final String HBASE_REGION_START_KEY = "0000000000000000";
    private static final String HBASE_REGION_STOP_KEY = "ffffffffffffffff";

    protected synchronized HBaseAdminPoolFactory getHBaseAdminFactory(HBaseDatasource datasource) {
        String dsId = datasource.getId();
        if (hbaseAdminPool == null) {
            hbaseAdminPool = new HashMap<>();
        }
        HBaseAdminPoolFactory factory = hbaseAdminPool.get(dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config();
            config.lifo = true;
            config.minIdle = 1;
            config.maxActive = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new HBaseAdminPoolFactory(config, datasource.getZkQuorum(), datasource.getZkPort());
        }
        hbaseAdminPool.put(dsId, factory);
        return factory;
    }

    protected HBaseAdmin getHBaseAdmin(HBaseDatasource datasource) {
        try {
            return getHBaseAdminFactory(datasource).getHBaseAdmin();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    protected void release(HBaseDatasource datasource, HBaseAdmin admin) {
        getHBaseAdminFactory(datasource).releaseHBaseAdmin(admin);
    }

    protected synchronized HBaseConnectionPoolFactory getDataSource(HBaseDatasource datasource) {
        String dsId = datasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<>();
        }
        HBaseConnectionPoolFactory factory = dataSourcePool.get(dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config();
            config.lifo = true;
            config.minIdle = 1;
            config.maxActive = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new HBaseConnectionPoolFactory(config, datasource.getZkQuorum(), datasource.getZkPort());
        }
        dataSourcePool.put(dsId, factory);
        return factory;
    }

    protected HConnection getConnection(HBaseDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    protected void release(HBaseDatasource datasource, HConnection conn) {
        getDataSource(datasource).releaseConnection(conn);
    }

    protected List<TableColumn> getTargetColumns(List<ModelMapping> modelMappings, HBaseMetadata hbaseMetadata) {
        List<TableColumn> columns = new ArrayList<>();
        columns.add(new TableColumn("key", DataType.STRING.getValue()));
        columns.add(new TableColumn("val", DataType.STRING.getValue()));
        return columns;
    }

    protected List<TblProperty> getTblProperties(String tableName) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("hbase.table.name", tableName));
        //tblProperties.add(new TblProperty("hbase.mapred.output.outputtable", tableName));
        return tblProperties;
    }

    protected String getTargetTableName(String fullTbName, String id) {
        String[] strs = fullTbName.split(DATABASE_AND_TABLE_SEP);
        String dbName = null;
        String tbName = null;
        if (strs.length == 1) {
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
        } else if (strs.length == 2) {
            dbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[1];
        }
        String tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + id;
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + dbName + HIVE_ENGINE_TABLE_SEP + tbName + HIVE_ENGINE_TABLE_SEP + id;
        } else if (StringUtils.isNotBlank(tbName)) {
            tableName = HIVE_ENGINE_TARGET_TABLE_PREFIX + tbName + HIVE_ENGINE_TABLE_SEP + id;
        }
        return tableName;
    }

    protected List<SerDeProperty> getSerDeProperties(List<ModelMapping> modelMappings, HBaseMetadata hbaseMetadata) {
        List<SerDeProperty> serDeProperties = new ArrayList<>();
        String family = hbaseMetadata.getFamily();
        String qualifier = hbaseMetadata.getQualifier();
        String hbaseColumnsMapping = ":key," + family + ":" + qualifier;
        serDeProperties.add(new SerDeProperty("hbase.columns.mapping", hbaseColumnsMapping));
        return serDeProperties;
    }

    /**
     * 创建HBase表
     *
     * @param datasource
     * @param tableName
     * @param numRegions
     * @param isCompression
     * @param isAutoSplit
     * @param family
     * @return
     */
    public boolean createHTable(HBaseDatasource datasource, String tableName,
                                int numRegions, boolean isCompression, boolean isAutoSplit, String family) {
        HBaseAdmin admin = getHBaseAdmin(datasource);
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            e1.printStackTrace();
            release(datasource, admin);
        }
        // 不存在该表
        if (!status) {
            HTableDescriptor hbaseTable = new HTableDescriptor(hbaseTableName);
            // 族设置参数
            HColumnDescriptor hbaseColumn = new HColumnDescriptor(family);
            hbaseColumn.setBlocksize(65536); // 块大小
            if (isCompression) {
                hbaseColumn.setCompressionType(Compression.Algorithm.SNAPPY); // 压缩
            }
            hbaseColumn.setMaxVersions(1); // 数据保存的最大版本数
            // hbaseColumn.setMinVersions(0); // 数据保存的最小版本数（配合TimeToLive使用）
            // hbaseColumn.setTimeToLive(36000); // 表中数据存储生命期，过期数据将自动被删除
            hbaseColumn.setBloomFilterType(BloomType.ROW); //
            hbaseColumn.setDataBlockEncoding(DataBlockEncoding.PREFIX);
            hbaseColumn.setBlockCacheEnabled(true); //
            hbaseColumn.setInMemory(false); // 是否保存在内存中以提高相应速度
            hbaseTable.addFamily(hbaseColumn);
            // 表设置参数
            if (!isAutoSplit) {
                hbaseTable.setConfiguration(HTableDescriptor.SPLIT_POLICY,
                        "org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy");
            }
            // 创建表
            byte[][] regionSplits = getHexSplits(HBASE_REGION_START_KEY, HBASE_REGION_STOP_KEY, numRegions);
            try {
                admin.createTable(hbaseTable, regionSplits);
                System.out.println("HBase表" + tableName + "创建成功！");
                return true;
            } catch (IOException e) {
                System.out.println("HBase表" + tableName + "创建失败！");
                e.printStackTrace();
            } finally {
                release(datasource, admin);
            }
        } else {
            System.out.println("HBase表" + tableName + "已经存在！");
        }
        return false;
    }

    /**
     * 禁用HBase表
     *
     * @param tableName
     * @return
     */
    public boolean disableHTable(HBaseDatasource datasource, String tableName) {
        HBaseAdmin admin = getHBaseAdmin(datasource);
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            e1.printStackTrace();
            release(datasource, admin);
        }
        // 存在该表
        if (status) {
            try {
                admin.disableTable(hbaseTableName);
                System.out.println("HBase表" + tableName + "禁用成功！");
                return true;
            } catch (IOException e) {
                System.out.println("HBase表" + tableName + "禁用失败！");
                e.printStackTrace();
            } finally {
                release(datasource, admin);
            }
        } else {
            System.out.println("HBase表" + tableName + "不存在！");
        }
        return false;
    }

    /**
     * 删除HBase表（必须先禁用才可删除）
     *
     * @param tableName
     * @return
     */
    public boolean deleteHTable(HBaseDatasource datasource, String tableName) {
        HBaseAdmin admin = getHBaseAdmin(datasource);
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            e1.printStackTrace();
            release(datasource, admin);
        }
        // 存在该表
        if (status) {
            try {
                admin.deleteTable(hbaseTableName);
                System.out.println("HBase表" + tableName + "删除成功！");
                return true;
            } catch (IOException e) {
                System.out.println("HBase表" + tableName + "删除失败！");
                e.printStackTrace();
            } finally {
                release(datasource, admin);
            }
        } else {
            System.out.println("HBase表" + tableName + "不存在！");
        }
        return false;
    }

    /**
     * 一步删除HBase表
     *
     * @param tableName
     * @return
     */
    public boolean dropHTable(HBaseDatasource datasource, String tableName) {
        HBaseAdmin admin = getHBaseAdmin(datasource);
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            e1.printStackTrace();
            release(datasource, admin);
        }
        // 存在该表
        if (status) {
            try {
                admin.disableTable(hbaseTableName);
                System.out.println("HBase表" + tableName + "禁用成功！");
                try {
                    admin.deleteTable(hbaseTableName);
                    System.out.println("HBase表" + tableName + "删除成功！");
                    return true;
                } catch (IOException e) {
                    System.out.println("HBase表" + tableName + "删除失败！");
                    e.printStackTrace();
                } finally {
                    release(datasource, admin);
                }
            } catch (IOException e) {
                System.out.println("HBase表" + tableName + "禁用失败！");
                e.printStackTrace();
            } finally {
                release(datasource, admin);
            }
        } else {
            System.out.println("HBase表" + tableName + "不存在！");
        }
        return false;
    }

    /**
     * 清空HBase表数据
     */
    public boolean emptyHTable(HBaseDatasource datasource, String tableName,
                               int numRegions, boolean isCompression, boolean isAutoSplit, String family) {
        if (dropHTable(datasource, tableName)) {
            return createHTable(datasource, tableName, numRegions, isCompression,
                    isAutoSplit, family);
        }
        return false;
    }

    /**
     * 清空HBase表数据
     */
    public boolean emptyHTable(HBaseDatasource datasource, String tableName, int numRegions) {
        if (dropHTable(datasource, tableName)) {
            TableName hbaseTableName = TableName.valueOf(tableName);
            HConnection conn = getConnection(datasource);
            HBaseAdmin admin = getHBaseAdmin(datasource);
            byte[][] regionSplits = getHexSplits(HBASE_REGION_START_KEY, HBASE_REGION_STOP_KEY, numRegions);
            try {
                HTableDescriptor hbaseTable = conn
                        .getHTableDescriptor(hbaseTableName);
                admin.createTable(hbaseTable, regionSplits);
                System.out.println("HBase表" + tableName + "创建成功！");
                return true;
            } catch (IOException e) {
                System.out.println("HBase表" + tableName + "创建失败！");
                e.printStackTrace();
            } finally {
                release(datasource, admin);
                release(datasource, conn);
            }
        }
        return false;
    }

    /**
     * 获取HBase Regions分区的Hex Splits
     *
     * @param startKey
     * @param endKey
     * @param numRegions
     * @return
     */
    private byte[][] getHexSplits(String startKey, String endKey, int numRegions) {
        byte[][] splits = new byte[numRegions - 1][];
        if (startKey.length() < 16) {
            int len = 16 - startKey.length();
            for (int i = 0; i < len; i++) {
                startKey += "0";
            }
        }
        if (endKey.length() < 16) {
            int len = 16 - endKey.length();
            for (int i = 0; i < len; i++) {
                endKey += "0";
            }
        }
        BigInteger lowestKey = new BigInteger(startKey, 16);
        BigInteger highestKey = new BigInteger(endKey, 16);
        BigInteger range = highestKey.subtract(lowestKey);
        BigInteger regionIncrement = range.divide(BigInteger
                .valueOf(numRegions));
        lowestKey = lowestKey.add(regionIncrement);
        for (int i = 0; i < numRegions - 1; i++) {
            BigInteger key = lowestKey.add(regionIncrement.multiply(BigInteger
                    .valueOf(i)));
            byte[] b = String.format("%016x", key).getBytes();
            splits[i] = b;
        }
        return splits;
    }
}
