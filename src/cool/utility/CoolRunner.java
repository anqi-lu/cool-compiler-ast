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

import org.antlr.v4.runtime.*;

/**
 * The CoolRunner is an object produced by the CoolFactory. It provides handles
 * to all of the methods needed to run the compiler. By default, each of these methods
 * throws a CoolException unless the factory created the necessary components when
 * creating the runner.
 * @version Oct 25, 2017
 */
public interface CoolRunner
{
	/**
	 * Scan the next token using the CoolLexer in the runner.
	 * @return the next token.
	 */
	Token nextToken();
	
	/**
	 * Parse the input using the CoolParser in the runner.
	 * @return the parse tree
	 */
	ParserRuleContext parse();
	
	/**
	 * Parse the input using the CoolParser in the runner.
	 * @return the parse tree for expressions
	 */
	ParserRuleContext parseExpr();
	
	/**
	 * Parse the input using the CoolParser in the runner.
	 * @return the parse tree for features
	 */
	ParserRuleContext parseFeature();
}
