package cool.ast;

import cool.lexparse.CoolBaseVisitor;
import cool.lexparse.CoolParser.ClassDefContext;
import cool.lexparse.CoolParser.CoolTextContext;
import cool.symbol.BindingFactory.ClassBinding;
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
	public ASTNode visit(CoolTextContext ctx)
	{
		final CoolText coolText = makeCoolText();
		for (ClassDefContext t : ctx.classes) {
			ASTNode type = t.accept(this);
			coolText.addChild(type);
		}
		return coolText; 
	}
	
	@Override
    public ASTNode visitClassDef(ClassDefContext ctx)
    {
	   final String className = ctx.TYPE().getText();
	   final InheritsContext inherits = ctx.inherits();
	   // Starting a new class causes a new scope to be created
	   currentClass = tm.StartNewClass(className,
	           inherits == null ? null : inherits.TYPE().getText());
	   final Type typeNode = makeType(className);
	   currentClass.token = ctx.TYPE().getSymbol();
	   typeNode.binding = currentClass;
	   ClassBodyContext body = ctx.classBody();
	   // visit variables first
	   for (VariableContext v : body.variables) {
	       typeNode.addChild(v.accept(this));
	   }
	   // now methods
	   for (MethodContext m : body.methods) {
	       typeNode.addChild(m.accept(this));
	   }
	   tm.exitScope();
	   return typeNode;
    }
	
	@Override
	public ASTNode visitVariable(VariableContext ctx)
   	{
       String id = ctx.ID().getText();
       String type = ctx.TYPE().getText();
       ObjectBinding b = tm.newVariable(id, type, ctx.ID().getSymbol());
       Variable var = makeVariable(b);
       var.token = ctx.ID().getSymbol();
       if (ctx.expr() != null) {  //
           ASTNode expr = ctx.expr().accept(this);
           var.addChild(expr);
       }
       if (tm.currentScopeLevel() == 1) { // at the class level
           currentClass.descriptor.addVariable(b);
       }
       return var; 
	}
	
	@Override
	public ASTNode visitIfExpr(IfExprContext ctx) {
		If expr = makeIf();
		expr.token = ctx.start; expr.addChild(ctx.condition.accept(this)); expr.addChild(ctx.thenExp.accept(this)); expr.addChild(ctx.elseExp.accept(this)); return expr;
	}

	@Override
	public ASTNode visitLetExpr(LetExprContext ctx) {
		Let letExpr = new Let();
		tm.enterScope();
		List<Variable> vars = new ArrayList<Variable>(); for (VariableContext v : ctx.variable()) {
		Variable var = enterLetSymbol(v); 
		} letExpr.addChild(ctx.expr().accept(this)); tm.exitScope();
		for (VariableContext v : ctx.variable()) {
		Variable var = vars.get(i++); if (v.expr() != null) {
		
		ASTNode expr = v.expr().accept(this);
		var.addChild(expr); }
		}
		return letExpr; 
	}

}
