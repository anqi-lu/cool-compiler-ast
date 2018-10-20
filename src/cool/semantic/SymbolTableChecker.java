package cool.semantic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cool.ast.ASTBaseVisitor;
import cool.ast.ASTNode;
import cool.ast.ASTNodeFactory.*;
import cool.symbol.BindingFactory.ClassBinding;
import cool.symbol.BindingFactory.MethodBinding;
import cool.symbol.BindingFactory.ObjectBinding;
import cool.symbol.ClassDescriptor;
import cool.symbol.SymbolTable;
import cool.symbol.TableManager;
import cool.utility.CoolException;

public class SymbolTableChecker extends ASTBaseVisitor<Void>
{
	private TableManager tm;
	private List<String> errors = new ArrayList<String>();
	private ClassDescriptor currentClass = null;
	
	public SymbolTableChecker() {
		tm = TableManager.getInstance();
	}

	/*
	 * Visit the CoolText node 
	 * walk down the tree
	 * throw exception for errors
	 * 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.CoolText)
	 */
	@Override
	public Void visit(CoolText node) {
		visitChildren(node); // first visit its children then see all the errors

		if (errors.size() > 0) {
			for (String s: errors) {
				System.err.println("====error==== " + s);
			}
			throw new CoolException("[SymbolTableChecker] found errors in bindings!");
		}
		return null;
	}
	
	/*
	 * Visit Type node
	 * look for classBinding in the symbol table
	 * update the current scope and then visit its children
	 * 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Type)
	 */
	@Override
	public Void visit(Type node) {
		ClassBinding cb = tm.lookupClass(node.binding.symbol);
		if (cb == null) {
			String e = "[SymbolTableChecker Type] Type " + 
					node.nodeClass + "not defined!";
			errors.add(e);
		} else {
		
			currentClass = cb.descriptor;
			
			String typeInherit = currentClass.inherits;
			// check if the type it inherits is present
			if (tm.lookupClass(typeInherit) == null) {
				String e = "[SymbolTableChecker Type] "
						+ "the type the current class inherits is not defined]";
				errors.add(e);
			}
		}
		
		return visitChildren(node);
	}
	
	/*
	 * visit Variable node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Variable)
	 */
	public Void visit(Variable node) {
		return visitChildren(node);
	}
	

	/*
	 * Visit Variable node
	 * check for object binding
	 * 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Formal)
	 */
	@Override
	public Void visit(Formal node) {
		System.out.println("[SymbolTypeChecker Formal] visiting formal node.");

		if (node.binding == null) {
			String error = "[SymbolTypeChecker Formal] Variable binding is not found." ;
			errors.add(error);
		}
		String var =  node.binding.getSymbol();
		ObjectBinding ob = tm.lookupID(var, currentClass.className, node.scope);
		if (ob == null) {
			String e = "[SymbolTypeChecker Formal] Variable " +
					var + " is not defined in the current class.";
			errors.add(e);

		}
		String symbolType = node.binding.getSymbolType();
		symbolType = checkForSelfType(symbolType);
		
		if (tm.lookupClass(symbolType) == null) {
			String e = "[SymbolTableChecker Formal] Variable " + 
					var + " has been defined as "
					+ symbolType + ". but it has not been defined.";
			errors.add(e);
		}
		
		return visitChildren(node);
	}

	/*
	 * Visit Method node
	 * check for method binding, method name, and return type 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Method)
	 */
	@Override
	public Void visit(Method node) {
		if (node.binding == null) {
			String e = "[SymbolTypeChecker Method] Method " + 
					node.token.getText() + " binding not found.";
			errors.add(e);
		}
		String methodName = node.descriptor.getMethodName();
		
		if (currentClass.getMethodDescriptor(methodName) == null) {
			String e = "[SymbolTableChecker Method] Method " 
					+ methodName + " is not defined in the current class.";
			errors.add(e);
		}
		
		String type = checkForSelfType(node.descriptor.returnType);
		
		if (tm.lookupClass(type) == null) {
			String e  = "[SymbolTableChecker Method] Method " + 
					methodName + "'s return type" + 
					node.descriptor.returnType + " is not defined.";
			errors.add(e);
		}
		
		return visitChildren(node);
	}
	
	/*
	 * Visit ExprList node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.ExprList)
	 */
	@Override 
	public Void visit(ExprList node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit ParamExpr node 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.ParamExpr)
	 */
	@Override 
	public Void visit(ParamExpr node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit Assign node 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Assign)
	 */
	@Override 
	public Void visit(Assign node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit BinaryExpr node 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.BinaryExpr)
	 */
	@Override 
	public Void visit(BinaryExpr node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit UnaryExpr node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.UnaryExpr)
	 */
	@Override 
	public Void visit(UnaryExpr node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit If node 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.If)
	 */
	@Override 
	public Void visit(If node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit While node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.While)
	 */
	@Override 
	public Void visit(While node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit Case node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Case)
	 */
	@Override 
	public Void visit(Case node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit CaseAlt node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.CaseAlt)
	 */
	@Override 
	public Void visit(CaseAlt node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit Let node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Let)
	 */
	@Override 
	public Void visit(Let node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit New node
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.New)
	 */
	@Override 
	public Void visit(New node) {
		return visitChildren(node);
	}
	
	/*
	 * Visit MethodCall node 
	 * 
	 * For object method dispatch, check for the object calling the method, 
	 * look for the method in that object's class.
	 * 
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.MethodCall)
	 */
	@Override
	public Void visit(MethodCall node) {
		visitChildren(node);
		ASTNode methodNameTerm = null;
	
		ASTNode object = null;
		ASTNode type = null;
		ObjectBinding ob = null;
		MethodBinding mb = null;
		
		switch (node.dispatchType) {
		case mcObject:
			object = node.getChild(0);
			methodNameTerm = node.getChild(1);
			String startClass = null; 
			
			if (object instanceof Terminal) {
				ob = (ObjectBinding)((Terminal) object).binding;
				
				if (ob == null) {
					switch (((Terminal) object).terminalType) {
					case tInt:
						startClass = "Int";
						break;
					case tStr:
						startClass = "Str";
						break;
					case tBool:
						startClass = "Bool";
						break;
					default:
						break;
					}
				} else {
					startClass = ob.getSymbolType();
				}
			} else if (object.nodeClass != null) {
				startClass = object.nodeClass; // node's type
			}
			
			if (startClass != null) {
				if (startClass.equals("SELF_TYPE")) {
					startClass = currentClass.className;
				}
				mb = tm.lookupMethodInClass(node.methodName, startClass);
				
				if (methodNameTerm instanceof Terminal) {
					((Terminal)methodNameTerm).binding = mb;
				} else {
					String e = "[SymbolTableChecker MethodCall] Method name is not a terminal.";
					errors.add(e);
				}
			}
		case mcLocal:
			methodNameTerm = node.getChild(0);
			mb = tm.lookupMethodInClass(node.methodName, currentClass.className);
			
			// mb would be null. will catch it in TypeChecker.
			if (methodNameTerm instanceof Terminal) {
				((Terminal)methodNameTerm).binding = mb;
			}
			break;
		}
		
		return visitChildren(node);
	}
	
	/*
	 * Visit Terminal node
	 * Check object binding for ID
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Terminal)
	 */
	@Override 
	public Void visit(Terminal node) {
		switch (node.terminalType) {
			case tInt:
				break;
			case tStr:
				break;
			case tBool:
				break;
			case tID: // variable reference
				if (node.binding == null) {
					ObjectBinding ob = tm.lookupID(node.token.getText(), 
							currentClass.className, node.scope);
					
					if (ob == null) {
						String error = "[SymbolTableChecker Terminal] Object "
								+ node.token.getText() + " binding not found.";
						errors.add(error);
					}
					
					node.binding = ob;
					node.nodeClass = ob == null ? null : node.binding.getSymbolType();
				}
				break;
			case tType:
				break;
			default:
				break;
		}
		return null;
	}
	
	/*
	 * visit all children of a node
	 * @see cool.ast.ASTVisitor#visitChildren(cool.ast.ASTNode)
	 */
	@Override
	public Void visitChildren(ASTNode node) {
		for (ASTNode child : node.children) {
			child.accept(this);
		}
		return null;
	}
	
	/**
	 * Check for SELF_TYPE and replace it with the appropriate type
	 * @param type
	 * @return type
	 */
	private String checkForSelfType(String type) {
		String resType = type;
		if (type.length() > 4 && type.substring(0, 4).equals("SELF")) {
			String[] types = type.split("_");
			resType = types[1];
		}
		
		return resType; 
	}
}

