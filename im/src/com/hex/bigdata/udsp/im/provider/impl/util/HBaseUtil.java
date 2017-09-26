package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseAdminPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-26.
 */
public class HBaseUtil {
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

    private static Logger logger = LogManager.getLogger(HBaseUtil.class);

    private static Map<String, HBaseConnectionPoolFactory> dataSourcePool;
    private static Map<String, HBaseAdminPoolFactory> hbaseAdminPool;

    private static final String HBASE_REGION_START_KEY = "0000000000000000";
    private static final String HBASE_REGION_STOP_KEY = "ffffffffffffffff";

    public static synchronized HBaseAdminPoolFactory getHBaseAdminFactory(HBaseDatasource datasource) {
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
            factory = new HBaseAdminPoolFactory(config, datasource);
        }
        hbaseAdminPool.put(dsId, factory);
        return factory;
    }

    public static HBaseAdmin getHBaseAdmin(HBaseDatasource datasource) {
        try {
            return getHBaseAdminFactory(datasource).getHBaseAdmin();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    public static void release(HBaseDatasource datasource, HBaseAdmin admin) {
        getHBaseAdminFactory(datasource).releaseHBaseAdmin(admin);
    }

    public static synchronized HBaseConnectionPoolFactory getDataSource(HBaseDatasource datasource) {
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
            factory = new HBaseConnectionPoolFactory(config, datasource);
        }
        dataSourcePool.put(dsId, factory);
        return factory;
    }

    public static HConnection getConnection(HBaseDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    public static void release(HBaseDatasource datasource, HConnection conn) {
        getDataSource(datasource).releaseConnection(conn);
    }

    /**
     * 获取HBase Regions分区的Hex Splits
     *
     * @param startKey
     * @param endKey
     * @param numRegions
     * @return
     */
    public static byte[][] getHexSplits(String startKey, String endKey, int numRegions) {
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
        BigInteger regionIncrement = range.divide(BigInteger.valueOf(numRegions));
        lowestKey = lowestKey.add(regionIncrement);
        for (int i = 0; i < numRegions - 1; i++) {
            BigInteger key = lowestKey.add(regionIncrement.multiply(BigInteger.valueOf(i)));
            byte[] b = String.format("%016x", key).getBytes();
            splits[i] = b;
        }
        return splits;
    }


    /**
     * 创建HBase表
     *
     * @return
     */
    public static boolean createHTable(HBaseMetadata metadata) throws IOException {
        HBaseDatasource datasource = new HBaseDatasource(metadata.getDatasource().getPropertyMap());
        HBaseAdmin admin = HBaseUtil.getHBaseAdmin(datasource);
        String tableName = metadata.getTbName();
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            release(datasource, admin);
            throw new IOException(e1.getMessage());
        }
        // 不存在该表
        if (status) {
            logger.warn("HBase表" + tableName + "已经存在！");
            throw new IOException("HBase表" + tableName + "已经存在！");
        } else {
            HTableDescriptor hbaseTable = new HTableDescriptor(hbaseTableName);
            // 族设置参数
            HColumnDescriptor hbaseColumn = new HColumnDescriptor(metadata.getFamily());
            hbaseColumn.setBlocksize(65536); // 块大小
            // 压缩
            String compression = metadata.getCompression();
            if (Compression.Algorithm.SNAPPY.getName().equals(compression)) {
                hbaseColumn.setCompressionType(Compression.Algorithm.SNAPPY);
            } else if (Compression.Algorithm.GZ.getName().equals(compression)) {
                hbaseColumn.setCompressionType(Compression.Algorithm.GZ);
            } else if (Compression.Algorithm.LZ4.getName().equals(compression)) {
                hbaseColumn.setCompressionType(Compression.Algorithm.LZ4);
            } else if (Compression.Algorithm.LZO.getName().equals(compression)) {
                hbaseColumn.setCompressionType(Compression.Algorithm.LZO);
            } else if (Compression.Algorithm.NONE.getName().equals(compression)) {
                hbaseColumn.setCompressionType(Compression.Algorithm.NONE);
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
            String splitPolicy = metadata.getSplitPolicy();
            if (StringUtils.isNotBlank(splitPolicy)) {
                hbaseTable.setConfiguration(HTableDescriptor.SPLIT_POLICY, splitPolicy);
            }
            // 创建表
            byte[][] regionSplits = getHexSplits(HBASE_REGION_START_KEY, HBASE_REGION_STOP_KEY, metadata.getRegionNum());
            try {
                admin.createTable(hbaseTable, regionSplits);
                logger.debug("HBase表" + tableName + "创建成功！");
                return true;
            } catch (IOException e) {
                logger.warn("HBase表" + tableName + "创建失败！");
                throw new IOException("HBase表" + tableName + "创建失败！" + e.getMessage());
            } finally {
                release(datasource, admin);
            }
        }
    }

    /**
     * 禁用HBase表
     *
     * @param tableName
     * @return
     */
    public static boolean disableHTable(HBaseDatasource datasource, String tableName) throws IOException {
        HBaseAdmin admin = getHBaseAdmin(datasource);
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            release(datasource, admin);
            throw new IOException(e1.getMessage());
        }
        // 存在该表
        if (status) {
            try {
                admin.disableTable(hbaseTableName);
                logger.debug("HBase表" + tableName + "禁用成功！");
                return true;
            } catch (IOException e) {
                logger.warn("HBase表" + tableName + "禁用失败！");
                throw new IOException("HBase表" + tableName + "禁用失败！" + e.getMessage());
            } finally {
                release(datasource, admin);
            }
        } else {
            logger.warn("HBase表" + tableName + "不存在！");
            throw new IOException("HBase表" + tableName + "不存在！");
        }
    }

    /**
     * 删除HBase表（必须先禁用才可删除）
     *
     * @param tableName
     * @return
     */
    public static boolean deleteHTable(HBaseDatasource datasource, String tableName) throws IOException {
        HBaseAdmin admin = getHBaseAdmin(datasource);
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            release(datasource, admin);
            throw new IOException(e1.getMessage());
        }
        // 存在该表
        if (status) {
            try {
                admin.deleteTable(hbaseTableName);
                logger.debug("HBase表" + tableName + "删除成功！");
                return true;
            } catch (IOException e) {
                logger.warn("HBase表" + tableName + "删除失败！");
                throw new IOException("HBase表" + tableName + "删除失败！" + e.getMessage());
            } finally {
                release(datasource, admin);
            }
        } else {
            logger.warn("HBase表" + tableName + "不存在！");
            throw new IOException("HBase表" + tableName + "不存在！");
        }
    }

    /**
     * 一步删除HBase表
     *
     * @param tableName
     * @return
     */
    public static boolean dropHTable(HBaseDatasource datasource, String tableName) throws IOException {
        HBaseAdmin admin = getHBaseAdmin(datasource);
        TableName hbaseTableName = TableName.valueOf(tableName);
        // 判断是否已经存在该表
        boolean status = false;
        try {
            status = admin.isTableAvailable(hbaseTableName);
        } catch (IOException e1) {
            release(datasource, admin);
            throw new IOException(e1.getMessage());
        }
        // 存在该表
        if (status) {
            try {
                admin.disableTable(hbaseTableName);
                logger.debug("HBase表" + tableName + "禁用成功！");
                try {
                    admin.deleteTable(hbaseTableName);
                    logger.debug("HBase表" + tableName + "删除成功！");
                    return true;
                } catch (IOException e) {
                    logger.warn("HBase表" + tableName + "删除失败！");
                    throw new IOException("HBase表" + tableName + "删除失败！" + e.getMessage());
                } finally {
                    release(datasource, admin);
                }
            } catch (IOException e) {
                logger.warn("HBase表" + tableName + "禁用失败！");
                throw new IOException("HBase表" + tableName + "禁用失败！" + e.getMessage());
            } finally {
                release(datasource, admin);
            }
        } else {
            logger.warn("HBase表" + tableName + "不存在！");
            throw new IOException("HBase表" + tableName + "不存在！");
        }
    }

    /**
     * 清空HBase表数据
     */
    public static boolean emptyHTable(HBaseMetadata metadata) throws IOException {
        HBaseDatasource datasource = new HBaseDatasource(metadata.getDatasource().getPropertyMap());
        String tableName = metadata.getTbName();
        if (dropHTable(datasource, tableName)) {
            return createHTable(metadata);
        }
        return false;
    }

    /**
     * 清空HBase表数据
     */
    public static boolean emptyHTable(HBaseDatasource datasource, String tableName, int numRegions) throws IOException {
        if (dropHTable(datasource, tableName)) {
            TableName hbaseTableName = TableName.valueOf(tableName);
            HConnection conn = getConnection(datasource);
            HBaseAdmin admin = getHBaseAdmin(datasource);
            byte[][] regionSplits = getHexSplits(HBASE_REGION_START_KEY, HBASE_REGION_STOP_KEY, numRegions);
            try {
                HTableDescriptor hbaseTable = conn.getHTableDescriptor(hbaseTableName);
                admin.createTable(hbaseTable, regionSplits);
                logger.debug("HBase表" + tableName + "创建成功！");
                return true;
            } catch (IOException e) {
                logger.warn("HBase表" + tableName + "创建失败！");
                throw new IOException("HBase表" + tableName + "创建失败！" + e.getMessage());
            } finally {
                release(datasource, admin);
                release(datasource, conn);
            }
        }
        return false;
    }

    /**
     * put数据到HBase表的rowkey的族列（单个列）
     *
     * @param tableName
     * @param rowkey
     * @param family
     * @param qualifier
     * @param value
     * @return
     */
    public static void put(HBaseDatasource datasource, String tableName, String rowkey,
                           String family, String qualifier, String value) throws IOException {
        HConnection conn = null;
        HTableInterface table = null;
        try {
            conn = getConnection(datasource);
            table = conn.getTable(tableName);
            Put put = new Put(Bytes.toBytes(rowkey));
            put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
            table.put(put);
            logger.debug("添加数据成功！");
        } catch (IOException e) {
            logger.warn("添加数据失败！" + e.getMessage());
            throw new IOException(e);
        } finally {
            if (table != null) {
                table.close();
            }
            release(datasource, conn);
        }
    }

    /**
     * put数据到HBase表的rowkey的族（多个列）
     *
     * @param datasource
     * @param tableName
     * @param rowkey
     * @param family
     * @param map
     * @throws IOException
     */
    public static void put(HBaseDatasource datasource, String tableName, String rowkey,
                           String family, Map<String, String> map) throws IOException {
        HConnection conn = null;
        HTableInterface table = null;
        try {
            conn = getConnection(datasource);
            table = conn.getTable(tableName);
            Put put = new Put(Bytes.toBytes(rowkey));
            for (Map.Entry<String, String> entry : map.entrySet()) {
                byte[] qualifier = Bytes.toBytes(entry.getKey());
                byte[] value = Bytes.toBytes(entry.getValue());
                put.add(Bytes.toBytes(family), qualifier, value);
            }
            table.put(put);
            logger.debug("添加数据成功！");
        } catch (IOException e) {
            logger.warn("添加数据失败！" + e.getMessage());
            throw new IOException(e);
        } finally {
            if (table != null) {
                table.close();
            }
            release(datasource, conn);
        }
    }
}
