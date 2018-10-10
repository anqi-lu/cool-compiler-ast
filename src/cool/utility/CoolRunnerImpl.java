/*******************************************************************************
 * This file is used in CS4533/CS544, Compiler Construction & Techniques of
 * Language Translation, Worcester Polytechnic Institute.
 *
 * Copyright (c) 2016-17 Gary F. Pollice
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cool.utility;

import java.util.Map;
import java.util.function.Supplier;
import org.antlr.v4.runtime.*;

import cool.ast.ASTCreator;
import cool.ast.ASTNode;
import cool.lexparse.*;
import cool.lexparse.CoolParser.CoolTextContext;
import cool.semantic.SymbolTableChecker;
import cool.semantic.TypeChecker;

/**
 * The implementation of the CoolRunner interface.
 * @version Oct 25, 2017
 */
public class CoolRunnerImpl implements CoolRunner
{
	private CoolLexer lexer;
	private CoolParser parser;
	private ParserRuleContext parseTree;
	
	private ASTNode ast;
	
	private Supplier<Token> nextToken;
	
	/**
	 * Default constructor. Everything is uninitialized.
	 */
	public CoolRunnerImpl()
	{
		nextToken = () -> { throw new CoolException("Lexer has not been initialized"); } ;
	}

	/************************************************************************** 
	 * Compiler Actions 
	 * These methods will usually be called by external clients. These are the
	 * methods called by the CoolRunner interface
	 */
	
	/*
	 * @see cool.utility.CoolRunner#nextToken()
	 */
	@Override
	public Token nextToken()
	{
		return nextToken.get();
	}
	
	/*
	 * @see cool.utility.CoolRunner#parse()
	 */
	@Override
	public ParserRuleContext parse()
	{
		return parser.coolText();
	}
	
	/*
	 * @see cool.utility.CoolRunner#parseExpr()
	 */
	@Override
	public ParserRuleContext parseExpr()
	{
		return parser.expr();
	}

	/*
	 * @see cool.utility.CoolRunner#parseFeature()
	 */
	@Override
	public ParserRuleContext parseFeature()
	{
		return parser.feature();
	}
	/************************************************************************** 
	 * Initializers
	 * These methods are called by the factory in order to set up and 
	 * initialize the compiler components.
	 */
	
	/**
	 * Set the lexer and change the nextToken variable
	 * @param lexer the lexer to set
	 */
	public void setLexer(CoolLexer lexer)
	{
		this.lexer = lexer;
		nextToken = () -> lexer.nextToken();
	}

	public void setParser(CoolParser parser)
	{
		this.parser = parser;
	}

	/**
	 * @return the lexer
	 */
	public CoolLexer getLexer()
	{
		return lexer;
	}

	/**
	 * @return the parser
	 */
	public CoolParser getParser()
	{
		return parser;
	}

	public void createAST() {
		// TODO Auto-generated method stub
		parseTree = parse();
		ASTCreator creator = new ASTCreator();
		ast = parseTree.accept(creator);
	}

	public ASTNode typecheck() {
		// TODO Auto-generated method stub
		createAST();
		ast.accept(new SymbolTableChecker());
		ast.accept(new TypeChecker());
		return ast;
		
	}

	public Map<String, byte[]> compile() {
		// TODO Auto-generated method stub
		return null;
	}

	public ParserRuleContext getParseTree() {
		// TODO Auto-generated method stub
		return parseTree;
	}

	public ASTNode getAst() {
		// TODO Auto-generated method stub
		return ast;
	}

}
