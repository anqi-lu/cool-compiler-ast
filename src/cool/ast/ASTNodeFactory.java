package cool.ast;

import org.antlr.v4.runtime.Token;

import cool.ast.ASTNodeFactory.BinaryExpr.BinaryOperatorType;
import cool.ast.ASTNodeFactory.UnaryExpr.UnaryOperatorType;
import cool.ast.ASTNodeFactory.Terminal.TerminalType;
import cool.symbol.Binding;
import cool.symbol.BindingFactory;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.symbol.ClassDescriptor;
import cool.symbol.MethodDescriptor;

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
		return new Type(); 
	}
	public static Variable makeVariable(ObjectBinding binding) {
		return new Variable(binding); 
	}

	public static Method makeMethod(MethodDescriptor descriptor) {
		return new Method(descriptor);
	}

	public static ExprList makeExprList() {
		return new ExprList();
	}
	
	public static Assign makeAssign(String id, ObjectBinding ob) {
		return new Assign(id, ob);
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
	
	public static Case makeCase() {
		return new Case();
	}
	
	public static CaseAlt makeCaseAlt(Binding b) {
		return new CaseAlt(b);
	}
	// TODO(alu): Refactor so that it accepts a binding.
	public static Terminal makeConstant(Token t, Terminal.TerminalType type) {
		return new Terminal(t, type); 
	}

	public static Terminal makeIDTerminal(Binding b) {
		return new Terminal(b.getToken(), TerminalType.tVarName);
	}
	
	public static Terminal makeIDTerminal(Token t) {
		return new Terminal(t, TerminalType.tMethodName);
	}
	
	public static Terminal makeTypeTerminal(Token t) {
		return new Terminal(t, TerminalType.tTypeName);
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
	
	public static class Type extends ASTNode {
		public ClassBinding binding;
 		private Type() {
 			super();
			nodeType = ASTNodeType.nType;
 		}
 		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	public static class Variable extends ASTNode {
		public ObjectBinding binding;
 		private Variable(ObjectBinding binding) {
 			super();
			nodeType = ASTNodeType.nVariable;
			this.binding = binding;
 		}
 		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	public static class Method extends ASTNode {

		public MethodDescriptor descriptor;

		private Method(MethodDescriptor descriptor) {
			super();
			nodeType = ASTNodeType.nMethod;
			this.descriptor = descriptor;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
		
	}
	
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
	
	public static class Assign extends ASTNode {
		public String id;
		public ObjectBinding ob; 
		
		private Assign(String id, ObjectBinding ob) {
			super();
			this.id = id;
			this.ob = ob;
			nodeType = ASTNodeType.nAssign;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
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
	
	public static class Terminal extends ASTNode {
		public static enum TerminalType {tInt, tStr, tBool, tVarName, tMethodName, tTypeName};
		public Binding binding;
		public TerminalType terminalType;
		public String strValue;
		
		private Terminal(Token t, Terminal.TerminalType type) {
			super();
			nodeType = ASTNodeType.nTerminal;
			token = t;
			this.terminalType = type;
		}
		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}

}
