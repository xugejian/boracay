// Generated from B:/workspaces/IntellijIdeaGithubWorkspaces/github/boracay/dsl/resource\DSLSQL.g4 by ANTLR 4.7.2

    package com.hex.bigdata.udsp.dsl.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DSLSQLLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "AS", "SELECT", "FROM", "MAX", "SUM", "AVG", 
			"MIN", "COUNT", "DISTINCT", "WHERE", "GROUP", "BY", "ORDER", "NOT", "IS", 
			"BETWEEN", "AND", "IN", "NULL", "OR", "ASC", "DESC", "LIMIT", "OFFSET", 
			"LIKE", "DESCRIBE", "SHOW", "SERVICES", "ON", "JOIN", "LEFT", "RIGHT", 
			"INNER", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", 
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", 
			"HEX_DIGIT", "DEC_DIGIT", "LETTER", "ID", "TEXT_STRING", "DECIMAL_LITERAL", 
			"WS"
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


	public DSLSQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DSLSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\63\u01bf\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"+
		"I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3"+
		"\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#"+
		"\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)"+
		"\3)\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\3."+
		"\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65"+
		"\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3"+
		"?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3J\3"+
		"J\3K\3K\3L\3L\7L\u01a0\nL\fL\16L\u01a3\13L\3M\3M\3M\3M\3M\3M\3M\3M\7M"+
		"\u01ad\nM\fM\16M\u01b0\13M\3M\3M\3N\6N\u01b5\nN\rN\16N\u01b6\3O\6O\u01ba"+
		"\nO\rO\16O\u01bb\3O\3O\2\2P\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\2_\2a\2"+
		"c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2\177\2\u0081\2\u0083\2\u0085"+
		"\2\u0087\2\u0089\2\u008b\2\u008d\2\u008f\2\u0091\2\u0093\2\u0095\2\u0097"+
		"\60\u0099\61\u009b\62\u009d\63\3\2\"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4"+
		"\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOo"+
		"o\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2"+
		"XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\4\2\62;CH\3\2\62;\4\2C\\c|\6\2"+
		"\62;C\\aac|\3\2))\5\2\13\f\17\17\"\"\2\u01a8\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2"+
		"\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2"+
		"M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3"+
		"\2\2\2\2[\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d"+
		"\3\2\2\2\3\u009f\3\2\2\2\5\u00a1\3\2\2\2\7\u00a3\3\2\2\2\t\u00a5\3\2\2"+
		"\2\13\u00a7\3\2\2\2\r\u00a9\3\2\2\2\17\u00ab\3\2\2\2\21\u00ad\3\2\2\2"+
		"\23\u00af\3\2\2\2\25\u00b1\3\2\2\2\27\u00b3\3\2\2\2\31\u00b5\3\2\2\2\33"+
		"\u00b7\3\2\2\2\35\u00ba\3\2\2\2\37\u00c1\3\2\2\2!\u00c6\3\2\2\2#\u00ca"+
		"\3\2\2\2%\u00ce\3\2\2\2\'\u00d2\3\2\2\2)\u00d6\3\2\2\2+\u00dc\3\2\2\2"+
		"-\u00e5\3\2\2\2/\u00eb\3\2\2\2\61\u00f1\3\2\2\2\63\u00f4\3\2\2\2\65\u00fa"+
		"\3\2\2\2\67\u00fe\3\2\2\29\u0101\3\2\2\2;\u0109\3\2\2\2=\u010d\3\2\2\2"+
		"?\u0110\3\2\2\2A\u0115\3\2\2\2C\u0118\3\2\2\2E\u011c\3\2\2\2G\u0121\3"+
		"\2\2\2I\u0127\3\2\2\2K\u012e\3\2\2\2M\u0133\3\2\2\2O\u013c\3\2\2\2Q\u0141"+
		"\3\2\2\2S\u014a\3\2\2\2U\u014d\3\2\2\2W\u0152\3\2\2\2Y\u0157\3\2\2\2["+
		"\u015d\3\2\2\2]\u0163\3\2\2\2_\u0165\3\2\2\2a\u0167\3\2\2\2c\u0169\3\2"+
		"\2\2e\u016b\3\2\2\2g\u016d\3\2\2\2i\u016f\3\2\2\2k\u0171\3\2\2\2m\u0173"+
		"\3\2\2\2o\u0175\3\2\2\2q\u0177\3\2\2\2s\u0179\3\2\2\2u\u017b\3\2\2\2w"+
		"\u017d\3\2\2\2y\u017f\3\2\2\2{\u0181\3\2\2\2}\u0183\3\2\2\2\177\u0185"+
		"\3\2\2\2\u0081\u0187\3\2\2\2\u0083\u0189\3\2\2\2\u0085\u018b\3\2\2\2\u0087"+
		"\u018d\3\2\2\2\u0089\u018f\3\2\2\2\u008b\u0191\3\2\2\2\u008d\u0193\3\2"+
		"\2\2\u008f\u0195\3\2\2\2\u0091\u0197\3\2\2\2\u0093\u0199\3\2\2\2\u0095"+
		"\u019b\3\2\2\2\u0097\u019d\3\2\2\2\u0099\u01a4\3\2\2\2\u009b\u01b4\3\2"+
		"\2\2\u009d\u01b9\3\2\2\2\u009f\u00a0\7*\2\2\u00a0\4\3\2\2\2\u00a1\u00a2"+
		"\7+\2\2\u00a2\6\3\2\2\2\u00a3\u00a4\7.\2\2\u00a4\b\3\2\2\2\u00a5\u00a6"+
		"\7\60\2\2\u00a6\n\3\2\2\2\u00a7\u00a8\7,\2\2\u00a8\f\3\2\2\2\u00a9\u00aa"+
		"\7?\2\2\u00aa\16\3\2\2\2\u00ab\u00ac\7@\2\2\u00ac\20\3\2\2\2\u00ad\u00ae"+
		"\7>\2\2\u00ae\22\3\2\2\2\u00af\u00b0\7#\2\2\u00b0\24\3\2\2\2\u00b1\u00b2"+
		"\7-\2\2\u00b2\26\3\2\2\2\u00b3\u00b4\7/\2\2\u00b4\30\3\2\2\2\u00b5\u00b6"+
		"\7\61\2\2\u00b6\32\3\2\2\2\u00b7\u00b8\5]/\2\u00b8\u00b9\5\u0081A\2\u00b9"+
		"\34\3\2\2\2\u00ba\u00bb\5\u0081A\2\u00bb\u00bc\5e\63\2\u00bc\u00bd\5s"+
		":\2\u00bd\u00be\5e\63\2\u00be\u00bf\5a\61\2\u00bf\u00c0\5\u0083B\2\u00c0"+
		"\36\3\2\2\2\u00c1\u00c2\5g\64\2\u00c2\u00c3\5\177@\2\u00c3\u00c4\5y=\2"+
		"\u00c4\u00c5\5u;\2\u00c5 \3\2\2\2\u00c6\u00c7\5u;\2\u00c7\u00c8\5]/\2"+
		"\u00c8\u00c9\5\u008bF\2\u00c9\"\3\2\2\2\u00ca\u00cb\5\u0081A\2\u00cb\u00cc"+
		"\5\u0085C\2\u00cc\u00cd\5u;\2\u00cd$\3\2\2\2\u00ce\u00cf\5]/\2\u00cf\u00d0"+
		"\5\u0087D\2\u00d0\u00d1\5i\65\2\u00d1&\3\2\2\2\u00d2\u00d3\5u;\2\u00d3"+
		"\u00d4\5m\67\2\u00d4\u00d5\5w<\2\u00d5(\3\2\2\2\u00d6\u00d7\5a\61\2\u00d7"+
		"\u00d8\5y=\2\u00d8\u00d9\5\u0085C\2\u00d9\u00da\5w<\2\u00da\u00db\5\u0083"+
		"B\2\u00db*\3\2\2\2\u00dc\u00dd\5c\62\2\u00dd\u00de\5m\67\2\u00de\u00df"+
		"\5\u0081A\2\u00df\u00e0\5\u0083B\2\u00e0\u00e1\5m\67\2\u00e1\u00e2\5w"+
		"<\2\u00e2\u00e3\5a\61\2\u00e3\u00e4\5\u0083B\2\u00e4,\3\2\2\2\u00e5\u00e6"+
		"\5\u0089E\2\u00e6\u00e7\5k\66\2\u00e7\u00e8\5e\63\2\u00e8\u00e9\5\177"+
		"@\2\u00e9\u00ea\5e\63\2\u00ea.\3\2\2\2\u00eb\u00ec\5i\65\2\u00ec\u00ed"+
		"\5\177@\2\u00ed\u00ee\5y=\2\u00ee\u00ef\5\u0085C\2\u00ef\u00f0\5{>\2\u00f0"+
		"\60\3\2\2\2\u00f1\u00f2\5_\60\2\u00f2\u00f3\5\u008dG\2\u00f3\62\3\2\2"+
		"\2\u00f4\u00f5\5y=\2\u00f5\u00f6\5\177@\2\u00f6\u00f7\5c\62\2\u00f7\u00f8"+
		"\5e\63\2\u00f8\u00f9\5\177@\2\u00f9\64\3\2\2\2\u00fa\u00fb\5w<\2\u00fb"+
		"\u00fc\5y=\2\u00fc\u00fd\5\u0083B\2\u00fd\66\3\2\2\2\u00fe\u00ff\5m\67"+
		"\2\u00ff\u0100\5\u0081A\2\u01008\3\2\2\2\u0101\u0102\5_\60\2\u0102\u0103"+
		"\5e\63\2\u0103\u0104\5\u0083B\2\u0104\u0105\5\u0089E\2\u0105\u0106\5e"+
		"\63\2\u0106\u0107\5e\63\2\u0107\u0108\5w<\2\u0108:\3\2\2\2\u0109\u010a"+
		"\5]/\2\u010a\u010b\5w<\2\u010b\u010c\5c\62\2\u010c<\3\2\2\2\u010d\u010e"+
		"\5m\67\2\u010e\u010f\5w<\2\u010f>\3\2\2\2\u0110\u0111\5w<\2\u0111\u0112"+
		"\5\u0085C\2\u0112\u0113\5s:\2\u0113\u0114\5s:\2\u0114@\3\2\2\2\u0115\u0116"+
		"\5y=\2\u0116\u0117\5\177@\2\u0117B\3\2\2\2\u0118\u0119\5]/\2\u0119\u011a"+
		"\5\u0081A\2\u011a\u011b\5a\61\2\u011bD\3\2\2\2\u011c\u011d\5c\62\2\u011d"+
		"\u011e\5e\63\2\u011e\u011f\5\u0081A\2\u011f\u0120\5a\61\2\u0120F\3\2\2"+
		"\2\u0121\u0122\5s:\2\u0122\u0123\5m\67\2\u0123\u0124\5u;\2\u0124\u0125"+
		"\5m\67\2\u0125\u0126\5\u0083B\2\u0126H\3\2\2\2\u0127\u0128\5y=\2\u0128"+
		"\u0129\5g\64\2\u0129\u012a\5g\64\2\u012a\u012b\5\u0081A\2\u012b\u012c"+
		"\5e\63\2\u012c\u012d\5\u0083B\2\u012dJ\3\2\2\2\u012e\u012f\5s:\2\u012f"+
		"\u0130\5m\67\2\u0130\u0131\5q9\2\u0131\u0132\5e\63\2\u0132L\3\2\2\2\u0133"+
		"\u0134\5c\62\2\u0134\u0135\5e\63\2\u0135\u0136\5\u0081A\2\u0136\u0137"+
		"\5a\61\2\u0137\u0138\5\177@\2\u0138\u0139\5m\67\2\u0139\u013a\5_\60\2"+
		"\u013a\u013b\5e\63\2\u013bN\3\2\2\2\u013c\u013d\5\u0081A\2\u013d\u013e"+
		"\5k\66\2\u013e\u013f\5y=\2\u013f\u0140\5\u0089E\2\u0140P\3\2\2\2\u0141"+
		"\u0142\5\u0081A\2\u0142\u0143\5e\63\2\u0143\u0144\5\177@\2\u0144\u0145"+
		"\5\u0087D\2\u0145\u0146\5m\67\2\u0146\u0147\5a\61\2\u0147\u0148\5e\63"+
		"\2\u0148\u0149\5\u0081A\2\u0149R\3\2\2\2\u014a\u014b\5y=\2\u014b\u014c"+
		"\5w<\2\u014cT\3\2\2\2\u014d\u014e\5o8\2\u014e\u014f\5y=\2\u014f\u0150"+
		"\5m\67\2\u0150\u0151\5w<\2\u0151V\3\2\2\2\u0152\u0153\5s:\2\u0153\u0154"+
		"\5e\63\2\u0154\u0155\5g\64\2\u0155\u0156\5\u0083B\2\u0156X\3\2\2\2\u0157"+
		"\u0158\5\177@\2\u0158\u0159\5m\67\2\u0159\u015a\5i\65\2\u015a\u015b\5"+
		"k\66\2\u015b\u015c\5\u0083B\2\u015cZ\3\2\2\2\u015d\u015e\5m\67\2\u015e"+
		"\u015f\5w<\2\u015f\u0160\5w<\2\u0160\u0161\5e\63\2\u0161\u0162\5\177@"+
		"\2\u0162\\\3\2\2\2\u0163\u0164\t\2\2\2\u0164^\3\2\2\2\u0165\u0166\t\3"+
		"\2\2\u0166`\3\2\2\2\u0167\u0168\t\4\2\2\u0168b\3\2\2\2\u0169\u016a\t\5"+
		"\2\2\u016ad\3\2\2\2\u016b\u016c\t\6\2\2\u016cf\3\2\2\2\u016d\u016e\t\7"+
		"\2\2\u016eh\3\2\2\2\u016f\u0170\t\b\2\2\u0170j\3\2\2\2\u0171\u0172\t\t"+
		"\2\2\u0172l\3\2\2\2\u0173\u0174\t\n\2\2\u0174n\3\2\2\2\u0175\u0176\t\13"+
		"\2\2\u0176p\3\2\2\2\u0177\u0178\t\f\2\2\u0178r\3\2\2\2\u0179\u017a\t\r"+
		"\2\2\u017at\3\2\2\2\u017b\u017c\t\16\2\2\u017cv\3\2\2\2\u017d\u017e\t"+
		"\17\2\2\u017ex\3\2\2\2\u017f\u0180\t\20\2\2\u0180z\3\2\2\2\u0181\u0182"+
		"\t\21\2\2\u0182|\3\2\2\2\u0183\u0184\t\22\2\2\u0184~\3\2\2\2\u0185\u0186"+
		"\t\23\2\2\u0186\u0080\3\2\2\2\u0187\u0188\t\24\2\2\u0188\u0082\3\2\2\2"+
		"\u0189\u018a\t\25\2\2\u018a\u0084\3\2\2\2\u018b\u018c\t\26\2\2\u018c\u0086"+
		"\3\2\2\2\u018d\u018e\t\27\2\2\u018e\u0088\3\2\2\2\u018f\u0190\t\30\2\2"+
		"\u0190\u008a\3\2\2\2\u0191\u0192\t\31\2\2\u0192\u008c\3\2\2\2\u0193\u0194"+
		"\t\32\2\2\u0194\u008e\3\2\2\2\u0195\u0196\t\33\2\2\u0196\u0090\3\2\2\2"+
		"\u0197\u0198\t\34\2\2\u0198\u0092\3\2\2\2\u0199\u019a\t\35\2\2\u019a\u0094"+
		"\3\2\2\2\u019b\u019c\t\36\2\2\u019c\u0096\3\2\2\2\u019d\u01a1\t\36\2\2"+
		"\u019e\u01a0\t\37\2\2\u019f\u019e\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f"+
		"\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u0098\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4"+
		"\u01ae\7)\2\2\u01a5\u01a6\7^\2\2\u01a6\u01ad\7^\2\2\u01a7\u01a8\7)\2\2"+
		"\u01a8\u01ad\7)\2\2\u01a9\u01aa\7^\2\2\u01aa\u01ad\7)\2\2\u01ab\u01ad"+
		"\n \2\2\u01ac\u01a5\3\2\2\2\u01ac\u01a7\3\2\2\2\u01ac\u01a9\3\2\2\2\u01ac"+
		"\u01ab\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2"+
		"\2\2\u01af\u01b1\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1\u01b2\7)\2\2\u01b2"+
		"\u009a\3\2\2\2\u01b3\u01b5\5\u0093J\2\u01b4\u01b3\3\2\2\2\u01b5\u01b6"+
		"\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u009c\3\2\2\2\u01b8"+
		"\u01ba\t!\2\2\u01b9\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01b9\3\2"+
		"\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01be\bO\2\2\u01be"+
		"\u009e\3\2\2\2\b\2\u01a1\u01ac\u01ae\u01b6\u01bb\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}