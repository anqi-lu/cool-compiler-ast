// Generated from /Users/anqilu/WPI/Compilers/ew2/COOL Compiler/grammars/Cool.g4 by ANTLR 4.7.1

package cool.lexparse;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, CLASS=2, ELSE=3, FALSE=4, FI=5, IF=6, IN=7, INHERITS=8, ISVOID=9, 
		LET=10, LOOP=11, POOL=12, THEN=13, WHILE=14, CASE=15, ESAC=16, NEW=17, 
		OF=18, TRUE=19, ID=20, INTEGER=21, TYPE=22, WS=23, NULL=24, COMMENT1=25, 
		COMMENT2=26, LINE_COM=27, OPEN_COM=28, CLOSE_COM=29, STRING=30, INVAL_ESC=31, 
		VALID_ESC=32, ASSIGN=33, CASE_ARROW=34, PLUS=35, MINUS=36, MULTIPLY=37, 
		DIVIDE=38, NOT=39, EQUAL=40, LESS_THAN=41, LESS_OR_EQUAL=42, NOT_EQUAL=43, 
		GREATER_OR_EQUAL=44, GREATER_THAN=45, COLN=46, LBRAC=47, RBRAC=48, LPARN=49, 
		RPARN=50, COMMA=51, SEMIC=52, SIGN=53;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "CLASS", "ELSE", "FALSE", "FI", "IF", "IN", "INHERITS", "ISVOID", 
		"LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "TRUE", 
		"ID", "INTEGER", "TYPE", "WS", "NULL", "COMMENT1", "COMMENT2", "LINE_COM", 
		"OPEN_COM", "CLOSE_COM", "STRING", "INVAL_ESC", "VALID_ESC", "ASSIGN", 
		"CASE_ARROW", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "NOT", "EQUAL", "LESS_THAN", 
		"LESS_OR_EQUAL", "NOT_EQUAL", "GREATER_OR_EQUAL", "GREATER_THAN", "COLN", 
		"LBRAC", "RBRAC", "LPARN", "RPARN", "COMMA", "SEMIC", "SIGN"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.'", "'class'", "'else'", "'false'", "'fi'", "'if'", "'in'", "'inherits'", 
		"'isvoid'", "'let'", "'loop'", "'pool'", "'then'", "'while'", "'case'", 
		"'esac'", "'new'", "'of'", "'true'", null, null, null, null, null, null, 
		null, "'#'", "'(*'", "'*)'", null, null, null, "'<-'", "'=>'", "'+'", 
		"'-'", "'*'", "'/'", "'~'", "'='", "'<'", "'<='", "'~='", "'>='", "'>'", 
		"':'", "'{'", "'}'", "'('", "')'", "','", "';'", "'@'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "CLASS", "ELSE", "FALSE", "FI", "IF", "IN", "INHERITS", "ISVOID", 
		"LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "TRUE", 
		"ID", "INTEGER", "TYPE", "WS", "NULL", "COMMENT1", "COMMENT2", "LINE_COM", 
		"OPEN_COM", "CLOSE_COM", "STRING", "INVAL_ESC", "VALID_ESC", "ASSIGN", 
		"CASE_ARROW", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "NOT", "EQUAL", "LESS_THAN", 
		"LESS_OR_EQUAL", "NOT_EQUAL", "GREATER_OR_EQUAL", "GREATER_THAN", "COLN", 
		"LBRAC", "RBRAC", "LPARN", "RPARN", "COMMA", "SEMIC", "SIGN"
	};
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


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Cool.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\67\u0149\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\7\25\u00cb\n\25\f\25\16\25\u00ce\13\25\3\26\6\26\u00d1"+
		"\n\26\r\26\16\26\u00d2\3\27\3\27\7\27\u00d7\n\27\f\27\16\27\u00da\13\27"+
		"\3\30\6\30\u00dd\n\30\r\30\16\30\u00de\3\30\3\30\3\31\3\31\3\32\3\32\7"+
		"\32\u00e7\n\32\f\32\16\32\u00ea\13\32\3\32\5\32\u00ed\n\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\3\33\7\33\u00f6\n\33\f\33\16\33\u00f9\13\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3"+
		"\37\7\37\u010b\n\37\f\37\16\37\u010e\13\37\3\37\3\37\3 \3 \5 \u0114\n"+
		" \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)"+
		"\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62"+
		"\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\66\3\66\4\u00e8\u00f7"+
		"\2\67\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67"+
		"\3\2\13\3\2c|\6\2\62;C\\aac|\3\2\62;\3\2C\\\5\2\n\f\16\17\"\"\3\2\2\2"+
		"\3\3\f\f\3\2\f\f\13\2CCEEGGKKNNPPUUXXaa\2\u0152\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5o\3\2\2\2\7u\3\2\2"+
		"\2\tz\3\2\2\2\13\u0080\3\2\2\2\r\u0083\3\2\2\2\17\u0086\3\2\2\2\21\u0089"+
		"\3\2\2\2\23\u0092\3\2\2\2\25\u0099\3\2\2\2\27\u009d\3\2\2\2\31\u00a2\3"+
		"\2\2\2\33\u00a7\3\2\2\2\35\u00ac\3\2\2\2\37\u00b2\3\2\2\2!\u00b7\3\2\2"+
		"\2#\u00bc\3\2\2\2%\u00c0\3\2\2\2\'\u00c3\3\2\2\2)\u00c8\3\2\2\2+\u00d0"+
		"\3\2\2\2-\u00d4\3\2\2\2/\u00dc\3\2\2\2\61\u00e2\3\2\2\2\63\u00e4\3\2\2"+
		"\2\65\u00f0\3\2\2\2\67\u00ff\3\2\2\29\u0101\3\2\2\2;\u0104\3\2\2\2=\u0107"+
		"\3\2\2\2?\u0113\3\2\2\2A\u0115\3\2\2\2C\u0118\3\2\2\2E\u011b\3\2\2\2G"+
		"\u011e\3\2\2\2I\u0120\3\2\2\2K\u0122\3\2\2\2M\u0124\3\2\2\2O\u0126\3\2"+
		"\2\2Q\u0128\3\2\2\2S\u012a\3\2\2\2U\u012c\3\2\2\2W\u012f\3\2\2\2Y\u0132"+
		"\3\2\2\2[\u0135\3\2\2\2]\u0137\3\2\2\2_\u0139\3\2\2\2a\u013b\3\2\2\2c"+
		"\u013d\3\2\2\2e\u013f\3\2\2\2g\u0141\3\2\2\2i\u0143\3\2\2\2k\u0145\3\2"+
		"\2\2mn\7\60\2\2n\4\3\2\2\2op\7e\2\2pq\7n\2\2qr\7c\2\2rs\7u\2\2st\7u\2"+
		"\2t\6\3\2\2\2uv\7g\2\2vw\7n\2\2wx\7u\2\2xy\7g\2\2y\b\3\2\2\2z{\7h\2\2"+
		"{|\7c\2\2|}\7n\2\2}~\7u\2\2~\177\7g\2\2\177\n\3\2\2\2\u0080\u0081\7h\2"+
		"\2\u0081\u0082\7k\2\2\u0082\f\3\2\2\2\u0083\u0084\7k\2\2\u0084\u0085\7"+
		"h\2\2\u0085\16\3\2\2\2\u0086\u0087\7k\2\2\u0087\u0088\7p\2\2\u0088\20"+
		"\3\2\2\2\u0089\u008a\7k\2\2\u008a\u008b\7p\2\2\u008b\u008c\7j\2\2\u008c"+
		"\u008d\7g\2\2\u008d\u008e\7t\2\2\u008e\u008f\7k\2\2\u008f\u0090\7v\2\2"+
		"\u0090\u0091\7u\2\2\u0091\22\3\2\2\2\u0092\u0093\7k\2\2\u0093\u0094\7"+
		"u\2\2\u0094\u0095\7x\2\2\u0095\u0096\7q\2\2\u0096\u0097\7k\2\2\u0097\u0098"+
		"\7f\2\2\u0098\24\3\2\2\2\u0099\u009a\7n\2\2\u009a\u009b\7g\2\2\u009b\u009c"+
		"\7v\2\2\u009c\26\3\2\2\2\u009d\u009e\7n\2\2\u009e\u009f\7q\2\2\u009f\u00a0"+
		"\7q\2\2\u00a0\u00a1\7r\2\2\u00a1\30\3\2\2\2\u00a2\u00a3\7r\2\2\u00a3\u00a4"+
		"\7q\2\2\u00a4\u00a5\7q\2\2\u00a5\u00a6\7n\2\2\u00a6\32\3\2\2\2\u00a7\u00a8"+
		"\7v\2\2\u00a8\u00a9\7j\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7p\2\2\u00ab"+
		"\34\3\2\2\2\u00ac\u00ad\7y\2\2\u00ad\u00ae\7j\2\2\u00ae\u00af\7k\2\2\u00af"+
		"\u00b0\7n\2\2\u00b0\u00b1\7g\2\2\u00b1\36\3\2\2\2\u00b2\u00b3\7e\2\2\u00b3"+
		"\u00b4\7c\2\2\u00b4\u00b5\7u\2\2\u00b5\u00b6\7g\2\2\u00b6 \3\2\2\2\u00b7"+
		"\u00b8\7g\2\2\u00b8\u00b9\7u\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb\7e\2\2"+
		"\u00bb\"\3\2\2\2\u00bc\u00bd\7p\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7y"+
		"\2\2\u00bf$\3\2\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7h\2\2\u00c2&\3\2\2"+
		"\2\u00c3\u00c4\7v\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7w\2\2\u00c6\u00c7"+
		"\7g\2\2\u00c7(\3\2\2\2\u00c8\u00cc\t\2\2\2\u00c9\u00cb\t\3\2\2\u00ca\u00c9"+
		"\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"*\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d1\t\4\2\2\u00d0\u00cf\3\2\2\2"+
		"\u00d1\u00d2\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3,\3"+
		"\2\2\2\u00d4\u00d8\t\5\2\2\u00d5\u00d7\t\3\2\2\u00d6\u00d5\3\2\2\2\u00d7"+
		"\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9.\3\2\2\2"+
		"\u00da\u00d8\3\2\2\2\u00db\u00dd\t\6\2\2\u00dc\u00db\3\2\2\2\u00dd\u00de"+
		"\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00e1\b\30\2\2\u00e1\60\3\2\2\2\u00e2\u00e3\t\7\2\2\u00e3\62\3\2\2\2"+
		"\u00e4\u00e8\7%\2\2\u00e5\u00e7\13\2\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ed\t\b\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2"+
		"\2\2\u00ee\u00ef\b\32\2\2\u00ef\64\3\2\2\2\u00f0\u00f1\7*\2\2\u00f1\u00f2"+
		"\7,\2\2\u00f2\u00f7\3\2\2\2\u00f3\u00f6\5\65\33\2\u00f4\u00f6\13\2\2\2"+
		"\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f8"+
		"\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa"+
		"\u00fb\7,\2\2\u00fb\u00fc\7+\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\b\33"+
		"\2\2\u00fe\66\3\2\2\2\u00ff\u0100\7%\2\2\u01008\3\2\2\2\u0101\u0102\7"+
		"*\2\2\u0102\u0103\7,\2\2\u0103:\3\2\2\2\u0104\u0105\7,\2\2\u0105\u0106"+
		"\7+\2\2\u0106<\3\2\2\2\u0107\u010c\7$\2\2\u0108\u010b\n\t\2\2\u0109\u010b"+
		"\5A!\2\u010a\u0108\3\2\2\2\u010a\u0109\3\2\2\2\u010b\u010e\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u010c\3\2"+
		"\2\2\u010f\u0110\7$\2\2\u0110>\3\2\2\2\u0111\u0114\5/\30\2\u0112\u0114"+
		"\5\61\31\2\u0113\u0111\3\2\2\2\u0113\u0112\3\2\2\2\u0114@\3\2\2\2\u0115"+
		"\u0116\7^\2\2\u0116\u0117\n\n\2\2\u0117B\3\2\2\2\u0118\u0119\7>\2\2\u0119"+
		"\u011a\7/\2\2\u011aD\3\2\2\2\u011b\u011c\7?\2\2\u011c\u011d\7@\2\2\u011d"+
		"F\3\2\2\2\u011e\u011f\7-\2\2\u011fH\3\2\2\2\u0120\u0121\7/\2\2\u0121J"+
		"\3\2\2\2\u0122\u0123\7,\2\2\u0123L\3\2\2\2\u0124\u0125\7\61\2\2\u0125"+
		"N\3\2\2\2\u0126\u0127\7\u0080\2\2\u0127P\3\2\2\2\u0128\u0129\7?\2\2\u0129"+
		"R\3\2\2\2\u012a\u012b\7>\2\2\u012bT\3\2\2\2\u012c\u012d\7>\2\2\u012d\u012e"+
		"\7?\2\2\u012eV\3\2\2\2\u012f\u0130\7\u0080\2\2\u0130\u0131\7?\2\2\u0131"+
		"X\3\2\2\2\u0132\u0133\7@\2\2\u0133\u0134\7?\2\2\u0134Z\3\2\2\2\u0135\u0136"+
		"\7@\2\2\u0136\\\3\2\2\2\u0137\u0138\7<\2\2\u0138^\3\2\2\2\u0139\u013a"+
		"\7}\2\2\u013a`\3\2\2\2\u013b\u013c\7\177\2\2\u013cb\3\2\2\2\u013d\u013e"+
		"\7*\2\2\u013ed\3\2\2\2\u013f\u0140\7+\2\2\u0140f\3\2\2\2\u0141\u0142\7"+
		".\2\2\u0142h\3\2\2\2\u0143\u0144\7=\2\2\u0144j\3\2\2\2\u0145\u0146\7B"+
		"\2\2\u0146\u0147\3\2\2\2\u0147\u0148\b\66\2\2\u0148l\3\2\2\2\16\2\u00cc"+
		"\u00d2\u00d8\u00de\u00e8\u00ec\u00f5\u00f7\u010a\u010c\u0113\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}