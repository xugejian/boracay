package com.hex.bigdata.udsp.dsl;

import com.hex.bigdata.udsp.dsl.constant.*;
import com.hex.bigdata.udsp.dsl.model.*;
import com.hex.bigdata.udsp.dsl.parser.DSLSQLLexer;
import com.hex.bigdata.udsp.dsl.parser.DSLSQLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DSLSQL的适配器
 */
public class DslSqlAdaptor {

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
            String name = logicExpressionContext.fullColumnName ().getText ();
            List<DSLSQLParser.ValueContext> valueContexts = logicExpressionContext.value ();
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

    private static List<Column> selectElementsContextToSelect(DSLSQLParser.SelectElementsContext selectElementsContext) {
        List<Column> list = new ArrayList<> ();
        if (selectElementsContext.star != null) {
            list.add (new Column ("*"));
        } else {
            List<DSLSQLParser.SelectElementContext> selectElementContexts = selectElementsContext.selectElement ();
            for (DSLSQLParser.SelectElementContext selectElementContext : selectElementContexts) {
                String alias = null;
                if (selectElementContext.AS () != null || selectElementContext.uid () != null) {
                    alias = selectElementContext.uid ().getText ();
                }
                DSLSQLParser.FullColumnNameContext fullColumnNameContext = selectElementContext.fullColumnName ();
                Aggregate aggregate = null;
                if (fullColumnNameContext != null) {
                    String name = fullColumnNameContext.columnName ().getText ();
                    aggregate = new Aggregate (name);
                } else {
                    DSLSQLParser.AggregateWindowedFunctionContext aggregateWindowedFunctionContext =
                            selectElementContext.functionCall ().aggregateWindowedFunction ();
                    String name = null;
                    AggregateFunction function = null;
                    if (aggregateWindowedFunctionContext.functionArg () != null) {
                        name = aggregateWindowedFunctionContext.functionArg ().columnName ().getText ();
                    }
                    if (aggregateWindowedFunctionContext.AVG () != null) { // AVG
                        function = AggregateFunction.AVG;
                    } else if (aggregateWindowedFunctionContext.MAX () != null) { // MAX
                        function = AggregateFunction.MAX;
                    } else if (aggregateWindowedFunctionContext.MIN () != null) { // MIN
                        function = AggregateFunction.MIN;
                    } else if (aggregateWindowedFunctionContext.SUM () != null) { // SUM
                        function = AggregateFunction.SUM;
                    } else if (aggregateWindowedFunctionContext.COUNT () != null) {
                        if (aggregateWindowedFunctionContext.DISTINCT () != null) { // COUNT_DISTINCT
                            function = AggregateFunction.COUNT_DISTINCT;
                        } else { // COUNT
                            if (aggregateWindowedFunctionContext.starArg != null) {
                                name = "*";
                            }
                            function = AggregateFunction.COUNT;
                        }
                    }
                    aggregate = new Aggregate (name, function);
                }
                list.add (new Column (alias, aggregate));
            }
        }
        return list;
    }

    /**
     * select SQL 解析成 DslRequest对象
     *
     * @param sql SQL语法
     * @return DslRequest
     */
    public static DslRequest selectSqlToDslRequest(String sql) {
        DSLSQLParser parser = DslSqlAdaptor.getDSLSQLParser (sql);
        DSLSQLParser.SelectStatementContext selectStatementContext = parser.selectStatement ();
        // serviceName
        DSLSQLParser.ServiceNameContext serviceNameContext = selectStatementContext.serviceName ();
        String serviceName = serviceNameContext.getText ();
        // select
        List<Column> select = null;
        DSLSQLParser.SelectElementsContext selectElementsContext = selectStatementContext.selectElements ();
        if (selectElementsContext != null) {
            select = DslSqlAdaptor.selectElementsContextToSelect (selectElementsContext);
        }
        // where
        Component where = null;
        DSLSQLParser.WhereClauseContext whereClauseContext = selectStatementContext.whereClause ();
        if (whereClauseContext != null) {
            DSLSQLParser.LogicExpressionsContext logicExpressionsContext = whereClauseContext.logicExpressions ();
            where = DslSqlAdaptor.logicExpressionsContextToComponent (logicExpressionsContext);
        }
        // group by
        List<String> groupBy = null;
        DSLSQLParser.GroupByCaluseContext groupByCaluseContext = selectStatementContext.groupByCaluse ();
        if (groupByCaluseContext != null) {
            groupBy = DslSqlAdaptor.groupByCaluseContextToGroupBy (groupByCaluseContext);
        }
        // order by
        List<Order> orderBy = null;
        DSLSQLParser.OrderByClauseContext orderByClauseContext = selectStatementContext.orderByClause ();
        if (orderByClauseContext != null) {
            orderBy = DslSqlAdaptor.orderByClauseContextToOrderBy (orderByClauseContext);
        }
        // limit
        Limit limit = null;
        DSLSQLParser.LimitClauseContext limitClauseContext = selectStatementContext.limitClause ();
        if (limitClauseContext != null) {
            limit = DslSqlAdaptor.limitClauseContextToLimit (limitClauseContext);
        }
        // 封装
        DslRequest dslRequest = new DslRequest ();
        dslRequest.setName (serviceName);
        dslRequest.setSelect (select);
        dslRequest.setWhere (where);
        dslRequest.setGroupBy (groupBy);
        dslRequest.setOrderBy (orderBy);
        dslRequest.setLimit (limit);
        return dslRequest;
    }

    private String getSelectStatement(List<Column> columns) {
        if (columns == null || columns.size () == 0) {
            return "";
        }
        String str = "";
        for (int i = 0, l = columns.size (); i < l; i++) {
            str += (i == 0 ? "\n" : "\n, ") + getSelectStatement (columns.get (i));
        }
        return str;
    }

    private static String getSelectStatement(Column column) {
        String alias = column.getAlias ();
        Aggregate aggregate = column.getAggregate ();
        String str = aggregate.getName ();
        AggregateFunction aggFun = aggregate.getAggFun ();
        if (aggFun != null) {
            str = getAggExp (aggFun, str);
        }
        if (StringUtils.isNotBlank (alias)) {
            str += " AS " + alias;
        }
        return str;
    }

    private static String getAggExp(AggregateFunction aggFun, String name) {
        switch (aggFun.getValue ()) {
            case "SUM":
                return "SUM(" + name + ")";
            case "AVG":
                return "AVG(" + name + ")";
            case "MAX":
                return "MAX(" + name + ")";
            case "MIN":
                return "MIN(" + name + ")";
            case "COUNT":
                return "COUNT(" + name + ")";
            case "COUNT DISTINCT":
                return "COUNT(DISTINCT " + name + ")";
        }
        return name;
    }

    private static String getOrderByStatement(List<Order> orderBy) {
        if (orderBy == null || orderBy.size () == 0) {
            return "";
        }
        String str = "ORDER BY";
        for (int i = 0, l = orderBy.size (); i < l; i++) {
            str += (i == 0 ? " " : ", ") + getOrderStatement (orderBy.get (i));
        }
        return str;
    }

    private static String getOrderStatement(Order order) {
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

    private static String getGroupByStatement(List<String> groupBy) {
        if (groupBy == null || groupBy.size () == 0) {
            return "";
        }
        String str = "GROUP BY";
        for (int i = 0, l = groupBy.size (); i < l; i++) {
            str += (i == 0 ? " " : ", ") + groupBy.get (i);
        }
        return str;
    }

    private static String getWhereStatement(Component where) {
        if (where == null) {
            return "";
        }
        return "WHERE " + getComponentStatement (where);
    }

    private static String getComponentStatement(Component component) {
        if (component instanceof Dimension) {
            return getDimensionStatement ((Dimension) component);
        } else if (component instanceof Composite) {
            Composite composite = (Composite) component;
            LogicalOperator logiOper = composite.getLogiOper ();
            List<Component> components = composite.getComponents ();
            String str = "";
            for (int i = 0, l = components.size (); i < l; i++) {
                str += (i == 0 ? " " : " " + logiOper.getValue () + " ") + getComponentStatement (components.get (i));
            }
            return str;
        }
        return null;
    }

    private static String getDimensionStatement(Dimension dimension) {
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

}
