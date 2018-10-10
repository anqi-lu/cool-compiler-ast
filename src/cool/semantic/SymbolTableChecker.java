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
	private Map<String, List<String>> objectBindingErrors = new HashMap<>();
	private Map<String, List<String>> methodBindingErrors = new HashMap<>();
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
		currentTable = node.scope;
		return visitChildren(node);
	}
	
	public Void visit(Variable node) {
		return visitChildren(node);
	}
	/**
	 * visit Variable node
	 * check for object binding
	 */
	@Override
	public Void visit(Formal node) {
		//String varName = node.getChild(0).token.getText();
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
			// class is not defined (yet) add error
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
//			List<String> val = new ArrayList<>(Arrays.asList(currentClass.className, error));
//			methodBindingErrors.put(node.token.getText(), val);
			throw new CoolException("[SymbolTableChecker] Method binding not found!");
		}
		String methodName = node.descriptor.getMethodName();
		
		if (currentClass.getMethodDescriptor(methodName) == null) {
			String error = "Method " + methodName + " is not defined in the current class.";
			errors.add(error);
//			List<String> val = new ArrayList<>(Arrays.asList(currentClass.className, error));
//			methodBindingErrors.put(node.token.getText(), val);
			throw new CoolException("[SymbolTableChecker] Method name not defined!");
		}
		
		if (tm.lookupClass(node.descriptor.returnType) == null) {
			String error  = "Method " + methodName + "'s return type" + 
					node.descriptor.returnType + " is not defined.";
			errors.add(error);
//			typeBindingErrors.put(node.descriptor.returnType, error);
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
				((Terminal)methodNameTerm).binding = mb;
			}
		case mcLocal:
			methodNameTerm = node.getChild(0);
			mb = tm.lookupMethodInClass(node.methodName, currentClass.className);
			if (mb == null) {
				//addError 
				String error = "Method " + node.methodName + " binding not found.";
				errors.add(error);
//				List<String> val = new ArrayList<>(Arrays.asList(currentClass.className, error));
//				methodBindingErrors.put(node.methodName, val);
				System.out.println(error);
			}
			
			((Terminal)methodNameTerm).binding = mb;
			break;
		}
		
		return visitChildren(node);
	}
	
	@Override 
	public Void visit(Terminal node) {
		System.out.println("[SymbolTableChecker]visiting temrinal node: " + node.token.getText());
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
						//addError 
						String error = "Object " + node.token.getText() + " binding not found.";
						errors.add(error);
//						List<String> val = new ArrayList<>(
//								Arrays.asList(currentClass.className, error));
//						objectBindingErrors.put(node.token.getText(), val);
						System.out.println(error);
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
	
//	
//	private List<String> checkBindings() {
//		// for all the bindings in symbol table, check it again
//		Map<String, String> newTypeBindingErrors = typeBindingErrors.entrySet().stream()
//			    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
//		List<String> errors = new ArrayList<>();
//		for (String type : newTypeBindingErrors.keySet()) {
//			if (tm.lookupClass(type) != null) {
//				typeBindingErrors.remove(type);
//			} else {
//				errors.add(newTypeBindingErrors.get(type));
//			}
//		}
//		
//		// object binding errors 
//		Map<String, List<String>> newObjectBindingErrors = objectBindingErrors.entrySet().stream()
//			    .collect(Collectors.toMap(e -> e.getKey(), e -> new ArrayList(e.getValue())));
//		for (String id : newObjectBindingErrors.keySet()) {
//			String className = newObjectBindingErrors.get(id).get(0);
//			if (tm.lookupIDInClass(id, className) == null) {
//				objectBindingErrors.remove(id);
//			} else {
//				errors.add(newObjectBindingErrors.get(id).get(1));
//			}
//		}
//		
//		// method binding errors
//		Map<String, List<String>> newMethodBindingErrors = methodBindingErrors.entrySet().stream()
//			    .collect(Collectors.toMap(e -> e.getKey(), e -> new ArrayList(e.getValue())));;
//		for (String id : newMethodBindingErrors.keySet()) {
//			
//			System.out.println("");
//			
//			String className = newMethodBindingErrors.get(id).get(0);
//			if (tm.lookupMethodInClass(id, className) == null) {
//				methodBindingErrors.remove(id);
//			} else {
//				errors.add(newMethodBindingErrors.get(id).get(1));
//			}
//		}
//		
//		return errors;
//	}
//	
	
}

