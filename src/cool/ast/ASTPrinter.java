/*******************************************************************************
 * This files was developed for CS4533/CS544: 
 *     Techniques of Program Translation/Compiler Construction.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2018 Gary F. Pollice
 *******************************************************************************/

package cool.ast;

import cool.ast.ASTNodeFactory.*;

/**
 * Pretty printer for abstract syntax trees
 * @version Jul 22, 2018
 */
public class ASTPrinter implements ASTVisitor<String>
{
    private StringBuilder treeString;
    private int indentLevel;
    private final static String SPACES = "  ";
    private final static char NL = '\n';
    
    /**
     * Default constructor
     */
    public ASTPrinter()
    {
        treeString = new StringBuilder();
        indentLevel = 0;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNode)
     */
    @Override
    public String visit(ASTNode node)
    {
        // TODO Auto-generated method stub
        return ASTVisitor.super.visit(node);
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.CoolText)
     */
    @Override
    public String visit(CoolText node)
    {
    	System.out.println("visiting CoolText. (printer)");
        treeString.append("Cool text file\n");
        visitChildren(node);
        return treeString.toString();
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Type)
     */
    @Override
    public String visit(Type t)
    {
        System.out.println("visiting type. (printer)");
    	treeString.append(indent() + 
        		"Class: " + 
        		t.binding.descriptor.className + 
        		" inherits " + 
        		t.binding.descriptor.inherits + NL);
        visitChildren(t);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Variable)
     */
    @Override
    public String visit(Variable v)
    {   
        treeString.append(indent() + "Variable" + NL);
        System.out.println("visiting variable (printer);");
        visitChildren(v);
        return null;
    }

    @Override
    public String visit(Formal v)
    {   
        treeString.append(indent() + "Formal (" + v.binding.getSymbol()
            + " : " + v.binding.getSymbolType() + ")" + NL);
        System.out.println("visiting formal (printer);");
        visitChildren(v);
        return null;
    }
    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Method)
     */
    @Override
    public String visit(Method m)
    {
    	System.out.println("visiting method (printer).");
        treeString.append(indent() + "Method: " + m.descriptor + NL);
        visitChildren(m);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Assign)
     */
    @Override
    public String visit(Assign node)
    {
        treeString.append(indent() + node.id + " <-" + nodeClass(node) + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.If)
     */
    @Override
    public String visit(If node)
    {
        treeString.append(indent() + "If (" + node.nodeClass + ")" + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.While)
     */
    @Override
    public String visit(While node)
    {
        treeString.append(indent() + "While (" + node.nodeClass + ")" + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.ExprList)
     */
    @Override
    public String visit(ExprList node)
    {
        treeString.append(indent() + "Expression list (" + node.nodeClass + ")" + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Let)
     */
    @Override
    public String visit(Let node)
    {
        treeString.append(indent() + "Let" + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Case)
     */
    @Override
    public String visit(Case node)
    {
        treeString.append(indent() + "Case" + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.CaseAlt)
     */
    @Override
    public String visit(CaseAlt node)
    {
        treeString.append(indent() + "Case alternative" + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.New)
     */
    @Override
    public String visit(New node)
    {
        treeString.append(indent() + "new " + node.type + NL);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Unary)
     */
    @Override
    public String visit(UnaryExpr node)
    {
        treeString.append(indent() + node.token.getText() + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Binary)
     */
    @Override
    public String visit(BinaryExpr node)
    {
        treeString.append(indent() + node.token.getText() + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.MethodCall)
     */
    @Override
    public String visit(MethodCall node)
    {
        treeString.append(indent() + node.dispatchType + " method call" + NL);
        visitChildren(node);
        return null;
    }

    /*
     * @see cool.ast.ASTVisitor#visit(cool.ast.ASTNodeFactory.Terminal)
     */
    @Override
    public String visit(Terminal t)
    {
    	System.out.println("visting terminal. (printer)");
        StringBuilder sb = new StringBuilder(indent());
        switch (t.terminalType) {
        case tInt: 
            sb.append("Int (" + t.token.getText() + ")");
            break;
        case tStr:
            sb.append("Str (" + t.token.getText() + ")");
            break;
        case tBool:
            sb.append("Bool (" + t.token.getText() + ")");
            break;
        case tType:
            sb.append("Type (" + t.token.getText() + ")");
            break;
        case tMethod:
            sb.append("Method call (" + t.token.getText());
            sb.append(t.binding == null ? "*" : " in class " 
                + t.binding.getClassWhereDefined());
            sb.append(')');
            break;
        case tID:
            if (t.binding == null) {
                sb.append("ID* (" + t.token.getText() + ")");
            } else {    // there is a binding
                sb.append("ID (" + t.binding.getSymbol() + " : " + t.binding.getSymbolType() + ")");
            }
            break;
        }
        sb.append(NL);
        treeString.append(sb.toString());
        return sb.toString();
    }
    
    private String indent()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append(SPACES);
        }
        return sb.toString();
    }
    
    private String nodeClass(ASTNode node)
    {
        String result = "";
        if (node.nodeClass != null) {
            result = " (" + node.nodeClass + ")";
        }
        return result;
    }

    /*
     * @see cool.ast.ASTVisitor#visitChildren(cool.ast.ASTNode)
     */
    @Override
    public String visitChildren(ASTNode node)
    {
    	System.out.println("Visting children. (printer)");
        indentLevel++;
        ASTVisitor.super.visitChildren(node);
        indentLevel--;
        return null;
    }

    
}
