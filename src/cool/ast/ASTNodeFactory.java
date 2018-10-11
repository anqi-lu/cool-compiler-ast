package cool.ast;

import org.antlr.v4.runtime.Token;

import cool.ast.ASTNodeFactory.BinaryExpr.BinaryOperatorType;
import cool.ast.ASTNodeFactory.MethodCall.DispatchType;
import cool.ast.ASTNodeFactory.UnaryExpr.UnaryOperatorType;
import cool.ast.ASTNodeFactory.Terminal.TerminalType;
import cool.symbol.Binding;
import cool.symbol.BindingFactory;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.symbol.ClassDescriptor;
import cool.symbol.MethodDescriptor;
import cool.symbol.SymbolTable;

/**
 * 
 * @author anqilu
 *
 */
public class ASTNodeFactory {
	public static CoolText makeCoolText() {
		return new CoolText(); 
	}
	
	public static Type makeType(String className) {
		return new Type(className); 
	}
	
	public static Variable makeVariable() {
		return new Variable(); 
	}
	
	public static Formal makeFormal(ObjectBinding binding, SymbolTable scope) {
		return new Formal(binding, scope); 
	}

	public static Method makeMethod
	(MethodBinding mb, MethodDescriptor descriptor, int numArgs) {
		return new Method(mb, descriptor, numArgs);
	}

	public static ExprList makeExprList() {
		return new ExprList();
	}
	
	public static ParamExpr makeParamExpr() {
		return new ParamExpr();
	}
	
	public static Assign makeAssign(String id, ObjectBinding ob, SymbolTable scope) {
		return new Assign(id, ob, scope);
	}
	
	public static BinaryExpr makeBinaryExpr(Token t, BinaryOperatorType opType) {
		return new BinaryExpr(t, opType);
	}
	
	public static UnaryExpr makeUnaryExpr(Token t, UnaryOperatorType opType) {
		return new UnaryExpr(t, opType);
	}
	
	public static If makeIf() {
		return new If();
	}
	
	public static While makeWhile() {
		return new While();
	}
	
	public static Case makeCase() {
		return new Case();
	}
	
	public static CaseAlt makeCaseAlt(Binding b) {
		return new CaseAlt(b);
	}
	
	public static Let makeLet() {
		return new Let();
	}
	
	public static New makeNew(String type) {
		return new New(type);
	}
	
	public static MethodCall makeMethodCall(String methodName, DispatchType type) {
		return new MethodCall(methodName, type);
	}
	
	// TODO(alu): Refactor so that it accepts a binding.
	public static Terminal makeConstant(Token t, Terminal.TerminalType type) {
		return new Terminal(t,null, null, type); 
	}

	public static Terminal makeIDTerminal(Binding b, SymbolTable scope) {
		return new Terminal(b.getToken(), b, scope, TerminalType.tID);
	}
	
	public static Terminal makeIDTerminal(Token t, SymbolTable scope) {
		return new Terminal(t, null, scope, TerminalType.tID);
	}
	
	public static Terminal makeMethodTerminal(Token t) {
		return new Terminal(t, null, null, TerminalType.tMethod);
	}
	
	public static Terminal makeTypeTerminal(Token t) {
		return new Terminal(t, null, null, TerminalType.tType);
	}
	
	/**
	 * Node Classes
	 */
	public static class CoolText extends ASTNode {
		public CoolText() {
			super();
			nodeType = ASTNodeType.nCoolText;
		}
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Node Classes
	 */
	public static class Type extends ASTNode {
		public ClassBinding binding;
		public String className;
		
 		private Type(String className) {
 			super();
			nodeType = ASTNodeType.nType;
			this.className = className;
 		}
 		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Node Classes
	 */
	public static class Variable extends ASTNode {
 		private Variable() {
 			super();
			nodeType = ASTNodeType.nVariable;
 		}
 		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Node Classes
	 */
	public static class Formal extends ASTNode {
		public ObjectBinding binding;
 		private Formal(ObjectBinding binding, SymbolTable scope) {
 			super();
			nodeType = ASTNodeType.nFormal;
			this.binding = binding;
 		}
 		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Node Classes
	 */
	public static class Method extends ASTNode {
		public MethodBinding binding;
		public MethodDescriptor descriptor;
		public int numArgs;

		private Method(MethodBinding mb, MethodDescriptor descriptor, int numArgs) {
			super();
			nodeType = ASTNodeType.nMethod;
			this.binding = mb;
			this.descriptor = descriptor;
			this.numArgs = numArgs;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
		
	}
	
	/**
	 * Node Classes
	 */
	public static class ExprList extends ASTNode {
		private ExprList() {
			super();
			nodeType = ASTNodeType.nExprList;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * 
	 */
	public static class ParamExpr extends ASTNode {
		private ParamExpr() {
			super();
			nodeType = ASTNodeType.nParamExpr;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Node Classes
	 */
	public static class Assign extends ASTNode {
		public String id;
		public ObjectBinding ob; 
		
		private Assign(String id, ObjectBinding ob, SymbolTable scope) {
			super();
			this.id = id;
			this.ob = ob;
			//scope = scope;
			nodeType = ASTNodeType.nAssign;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Node Classes
	 */
	public static class BinaryExpr extends ASTNode {
		public static enum BinaryOperatorType {
			PLUS, 
			MINUS,
			MULTIPLY,
			DIVIDE,
			EQUAL,
			NOT_EQUAL,
			LESS_THAN,
			LESS_OR_EQUAL,	
			GREATER_OR_EQUAL, 
			GREATER_THAN,
			}
		public BinaryOperatorType operatorType;
		private BinaryExpr(Token t, BinaryOperatorType opType) {
			super();
			nodeType = ASTNodeType.nBinary;
			token = t;
			this.operatorType = opType;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Node Classes
	 */
	public static class UnaryExpr extends ASTNode {
		public static enum UnaryOperatorType {
			NOT,
			NEG,
			ISVOID,
			}
		public UnaryOperatorType operatorType;
		private UnaryExpr(Token t, UnaryOperatorType opType) {
			super();
			nodeType = ASTNodeType.nUnary;
			token = t;
			this.operatorType = opType;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * If Node 
	 */
	public static class If extends ASTNode {
		private If() {
			super();
			nodeType = ASTNodeType.nIf;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * While Node 
	 */
	public static class While extends ASTNode {
		private While() {
			super();
			nodeType = ASTNodeType.nWhile;
		}
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Case Node
	 */
	public static class Case extends ASTNode {
		private Case() {
			super();
			nodeType = ASTNodeType.nCase;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * CaseAlt Node
	 * used for conditions nested in Case expression
	 */
	public static class CaseAlt extends ASTNode {
		public Binding binding;
		private CaseAlt(Binding b) {
			super();
			nodeType = ASTNodeType.nCaseAlt;
			this.binding = b;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Let Node
	 */
	public static class Let extends ASTNode {
		private Let() {
			super();
			nodeType = ASTNodeType.nLet;
		}
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * New node
	 */
	public static class New extends ASTNode {
		public String type;

		private New(String type) {
			super();
			nodeType = ASTNodeType.nNew;
			this.type = type;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * 
	 */
	public static class MethodCall extends ASTNode {
		public static enum DispatchType {mcObject, mcLocal};
		public String methodName;
		public DispatchType dispatchType;
		
		private MethodCall(String methodName, DispatchType type) {
			super();
			nodeType = ASTNodeType.nMethodCall;
			this.methodName = methodName;
			this.dispatchType = type;
		}
		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	/**
	 * Terminal Node
	 * Can be Int, Str, Bool, ID or Type 
	 */
	public static class Terminal extends ASTNode {
		public static enum TerminalType {tInt, tStr, tBool, tID, tMethod, tType};
		public Binding binding;
		public TerminalType terminalType;
		public String strValue;
		
		private Terminal(Token t, Binding b, SymbolTable scope, Terminal.TerminalType type) {
			super();
			nodeType = ASTNodeType.nTerminal;
			token = t;
			binding = b;
			this.terminalType = type;
		}

 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}

}
