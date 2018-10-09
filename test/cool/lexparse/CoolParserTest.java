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
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package cool.lexparse;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import javax.swing.JFrame;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import cool.utility.*;

/**
 * Test cases for the CoolParser class.
 * @version Jun 14, 2018
 */
class CoolParserTest
{
	private CoolParser parser;
	private ParserRuleContext tree;
	private CoolRunner runner;
	private CoolRunnerImpl testRunner;
	
	/**
	 * Clear the state for the new test
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		parser = null;
		tree = null;
		runner = testRunner = new CoolRunnerImpl();
	}
	
	@Test
	void sandbox()
	{
		doParse("class Main {\n" + 
				"    i : Int;\n" + 
				"    k : Str <- \"yummy\";\n" + 
				"\n" + 
				"    evolve(a: Int) : SELF_TYPE {\n" + 
				"        (let temp : Str in\n" + 
				"            {\n" + 
				"\n" + 
				"                temp <- (new BABY).concat(cell_at_next_evolution(position));\n" + 
				"                position <- position + 1;\n" + 
				"\n" + 
				"            }\n" + 
				"        ) \n" + 
				"    };\n" + 
				"\n" + 
				"} ;");
 		//showTree();
	}
	
	@ParameterizedTest
	@MethodSource("basicValidProgramProvider")
	void validPrograms(String text)
	{
		doParse(text);
	}
	
	@ParameterizedTest
	@MethodSource("basicValidExprProvider")
	void validExprs(String text)
	{
		doParseExpr(text);
	}
	
	@ParameterizedTest
	@MethodSource("basicValidFeatureProvider")
	void validFeatures(String text)
	{
		doParseFeature(text);
	}
	
	// Use the following test if you have test files in a directory.
	@ParameterizedTest
	@ValueSource( strings = {
		"book_list.cl", "cells.cl", "cool.cl", "test.cl", "hello_world.cl", "complex.cl", "io.cl", "life.cl", "list.cl", "hello_01.cl"
	})
	void parseFile(String f) throws IOException
	{
		doFileParse("testfiles/" + f);
	}
	
	@ParameterizedTest
	@MethodSource("invalidProgramProvider")
	void badPrograms(String text)
	{
		assertThrows(CoolException.class, () -> doParse(text));
	}
	
	@ParameterizedTest
	@MethodSource("badFunctionProvider")
	void badFunction(String text)
	{
		String prefix = "class Main {\n" + 
				"  f(a : Object):Object {\n";
		String suffix = "};};";
		assertThrows(CoolException.class, () -> doParse(prefix + text + suffix));
	}

	// Input providers
	/**
	 * Provider for valid programs.
	 * @return the stream of valid programs
	 */
	private static Stream<Arguments> basicValidProgramProvider()
	{
		return Stream.of(
			Arguments.of("class Main {};"),
			Arguments.of("class A inherits B {};"),
			Arguments.of("class A { a : B; };"),
			Arguments.of("class A { i : Int <- 1; };"),
			Arguments.of("class A { i : Int; j : Int; };"),
			Arguments.of("class A {}; class B {};"),
			Arguments.of("class A { f() : Int { 1 }; };"),
			Arguments.of("class A { f(a : Int) : Int { a <- 1 } ; };"),
			Arguments.of("class A { a : B; (*Im just a comment*)};"),
			Arguments.of("class A { a : B; \n\n\n};"),
			Arguments.of("class Cons inherits BookList {\n" + 
					"    xcar : Book;  # We keep the car and cdr in attributes.\n" + 
					"    xcdr : BookList; # Because methods and features must have different names,\n" + 
					"    # we use xcar and xcdr for the attributes and reserve\n" + 
					"    # car and cdr for the features.\n" + 
					"    \n" + 
					"    isNil() : Bool { false };\n" + 
					"    \n" + 
					"    init(hd : Book, tl : BookList) : Cons {\n" + 
					"        {\n" + 
					"            xcar <- hd;\n" + 
					"            xcdr <- tl;\n" + 
					"            self;\n" + 
					"        }\n" + 
					"    };\n" + 
					"\n" + 
					"    car() : Book { xcar };\n" + 
					"\n" + 
					"    cdr() : BookList { xcdr };\n" + 
					"    \n" + 
					"    print_list() : Object {\n" + 
					"        {\n" + 
					"            case xcar.print() of\n" + 
					"                dummy : Book => outStr(\"- dynamic type was Book -\\n\");\n" + 
					"                dummy : Article => outStr(\"- dynamic type was Article -\\n\");\n" + 
					"            esac;\n" + 
					"            xcdr.print_list();\n" + 
					"        }\n" + 
					"    };\n" + 
					"};")
		);
	}
	
	private static Stream<Arguments> basicValidExprProvider()
	{
		return Stream.of(
			Arguments.of("2 + 4 * 6"),
			Arguments.of("isvoid 3 + 4"),
			Arguments.of("(new A)"),
			Arguments.of("-(-4)"),
			Arguments.of("(new A).out_a();"),
			Arguments.of("x = -x;"),
			Arguments.of("1 + 1 # we could think of \"halt\" as SIGTERM"),
			Arguments.of(    " case xcar.print() of\n" + 
					"                dummy : Book => outStr(\"- dynamic type was Book -\\n\");\n" + 
					"                dummy : Article => outStr(\"- dynamic type was Article -\\n\");\n" + 
					"            esac;\n"  )
		);
	}
	
	private static Stream<Arguments> basicValidFeatureProvider()
	{
		return Stream.of(
				Arguments.of("isNil() : Bool { { abort(); true; } };"),
			Arguments.of("isNil() : Bool { { abort(); true; } };"),
			Arguments.of("a : Integer <- 3 + 4 ;"),
			Arguments.of("xcar : Book;  # We keep the car and cdr in attributes.\n"),
			Arguments.of("evolve() : SELF_TYPE {\n" + 
					"        (let position : Int in\n" + 
					"        (let num : Int <- num_cells() in\n" + 
					"        (let temp : Str in\n" + 
					"            {\n" + 
					"                while position < num loop\n" + 
					"                    {\n" + 
					"                        temp <- temp.concat(cell_at_next_evolution(position));\n" + 
					"                        position <- position + 1;\n" + 
					"                    }\n" + 
					"                pool;\n" + 
					"                population_map <- temp;\n" + 
					"                self;\n" + 
					"            }\n" + 
					"        ) ) )\n" + 
					"    };")
		);
	}
	
	/**
	 * Provider for invalid programs.
	 * @return the stream of invalid programs
	 */
	private static Stream<Arguments> invalidProgramProvider()
	{
		return Stream.of(
			Arguments.of("class {};"),
			Arguments.of("class main {};"),
			Arguments.of("class Main {"),
			Arguments.of("class A inherits {};")
		);
	}
	
	private static Stream<Arguments> badFunctionProvider()
	{
		return Stream.of(
			Arguments.of("a : Int;"),
			Arguments.of("a <- {};")
		);
	}
	
	// Helper methods
	/**
	 * This method performs the parse. If you want to see what the tree looks like, use
	 * <br>
	 * <code>System.out.println(tree.toStringTree());<code></br>
	 * after calling this method.
	 * 
	 * @param text
	 *            the text to parse
	 */
	private void doParse(String text)
	{
		runner = testRunner = 
			(CoolRunnerImpl) CoolFactory.makeParserRunner(CharStreams.fromString(text));
		parser = testRunner.getParser();
		tree = parser.coolText();	// does the parse
		assertTrue(true);
	}
	
	private void doParseExpr(String text)
	{
		runner = testRunner = 
			(CoolRunnerImpl) CoolFactory.makeParserRunner(CharStreams.fromString(text));
		parser = testRunner.getParser();
		tree = parser.expr();	// does the parse
		assertTrue(true);
	}
	
	private void doParseFeature(String text)
	{
		runner = testRunner = 
			(CoolRunnerImpl) CoolFactory.makeParserRunner(CharStreams.fromString(text));
		parser = testRunner.getParser();
		tree = parser.feature();	// does the parse
		assertTrue(true);
	}
	
	/**
	 * Parse the file named in the argument.
	 * @param fileName
	 * @throws IOException
	 */
	private void doFileParse(String fileName) throws IOException
	{
		runner = testRunner = (CoolRunnerImpl) CoolFactory.makeParserRunner(
			CharStreams.fromFileName(fileName));
		parser = testRunner.getParser();
		tree = runner.parse();
		assertTrue(true);
	}
	
	/**
	 * Call this routine to display the parse tree. Hit ENTER on the console to continue.
	 */
	private void showTree()
	{
		System.out.println(tree.toStringTree(parser));
		List<String> ruleNames = Arrays.asList(parser.getRuleNames());
		TreeViewer tv = new TreeViewer(ruleNames, tree);
		JFrame frame = new JFrame("Parse Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(tv);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

