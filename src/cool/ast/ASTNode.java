package cool.ast;

import java.util.*;
import org.antlr.v4.runtime.Token;
import cool.symbol.*;

public abstract class ASTNode {
	public enum ASTNodeType {
		nCoolText, nType, nVariable, nMethod, nAssign, nIf, nWhile, nExprList, nLet, nCase, nCaseAlt, nNew, nUnary, nBinary, nMethodCall, nTerminal
	}
	
	public ASTNodeType nodeType;
	public String nodeClass;
	public ASTNode parent;
	public List<ASTNode> children;
	public Token token;
	public SymbolTable scope;

	public ASTNode()
	{
		this(null);
	}

	/** Constructor */
	public ASTNode(ASTNode parent) {
		children = new ArrayList<ASTNode>(); 
		nodeClass = null;
		this.parent = parent;
		token = null;
		scope = TableManager.getInstance().getCurrentTable(); 
	}
	
	abstract public <T> T accept(ASTVisitor<? extends T> visitor);
	
	/** Constructor */
	//abstract public <T, P> T accept(ASTVisitorWithParameter<? extends T, P> visitor, P param);
	
	public ASTNode getChild(int i)
	{
		return children.get(i);
	}
	
	public void addChild(ASTNode child)
	{
		children.add(child);
	}
	
	public void removeChild(int i)
	{
		children.remove(i);
	}

	public void removeLastChild()
	{
		children.remove(children.size() - 1);
	}
}
