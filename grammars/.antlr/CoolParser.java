// Generated from /Users/anqilu/WPI/Compilers/ew2/COOL Compiler/grammars/Cool.g4 by ANTLR 4.7.1

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
		RULE_variable = 4, RULE_formal = 5, RULE_expr = 6, RULE_caseAltExpr = 7, 
		RULE_term = 8;
	public static final String[] ruleNames = {
		"coolText", "classDef", "feature", "method", "variable", "formal", "expr", 
		"caseAltExpr", "term"
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
			setState(19); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(18);
				((CoolTextContext)_localctx).classDef = classDef();
				((CoolTextContext)_localctx).classes.add(((CoolTextContext)_localctx).classDef);
				}
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			setState(23);
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
			setState(25);
			match(CLASS);
			setState(26);
			((ClassDefContext)_localctx).type = match(TYPE);
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(27);
				match(INHERITS);
				setState(28);
				((ClassDefContext)_localctx).inherits = match(TYPE);
				}
			}

			setState(31);
			match(LBRAC);
			setState(32);
			((ClassDefContext)_localctx).features = feature();
			setState(33);
			match(RBRAC);
			setState(34);
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
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				setState(42);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					{
					setState(36);
					((FeatureContext)_localctx).method = method();
					((FeatureContext)_localctx).methods.add(((FeatureContext)_localctx).method);
					setState(37);
					match(SEMIC);
					}
					}
					break;
				case 2:
					{
					{
					setState(39);
					((FeatureContext)_localctx).variable = variable();
					((FeatureContext)_localctx).variables.add(((FeatureContext)_localctx).variable);
					setState(40);
					match(SEMIC);
					}
					}
					break;
				}
				}
				setState(46);
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
		public Token id;
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
			setState(47);
			((MethodContext)_localctx).id = match(ID);
			setState(48);
			match(LPARN);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(49);
				((MethodContext)_localctx).formal = formal();
				((MethodContext)_localctx).paramaters.add(((MethodContext)_localctx).formal);
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(50);
					match(COMMA);
					setState(51);
					((MethodContext)_localctx).formal = formal();
					((MethodContext)_localctx).paramaters.add(((MethodContext)_localctx).formal);
					}
					}
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(59);
			match(RPARN);
			setState(60);
			match(COLN);
			setState(61);
			((MethodContext)_localctx).type = match(TYPE);
			setState(62);
			match(LBRAC);
			setState(63);
			((MethodContext)_localctx).body = expr(0);
			setState(64);
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
		public FormalContext form;
		public ExprContext value;
		public FormalContext formal() {
			return getRuleContext(FormalContext.class,0);
		}
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
			setState(66);
			((VariableContext)_localctx).form = formal();
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(67);
				match(ASSIGN);
				setState(68);
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
			setState(71);
			((FormalContext)_localctx).id = match(ID);
			setState(72);
			match(COLN);
			setState(73);
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
		public Token type;
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class BinaryExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULTIPLY() { return getToken(CoolParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(CoolParser.DIVIDE, 0); }
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public TerminalNode EQUAL() { return getToken(CoolParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(CoolParser.NOT_EQUAL, 0); }
		public TerminalNode LESS_THAN() { return getToken(CoolParser.LESS_THAN, 0); }
		public TerminalNode LESS_OR_EQUAL() { return getToken(CoolParser.LESS_OR_EQUAL, 0); }
		public TerminalNode GREATER_OR_EQUAL() { return getToken(CoolParser.GREATER_OR_EQUAL, 0); }
		public TerminalNode GREATER_THAN() { return getToken(CoolParser.GREATER_THAN, 0); }
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class TerminalContext extends ExprContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class LetExprContext extends ExprContext {
		public VariableContext variable;
		public List<VariableContext> var = new ArrayList<VariableContext>();
		public ExprContext exp;
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LetExprContext(ExprContext ctx) { copyFrom(ctx); }
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
	public static class ParamExprContext extends ExprContext {
		public ExprContext exp;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class UnaryExprContext extends ExprContext {
		public Token op;
		public ExprContext exp;
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class WhileExprContext extends ExprContext {
		public ExprContext cond;
		public ExprContext exp;
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhileExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class ExprListContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> exprs = new ArrayList<ExprContext>();
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
		public ExprContext thenExpr;
		public ExprContext elseExpr;
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
		public Token op;
		public ExprContext exp;
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IsvoidExprContext(ExprContext ctx) { copyFrom(ctx); }
	}
	public static class CaseExprContext extends ExprContext {
		public ExprContext exp;
		public CaseAltExprContext caseAltExpr;
		public List<CaseAltExprContext> alts = new ArrayList<CaseAltExprContext>();
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CaseAltExprContext> caseAltExpr() {
			return getRuleContexts(CaseAltExprContext.class);
		}
		public CaseAltExprContext caseAltExpr(int i) {
			return getRuleContext(CaseAltExprContext.class,i);
		}
		public CaseExprContext(ExprContext ctx) { copyFrom(ctx); }
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
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new MethodCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(76);
				((MethodCallExprContext)_localctx).methodName = match(ID);
				setState(77);
				match(LPARN);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << TRUE) | (1L << ID) | (1L << INTEGER) | (1L << STRING) | (1L << MINUS) | (1L << NOT) | (1L << LBRAC) | (1L << LPARN))) != 0)) {
					{
					setState(78);
					((MethodCallExprContext)_localctx).expr = expr(0);
					((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(79);
						match(COMMA);
						setState(80);
						((MethodCallExprContext)_localctx).expr = expr(0);
						((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
						}
						}
						setState(85);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(88);
				match(RPARN);
				}
				break;
			case 2:
				{
				_localctx = new IfExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(89);
				match(IF);
				setState(90);
				((IfExprContext)_localctx).cond = expr(0);
				setState(91);
				match(THEN);
				setState(92);
				((IfExprContext)_localctx).thenExpr = expr(0);
				setState(93);
				match(ELSE);
				setState(94);
				((IfExprContext)_localctx).elseExpr = expr(0);
				setState(95);
				match(FI);
				}
				break;
			case 3:
				{
				_localctx = new WhileExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				match(WHILE);
				setState(98);
				((WhileExprContext)_localctx).cond = expr(0);
				setState(99);
				match(LOOP);
				setState(100);
				((WhileExprContext)_localctx).exp = expr(0);
				setState(101);
				match(POOL);
				}
				break;
			case 4:
				{
				_localctx = new ExprListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				match(LBRAC);
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(104);
					((ExprListContext)_localctx).expr = expr(0);
					((ExprListContext)_localctx).exprs.add(((ExprListContext)_localctx).expr);
					setState(105);
					match(SEMIC);
					}
					}
					setState(109); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << TRUE) | (1L << ID) | (1L << INTEGER) | (1L << STRING) | (1L << MINUS) | (1L << NOT) | (1L << LBRAC) | (1L << LPARN))) != 0) );
				setState(111);
				match(RBRAC);
				}
				break;
			case 5:
				{
				_localctx = new LetExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(LET);
				setState(114);
				((LetExprContext)_localctx).variable = variable();
				((LetExprContext)_localctx).var.add(((LetExprContext)_localctx).variable);
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(115);
					match(COMMA);
					setState(116);
					((LetExprContext)_localctx).variable = variable();
					((LetExprContext)_localctx).var.add(((LetExprContext)_localctx).variable);
					}
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(122);
				match(IN);
				setState(123);
				((LetExprContext)_localctx).exp = expr(13);
				}
				break;
			case 6:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				match(CASE);
				setState(126);
				((CaseExprContext)_localctx).exp = expr(0);
				setState(127);
				match(OF);
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(128);
					((CaseExprContext)_localctx).caseAltExpr = caseAltExpr();
					((CaseExprContext)_localctx).alts.add(((CaseExprContext)_localctx).caseAltExpr);
					}
					}
					setState(131); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(133);
				match(ESAC);
				}
				break;
			case 7:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				match(NEW);
				setState(136);
				((NewExprContext)_localctx).type = match(TYPE);
				}
				break;
			case 8:
				{
				_localctx = new IsvoidExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				((IsvoidExprContext)_localctx).op = match(ISVOID);
				setState(138);
				((IsvoidExprContext)_localctx).exp = expr(10);
				}
				break;
			case 9:
				{
				_localctx = new ParamExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				match(LPARN);
				setState(140);
				((ParamExprContext)_localctx).exp = expr(0);
				setState(141);
				match(RPARN);
				}
				break;
			case 10:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				((UnaryExprContext)_localctx).op = match(NOT);
				setState(144);
				((UnaryExprContext)_localctx).exp = expr(4);
				}
				break;
			case 11:
				{
				_localctx = new AssignExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				((AssignExprContext)_localctx).id = match(ID);
				setState(146);
				match(ASSIGN);
				setState(147);
				((AssignExprContext)_localctx).value = expr(3);
				}
				break;
			case 12:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				((UnaryExprContext)_localctx).op = match(MINUS);
				setState(149);
				((UnaryExprContext)_localctx).exp = expr(2);
				}
				break;
			case 13:
				{
				_localctx = new TerminalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				term();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(180);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(153);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(154);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULTIPLY || _la==DIVIDE) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(155);
						((BinaryExprContext)_localctx).right = expr(10);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(156);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(157);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(158);
						((BinaryExprContext)_localctx).right = expr(9);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(159);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(160);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOT_EQUAL) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(161);
						((BinaryExprContext)_localctx).right = expr(7);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(162);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(163);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_THAN) | (1L << LESS_OR_EQUAL) | (1L << GREATER_OR_EQUAL) | (1L << GREATER_THAN))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(164);
						((BinaryExprContext)_localctx).right = expr(7);
						}
						break;
					case 5:
						{
						_localctx = new MethodCallExprContext(new ExprContext(_parentctx, _parentState));
						((MethodCallExprContext)_localctx).object = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(165);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(166);
						match(T__0);
						setState(167);
						((MethodCallExprContext)_localctx).methodName = match(ID);
						setState(168);
						match(LPARN);
						setState(177);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << IF) | (1L << ISVOID) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << NEW) | (1L << TRUE) | (1L << ID) | (1L << INTEGER) | (1L << STRING) | (1L << MINUS) | (1L << NOT) | (1L << LBRAC) | (1L << LPARN))) != 0)) {
							{
							setState(169);
							((MethodCallExprContext)_localctx).expr = expr(0);
							((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
							setState(174);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(170);
								match(COMMA);
								setState(171);
								((MethodCallExprContext)_localctx).expr = expr(0);
								((MethodCallExprContext)_localctx).args.add(((MethodCallExprContext)_localctx).expr);
								}
								}
								setState(176);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(179);
						match(RPARN);
						}
						break;
					}
					} 
				}
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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

	public static class CaseAltExprContext extends ParserRuleContext {
		public Token id;
		public Token type;
		public ExprContext exp;
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CaseAltExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseAltExpr; }
	}

	public final CaseAltExprContext caseAltExpr() throws RecognitionException {
		CaseAltExprContext _localctx = new CaseAltExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_caseAltExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(185);
			((CaseAltExprContext)_localctx).id = match(ID);
			setState(186);
			match(COLN);
			setState(187);
			((CaseAltExprContext)_localctx).type = match(TYPE);
			setState(188);
			match(CASE_ARROW);
			setState(189);
			((CaseAltExprContext)_localctx).exp = expr(0);
			setState(190);
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
		enterRule(_localctx, 16, RULE_term);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				((TermContext)_localctx).idTerm = match(ID);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				((TermContext)_localctx).intTerm = match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				((TermContext)_localctx).strTerm = match(STRING);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
				((TermContext)_localctx).boolTerm = match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(196);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67\u00ca\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\6"+
		"\2\26\n\2\r\2\16\2\27\3\2\3\2\3\3\3\3\3\3\3\3\5\3 \n\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\5\3\5\3\5\3\5"+
		"\3\5\7\5\67\n\5\f\5\16\5:\13\5\5\5<\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\5\6H\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\7\bT\n\b\f"+
		"\b\16\bW\13\b\5\bY\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\6\bn\n\b\r\b\16\bo\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\7\bx\n\b\f\b\16\b{\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u0084\n\b\r"+
		"\b\16\b\u0085\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\5\b\u009a\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00af\n\b\f\b\16\b\u00b2\13\b"+
		"\5\b\u00b4\n\b\3\b\7\b\u00b7\n\b\f\b\16\b\u00ba\13\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\5\n\u00c8\n\n\3\n\2\3\16\13\2\4\6\b\n\f"+
		"\16\20\22\2\6\3\2\'(\3\2%&\4\2**--\4\2+,./\2\u00e3\2\25\3\2\2\2\4\33\3"+
		"\2\2\2\6.\3\2\2\2\b\61\3\2\2\2\nD\3\2\2\2\fI\3\2\2\2\16\u0099\3\2\2\2"+
		"\20\u00bb\3\2\2\2\22\u00c7\3\2\2\2\24\26\5\4\3\2\25\24\3\2\2\2\26\27\3"+
		"\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30\31\3\2\2\2\31\32\7\2\2\3\32\3\3"+
		"\2\2\2\33\34\7\4\2\2\34\37\7\30\2\2\35\36\7\n\2\2\36 \7\30\2\2\37\35\3"+
		"\2\2\2\37 \3\2\2\2 !\3\2\2\2!\"\7\61\2\2\"#\5\6\4\2#$\7\62\2\2$%\7\66"+
		"\2\2%\5\3\2\2\2&\'\5\b\5\2\'(\7\66\2\2(-\3\2\2\2)*\5\n\6\2*+\7\66\2\2"+
		"+-\3\2\2\2,&\3\2\2\2,)\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\7\3\2"+
		"\2\2\60.\3\2\2\2\61\62\7\26\2\2\62;\7\63\2\2\638\5\f\7\2\64\65\7\65\2"+
		"\2\65\67\5\f\7\2\66\64\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29<\3\2"+
		"\2\2:8\3\2\2\2;\63\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\7\64\2\2>?\7\60\2\2?"+
		"@\7\30\2\2@A\7\61\2\2AB\5\16\b\2BC\7\62\2\2C\t\3\2\2\2DG\5\f\7\2EF\7#"+
		"\2\2FH\5\16\b\2GE\3\2\2\2GH\3\2\2\2H\13\3\2\2\2IJ\7\26\2\2JK\7\60\2\2"+
		"KL\7\30\2\2L\r\3\2\2\2MN\b\b\1\2NO\7\26\2\2OX\7\63\2\2PU\5\16\b\2QR\7"+
		"\65\2\2RT\5\16\b\2SQ\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VY\3\2\2\2W"+
		"U\3\2\2\2XP\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z\u009a\7\64\2\2[\\\7\b\2\2\\]"+
		"\5\16\b\2]^\7\17\2\2^_\5\16\b\2_`\7\5\2\2`a\5\16\b\2ab\7\7\2\2b\u009a"+
		"\3\2\2\2cd\7\20\2\2de\5\16\b\2ef\7\r\2\2fg\5\16\b\2gh\7\16\2\2h\u009a"+
		"\3\2\2\2im\7\61\2\2jk\5\16\b\2kl\7\66\2\2ln\3\2\2\2mj\3\2\2\2no\3\2\2"+
		"\2om\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\7\62\2\2r\u009a\3\2\2\2st\7\f\2\2t"+
		"y\5\n\6\2uv\7\65\2\2vx\5\n\6\2wu\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2"+
		"z|\3\2\2\2{y\3\2\2\2|}\7\t\2\2}~\5\16\b\17~\u009a\3\2\2\2\177\u0080\7"+
		"\21\2\2\u0080\u0081\5\16\b\2\u0081\u0083\7\24\2\2\u0082\u0084\5\20\t\2"+
		"\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086"+
		"\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\22\2\2\u0088\u009a\3\2\2\2"+
		"\u0089\u008a\7\23\2\2\u008a\u009a\7\30\2\2\u008b\u008c\7\13\2\2\u008c"+
		"\u009a\5\16\b\f\u008d\u008e\7\63\2\2\u008e\u008f\5\16\b\2\u008f\u0090"+
		"\7\64\2\2\u0090\u009a\3\2\2\2\u0091\u0092\7)\2\2\u0092\u009a\5\16\b\6"+
		"\u0093\u0094\7\26\2\2\u0094\u0095\7#\2\2\u0095\u009a\5\16\b\5\u0096\u0097"+
		"\7&\2\2\u0097\u009a\5\16\b\4\u0098\u009a\5\22\n\2\u0099M\3\2\2\2\u0099"+
		"[\3\2\2\2\u0099c\3\2\2\2\u0099i\3\2\2\2\u0099s\3\2\2\2\u0099\177\3\2\2"+
		"\2\u0099\u0089\3\2\2\2\u0099\u008b\3\2\2\2\u0099\u008d\3\2\2\2\u0099\u0091"+
		"\3\2\2\2\u0099\u0093\3\2\2\2\u0099\u0096\3\2\2\2\u0099\u0098\3\2\2\2\u009a"+
		"\u00b8\3\2\2\2\u009b\u009c\f\13\2\2\u009c\u009d\t\2\2\2\u009d\u00b7\5"+
		"\16\b\f\u009e\u009f\f\n\2\2\u009f\u00a0\t\3\2\2\u00a0\u00b7\5\16\b\13"+
		"\u00a1\u00a2\f\t\2\2\u00a2\u00a3\t\4\2\2\u00a3\u00b7\5\16\b\t\u00a4\u00a5"+
		"\f\b\2\2\u00a5\u00a6\t\5\2\2\u00a6\u00b7\5\16\b\t\u00a7\u00a8\f\24\2\2"+
		"\u00a8\u00a9\7\3\2\2\u00a9\u00aa\7\26\2\2\u00aa\u00b3\7\63\2\2\u00ab\u00b0"+
		"\5\16\b\2\u00ac\u00ad\7\65\2\2\u00ad\u00af\5\16\b\2\u00ae\u00ac\3\2\2"+
		"\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b4"+
		"\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00ab\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b7\7\64\2\2\u00b6\u009b\3\2\2\2\u00b6\u009e\3"+
		"\2\2\2\u00b6\u00a1\3\2\2\2\u00b6\u00a4\3\2\2\2\u00b6\u00a7\3\2\2\2\u00b7"+
		"\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\17\3\2\2"+
		"\2\u00ba\u00b8\3\2\2\2\u00bb\u00bc\7\26\2\2\u00bc\u00bd\7\60\2\2\u00bd"+
		"\u00be\7\30\2\2\u00be\u00bf\7$\2\2\u00bf\u00c0\5\16\b\2\u00c0\u00c1\7"+
		"\66\2\2\u00c1\21\3\2\2\2\u00c2\u00c8\7\26\2\2\u00c3\u00c8\7\27\2\2\u00c4"+
		"\u00c8\7 \2\2\u00c5\u00c8\7\25\2\2\u00c6\u00c8\7\6\2\2\u00c7\u00c2\3\2"+
		"\2\2\u00c7\u00c3\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7"+
		"\u00c6\3\2\2\2\u00c8\23\3\2\2\2\24\27\37,.8;GUXoy\u0085\u0099\u00b0\u00b3"+
		"\u00b6\u00b8\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}