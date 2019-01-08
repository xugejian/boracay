// Generated from B:/workspaces/IntellijIdeaGithubWorkspaces/github/boracay/dsl/resource\APPDSL.g4 by ANTLR 4.7.2

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
public class APPDSLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, AS=6, COUNT=7, SELECT=8, FROM=9, 
		WHERE=10, AND=11, LIMIT=12, OFFSET=13, DESCRIBE=14, SHOW=15, SERVICES=16, 
		ID=17, TEXT_STRING=18, DECIMAL_LITERAL=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "AS", "COUNT", "SELECT", "FROM", 
			"WHERE", "AND", "LIMIT", "OFFSET", "DESCRIBE", "SHOW", "SERVICES", "A", 
			"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", 
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "HEX_DIGIT", "DEC_DIGIT", 
			"LETTER", "ID", "TEXT_STRING", "DECIMAL_LITERAL", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'*'", "','", "'='", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "AS", "COUNT", "SELECT", "FROM", 
			"WHERE", "AND", "LIMIT", "OFFSET", "DESCRIBE", "SHOW", "SERVICES", "ID", 
			"TEXT_STRING", "DECIMAL_LITERAL", "WS"
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


	public APPDSLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "APPDSL.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u010e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\""+
		"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3"+
		".\3.\3/\3/\7/\u00ef\n/\f/\16/\u00f2\13/\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\7\60\u00fc\n\60\f\60\16\60\u00ff\13\60\3\60\3\60\3\61\6\61"+
		"\u0104\n\61\r\61\16\61\u0105\3\62\6\62\u0109\n\62\r\62\16\62\u010a\3\62"+
		"\3\62\2\2\63\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\2%\2\'\2)\2+\2-\2/\2\61\2\63\2\65\2\67\29\2;\2"+
		"=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\23_\24a\25c\26\3\2\""+
		"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2K"+
		"Kkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4"+
		"\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\"+
		"||\4\2\62;CH\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\3\2))\5\2\13\f\17\17\"\""+
		"\2\u00f7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\3e\3\2\2\2\5g\3\2\2\2\7"+
		"i\3\2\2\2\tk\3\2\2\2\13m\3\2\2\2\ro\3\2\2\2\17r\3\2\2\2\21x\3\2\2\2\23"+
		"\177\3\2\2\2\25\u0084\3\2\2\2\27\u008a\3\2\2\2\31\u008e\3\2\2\2\33\u0094"+
		"\3\2\2\2\35\u009b\3\2\2\2\37\u00a4\3\2\2\2!\u00a9\3\2\2\2#\u00b2\3\2\2"+
		"\2%\u00b4\3\2\2\2\'\u00b6\3\2\2\2)\u00b8\3\2\2\2+\u00ba\3\2\2\2-\u00bc"+
		"\3\2\2\2/\u00be\3\2\2\2\61\u00c0\3\2\2\2\63\u00c2\3\2\2\2\65\u00c4\3\2"+
		"\2\2\67\u00c6\3\2\2\29\u00c8\3\2\2\2;\u00ca\3\2\2\2=\u00cc\3\2\2\2?\u00ce"+
		"\3\2\2\2A\u00d0\3\2\2\2C\u00d2\3\2\2\2E\u00d4\3\2\2\2G\u00d6\3\2\2\2I"+
		"\u00d8\3\2\2\2K\u00da\3\2\2\2M\u00dc\3\2\2\2O\u00de\3\2\2\2Q\u00e0\3\2"+
		"\2\2S\u00e2\3\2\2\2U\u00e4\3\2\2\2W\u00e6\3\2\2\2Y\u00e8\3\2\2\2[\u00ea"+
		"\3\2\2\2]\u00ec\3\2\2\2_\u00f3\3\2\2\2a\u0103\3\2\2\2c\u0108\3\2\2\2e"+
		"f\7,\2\2f\4\3\2\2\2gh\7.\2\2h\6\3\2\2\2ij\7?\2\2j\b\3\2\2\2kl\7*\2\2l"+
		"\n\3\2\2\2mn\7+\2\2n\f\3\2\2\2op\5#\22\2pq\5G$\2q\16\3\2\2\2rs\5\'\24"+
		"\2st\5? \2tu\5K&\2uv\5=\37\2vw\5I%\2w\20\3\2\2\2xy\5G$\2yz\5+\26\2z{\5"+
		"9\35\2{|\5+\26\2|}\5\'\24\2}~\5I%\2~\22\3\2\2\2\177\u0080\5-\27\2\u0080"+
		"\u0081\5E#\2\u0081\u0082\5? \2\u0082\u0083\5;\36\2\u0083\24\3\2\2\2\u0084"+
		"\u0085\5O(\2\u0085\u0086\5\61\31\2\u0086\u0087\5+\26\2\u0087\u0088\5E"+
		"#\2\u0088\u0089\5+\26\2\u0089\26\3\2\2\2\u008a\u008b\5#\22\2\u008b\u008c"+
		"\5=\37\2\u008c\u008d\5)\25\2\u008d\30\3\2\2\2\u008e\u008f\59\35\2\u008f"+
		"\u0090\5\63\32\2\u0090\u0091\5;\36\2\u0091\u0092\5\63\32\2\u0092\u0093"+
		"\5I%\2\u0093\32\3\2\2\2\u0094\u0095\5? \2\u0095\u0096\5-\27\2\u0096\u0097"+
		"\5-\27\2\u0097\u0098\5G$\2\u0098\u0099\5+\26\2\u0099\u009a\5I%\2\u009a"+
		"\34\3\2\2\2\u009b\u009c\5)\25\2\u009c\u009d\5+\26\2\u009d\u009e\5G$\2"+
		"\u009e\u009f\5\'\24\2\u009f\u00a0\5E#\2\u00a0\u00a1\5\63\32\2\u00a1\u00a2"+
		"\5%\23\2\u00a2\u00a3\5+\26\2\u00a3\36\3\2\2\2\u00a4\u00a5\5G$\2\u00a5"+
		"\u00a6\5\61\31\2\u00a6\u00a7\5? \2\u00a7\u00a8\5O(\2\u00a8 \3\2\2\2\u00a9"+
		"\u00aa\5G$\2\u00aa\u00ab\5+\26\2\u00ab\u00ac\5E#\2\u00ac\u00ad\5M\'\2"+
		"\u00ad\u00ae\5\63\32\2\u00ae\u00af\5\'\24\2\u00af\u00b0\5+\26\2\u00b0"+
		"\u00b1\5G$\2\u00b1\"\3\2\2\2\u00b2\u00b3\t\2\2\2\u00b3$\3\2\2\2\u00b4"+
		"\u00b5\t\3\2\2\u00b5&\3\2\2\2\u00b6\u00b7\t\4\2\2\u00b7(\3\2\2\2\u00b8"+
		"\u00b9\t\5\2\2\u00b9*\3\2\2\2\u00ba\u00bb\t\6\2\2\u00bb,\3\2\2\2\u00bc"+
		"\u00bd\t\7\2\2\u00bd.\3\2\2\2\u00be\u00bf\t\b\2\2\u00bf\60\3\2\2\2\u00c0"+
		"\u00c1\t\t\2\2\u00c1\62\3\2\2\2\u00c2\u00c3\t\n\2\2\u00c3\64\3\2\2\2\u00c4"+
		"\u00c5\t\13\2\2\u00c5\66\3\2\2\2\u00c6\u00c7\t\f\2\2\u00c78\3\2\2\2\u00c8"+
		"\u00c9\t\r\2\2\u00c9:\3\2\2\2\u00ca\u00cb\t\16\2\2\u00cb<\3\2\2\2\u00cc"+
		"\u00cd\t\17\2\2\u00cd>\3\2\2\2\u00ce\u00cf\t\20\2\2\u00cf@\3\2\2\2\u00d0"+
		"\u00d1\t\21\2\2\u00d1B\3\2\2\2\u00d2\u00d3\t\22\2\2\u00d3D\3\2\2\2\u00d4"+
		"\u00d5\t\23\2\2\u00d5F\3\2\2\2\u00d6\u00d7\t\24\2\2\u00d7H\3\2\2\2\u00d8"+
		"\u00d9\t\25\2\2\u00d9J\3\2\2\2\u00da\u00db\t\26\2\2\u00dbL\3\2\2\2\u00dc"+
		"\u00dd\t\27\2\2\u00ddN\3\2\2\2\u00de\u00df\t\30\2\2\u00dfP\3\2\2\2\u00e0"+
		"\u00e1\t\31\2\2\u00e1R\3\2\2\2\u00e2\u00e3\t\32\2\2\u00e3T\3\2\2\2\u00e4"+
		"\u00e5\t\33\2\2\u00e5V\3\2\2\2\u00e6\u00e7\t\34\2\2\u00e7X\3\2\2\2\u00e8"+
		"\u00e9\t\35\2\2\u00e9Z\3\2\2\2\u00ea\u00eb\t\36\2\2\u00eb\\\3\2\2\2\u00ec"+
		"\u00f0\t\36\2\2\u00ed\u00ef\t\37\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3"+
		"\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1^\3\2\2\2\u00f2\u00f0"+
		"\3\2\2\2\u00f3\u00fd\7)\2\2\u00f4\u00f5\7^\2\2\u00f5\u00fc\7^\2\2\u00f6"+
		"\u00f7\7)\2\2\u00f7\u00fc\7)\2\2\u00f8\u00f9\7^\2\2\u00f9\u00fc\7)\2\2"+
		"\u00fa\u00fc\n \2\2\u00fb\u00f4\3\2\2\2\u00fb\u00f6\3\2\2\2\u00fb\u00f8"+
		"\3\2\2\2\u00fb\u00fa\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd"+
		"\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\7)"+
		"\2\2\u0101`\3\2\2\2\u0102\u0104\5Y-\2\u0103\u0102\3\2\2\2\u0104\u0105"+
		"\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106b\3\2\2\2\u0107"+
		"\u0109\t!\2\2\u0108\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u0108\3\2"+
		"\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\b\62\2\2\u010d"+
		"d\3\2\2\2\b\2\u00f0\u00fb\u00fd\u0105\u010a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}