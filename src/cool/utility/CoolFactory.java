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
import cool.lexparse.CoolLexer;

/**
 * The CoolFactory is a factory class that provides appropriate parts of the COOL
 * compiler. When asked to provide a compiler component, it provides a runner that has
 * all of the components prior to the requested component initialized to an appropriate
 * state.
 * @version Oct 25, 2017
 */
public class CoolFactory
{
	/**
	 * Return a runner that has just a lexer.
	 * @param input the input stream
	 * @return tje rimmer
	 */
	public static CoolRunner makeLexerRunner(CharStream input)
	{
		CoolRunnerImpl runner = new CoolRunnerImpl();
		runner.setLexer(makeLexer(input));
		return runner;
	}

	/**
	 * Make the Lexer for the given input. Add a base error listener.
	 * @param input the input stream
	 * @return the lexer
	 */
	private static CoolLexer makeLexer(CharStream input)
	{
		CoolLexer lexer = new CoolLexer(input);
		lexer.addErrorListener(
				new BaseErrorListener() {
					@Override
					public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
							int line, int charPositionInLine, String msg,
							RecognitionException e)
					{
						throw new CoolException(msg, e);
					}
				}
		);
		return lexer;
	}
}
