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

class TypeCheckerTest {
	private TypeChecker checker; 
	private CoolRunner runner;
	private ASTNode ast;
	private CoolRunnerImpl testRunner;
	
	@BeforeEach
	void setUp() throws Exception {
		TableManager.reset();
		checker = new TypeChecker();
		ast = null;
		runner = testRunner = new CoolRunnerImpl();
	}
	
	@Test
	void testLowestCommonAncester0() {
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<String> list2 = Arrays.asList("a");
		String expected = "a";
		assertEquals(checker.lowestCommonAncestor(list1, list2), expected);
	}
	
	@Test
	void testLowestCommonAncester1() {
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<String> list2 = Arrays.asList("d", "f", "b");
		String expected = "b";
		assertEquals(checker.lowestCommonAncestor(list1, list2), expected);
	}
	
	@Test
	void testLowestCommonAncester2() {
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<String> list2 = Arrays.asList("d");
		String expected = null;
		assertEquals(checker.lowestCommonAncestor(list1, list2), expected);
	}

	@ParameterizedTest
	@MethodSource("basicValidProgramProvider")
	void typeCheckPrograms(String text) {
		doTypeCheck(text);
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
			"hello_04.cl",
			"hello_05.cl",
			"hello_06.cl",
			"hello_07.cl",
			"hello_08.cl"
	})
	void typeCheckFile(String f) throws IOException {
		doTypeCheckFromFile("testfiles/" + f);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
			"wrong1.cl",
			"wrong2.cl",
			"wrong3.cl",
			"wrong4.cl",
			"wrong5.cl",
			"wrong6.cl",
			"wrong7.cl",
			"wrong8.cl",
			"wrong9.cl",
			"wrong10.cl",
			"wrong11.cl",
			"wrong12.cl",
			"wrong13.cl",
			"wrong14.cl"
	})
	void typeCheckWrongFile(String f) throws IOException {
		assertThrows(CoolException.class, () -> doTypeCheckFromFile("testfiles/" + f));
	}
	
	@ParameterizedTest
	@MethodSource("invalidProgramProvider")
	void badPrograms(String text)
	{
		assertThrows(CoolException.class, () -> doTypeCheck(text));
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
			Arguments.of("class Main {"),
			Arguments.of("class A { a : B; };"),
			Arguments.of("class A inherits B {};")
		);
	}
	
	// Helper Methods
	
	private void doTypeCheck(String text) {
		runner = testRunner =
				CoolFactory.makeParserRunner(CharStreams.fromString(text));
		testRunner.typecheck();
		assertTrue(true);
	}
	
	/**
	 * Type check the file named in the argument.
	 * @param fileName
	 * @throws IOException
	 */
	private void doTypeCheckFromFile(String fileName) throws IOException {
		runner = testRunner =
				CoolFactory.makeParserRunner(CharStreams.fromFileName(fileName));
		testRunner.typecheck();
		assertTrue(true);
	}
}
