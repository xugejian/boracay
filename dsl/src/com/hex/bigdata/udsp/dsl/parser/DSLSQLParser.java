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
		RULE_selectElements = 10, RULE_star = 11, RULE_whereClause = 12, RULE_logicExpressions = 13, 
		RULE_logicExpression = 14, RULE_logicExpressionCal = 15, RULE_groupByCaluse = 16, 
		RULE_orderByClause = 17, RULE_limitClause = 18, RULE_orderByExpression = 19, 
		RULE_groupByItem = 20, RULE_logicalOperator = 21, RULE_comparisonOperator = 22, 
		RULE_value = 23, RULE_decimalLiteral = 24, RULE_textLiteral = 25, RULE_selectElement = 26, 
		RULE_selectElementCal = 27, RULE_fullColumnName = 28, RULE_arithmeticCall = 29, 
		RULE_stringAndNumber = 30, RULE_arithmetic = 31, RULE_functionCall = 32, 
		RULE_aggregateFunction = 33, RULE_functionArg = 34, RULE_functionArgs = 35, 
		RULE_otherFunction = 36, RULE_otherFunctionArgs = 37, RULE_otherFunctionName = 38, 
		RULE_serviceName = 39, RULE_columnName = 40, RULE_uid = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "describeServiceStatement", "showServicesStatement", "selectStatement", 
			"joinCaluse", "joinElement", "joinStatement", "joinOperator", "onStatement", 
			"subSelectStatement", "selectElements", "star", "whereClause", "logicExpressions", 
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
			null, "'('", "')'", "','", "'.'", "'*'", "'='", "'>'", "'<'", "'!'", 
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
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				selectStatement();
				}
				break;
			case DESCRIBE:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				describeServiceStatement();
				}
				break;
			case SHOW:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
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
			setState(89);
			match(DESCRIBE);
			setState(90);
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
			setState(92);
			match(SHOW);
			setState(93);
			match(SERVICES);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIKE) {
				{
				setState(94);
				match(LIKE);
				setState(95);
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
			setState(98);
			match(SELECT);
			setState(99);
			selectElements();
			setState(100);
			match(FROM);
			setState(101);
			subSelectStatement();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT) | (1L << RIGHT) | (1L << INNER))) != 0)) {
				{
				setState(102);
				joinCaluse();
				}
			}

			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(105);
				whereClause();
				}
			}

			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(108);
				groupByCaluse();
				}
			}

			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(111);
				orderByClause();
				}
			}

			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(114);
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
			setState(117);
			joinElement();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEFT) | (1L << RIGHT) | (1L << INNER))) != 0)) {
				{
				{
				setState(118);
				joinElement();
				}
				}
				setState(123);
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
			setState(124);
			joinStatement();
			setState(125);
			subSelectStatement();
			setState(126);
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
			setState(128);
			joinOperator();
			setState(129);
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
			setState(131);
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
			setState(133);
			match(ON);
			setState(134);
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
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				serviceName();
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || _la==ID) {
					{
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(137);
						match(AS);
						}
					}

					setState(140);
					uid();
					}
				}

				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(T__0);
				setState(144);
				selectStatement();
				setState(145);
				match(T__1);
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(146);
					match(AS);
					}
				}

				setState(149);
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
		public List<StarContext> star() {
			return getRuleContexts(StarContext.class);
		}
		public StarContext star(int i) {
			return getRuleContext(StarContext.class,i);
		}
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
			setState(169);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				star();
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(154);
					match(T__2);
					setState(155);
					star();
					}
					}
					setState(160);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				selectElement();
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(162);
					match(T__2);
					setState(163);
					selectElement();
					}
					}
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class StarContext extends ParserRuleContext {
		public UidContext uid() {
			return getRuleContext(UidContext.class,0);
		}
		public StarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_star; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterStar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitStar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DSLSQLVisitor ) return ((DSLSQLVisitor<? extends T>)visitor).visitStar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StarContext star() throws RecognitionException {
		StarContext _localctx = new StarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_star);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(171);
				uid();
				setState(172);
				match(T__3);
				}
			}

			setState(176);
			match(T__4);
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
		enterRule(_localctx, 24, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(WHERE);
			setState(179);
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
		enterRule(_localctx, 26, RULE_logicExpressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			logicExpression();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(182);
				logicalOperator();
				setState(183);
				logicExpression();
				}
				}
				setState(189);
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
		enterRule(_localctx, 28, RULE_logicExpression);
		int _la;
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				logicExpressionCal();
				setState(191);
				comparisonOperator();
				setState(192);
				logicExpressionCal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				fullColumnName();
				setState(195);
				match(BETWEEN);
				setState(196);
				value();
				setState(197);
				match(AND);
				setState(198);
				value();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				fullColumnName();
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(201);
					match(NOT);
					}
				}

				setState(204);
				match(IN);
				setState(205);
				match(T__0);
				setState(206);
				value();
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(207);
					match(T__2);
					setState(208);
					value();
					}
					}
					setState(213);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(214);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(216);
				fullColumnName();
				setState(217);
				match(IS);
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(218);
					match(NOT);
					}
				}

				setState(221);
				match(NULL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(223);
				match(T__0);
				setState(224);
				logicExpressions();
				setState(225);
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
		enterRule(_localctx, 30, RULE_logicExpressionCal);
		try {
			setState(233);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				fullColumnName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				arithmeticCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(231);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(232);
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
		enterRule(_localctx, 32, RULE_groupByCaluse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(GROUP);
			setState(236);
			match(BY);
			setState(237);
			groupByItem();
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(238);
				match(T__2);
				setState(239);
				groupByItem();
				}
				}
				setState(244);
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
		enterRule(_localctx, 34, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(ORDER);
			setState(246);
			match(BY);
			setState(247);
			orderByExpression();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(248);
				match(T__2);
				setState(249);
				orderByExpression();
				}
				}
				setState(254);
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
		enterRule(_localctx, 36, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(LIMIT);
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(259);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(256);
					((LimitClauseContext)_localctx).offset = decimalLiteral();
					setState(257);
					match(T__2);
					}
					break;
				}
				setState(261);
				((LimitClauseContext)_localctx).limit = decimalLiteral();
				}
				break;
			case 2:
				{
				setState(262);
				((LimitClauseContext)_localctx).limit = decimalLiteral();
				setState(263);
				match(OFFSET);
				setState(264);
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
		enterRule(_localctx, 38, RULE_orderByExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			fullColumnName();
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASC || _la==DESC) {
				{
				setState(269);
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
		enterRule(_localctx, 40, RULE_groupByItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
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
		enterRule(_localctx, 42, RULE_logicalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
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
		enterRule(_localctx, 44, RULE_comparisonOperator);
		int _la;
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(276);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				match(T__6);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(278);
				match(T__7);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(279);
				match(T__7);
				setState(280);
				match(T__5);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(281);
				match(T__6);
				setState(282);
				match(T__5);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(283);
				match(T__8);
				setState(284);
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(285);
				match(T__7);
				setState(286);
				match(T__6);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(287);
					match(NOT);
					}
				}

				setState(290);
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
		enterRule(_localctx, 46, RULE_value);
		try {
			setState(295);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT_STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				textLiteral();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(294);
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
		enterRule(_localctx, 48, RULE_decimalLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
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
		enterRule(_localctx, 50, RULE_textLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
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
		enterRule(_localctx, 52, RULE_selectElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			selectElementCal();
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(302);
					match(AS);
					}
				}

				setState(305);
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
		enterRule(_localctx, 54, RULE_selectElementCal);
		try {
			setState(312);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				fullColumnName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				arithmeticCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(310);
				functionCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(311);
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
		enterRule(_localctx, 56, RULE_fullColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(314);
				uid();
				setState(315);
				match(T__3);
				}
				break;
			}
			setState(319);
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
		enterRule(_localctx, 58, RULE_arithmeticCall);
		int _la;
		try {
			setState(333);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				stringAndNumber();
				setState(325); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(322);
					arithmetic();
					setState(323);
					stringAndNumber();
					}
					}
					setState(327); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0) );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				match(T__0);
				setState(330);
				arithmeticCall();
				setState(331);
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
		enterRule(_localctx, 60, RULE_stringAndNumber);
		try {
			setState(341);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(335);
				fullColumnName();
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(336);
				decimalLiteral();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(337);
				match(T__0);
				setState(338);
				arithmeticCall();
				setState(339);
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
		enterRule(_localctx, 62, RULE_arithmetic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
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
		enterRule(_localctx, 64, RULE_functionCall);
		try {
			setState(347);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MAX:
			case SUM:
			case AVG:
			case MIN:
			case COUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(345);
				aggregateFunction();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(346);
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
		enterRule(_localctx, 66, RULE_aggregateFunction);
		int _la;
		try {
			setState(369);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(349);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAX) | (1L << SUM) | (1L << AVG) | (1L << MIN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(350);
				match(T__0);
				setState(351);
				stringAndNumber();
				setState(352);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(354);
				match(COUNT);
				setState(355);
				match(T__0);
				setState(360);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(356);
					((AggregateFunctionContext)_localctx).starArg = match(T__4);
					}
					break;
				case T__0:
				case T__1:
				case ID:
				case DECIMAL_LITERAL:
					{
					setState(358);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ID) | (1L << DECIMAL_LITERAL))) != 0)) {
						{
						setState(357);
						functionArg();
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(362);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(363);
				match(COUNT);
				setState(364);
				match(T__0);
				setState(365);
				((AggregateFunctionContext)_localctx).aggregator = match(DISTINCT);
				setState(366);
				functionArgs();
				setState(367);
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
		enterRule(_localctx, 68, RULE_functionArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
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
		enterRule(_localctx, 70, RULE_functionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			stringAndNumber();
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(374);
				match(T__2);
				setState(375);
				stringAndNumber();
				}
				}
				setState(380);
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
		enterRule(_localctx, 72, RULE_otherFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			otherFunctionName();
			setState(382);
			match(T__0);
			setState(383);
			otherFunctionArgs();
			setState(384);
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
		enterRule(_localctx, 74, RULE_otherFunctionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			selectElementCal();
			setState(391);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(387);
				match(T__2);
				setState(388);
				selectElementCal();
				}
				}
				setState(393);
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
		enterRule(_localctx, 76, RULE_otherFunctionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
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
		enterRule(_localctx, 78, RULE_serviceName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
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
		enterRule(_localctx, 80, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
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
		enterRule(_localctx, 82, RULE_uid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u0195\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\2\5\2Z\n\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4c\n\4\3\5\3\5\3\5\3"+
		"\5\3\5\5\5j\n\5\3\5\5\5m\n\5\3\5\5\5p\n\5\3\5\5\5s\n\5\3\5\5\5v\n\5\3"+
		"\6\3\6\7\6z\n\6\f\6\16\6}\13\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\n\3\13\3\13\5\13\u008d\n\13\3\13\5\13\u0090\n\13\3\13\3\13\3\13"+
		"\3\13\5\13\u0096\n\13\3\13\3\13\5\13\u009a\n\13\3\f\3\f\3\f\7\f\u009f"+
		"\n\f\f\f\16\f\u00a2\13\f\3\f\3\f\3\f\7\f\u00a7\n\f\f\f\16\f\u00aa\13\f"+
		"\5\f\u00ac\n\f\3\r\3\r\3\r\5\r\u00b1\n\r\3\r\3\r\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\7\17\u00bc\n\17\f\17\16\17\u00bf\13\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00cd\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\7\20\u00d4\n\20\f\20\16\20\u00d7\13\20\3\20\3\20\3\20"+
		"\3\20\3\20\5\20\u00de\n\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00e6\n"+
		"\20\3\21\3\21\3\21\3\21\5\21\u00ec\n\21\3\22\3\22\3\22\3\22\3\22\7\22"+
		"\u00f3\n\22\f\22\16\22\u00f6\13\22\3\23\3\23\3\23\3\23\3\23\7\23\u00fd"+
		"\n\23\f\23\16\23\u0100\13\23\3\24\3\24\3\24\3\24\5\24\u0106\n\24\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u010d\n\24\3\25\3\25\5\25\u0111\n\25\3\26\3"+
		"\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\5\30\u0123\n\30\3\30\5\30\u0126\n\30\3\31\3\31\5\31\u012a\n\31\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\5\34\u0132\n\34\3\34\5\34\u0135\n\34\3\35\3"+
		"\35\3\35\3\35\5\35\u013b\n\35\3\36\3\36\3\36\5\36\u0140\n\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\6\37\u0148\n\37\r\37\16\37\u0149\3\37\3\37\3\37\3"+
		"\37\5\37\u0150\n\37\3 \3 \3 \3 \3 \3 \5 \u0158\n \3!\3!\3\"\3\"\5\"\u015e"+
		"\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0169\n#\5#\u016b\n#\3#\3#\3#\3#\3"+
		"#\3#\3#\5#\u0174\n#\3$\3$\3%\3%\3%\7%\u017b\n%\f%\16%\u017e\13%\3&\3&"+
		"\3&\3&\3&\3\'\3\'\3\'\7\'\u0188\n\'\f\'\16\'\u018b\13\'\3(\3(\3)\3)\3"+
		"*\3*\3+\3+\3+\2\2,\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFHJLNPRT\2\7\3\2-/\3\2#$\4\2\37\37\"\"\4\2\7\7\f\16\3"+
		"\2\22\25\2\u01a5\2Y\3\2\2\2\4[\3\2\2\2\6^\3\2\2\2\bd\3\2\2\2\nw\3\2\2"+
		"\2\f~\3\2\2\2\16\u0082\3\2\2\2\20\u0085\3\2\2\2\22\u0087\3\2\2\2\24\u0099"+
		"\3\2\2\2\26\u00ab\3\2\2\2\30\u00b0\3\2\2\2\32\u00b4\3\2\2\2\34\u00b7\3"+
		"\2\2\2\36\u00e5\3\2\2\2 \u00eb\3\2\2\2\"\u00ed\3\2\2\2$\u00f7\3\2\2\2"+
		"&\u0101\3\2\2\2(\u010e\3\2\2\2*\u0112\3\2\2\2,\u0114\3\2\2\2.\u0125\3"+
		"\2\2\2\60\u0129\3\2\2\2\62\u012b\3\2\2\2\64\u012d\3\2\2\2\66\u012f\3\2"+
		"\2\28\u013a\3\2\2\2:\u013f\3\2\2\2<\u014f\3\2\2\2>\u0157\3\2\2\2@\u0159"+
		"\3\2\2\2B\u015d\3\2\2\2D\u0173\3\2\2\2F\u0175\3\2\2\2H\u0177\3\2\2\2J"+
		"\u017f\3\2\2\2L\u0184\3\2\2\2N\u018c\3\2\2\2P\u018e\3\2\2\2R\u0190\3\2"+
		"\2\2T\u0192\3\2\2\2VZ\5\b\5\2WZ\5\4\3\2XZ\5\6\4\2YV\3\2\2\2YW\3\2\2\2"+
		"YX\3\2\2\2Z\3\3\2\2\2[\\\7(\2\2\\]\5P)\2]\5\3\2\2\2^_\7)\2\2_b\7*\2\2"+
		"`a\7\'\2\2ac\5\64\33\2b`\3\2\2\2bc\3\2\2\2c\7\3\2\2\2de\7\20\2\2ef\5\26"+
		"\f\2fg\7\21\2\2gi\5\24\13\2hj\5\n\6\2ih\3\2\2\2ij\3\2\2\2jl\3\2\2\2km"+
		"\5\32\16\2lk\3\2\2\2lm\3\2\2\2mo\3\2\2\2np\5\"\22\2on\3\2\2\2op\3\2\2"+
		"\2pr\3\2\2\2qs\5$\23\2rq\3\2\2\2rs\3\2\2\2su\3\2\2\2tv\5&\24\2ut\3\2\2"+
		"\2uv\3\2\2\2v\t\3\2\2\2w{\5\f\7\2xz\5\f\7\2yx\3\2\2\2z}\3\2\2\2{y\3\2"+
		"\2\2{|\3\2\2\2|\13\3\2\2\2}{\3\2\2\2~\177\5\16\b\2\177\u0080\5\24\13\2"+
		"\u0080\u0081\5\22\n\2\u0081\r\3\2\2\2\u0082\u0083\5\20\t\2\u0083\u0084"+
		"\7,\2\2\u0084\17\3\2\2\2\u0085\u0086\t\2\2\2\u0086\21\3\2\2\2\u0087\u0088"+
		"\7+\2\2\u0088\u0089\5\34\17\2\u0089\23\3\2\2\2\u008a\u008f\5P)\2\u008b"+
		"\u008d\7\17\2\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3"+
		"\2\2\2\u008e\u0090\5T+\2\u008f\u008c\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u009a\3\2\2\2\u0091\u0092\7\3\2\2\u0092\u0093\5\b\5\2\u0093\u0095\7\4"+
		"\2\2\u0094\u0096\7\17\2\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\u0098\5T+\2\u0098\u009a\3\2\2\2\u0099\u008a\3\2\2"+
		"\2\u0099\u0091\3\2\2\2\u009a\25\3\2\2\2\u009b\u00a0\5\30\r\2\u009c\u009d"+
		"\7\5\2\2\u009d\u009f\5\30\r\2\u009e\u009c\3\2\2\2\u009f\u00a2\3\2\2\2"+
		"\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00ac\3\2\2\2\u00a2\u00a0"+
		"\3\2\2\2\u00a3\u00a8\5\66\34\2\u00a4\u00a5\7\5\2\2\u00a5\u00a7\5\66\34"+
		"\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u009b\3\2\2\2\u00ab"+
		"\u00a3\3\2\2\2\u00ac\27\3\2\2\2\u00ad\u00ae\5T+\2\u00ae\u00af\7\6\2\2"+
		"\u00af\u00b1\3\2\2\2\u00b0\u00ad\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2"+
		"\3\2\2\2\u00b2\u00b3\7\7\2\2\u00b3\31\3\2\2\2\u00b4\u00b5\7\30\2\2\u00b5"+
		"\u00b6\5\34\17\2\u00b6\33\3\2\2\2\u00b7\u00bd\5\36\20\2\u00b8\u00b9\5"+
		",\27\2\u00b9\u00ba\5\36\20\2\u00ba\u00bc\3\2\2\2\u00bb\u00b8\3\2\2\2\u00bc"+
		"\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\35\3\2\2"+
		"\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1\5 \21\2\u00c1\u00c2\5.\30\2\u00c2\u00c3"+
		"\5 \21\2\u00c3\u00e6\3\2\2\2\u00c4\u00c5\5:\36\2\u00c5\u00c6\7\36\2\2"+
		"\u00c6\u00c7\5\60\31\2\u00c7\u00c8\7\37\2\2\u00c8\u00c9\5\60\31\2\u00c9"+
		"\u00e6\3\2\2\2\u00ca\u00cc\5:\36\2\u00cb\u00cd\7\34\2\2\u00cc\u00cb\3"+
		"\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7 \2\2\u00cf"+
		"\u00d0\7\3\2\2\u00d0\u00d5\5\60\31\2\u00d1\u00d2\7\5\2\2\u00d2\u00d4\5"+
		"\60\31\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\7\4"+
		"\2\2\u00d9\u00e6\3\2\2\2\u00da\u00db\5:\36\2\u00db\u00dd\7\35\2\2\u00dc"+
		"\u00de\7\34\2\2\u00dd\u00dc\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\3"+
		"\2\2\2\u00df\u00e0\7!\2\2\u00e0\u00e6\3\2\2\2\u00e1\u00e2\7\3\2\2\u00e2"+
		"\u00e3\5\34\17\2\u00e3\u00e4\7\4\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00c0\3"+
		"\2\2\2\u00e5\u00c4\3\2\2\2\u00e5\u00ca\3\2\2\2\u00e5\u00da\3\2\2\2\u00e5"+
		"\u00e1\3\2\2\2\u00e6\37\3\2\2\2\u00e7\u00ec\5:\36\2\u00e8\u00ec\5<\37"+
		"\2\u00e9\u00ec\5B\"\2\u00ea\u00ec\5\60\31\2\u00eb\u00e7\3\2\2\2\u00eb"+
		"\u00e8\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec!\3\2\2\2"+
		"\u00ed\u00ee\7\31\2\2\u00ee\u00ef\7\32\2\2\u00ef\u00f4\5*\26\2\u00f0\u00f1"+
		"\7\5\2\2\u00f1\u00f3\5*\26\2\u00f2\u00f0\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5#\3\2\2\2\u00f6\u00f4\3\2\2\2"+
		"\u00f7\u00f8\7\33\2\2\u00f8\u00f9\7\32\2\2\u00f9\u00fe\5(\25\2\u00fa\u00fb"+
		"\7\5\2\2\u00fb\u00fd\5(\25\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff%\3\2\2\2\u0100\u00fe\3\2\2\2"+
		"\u0101\u010c\7%\2\2\u0102\u0103\5\62\32\2\u0103\u0104\7\5\2\2\u0104\u0106"+
		"\3\2\2\2\u0105\u0102\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107"+
		"\u010d\5\62\32\2\u0108\u0109\5\62\32\2\u0109\u010a\7&\2\2\u010a\u010b"+
		"\5\62\32\2\u010b\u010d\3\2\2\2\u010c\u0105\3\2\2\2\u010c\u0108\3\2\2\2"+
		"\u010d\'\3\2\2\2\u010e\u0110\5:\36\2\u010f\u0111\t\3\2\2\u0110\u010f\3"+
		"\2\2\2\u0110\u0111\3\2\2\2\u0111)\3\2\2\2\u0112\u0113\5:\36\2\u0113+\3"+
		"\2\2\2\u0114\u0115\t\4\2\2\u0115-\3\2\2\2\u0116\u0126\7\b\2\2\u0117\u0126"+
		"\7\t\2\2\u0118\u0126\7\n\2\2\u0119\u011a\7\n\2\2\u011a\u0126\7\b\2\2\u011b"+
		"\u011c\7\t\2\2\u011c\u0126\7\b\2\2\u011d\u011e\7\13\2\2\u011e\u0126\7"+
		"\b\2\2\u011f\u0120\7\n\2\2\u0120\u0126\7\t\2\2\u0121\u0123\7\34\2\2\u0122"+
		"\u0121\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\7\'"+
		"\2\2\u0125\u0116\3\2\2\2\u0125\u0117\3\2\2\2\u0125\u0118\3\2\2\2\u0125"+
		"\u0119\3\2\2\2\u0125\u011b\3\2\2\2\u0125\u011d\3\2\2\2\u0125\u011f\3\2"+
		"\2\2\u0125\u0122\3\2\2\2\u0126/\3\2\2\2\u0127\u012a\5\64\33\2\u0128\u012a"+
		"\5\62\32\2\u0129\u0127\3\2\2\2\u0129\u0128\3\2\2\2\u012a\61\3\2\2\2\u012b"+
		"\u012c\7\62\2\2\u012c\63\3\2\2\2\u012d\u012e\7\61\2\2\u012e\65\3\2\2\2"+
		"\u012f\u0134\58\35\2\u0130\u0132\7\17\2\2\u0131\u0130\3\2\2\2\u0131\u0132"+
		"\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0135\5T+\2\u0134\u0131\3\2\2\2\u0134"+
		"\u0135\3\2\2\2\u0135\67\3\2\2\2\u0136\u013b\5:\36\2\u0137\u013b\5<\37"+
		"\2\u0138\u013b\5B\"\2\u0139\u013b\5\60\31\2\u013a\u0136\3\2\2\2\u013a"+
		"\u0137\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u0139\3\2\2\2\u013b9\3\2\2\2"+
		"\u013c\u013d\5T+\2\u013d\u013e\7\6\2\2\u013e\u0140\3\2\2\2\u013f\u013c"+
		"\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\5R*\2\u0142"+
		";\3\2\2\2\u0143\u0147\5> \2\u0144\u0145\5@!\2\u0145\u0146\5> \2\u0146"+
		"\u0148\3\2\2\2\u0147\u0144\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2"+
		"\2\2\u0149\u014a\3\2\2\2\u014a\u0150\3\2\2\2\u014b\u014c\7\3\2\2\u014c"+
		"\u014d\5<\37\2\u014d\u014e\7\4\2\2\u014e\u0150\3\2\2\2\u014f\u0143\3\2"+
		"\2\2\u014f\u014b\3\2\2\2\u0150=\3\2\2\2\u0151\u0158\5:\36\2\u0152\u0158"+
		"\5\62\32\2\u0153\u0154\7\3\2\2\u0154\u0155\5<\37\2\u0155\u0156\7\4\2\2"+
		"\u0156\u0158\3\2\2\2\u0157\u0151\3\2\2\2\u0157\u0152\3\2\2\2\u0157\u0153"+
		"\3\2\2\2\u0158?\3\2\2\2\u0159\u015a\t\5\2\2\u015aA\3\2\2\2\u015b\u015e"+
		"\5D#\2\u015c\u015e\5J&\2\u015d\u015b\3\2\2\2\u015d\u015c\3\2\2\2\u015e"+
		"C\3\2\2\2\u015f\u0160\t\6\2\2\u0160\u0161\7\3\2\2\u0161\u0162\5> \2\u0162"+
		"\u0163\7\4\2\2\u0163\u0174\3\2\2\2\u0164\u0165\7\26\2\2\u0165\u016a\7"+
		"\3\2\2\u0166\u016b\7\7\2\2\u0167\u0169\5F$\2\u0168\u0167\3\2\2\2\u0168"+
		"\u0169\3\2\2\2\u0169\u016b\3\2\2\2\u016a\u0166\3\2\2\2\u016a\u0168\3\2"+
		"\2\2\u016b\u016c\3\2\2\2\u016c\u0174\7\4\2\2\u016d\u016e\7\26\2\2\u016e"+
		"\u016f\7\3\2\2\u016f\u0170\7\27\2\2\u0170\u0171\5H%\2\u0171\u0172\7\4"+
		"\2\2\u0172\u0174\3\2\2\2\u0173\u015f\3\2\2\2\u0173\u0164\3\2\2\2\u0173"+
		"\u016d\3\2\2\2\u0174E\3\2\2\2\u0175\u0176\5> \2\u0176G\3\2\2\2\u0177\u017c"+
		"\5> \2\u0178\u0179\7\5\2\2\u0179\u017b\5> \2\u017a\u0178\3\2\2\2\u017b"+
		"\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017dI\3\2\2\2"+
		"\u017e\u017c\3\2\2\2\u017f\u0180\5N(\2\u0180\u0181\7\3\2\2\u0181\u0182"+
		"\5L\'\2\u0182\u0183\7\4\2\2\u0183K\3\2\2\2\u0184\u0189\58\35\2\u0185\u0186"+
		"\7\5\2\2\u0186\u0188\58\35\2\u0187\u0185\3\2\2\2\u0188\u018b\3\2\2\2\u0189"+
		"\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018aM\3\2\2\2\u018b\u0189\3\2\2\2"+
		"\u018c\u018d\7\60\2\2\u018dO\3\2\2\2\u018e\u018f\7\60\2\2\u018fQ\3\2\2"+
		"\2\u0190\u0191\7\60\2\2\u0191S\3\2\2\2\u0192\u0193\7\60\2\2\u0193U\3\2"+
		"\2\2-Ybiloru{\u008c\u008f\u0095\u0099\u00a0\u00a8\u00ab\u00b0\u00bd\u00cc"+
		"\u00d5\u00dd\u00e5\u00eb\u00f4\u00fe\u0105\u010c\u0110\u0122\u0125\u0129"+
		"\u0131\u0134\u013a\u013f\u0149\u014f\u0157\u015d\u0168\u016a\u0173\u017c"+
		"\u0189";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}