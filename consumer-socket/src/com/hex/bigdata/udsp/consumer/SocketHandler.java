package com.hex.bigdata.udsp.consumer;

import com.hex.bigdata.udsp.common.api.model.Page;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.ServiceType;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.consumer.model.ConsumeRequest;
import com.hex.bigdata.udsp.consumer.model.Request;
import com.hex.bigdata.udsp.consumer.model.Response;
import com.hex.bigdata.udsp.consumer.service.ExternalConsumerService;
import com.hex.bigdata.udsp.consumer.service.LoggingService;
import com.hex.bigdata.udsp.consumer.util.RequestUtil;
import com.hex.bigdata.udsp.dsl.AppDslAdaptor;
import com.hex.bigdata.udsp.dsl.DslSqlAdaptor;
import com.hex.bigdata.udsp.dsl.parser.APPDSLParser;
import com.hex.bigdata.udsp.dsl.parser.DSLSQLParser;
import com.hex.goframe.util.WebApplicationContextUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Netty Socket Server Handler
 */
public class SocketHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static Logger logger = Logger.getLogger (SocketHandler.class);

    private ExternalConsumerService consumerService;
    private LoggingService loggingService;

    public SocketHandler() {
        this.consumerService = (ExternalConsumerService) WebApplicationContextUtil.getBean ("externalConsumerService");
        this.loggingService = (LoggingService) WebApplicationContextUtil.getBean ("loggingService");
    }

    /**
     * 收到消息时，发送信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println ("服务端接受的消息 : " + msg);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel ().remoteAddress ();
        String clientIp = insocket.getAddress ().getHostAddress ();

        // 接收数据
        byte[] reqByte = new byte[msg.readableBytes ()];
        msg.readBytes (reqByte);
        String reqJson = new String (reqByte, CharsetUtil.UTF_8);
        logger.info ("接收报文内容:\n" + reqJson);

        // 消费
        String rsponse = consume (reqJson, clientIp);

        // 响应数据
        logger.info ("响应报文内容:\n" + rsponse);
        byte[] rspByte = rsponse.getBytes (CharsetUtil.UTF_8);
        if (rspByte.length > 0) {
            ByteBuf buf = ctx.alloc ().buffer (rspByte.length);
            buf.writeBytes (rspByte);
            ctx.writeAndFlush (buf).addListener (generateDataGenerator);
        }
    }

    /**
     * 执行消费
     *
     * @param json
     * @param ip
     * @return
     */
    private String consume(String json, String ip) {
        Response response = new Response ();
        long bef = System.currentTimeMillis ();
        try {
            Request request = RequestUtil.jsonToRequest (json);
            request.setRequestIp (ip);
            String sql = request.getSql ();
            if (StringUtils.isBlank (request.getServiceName ())
                    && StringUtils.isNotBlank (sql)) { // 针对IQ模块的应用提供自定义SQL语法服务
                logger.info ("DSL: " + sql);
                DSLSQLParser parser = DslSqlAdaptor.getDSLSQLParser (sql);
                DSLSQLParser.StatementContext context = parser.statement ();
                if (context.getChildCount () == 1) {
                    ParseTree parse = context.getChild (0);
                    if (parse instanceof DSLSQLParser.SelectStatementContext) { // select ...
                        logger.debug ("select ...");
                        String serviceName = ((DSLSQLParser.SelectStatementContext) parse).serviceName ().getText ();
                        String serviceType = consumerService.getServiceType (serviceName);
                        if (ServiceType.IQ_DSL.getValue ().equals (serviceType)) {
                            response = sdlSqlSelect (request); // 交互查询的自定义select SQL
                        } else if (ServiceType.IQ.getValue ().equals (serviceType)) {
                            response = appSdlSelect (request); // 交互查询应用的自定义select SQL
                        } else {
                            throw new Exception ("该服务：" + serviceName + "不是交互查询类型");
                        }
                    } else if (parse instanceof DSLSQLParser.ShowServicesStatementContext) { // show services
                        logger.debug ("show services");
                        response = consumerService.showServices ();
                    } else if (parse instanceof DSLSQLParser.DescribeServiceStatementContext) { // describe <service_name>
                        String serviceName = ((DSLSQLParser.DescribeServiceStatementContext) parse).serviceName ().getText ();
                        logger.debug ("describe " + serviceName);
                        response = consumerService.describeService (serviceName);
                    } else {
                        throw new Exception ("交互查询不支持该SQL语句：" + sql);
                    }
                }
            } else { // 老的请求方式
                response = consumerService.consume (request);
            }
        } catch (Exception e) {
            e.printStackTrace ();
            loggingService.writeResponseLog (response, new ConsumeRequest (), bef, 0,
                    ErrorCode.ERROR_000005.getValue (), ErrorCode.ERROR_000005.getName () + ":" + e.getMessage ());
        }
        return JSONUtil.parseObj2JSON (response);
    }

    /**
     * 交互查询的自定义select SQL
     *
     * @param request
     * @return
     */
    private Response sdlSqlSelect(Request request) {
        Response response = new Response ();
        String sql = request.getSql ();
        ParseTree parse = null;
        DSLSQLParser parser = DslSqlAdaptor.getDSLSQLParser (sql);
        DSLSQLParser.SelectStatementContext selectStatementContext = parser.selectStatement ();
        // serviceName
        DSLSQLParser.ServiceNameContext serviceNameContext = selectStatementContext.serviceName ();
        String serviceName = serviceNameContext.getText ();
        logger.debug ("serviceName:" + serviceName);
        request.setServiceName (serviceName); // 设置serviceName
        // where
        DSLSQLParser.WhereClauseContext whereClauseContext = selectStatementContext.whereClause ();
        if (whereClauseContext != null) {
            logger.debug ("whereClause:" + whereClauseContext.toStringTree (parser));
            // TODO ...
        }
        // limit
        DSLSQLParser.LimitClauseContext limitClauseContext = selectStatementContext.limitClause ();
        if (limitClauseContext != null) {
            logger.debug ("limitClause:" + limitClauseContext.toStringTree (parser));
            // TODO ...
        }
        // group by
        DSLSQLParser.GroupByCaluseContext groupByCaluseContext = selectStatementContext.groupByCaluse ();
        if (groupByCaluseContext != null) {
            logger.debug ("groupByCaluse:" + groupByCaluseContext.toStringTree (parser));
            // TODO ...
        }
        // order by
        DSLSQLParser.OrderByClauseContext orderByClauseContext = selectStatementContext.orderByClause ();
        if (orderByClauseContext != null) {
            logger.debug ("orderByClause:" + orderByClauseContext.toStringTree (parser));
            // TODO ...
        }
        // select
        DSLSQLParser.SelectElementsContext selectElementsContext = selectStatementContext.selectElements ();
        if (selectElementsContext != null) {
            logger.debug ("selectElements:" + selectElementsContext.toStringTree (parser));
            // TODO ...
        }


        return response;
    }

    /**
     * 交互查询应用的自定义select SQL
     *
     * @param request
     * @return
     */
    private Response appSdlSelect(Request request) {
        Response response = new Response ();
        String sql = request.getSql ();
        ParseTree parse = null;
        APPDSLParser parser = AppDslAdaptor.getAPPDSLParser (sql);
        APPDSLParser.SelectStatementContext selectStatementContext = parser.selectStatement ();
        // serviceName
        APPDSLParser.ServiceNameContext serviceNameContext = selectStatementContext.serviceName ();
        String serviceName = serviceNameContext.getText ();
        logger.debug ("serviceName:" + serviceName);
        request.setServiceName (serviceName); // 设置serviceName
        // where
        APPDSLParser.WhereClauseContext whereClauseContext = selectStatementContext.whereClause ();
        if (whereClauseContext != null) {
            logger.debug ("whereClause:" + whereClauseContext.toStringTree (parser));
            Map<String, String> data = new HashMap<> ();
            APPDSLParser.LogicExpressionsContext logicExpressionsContext = whereClauseContext.logicExpressions ();
            List<APPDSLParser.LogicExpressionContext> logicExpressionContexts = logicExpressionsContext.logicExpression ();
            for (APPDSLParser.LogicExpressionContext logicExpressionContext : logicExpressionContexts) {
                APPDSLParser.FullColumnNameContext fullColumnNameContext = logicExpressionContext.fullColumnName ();
                APPDSLParser.ValueContext valueContext = logicExpressionContext.value ();
                String name = fullColumnNameContext.getText ();
                APPDSLParser.TextLiteralContext textLiteralContext = valueContext.textLiteral ();
                APPDSLParser.DecimalLiteralContext decimalLiteralContext = valueContext.decimalLiteral ();
                String value = null;
                if (textLiteralContext != null) {
                    value = textLiteralContext.getText ();
                    value = value.substring (1, value.length () - 1); // 去除首尾的单引号
                } else if (decimalLiteralContext != null) {
                    value = decimalLiteralContext.getText ();
                }
                data.put (name, value);
            }
            request.setData (data); // 设置请求参数
        }
        // limit
        APPDSLParser.LimitClauseContext limitClauseContext = selectStatementContext.limitClause ();
        if (limitClauseContext != null) {
            logger.debug ("limitClause:" + limitClauseContext.toStringTree (parser));
            Page page = new Page ();
            int offset = 0;
            int limit = 1;
            APPDSLParser.DecimalLiteralContext limitContext = limitClauseContext.limit;
            APPDSLParser.DecimalLiteralContext offsetContext = limitClauseContext.offset;
            if (limitContext != null) {
                limit = Integer.valueOf (limitContext.getText ());
            }
            if (offsetContext != null) {
                offset = Integer.valueOf (offsetContext.getText ());
            }
            int pageIndex = offset / limit + 1;
            int pageSize = limit;
            page.setPageIndex (pageIndex);
            page.setPageSize (pageSize);
            request.setPage (page); // 设置分页信息
        }
        // select
        APPDSLParser.SelectElementsContext selectElementsContext = selectStatementContext.selectElements ();
        if (selectElementsContext != null) {
            logger.debug ("selectElements:" + selectElementsContext.toStringTree (parser));
            if (selectElementsContext.getChildCount () == 1) {
                parse = selectElementsContext.getChild (0);
                if (parse instanceof APPDSLParser.SelectElementContext) {
                    String alias = parse.getText ();
                    if (parse.getChildCount () == 3) {
                        alias = parse.getChild (2).getText (); // AS别名
                    }
                    APPDSLParser.FunctionCallContext functionCallContext = ((APPDSLParser.SelectElementContext) parse).functionCall ();
                    APPDSLParser.AggregateWindowedFunctionContext aggregateWindowedFunctionContext = functionCallContext.aggregateWindowedFunction ();
                    if (aggregateWindowedFunctionContext.getChildCount () == 4
                            && "COUNT".equalsIgnoreCase (aggregateWindowedFunctionContext.getChild (0).getText ())) {
                        // 获取总行数
                        response = consumerService.consume (request);
                        long totalCount = response.getRecords ().size ();
                        Page page = response.getPage ();
                        if (page != null) {
                            totalCount = page.getTotalCount ();
                        }
                        List<Map<String, String>> records = new ArrayList<> ();
                        Map<String, String> record = new HashMap<> ();
                        record.put (alias, String.valueOf (totalCount));
                        records.add (record);
                        response.setRecords (records);
                        response.setReturnColumns (null);
                    }
                } else {
                    if ("*".equals (parse.getText ())) {
                        // 获取数据集
                        response = consumerService.consume (request);
                    }
                }
            }
        }
        return response;
    }

    /**
     * 发生异常关闭链路
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace ();
        ctx.close ();
    }

    /**
     * 建立连接时，发送消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info ("连接的客户端地址:" + ctx.channel ().remoteAddress ());
    }

    private final ChannelFutureListener generateDataGenerator = new ChannelFutureListener () {
        @Override
        public void operationComplete(ChannelFuture future) {
            if (!future.isSuccess ()) {
                future.cause ().printStackTrace ();
                future.channel ().close ();
            }
        }
    };
}
