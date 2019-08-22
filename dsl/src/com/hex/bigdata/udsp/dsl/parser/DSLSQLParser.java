// Generated from B:/workspaces/IntellijIdeaGithubWorkspaces/github/boracay/dsl/resource\DSLSQL.g4 by ANTLR 4.7.2

    package com.hex.bigdata.udsp.dsl.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DSLSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, AS=12, SELECT=13, FROM=14, MAX=15, SUM=16, AVG=17, 
		MIN=18, COUNT=19, DISTINCT=20, WHERE=21, GROUP=22, BY=23, ORDER=24, NOT=25, 
		IS=26, BETWEEN=27, AND=28, IN=29, NULL=30, OR=31, ASC=32, DESC=33, LIMIT=34, 
		OFFSET=35, LIKE=36, DESCRIBE=37, SHOW=38, SERVICES=39, ID=40, TEXT_STRING=41, 
		DECIMAL_LITERAL=42, WS=43;
	public static final int
		RULE_statement = 0, RULE_describeServiceStatement = 1, RULE_showServicesStatement = 2, 
		RULE_selectStatement = 3, RULE_selectElements = 4, RULE_whereClause = 5, 
		RULE_logicExpressions = 6, RULE_logicExpression = 7, RULE_groupByCaluse = 8, 
		RULE_orderByClause = 9, RULE_limitClause = 10, RULE_orderByExpression = 11, 
		RULE_groupByItem = 12, RULE_logicalOperator = 13, RULE_comparisonOperator = 14, 
		RULE_value = 15, RULE_decimalLiteral = 16, RULE_textLiteral = 17, RULE_selectElement = 18, 
		RULE_selectElementCal = 19, RULE_fullColumnName = 20, RULE_arithmeticCall = 21, 
		RULE_stringAndNumber = 22, RULE_arithmetic = 23, RULE_functionCall = 24, 
		RULE_aggregateFunction = 25, RULE_functionArg = 26, RULE_functionArgs = 27, 
		RULE_otherFunction = 28, RULE_otherFunctionArgs = 29, RULE_otherFunctionName = 30, 
		RULE_serviceName = 31, RULE_columnName = 32, RULE_uid = 33;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "describeServiceStatement", "showServicesStatement", "selectStatement", 
			"selectElements", "whereClause", "logicExpressions", "logicExpression", 
			"groupByCaluse", "orderByClause", "limitClause", "orderByExpression", 
			"groupByItem", "logicalOperator", "comparisonOperator", "value", "decimalLiteral", 
			"textLiteral", "selectElement", "selectElementCal", "fullColumnName", 
			"arithmeticCall", "stringAndNumber", "arithmetic", "functionCall", "aggregateFunction", 
			"functionArg", "functionArgs", "otherFunction", "otherFunctionArgs", 
			"otherFunctionName", "serviceName", "columnName", "uid"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", "','", "'('", "')'", "'='", "'>'", "'<'", "'!'", "'+'", 
			"'-'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"AS", "SELECT", "FROM", "MAX", "SUM", "AVG", "MIN", "COUNT", "DISTINCT", 
			"WHERE", "GROUP", "BY", "ORDER", "NOT", "IS", "BETWEEN", "AND", "IN", 
			"NULL", "OR", "ASC", "DESC", "LIMIT", "OFFSET", "LIKE", "DESCRIBE", "SHOW", 
			"SERVICES", "ID", "TEXT_STRING", "DECIMAL_LITERAL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DSLSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DSLSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StatementContext extends ParserRuleContext {
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public DescribeServiceStatementContext describeServiceStatement() {
			return getRuleContext(DescribeServiceStatementContext.class,0);
		}
		public ShowServicesStatementContext showServicesStatement() {
			return getRuleContext(ShowServicesStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		try {
			setState(71);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				selectStatement();
				}
				break;
			case DESCRIBE:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				describeServiceStatement();
				}
				break;
			case SHOW:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				showServicesStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescribeServiceStatementContext extends ParserRuleContext {
		public TerminalNode DESCRIBE() { return getToken(DSLSQLParser.DESCRIBE, 0); }
		public ServiceNameContext serviceName() {
			return getRuleContext(ServiceNameContext.class,0);
		}
		public DescribeServiceStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_describeServiceStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterDescribeServiceStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitDescribeServiceStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitDescribeServiceStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescribeServiceStatementContext describeServiceStatement() throws RecognitionException {
		DescribeServiceStatementContext _localctx = new DescribeServiceStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_describeServiceStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(DESCRIBE);
			setState(74);
			serviceName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShowServicesStatementContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(DSLSQLParser.SHOW, 0); }
		public TerminalNode SERVICES() { return getToken(DSLSQLParser.SERVICES, 0); }
		public TerminalNode LIKE() { return getToken(DSLSQLParser.LIKE, 0); }
		public TextLiteralContext textLiteral() {
			return getRuleContext(TextLiteralContext.class,0);
		}
		public ShowServicesStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showServicesStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterShowServicesStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitShowServicesStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitShowServicesStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowServicesStatementContext showServicesStatement() throws RecognitionException {
		ShowServicesStatementContext _localctx = new ShowServicesStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_showServicesStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(SHOW);
			setState(77);
			match(SERVICES);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIKE) {
				{
				setState(78);
				match(LIKE);
				setState(79);
				textLiteral();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(DSLSQLParser.SELECT, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode FROM() { return getToken(DSLSQLParser.FROM, 0); }
		public ServiceNameContext serviceName() {
			return getRuleContext(ServiceNameContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public GroupByCaluseContext groupByCaluse() {
			return getRuleContext(GroupByCaluseContext.class,0);
		}
		public OrderByClauseContext orderByClause() {
			return getRuleContext(OrderByClauseContext.class,0);
		}
		public LimitClauseContext limitClause() {
			return getRuleContext(LimitClauseContext.class,0);
		}
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSelectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(SELECT);
			setState(83);
			selectElements();
			setState(84);
			match(FROM);
			setState(85);
			serviceName();
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(86);
				whereClause();
				}
			}

			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(89);
				groupByCaluse();
				}
			}

			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(92);
				orderByClause();
				}
			}

			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(95);
				limitClause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectElementsContext extends ParserRuleContext {
		public Token star;
		public List<SelectElementContext> selectElement() {
			return getRuleContexts(SelectElementContext.class);
		}
		public SelectElementContext selectElement(int i) {
			return getRuleContext(SelectElementContext.class,i);
		}
		public SelectElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSelectElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSelectElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitSelectElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementsContext selectElements() throws RecognitionException {
		SelectElementsContext _localctx = new SelectElementsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_selectElements);
		int _la;
		try {
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				((SelectElementsContext)_localctx).star = match(T__0);
				}
				break;
			case MAX:
			case SUM:
			case AVG:
			case MIN:
			case COUNT:
			case ID:
			case TEXT_STRING:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				selectElement();
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(100);
					match(T__1);
					setState(101);
					selectElement();
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereClauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(DSLSQLParser.WHERE, 0); }
		public LogicExpressionsContext logicExpressions() {
			return getRuleContext(LogicExpressionsContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(WHERE);
			setState(110);
			logicExpressions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicExpressionsContext extends ParserRuleContext {
		public List<LogicExpressionContext> logicExpression() {
			return getRuleContexts(LogicExpressionContext.class);
		}
		public LogicExpressionContext logicExpression(int i) {
			return getRuleContext(LogicExpressionContext.class,i);
		}
		public List<LogicalOperatorContext> logicalOperator() {
			return getRuleContexts(LogicalOperatorContext.class);
		}
		public LogicalOperatorContext logicalOperator(int i) {
			return getRuleContext(LogicalOperatorContext.class,i);
		}
		public LogicExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterLogicExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitLogicExpressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitLogicExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicExpressionsContext logicExpressions() throws RecognitionException {
		LogicExpressionsContext _localctx = new LogicExpressionsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_logicExpressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			logicExpression();
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(113);
				logicalOperator();
				setState(114);
				logicExpression();
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicExpressionContext extends ParserRuleContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode BETWEEN() { return getToken(DSLSQLParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(DSLSQLParser.AND, 0); }
		public TerminalNode IN() { return getToken(DSLSQLParser.IN, 0); }
		public TerminalNode NOT() { return getToken(DSLSQLParser.NOT, 0); }
		public TerminalNode IS() { return getToken(DSLSQLParser.IS, 0); }
		public TerminalNode NULL() { return getToken(DSLSQLParser.NULL, 0); }
		public LogicExpressionsContext logicExpressions() {
			return getRuleContext(LogicExpressionsContext.class,0);
		}
		public LogicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterLogicExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitLogicExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitLogicExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicExpressionContext logicExpression() throws RecognitionException {
		LogicExpressionContext _localctx = new LogicExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_logicExpression);
		int _la;
		try {
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				fullColumnName();
				setState(122);
				comparisonOperator();
				setState(123);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				fullColumnName();
				setState(126);
				match(BETWEEN);
				setState(127);
				value();
				setState(128);
				match(AND);
				setState(129);
				value();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				fullColumnName();
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(132);
					match(NOT);
					}
				}

				setState(135);
				match(IN);
				setState(136);
				match(T__2);
				setState(137);
				value();
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(138);
					match(T__1);
					setState(139);
					value();
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				match(T__3);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				fullColumnName();
				setState(148);
				match(IS);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(149);
					match(NOT);
					}
				}

				setState(152);
				match(NULL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(154);
				match(T__2);
				setState(155);
				logicExpressions();
				setState(156);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupByCaluseContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(DSLSQLParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(DSLSQLParser.BY, 0); }
		public List<GroupByItemContext> groupByItem() {
			return getRuleContexts(GroupByItemContext.class);
		}
		public GroupByItemContext groupByItem(int i) {
			return getRuleContext(GroupByItemContext.class,i);
		}
		public GroupByCaluseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByCaluse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterGroupByCaluse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitGroupByCaluse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitGroupByCaluse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByCaluseContext groupByCaluse() throws RecognitionException {
		GroupByCaluseContext _localctx = new GroupByCaluseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_groupByCaluse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(GROUP);
			setState(161);
			match(BY);
			setState(162);
			groupByItem();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(163);
				match(T__1);
				setState(164);
				groupByItem();
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderByClauseContext extends ParserRuleContext {
		public TerminalNode ORDER() { return getToken(DSLSQLParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(DSLSQLParser.BY, 0); }
		public List<OrderByExpressionContext> orderByExpression() {
			return getRuleContexts(OrderByExpressionContext.class);
		}
		public OrderByExpressionContext orderByExpression(int i) {
			return getRuleContext(OrderByExpressionContext.class,i);
		}
		public OrderByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOrderByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOrderByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitOrderByClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderByClauseContext orderByClause() throws RecognitionException {
		OrderByClauseContext _localctx = new OrderByClauseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(ORDER);
			setState(171);
			match(BY);
			setState(172);
			orderByExpression();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(173);
				match(T__1);
				setState(174);
				orderByExpression();
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitClauseContext extends ParserRuleContext {
		public DecimalLiteralContext offset;
		public DecimalLiteralContext limit;
		public TerminalNode LIMIT() { return getToken(DSLSQLParser.LIMIT, 0); }
		public TerminalNode OFFSET() { return getToken(DSLSQLParser.OFFSET, 0); }
		public List<DecimalLiteralContext> decimalLiteral() {
			return getRuleContexts(DecimalLiteralContext.class);
		}
		public DecimalLiteralContext decimalLiteral(int i) {
			return getRuleContext(DecimalLiteralContext.class,i);
		}
		public LimitClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterLimitClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitLimitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitLimitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitClauseContext limitClause() throws RecognitionException {
		LimitClauseContext _localctx = new LimitClauseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(LIMIT);
			setState(191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(184);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(181);
					((LimitClauseContext)_localctx).offset = decimalLiteral();
					setState(182);
					match(T__1);
					}
					break;
				}
				setState(186);
				((LimitClauseContext)_localctx).limit = decimalLiteral();
				}
				break;
			case 2:
				{
				setState(187);
				((LimitClauseContext)_localctx).limit = decimalLiteral();
				setState(188);
				match(OFFSET);
				setState(189);
				((LimitClauseContext)_localctx).offset = decimalLiteral();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderByExpressionContext extends ParserRuleContext {
		public Token order;
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public TerminalNode ASC() { return getToken(DSLSQLParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(DSLSQLParser.DESC, 0); }
		public OrderByExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOrderByExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOrderByExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitOrderByExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderByExpressionContext orderByExpression() throws RecognitionException {
		OrderByExpressionContext _localctx = new OrderByExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_orderByExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			fullColumnName();
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASC || _la==DESC) {
				{
				setState(194);
				((OrderByExpressionContext)_localctx).order = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ASC || _la==DESC) ) {
					((OrderByExpressionContext)_localctx).order = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupByItemContext extends ParserRuleContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public GroupByItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterGroupByItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitGroupByItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitGroupByItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByItemContext groupByItem() throws RecognitionException {
		GroupByItemContext _localctx = new GroupByItemContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_groupByItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			fullColumnName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalOperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(DSLSQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(DSLSQLParser.OR, 0); }
		public LogicalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterLogicalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitLogicalOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitLogicalOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOperatorContext logicalOperator() throws RecognitionException {
		LogicalOperatorContext _localctx = new LogicalOperatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_logicalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode LIKE() { return getToken(DSLSQLParser.LIKE, 0); }
		public TerminalNode NOT() { return getToken(DSLSQLParser.NOT, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comparisonOperator);
		int _la;
		try {
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				match(T__5);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				match(T__6);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				match(T__6);
				setState(205);
				match(T__4);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(206);
				match(T__5);
				setState(207);
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(208);
				match(T__7);
				setState(209);
				match(T__4);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(210);
				match(T__6);
				setState(211);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(212);
					match(NOT);
					}
				}

				setState(215);
				match(LIKE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TextLiteralContext textLiteral() {
			return getRuleContext(TextLiteralContext.class,0);
		}
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_value);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT_STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				textLiteral();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				decimalLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecimalLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(DSLSQLParser.DECIMAL_LITERAL, 0); }
		public DecimalLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimalLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterDecimalLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitDecimalLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitDecimalLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalLiteralContext decimalLiteral() throws RecognitionException {
		DecimalLiteralContext _localctx = new DecimalLiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_decimalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(DECIMAL_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextLiteralContext extends ParserRuleContext {
		public TerminalNode TEXT_STRING() { return getToken(DSLSQLParser.TEXT_STRING, 0); }
		public TextLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterTextLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitTextLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitTextLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextLiteralContext textLiteral() throws RecognitionException {
		TextLiteralContext _localctx = new TextLiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_textLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(TEXT_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectElementContext extends ParserRuleContext {
		public SelectElementCalContext selectElementCal() {
			return getRuleContext(SelectElementCalContext.class,0);
		}
		public UidContext uid() {
			return getRuleContext(UidContext.class,0);
		}
		public TerminalNode AS() { return getToken(DSLSQLParser.AS, 0); }
		public SelectElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSelectElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSelectElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitSelectElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementContext selectElement() throws RecognitionException {
		SelectElementContext _localctx = new SelectElementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_selectElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			selectElementCal();
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(227);
					match(AS);
					}
				}

				setState(230);
				uid();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectElementCalContext extends ParserRuleContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public ArithmeticCallContext arithmeticCall() {
			return getRuleContext(ArithmeticCallContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public SelectElementCalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElementCal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSelectElementCal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSelectElementCal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitSelectElementCal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementCalContext selectElementCal() throws RecognitionException {
		SelectElementCalContext _localctx = new SelectElementCalContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_selectElementCal);
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				fullColumnName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				arithmeticCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(235);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(236);
				value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FullColumnNameContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public FullColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullColumnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterFullColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitFullColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitFullColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullColumnNameContext fullColumnName() throws RecognitionException {
		FullColumnNameContext _localctx = new FullColumnNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fullColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			columnName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticCallContext extends ParserRuleContext {
		public List<StringAndNumberContext> stringAndNumber() {
			return getRuleContexts(StringAndNumberContext.class);
		}
		public StringAndNumberContext stringAndNumber(int i) {
			return getRuleContext(StringAndNumberContext.class,i);
		}
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public ArithmeticCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterArithmeticCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitArithmeticCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitArithmeticCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticCallContext arithmeticCall() throws RecognitionException {
		ArithmeticCallContext _localctx = new ArithmeticCallContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arithmeticCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			stringAndNumber();
			setState(245); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(242);
				arithmetic();
				setState(243);
				stringAndNumber();
				}
				}
				setState(247); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringAndNumberContext extends ParserRuleContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public StringAndNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringAndNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterStringAndNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitStringAndNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitStringAndNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringAndNumberContext stringAndNumber() throws RecognitionException {
		StringAndNumberContext _localctx = new StringAndNumberContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_stringAndNumber);
		try {
			setState(251);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				fullColumnName();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(250);
				decimalLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArithmeticContext extends ParserRuleContext {
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public AggregateFunctionContext aggregateFunction() {
			return getRuleContext(AggregateFunctionContext.class,0);
		}
		public OtherFunctionContext otherFunction() {
			return getRuleContext(OtherFunctionContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_functionCall);
		try {
			setState(257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MAX:
			case SUM:
			case AVG:
			case MIN:
			case COUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				aggregateFunction();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				otherFunction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregateFunctionContext extends ParserRuleContext {
		public Token starArg;
		public Token aggregator;
		public StringAndNumberContext stringAndNumber() {
			return getRuleContext(StringAndNumberContext.class,0);
		}
		public TerminalNode AVG() { return getToken(DSLSQLParser.AVG, 0); }
		public TerminalNode MAX() { return getToken(DSLSQLParser.MAX, 0); }
		public TerminalNode MIN() { return getToken(DSLSQLParser.MIN, 0); }
		public TerminalNode SUM() { return getToken(DSLSQLParser.SUM, 0); }
		public TerminalNode COUNT() { return getToken(DSLSQLParser.COUNT, 0); }
		public FunctionArgContext functionArg() {
			return getRuleContext(FunctionArgContext.class,0);
		}
		public FunctionArgsContext functionArgs() {
			return getRuleContext(FunctionArgsContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(DSLSQLParser.DISTINCT, 0); }
		public AggregateFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregateFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterAggregateFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitAggregateFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitAggregateFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregateFunctionContext aggregateFunction() throws RecognitionException {
		AggregateFunctionContext _localctx = new AggregateFunctionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_aggregateFunction);
		int _la;
		try {
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAX) | (1L << SUM) | (1L << AVG) | (1L << MIN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(260);
				match(T__2);
				setState(261);
				stringAndNumber();
				setState(262);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				match(COUNT);
				setState(265);
				match(T__2);
				setState(270);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
					{
					setState(266);
					((AggregateFunctionContext)_localctx).starArg = match(T__0);
					}
					break;
				case T__3:
				case ID:
				case DECIMAL_LITERAL:
					{
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID || _la==DECIMAL_LITERAL) {
						{
						setState(267);
						functionArg();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(272);
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(273);
				match(COUNT);
				setState(274);
				match(T__2);
				setState(275);
				((AggregateFunctionContext)_localctx).aggregator = match(DISTINCT);
				setState(276);
				functionArgs();
				setState(277);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgContext extends ParserRuleContext {
		public StringAndNumberContext stringAndNumber() {
			return getRuleContext(StringAndNumberContext.class,0);
		}
		public FunctionArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterFunctionArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitFunctionArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitFunctionArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionArgContext functionArg() throws RecognitionException {
		FunctionArgContext _localctx = new FunctionArgContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_functionArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			stringAndNumber();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgsContext extends ParserRuleContext {
		public List<StringAndNumberContext> stringAndNumber() {
			return getRuleContexts(StringAndNumberContext.class);
		}
		public StringAndNumberContext stringAndNumber(int i) {
			return getRuleContext(StringAndNumberContext.class,i);
		}
		public FunctionArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterFunctionArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitFunctionArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitFunctionArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionArgsContext functionArgs() throws RecognitionException {
		FunctionArgsContext _localctx = new FunctionArgsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_functionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			stringAndNumber();
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(284);
				match(T__1);
				setState(285);
				stringAndNumber();
				}
				}
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OtherFunctionContext extends ParserRuleContext {
		public OtherFunctionNameContext otherFunctionName() {
			return getRuleContext(OtherFunctionNameContext.class,0);
		}
		public OtherFunctionArgsContext otherFunctionArgs() {
			return getRuleContext(OtherFunctionArgsContext.class,0);
		}
		public OtherFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOtherFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOtherFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitOtherFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherFunctionContext otherFunction() throws RecognitionException {
		OtherFunctionContext _localctx = new OtherFunctionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_otherFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			otherFunctionName();
			setState(292);
			match(T__2);
			setState(293);
			otherFunctionArgs();
			setState(294);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OtherFunctionArgsContext extends ParserRuleContext {
		public List<SelectElementCalContext> selectElementCal() {
			return getRuleContexts(SelectElementCalContext.class);
		}
		public SelectElementCalContext selectElementCal(int i) {
			return getRuleContext(SelectElementCalContext.class,i);
		}
		public OtherFunctionArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherFunctionArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOtherFunctionArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOtherFunctionArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitOtherFunctionArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherFunctionArgsContext otherFunctionArgs() throws RecognitionException {
		OtherFunctionArgsContext _localctx = new OtherFunctionArgsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_otherFunctionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			selectElementCal();
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(297);
				match(T__1);
				setState(298);
				selectElementCal();
				}
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OtherFunctionNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DSLSQLParser.ID, 0); }
		public OtherFunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherFunctionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOtherFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOtherFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitOtherFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherFunctionNameContext otherFunctionName() throws RecognitionException {
		OtherFunctionNameContext _localctx = new OtherFunctionNameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_otherFunctionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceNameContext extends ParserRuleContext {
		public Token tmpName;
		public TerminalNode ID() { return getToken(DSLSQLParser.ID, 0); }
		public ServiceNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterServiceName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitServiceName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitServiceName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceNameContext serviceName() throws RecognitionException {
		ServiceNameContext _localctx = new ServiceNameContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_serviceName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			((ServiceNameContext)_localctx).tmpName = match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DSLSQLParser.ID, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UidContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(DSLSQLParser.ID, 0); }
		public UidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterUid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitUid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitUid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UidContext uid() throws RecognitionException {
		UidContext _localctx = new UidContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_uid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u013b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\5\2J\n\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4"+
		"S\n\4\3\5\3\5\3\5\3\5\3\5\5\5Z\n\5\3\5\5\5]\n\5\3\5\5\5`\n\5\3\5\5\5c"+
		"\n\5\3\6\3\6\3\6\3\6\7\6i\n\6\f\6\16\6l\13\6\5\6n\n\6\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\7\bw\n\b\f\b\16\bz\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\t\u0088\n\t\3\t\3\t\3\t\3\t\3\t\7\t\u008f\n\t\f\t\16"+
		"\t\u0092\13\t\3\t\3\t\3\t\3\t\3\t\5\t\u0099\n\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\5\t\u00a1\n\t\3\n\3\n\3\n\3\n\3\n\7\n\u00a8\n\n\f\n\16\n\u00ab\13\n"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u00b2\n\13\f\13\16\13\u00b5\13\13\3\f\3"+
		"\f\3\f\3\f\5\f\u00bb\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c2\n\f\3\r\3\r\5\r"+
		"\u00c6\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00d8\n\20\3\20\5\20\u00db\n\20\3\21\3\21\5"+
		"\21\u00df\n\21\3\22\3\22\3\23\3\23\3\24\3\24\5\24\u00e7\n\24\3\24\5\24"+
		"\u00ea\n\24\3\25\3\25\3\25\3\25\5\25\u00f0\n\25\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\6\27\u00f8\n\27\r\27\16\27\u00f9\3\30\3\30\5\30\u00fe\n\30\3"+
		"\31\3\31\3\32\3\32\5\32\u0104\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\5\33\u010f\n\33\5\33\u0111\n\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\5\33\u011a\n\33\3\34\3\34\3\35\3\35\3\35\7\35\u0121\n\35\f\35"+
		"\16\35\u0124\13\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\7\37\u012e"+
		"\n\37\f\37\16\37\u0131\13\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\2\2$\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BD\2\6\3\2\""+
		"#\4\2\36\36!!\4\2\3\3\13\r\3\2\21\24\2\u0145\2I\3\2\2\2\4K\3\2\2\2\6N"+
		"\3\2\2\2\bT\3\2\2\2\nm\3\2\2\2\fo\3\2\2\2\16r\3\2\2\2\20\u00a0\3\2\2\2"+
		"\22\u00a2\3\2\2\2\24\u00ac\3\2\2\2\26\u00b6\3\2\2\2\30\u00c3\3\2\2\2\32"+
		"\u00c7\3\2\2\2\34\u00c9\3\2\2\2\36\u00da\3\2\2\2 \u00de\3\2\2\2\"\u00e0"+
		"\3\2\2\2$\u00e2\3\2\2\2&\u00e4\3\2\2\2(\u00ef\3\2\2\2*\u00f1\3\2\2\2,"+
		"\u00f3\3\2\2\2.\u00fd\3\2\2\2\60\u00ff\3\2\2\2\62\u0103\3\2\2\2\64\u0119"+
		"\3\2\2\2\66\u011b\3\2\2\28\u011d\3\2\2\2:\u0125\3\2\2\2<\u012a\3\2\2\2"+
		">\u0132\3\2\2\2@\u0134\3\2\2\2B\u0136\3\2\2\2D\u0138\3\2\2\2FJ\5\b\5\2"+
		"GJ\5\4\3\2HJ\5\6\4\2IF\3\2\2\2IG\3\2\2\2IH\3\2\2\2J\3\3\2\2\2KL\7\'\2"+
		"\2LM\5@!\2M\5\3\2\2\2NO\7(\2\2OR\7)\2\2PQ\7&\2\2QS\5$\23\2RP\3\2\2\2R"+
		"S\3\2\2\2S\7\3\2\2\2TU\7\17\2\2UV\5\n\6\2VW\7\20\2\2WY\5@!\2XZ\5\f\7\2"+
		"YX\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[]\5\22\n\2\\[\3\2\2\2\\]\3\2\2\2]_\3\2"+
		"\2\2^`\5\24\13\2_^\3\2\2\2_`\3\2\2\2`b\3\2\2\2ac\5\26\f\2ba\3\2\2\2bc"+
		"\3\2\2\2c\t\3\2\2\2dn\7\3\2\2ej\5&\24\2fg\7\4\2\2gi\5&\24\2hf\3\2\2\2"+
		"il\3\2\2\2jh\3\2\2\2jk\3\2\2\2kn\3\2\2\2lj\3\2\2\2md\3\2\2\2me\3\2\2\2"+
		"n\13\3\2\2\2op\7\27\2\2pq\5\16\b\2q\r\3\2\2\2rx\5\20\t\2st\5\34\17\2t"+
		"u\5\20\t\2uw\3\2\2\2vs\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\17\3\2\2"+
		"\2zx\3\2\2\2{|\5*\26\2|}\5\36\20\2}~\5 \21\2~\u00a1\3\2\2\2\177\u0080"+
		"\5*\26\2\u0080\u0081\7\35\2\2\u0081\u0082\5 \21\2\u0082\u0083\7\36\2\2"+
		"\u0083\u0084\5 \21\2\u0084\u00a1\3\2\2\2\u0085\u0087\5*\26\2\u0086\u0088"+
		"\7\33\2\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2"+
		"\u0089\u008a\7\37\2\2\u008a\u008b\7\5\2\2\u008b\u0090\5 \21\2\u008c\u008d"+
		"\7\4\2\2\u008d\u008f\5 \21\2\u008e\u008c\3\2\2\2\u008f\u0092\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0093\u0094\7\6\2\2\u0094\u00a1\3\2\2\2\u0095\u0096\5*\26\2\u0096"+
		"\u0098\7\34\2\2\u0097\u0099\7\33\2\2\u0098\u0097\3\2\2\2\u0098\u0099\3"+
		"\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7 \2\2\u009b\u00a1\3\2\2\2\u009c"+
		"\u009d\7\5\2\2\u009d\u009e\5\16\b\2\u009e\u009f\7\6\2\2\u009f\u00a1\3"+
		"\2\2\2\u00a0{\3\2\2\2\u00a0\177\3\2\2\2\u00a0\u0085\3\2\2\2\u00a0\u0095"+
		"\3\2\2\2\u00a0\u009c\3\2\2\2\u00a1\21\3\2\2\2\u00a2\u00a3\7\30\2\2\u00a3"+
		"\u00a4\7\31\2\2\u00a4\u00a9\5\32\16\2\u00a5\u00a6\7\4\2\2\u00a6\u00a8"+
		"\5\32\16\2\u00a7\u00a5\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2"+
		"\u00a9\u00aa\3\2\2\2\u00aa\23\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad"+
		"\7\32\2\2\u00ad\u00ae\7\31\2\2\u00ae\u00b3\5\30\r\2\u00af\u00b0\7\4\2"+
		"\2\u00b0\u00b2\5\30\r\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3"+
		"\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\25\3\2\2\2\u00b5\u00b3\3\2\2"+
		"\2\u00b6\u00c1\7$\2\2\u00b7\u00b8\5\"\22\2\u00b8\u00b9\7\4\2\2\u00b9\u00bb"+
		"\3\2\2\2\u00ba\u00b7\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc"+
		"\u00c2\5\"\22\2\u00bd\u00be\5\"\22\2\u00be\u00bf\7%\2\2\u00bf\u00c0\5"+
		"\"\22\2\u00c0\u00c2\3\2\2\2\u00c1\u00ba\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c2"+
		"\27\3\2\2\2\u00c3\u00c5\5*\26\2\u00c4\u00c6\t\2\2\2\u00c5\u00c4\3\2\2"+
		"\2\u00c5\u00c6\3\2\2\2\u00c6\31\3\2\2\2\u00c7\u00c8\5*\26\2\u00c8\33\3"+
		"\2\2\2\u00c9\u00ca\t\3\2\2\u00ca\35\3\2\2\2\u00cb\u00db\7\7\2\2\u00cc"+
		"\u00db\7\b\2\2\u00cd\u00db\7\t\2\2\u00ce\u00cf\7\t\2\2\u00cf\u00db\7\7"+
		"\2\2\u00d0\u00d1\7\b\2\2\u00d1\u00db\7\7\2\2\u00d2\u00d3\7\n\2\2\u00d3"+
		"\u00db\7\7\2\2\u00d4\u00d5\7\t\2\2\u00d5\u00db\7\b\2\2\u00d6\u00d8\7\33"+
		"\2\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00db\7&\2\2\u00da\u00cb\3\2\2\2\u00da\u00cc\3\2\2\2\u00da\u00cd\3\2"+
		"\2\2\u00da\u00ce\3\2\2\2\u00da\u00d0\3\2\2\2\u00da\u00d2\3\2\2\2\u00da"+
		"\u00d4\3\2\2\2\u00da\u00d7\3\2\2\2\u00db\37\3\2\2\2\u00dc\u00df\5$\23"+
		"\2\u00dd\u00df\5\"\22\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df"+
		"!\3\2\2\2\u00e0\u00e1\7,\2\2\u00e1#\3\2\2\2\u00e2\u00e3\7+\2\2\u00e3%"+
		"\3\2\2\2\u00e4\u00e9\5(\25\2\u00e5\u00e7\7\16\2\2\u00e6\u00e5\3\2\2\2"+
		"\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00ea\5D#\2\u00e9\u00e6"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\'\3\2\2\2\u00eb\u00f0\5*\26\2\u00ec"+
		"\u00f0\5,\27\2\u00ed\u00f0\5\62\32\2\u00ee\u00f0\5 \21\2\u00ef\u00eb\3"+
		"\2\2\2\u00ef\u00ec\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0"+
		")\3\2\2\2\u00f1\u00f2\5B\"\2\u00f2+\3\2\2\2\u00f3\u00f7\5.\30\2\u00f4"+
		"\u00f5\5\60\31\2\u00f5\u00f6\5.\30\2\u00f6\u00f8\3\2\2\2\u00f7\u00f4\3"+
		"\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"-\3\2\2\2\u00fb\u00fe\5*\26\2\u00fc\u00fe\5\"\22\2\u00fd\u00fb\3\2\2\2"+
		"\u00fd\u00fc\3\2\2\2\u00fe/\3\2\2\2\u00ff\u0100\t\4\2\2\u0100\61\3\2\2"+
		"\2\u0101\u0104\5\64\33\2\u0102\u0104\5:\36\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0102\3\2\2\2\u0104\63\3\2\2\2\u0105\u0106\t\5\2\2\u0106\u0107\7\5\2"+
		"\2\u0107\u0108\5.\30\2\u0108\u0109\7\6\2\2\u0109\u011a\3\2\2\2\u010a\u010b"+
		"\7\25\2\2\u010b\u0110\7\5\2\2\u010c\u0111\7\3\2\2\u010d\u010f\5\66\34"+
		"\2\u010e\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2\2\2\u0110\u010c"+
		"\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u011a\7\6\2\2\u0113"+
		"\u0114\7\25\2\2\u0114\u0115\7\5\2\2\u0115\u0116\7\26\2\2\u0116\u0117\5"+
		"8\35\2\u0117\u0118\7\6\2\2\u0118\u011a\3\2\2\2\u0119\u0105\3\2\2\2\u0119"+
		"\u010a\3\2\2\2\u0119\u0113\3\2\2\2\u011a\65\3\2\2\2\u011b\u011c\5.\30"+
		"\2\u011c\67\3\2\2\2\u011d\u0122\5.\30\2\u011e\u011f\7\4\2\2\u011f\u0121"+
		"\5.\30\2\u0120\u011e\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u01239\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126\5> \2\u0126"+
		"\u0127\7\5\2\2\u0127\u0128\5<\37\2\u0128\u0129\7\6\2\2\u0129;\3\2\2\2"+
		"\u012a\u012f\5(\25\2\u012b\u012c\7\4\2\2\u012c\u012e\5(\25\2\u012d\u012b"+
		"\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"=\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\7*\2\2\u0133?\3\2\2\2\u0134"+
		"\u0135\7*\2\2\u0135A\3\2\2\2\u0136\u0137\7*\2\2\u0137C\3\2\2\2\u0138\u0139"+
		"\7*\2\2\u0139E\3\2\2\2\"IRY\\_bjmx\u0087\u0090\u0098\u00a0\u00a9\u00b3"+
		"\u00ba\u00c1\u00c5\u00d7\u00da\u00de\u00e6\u00e9\u00ef\u00f9\u00fd\u0103"+
		"\u010e\u0110\u0119\u0122\u012f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}