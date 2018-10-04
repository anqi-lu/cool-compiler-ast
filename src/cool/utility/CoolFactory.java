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
import cool.lexparse.*;

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
	 * @return the runner
	 */
	public static CoolRunner makeLexerRunner(CharStream input)
	{
		CoolRunnerImpl runner = new CoolRunnerImpl();
		runner.setLexer(makeLexer(input));
		return runner;
	}
	
	/**
	 * Return a runner that has a working parser
	 * @param input
	 * @return input the input stream
	 * @return the runner
	 */
	public static CoolRunnerImpl makeParserRunner(CharStream input)
	{
		final CoolRunnerImpl runner = new CoolRunnerImpl();
		final CoolLexer lexer = makeLexer(input);
		runner.setLexer(lexer);
		final CoolParser parser = makeParser(lexer);
		runner.setParser(parser);
		return runner;
	}

	/**
	 * Make the Lexer for the given input. Add a base error listener.
	 * @param input the input stream
	 * @return the lexer
	 */
	private static CoolLexer makeLexer(CharStream input)
	{
		final CoolLexer lexer = new CoolLexer(input);
		lexer.removeErrorListeners();
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
	
	private static CoolParser makeParser(CoolLexer lexer)
	{
		final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		final CoolParser parser = new CoolParser(tokenStream);
		parser.removeErrorListeners();
		parser.addErrorListener(
				new BaseErrorListener() {
					@Override
					public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
							int line, int charPositionInLine, String msg,
							RecognitionException e)
					{
						throw new CoolException(
							e == null ? "Recoverable parser error" : e.getMessage(), e);
					}
				}
		);
		return parser;
	}
}
