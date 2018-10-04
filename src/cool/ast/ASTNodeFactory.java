package cool.ast;

public class ASTNodeFactory {
	public static CoolText makeCoolText() { return new Cooltext(); }
	public static Type makeType(String className) { return new Type(className); }
	// ...
	
	public static Terminal makeTypeTerminal(Token t) { return new Terminal(t, tType); }

}
