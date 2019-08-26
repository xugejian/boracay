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
		T__9=10, T__10=11, T__11=12, AS=13, SELECT=14, FROM=15, MAX=16, SUM=17, 
		AVG=18, MIN=19, COUNT=20, DISTINCT=21, WHERE=22, GROUP=23, BY=24, ORDER=25, 
		NOT=26, IS=27, BETWEEN=28, AND=29, IN=30, NULL=31, OR=32, ASC=33, DESC=34, 
		LIMIT=35, OFFSET=36, LIKE=37, DESCRIBE=38, SHOW=39, SERVICES=40, ON=41, 
		JOIN=42, LEFT=43, RIGHT=44, INNER=45, ID=46, TEXT_STRING=47, DECIMAL_LITERAL=48, 
		WS=49;
	public static final int
		RULE_statement = 0, RULE_describeServiceStatement = 1, RULE_showServicesStatement = 2, 
		RULE_selectStatement = 3, RULE_joinCaluse = 4, RULE_joinElement = 5, RULE_joinStatement = 6, 
		RULE_joinOperator = 7, RULE_onStatement = 8, RULE_subSelectStatement = 9, 
		RULE_selectElements = 10, RULE_whereClause = 11, RULE_logicExpressions = 12, 
		RULE_logicExpression = 13, RULE_logicExpressionCal = 14, RULE_groupByCaluse = 15, 
		RULE_orderByClause = 16, RULE_limitClause = 17, RULE_orderByExpression = 18, 
		RULE_groupByItem = 19, RULE_logicalOperator = 20, RULE_comparisonOperator = 21, 
		RULE_value = 22, RULE_decimalLiteral = 23, RULE_textLiteral = 24, RULE_selectElement = 25, 
		RULE_selectElementCal = 26, RULE_fullColumnName = 27, RULE_arithmeticCall = 28, 
		RULE_stringAndNumber = 29, RULE_arithmetic = 30, RULE_functionCall = 31, 
		RULE_aggregateFunction = 32, RULE_functionArg = 33, RULE_functionArgs = 34, 
		RULE_otherFunction = 35, RULE_otherFunctionArgs = 36, RULE_otherFunctionName = 37, 
		RULE_serviceName = 38, RULE_columnName = 39, RULE_uid = 40;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "describeServiceStatement", "showServicesStatement", "selectStatement", 
			"joinCaluse", "joinElement", "joinStatement", "joinOperator", "onStatement", 
			"subSelectStatement", "selectElements", "whereClause", "logicExpressions", 
			"logicExpression", "logicExpressionCal", "groupByCaluse", "orderByClause", 
			"limitClause", "orderByExpression", "groupByItem", "logicalOperator", 
			"comparisonOperator", "value", "decimalLiteral", "textLiteral", "selectElement", 
			"selectElementCal", "fullColumnName", "arithmeticCall", "stringAndNumber", 
			"arithmetic", "functionCall", "aggregateFunction", "functionArg", "functionArgs", 
			"otherFunction", "otherFunctionArgs", "otherFunctionName", "serviceName", 
			"columnName", "uid"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'*'", "','", "'='", "'>'", "'<'", "'!'", "'.'", 
			"'+'", "'-'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "AS", "SELECT", "FROM", "MAX", "SUM", "AVG", "MIN", "COUNT", "DISTINCT", 
			"WHERE", "GROUP", "BY", "ORDER", "NOT", "IS", "BETWEEN", "AND", "IN", 
			"NULL", "OR", "ASC", "DESC", "LIMIT", "OFFSET", "LIKE", "DESCRIBE", "SHOW", 
			"SERVICES", "ON", "JOIN", "LEFT", "RIGHT", "INNER", "ID", "TEXT_STRING", 
			"DECIMAL_LITERAL", "WS"
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
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				selectStatement();
				}
				break;
			case DESCRIBE:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				describeServiceStatement();
				}
				break;
			case SHOW:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
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
			setState(87);
			match(DESCRIBE);
			setState(88);
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
			setState(90);
			match(SHOW);
			setState(91);
			match(SERVICES);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIKE) {
				{
				setState(92);
				match(LIKE);
				setState(93);
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
		public SubSelectStatementContext subSelectStatement() {
			return getRuleContext(SubSelectStatementContext.class,0);
		}
		public JoinCaluseContext joinCaluse() {
			return getRuleContext(JoinCaluseContext.class,0);
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
			setState(96);
			match(SELECT);
			setState(97);
			selectElements();
			setState(98);
			match(FROM);
			setState(99);
			subSelectStatement();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT) | (1L << RIGHT) | (1L << INNER))) != 0)) {
				{
				setState(100);
				joinCaluse();
				}
			}

			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(103);
				whereClause();
				}
			}

			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(106);
				groupByCaluse();
				}
			}

			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(109);
				orderByClause();
				}
			}

			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(112);
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

	public static class JoinCaluseContext extends ParserRuleContext {
		public List<JoinElementContext> joinElement() {
			return getRuleContexts(JoinElementContext.class);
		}
		public JoinElementContext joinElement(int i) {
			return getRuleContext(JoinElementContext.class,i);
		}
		public JoinCaluseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinCaluse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterJoinCaluse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitJoinCaluse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitJoinCaluse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinCaluseContext joinCaluse() throws RecognitionException {
		JoinCaluseContext _localctx = new JoinCaluseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_joinCaluse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			joinElement();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT) | (1L << RIGHT) | (1L << INNER))) != 0)) {
				{
				{
				setState(116);
				joinElement();
				}
				}
				setState(121);
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

	public static class JoinElementContext extends ParserRuleContext {
		public JoinStatementContext joinStatement() {
			return getRuleContext(JoinStatementContext.class,0);
		}
		public SubSelectStatementContext subSelectStatement() {
			return getRuleContext(SubSelectStatementContext.class,0);
		}
		public OnStatementContext onStatement() {
			return getRuleContext(OnStatementContext.class,0);
		}
		public JoinElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterJoinElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitJoinElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitJoinElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinElementContext joinElement() throws RecognitionException {
		JoinElementContext _localctx = new JoinElementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_joinElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			joinStatement();
			setState(123);
			subSelectStatement();
			setState(124);
			onStatement();
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

	public static class JoinStatementContext extends ParserRuleContext {
		public JoinOperatorContext joinOperator() {
			return getRuleContext(JoinOperatorContext.class,0);
		}
		public TerminalNode JOIN() { return getToken(DSLSQLParser.JOIN, 0); }
		public JoinStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterJoinStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitJoinStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitJoinStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinStatementContext joinStatement() throws RecognitionException {
		JoinStatementContext _localctx = new JoinStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_joinStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			joinOperator();
			setState(127);
			match(JOIN);
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

	public static class JoinOperatorContext extends ParserRuleContext {
		public TerminalNode LEFT() { return getToken(DSLSQLParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(DSLSQLParser.RIGHT, 0); }
		public TerminalNode INNER() { return getToken(DSLSQLParser.INNER, 0); }
		public JoinOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterJoinOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitJoinOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitJoinOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinOperatorContext joinOperator() throws RecognitionException {
		JoinOperatorContext _localctx = new JoinOperatorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_joinOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT) | (1L << RIGHT) | (1L << INNER))) != 0)) ) {
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

	public static class OnStatementContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(DSLSQLParser.ON, 0); }
		public LogicExpressionsContext logicExpressions() {
			return getRuleContext(LogicExpressionsContext.class,0);
		}
		public OnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitOnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OnStatementContext onStatement() throws RecognitionException {
		OnStatementContext _localctx = new OnStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_onStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(ON);
			setState(132);
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

	public static class SubSelectStatementContext extends ParserRuleContext {
		public ServiceNameContext serviceName() {
			return getRuleContext(ServiceNameContext.class,0);
		}
		public UidContext uid() {
			return getRuleContext(UidContext.class,0);
		}
		public TerminalNode AS() { return getToken(DSLSQLParser.AS, 0); }
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public SubSelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subSelectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSubSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSubSelectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitSubSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubSelectStatementContext subSelectStatement() throws RecognitionException {
		SubSelectStatementContext _localctx = new SubSelectStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_subSelectStatement);
		int _la;
		try {
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				serviceName();
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || _la==ID) {
					{
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(135);
						match(AS);
						}
					}

					setState(138);
					uid();
					}
				}

				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				match(T__0);
				setState(142);
				selectStatement();
				setState(143);
				match(T__1);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(144);
					match(AS);
					}
				}

				setState(147);
				uid();
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
		enterRule(_localctx, 20, RULE_selectElements);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				((SelectElementsContext)_localctx).star = match(T__2);
				}
				break;
			case T__0:
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
				setState(152);
				selectElement();
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(153);
					match(T__3);
					setState(154);
					selectElement();
					}
					}
					setState(159);
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
		enterRule(_localctx, 22, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(WHERE);
			setState(163);
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
		enterRule(_localctx, 24, RULE_logicExpressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			logicExpression();
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(166);
				logicalOperator();
				setState(167);
				logicExpression();
				}
				}
				setState(173);
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
		public List<LogicExpressionCalContext> logicExpressionCal() {
			return getRuleContexts(LogicExpressionCalContext.class);
		}
		public LogicExpressionCalContext logicExpressionCal(int i) {
			return getRuleContext(LogicExpressionCalContext.class,i);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public TerminalNode BETWEEN() { return getToken(DSLSQLParser.BETWEEN, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
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
		enterRule(_localctx, 26, RULE_logicExpression);
		int _la;
		try {
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				logicExpressionCal();
				setState(175);
				comparisonOperator();
				setState(176);
				logicExpressionCal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(178);
				fullColumnName();
				setState(179);
				match(BETWEEN);
				setState(180);
				value();
				setState(181);
				match(AND);
				setState(182);
				value();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				fullColumnName();
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(185);
					match(NOT);
					}
				}

				setState(188);
				match(IN);
				setState(189);
				match(T__0);
				setState(190);
				value();
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(191);
					match(T__3);
					setState(192);
					value();
					}
					}
					setState(197);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(198);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(200);
				fullColumnName();
				setState(201);
				match(IS);
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(202);
					match(NOT);
					}
				}

				setState(205);
				match(NULL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(207);
				match(T__0);
				setState(208);
				logicExpressions();
				setState(209);
				match(T__1);
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

	public static class LogicExpressionCalContext extends ParserRuleContext {
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
		public LogicExpressionCalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicExpressionCal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterLogicExpressionCal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitLogicExpressionCal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitLogicExpressionCal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicExpressionCalContext logicExpressionCal() throws RecognitionException {
		LogicExpressionCalContext _localctx = new LogicExpressionCalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_logicExpressionCal);
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				fullColumnName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				arithmeticCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(215);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(216);
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
		enterRule(_localctx, 30, RULE_groupByCaluse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(GROUP);
			setState(220);
			match(BY);
			setState(221);
			groupByItem();
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(222);
				match(T__3);
				setState(223);
				groupByItem();
				}
				}
				setState(228);
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
		enterRule(_localctx, 32, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(ORDER);
			setState(230);
			match(BY);
			setState(231);
			orderByExpression();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(232);
				match(T__3);
				setState(233);
				orderByExpression();
				}
				}
				setState(238);
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
		enterRule(_localctx, 34, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(LIMIT);
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(243);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(240);
					((LimitClauseContext)_localctx).offset = decimalLiteral();
					setState(241);
					match(T__3);
					}
					break;
				}
				setState(245);
				((LimitClauseContext)_localctx).limit = decimalLiteral();
				}
				break;
			case 2:
				{
				setState(246);
				((LimitClauseContext)_localctx).limit = decimalLiteral();
				setState(247);
				match(OFFSET);
				setState(248);
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
		enterRule(_localctx, 36, RULE_orderByExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			fullColumnName();
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASC || _la==DESC) {
				{
				setState(253);
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
		enterRule(_localctx, 38, RULE_groupByItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
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
		enterRule(_localctx, 40, RULE_logicalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
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
		enterRule(_localctx, 42, RULE_comparisonOperator);
		int _la;
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				match(T__5);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(262);
				match(T__6);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(263);
				match(T__6);
				setState(264);
				match(T__4);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(265);
				match(T__5);
				setState(266);
				match(T__4);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(267);
				match(T__7);
				setState(268);
				match(T__4);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(269);
				match(T__6);
				setState(270);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(271);
					match(NOT);
					}
				}

				setState(274);
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
		enterRule(_localctx, 44, RULE_value);
		try {
			setState(279);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT_STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				textLiteral();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
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
		enterRule(_localctx, 46, RULE_decimalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
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
		enterRule(_localctx, 48, RULE_textLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
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
		enterRule(_localctx, 50, RULE_selectElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			selectElementCal();
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(286);
					match(AS);
					}
				}

				setState(289);
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
		enterRule(_localctx, 52, RULE_selectElementCal);
		try {
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				fullColumnName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				arithmeticCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(295);
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
		public UidContext uid() {
			return getRuleContext(UidContext.class,0);
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
		enterRule(_localctx, 54, RULE_fullColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(298);
				uid();
				setState(299);
				match(T__8);
				}
				break;
			}
			setState(303);
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
		public ArithmeticCallContext arithmeticCall() {
			return getRuleContext(ArithmeticCallContext.class,0);
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
		enterRule(_localctx, 56, RULE_arithmeticCall);
		int _la;
		try {
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				stringAndNumber();
				setState(309); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(306);
					arithmetic();
					setState(307);
					stringAndNumber();
					}
					}
					setState(311); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0) );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(T__0);
				setState(314);
				arithmeticCall();
				setState(315);
				match(T__1);
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

	public static class StringAndNumberContext extends ParserRuleContext {
		public FullColumnNameContext fullColumnName() {
			return getRuleContext(FullColumnNameContext.class,0);
		}
		public DecimalLiteralContext decimalLiteral() {
			return getRuleContext(DecimalLiteralContext.class,0);
		}
		public ArithmeticCallContext arithmeticCall() {
			return getRuleContext(ArithmeticCallContext.class,0);
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
		enterRule(_localctx, 58, RULE_stringAndNumber);
		try {
			setState(325);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				fullColumnName();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(320);
				decimalLiteral();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(321);
				match(T__0);
				setState(322);
				arithmeticCall();
				setState(323);
				match(T__1);
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
		enterRule(_localctx, 60, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
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
		enterRule(_localctx, 62, RULE_functionCall);
		try {
			setState(331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MAX:
			case SUM:
			case AVG:
			case MIN:
			case COUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				aggregateFunction();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
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
		enterRule(_localctx, 64, RULE_aggregateFunction);
		int _la;
		try {
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAX) | (1L << SUM) | (1L << AVG) | (1L << MIN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(334);
				match(T__0);
				setState(335);
				stringAndNumber();
				setState(336);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				match(COUNT);
				setState(339);
				match(T__0);
				setState(344);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
					{
					setState(340);
					((AggregateFunctionContext)_localctx).starArg = match(T__2);
					}
					break;
				case T__0:
				case T__1:
				case ID:
				case DECIMAL_LITERAL:
					{
					setState(342);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ID) | (1L << DECIMAL_LITERAL))) != 0)) {
						{
						setState(341);
						functionArg();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(346);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(347);
				match(COUNT);
				setState(348);
				match(T__0);
				setState(349);
				((AggregateFunctionContext)_localctx).aggregator = match(DISTINCT);
				setState(350);
				functionArgs();
				setState(351);
				match(T__1);
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
		enterRule(_localctx, 66, RULE_functionArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
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
		enterRule(_localctx, 68, RULE_functionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			stringAndNumber();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(358);
				match(T__3);
				setState(359);
				stringAndNumber();
				}
				}
				setState(364);
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
		enterRule(_localctx, 70, RULE_otherFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			otherFunctionName();
			setState(366);
			match(T__0);
			setState(367);
			otherFunctionArgs();
			setState(368);
			match(T__1);
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
		enterRule(_localctx, 72, RULE_otherFunctionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			selectElementCal();
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(371);
				match(T__3);
				setState(372);
				selectElementCal();
				}
				}
				setState(377);
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
		enterRule(_localctx, 74, RULE_otherFunctionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
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
		enterRule(_localctx, 76, RULE_serviceName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
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
		enterRule(_localctx, 78, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
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
		public Token aliasName;
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
		enterRule(_localctx, 80, RULE_uid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			((UidContext)_localctx).aliasName = match(ID);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u0185\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\5\2X\n\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4a\n\4\3\5\3\5\3\5\3\5\3\5"+
		"\5\5h\n\5\3\5\5\5k\n\5\3\5\5\5n\n\5\3\5\5\5q\n\5\3\5\5\5t\n\5\3\6\3\6"+
		"\7\6x\n\6\f\6\16\6{\13\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\5\13\u008b\n\13\3\13\5\13\u008e\n\13\3\13\3\13\3\13\3\13"+
		"\5\13\u0094\n\13\3\13\3\13\5\13\u0098\n\13\3\f\3\f\3\f\3\f\7\f\u009e\n"+
		"\f\f\f\16\f\u00a1\13\f\5\f\u00a3\n\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7"+
		"\16\u00ac\n\16\f\16\16\16\u00af\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\5\17\u00bd\n\17\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u00c4\n\17\f\17\16\17\u00c7\13\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u00ce\n\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00d6\n\17\3\20\3\20\3"+
		"\20\3\20\5\20\u00dc\n\20\3\21\3\21\3\21\3\21\3\21\7\21\u00e3\n\21\f\21"+
		"\16\21\u00e6\13\21\3\22\3\22\3\22\3\22\3\22\7\22\u00ed\n\22\f\22\16\22"+
		"\u00f0\13\22\3\23\3\23\3\23\3\23\5\23\u00f6\n\23\3\23\3\23\3\23\3\23\3"+
		"\23\5\23\u00fd\n\23\3\24\3\24\5\24\u0101\n\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0113\n\27"+
		"\3\27\5\27\u0116\n\27\3\30\3\30\5\30\u011a\n\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\5\33\u0122\n\33\3\33\5\33\u0125\n\33\3\34\3\34\3\34\3\34\5\34"+
		"\u012b\n\34\3\35\3\35\3\35\5\35\u0130\n\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\6\36\u0138\n\36\r\36\16\36\u0139\3\36\3\36\3\36\3\36\5\36\u0140\n"+
		"\36\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0148\n\37\3 \3 \3!\3!\5!\u014e"+
		"\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u0159\n\"\5\"\u015b\n\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u0164\n\"\3#\3#\3$\3$\3$\7$\u016b\n$\f"+
		"$\16$\u016e\13$\3%\3%\3%\3%\3%\3&\3&\3&\7&\u0178\n&\f&\16&\u017b\13&\3"+
		"\'\3\'\3(\3(\3)\3)\3*\3*\3*\2\2+\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\7\3\2-/\3\2#$\4\2\37\37\"\"\4\2"+
		"\5\5\f\16\3\2\22\25\2\u0194\2W\3\2\2\2\4Y\3\2\2\2\6\\\3\2\2\2\bb\3\2\2"+
		"\2\nu\3\2\2\2\f|\3\2\2\2\16\u0080\3\2\2\2\20\u0083\3\2\2\2\22\u0085\3"+
		"\2\2\2\24\u0097\3\2\2\2\26\u00a2\3\2\2\2\30\u00a4\3\2\2\2\32\u00a7\3\2"+
		"\2\2\34\u00d5\3\2\2\2\36\u00db\3\2\2\2 \u00dd\3\2\2\2\"\u00e7\3\2\2\2"+
		"$\u00f1\3\2\2\2&\u00fe\3\2\2\2(\u0102\3\2\2\2*\u0104\3\2\2\2,\u0115\3"+
		"\2\2\2.\u0119\3\2\2\2\60\u011b\3\2\2\2\62\u011d\3\2\2\2\64\u011f\3\2\2"+
		"\2\66\u012a\3\2\2\28\u012f\3\2\2\2:\u013f\3\2\2\2<\u0147\3\2\2\2>\u0149"+
		"\3\2\2\2@\u014d\3\2\2\2B\u0163\3\2\2\2D\u0165\3\2\2\2F\u0167\3\2\2\2H"+
		"\u016f\3\2\2\2J\u0174\3\2\2\2L\u017c\3\2\2\2N\u017e\3\2\2\2P\u0180\3\2"+
		"\2\2R\u0182\3\2\2\2TX\5\b\5\2UX\5\4\3\2VX\5\6\4\2WT\3\2\2\2WU\3\2\2\2"+
		"WV\3\2\2\2X\3\3\2\2\2YZ\7(\2\2Z[\5N(\2[\5\3\2\2\2\\]\7)\2\2]`\7*\2\2^"+
		"_\7\'\2\2_a\5\62\32\2`^\3\2\2\2`a\3\2\2\2a\7\3\2\2\2bc\7\20\2\2cd\5\26"+
		"\f\2de\7\21\2\2eg\5\24\13\2fh\5\n\6\2gf\3\2\2\2gh\3\2\2\2hj\3\2\2\2ik"+
		"\5\30\r\2ji\3\2\2\2jk\3\2\2\2km\3\2\2\2ln\5 \21\2ml\3\2\2\2mn\3\2\2\2"+
		"np\3\2\2\2oq\5\"\22\2po\3\2\2\2pq\3\2\2\2qs\3\2\2\2rt\5$\23\2sr\3\2\2"+
		"\2st\3\2\2\2t\t\3\2\2\2uy\5\f\7\2vx\5\f\7\2wv\3\2\2\2x{\3\2\2\2yw\3\2"+
		"\2\2yz\3\2\2\2z\13\3\2\2\2{y\3\2\2\2|}\5\16\b\2}~\5\24\13\2~\177\5\22"+
		"\n\2\177\r\3\2\2\2\u0080\u0081\5\20\t\2\u0081\u0082\7,\2\2\u0082\17\3"+
		"\2\2\2\u0083\u0084\t\2\2\2\u0084\21\3\2\2\2\u0085\u0086\7+\2\2\u0086\u0087"+
		"\5\32\16\2\u0087\23\3\2\2\2\u0088\u008d\5N(\2\u0089\u008b\7\17\2\2\u008a"+
		"\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\5R"+
		"*\2\u008d\u008a\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0098\3\2\2\2\u008f"+
		"\u0090\7\3\2\2\u0090\u0091\5\b\5\2\u0091\u0093\7\4\2\2\u0092\u0094\7\17"+
		"\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0096\5R*\2\u0096\u0098\3\2\2\2\u0097\u0088\3\2\2\2\u0097\u008f\3\2\2"+
		"\2\u0098\25\3\2\2\2\u0099\u00a3\7\5\2\2\u009a\u009f\5\64\33\2\u009b\u009c"+
		"\7\6\2\2\u009c\u009e\5\64\33\2\u009d\u009b\3\2\2\2\u009e\u00a1\3\2\2\2"+
		"\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f"+
		"\3\2\2\2\u00a2\u0099\3\2\2\2\u00a2\u009a\3\2\2\2\u00a3\27\3\2\2\2\u00a4"+
		"\u00a5\7\30\2\2\u00a5\u00a6\5\32\16\2\u00a6\31\3\2\2\2\u00a7\u00ad\5\34"+
		"\17\2\u00a8\u00a9\5*\26\2\u00a9\u00aa\5\34\17\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a8\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\33\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\5\36\20\2\u00b1"+
		"\u00b2\5,\27\2\u00b2\u00b3\5\36\20\2\u00b3\u00d6\3\2\2\2\u00b4\u00b5\5"+
		"8\35\2\u00b5\u00b6\7\36\2\2\u00b6\u00b7\5.\30\2\u00b7\u00b8\7\37\2\2\u00b8"+
		"\u00b9\5.\30\2\u00b9\u00d6\3\2\2\2\u00ba\u00bc\58\35\2\u00bb\u00bd\7\34"+
		"\2\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00bf\7 \2\2\u00bf\u00c0\7\3\2\2\u00c0\u00c5\5.\30\2\u00c1\u00c2\7\6"+
		"\2\2\u00c2\u00c4\5.\30\2\u00c3\u00c1\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00c5\3\2"+
		"\2\2\u00c8\u00c9\7\4\2\2\u00c9\u00d6\3\2\2\2\u00ca\u00cb\58\35\2\u00cb"+
		"\u00cd\7\35\2\2\u00cc\u00ce\7\34\2\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3"+
		"\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\7!\2\2\u00d0\u00d6\3\2\2\2\u00d1"+
		"\u00d2\7\3\2\2\u00d2\u00d3\5\32\16\2\u00d3\u00d4\7\4\2\2\u00d4\u00d6\3"+
		"\2\2\2\u00d5\u00b0\3\2\2\2\u00d5\u00b4\3\2\2\2\u00d5\u00ba\3\2\2\2\u00d5"+
		"\u00ca\3\2\2\2\u00d5\u00d1\3\2\2\2\u00d6\35\3\2\2\2\u00d7\u00dc\58\35"+
		"\2\u00d8\u00dc\5:\36\2\u00d9\u00dc\5@!\2\u00da\u00dc\5.\30\2\u00db\u00d7"+
		"\3\2\2\2\u00db\u00d8\3\2\2\2\u00db\u00d9\3\2\2\2\u00db\u00da\3\2\2\2\u00dc"+
		"\37\3\2\2\2\u00dd\u00de\7\31\2\2\u00de\u00df\7\32\2\2\u00df\u00e4\5(\25"+
		"\2\u00e0\u00e1\7\6\2\2\u00e1\u00e3\5(\25\2\u00e2\u00e0\3\2\2\2\u00e3\u00e6"+
		"\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5!\3\2\2\2\u00e6"+
		"\u00e4\3\2\2\2\u00e7\u00e8\7\33\2\2\u00e8\u00e9\7\32\2\2\u00e9\u00ee\5"+
		"&\24\2\u00ea\u00eb\7\6\2\2\u00eb\u00ed\5&\24\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef#\3\2\2\2"+
		"\u00f0\u00ee\3\2\2\2\u00f1\u00fc\7%\2\2\u00f2\u00f3\5\60\31\2\u00f3\u00f4"+
		"\7\6\2\2\u00f4\u00f6\3\2\2\2\u00f5\u00f2\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\u00fd\5\60\31\2\u00f8\u00f9\5\60\31\2\u00f9\u00fa"+
		"\7&\2\2\u00fa\u00fb\5\60\31\2\u00fb\u00fd\3\2\2\2\u00fc\u00f5\3\2\2\2"+
		"\u00fc\u00f8\3\2\2\2\u00fd%\3\2\2\2\u00fe\u0100\58\35\2\u00ff\u0101\t"+
		"\3\2\2\u0100\u00ff\3\2\2\2\u0100\u0101\3\2\2\2\u0101\'\3\2\2\2\u0102\u0103"+
		"\58\35\2\u0103)\3\2\2\2\u0104\u0105\t\4\2\2\u0105+\3\2\2\2\u0106\u0116"+
		"\7\7\2\2\u0107\u0116\7\b\2\2\u0108\u0116\7\t\2\2\u0109\u010a\7\t\2\2\u010a"+
		"\u0116\7\7\2\2\u010b\u010c\7\b\2\2\u010c\u0116\7\7\2\2\u010d\u010e\7\n"+
		"\2\2\u010e\u0116\7\7\2\2\u010f\u0110\7\t\2\2\u0110\u0116\7\b\2\2\u0111"+
		"\u0113\7\34\2\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3"+
		"\2\2\2\u0114\u0116\7\'\2\2\u0115\u0106\3\2\2\2\u0115\u0107\3\2\2\2\u0115"+
		"\u0108\3\2\2\2\u0115\u0109\3\2\2\2\u0115\u010b\3\2\2\2\u0115\u010d\3\2"+
		"\2\2\u0115\u010f\3\2\2\2\u0115\u0112\3\2\2\2\u0116-\3\2\2\2\u0117\u011a"+
		"\5\62\32\2\u0118\u011a\5\60\31\2\u0119\u0117\3\2\2\2\u0119\u0118\3\2\2"+
		"\2\u011a/\3\2\2\2\u011b\u011c\7\62\2\2\u011c\61\3\2\2\2\u011d\u011e\7"+
		"\61\2\2\u011e\63\3\2\2\2\u011f\u0124\5\66\34\2\u0120\u0122\7\17\2\2\u0121"+
		"\u0120\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0125\5R"+
		"*\2\u0124\u0121\3\2\2\2\u0124\u0125\3\2\2\2\u0125\65\3\2\2\2\u0126\u012b"+
		"\58\35\2\u0127\u012b\5:\36\2\u0128\u012b\5@!\2\u0129\u012b\5.\30\2\u012a"+
		"\u0126\3\2\2\2\u012a\u0127\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u0129\3\2"+
		"\2\2\u012b\67\3\2\2\2\u012c\u012d\5R*\2\u012d\u012e\7\13\2\2\u012e\u0130"+
		"\3\2\2\2\u012f\u012c\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\u0132\5P)\2\u01329\3\2\2\2\u0133\u0137\5<\37\2\u0134\u0135\5> \2\u0135"+
		"\u0136\5<\37\2\u0136\u0138\3\2\2\2\u0137\u0134\3\2\2\2\u0138\u0139\3\2"+
		"\2\2\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u0140\3\2\2\2\u013b"+
		"\u013c\7\3\2\2\u013c\u013d\5:\36\2\u013d\u013e\7\4\2\2\u013e\u0140\3\2"+
		"\2\2\u013f\u0133\3\2\2\2\u013f\u013b\3\2\2\2\u0140;\3\2\2\2\u0141\u0148"+
		"\58\35\2\u0142\u0148\5\60\31\2\u0143\u0144\7\3\2\2\u0144\u0145\5:\36\2"+
		"\u0145\u0146\7\4\2\2\u0146\u0148\3\2\2\2\u0147\u0141\3\2\2\2\u0147\u0142"+
		"\3\2\2\2\u0147\u0143\3\2\2\2\u0148=\3\2\2\2\u0149\u014a\t\5\2\2\u014a"+
		"?\3\2\2\2\u014b\u014e\5B\"\2\u014c\u014e\5H%\2\u014d\u014b\3\2\2\2\u014d"+
		"\u014c\3\2\2\2\u014eA\3\2\2\2\u014f\u0150\t\6\2\2\u0150\u0151\7\3\2\2"+
		"\u0151\u0152\5<\37\2\u0152\u0153\7\4\2\2\u0153\u0164\3\2\2\2\u0154\u0155"+
		"\7\26\2\2\u0155\u015a\7\3\2\2\u0156\u015b\7\5\2\2\u0157\u0159\5D#\2\u0158"+
		"\u0157\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u0156\3\2"+
		"\2\2\u015a\u0158\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u0164\7\4\2\2\u015d"+
		"\u015e\7\26\2\2\u015e\u015f\7\3\2\2\u015f\u0160\7\27\2\2\u0160\u0161\5"+
		"F$\2\u0161\u0162\7\4\2\2\u0162\u0164\3\2\2\2\u0163\u014f\3\2\2\2\u0163"+
		"\u0154\3\2\2\2\u0163\u015d\3\2\2\2\u0164C\3\2\2\2\u0165\u0166\5<\37\2"+
		"\u0166E\3\2\2\2\u0167\u016c\5<\37\2\u0168\u0169\7\6\2\2\u0169\u016b\5"+
		"<\37\2\u016a\u0168\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016dG\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0170\5L\'\2\u0170"+
		"\u0171\7\3\2\2\u0171\u0172\5J&\2\u0172\u0173\7\4\2\2\u0173I\3\2\2\2\u0174"+
		"\u0179\5\66\34\2\u0175\u0176\7\6\2\2\u0176\u0178\5\66\34\2\u0177\u0175"+
		"\3\2\2\2\u0178\u017b\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a"+
		"K\3\2\2\2\u017b\u0179\3\2\2\2\u017c\u017d\7\60\2\2\u017dM\3\2\2\2\u017e"+
		"\u017f\7\60\2\2\u017fO\3\2\2\2\u0180\u0181\7\60\2\2\u0181Q\3\2\2\2\u0182"+
		"\u0183\7\60\2\2\u0183S\3\2\2\2+W`gjmpsy\u008a\u008d\u0093\u0097\u009f"+
		"\u00a2\u00ad\u00bc\u00c5\u00cd\u00d5\u00db\u00e4\u00ee\u00f5\u00fc\u0100"+
		"\u0112\u0115\u0119\u0121\u0124\u012a\u012f\u0139\u013f\u0147\u014d\u0158"+
		"\u015a\u0163\u016c\u0179";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}