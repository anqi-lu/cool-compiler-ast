package cool.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cool.ast.ASTBaseVisitor;
import cool.ast.ASTNode;
import cool.ast.ASTNodeFactory.Assign;
import cool.ast.ASTNodeFactory.BinaryExpr;
import cool.ast.ASTNodeFactory.BinaryExpr.BinaryOperatorType;
import cool.ast.ASTNodeFactory.BinaryExpr.BinaryOperatorType.*;
import cool.ast.ASTNodeFactory.Case;
import cool.ast.ASTNodeFactory.CaseAlt;
import cool.ast.ASTNodeFactory.CoolText;
import cool.ast.ASTNodeFactory.ExprList;
import cool.ast.ASTNodeFactory.Formal;
import cool.ast.ASTNodeFactory.If;
import cool.ast.ASTNodeFactory.Let;
import cool.ast.ASTNodeFactory.Method;
import cool.ast.ASTNodeFactory.MethodCall;
import cool.ast.ASTNodeFactory.New;
import cool.ast.ASTNodeFactory.ParamExpr;
import cool.ast.ASTNodeFactory.Terminal;
import cool.ast.ASTNodeFactory.Type;
import cool.ast.ASTNodeFactory.UnaryExpr;
import cool.ast.ASTNodeFactory.Variable;
import cool.ast.ASTNodeFactory.While;
import cool.symbol.ClassDescriptor;
import cool.symbol.SymbolTable;
import cool.symbol.TableManager;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.utility.CoolException;

public class TypeChecker extends ASTBaseVisitor<String>{
	
	private TableManager tm;
	private List<String> errors = new ArrayList<String>();
	private ClassDescriptor currentClass = null;
	private SymbolTable currentTable;
	
	private static final  String TYPE_OBJECT = "Object";
	private static final  String TYPE_IO = "IO";
	private static final  String TYPE_INT = "Int";
	private static final  String TYPE_STR = "Str";
	private static final  String TYPE_BOOL = "Bool";
	
	public TypeChecker() {
		tm = TableManager.getInstance();
	}

	/**
	 * visit the CoolText node 
	 * check bindings
	 * throw exception for errors
	 */
	@Override
	public String visit(CoolText node) {
		return visitChildren(node);
	}
	
	/**
	 * visit Type node
	 * look for classBinding in the symbol table
	 * update the current scope and then visit its children
	 */
	@Override
	public String visit(Type node) {
		System.out.println("getting type of type node");
		return visitChildren(node);
	}
	
	public String visit(Variable node) {
		System.out.println("getting type of variable node");
		return visitChildren(node);
	}
	/**
	 * visit Variable node
	 * check for object binding
	 */
	@Override
	public String visit(Formal node) {
		System.out.println("getting type of formal node");
		return node.binding.symbolType;
	}

	/**
	 * visit Method node
	 * check for method binding, method name, and return type 
	 */
	@Override
	public String visit(Method node) {
		System.out.println("getting type of method node");
		return visitChildren(node);
	}
	
	@Override 
	public String visit(ExprList node) {
		return visitChildren(node);
	}
	
	@Override 
	public String visit(ParamExpr node) {
		return visitChildren(node);
	}
	
	@Override 
	public String visit(Assign node) {
		System.out.println("getting type of assign node");
		return visitChildren(node);
	}
	
	@Override 
	public String visit(BinaryExpr node) {
		System.out.println("getting type of binary node");
		BinaryOperatorType operatorType = node.operatorType;
		List<String> expectedTypes = new ArrayList<>();
		String returnType = null;
		switch (operatorType) { 
		case PLUS: // fall through
		case MINUS:
		case MULTIPLY:
		case DIVIDE:
			expectedTypes.add(TYPE_INT);
			returnType = TYPE_INT;
			break;
		case EQUAL:
		case NOT_EQUAL:
			expectedTypes.add(TYPE_INT);
			expectedTypes.add(TYPE_STR);
			expectedTypes.add(TYPE_BOOL);
			returnType = TYPE_BOOL;
			break;
		case LESS_THAN:
		case LESS_OR_EQUAL:
		case GREATER_OR_EQUAL:
		case GREATER_THAN:
			expectedTypes.add(TYPE_INT);
			returnType = TYPE_BOOL;
			break;
		}
		
		// check if both children have same type
		if (node.children.size() == 2) {	
			List<String> types = getChildrenTypes(node);
			String typeLeft = types.get(0);
			String typeRight = types.get(1);

			if (typeLeft != typeRight) {
				String e = "[Visit BinaryExpr] Exprs for binary expression not matching.";
				errors.add(e);
				throw new CoolException(e);
			}
			if (!expectedTypes.contains(typeLeft)) {
				String e = "[Visit BinaryExpr] unexpected expr type.";
				errors.add(e);
				throw new CoolException(e);
			}		
		} else {
			System.out.println("BinaryExpr doesn't have two children!");
		}

		return returnType;
	}
	
	
	@Override 
	public String visit(UnaryExpr node) {
		System.out.println("getting type of unary node");
		return visitChildren(node);
	}
	
	@Override 
	public String visit(If node) {
		System.out.println("getting type of if node");
		// make sure cond is bool
		return visitChildren(node);
	}
	
	@Override 
	public String visit(While node) {
		System.out.println("getting type of while node");
		// make sure cond is bool
		return visitChildren(node);
	}
	
	@Override 
	public String visit(Case node) {
		System.out.println("getting type of case node");
		// make sure cond is bool
		return visitChildren(node);
	}
	
	@Override 
	public String visit(CaseAlt node) {
		System.out.println("getting type of casealt node");
		return visitChildren(node);
	}
	
	@Override 
	public String visit(Let node) {
		System.out.println("getting type of let node");
		return visitChildren(node);
	}
	
	@Override 
	public String visit(New node) {
		System.out.println("getting type of new node");
		return node.type;
	}
	
	@Override
	public String visit(MethodCall node) {
		System.out.println("getting type of methodcall node");
		MethodBinding mb = tm.lookupMethodInClass(node.methodName, tm.currentClassName);
		return mb.getMethodDescriptor().getReturnType();
	}
	
	@Override 
	public String visit(Terminal node) {
		System.out.println("getting type of terminal node");
		String type = TYPE_OBJECT;
		switch (node.terminalType) {
		case tInt:
			type = TYPE_INT;
			break;
		case tStr:
			type = TYPE_STR;
			break;
		case tBool:
			type = TYPE_BOOL;
			break;
		case tID: 
			type = node.binding.getSymbolType();
			break;
		case tType:
			type = node.token.getText();
			break;
		default:
			break;
		}
		return type;
	}
	
//	@Override
//	public String visitChildren(ASTNode node) {
//		System.out.println("visting children.(AST V)");
//		for (ASTNode child : node.children) {
//			System.out.println("visting child.(AST V)");
//			child.accept(this);
//		}
//		return null;
//	}
	
	private String leastUpperBound(String type1, String type2) {
		return TYPE_OBJECT;
	}
	
	public List<String> getChildrenTypes(ASTNode node) {
		List<String> types = new ArrayList<>();
		for (ASTNode child : node.children) {
			System.out.println("visting child.(AST V)");
			types.add(child.accept(this));
		}
		return types;
	}
}
