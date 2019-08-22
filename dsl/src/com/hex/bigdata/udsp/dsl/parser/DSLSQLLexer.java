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
		T__9=10, T__10=11, AS=12, SELECT=13, FROM=14, MAX=15, SUM=16, AVG=17, 
		MIN=18, COUNT=19, DISTINCT=20, WHERE=21, GROUP=22, BY=23, ORDER=24, NOT=25, 
		IS=26, BETWEEN=27, AND=28, IN=29, NULL=30, OR=31, ASC=32, DESC=33, LIMIT=34, 
		OFFSET=35, LIKE=36, DESCRIBE=37, SHOW=38, SERVICES=39, ID=40, TEXT_STRING=41, 
		DECIMAL_LITERAL=42, WS=43;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "AS", "SELECT", "FROM", "MAX", "SUM", "AVG", "MIN", 
			"COUNT", "DISTINCT", "WHERE", "GROUP", "BY", "ORDER", "NOT", "IS", "BETWEEN", 
			"AND", "IN", "NULL", "OR", "ASC", "DESC", "LIMIT", "OFFSET", "LIKE", 
			"DESCRIBE", "SHOW", "SERVICES", "A", "B", "C", "D", "E", "F", "G", "H", 
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", 
			"W", "X", "Y", "Z", "HEX_DIGIT", "DEC_DIGIT", "LETTER", "ID", "TEXT_STRING", 
			"DECIMAL_LITERAL", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u0198\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3"+
		"$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3"+
		"\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3"+
		"/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66"+
		"\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3"+
		"A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\7F\u0179\nF\fF\16F\u017c\13F\3G\3G\3G"+
		"\3G\3G\3G\3G\3G\7G\u0186\nG\fG\16G\u0189\13G\3G\3G\3H\6H\u018e\nH\rH\16"+
		"H\u018f\3I\6I\u0193\nI\rI\16I\u0194\3I\3I\2\2J\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2y\2{\2}\2"+
		"\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b*\u008d+\u008f,\u0091"+
		"-\3\2\"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJ"+
		"jj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2"+
		"SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4"+
		"\2\\\\||\4\2\62;CH\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\3\2))\5\2\13\f\17\17"+
		"\"\"\2\u0181\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2\u008b\3\2"+
		"\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\3\u0093\3\2\2\2\5"+
		"\u0095\3\2\2\2\7\u0097\3\2\2\2\t\u0099\3\2\2\2\13\u009b\3\2\2\2\r\u009d"+
		"\3\2\2\2\17\u009f\3\2\2\2\21\u00a1\3\2\2\2\23\u00a3\3\2\2\2\25\u00a5\3"+
		"\2\2\2\27\u00a7\3\2\2\2\31\u00a9\3\2\2\2\33\u00ac\3\2\2\2\35\u00b3\3\2"+
		"\2\2\37\u00b8\3\2\2\2!\u00bc\3\2\2\2#\u00c0\3\2\2\2%\u00c4\3\2\2\2\'\u00c8"+
		"\3\2\2\2)\u00ce\3\2\2\2+\u00d7\3\2\2\2-\u00dd\3\2\2\2/\u00e3\3\2\2\2\61"+
		"\u00e6\3\2\2\2\63\u00ec\3\2\2\2\65\u00f0\3\2\2\2\67\u00f3\3\2\2\29\u00fb"+
		"\3\2\2\2;\u00ff\3\2\2\2=\u0102\3\2\2\2?\u0107\3\2\2\2A\u010a\3\2\2\2C"+
		"\u010e\3\2\2\2E\u0113\3\2\2\2G\u0119\3\2\2\2I\u0120\3\2\2\2K\u0125\3\2"+
		"\2\2M\u012e\3\2\2\2O\u0133\3\2\2\2Q\u013c\3\2\2\2S\u013e\3\2\2\2U\u0140"+
		"\3\2\2\2W\u0142\3\2\2\2Y\u0144\3\2\2\2[\u0146\3\2\2\2]\u0148\3\2\2\2_"+
		"\u014a\3\2\2\2a\u014c\3\2\2\2c\u014e\3\2\2\2e\u0150\3\2\2\2g\u0152\3\2"+
		"\2\2i\u0154\3\2\2\2k\u0156\3\2\2\2m\u0158\3\2\2\2o\u015a\3\2\2\2q\u015c"+
		"\3\2\2\2s\u015e\3\2\2\2u\u0160\3\2\2\2w\u0162\3\2\2\2y\u0164\3\2\2\2{"+
		"\u0166\3\2\2\2}\u0168\3\2\2\2\177\u016a\3\2\2\2\u0081\u016c\3\2\2\2\u0083"+
		"\u016e\3\2\2\2\u0085\u0170\3\2\2\2\u0087\u0172\3\2\2\2\u0089\u0174\3\2"+
		"\2\2\u008b\u0176\3\2\2\2\u008d\u017d\3\2\2\2\u008f\u018d\3\2\2\2\u0091"+
		"\u0192\3\2\2\2\u0093\u0094\7,\2\2\u0094\4\3\2\2\2\u0095\u0096\7.\2\2\u0096"+
		"\6\3\2\2\2\u0097\u0098\7*\2\2\u0098\b\3\2\2\2\u0099\u009a\7+\2\2\u009a"+
		"\n\3\2\2\2\u009b\u009c\7?\2\2\u009c\f\3\2\2\2\u009d\u009e\7@\2\2\u009e"+
		"\16\3\2\2\2\u009f\u00a0\7>\2\2\u00a0\20\3\2\2\2\u00a1\u00a2\7#\2\2\u00a2"+
		"\22\3\2\2\2\u00a3\u00a4\7-\2\2\u00a4\24\3\2\2\2\u00a5\u00a6\7/\2\2\u00a6"+
		"\26\3\2\2\2\u00a7\u00a8\7\61\2\2\u00a8\30\3\2\2\2\u00a9\u00aa\5Q)\2\u00aa"+
		"\u00ab\5u;\2\u00ab\32\3\2\2\2\u00ac\u00ad\5u;\2\u00ad\u00ae\5Y-\2\u00ae"+
		"\u00af\5g\64\2\u00af\u00b0\5Y-\2\u00b0\u00b1\5U+\2\u00b1\u00b2\5w<\2\u00b2"+
		"\34\3\2\2\2\u00b3\u00b4\5[.\2\u00b4\u00b5\5s:\2\u00b5\u00b6\5m\67\2\u00b6"+
		"\u00b7\5i\65\2\u00b7\36\3\2\2\2\u00b8\u00b9\5i\65\2\u00b9\u00ba\5Q)\2"+
		"\u00ba\u00bb\5\177@\2\u00bb \3\2\2\2\u00bc\u00bd\5u;\2\u00bd\u00be\5y"+
		"=\2\u00be\u00bf\5i\65\2\u00bf\"\3\2\2\2\u00c0\u00c1\5Q)\2\u00c1\u00c2"+
		"\5{>\2\u00c2\u00c3\5]/\2\u00c3$\3\2\2\2\u00c4\u00c5\5i\65\2\u00c5\u00c6"+
		"\5a\61\2\u00c6\u00c7\5k\66\2\u00c7&\3\2\2\2\u00c8\u00c9\5U+\2\u00c9\u00ca"+
		"\5m\67\2\u00ca\u00cb\5y=\2\u00cb\u00cc\5k\66\2\u00cc\u00cd\5w<\2\u00cd"+
		"(\3\2\2\2\u00ce\u00cf\5W,\2\u00cf\u00d0\5a\61\2\u00d0\u00d1\5u;\2\u00d1"+
		"\u00d2\5w<\2\u00d2\u00d3\5a\61\2\u00d3\u00d4\5k\66\2\u00d4\u00d5\5U+\2"+
		"\u00d5\u00d6\5w<\2\u00d6*\3\2\2\2\u00d7\u00d8\5}?\2\u00d8\u00d9\5_\60"+
		"\2\u00d9\u00da\5Y-\2\u00da\u00db\5s:\2\u00db\u00dc\5Y-\2\u00dc,\3\2\2"+
		"\2\u00dd\u00de\5]/\2\u00de\u00df\5s:\2\u00df\u00e0\5m\67\2\u00e0\u00e1"+
		"\5y=\2\u00e1\u00e2\5o8\2\u00e2.\3\2\2\2\u00e3\u00e4\5S*\2\u00e4\u00e5"+
		"\5\u0081A\2\u00e5\60\3\2\2\2\u00e6\u00e7\5m\67\2\u00e7\u00e8\5s:\2\u00e8"+
		"\u00e9\5W,\2\u00e9\u00ea\5Y-\2\u00ea\u00eb\5s:\2\u00eb\62\3\2\2\2\u00ec"+
		"\u00ed\5k\66\2\u00ed\u00ee\5m\67\2\u00ee\u00ef\5w<\2\u00ef\64\3\2\2\2"+
		"\u00f0\u00f1\5a\61\2\u00f1\u00f2\5u;\2\u00f2\66\3\2\2\2\u00f3\u00f4\5"+
		"S*\2\u00f4\u00f5\5Y-\2\u00f5\u00f6\5w<\2\u00f6\u00f7\5}?\2\u00f7\u00f8"+
		"\5Y-\2\u00f8\u00f9\5Y-\2\u00f9\u00fa\5k\66\2\u00fa8\3\2\2\2\u00fb\u00fc"+
		"\5Q)\2\u00fc\u00fd\5k\66\2\u00fd\u00fe\5W,\2\u00fe:\3\2\2\2\u00ff\u0100"+
		"\5a\61\2\u0100\u0101\5k\66\2\u0101<\3\2\2\2\u0102\u0103\5k\66\2\u0103"+
		"\u0104\5y=\2\u0104\u0105\5g\64\2\u0105\u0106\5g\64\2\u0106>\3\2\2\2\u0107"+
		"\u0108\5m\67\2\u0108\u0109\5s:\2\u0109@\3\2\2\2\u010a\u010b\5Q)\2\u010b"+
		"\u010c\5u;\2\u010c\u010d\5U+\2\u010dB\3\2\2\2\u010e\u010f\5W,\2\u010f"+
		"\u0110\5Y-\2\u0110\u0111\5u;\2\u0111\u0112\5U+\2\u0112D\3\2\2\2\u0113"+
		"\u0114\5g\64\2\u0114\u0115\5a\61\2\u0115\u0116\5i\65\2\u0116\u0117\5a"+
		"\61\2\u0117\u0118\5w<\2\u0118F\3\2\2\2\u0119\u011a\5m\67\2\u011a\u011b"+
		"\5[.\2\u011b\u011c\5[.\2\u011c\u011d\5u;\2\u011d\u011e\5Y-\2\u011e\u011f"+
		"\5w<\2\u011fH\3\2\2\2\u0120\u0121\5g\64\2\u0121\u0122\5a\61\2\u0122\u0123"+
		"\5e\63\2\u0123\u0124\5Y-\2\u0124J\3\2\2\2\u0125\u0126\5W,\2\u0126\u0127"+
		"\5Y-\2\u0127\u0128\5u;\2\u0128\u0129\5U+\2\u0129\u012a\5s:\2\u012a\u012b"+
		"\5a\61\2\u012b\u012c\5S*\2\u012c\u012d\5Y-\2\u012dL\3\2\2\2\u012e\u012f"+
		"\5u;\2\u012f\u0130\5_\60\2\u0130\u0131\5m\67\2\u0131\u0132\5}?\2\u0132"+
		"N\3\2\2\2\u0133\u0134\5u;\2\u0134\u0135\5Y-\2\u0135\u0136\5s:\2\u0136"+
		"\u0137\5{>\2\u0137\u0138\5a\61\2\u0138\u0139\5U+\2\u0139\u013a\5Y-\2\u013a"+
		"\u013b\5u;\2\u013bP\3\2\2\2\u013c\u013d\t\2\2\2\u013dR\3\2\2\2\u013e\u013f"+
		"\t\3\2\2\u013fT\3\2\2\2\u0140\u0141\t\4\2\2\u0141V\3\2\2\2\u0142\u0143"+
		"\t\5\2\2\u0143X\3\2\2\2\u0144\u0145\t\6\2\2\u0145Z\3\2\2\2\u0146\u0147"+
		"\t\7\2\2\u0147\\\3\2\2\2\u0148\u0149\t\b\2\2\u0149^\3\2\2\2\u014a\u014b"+
		"\t\t\2\2\u014b`\3\2\2\2\u014c\u014d\t\n\2\2\u014db\3\2\2\2\u014e\u014f"+
		"\t\13\2\2\u014fd\3\2\2\2\u0150\u0151\t\f\2\2\u0151f\3\2\2\2\u0152\u0153"+
		"\t\r\2\2\u0153h\3\2\2\2\u0154\u0155\t\16\2\2\u0155j\3\2\2\2\u0156\u0157"+
		"\t\17\2\2\u0157l\3\2\2\2\u0158\u0159\t\20\2\2\u0159n\3\2\2\2\u015a\u015b"+
		"\t\21\2\2\u015bp\3\2\2\2\u015c\u015d\t\22\2\2\u015dr\3\2\2\2\u015e\u015f"+
		"\t\23\2\2\u015ft\3\2\2\2\u0160\u0161\t\24\2\2\u0161v\3\2\2\2\u0162\u0163"+
		"\t\25\2\2\u0163x\3\2\2\2\u0164\u0165\t\26\2\2\u0165z\3\2\2\2\u0166\u0167"+
		"\t\27\2\2\u0167|\3\2\2\2\u0168\u0169\t\30\2\2\u0169~\3\2\2\2\u016a\u016b"+
		"\t\31\2\2\u016b\u0080\3\2\2\2\u016c\u016d\t\32\2\2\u016d\u0082\3\2\2\2"+
		"\u016e\u016f\t\33\2\2\u016f\u0084\3\2\2\2\u0170\u0171\t\34\2\2\u0171\u0086"+
		"\3\2\2\2\u0172\u0173\t\35\2\2\u0173\u0088\3\2\2\2\u0174\u0175\t\36\2\2"+
		"\u0175\u008a\3\2\2\2\u0176\u017a\t\36\2\2\u0177\u0179\t\37\2\2\u0178\u0177"+
		"\3\2\2\2\u0179\u017c\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u008c\3\2\2\2\u017c\u017a\3\2\2\2\u017d\u0187\7)\2\2\u017e\u017f\7^\2"+
		"\2\u017f\u0186\7^\2\2\u0180\u0181\7)\2\2\u0181\u0186\7)\2\2\u0182\u0183"+
		"\7^\2\2\u0183\u0186\7)\2\2\u0184\u0186\n \2\2\u0185\u017e\3\2\2\2\u0185"+
		"\u0180\3\2\2\2\u0185\u0182\3\2\2\2\u0185\u0184\3\2\2\2\u0186\u0189\3\2"+
		"\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018a\3\2\2\2\u0189"+
		"\u0187\3\2\2\2\u018a\u018b\7)\2\2\u018b\u008e\3\2\2\2\u018c\u018e\5\u0087"+
		"D\2\u018d\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u018d\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u0090\3\2\2\2\u0191\u0193\t!\2\2\u0192\u0191\3\2"+
		"\2\2\u0193\u0194\3\2\2\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195"+
		"\u0196\3\2\2\2\u0196\u0197\bI\2\2\u0197\u0092\3\2\2\2\b\2\u017a\u0185"+
		"\u0187\u018f\u0194\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}