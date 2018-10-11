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
	private Map<String, String> typeBindingErrors = new HashMap<>();
	private ClassDescriptor currentClass = null;
	private SymbolTable currentTable;
	
	public SymbolTableChecker() {
		tm = TableManager.getInstance();
	}
	
	/**
	 * visit the CoolText node 
	 * check bindings
	 * throw exception for errors
	 */
	@Override
	public Void visit(CoolText node) {
		visitChildren(node); // first visit its children then see all the errors

		//List<String> errors = checkBindings(); // second pass 
		if (errors.size() > 0) {
			for (String s: errors) {
				System.err.println("====error==== " + s);
			}
			throw new CoolException("[SymbolTableChecker] found errors in bindings!");
		}
		return null;
	}
	
	/**
	 * visit Type node
	 * look for classBinding in the symbol table
	 * update the current scope and then visit its children
	 */
	@Override
	public Void visit(Type node) {
		ClassBinding cb = tm.lookupClass(node.binding.symbol);
		if (cb == null) {
			throw new CoolException("[SymbolTableChecker]: Type " + 
					node.nodeClass + "not defined!");
		}
		
		currentClass = cb.descriptor;
		
		String typeInherit = currentClass.inherits;
		// check if the type it inherits is present
		if (tm.lookupClass(typeInherit) == null) {
			String e = "[the type the current class inherits is not defined]";
			errors.add(e);
		}
		
		currentTable = node.scope;
		return visitChildren(node);
	}
	
	/*
	 * (non-Javadoc)
	 * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Variable)
	 */
	public Void visit(Variable node) {
		System.out.println("[SymbolTypeCheck] visiting variable node.");
		return visitChildren(node);
	}
	
	/**
	 * visit Variable node
	 * check for object binding
	 */
	@Override
	public Void visit(Formal node) {
		System.out.println("[SymbolTypeCheck] visiting formal node.");

		if (node.binding == null) {
			String error = "Variable binding is not found." ;
			errors.add(error);
			throw new CoolException("[SymbolTableChecker] Variable not found!");
		}
		String var =  node.binding.getSymbol();
		ObjectBinding ob = tm.lookupID(var, currentClass.className, node.scope);
		if (ob == null) {
			String error = "Variable " + var + " is not defined in the current class.";
			errors.add(error);
			throw new CoolException("[SymbolTableChecker] Variable not found!");

		}
		String symbolType = node.binding.getSymbolType();
		
		if (tm.lookupClass(symbolType) == null) {
			// class is not defined, add error
			String error = "Variable " + var + " has been defined as "
					+ symbolType + " but it has not been defined.";
			errors.add(error);
			typeBindingErrors.put(symbolType, error);
			System.out.println(error);
		}
		
		return visitChildren(node);
	}

	/**
	 * visit Method node
	 * check for method binding, method name, and return type 
	 */
	@Override
	public Void visit(Method node) {
		System.out.println("visiting method node: " + node.binding.symbol);
		if (node.binding == null) {
			String error = "Method " + node.token.getText() + " binding not found.";
			errors.add(error);
			throw new CoolException("[SymbolTableChecker] Method binding not found!");
		}
		String methodName = node.descriptor.getMethodName();
		
		if (currentClass.getMethodDescriptor(methodName) == null) {
			String error = "Method " + methodName + " is not defined in the current class.";
			errors.add(error);
			throw new CoolException("[SymbolTableChecker] Method name not defined!");
		}
		
		String type = node.descriptor.returnType;
		if (type.length() > 4 && type.substring(0, 4).equals("SELF")) {
			type = type.split("_")[1];
		}
		
		if (tm.lookupClass(type) == null) {
			
			String error  = "Method " + methodName + "'s return type" + 
					node.descriptor.returnType + " is not defined.";
			errors.add(error);
			System.out.println(error);
		}
		
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(ExprList node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(ParamExpr node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(Assign node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(BinaryExpr node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(UnaryExpr node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(If node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(While node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(Case node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(CaseAlt node) {
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(Let node) {
		System.out.println("[SymbolTableChecker] visiting Let node");
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(New node) {
		return visitChildren(node);
	}
	
	@Override
	public Void visit(MethodCall node) {
		System.out.println("visiting methodCall node: " + node.methodName);
		visitChildren(node);
		ASTNode methodNameTerm = null;// methodname terminal
	
		ASTNode object = null;
		ASTNode type = null;
		ObjectBinding ob = null;
		MethodBinding mb = null;
		
		switch (node.dispatchType) {
		case mcObject:
			object = node.getChild(0);

			methodNameTerm = node.getChild(1); // either id or (new TYPE)
						
			String startClass = null; 
			
			if (object instanceof Terminal) {
				System.out.println(((Terminal) object).terminalType);
				
				System.out.println(object.token.getText());
				
				if (((Terminal) object).binding instanceof MethodBinding) {
					MethodBinding binding = (MethodBinding) ((Terminal) object).binding;
					String x = binding.getClassDescriptor().toString();
					System.out.println(x);
				}
				
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
					// types in the program
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
					
					System.out.println("Methodname is not a terminal");
					throw new CoolException("Method name is not a terminal!");
				}
			}
		case mcLocal:
			methodNameTerm = node.getChild(0);
			mb = tm.lookupMethodInClass(node.methodName, currentClass.className);
			if (mb == null) {
				String error = "Method " + node.methodName + " binding not found.";
				// errors.add(error);
				// not adding because we are going to check it in type checker
			}
			if (methodNameTerm instanceof Terminal) {
				((Terminal)methodNameTerm).binding = mb;
			}
			break;
		}
		
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(Terminal node) {
//		System.out.println("[SymbolTableChecker]visiting temrinal node: "
//				+ node.token.getText() + " node scope is: " + node.scope
//				+ " on line: " + node.token.getLine());
		System.out.println("********** node type is: " + node.terminalType);
		
		switch (node.terminalType) {
			case tInt:
				break;
			case tStr:
				break;
			case tBool:
				break;
			case tID: // variable reference
				System.out.println("[SymbolTableChecker]visiting temrinal node: "
						+ node.token.getText() + " node scope is: " + node.scope
						+ " on line: " + node.token.getLine());
				if (node.binding == null) {
					
					
					ObjectBinding ob = tm.lookupID(node.token.getText(), 
							currentClass.className, node.scope);
					if (ob == null) {
						//addError 
						String error = "Object " + node.token.getText() + " binding not found.";
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
}

