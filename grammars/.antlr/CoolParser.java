// Generated from /Users/anqilu/Documents/WPI/Compilers/eclipse-workspace/cool-compiler/grammars/Cool.g4 by ANTLR 4.7.1

package cool.lexparse;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolParser extends Parser {
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
	public static final int
		RULE_coolText = 0, RULE_classDef = 1, RULE_feature = 2, RULE_method = 3, 
		RULE_variable = 4, RULE_formal = 5, RULE_expr = 6, RULE_multExpr = 7, 
		RULE_plusExpr = 8, RULE_compExpr = 9, RULE_term = 10;
	public static final String[] ruleNames = {
		"coolText", "classDef", "feature", "method", "variable", "formal", "expr", 
		"multExpr", "plusExpr", "compExpr", "term"
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

	@Override
	public String getGrammarFileName() { return "Cool.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CoolTextContext extends ParserRuleContext {
		public ClassDefContext classDef;
		public List<ClassDefContext> classes = new ArrayList<ClassDefContext>();
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public CoolTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coolText; }
	}

	public final CoolTextContext coolText() throws RecognitionException {
		CoolTextContext _localctx = new CoolTextContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_coolText);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(22);
				((CoolTextContext)_localctx).classDef = classDef();
				((CoolTextContext)_localctx).classes.add(((CoolTextContext)_localctx).classDef);
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			setState(27);
			match(EOF);
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

	public static class ClassDefContext extends ParserRuleContext {
		public Token type;
		public Token inherits;
		public FeatureContext features;
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public List<TerminalNode> TYPE() { return getTokens(CoolParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CoolParser.TYPE, i);
		}
		public FeatureContext feature() {
			return getRuleContext(FeatureContext.class,0);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(29);
			match(CLASS);
			setState(30);
			((ClassDefContext)_localctx).type = match(TYPE);
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(31);
				match(INHERITS);
				setState(32);
				((ClassDefContext)_localctx).inherits = match(TYPE);
				}
			}

			setState(35);
			match(LBRAC);
			setState(36);
			((ClassDefContext)_localctx).features = feature();
			setState(37);
			match(RBRAC);
			setState(38);
			match(SEMIC);
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

	public static class FeatureContext extends ParserRuleContext {
		public MethodContext method;
		public List<MethodContext> methods = new ArrayList<MethodContext>();
		public VariableContext variable;
		public List<VariableContext> variables = new ArrayList<VariableContext>();
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_feature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				setState(46);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					{
					setState(40);
					((FeatureContext)_localctx).method = method();
					((FeatureContext)_localctx).methods.add(((FeatureContext)_localctx).method);
					setState(41);
					match(SEMIC);
					}
					}
					break;
				case 2:
					{
					{
					setState(43);
					((FeatureContext)_localctx).variable = variable();
					((FeatureContext)_localctx).variables.add(((FeatureContext)_localctx).variable);
					setState(44);
					match(SEMIC);
					}
					}
					break;
				}
				}
				setState(50);
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

	public static class MethodContext extends ParserRuleContext {
		public Token name;
		public FormalContext formal;
		public List<FormalContext> paramaters = new ArrayList<FormalContext>();
		public Token type;
		public ExprContext body;
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			((MethodContext)_localctx).name = match(ID);
			setState(52);
			match(LPARN);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(53);
				((MethodContext)_localctx).formal = formal();
				((MethodContext)_localctx).paramaters.add(((MethodContext)_localctx).formal);
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(54);
					match(COMMA);
					setState(55);
					((MethodContext)_localctx).formal = formal();
					((MethodContext)_localctx).paramaters.add(((MethodContext)_localctx).formal);
					}
					}
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(RPARN);
			setState(67);
			match(COLN);
			setState(68);
			((MethodContext)_localctx).type = match(TYPE);
			setState(69);
			match(LBRAC);
			setState(70);
			((MethodContext)_localctx).body = expr(0);
			setState(71);
			match(RBRAC);
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

	public static class VariableContext extends ParserRuleContext {
		public Token id;
		public Token type;
		public ExprContext value;
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			((VariableContext)_localctx).id = match(ID);
			setState(74);
			match(COLN);
			setState(75);
			((VariableContext)_localctx).type = match(TYPE);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(76);
				match(ASSIGN);
				setState(77);
				((VariableContext)_localctx).value = expr(0);
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

	public static class FormalContext extends ParserRuleContext {
		public Token id;
		public Token type;
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			((FormalContext)_localctx).id = match(ID);
			setState(81);
			match(COLN);
			setState(82);
			((FormalContext)_localctx).type = match(TYPE);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExprContext {
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class MethodCallExprContext extends ExprContext {
		public ExprContext object;
		public Token methodName;
		public ExprContext expr;
		public List<ExprContext> args = new ArrayList<ExprContext>();
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MethodCallExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class UnaryExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class WhileExprContext extends ExprContext {
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public WhileExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ExprListContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprListContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class IfExprContext extends ExprContext {
		public ExprContext cond;
		public ExprContext then;
		public ExprContext else;
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class IsvoidExprContext extends ExprContext {
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IsvoidExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class BinaryExprContext extends ExprContext {
		public ExprContext left;
		public ExprContext right;
		public MultExprContext multExpr() {
			return getRuleContext(MultExprContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PlusExprContext plusExpr() {
			return getRuleContext(PlusExprContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(CoolParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(CoolParser.NOT_EQUAL, 0); }
		public CompExprContext compExpr() {
			return getRuleContext(CompExprContext.class,0);
		}
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class CaseExprContext extends ExprContext {
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public List<TerminalNode> TYPE() { return getTokens(CoolParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CoolParser.TYPE, i);
		}
		public CaseExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class TerminalContext extends ExprContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class AssignExprContext extends ExprContext {
		public Token id;
		public ExprContext value;
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class LetExprContext extends ExprContext {
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public List<TerminalNode> TYPE() { return getTokens(CoolParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CoolParser.TYPE, i);
		}
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> ASSIGN() { return getTokens(CoolParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(CoolParser.ASSIGN, i);
		}
		public LetExprContext(ExprContext ctx) { copyFrom(ctx); }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new MethodCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(85);
				((MethodCallExprContext)_localctx).methodName = match(ID);
				setState(86);
				match(LPARN);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << TRUE) | (1L << ID) | (1L << INTEGER) | (1L << STRING) | (1L << MINUS) | (1L << NOT) | (1L << LBRAC) | (1L << LPARN))) != 0)) {
					{
					setState(87);
					((MethodCallExprContext)_localctx).expr = expr(0);
					((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
					setState(92);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(88);
						match(COMMA);
						setState(89);
						((MethodCallExprContext)_localctx).expr = expr(0);
						((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
						}
						}
						setState(94);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(97);
				match(RPARN);
				}
				break;
			case 2:
				{
				_localctx = new IfExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98);
				match(IF);
				setState(99);
				((IfExprContext)_localctx).cond = expr(0);
				setState(100);
				match(THEN);
				setState(101);
				((IfExprContext)_localctx).then = expr(0);
				setState(102);
				match(ELSE);
				setState(103);
				((IfExprContext)_localctx).else = expr(0);
				setState(104);
				match(FI);
				}
				break;
			case 3:
				{
				_localctx = new WhileExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(WHILE);
				setState(107);
				expr(0);
				setState(108);
				match(LOOP);
				setState(109);
				expr(0);
				setState(110);
				match(POOL);
				}
				break;
			case 4:
				{
				_localctx = new ExprListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(LBRAC);
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(113);
					expr(0);
					setState(114);
					match(SEMIC);
					}
					}
					setState(118); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << TRUE) | (1L << ID) | (1L << INTEGER) | (1L << STRING) | (1L << MINUS) | (1L << NOT) | (1L << LBRAC) | (1L << LPARN))) != 0) );
				setState(120);
				match(RBRAC);
				}
				break;
			case 5:
				{
				_localctx = new LetExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				match(LET);
				setState(123);
				match(ID);
				setState(124);
				match(COLN);
				setState(125);
				match(TYPE);
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(126);
					match(ASSIGN);
					setState(127);
					expr(0);
					}
				}

				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(130);
					match(COMMA);
					setState(131);
					match(ID);
					setState(132);
					match(COLN);
					setState(133);
					match(TYPE);
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ASSIGN) {
						{
						setState(134);
						match(ASSIGN);
						setState(135);
						expr(0);
						}
					}

					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143);
				match(IN);
				setState(144);
				expr(13);
				}
				break;
			case 6:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(CASE);
				setState(146);
				expr(0);
				setState(147);
				match(OF);
				setState(155); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(148);
					match(ID);
					setState(149);
					match(COLN);
					setState(150);
					match(TYPE);
					setState(151);
					match(CASE_ARROW);
					setState(152);
					expr(0);
					setState(153);
					match(SEMIC);
					}
					}
					setState(157); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(159);
				match(ESAC);
				}
				break;
			case 7:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				match(NEW);
				setState(162);
				match(TYPE);
				}
				break;
			case 8:
				{
				_localctx = new IsvoidExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163);
				match(ISVOID);
				setState(164);
				expr(10);
				}
				break;
			case 9:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(165);
				match(LPARN);
				setState(166);
				expr(0);
				setState(167);
				match(RPARN);
				}
				break;
			case 10:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169);
				match(NOT);
				setState(170);
				expr(4);
				}
				break;
			case 11:
				{
				_localctx = new AssignExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				((AssignExprContext)_localctx).id = match(ID);
				setState(172);
				match(ASSIGN);
				setState(173);
				((AssignExprContext)_localctx).value = expr(3);
				}
				break;
			case 12:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				match(MINUS);
				setState(175);
				expr(2);
				}
				break;
			case 13:
				{
				_localctx = new TerminalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				term();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(211);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(209);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(180);
						multExpr();
						setState(181);
						((BinaryExprContext)_localctx).right = expr(10);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(183);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(184);
						plusExpr();
						setState(185);
						((BinaryExprContext)_localctx).right = expr(9);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(187);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(188);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOT_EQUAL) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(189);
						((BinaryExprContext)_localctx).right = expr(7);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(190);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(191);
						compExpr();
						setState(192);
						((BinaryExprContext)_localctx).right = expr(7);
						}
						break;
					case 5:
						{
						_localctx = new MethodCallExprContext(new ExprContext(_parentctx, _parentState));
						((MethodCallExprContext)_localctx).object = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(194);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(195);
						match(T__0);
						setState(196);
						((MethodCallExprContext)_localctx).methodName = match(ID);
						setState(197);
						match(LPARN);
						setState(206);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << TRUE) | (1L << ID) | (1L << INTEGER) | (1L << STRING) | (1L << MINUS) | (1L << NOT) | (1L << LBRAC) | (1L << LPARN))) != 0)) {
							{
							setState(198);
							((MethodCallExprContext)_localctx).expr = expr(0);
							((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
							setState(203);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(199);
								match(COMMA);
								setState(200);
								((MethodCallExprContext)_localctx).expr = expr(0);
								((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
								}
								}
								setState(205);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(208);
						match(RPARN);
						}
						break;
					}
					} 
				}
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MultExprContext extends ParserRuleContext {
		public TerminalNode MULTIPLY() { return getToken(CoolParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(CoolParser.DIVIDE, 0); }
		public MultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multExpr; }
	}

	public final MultExprContext multExpr() throws RecognitionException {
		MultExprContext _localctx = new MultExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_multExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_la = _input.LA(1);
			if ( !(_la==MULTIPLY || _la==DIVIDE) ) {
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

	public static class PlusExprContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public PlusExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusExpr; }
	}

	public final PlusExprContext plusExpr() throws RecognitionException {
		PlusExprContext _localctx = new PlusExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_plusExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
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

	public static class CompExprContext extends ParserRuleContext {
		public TerminalNode LESS_THAN() { return getToken(CoolParser.LESS_THAN, 0); }
		public TerminalNode LESS_OR_EQUAL() { return getToken(CoolParser.LESS_OR_EQUAL, 0); }
		public TerminalNode GREATER_OR_EQUAL() { return getToken(CoolParser.GREATER_OR_EQUAL, 0); }
		public TerminalNode GREATER_THAN() { return getToken(CoolParser.GREATER_THAN, 0); }
		public CompExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compExpr; }
	}

	public final CompExprContext compExpr() throws RecognitionException {
		CompExprContext _localctx = new CompExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_compExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LESS_OR_EQUAL) | (1L << GREATER_OR_EQUAL) | (1L << GREATER_THAN))) != 0)) ) {
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

	public static class TermContext extends ParserRuleContext {
		public Token idTerm;
		public Token intTerm;
		public Token strTerm;
		public Token boolTerm;
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode INTEGER() { return getToken(CoolParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public TerminalNode TRUE() { return getToken(CoolParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CoolParser.FALSE, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_term);
		try {
			setState(225);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				((TermContext)_localctx).idTerm = match(ID);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				((TermContext)_localctx).intTerm = match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(222);
				((TermContext)_localctx).strTerm = match(STRING);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(223);
				((TermContext)_localctx).boolTerm = match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(224);
				((TermContext)_localctx).boolTerm = match(FALSE);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 18);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67\u00e6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\6\2\32\n\2\r\2\16\2\33\3\2\3\2\3\3\3\3\3\3\3\3\5\3$"+
		"\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4\64"+
		"\13\4\3\5\3\5\3\5\3\5\3\5\7\5;\n\5\f\5\16\5>\13\5\7\5@\n\5\f\5\16\5C\13"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6Q\n\6\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\b]\n\b\f\b\16\b`\13\b\5\bb\n\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\6\bw\n\b\r\b\16\bx\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0083\n\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\5\b\u008b\n\b\7\b\u008d\n\b\f\b\16\b\u0090\13\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u009e\n\b\r\b\16\b"+
		"\u009f\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\b\u00b4\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00cc\n\b\f\b\16\b\u00cf"+
		"\13\b\5\b\u00d1\n\b\3\b\7\b\u00d4\n\b\f\b\16\b\u00d7\13\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\f\u00e4\n\f\3\f\2\3\16\r\2\4\6\b"+
		"\n\f\16\20\22\24\26\2\6\4\2**--\3\2\'(\3\2%&\4\2+,./\2\u00ff\2\31\3\2"+
		"\2\2\4\37\3\2\2\2\6\62\3\2\2\2\b\65\3\2\2\2\nK\3\2\2\2\fR\3\2\2\2\16\u00b3"+
		"\3\2\2\2\20\u00d8\3\2\2\2\22\u00da\3\2\2\2\24\u00dc\3\2\2\2\26\u00e3\3"+
		"\2\2\2\30\32\5\4\3\2\31\30\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2\33\34\3"+
		"\2\2\2\34\35\3\2\2\2\35\36\7\2\2\3\36\3\3\2\2\2\37 \7\4\2\2 #\7\30\2\2"+
		"!\"\7\n\2\2\"$\7\30\2\2#!\3\2\2\2#$\3\2\2\2$%\3\2\2\2%&\7\61\2\2&\'\5"+
		"\6\4\2\'(\7\62\2\2()\7\66\2\2)\5\3\2\2\2*+\5\b\5\2+,\7\66\2\2,\61\3\2"+
		"\2\2-.\5\n\6\2./\7\66\2\2/\61\3\2\2\2\60*\3\2\2\2\60-\3\2\2\2\61\64\3"+
		"\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\7\3\2\2\2\64\62\3\2\2\2\65\66\7"+
		"\26\2\2\66A\7\63\2\2\67<\5\f\7\289\7\65\2\29;\5\f\7\2:8\3\2\2\2;>\3\2"+
		"\2\2<:\3\2\2\2<=\3\2\2\2=@\3\2\2\2><\3\2\2\2?\67\3\2\2\2@C\3\2\2\2A?\3"+
		"\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\64\2\2EF\7\60\2\2FG\7\30\2\2"+
		"GH\7\61\2\2HI\5\16\b\2IJ\7\62\2\2J\t\3\2\2\2KL\7\26\2\2LM\7\60\2\2MP\7"+
		"\30\2\2NO\7#\2\2OQ\5\16\b\2PN\3\2\2\2PQ\3\2\2\2Q\13\3\2\2\2RS\7\26\2\2"+
		"ST\7\60\2\2TU\7\30\2\2U\r\3\2\2\2VW\b\b\1\2WX\7\26\2\2Xa\7\63\2\2Y^\5"+
		"\16\b\2Z[\7\65\2\2[]\5\16\b\2\\Z\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2"+
		"\2_b\3\2\2\2`^\3\2\2\2aY\3\2\2\2ab\3\2\2\2bc\3\2\2\2c\u00b4\7\64\2\2d"+
		"e\7\b\2\2ef\5\16\b\2fg\7\17\2\2gh\5\16\b\2hi\7\5\2\2ij\5\16\b\2jk\7\7"+
		"\2\2k\u00b4\3\2\2\2lm\7\20\2\2mn\5\16\b\2no\7\r\2\2op\5\16\b\2pq\7\16"+
		"\2\2q\u00b4\3\2\2\2rv\7\61\2\2st\5\16\b\2tu\7\66\2\2uw\3\2\2\2vs\3\2\2"+
		"\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2yz\3\2\2\2z{\7\62\2\2{\u00b4\3\2\2\2|"+
		"}\7\f\2\2}~\7\26\2\2~\177\7\60\2\2\177\u0082\7\30\2\2\u0080\u0081\7#\2"+
		"\2\u0081\u0083\5\16\b\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u008e\3\2\2\2\u0084\u0085\7\65\2\2\u0085\u0086\7\26\2\2\u0086\u0087\7"+
		"\60\2\2\u0087\u008a\7\30\2\2\u0088\u0089\7#\2\2\u0089\u008b\5\16\b\2\u008a"+
		"\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u0084\3\2"+
		"\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7\t\2\2\u0092\u00b4\5\16"+
		"\b\17\u0093\u0094\7\21\2\2\u0094\u0095\5\16\b\2\u0095\u009d\7\24\2\2\u0096"+
		"\u0097\7\26\2\2\u0097\u0098\7\60\2\2\u0098\u0099\7\30\2\2\u0099\u009a"+
		"\7$\2\2\u009a\u009b\5\16\b\2\u009b\u009c\7\66\2\2\u009c\u009e\3\2\2\2"+
		"\u009d\u0096\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\7\22\2\2\u00a2\u00b4\3\2\2\2"+
		"\u00a3\u00a4\7\23\2\2\u00a4\u00b4\7\30\2\2\u00a5\u00a6\7\13\2\2\u00a6"+
		"\u00b4\5\16\b\f\u00a7\u00a8\7\63\2\2\u00a8\u00a9\5\16\b\2\u00a9\u00aa"+
		"\7\64\2\2\u00aa\u00b4\3\2\2\2\u00ab\u00ac\7)\2\2\u00ac\u00b4\5\16\b\6"+
		"\u00ad\u00ae\7\26\2\2\u00ae\u00af\7#\2\2\u00af\u00b4\5\16\b\5\u00b0\u00b1"+
		"\7&\2\2\u00b1\u00b4\5\16\b\4\u00b2\u00b4\5\26\f\2\u00b3V\3\2\2\2\u00b3"+
		"d\3\2\2\2\u00b3l\3\2\2\2\u00b3r\3\2\2\2\u00b3|\3\2\2\2\u00b3\u0093\3\2"+
		"\2\2\u00b3\u00a3\3\2\2\2\u00b3\u00a5\3\2\2\2\u00b3\u00a7\3\2\2\2\u00b3"+
		"\u00ab\3\2\2\2\u00b3\u00ad\3\2\2\2\u00b3\u00b0\3\2\2\2\u00b3\u00b2\3\2"+
		"\2\2\u00b4\u00d5\3\2\2\2\u00b5\u00b6\f\13\2\2\u00b6\u00b7\5\20\t\2\u00b7"+
		"\u00b8\5\16\b\f\u00b8\u00d4\3\2\2\2\u00b9\u00ba\f\n\2\2\u00ba\u00bb\5"+
		"\22\n\2\u00bb\u00bc\5\16\b\13\u00bc\u00d4\3\2\2\2\u00bd\u00be\f\t\2\2"+
		"\u00be\u00bf\t\2\2\2\u00bf\u00d4\5\16\b\t\u00c0\u00c1\f\b\2\2\u00c1\u00c2"+
		"\5\24\13\2\u00c2\u00c3\5\16\b\t\u00c3\u00d4\3\2\2\2\u00c4\u00c5\f\24\2"+
		"\2\u00c5\u00c6\7\3\2\2\u00c6\u00c7\7\26\2\2\u00c7\u00d0\7\63\2\2\u00c8"+
		"\u00cd\5\16\b\2\u00c9\u00ca\7\65\2\2\u00ca\u00cc\5\16\b\2\u00cb\u00c9"+
		"\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce"+
		"\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\7\64\2\2\u00d3\u00b5\3\2\2\2\u00d3"+
		"\u00b9\3\2\2\2\u00d3\u00bd\3\2\2\2\u00d3\u00c0\3\2\2\2\u00d3\u00c4\3\2"+
		"\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\17\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\t\3\2\2\u00d9\21\3\2\2\2\u00da"+
		"\u00db\t\4\2\2\u00db\23\3\2\2\2\u00dc\u00dd\t\5\2\2\u00dd\25\3\2\2\2\u00de"+
		"\u00e4\7\26\2\2\u00df\u00e4\7\27\2\2\u00e0\u00e4\7 \2\2\u00e1\u00e4\7"+
		"\25\2\2\u00e2\u00e4\7\6\2\2\u00e3\u00de\3\2\2\2\u00e3\u00df\3\2\2\2\u00e3"+
		"\u00e0\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4\27\3\2\2"+
		"\2\26\33#\60\62<AP^ax\u0082\u008a\u008e\u009f\u00b3\u00cd\u00d0\u00d3"+
		"\u00d5\u00e3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}