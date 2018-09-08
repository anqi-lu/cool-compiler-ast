/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cool.lexparse;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Stream;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import cool.utility.*;

import static cool.lexparse.CoolLexer.*;

/**
 * Test cases for the Cool lexer. There are examples to start with. The tests use the
 * new JUnit 5 (Jupiter) tests. You may extend this file with your tests or use any other 
 * method of testing that you choose; however, you are responsible for testing your lexer 
 * thoroughly.
 * @version Oct 22, 2017
 */
class CoolLexerTest
{
	 @ParameterizedTest
     @MethodSource("textTypeProvider2")
     void recognizeMultipleToken(String text, int... types)
     {
	  	CoolRunner runner = newLexer(toStream(text));
	
        for (int i : types) {
        	Token t = runner.nextToken();
        	assertEquals(i, t.getType());
        }
        assertEquals(EOF, runner.nextToken().getType());
     }

     private static Stream<Arguments> textTypeProvider2()
     {
         return Stream.of(
             Arguments.of("TYPE", new int[]{TYPE}),
             Arguments.of("-1", new int[]{INTEGER}),
             Arguments.of("1a", new int[]{INTEGER, ID}),
             Arguments.of("1 b", new int[]{INTEGER, ID}),
             Arguments.of("1a1a1a", new int[]{INTEGER, ID}),
             Arguments.of("hello <- 123", new int[] {ID, ASSIGN, INTEGER}),
             Arguments.of("id < -1", new int[] {ID, LESS_THAN, INTEGER}),
             Arguments.of("(false)", new int[] {FALSE}),
             Arguments.of("f(a : Object) : Object", new int[] {ID, ID, TYPE, TYPE}),
             Arguments.of("a : Object", new int[] {ID, TYPE}),
             Arguments.of("(*this is a comment*)", new int[] {COMMENT})
         );
     }
	   
	@ParameterizedTest
	@MethodSource("textTypeProvider")
	void recognizeSingleToken(String text, int type)
	{
		CoolRunner runner = newLexer(toStream(text));
		Token t = runner.nextToken();
		assertEquals(type, t.getType());
		assertEquals(EOF, runner.nextToken().getType());
	}

	// Helper methods
	/**
	 * Turn the string into an ANTLRInputStream
	 * @param text the original text
	 * @return the stream created from the text
	 */
	private CharStream toStream(String text)
	{
		return CharStreams.fromString(text);
	}
	
	/**
	 * Create the lexer for the current test
	 * @param input the ANTLRInputStream to be scanned
	 * @return the lexer
	 */
	private CoolRunner newLexer(CharStream input)
	{
		return CoolFactory.makeLexerRunner(input);
	}
	
	/**
	 * Data for single lexeme tests. These tests take a string that
	 * should only return a single token and then be at EOF. Each instance
	 * of the Arguments.of() method provides the two parameters for these
	 * tests, a String and an int representing the token type.
	 * @return the stream of arguments
	 */
	@SuppressWarnings("unused")
	private static Stream<Arguments> textTypeProvider()
	{
		return Stream.of(
			Arguments.of("class", CLASS),
            Arguments.of("123", INTEGER),
            Arguments.of("WPICS", TYPE),
            Arguments.of("hello", ID), 
            Arguments.of("\"cat\"", STRING), 
            Arguments.of("\"\\cat\"", STRING), 
            //Arguments.of("\"this is \n not ok \"", STRING), // should fail
            Arguments.of("\"this is \\n ok \"", STRING),
            Arguments.of("\"this is \\\nok \"", STRING),
            Arguments.of("\"if\"", STRING),
            Arguments.of("\"\"\"", STRING),
            Arguments.of("\"\\\"", STRING),
            Arguments.of("\"\t\"", STRING),
            Arguments.of("\"\u0000\"", STRING),
            //Arguments.of("\"hello", STRING), //should fail
            Arguments.of("\" have a \" is ok \"", STRING),
            Arguments.of("else", ELSE),
            
            Arguments.of("## this is a comment \n", COMMENT),
            Arguments.of("(* hello (* *)", COMMENT),
            Arguments.of("(* hello", COMMENT), // should fail
            Arguments.of("(* this is a comment *)", COMMENT),
            Arguments.of("(* comment (* hello *) *)", COMMENT)

		);
		
	}
	
}
