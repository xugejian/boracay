package com.hex.bigdata.udsp.iq.provider.impl;

import com.hex.bigdata.udsp.common.api.model.Datasource;
import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.dsl.constant.ComparisonOperator;
import com.hex.bigdata.udsp.dsl.constant.LogicalOperator;
import com.hex.bigdata.udsp.dsl.model.Component;
import com.hex.bigdata.udsp.dsl.model.Composite;
import com.hex.bigdata.udsp.dsl.model.Dimension;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseMetadata;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBasePage;
import com.hex.bigdata.udsp.iq.provider.impl.util.HBaseUtil;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslRequest;
import com.hex.bigdata.udsp.iq.provider.model.dsl.IqDslResponse;
import com.hex.bigdata.udsp.iq.provider.util.Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by junjiem on 2017-3-3.
 */
public class HBaseProvider implements Provider {

    private static Logger logger = LogManager.getLogger (HBaseProvider.class);
    private static final FastDateFormat format8 = FastDateFormat.getInstance ("yyyyMMdd");
    private static final FastDateFormat format10 = FastDateFormat.getInstance ("yyyy-MM-dd");
    private static final FastDateFormat format17 = FastDateFormat.getInstance ("yyyyMMdd HH:mm:ss");
    private static final FastDateFormat format19 = FastDateFormat.getInstance ("yyyy-MM-dd HH:mm:ss");

    @Override
    public IqResponse query(IqRequest request) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request));
        long bef = System.currentTimeMillis ();
        IqResponse response = new IqResponse ();

        try {
            Application application = request.getApplication ();
            Metadata metadata = application.getMetadata ();
            List<QueryColumn> queryColumns = application.getQueryColumns ();
            Collections.sort (queryColumns, new Comparator<QueryColumn> () {
                @Override
                public int compare(QueryColumn obj1, QueryColumn obj2) {
                    return obj1.getSeq ().compareTo (obj2.getSeq ());
                }
            });
            List<Map<String, String>> records = getRecords (metadata, queryColumns);
            List<OrderColumn> orderColumns = application.getOrderColumns ();
            records = Util.orderBy (records, orderColumns); // 排序处理
            List<ReturnColumn> returnColumns = application.getReturnColumns ();
            response.setRecords (Util.tranRecords (records, returnColumns)); // 字段过滤并字段名改别名
            response.setStatus (Status.SUCCESS);
            response.setStatusCode (StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace ();
            response.setStatus (Status.DEFEAT);
            response.setStatusCode (StatusCode.DEFEAT);
            response.setMessage (e.getMessage ());
            logger.warn (e.toString ());
        }

        long now = System.currentTimeMillis ();
        long consumeTime = now - bef;
        response.setConsumeTime (consumeTime);

        logger.debug ("consumeTime=" + response.getConsumeTime ());
        return response;
    }

    @Override
    public IqResponse query(IqRequest request, Page page) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request)
                + " pageIndex=" + page.getPageIndex () + " pageSize=" + page.getPageSize ());
        long bef = System.currentTimeMillis ();
        IqResponse response = new IqResponse ();

        try {
            Application application = request.getApplication ();
            Metadata metadata = application.getMetadata ();
            List<QueryColumn> queryColumns = application.getQueryColumns ();
            Collections.sort (queryColumns, new Comparator<QueryColumn> () {
                @Override
                public int compare(QueryColumn obj1, QueryColumn obj2) {
                    return obj1.getSeq ().compareTo (obj2.getSeq ());
                }
            });
            HBasePage hbasePage = getHBasePage (metadata, queryColumns, page);
            List<Map<String, String>> records = hbasePage.getRecords ();
            // TODO 这里是分页后再排序的，实际上没有满足排序分页的要求
            List<OrderColumn> orderColumns = application.getOrderColumns ();
            records = Util.orderBy (records, orderColumns); // 排序处理
            List<ReturnColumn> returnColumns = application.getReturnColumns ();
            records = Util.tranRecords (records, returnColumns); // 字段过滤并字段名改别名
            response.setRecords (records);
            page.setTotalCount (hbasePage.getTotalCount ());
            response.setPage (page);
            response.setStatus (Status.SUCCESS);
            response.setStatusCode (StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace ();
            response.setStatus (Status.DEFEAT);
            response.setStatusCode (StatusCode.DEFEAT);
            response.setMessage (e.getMessage ());
            logger.warn (e.toString ());
        }

        long now = System.currentTimeMillis ();
        long consumeTime = now - bef;
        response.setConsumeTime (consumeTime);

        logger.debug ("consumeTime=" + response.getConsumeTime ());
        return response;
    }

    private HBasePage getHBasePage(Metadata metadata, List<QueryColumn> queryColumns, Page page) throws Exception {
        List<DataColumn> metaReturnColumns = metadata.getReturnColumns ();
        String tbName = metadata.getTbName ();
        HBaseMetadata hbaseMetadata = new HBaseMetadata (metadata.getPropertyMap ());
        HBaseDatasource hbaseDatasource = new HBaseDatasource (metadata.getDatasource ());
        String startRow = getStartRow (queryColumns);
        String stopRow = getStopRow (queryColumns);
        logger.debug ("startRow:" + startRow + ", startRow:" + startRow);
        Map<Integer, String> colMap = getColMap (metaReturnColumns);
        HBasePage hbasePage = new HBasePage (page.getPageSize (), page.getPageIndex (), startRow, stopRow);
        return HBaseUtil.scanPage (hbaseDatasource, tbName, hbasePage, colMap, hbaseMetadata);
    }

    private List<Map<String, String>> getRecords(Metadata metadata, List<QueryColumn> queryColumns) throws Exception {
        if (queryColumns == null || queryColumns.size () == 0) {
            throw new IllegalArgumentException ("HBase需要一至多个查询字段进行查询");
        }
        List<DataColumn> metaReturnColumns = metadata.getReturnColumns ();
        String tbName = metadata.getTbName ();
        HBaseMetadata hbaseMetadata = new HBaseMetadata (metadata.getPropertyMap ());
        HBaseDatasource hbaseDatasource = new HBaseDatasource (metadata.getDatasource ());
        String startRow = getStartRow (queryColumns);
        String stopRow = getStopRow (queryColumns);
        logger.debug ("startRow:" + startRow + ", startRow:" + startRow);
        Map<Integer, String> colMap = getColMap (metaReturnColumns);
        return HBaseUtil.scan (hbaseDatasource, tbName, startRow, stopRow, colMap, hbaseMetadata);
    }

    private String getStartRow(List<QueryColumn> queryColumns) {
        boolean optionalIsNull = false;
        boolean nextOptionalIsNull = true;
        boolean rangeIsNull = true;
        String startRow = getMd5Str (queryColumns);
        for (QueryColumn queryColumn : queryColumns) {
            String label = queryColumn.getLabel ();
            Operator operator = queryColumn.getOperator ();
            boolean isNeed = queryColumn.isNeed ();
//            DataType dataType = queryColumn.getType();
            String value = queryColumn.getValue ();
            int length = getLen (queryColumn.getLength ());
            if (isNeed && StringUtils.isBlank (value)) {
                throw new IllegalArgumentException ("必输项" + label + "值不可为空");
            }
            if (!Operator.EQ.equals (operator) && !Operator.GE.equals (operator) && !Operator.LE.equals (operator)) {
                throw new IllegalArgumentException ("只支持等于、大于等于和小于等于操作");
            }
            // 只能是等于或大于等于操作
            if (Operator.EQ.equals (operator) || Operator.GE.equals (operator)) {
                if (isNeed && Operator.EQ.equals (operator)) { // 必填且等于操作
                    value = realValue (value, length); // 右补齐空格
                } else if (!isNeed && Operator.EQ.equals (operator)) { // 选填且等于操作
                    if (StringUtils.isNotBlank (value)) { // 不为空
                        value = realValue (value, length); // 右补齐空格
                        if (optionalIsNull) {
                            nextOptionalIsNull = false;
                        }
                    } else {
                        optionalIsNull = true;
                    }
                } else if (Operator.GE.equals (operator)) { // 大于等于操作
                    if (StringUtils.isNotBlank (value)) { // 不为空
                        value = tarnDateStr (length, value); // 日期格式转换
                        if (optionalIsNull) {
                            rangeIsNull = false;
                        }
                    }
                }
                if (StringUtils.isNotBlank (value)) {
                    startRow += (StringUtils.isBlank (startRow) ? value : HBaseUtil.rkSep + value);
                }
            }
            if (!nextOptionalIsNull) {
                throw new IllegalArgumentException ("选填项值不为空时，其前面的选填项值也不可为空");
            }
            if (!rangeIsNull) {
                throw new IllegalArgumentException ("范围开始值不为空时，其前面的选填项值也不可为空");
            }
            if (Operator.GE.equals (operator)) {
                break; // 退出
            }
        }
        return startRow;
    }

    private String getStopRow(List<QueryColumn> queryColumns) {
        boolean optionalIsNull = false;
        boolean nextOptionalIsNull = true;
        boolean rangeIsNull = true;
        String stopRow = getMd5Str (queryColumns);
        for (QueryColumn queryColumn : queryColumns) {
            String label = queryColumn.getLabel ();
            Operator operator = queryColumn.getOperator ();
            boolean isNeed = queryColumn.isNeed ();
//            DataType dataType = queryColumn.getType();
            String value = queryColumn.getValue ();
            int length = getLen (queryColumn.getLength ());
            if (queryColumn.isNeed () && StringUtils.isBlank (value)) {
                throw new IllegalArgumentException ("必输项" + label + "值不可为空");
            }
            if (!Operator.EQ.equals (operator) && !Operator.GE.equals (operator) && !Operator.LE.equals (operator)) {
                throw new IllegalArgumentException ("HBase只支持等于、大于等于和小于等于操作");
            }
            // 只能是等于或小于等于操作
            if (Operator.EQ.equals (operator) || Operator.LE.equals (operator)) {
                if (isNeed && Operator.EQ.equals (operator)) { // 必填且等于操作
                    value = realValue (value, length); // 右补齐空格
                } else if (!isNeed && Operator.EQ.equals (operator)) { // 选填且等于操作
                    if (StringUtils.isNotBlank (value)) { // 不为空
                        value = realValue (value, length); // 右补齐空格
                        if (optionalIsNull) {
                            nextOptionalIsNull = false;
                        }
                    } else {
                        optionalIsNull = true;
                    }
                } else if (Operator.LE.equals (operator)) { // 小于等于操作
                    if (StringUtils.isNotBlank (value)) { // 不为空
                        value = tarnDateStr (length, value); // 日期格式转换
                        if (optionalIsNull) {
                            rangeIsNull = false;
                        }
                    }
                }
                if (StringUtils.isNotBlank (value)) {
                    stopRow += (StringUtils.isBlank (stopRow) ? value : HBaseUtil.rkSep + value);
                }
            }
            if (!nextOptionalIsNull) {
                throw new IllegalArgumentException ("选填项值不为空时，其前面的选填项值也不可为空");
            }
            if (!rangeIsNull) {
                throw new IllegalArgumentException ("范围结束值不为空时，其前面的选填项值也不可为空");
            }
            if (Operator.LE.equals (operator)) {
                break; // 退出
            }
        }
        return stopRow;
    }

    private String tarnDateStr(int length, String value) {
        if (length == 8 || length == 10 || length == 17 || length == 19) {
            Date date = strToDate (value);
            if (date != null) {
                if (length == 8) {
                    value = format8.format (date);
                } else if (length == 10) {
                    value = format10.format (date);
                } else if (length == 17) {
                    value = format17.format (date);
                } else if (length == 19) {
                    value = format19.format (date);
                }
            }
        }
        return value;
    }

    private Date strToDate(String dataStr) {
        Date date = null;
        try {
            if (dataStr.length () == 8) {
                date = format8.parse (dataStr);
            } else if (dataStr.length () == 10) {
                date = format10.parse (dataStr.replaceAll ("/", "-"));
            } else if (dataStr.length () == 17) {
                date = format17.parse (dataStr);
            } else if (dataStr.length () == 19) {
                date = format19.parse (dataStr.replaceAll ("/", "-"));
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException ("日期字段传入的不是日期格式字符串参数");
        }
        return date;
    }

    private int getLen(String length) {
        int len = 0;
        if (StringUtils.isNotBlank (length) && StringUtils.isNumeric (length)) {
            try {
                len = Integer.valueOf (length);
            } catch (Exception e) {
                logger.debug (ExceptionUtil.getMessage (e));
            }
        }
        return len;
    }

    private String getMd5Str(List<QueryColumn> queryColumns) {
        String str = "";
        int count = 0;
        for (QueryColumn queryColumn : queryColumns) {
            if (queryColumn.isNeed () && Operator.EQ.equals (queryColumn.getOperator ())) {
                String value = queryColumn.getValue ();
                if (StringUtils.isBlank (value)) {
                    throw new IllegalArgumentException ("必输项值为空");
                }
                str += (count == 0 ? value : HBaseUtil.rkSep + value);
                count++;
            } else {
                break;
            }
        }
        if (StringUtils.isNotBlank (str)) {
            str = md5_16 (str);
        }
        return str;
    }

    private Map<Integer, String> getColMap(List<DataColumn> returnColumns) {
        Map<Integer, String> colMap = new HashMap<Integer, String> ();
        Collections.sort (returnColumns, new Comparator<DataColumn> () {
            @Override
            public int compare(DataColumn obj1, DataColumn obj2) {
                return obj1.getSeq ().compareTo (obj2.getSeq ());
            }
        });
        for (int i = 0; i < returnColumns.size (); i++) {
            colMap.put (i + 1, returnColumns.get (i).getName ());
        }
        return colMap;
    }

    //得到16位的MD5
    private String md5_16(String str) {
        return DigestUtils.md5Hex (str).substring (8, 24);
    }

    //判断字符串长度，不足补空格
    private String realValue(String value, int length) {
        int len = 0;
        try {
            len = countCode (value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        if (len < length) {
            for (int i = 0; i < length - len; i++) {
                value = value + ' ';
            }
        }
        return value;
    }

    // 获取不同编码的字符串长度
    private int countCode(String str, String code) throws UnsupportedEncodingException {
        return str.getBytes (code).length;
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        HBaseDatasource hBaseDatasource = new HBaseDatasource (datasource);
        return !HBaseUtil.isAborted (hBaseDatasource);
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        return null;
    }

    @Override
    public IqDslResponse select(IqDslRequest request) {
        logger.debug ("request=" + JSONUtil.parseObj2JSON (request));
        long bef = System.currentTimeMillis ();
        IqDslResponse response = new IqDslResponse ();

        try {
            Metadata metadata = request.getMetadata ();
            Map<String, DataColumn> columns = new HashMap<> ();
            for (DataColumn column : metadata.getQueryColumns ()) {
                columns.put (column.getName (), column);
            }
            Component component = request.getComponent ();
            List<QueryColumn> queryColumns = component (component, columns); // Component递归解析成List<QueryColumn>

            List<Map<String, String>> records = getRecords (metadata, queryColumns);
            response.setRecords (records);
            response.setStatus (Status.SUCCESS);
            response.setStatusCode (StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace ();
            response.setStatus (Status.DEFEAT);
            response.setStatusCode (StatusCode.DEFEAT);
            response.setMessage (e.getMessage ());
            logger.warn (e.toString ());
        }

        long now = System.currentTimeMillis ();
        long consumeTime = now - bef;
        response.setConsumeTime (consumeTime);

        logger.debug ("consumeTime=" + response.getConsumeTime ());
        return response;
    }

    private List<QueryColumn> component(Component component, Map<String, DataColumn> columns) {
        List<QueryColumn> queryColumns = new ArrayList<> ();
        if (component instanceof Composite) {
            Composite composite = (Composite) component;
            LogicalOperator logiOper = composite.getLogiOper ();
            if (LogicalOperator.OR == logiOper) {
                throw new IllegalArgumentException ("HBase只支持AND查询");
            }
            List<Component> components = composite.getComponents ();
            for (Component c : components) {
                queryColumns.addAll (component (c, columns));
            }
        } else if (component instanceof Dimension) {
            Dimension dimension = (Dimension) component;
            DataColumn column = columns.get (dimension.getColumnName ());
            queryColumns.add (getQueryColumn (dimension, column));
        }
        return queryColumns;
    }

    private QueryColumn getQueryColumn(Dimension dimension, DataColumn column) {
        String columnName = dimension.getColumnName ();
        ComparisonOperator compOper = dimension.getCompOper ();
        List<String> values = dimension.getValues ();
        QueryColumn queryColumn = new QueryColumn ();
        queryColumn.setName (columnName);
        queryColumn.setLabel (columnName);
        Operator operator = null;
        switch (compOper) {
            case EQ:
                operator = Operator.EQ;
                break;
            case GE:
                operator = Operator.GE;
                break;
            case LE:
                operator = Operator.LE;
                break;
            case NE:
            case NE2:
            case GT:
            case LT:
            case IN:
            case LIKE:
            case NOT_LIKE:
            case NOT_IN:
            case IS_NOT_NULL:
            case IS_NULL:
            case BETWEEN_AND:
            default:
                throw new IllegalArgumentException ("HBase只支持等于、大于等于和小于等于操作");
        }
        queryColumn.setOperator (operator);
        if (values != null && values.size () != 0 && ComparisonOperator.EQ == compOper) {
            queryColumn.setNeed (true);
        }
        if (values != null && values.size () != 0) {
            queryColumn.setValue (values.get (0));
        }
        queryColumn.setLength (column.getLength ());
        return queryColumn;
    }

}
