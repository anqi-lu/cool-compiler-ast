package cool.semantic;

import java.util.List;

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
	private List<String> errors = null;
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

		checkBindings(); // second pass 
		if (errors != null) {
			for (String s: errors) {
				System.err.println("[SymbolTableChecker]: " + s);
			}
			throw new CoolException("[SymbolTableChecker] found errors in bindings!");
		}
		return null;
	}
	
	private void checkBindings() {
		// for all the bindings in symbol table, check it 
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
	
	/**
	 * visit Variable node
	 * check for object binding
	 */
	@Override
	public Void visit(Variable node) {
		String var =  node.binding.getSymbol();
		if (node.binding == null) {
			errors.add("Variable " + var + " binding is not found." );
			//throw new CoolException("[SymbolTableChecker] Variable not found!");
		}
		ObjectBinding ob = tm.lookupID(var, currentClass.className, node.scope);
		if (ob == null) {
			errors.add("Variable " + var + " is not defined in the current class." );
			//throw new CoolException("[SymbolTableChecker] Variable not found!");

		}
		String symbolType = node.binding.getSymbolType();
		
		if (tm.lookupClass(symbolType) == null) {
			// class is not defined (yet) add error
			errors.add("Variable " + var + " has been defined as "
			+ symbolType + " but it has not been defined.");
		}
		
		return visitChildren(node);
	}

	/**
	 * visit Method node
	 * check for method binding, method name, and return type 
	 */
	@Override
	public Void visit(Method node) {
		if (node.binding == null) {
			errors.add("Method " + node.token.getText() + " binding not found.");
			throw new CoolException("[SymbolTableChecker] Method binding not found!");
		}
		String methodName = node.descriptor.getMethodName();
		
		if (currentClass.getMethodDescriptor(methodName) == null) {
			errors.add("Method " + methodName + " is not defined in the current class.");
			throw new CoolException("[SymbolTableChecker] Method name not defined!");
		}
		
		if (tm.lookupClass(node.descriptor.returnType) == null) {
			errors.add("Method " + methodName + "'s return type" + 
					node.descriptor.returnType + " is not defined.");
			System.out.println(errors.get(errors.size()-1));
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
				errors.add("Method " + node.methodName + " binding not found.");
				System.out.println(errors.get(errors.size()-1));
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
						errors.add("Object" + node.token.getText() + " binding not found.");
						System.out.println(errors.get(errors.size()-1));
					}
					
					node.binding = ob;
					node.nodeClass = ob == null ? null : node.binding.getSymbolType();
				}
				break;
			case tType:
				break;
		}
		return null;
	}
	
}

