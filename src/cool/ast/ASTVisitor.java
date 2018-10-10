package cool.ast;

import cool.ast.ASTNodeFactory.*;

public interface ASTVisitor<T> {
	public default T visit(ASTNode node) { return visitChildren(node); }
	public default T visit(CoolText node) { return visitChildren(node); }
	public default T visit(Type node) { return visitChildren(node); }
	public default T visit(Variable node) { return visitChildren(node); }
	public default T visit(Formal node) { return visitChildren(node); }
	public default T visit(Method node) { return visitChildren(node); }
	public default T visit(Assign node) { return visitChildren(node); }
	public default T visit(If node) { return visitChildren(node); }
	public default T visit(While node) { return visitChildren(node); }
	public default T visit(ExprList node) { return visitChildren(node); }
	public default T visit(ParamExpr node) { return visitChildren(node); }
	public default T visit(Let node) { return visitChildren(node); }
	public default T visit(Case node) { return visitChildren(node); }
	public default T visit(CaseAlt node) { return visitChildren(node); }
	public default T visit(New node) { return visitChildren(node); }
	public default T visit(UnaryExpr node) { return visitChildren(node); }
	public default T visit(BinaryExpr node) { return visitChildren(node); }
	public default T visit(MethodCall node) { return visitChildren(node); }
	public default T visit(Terminal node) { return visitChildren(node); }
	
	public default T visitChildren(ASTNode node) {
		System.out.println("visting children.(AST V)");
		for (ASTNode child : node.children) {
			System.out.println("visting child.(AST V)");
			child.accept(this);
		}
		return null;
	}
}
