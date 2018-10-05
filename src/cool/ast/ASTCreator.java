package cool.ast;

import cool.ast.ASTNodeFactory.CoolText;
import cool.ast.ASTNodeFactory.Method;
import cool.ast.ASTNodeFactory.Terminal;
import cool.ast.ASTNodeFactory.Variable;
import cool.lexparse.CoolBaseVisitor;
import cool.lexparse.CoolParser.ClassDefContext;
import cool.lexparse.CoolParser.CoolTextContext;
import cool.lexparse.CoolParser.FeatureContext;
import cool.lexparse.CoolParser.FormalContext;
import cool.lexparse.CoolParser.IfExprContext;
import cool.lexparse.CoolParser.LetExprContext;
import cool.lexparse.CoolParser.MethodContext;
import cool.lexparse.CoolParser.TerminalContext;
import cool.lexparse.CoolParser.VariableContext;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.symbol.MethodDescriptor;
import cool.symbol.TableManager;

public class ASTCreator extends CoolBaseVisitor<ASTNode>{
	private TableManager tm;
	private ClassBinding currentClass;
	
	/**
	 * Default creator.
	 */
	public ASTCreator() {
		tm = TableManager.getInstance();
	}
	
	/**
	 * Visit the top-level parse tree node.
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitCoolText
	 * (cool.lexparse.CoolParser.CoolTextContext)
	 */
	@Override
	public ASTNode visitCoolText(CoolTextContext ctx)
	{
		System.out.println("visitCoolText");
		final CoolText coolText = ASTNodeFactory.makeCoolText();
		for (ClassDefContext t : ctx.classes) {
			System.out.println("accept this class context. visitCoolText.");
			ASTNode type = t.accept(this);
			coolText.addChild(type);
			System.out.println("classes added. visitCoolText.");
		}
		return coolText; 
	}
	
	@Override
    public ASTNode visitClassDef(ClassDefContext ctx)
    {
		
	   final String className = ctx.type.getText();
	   currentClass = tm.StartNewClass(className,
			   ctx.inherits == null ? null : ctx.inherits.getText());
	   final ASTNodeFactory.Type typeNode = ASTNodeFactory.makeType(className);
	   currentClass.token = ctx.type;
	   typeNode.binding = currentClass;
	   FeatureContext features = ctx.features;
	   // visit variables first
	   for (VariableContext v : features.variables) {
	       typeNode.addChild(v.accept(this));
	   }
	   // now methods
	   for (MethodContext m : features.methods) {
	       typeNode.addChild(m.accept(this));
	   }
	   tm.exitScope();
	   System.out.println("visitClassDef");
	   return typeNode;
    }
	
	@Override
	public ASTNode visitVariable(VariableContext ctx) {
       String id = ctx.id.getText();
       String type = ctx.type.getText();
       ObjectBinding b = tm.newVariable(id, type, ctx.id);
       Variable var = ASTNodeFactory.makeVariable(b);
       var.token = ctx.ID().getSymbol();
       if (ctx.value != null) {
    	   System.out.println("visiting value.");
           ASTNode expr = ctx.value.accept(this);
           var.addChild(expr);
       } 
       
       if (tm.currentScopeLevel() == 1) { // at the class level
    	   System.out.println("adding v car.");
    	   currentClass.descriptor.addVariable(b);
       }
       return var; 
	}
	
	@Override
	public ASTNode visitMethod(MethodContext ctx) {
		String name = ctx.name.getText();
		String type = ctx.type.getText();
		MethodDescriptor md = new MethodDescriptor(name, type);
		
		for (FormalContext param : ctx.paramaters) {
			md.addArgumentType(param.type.getText());
		}
		MethodBinding b = tm.newMethod(md, ctx.name);
		Method m = ASTNodeFactory.makeMethod(md);
		
	
		return m;
	}
	
	@Override
	public ASTNode visitTerminal(TerminalContext ctx)
	{
		System.out.println("visiting terminal. (ast)");
		if (ctx.term().idTerm != null) {
			// TODO(alu): impl.
		} else if (ctx.term().intTerm != null) {
			System.out.println("visiting terminal int. (ast)");
			return ASTNodeFactory.makeConstant(ctx.term().intTerm, Terminal.TerminalType.tInt);
		} else if (ctx.term().strTerm != null) {
			return ASTNodeFactory.makeConstant(ctx.term().strTerm, Terminal.TerminalType.tStr);
		} else if (ctx.term().boolTerm != null) {
			return ASTNodeFactory.makeConstant(ctx.term().boolTerm, Terminal.TerminalType.tBool);
		}
		return null;
	}
	
//	@Override
//	public ASTNode visitIfExpr(IfExprContext ctx) {
//		If expr = makeIf();
//		expr.token = ctx.start; expr.addChild(ctx.condition.accept(this)); expr.addChild(ctx.thenExp.accept(this)); expr.addChild(ctx.elseExp.accept(this)); return expr;
//	}
//
//	@Override
//	public ASTNode visitLetExpr(LetExprContext ctx) {
//		Let letExpr = new Let();
//		tm.enterScope();
//		List<Variable> vars = new ArrayList<Variable>(); for (VariableContext v : ctx.variable()) {
//		Variable var = enterLetSymbol(v); 
//		} letExpr.addChild(ctx.expr().accept(this)); tm.exitScope();
//		for (VariableContext v : ctx.variable()) {
//		Variable var = vars.get(i++); if (v.expr() != null) {
//		
//		ASTNode expr = v.expr().accept(this);
//		var.addChild(expr); }
//		}
//		return letExpr; 
//	}

}
