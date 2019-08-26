package com.hex.bigdata.udsp.dsl;

import com.hex.bigdata.udsp.dsl.constant.ColumnType;
import com.hex.bigdata.udsp.dsl.constant.ComparisonOperator;
import com.hex.bigdata.udsp.dsl.constant.LogicalOperator;
import com.hex.bigdata.udsp.dsl.constant.OrderExpression;
import com.hex.bigdata.udsp.dsl.model.*;
import com.hex.bigdata.udsp.dsl.parser.DSLSQLLexer;
import com.hex.bigdata.udsp.dsl.parser.DSLSQLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DSLSQL的适配器
 */
public class DslSqlAdaptor {
    private static Logger logger = LogManager.getLogger (DslSqlAdaptor.class);

    private static final String ALTERNATIVE_OPERATOR = "${subSelectSql}";

    /**
     * 获取DSLSQLParser
     *
     * @param sql
     * @return
     * @throws RuntimeException
     */
    public static DSLSQLParser getDSLSQLParser(String sql) throws RuntimeException {
        // 新建一个输入流，从标准输入读取数据
        ANTLRInputStream input = new ANTLRInputStream (sql);
        // 新建一个词法分析器，处理输入的字符流
        DSLSQLLexer lexer = new DSLSQLLexer (input);
        lexer.addErrorListener (DescriptiveErrorListener.INSTANCE); // 设置错误处理的监听类
        // 新建一个词法符号的缓冲区，用于储存词法分析器将生成的词法符号
        CommonTokenStream tokens = new CommonTokenStream (lexer);
        // 新建一个语法分析器，处理词法符号缓冲区中的内容
        DSLSQLParser parser = new DSLSQLParser (tokens);
//        parser.setErrorHandler(new BailErrorStrategy ()); // 设置错误处理的策略类
        parser.addErrorListener (DescriptiveErrorListener.INSTANCE); // 设置错误处理的监听类
        return parser;
    }

    /**
     * 获取真实的文本，包含空格、指标符和换行符
     *
     * @param sql
     * @param context
     * @return
     */
    public static String getText(String sql, ParserRuleContext context) {
        int startIndex = context.getStart ().getStartIndex ();
        int stopIndex = context.getStop ().getStopIndex ();
        return sql.substring (startIndex, stopIndex + 1);
    }

    /**
     * SQL字符串解析成对象
     *
     * @param sql SQL语法
     * @return
     */
    public static DslSelectSql sqlToObject(String sql) {
        DSLSQLParser parser = DslSqlAdaptor.getDSLSQLParser (sql);
        DSLSQLParser.SelectStatementContext selectStatementContext = parser.selectStatement ();
        logger.debug (getText (sql, selectStatementContext));
        // lowest SelectStatementContext
        DSLSQLParser.SelectStatementContext lowestSelectStatementContext = lowestSelectStatementContext (selectStatementContext);
        // serviceName
        DSLSQLParser.ServiceNameContext serviceNameContext = lowestSelectStatementContext.subSelectStatement ().serviceName ();
        String serviceName = serviceNameContext.getText ();
        // select
        String select = null;
        DSLSQLParser.SelectElementsContext selectElementsContext = lowestSelectStatementContext.selectElements ();
        if (selectElementsContext != null) {
            select = getText (sql, selectElementsContext);
        }
        // where
        Component where = null;
        DSLSQLParser.WhereClauseContext whereClauseContext = lowestSelectStatementContext.whereClause ();
        if (whereClauseContext != null) {
            DSLSQLParser.LogicExpressionsContext logicExpressionsContext = whereClauseContext.logicExpressions ();
            where = DslSqlAdaptor.logicExpressionsContextToComponent (logicExpressionsContext);
        }
        // group by
        List<String> groupBy = null;
        DSLSQLParser.GroupByCaluseContext groupByCaluseContext = lowestSelectStatementContext.groupByCaluse ();
        if (groupByCaluseContext != null) {
            groupBy = DslSqlAdaptor.groupByCaluseContextToGroupBy (groupByCaluseContext);
        }
        // order by
        List<Order> orderBy = null;
        DSLSQLParser.OrderByClauseContext orderByClauseContext = lowestSelectStatementContext.orderByClause ();
        if (orderByClauseContext != null) {
            orderBy = DslSqlAdaptor.orderByClauseContextToOrderBy (orderByClauseContext);
        }
        // limit
        Limit limit = null;
        DSLSQLParser.LimitClauseContext limitClauseContext = lowestSelectStatementContext.limitClause ();
        if (limitClauseContext != null) {
            limit = DslSqlAdaptor.limitClauseContextToLimit (limitClauseContext);
        }
        // 封装DslSql
        DslSql dslSql = new DslSql ();
        dslSql.setName (serviceName);
        dslSql.setSelect (select);
        dslSql.setWhere (where);
        dslSql.setGroupBy (groupBy);
        dslSql.setOrderBy (orderBy);
        dslSql.setLimit (limit);
        // 封装DslSelectSql
        DslSelectSql dslSelectSql = new DslSelectSql ();
        dslSelectSql.setDslSql (dslSql);
        logger.info ("sql: " + sql);
        dslSelectSql.setSourceSql (sql);
        String fakeSql = getFakeSql (sql, lowestSelectStatementContext);
        logger.info ("fakeSql: " + fakeSql);
        dslSelectSql.setFakeSql (fakeSql);
        return dslSelectSql;
    }

    private static String getFakeSql(String sql, ParserRuleContext context) {
        int startIndex = context.getStart ().getStartIndex ();
        int stopIndex = context.getStop ().getStopIndex ();
        String fakeSqlPrefix = sql.substring (0, startIndex);
        String fakeSqlSuffix = sql.substring (stopIndex + 1);
        return fakeSqlPrefix + ALTERNATIVE_OPERATOR + fakeSqlSuffix;
    }

    /**
     * 递归获取最底层的子Select SQL对象
     *
     * @param selectStatementContext
     * @return
     */
    public static DSLSQLParser.SelectStatementContext lowestSelectStatementContext(DSLSQLParser.SelectStatementContext selectStatementContext) {
        DSLSQLParser.SubSelectStatementContext subSelectStatementContext = selectStatementContext.subSelectStatement ();
        if (subSelectStatementContext.getChildCount () == 1) {
            return selectStatementContext;
        }
        return lowestSelectStatementContext (subSelectStatementContext.selectStatement ());
    }

    private static String textLiteralToValue(String textLiteral) {
        return textLiteral.substring (1, textLiteral.length () - 1); // 去除首尾的单引号
    }

    public static String textLiteralContextToValue(DSLSQLParser.TextLiteralContext textLiteralContext) {
        return textLiteralToValue (textLiteralContext.getText ());
    }

    private static String valueContextToValue(DSLSQLParser.ValueContext valueContext) {
        String value = null;
        DSLSQLParser.TextLiteralContext textLiteralContext = valueContext.textLiteral ();
        if (textLiteralContext != null) {
            value = textLiteralContextToValue (textLiteralContext);
        } else {
            value = valueContext.decimalLiteral ().getText ();
        }
        return value;
    }

    private static ColumnType valueContextToColumnType(DSLSQLParser.ValueContext valueContext) {
        DSLSQLParser.TextLiteralContext textLiteralContext = valueContext.textLiteral ();
        if (textLiteralContext != null) {
            return ColumnType.STRING;
        } else {
            return ColumnType.NUMBER;
        }
    }

    private static ComparisonOperator transComparisonOperator(String operator) {
        operator = operator.toUpperCase ();
        ComparisonOperator comparison = ComparisonOperator.EQ;
        if (ComparisonOperator.EQ.getValue ().equals (operator)) {
            comparison = ComparisonOperator.EQ;
        } else if (ComparisonOperator.NE.getValue ().equals (operator)) {
            comparison = ComparisonOperator.NE;
        } else if (ComparisonOperator.NE2.getValue ().equals (operator)) {
            comparison = ComparisonOperator.NE2;
        } else if (ComparisonOperator.GE.getValue ().equals (operator)) {
            comparison = ComparisonOperator.GE;
        } else if (ComparisonOperator.LE.getValue ().equals (operator)) {
            comparison = ComparisonOperator.LE;
        } else if (ComparisonOperator.GT.getValue ().equals (operator)) {
            comparison = ComparisonOperator.GT;
        } else if (ComparisonOperator.LT.getValue ().equals (operator)) {
            comparison = ComparisonOperator.LT;
        } else if (ComparisonOperator.IN.getValue ().equals (operator)) {
            comparison = ComparisonOperator.IN;
        } else if (ComparisonOperator.NOT_IN.getValue ().equals (operator)) {
            comparison = ComparisonOperator.NOT_IN;
        } else if (ComparisonOperator.LIKE.getValue ().equals (operator)) {
            comparison = ComparisonOperator.LIKE;
        } else if (ComparisonOperator.NOT_LIKE.getValue ().equals (operator)) {
            comparison = ComparisonOperator.NOT_LIKE;
        } else if (ComparisonOperator.BETWEEN_AND.getValue ().equals (operator)) {
            comparison = ComparisonOperator.BETWEEN_AND;
        } else if (ComparisonOperator.IS_NULL.getValue ().equals (operator)) {
            comparison = ComparisonOperator.IS_NULL;
        } else if (ComparisonOperator.IS_NOT_NULL.getValue ().equals (operator)) {
            comparison = ComparisonOperator.IS_NOT_NULL;
        }
        return comparison;
    }

    private static OrderExpression transOrderExpression(String order) {
        OrderExpression expression = OrderExpression.ASC;
        if (OrderExpression.DESC.getValue ().equals (order)) {
            expression = OrderExpression.DESC;
        }
        return expression;
    }

    /**
     * DSLSQLParser.LogicExpressionsContext 转 Component
     *
     * @param logicExpressionsContext
     * @return
     */
    private static Component logicExpressionsContextToComponent(DSLSQLParser.LogicExpressionsContext logicExpressionsContext) {
        int count = logicExpressionsContext.getChildCount ();
        // 拆分OR逻辑操作
        Map<Integer, Integer> map = new HashMap<> ();
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < count; i++) {
            if (i % 2 == 1) { // 逻辑操作符
                DSLSQLParser.LogicalOperatorContext logicalOperatorContext = (DSLSQLParser.LogicalOperatorContext) logicExpressionsContext.getChild (i);
                if (logicalOperatorContext.OR () != null) {
                    endIndex = i - 1;
                    map.put (startIndex, endIndex);
                    startIndex = i + 1;
                }
            }
        }
        map.put (startIndex, count - 1);
        // 处理每个拆分的逻辑操作
        List<Component> components = new ArrayList<> ();
        for (Map.Entry<Integer, Integer> entry : map.entrySet ()) {
            Component component = logicExpressionsContextToComponent (logicExpressionsContext, entry.getKey (), entry.getValue ());
            components.add (component);
        }
        if (components.size () == 1) {
            return components.get (0);
        } else {
            return new Composite (LogicalOperator.OR, components);
        }
    }

    private static Component logicExpressionsContextToComponent(DSLSQLParser.LogicExpressionsContext logicExpressionsContext, int startIndex, int endIndex) {
        List<Component> components = new ArrayList<> ();
        for (int i = startIndex; i <= endIndex; i++) {
            if (i % 2 == 0) { // 逻辑操作
                DSLSQLParser.LogicExpressionContext logicExpressionContext = (DSLSQLParser.LogicExpressionContext) logicExpressionsContext.getChild (i);
                Component component = logicExpressionContextToComponent (logicExpressionContext);
                components.add (component);
            }
        }
        if (components.size () == 1) {
            return components.get (0);
        } else {
            return new Composite (LogicalOperator.AND, components);
        }
    }

    /**
     * DSLSQLParser.LogicExpressionContext 转 Component
     *
     * @param logicExpressionContext
     * @return
     */
    private static Component logicExpressionContextToComponent(DSLSQLParser.LogicExpressionContext logicExpressionContext) {
        Dimension dimension = null;
        ComparisonOperator comparison = null;
        List<String> values = new ArrayList<> ();
        DSLSQLParser.LogicExpressionsContext logicExpressionsContext = logicExpressionContext.logicExpressions ();
        if (logicExpressionsContext != null) { // '(' logicExpressions ')'
            return logicExpressionsContextToComponent (logicExpressionsContext);
        } else { // 非'(' logicExpressions ')'
            List<DSLSQLParser.FullColumnNameContext> fullColumnNameContexts = logicExpressionContext.fullColumnName ();
            if (fullColumnNameContexts.size () == 2) { // fullColumnName comparisonOperator fullColumnName
                String leftName = fullColumnNameContexts.get (0).columnName ().getText (); // left columnName
                String rightName = fullColumnNameContexts.get (1).columnName ().getText (); // right columnName
                String operator = logicExpressionContext.comparisonOperator ().getText ();
                comparison = transComparisonOperator (operator);
                dimension = new Dimension (leftName, comparison);
            } else { // other
                String name = fullColumnNameContexts.get (0).columnName ().getText (); // columnName
                List<DSLSQLParser.ValueContext> valueContexts = logicExpressionContext.value (); // values
                if (logicExpressionContext.comparisonOperator () != null) { // fullColumnName comparisonOperator value
                    String operator = logicExpressionContext.comparisonOperator ().getText ();
                    comparison = transComparisonOperator (operator);
                    DSLSQLParser.ValueContext valueContext = valueContexts.get (0);
                    values.add (valueContextToValue (valueContext));
                    ColumnType columnType = valueContextToColumnType (valueContext);
                    dimension = new Dimension (name, comparison, columnType, values);
                } else if (logicExpressionContext.BETWEEN () != null && logicExpressionContext.AND () != null) { // fullColumnName BETWEEN value AND value
                    DSLSQLParser.ValueContext startValueContext = valueContexts.get (0);
                    DSLSQLParser.ValueContext endValueContext = valueContexts.get (1);
                    values.add (valueContextToValue (startValueContext));
                    values.add (valueContextToValue (endValueContext));
                    ColumnType columnType = valueContextToColumnType (startValueContext);
                    dimension = new Dimension (name, ComparisonOperator.BETWEEN_AND, columnType, values);
                } else if (logicExpressionContext.IN () != null) { // fullColumnName NOT? IN '(' value (',' value)*  ')'
                    if (logicExpressionContext.NOT () != null) { // NOT IN
                        comparison = ComparisonOperator.NOT_IN;
                    } else { // IN
                        comparison = ComparisonOperator.IN;
                    }
                    for (DSLSQLParser.ValueContext valueContext : valueContexts) {
                        values.add (valueContextToValue (valueContext));
                    }
                    ColumnType columnType = valueContextToColumnType (valueContexts.get (0));
                    dimension = new Dimension (name, comparison, columnType, values);
                } else if (logicExpressionContext.IS () != null && logicExpressionContext.NULL () != null) { // fullColumnName IS NOT? NULL
                    if (logicExpressionContext.NOT () != null) { // IS NOT NULL
                        comparison = ComparisonOperator.IS_NOT_NULL;
                    } else { // IS NULL
                        comparison = ComparisonOperator.IS_NULL;
                    }
                    dimension = new Dimension (name, comparison);
                }
            }
        }
        return dimension;
    }

    private static Limit limitClauseContextToLimit(DSLSQLParser.LimitClauseContext limitClauseContext) {
        int offset = 0;
        int limit = 1;
        DSLSQLParser.DecimalLiteralContext limitContext = limitClauseContext.limit;
        DSLSQLParser.DecimalLiteralContext offsetContext = limitClauseContext.offset;
        if (limitContext != null) {
            limit = Integer.valueOf (limitContext.getText ());
        }
        if (offsetContext != null) {
            offset = Integer.valueOf (offsetContext.getText ());
        }
        return new Limit (limit, offset);
    }

    private static List<String> groupByCaluseContextToGroupBy(DSLSQLParser.GroupByCaluseContext groupByCaluseContext) {
        List<String> list = new ArrayList<> ();
        List<DSLSQLParser.GroupByItemContext> groupByItemContexts = groupByCaluseContext.groupByItem ();
        for (DSLSQLParser.GroupByItemContext groupByItemContext : groupByItemContexts) {
            list.add (groupByItemContext.fullColumnName ().columnName ().getText ());
        }
        return list;
    }

    private static List<Order> orderByClauseContextToOrderBy(DSLSQLParser.OrderByClauseContext orderByClauseContext) {
        List<Order> list = new ArrayList<> ();
        List<DSLSQLParser.OrderByExpressionContext> orderByExpressionContexts = orderByClauseContext.orderByExpression ();
        for (DSLSQLParser.OrderByExpressionContext orderByExpressionContext : orderByExpressionContexts) {
            String name = orderByExpressionContext.fullColumnName ().columnName ().getText ();
            OrderExpression expression = OrderExpression.ASC;
            if (orderByExpressionContext.order != null) {
                expression = transOrderExpression (orderByExpressionContext.order.getText ());
            }
            list.add (new Order (name, expression));
        }
        return list;
    }

    /**
     * 对象解析成SQL字符串
     *
     * @param dslSelectSql
     * @return
     */
    public static String objectToSql(DslSelectSql dslSelectSql) {
        String fakeSql = dslSelectSql.getFakeSql ();
        logger.info ("fakeSql: " + fakeSql);
        String subSql = dslSqlToSql (dslSelectSql.getDslSql ());
        logger.info ("subSql: " + subSql);
        String sql = fakeSql.replace (ALTERNATIVE_OPERATOR, subSql);
        logger.info ("sql: " + sql);
        return sql;
    }

    private static String dslSqlToSql(DslSql dslSql) {
        return selectToStatement (dslSql.getSelect ())
                + fromToStatement (dslSql.getName ())
                + whereToStatement (dslSql.getWhere ())
                + groupByToStatement (dslSql.getGroupBy ())
                + orderByToStatement (dslSql.getOrderBy ())
                + limitToStatement (dslSql.getLimit ());
    }

    private static String fromToStatement(String tableName) {
        return " FROM " + tableName;
    }

    private static String selectToStatement(String select) {
        if (StringUtils.isBlank (select)) {
            return "";
        }
        return "SELECT " + select;
    }

    private static String orderByToStatement(List<Order> orderBy) {
        if (orderBy == null || orderBy.size () == 0) {
            return "";
        }
        String str = " ORDER BY ";
        for (int i = 0, l = orderBy.size (); i < l; i++) {
            str += (i == 0 ? "" : ", ") + orderToStatement (orderBy.get (i));
        }
        return str;
    }

    private static String orderToStatement(Order order) {
        String str = order.getName ();
        OrderExpression orderExp = order.getOrderExp ();
        if (orderExp != null) {
            str = getOrderExp (orderExp, str);
        }
        return str;
    }

    private static String getOrderExp(OrderExpression orderExp, String column) {
        switch (orderExp.getValue ()) {
            case "DESC":
                return column + " DESC";
            default:
                return column + " ASC";
        }
    }

    private static String groupByToStatement(List<String> groupBy) {
        if (groupBy == null || groupBy.size () == 0) {
            return "";
        }
        String str = " GROUP BY ";
        for (int i = 0, l = groupBy.size (); i < l; i++) {
            str += (i == 0 ? "" : ", ") + groupBy.get (i);
        }
        return str;
    }

    private static String whereToStatement(Component where) {
        if (where == null) {
            return "";
        }
        return " WHERE " + componentToStatement (where);
    }

    /**
     * 递归解析Component
     *
     * @param component
     * @return
     */
    public static String componentToStatement(Component component) {
        if (component instanceof Dimension) {
            return dimensionToStatement ((Dimension) component);
        } else if (component instanceof Composite) {
            Composite composite = (Composite) component;
            LogicalOperator logiOper = composite.getLogiOper ();
            String str = "(";
            List<Component> components = composite.getComponents ();
            for (int i = 0, l = components.size (); i < l; i++) {
                str += (i == 0 ? "" : " " + logiOper.getValue () + " ");
                str += componentToStatement (components.get (i));
            }
            str += ")";
            return str;
        }
        return null;
    }

    private static String dimensionToStatement(Dimension dimension) {
        String columnName = dimension.getColumnName ();
        ComparisonOperator compOper = dimension.getCompOper ();
        List<String> values = getValues (dimension.getColumnType (), dimension.getValues ());
        switch (compOper.getValue ()) {
            case "=":
            case "!=":
            case "<>":
            case ">":
            case "<":
            case ">=":
            case "<=":
            case "LIKE":
            case "NOT LIKE":
                return (values == null || values.size () == 0) ? "1 = 1" :
                        columnName + " " + compOper.getValue () + " " + values.get (0);
            case "BETWEEN AND":
                return (values == null || values.size () < 2) ? "1 = 1" :
                        "(" + columnName + " >= " + values.get (0) + " AND " + columnName + " <= " + values.get (1) + ")";
            case "IN":
            case "NOT IN":
                return (values == null || values.size () == 0) ? "1 = 1" :
                        columnName + " " + compOper.getValue () + " (" + StringUtils.join (values, ",") + ")";
            case "IS NULL":
            case "IS NOT NULL":
                return columnName + " " + compOper.getValue ();
        }
        return null;
    }

    private static List<String> getValues(ColumnType columnType, List<String> values) {
        List<String> newValues = new ArrayList<> ();
        for (String value : values) {
            newValues.add (getValue (columnType, value));
        }
        return newValues;
    }

    private static String getValue(ColumnType columnType, String value) {
        switch (columnType.getValue ()) {
            case "STRING":
                return "'" + value + "'";
            default:
                return value;
        }
    }

    private static String limitToStatement(Limit limit) {
        if (limit == null || limit.getLimit () == 0) {
            return "";
        }
        String str = " LIMIT " + limit.getLimit ();
        if (limit.getOffset () > 0) {
            str += " OFFSET " + limit.getOffset ();
        }
        return str;
    }

}
