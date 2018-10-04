package cool.ast;

import java.util.*;
import org.antlr.v4.runtime.Token;
import cool.symbol.*;

public abstract class ASTNode {
	public enum ASTNodeType {
		
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
		children = new ArrayList<ASTNode>(); nodeClass = null;
		this.parent = parent;
		token = null;
		scope = TableManager.getInstance().getCurrentTable(); 
	}
	
	public <T> T accept(ASTVisitor<? extends T> visitor) { return visitor.visit(this); }
	public <T, P> T accept(ASTVisitorWithParameter<? extends T, P> visitor, P param) 
	{
		return visitor.visit(this, param);
	}
	
	public ASTNode getChild(int i)
	{
		return children.get(i);
	}
	
	public void addChild(int i, ASTNode child)
	{
		
	}
	
	public ASTNode removeChild(int i)
	{
		
	}
}
