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

import java.util.function.Supplier;
import org.antlr.v4.runtime.Token;
import cool.lexparse.CoolLexer;

/**
 * The implementation of the CoolRunner interface.
 * @version Oct 25, 2017
 */
public class CoolRunnerImpl implements CoolRunner
{
	private CoolLexer lexer;
	
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

	
}
