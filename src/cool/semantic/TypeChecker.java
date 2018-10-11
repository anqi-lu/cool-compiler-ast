package cool.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cool.ast.ASTBaseVisitor;
import cool.ast.ASTNode;
import cool.ast.ASTNodeFactory.Assign;
import cool.ast.ASTNodeFactory.BinaryExpr;
import cool.ast.ASTNodeFactory.BinaryExpr.BinaryOperatorType;
import cool.ast.ASTNodeFactory.BinaryExpr.BinaryOperatorType.*;
import cool.ast.ASTNodeFactory.UnaryExpr.UnaryOperatorType;
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
import cool.symbol.MethodDescriptor;
import cool.symbol.SymbolTable;
import cool.symbol.TableManager;
import cool.symbol.Binding;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.utility.CoolException;

public class TypeChecker extends ASTBaseVisitor<String>{
	
	private TableManager tm;
	private List<String> errors = new ArrayList<String>();
	private ClassBinding currentClass = null;
	private SymbolTable currentTable;
	
	private static final String TYPE_OBJECT = "Object";
	private static final String TYPE_IO = "IO";
	private static final String TYPE_INT = "Int";
	private static final String TYPE_STR = "Str";
	private static final String TYPE_BOOL = "Bool";
	private static final String TYPE_SELF = "SELF_TYPE"; 
	private static final String TYPE_SELF_C = "SELF_TYPE_C"; 
	
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
		visitChildren(node);

		//List<String> errors = checkBindings(); // second pass 
		if (errors.size() > 0) {
			for (String s: errors) {
				System.err.println("====error==== " + s);
			}
			throw new CoolException("[TypeChecker] found errors in type!");
		}
		
		return null;
	}
	
	/**
	 * visit Type node
	 * look for classBinding in the symbol table
	 * update the current scope and then visit its children
	 */
	@Override
	public String visit(Type node) {
		System.out.println("getting type of type node");
		currentClass = tm.lookupClass(node.className);
		return visitChildren(node);
	}
	
	/*
	 * visit Variable node
	 * It can be either just a variable init or plus assignment
	 * if assignment, check if the exprType conforms to varType
	 * and returns exprType
	 * ohterwise return varType
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Variable)
	 */
	@Override
	public String visit(Variable node) {
		
		List<String> types = getChildrenTypes(node);
		String varType = types.get(0);
		if (types.size() == 2) { // if there is an expr
			String exprType = types.get(1);
			
			System.out.println(exprType);
			if (!conformsTo(exprType, varType, currentClass.getClassDescriptor().className)) {
				String e = "ExprType doesn't conform to VarType in variable init assignment.";
				errors.add(e);
				throw new CoolException(e);
			}
			
			System.out.println("getting type of variable node: " + exprType);
			return exprType;
		} 
		System.out.println("getting type of variable node: " + varType);
		return varType;
	}

	/*
	 * visit Variable node
	 * check for object binding
	 * return the type the id has been assigned to
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Formal)
	 */
	@Override
	public String visit(Formal node) {
		
		String idType = node.binding.symbolType;
		String typeName = getChildTypeByIndex(node, 1);
		
		if (!idType.equals(typeName)) {
			String e = "Variable id had been defined for another type.";
			errors.add(e);
			throw new CoolException(e);
		}
		System.out.println("getting type of formal node: " + typeName);
		return typeName;
	}

	/**
	 * visit Method node
	 * check for method binding, method name, and return type 
	 */
	@Override
	public String visit(Method node) {
		String returnType = node.descriptor.returnType;
		
		System.out.println("[debug] defining method: " +
				node.descriptor.methodName
				+ " whose in class "
				+ currentClass.getClassDescriptor().className);
		
	
		System.out.println("getting type of method node: " + returnType);
		return returnType;
	}
	
	/*
	 * Visit ExprList node
	 * return it's last expression's type
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.ExprList)
	 */
	@Override 
	public String visit(ExprList node) {
		return getChildTypeByIndex(node, node.children.size() - 1);
	}
	
	/*
	 * Visit ParamExpr node
	 * return it's only child - expression's type 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.ParamExpr)
	 */
	@Override 
	public String visit(ParamExpr node) {
		ASTNode unwrappedNode = unwrapParans(node);
		return visitChildren(unwrappedNode);
	}
	
	/*
	 * Visit Assign node 
	 * the expression on right hand side need to conform to the variable's type 
	 * return the expression type 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Assign)
	 */
	@Override 
	public String visit(Assign node) {
		List<String> types = getChildrenTypes(node);
		String varType = getChildTypeByIndex(node, 0);
		String exprType = getChildTypeByIndex(node, 1);

		if (!conformsTo(exprType, varType, currentClass.getClassDescriptor().className)) { //TODO
			String e = "ExprType doesn't conform to VarType in an assignment.";
			errors.add(e);
			throw new CoolException(e);
		}
		System.out.println("getting type of assign node: " + exprType);
		return exprType;

	}
	
	/*
	 * Visit BinaryExpr node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.BinaryExpr)
	 */
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
				String e = "[TypeCheck BinaryExpr] Exprs for binary expression not matching.";
				errors.add(e);
				throw new CoolException(e);
			}
			if (!expectedTypes.contains(typeLeft)) {
				String e = "[TypeCheck BinaryExpr] unexpected expr type.";
				errors.add(e);
				throw new CoolException(e);
			}		
		} else {
			System.out.println("BinaryExpr doesn't have two children!");
		}

		return returnType;
	}
	
	/*
	 * (non-Javadoc)
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.UnaryExpr)
	 */
	@Override 
	public String visit(UnaryExpr node) {
		
		String expectType = null;
		String returnType = TYPE_BOOL;
		switch (node.operatorType) {
		case NOT:
			expectType = TYPE_BOOL;
			break;
		case NEG:
			expectType = TYPE_INT;
			break;
		case ISVOID: // can be any type
			break;
		default:
			break;	
		}
		String childType = getChildTypeByIndex(node, 0);
		if (!childType.equals(expectType)) {
			String e = "[TypeCheck UnaryExpr] expression type not valid for operator "
					+ node.operatorType;
			errors.add(e);
			throw new CoolException(e);
		}
		
		System.out.println("getting type of unary node: " + returnType);
		return returnType;
	}
	
	/*
	 * Visit If node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.If)
	 */
	@Override 
	public String visit(If node) {
		System.out.println("getting type of if node");
		List<String> types = getChildrenTypes(node);
		String typeCond = types.get(0);
		String typeThen = types.get(1);
		String typeElse = types.get(2);
		// make sure cond is bool
		if (!typeCond.equals(TYPE_BOOL)) {
			String e = "[TypeCheck If] condition is not boolean.";
			errors.add(e);
			throw new CoolException(e);
		}
		return leastUpperBound(typeThen, typeElse);
	}
	
	/*
	 * Visit While node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.While)
	 */
	@Override 
	public String visit(While node) {
		System.out.println("getting type of while node");
		String typeCond = getChildTypeByIndex(node, 0);
		// make sure cond is bool
		if (!typeCond.equals(TYPE_BOOL)) {
			String e = "[TypeCheck If] condition is not boolean.";
			errors.add(e);
			throw new CoolException(e);
		}
		return TYPE_OBJECT;
	}
	
	/*
	 * Visit Case node 
	 * returns the least upper bound of all of its alternatives' expression types 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Case)
	 */
	@Override 
	public String visit(Case node) {
		System.out.println("getting type of case node");
		
		String condType = getChildTypeByIndex(node, 0);
		List<String> caseAltTypes = getChildrenTypesByRange(node, 1, node.children.size() - 2);
		
		return multiNodesLeastUpperBound(caseAltTypes);
	}
	
	/*
	 * Visit CaseAlt node 
	 * returns the type of the expression
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.CaseAlt)
	 */
	@Override 
	public String visit(CaseAlt node) {
		System.out.println("getting type of casealt node");
		
		return visit(node.getChild(0));
	}
	
	/*
	 * Visit Let node
	 * TODO No init and init
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Let)
	 */
	@Override 
	public String visit(Let node) {
		String exprType = getChildTypeByIndex(node, 0);
		
		System.out.println("getting type of let node: " + exprType);
		return exprType;
	}
	
	/**
	 * visit New node
	 * return SELF_TYPEc if it is SELF_TYPE
	 * otherwise return the defined type
	 */
	@Override 
	public String visit(New node) {
		if (node.type.equals(TYPE_SELF)) {
			System.out.println("getting type of New node: " + TYPE_SELF_C);
			return TYPE_SELF_C;
		}
		
		System.out.println("getting type of New node: " + node.type);
		return node.type;
	}
	
	@Override
	public String visit(MethodCall node) {

		MethodBinding mb = 
				tm.lookupMethodInClass(node.methodName, 
						currentClass.getClassDescriptor().className);
		MethodDescriptor md = mb.getMethodDescriptor();	
		String returnType = md.getReturnType();
		
		System.out.println("[debug] calling method: " +
				mb.getMethodDescriptor().methodName
				+ " whose in class "
				+ currentClass.getClassDescriptor().className);
		
		switch(node.dispatchType) {
		case mcObject:
			// check argument size
			if (node.children.size() - 2 != md.argumentTypes.size()) {
				String e = "[TypeChecker MethodCall] argument number not matching";
				errors.add(e);
				// throw new CoolException(e);
			}
			// check argument types 
			int i = 2;
			for (String paramType : md.argumentTypes) {
				String argType = node.getChild(i++).accept(this);
				if (!conformsTo(argType, paramType, currentClass.getClassDescriptor().className)) {
					String e = "[TypeChecker MethodCall] argument type not matching.";
					errors.add(e);
					// throw new CoolException(e);
				}
			}
			
			ASTNode objNode = node.getChild(0);
			String callObjType = null;
			
			// since newExpr (new TYPE) is wrapped in a ParamExpr, get into another layer
			if (objNode instanceof ParamExpr) {
				objNode = unwrapParans(objNode);
			}
			
			if (objNode instanceof New) { // new expr
				callObjType = ((New) objNode).type;
			} else { // id
				Binding b = (ObjectBinding) ((Terminal) objNode).binding;
				callObjType = b.getSymbolType();
			}
			
			if (returnType.equals(TYPE_SELF)) {
				System.out.println("getting type of methodcall node: " + callObjType);
				return callObjType;
			}
			break;
		case mcLocal:
			// check argument size
			if (node.children.size() - 1 != md.argumentTypes.size()) {
				String e = "[TypeChecker MethodCall] argument number not matching";
				errors.add(e);
				// throw new CoolException(e);
			}
			// check argument types 
			int j = 1;
			for (String paramType : md.argumentTypes) {
				String argType = node.getChild(j++).accept(this);
				if (!conformsTo(argType, paramType, currentClass.getClassDescriptor().className)) {
					String e = "[TypeChecker MethodCall] argument type not matching.";
					errors.add(e);
					// throw new CoolException(e);
				}
			}
			
			if (returnType.equals(TYPE_SELF)) {
				return currentClass.getClassDescriptor().className;
			}
			break;
		}
		System.out.println("getting type of methodcall node: " + returnType);
		return returnType;
	}
	
	/*
	 * Visit Terminal node 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Terminal)
	 */
	@Override 
	public String visit(Terminal node) {
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
		
		System.out.println("getting type of terminal node: " + type);
		return type;
	}
	
	// Helper Methods 
	/**
	 * find the least upper bound for a list of types 
	 * @param types
	 * @return the lub for the given list of types 
	 */
	public String multiNodesLeastUpperBound(List<String> types) {
		String lub = types.get(0);
		
		for (int i = 1; i < types.size(); i++) {
			lub = leastUpperBound(lub, types.get(i));
		}
		return lub;
	}
	/**
	 * find least upper bound between two types 
	 * @param type1
	 * @param type2
	 * @return least upper bound of the two types 
	 */
	public String leastUpperBound(String type1, String type2) {
		System.out.println(type1);
		System.out.println(type2);
		// go through inherits for both 
		if (type1.equals(type2)) {
			return type1;
		}
		List<String> type1Ancestors = findAllAncestors(type1);
		List<String> type2Ancestors = findAllAncestors(type2);
		
		
		return (String) lowestCommonAncestor(type1Ancestors, type2Ancestors);
	}
	
	/** 
	 * find all ancestor's of the input type
	 * @param type
	 * @return list of the input's ancestors
	 */
	public List<String> findAllAncestors(String type) {
		if (tm.lookupClass(type) == null) {
			throw new CoolException("Type Error!");
		}
		ClassDescriptor cd = tm.lookupClass(type).getClassDescriptor();

		List<String> ancestors = new ArrayList<>();

		ClassDescriptor temp = cd;
		while (temp.className != TYPE_OBJECT) {
			ancestors.add(temp.className);
			temp = tm.lookupClass(temp.inherits).getClassDescriptor();
		}
		
		ancestors.add(TYPE_OBJECT);
		return ancestors;
	}
	
 	/**
	 * find the lowest common ancester between two paths 
	 * @param list1
	 * @param list2
	 * @return the lowest common ancester
	 */
	public <T> T lowestCommonAncestor(List<T> list1, List<T> list2) {
		List<T> commonElms = list1
				.stream()
				.filter(list2::contains)
				.collect(Collectors.toList());
		
		if (commonElms.size() == 0) {
			return null;
		}
		
		return commonElms.get(0);
	}
	
	/**
	 * Get list of one node's children types 
	 * @param node
	 * @return list of its children's types
	 */
	private List<String> getChildrenTypes(ASTNode node) {
		List<String> types = new ArrayList<>();
		for (ASTNode child : node.children) {
			types.add(child.accept(this));
		}
		return types;
	}
	
	/**
	 * Get list of one node's children type of a index range 
	 * @param node
	 * @param start index
	 * @param end index
	 * @return list of types
	 */
	private List<String> getChildrenTypesByRange(ASTNode node, int start, int end) {
		List<String> types = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			types.add(getChildTypeByIndex(node, i));
		}
		return types;
	}
	
	/**
	 * Get a particular child's type
	 * @param node
	 * @param index of the child 
	 * @return the child's type
	 */
	private String getChildTypeByIndex(ASTNode node, int i) {		
		ASTNode child = node.children.get(i);
		
		return child.accept(this);
	}
	
	/**
	 * Check whether type1 conforms to type2
	 * @param type1
	 * @param type2
	 * @return boolean
	 */
	public Boolean conformsTo(String type1, String type2, String currentClassName) {
		if (type1 == null || type2 == null) {
			throw new CoolException("[TypeChecker] one of the types is null");
		}
		
		// type 2 will be one of type1's ancestors
		if (!type2.equals(TYPE_SELF_C) && !type1.equals(TYPE_SELF_C)) {
			// T <= T'
			return leastUpperBound(type1, type2).equals(type2);
		} else if (type1.equals(TYPE_SELF_C) && !type2.equals(TYPE_SELF_C)){
			// SELF_TYPEc <= T if C <= T
			return conformsTo(currentClassName, type2, null);
		} else {
			// SELF_TYPEc <= SELF_TYPEc
			// T <= SELF_TYPEC always false
			return type1.equals(type2);
		}
		
	}
	
	/**
	 * unwrap paranExpr
	 * for example (((id))) becomes id
	 * @param node
	 * @return the expr without parans! 
	 */
	private ASTNode unwrapParans(ASTNode node) {
		ASTNode resNode = null;
		while (node instanceof ParamExpr) {
			resNode = node.getChild(0);
			node = resNode;
		}
		
		return resNode;
	}
}
