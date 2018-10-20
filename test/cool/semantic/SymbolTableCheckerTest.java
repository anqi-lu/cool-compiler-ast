package cool.semantic;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import javax.swing.JFrame;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import cool.ast.ASTNode;
import cool.symbol.TableManager;
import cool.utility.*;

class SymbolTableCheckerTest {
	private SymbolTableChecker checker; 
	private CoolRunner runner;
	private ASTNode ast;
	private CoolRunnerImpl testRunner;
	
	@BeforeEach
	void setUp() throws Exception {
		TableManager.reset();
		checker = new SymbolTableChecker();
		ast = null;
		runner = testRunner = new CoolRunnerImpl();
	}
	
	@ParameterizedTest
	@MethodSource("basicValidProgramProvider")
	void typeCheckPrograms(String text) {
		doSymbolCheck(text);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
			"book_list.cl", 
			"cells.cl", 
			"cool.cl", 
			"hello_world.cl", 
			"complex.cl", 
			"io.cl", 
			"life.cl", 
			"list.cl", 
			"hello_01.cl",
			"hello_02.cl",
			"hello_03.cl",
			"hello_09.cl"
	})
	void typeCheckFile(String f) throws IOException {
		doSymbolCheckFromFile("testfiles/" + f);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
			"wrong6.cl",
			"wrong8.cl",
			"wrong14.cl",
			"wrong15.cl"
	})
	void typeCheckWrongFile(String f) throws IOException {
		assertThrows(CoolException.class, () -> doSymbolCheckFromFile("testfiles/" + f));
	}
	
	
	@ParameterizedTest
	@MethodSource("invalidProgramProvider")
	void badPrograms(String text) {
		assertThrows(CoolException.class, () -> doSymbolCheck(text));
	}
	
	@Test
	void sandbox() {
		
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
			Arguments.of("class A inherits Object {};"),
			Arguments.of("class A { i : Int <- 1; };"),
			Arguments.of("class A { i : Int; j : Int; };"),
			Arguments.of("class A {}; class B {};"),
			Arguments.of("class A { f() : Int { 1 }; };"),
			Arguments.of("class A { f(a : Int) : Int { a <- 1 } ; };")
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
			Arguments.of("class A {  }"),
			Arguments.of("class A { a : B; };"),
			Arguments.of("class A inherits B {};"),
			Arguments.of("class Main inherits B {\n" + 
					"    i : Int;\n" + 
					"\n" + 
					"    init(j : Str) : SELF_TYPE {\n" + 
					"        {\n" + 
					"            k <- 1 + 1;\n" + 
					"            self;\n" + 
					"        }\n" + 
					"    };\n" + 
					"\n" + 
					"} ;")
		);
	}
	
	// Helper Methods
	
	private void doSymbolCheck(String text) {
		runner = testRunner =
				CoolFactory.makeParserRunner(CharStreams.fromString(text));
		testRunner.symbolcheck();
		assertTrue(true);
	}
	
	/**
	 * Type check the file named in the argument.
	 * @param fileName
	 * @throws IOException
	 */
	private void doSymbolCheckFromFile(String fileName) throws IOException {
		runner = testRunner =
				CoolFactory.makeParserRunner(CharStreams.fromFileName(fileName));
		testRunner.symbolcheck();
		assertTrue(true);
	}
}
