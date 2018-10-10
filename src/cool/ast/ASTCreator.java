package cool.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import cool.ast.ASTNodeFactory.Assign;
import cool.ast.ASTNodeFactory.BinaryExpr;
import cool.ast.ASTNodeFactory.BinaryExpr.BinaryOperatorType;
import cool.ast.ASTNodeFactory.Case;
import cool.ast.ASTNodeFactory.CaseAlt;
import cool.ast.ASTNodeFactory.CoolText;
import cool.ast.ASTNodeFactory.ExprList;
import cool.ast.ASTNodeFactory.If;
import cool.ast.ASTNodeFactory.Let;
import cool.ast.ASTNodeFactory.Method;
import cool.ast.ASTNodeFactory.MethodCall;
import cool.ast.ASTNodeFactory.MethodCall.DispatchType;
import cool.ast.ASTNodeFactory.New;
import cool.ast.ASTNodeFactory.ParamExpr;
import cool.ast.ASTNodeFactory.Terminal;
import cool.ast.ASTNodeFactory.UnaryExpr;
import cool.ast.ASTNodeFactory.UnaryExpr.UnaryOperatorType;
import cool.ast.ASTNodeFactory.Variable;
import cool.ast.ASTNodeFactory.While;
import cool.lexparse.CoolBaseVisitor;
import cool.lexparse.CoolParser.AssignExprContext;
import cool.lexparse.CoolParser.BinaryExprContext;
import cool.lexparse.CoolParser.CaseAltExprContext;
import cool.lexparse.CoolParser.CaseExprContext;
import cool.lexparse.CoolParser.ClassDefContext;
import cool.lexparse.CoolParser.CoolTextContext;
import cool.lexparse.CoolParser.ExprContext;
import cool.lexparse.CoolParser.ExprListContext;
import cool.lexparse.CoolParser.FeatureContext;
import cool.lexparse.CoolParser.FormalContext;
import cool.lexparse.CoolParser.IfExprContext;
import cool.lexparse.CoolParser.LetExprContext;
import cool.lexparse.CoolParser.MethodCallExprContext;
import cool.lexparse.CoolParser.MethodContext;
import cool.lexparse.CoolParser.NewExprContext;
import cool.lexparse.CoolParser.ParamExprContext;
import cool.lexparse.CoolParser.TerminalContext;
import cool.lexparse.CoolParser.UnaryExprContext;
import cool.lexparse.CoolParser.VariableContext;
import cool.lexparse.CoolParser.WhileExprContext;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.symbol.MethodDescriptor;
import cool.symbol.TableManager;
import cool.utility.CoolException;

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
		System.out.println("AST done for CoolText");
		System.out.println("===========================");
		return coolText; 
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitClassDef
	 * (cool.lexparse.CoolParser.ClassDefContext)
	 */
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
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitVariable
	 * (cool.lexparse.CoolParser.VariableContext)
	 */
	@Override
	public ASTNode visitVariable(VariableContext ctx) {
       String id = ctx.id.getText();
       String type = ctx.type.getText();
       ObjectBinding b = tm.newVariable(id, type, ctx.id);
       Variable var = ASTNodeFactory.makeVariable(b);;
       
       if (b == null) {
    	   System.out.println("how it the binding be nul??");
       }
       // add variable name and type name terminal nodes
//		Terminal varName = ASTNodeFactory.makeIDTerminal(b);
//		var.addChild(varName);
		
//		Terminal typeName = ASTNodeFactory.makeTypeTerminal(ctx.type);
//		var.addChild(typeName);

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
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitMethod
	 * (cool.lexparse.CoolParser.MethodContext)
	 */
	@Override
	public ASTNode visitMethod(MethodContext ctx) {
		String name = ctx.id.getText();
		String type = ctx.type.getText();

		MethodDescriptor md = new MethodDescriptor(name, type);
		
		for (FormalContext param : ctx.paramaters) {
			md.addArgumentType(param.type.getText());
		}
		
		MethodBinding b = tm.newMethod(md, ctx.id);
		Method m = ASTNodeFactory.makeMethod(b, md);
		
		// add method name terminal
		Terminal methodName = ASTNodeFactory.makeMethodTerminal(ctx.id);
		m.addChild(methodName);
		
		ExprContext body = ctx.body;
		if (body != null) {
			tm.enterScope();
			System.out.println("accept the body expr context in method. visitMethod.");
			ASTNode child = body.accept(this);
			m.addChild(child);
			System.out.println("body added. visitMethod.");
			tm.exitScope(); 
		}
		return m;
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitExprList
	 * (cool.lexparse.CoolParser.ExprListContext)
	 */
	@Override
	public ASTNode visitExprList(ExprListContext ctx) {
		ExprList exprList = ASTNodeFactory.makeExprList();
		for (ExprContext expr: ctx.exprs) {
			ASTNode child = expr.accept(this);
			exprList.addChild(child);
		}
		return exprList;
	}
	
	/**
	 * Visit ParamExpr node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitExprList
	 * (cool.lexparse.CoolParser.ExprListContext)
	 */
	@Override
	public ASTNode visitParamExpr(ParamExprContext ctx) {
		ParamExpr paramExpr = ASTNodeFactory.makeParamExpr();
		paramExpr.addChild(ctx.exp.accept(this));
		
		return paramExpr;
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitAssignExpr
	 * (cool.lexparse.CoolParser.AssignExprContext)
	 */
	@Override
	public ASTNode visitAssignExpr(AssignExprContext ctx) {
		String id = ctx.id.getText();
		ExprContext value = ctx.value;
		String className = tm.currentClassName;
		
		 ObjectBinding ob = tm.lookupIDInClass(id, className);
		 
		final Assign assign = ASTNodeFactory.makeAssign(id, ob);

		System.out.println("accept the expr context in assign. visitAssign.");
		
		Terminal idTerm = ASTNodeFactory.makeIDTerminal(ctx.id);
	    assign.addChild(idTerm);
	    
		ASTNode expr = value.accept(this);
		assign.addChild(expr);
		System.out.println("expr added. visitAssign.");
			
		return assign; 
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitBinaryExpr
	 * (cool.lexparse.CoolParser.BinaryExprContext)
	 */
	@Override
	public ASTNode visitBinaryExpr(BinaryExprContext ctx) {
		Token op = ctx.op;
		BinaryOperatorType opType;
		switch (op.getText()) {
			case "+": 
				opType = BinaryOperatorType.PLUS;
				break;
			case "-":
				opType = BinaryOperatorType.MINUS;
				break;
			case "*":
				opType = BinaryOperatorType.MULTIPLY;
				break;
			case "/":
				opType = BinaryOperatorType.DIVIDE;
				break;
			case "=":
				opType = BinaryOperatorType.EQUAL;
				break;
			case "~=":
				opType = BinaryOperatorType.MINUS;
				break;
			case "<":
				opType = BinaryOperatorType.LESS_THAN;
				break;
			case "<=":
				opType = BinaryOperatorType.LESS_OR_EQUAL;
				break;
			case ">=":
				opType = BinaryOperatorType.GREATER_OR_EQUAL;
				break;
			case ">":
				opType = BinaryOperatorType.GREATER_THAN;
				break;
			default:
				opType = null; // throw exception
				break;
		}
				
		BinaryExpr binary = ASTNodeFactory.makeBinaryExpr(op, opType);
		binary.addChild(ctx.left.accept(this));
		binary.addChild(ctx.right.accept(this));
		
		return binary;
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitUnaryExpr
	 * (cool.lexparse.CoolParser.UnaryExprContext)
	 */
	@Override
	public ASTNode visitUnaryExpr(UnaryExprContext ctx) {
		Token op = ctx.op;
		UnaryOperatorType opType;
		switch (op.getText()) {
			case "-":
				opType = UnaryOperatorType.NEG;
				break;
			case "~":
				opType = UnaryOperatorType.NOT;
				break;
			case "isvoid":
				opType = UnaryOperatorType.ISVOID;
				break;
			default:
				opType = null;
				break;
		}
		
		UnaryExpr unary = ASTNodeFactory.makeUnaryExpr(op, opType);
		unary.addChild(ctx.exp.accept(this));
		
		return unary;
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitIfExpr
	 * (cool.lexparse.CoolParser.IfExprContext)
	 */
	@Override
	public ASTNode visitIfExpr(IfExprContext ctx) {
		If expr = ASTNodeFactory.makeIf();
		// expr.token = ctx.start; // ?
		expr.addChild(ctx.cond.accept(this)); 
		expr.addChild(ctx.thenExpr.accept(this)); 
		expr.addChild(ctx.elseExpr.accept(this)); 
		return expr;
	}
	
	/**
	 * Visit While node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitWhileExpr
	 * (cool.lexparse.CoolParser.WhileExprContext)
	 */
	@Override
	public ASTNode visitWhileExpr(WhileExprContext ctx) {
		System.out.println("visit while. (ast)");
		While expr = ASTNodeFactory.makeWhile();
		if (ctx.cond.accept(this) == null) {
			System.out.println("while ctx cond is null");
		}
		if (ctx.exp.accept(this) == null) {
			System.out.println("while ctx exp is null");
		}
		expr.addChild(ctx.cond.accept(this));
		expr.addChild(ctx.exp.accept(this));
		System.out.println("exit visit while. (ast)");
		return expr;
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitCaseExpr
	 * (cool.lexparse.CoolParser.CaseExprContext)
	 */
	@Override
	public ASTNode visitCaseExpr(CaseExprContext ctx) {
		Case c = ASTNodeFactory.makeCase();
		
		for (CaseAltExprContext alt: ctx.alts) {
			ASTNode child = alt.accept(this);
			c.addChild(child);
		}
		return c;
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitCaseAltExpr
	 * (cool.lexparse.CoolParser.CaseAltExprContext)
	 */
	@Override
	public ASTNode visitCaseAltExpr(CaseAltExprContext ctx) {
		String id = ctx.id.getText();
	    String type = ctx.type.getText();
	    tm.enterScope();
	    ObjectBinding b = tm.newVariable(id, type, ctx.id);
	    
	    
		CaseAlt c = ASTNodeFactory.makeCaseAlt(b);
        ASTNode expr = ctx.exp.accept(this);
        c.addChild(expr);
        tm.exitScope();
		return c;
	}
	
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitNewExpr
	 * (cool.lexparse.CoolParser.NewExprContext)
	 */
	@Override
	public ASTNode visitNewExpr(NewExprContext ctx) {
		String type = ctx.type.getText();
		New n = ASTNodeFactory.makeNew(type);
		
		// add terminal type
		Terminal typeName = ASTNodeFactory.makeTypeTerminal(ctx.type);
		n.addChild(typeName);
		
		return n;
	}

	/**
	 * Visit LetExpr node
	 * @returns the Let Node with 1 or more Variable nodes
	 * and the Expr Node as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitLetExpr
	 * (cool.lexparse.CoolParser.LetExprContext)
	 */
	 @Override
	 public ASTNode visitLetExpr(LetExprContext ctx) {
		System.out.println("visit LetExpr. (ast)");
	 	Let letExpr = ASTNodeFactory.makeLet();
	 	tm.enterScope();
	 	List<Variable> vars = new ArrayList<Variable>(); 
      	for (VariableContext v : ctx.variable()) {
	 		Variable var = enterLetSymbol(v); 
	 		vars.add(var);
	 	} 
	 	letExpr.addChild(ctx.expr().accept(this)); 
	 	tm.exitScope();
	 	
	 	int i = 0;
	 	System.out.println("visit variable. ");
	 	for (VariableContext v : ctx.variable()) {
	 		Variable var = vars.get(i++); 
	 		if (v.expr() != null) {
	 			ASTNode expr = v.expr().accept(this);
	 			var.addChild(expr); }
	 	}
//	 	System.out.println("exit visit LetExpr. (ast)");
	 	return letExpr; 
	 }
	 
	 private Variable enterLetSymbol(VariableContext ctx) {     
		return (Variable) ctx.accept(this);
	 }
	 
	/**
	 * Visit ClassDef node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitLetExpr
	 * (cool.lexparse.CoolParser.LetExprContext)
	 */
	 @Override
	 public ASTNode visitMethodCallExpr(MethodCallExprContext ctx) {
		 
		 MethodCall call;
		 String methodName = ctx.methodName.getText();
		 System.out.println("visit methodcall: " + methodName);
//		 MethodDescriptor md = new MethodDescriptor(methodName, type);
//
//         MethodBinding b = tm.newMethod(md, ctx.name);
//		 Method m = ASTNodeFactory.makeMethod(b, md);
				 
		 MethodBinding mb = tm.lookupMethodInClass(methodName, tm.currentClassName);
			
		 if (mb == null) {
			 System.out.println("mb is null");
			
		 }
		 if (ctx.object != null) {
			 call = ASTNodeFactory.makeMethodCall(methodName, DispatchType.mcObject);
			 call.addChild(ctx.object.accept(this));
			 
		 } else {
			 call = ASTNodeFactory.makeMethodCall(methodName, DispatchType.mcLocal);
		 }
		 
		 Terminal idTerm = ASTNodeFactory.makeMethodTerminal(ctx.methodName);
		call.addChild(idTerm);
		
		for (ExprContext arg : ctx.args) {
			call.addChild(arg.accept(this));
		}
			
		 return call;
	 }

	/**
	 * Visit Terminal node
	 * @returns the root of the AST with 1 or more Type nodes
	 * as children.
	 * @see cool.lexparse.CoolBaseVisitor#visitClassDef
	 * (cool.lexparse.CoolParser.ClassDefContext)
	 */ 
	@Override
	public ASTNode visitTerminal(TerminalContext ctx) {		
	
		if (ctx.term().idTerm != null) {
			Terminal idTerm;
			Token idToken = ctx.term().idTerm;
			String id = idToken.getText();
			String className = tm.currentClassName;
			
			System.out.println("visiting terminal. id = " + id + " in class " + className);
			if (Character.isUpperCase(id.charAt(0))) { // Type
				idTerm = ASTNodeFactory.makeTypeTerminal(idToken);
			}
			
			ObjectBinding ob = tm.lookupID(id, className, tm.currentTable);
			// can only be an object not a method 
			// MethodBinding mb = tm.lookupMethodInClass(id, className);
			
			if (ob == null) { // Variable
				System.out.println("Made Terminal id = " + id + " with object binding");
				idTerm = ASTNodeFactory.makeIDTerminal(idToken);
			} else {
				System.out.println("Made Terminal id = " + id + " without object binding");
				idTerm = ASTNodeFactory.makeIDTerminal(ob);
			}
			
			return idTerm;
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
	

}
