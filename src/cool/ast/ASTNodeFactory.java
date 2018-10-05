package cool.ast;

import org.antlr.v4.runtime.Token;

import cool.symbol.Binding;
import cool.symbol.BindingFactory;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.symbol.ClassDescriptor;
import cool.symbol.MethodDescriptor;

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
	
	// TODO(alu): Refactor so that it accepts a binding.
	public static Terminal makeConstant(Token t, Terminal.TerminalType type) {
		return new Terminal(t, type); 
	}

	public static Method makeMethod(MethodDescriptor descriptor) {
		return new Method(descriptor);
	}
	
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
	
	public static class Terminal extends ASTNode {
		public static enum TerminalType {tInt, tStr, tBool, tMethodName, tClassName};
		public Binding binding;
		public TerminalType terminalType;
		public String strValue;
		
		private Terminal(Token t, Terminal.TerminalType type) {
			this.token = t;
			this.terminalType = type;
		}
		
 		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
	}
	
	public static class Method extends ASTNode {

		public MethodDescriptor descriptor;

		private Method(MethodDescriptor descriptor) {
			this.descriptor = descriptor;
		}
		
		@Override
		public <T> T accept(ASTVisitor<? extends T> visitor) {
			return visitor.visit(this);
		}
		
	}
}
