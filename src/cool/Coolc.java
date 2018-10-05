package cool;

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
 * Copyright ©2018 Gary F. Pollice
 *******************************************************************************/


import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import cool.ast.ASTPrinter;
import cool.lexparse.CoolParser;
import cool.symbol.TableManager;
import cool.utility.CoolRunnerImpl;
import static cool.Coolc.Phase.*;
import static cool.utility.CoolFactory.*;
/**
 * Compiler tool for the Cool-W programming language.
 * @version Aug 17, 2018
 */
public class Coolc
{
    public static enum Phase {PARSE, AST, SEMANTIC, COMPILE};
    private List<String> fileNames;
    private String outputDirectory;
    private Phase phase = COMPILE;
    private boolean displayParseTree;
    private boolean displayGUI;
    private boolean displayAST;
    private boolean displayTables;
    private boolean displaySource;
    private final String PACKAGE = "cool"; 
    private CoolRunnerImpl runner;
    private Map<String, byte[]> bytecode;
    
    /**
     * Description
     */
    public Coolc()
    {
        fileNames = new ArrayList<String>();
        outputDirectory = ".";        // default
        phase = COMPILE;
        displayParseTree = false;
        displayGUI = false;
        displayAST = false;
        displayTables = false;
        displaySource = false;
        bytecode = null;
    }
    
    public void executeTool(String[] args) throws Exception
    {
        parseArgs(args);
        StringBuilder coolText = new StringBuilder();
        if (fileNames.size() > 0) {
            for (String fn : fileNames) {
                coolText.append("########################################\n"
                        + "#\n# File: " +fn 
                        + "\n#\n########################################\n");
                coolText.append(new Scanner(new File(fn)).useDelimiter("\\A").next());
            }
            processProgram(coolText);
            postProcess(coolText);
        }
    }
    
    private void processProgram(StringBuilder coolText) throws Exception
    {
        runner = makeParserRunner(CharStreams.fromString(coolText.toString()));
        switch (phase) {
            case PARSE: runner.parse(); break;
            case AST: runner.createAST(); break;
            case SEMANTIC: runner.typecheck(); break;
            case COMPILE: 
                bytecode = runner.compile(); 
                writeOutput();
                break;
        }
    }
    
    private void writeOutput() throws Exception
    {
        if (bytecode != null) {
            for (String s : bytecode.keySet()) {
                String classFilePath =  outputDirectory + "/cool/" + s + ".class";
                FileOutputStream fos = new FileOutputStream(classFilePath);
                fos.write(bytecode.get(s));
                fos.close();
            }
        }
    }
    
    private void FileOutputStream(String string)
    {
        // TODO Auto-generated method stub
        
    }

    private void postProcess(StringBuilder coolText)
    {
        // TODO: write the bytecode if any
        if (displaySource) {
            System.out.println("\n------------------------------\nSource:\n\n");
            System.out.println(coolText.toString());
        }
        if (displayTables) {
            System.out.println("\n------------------------------\nSymbol tables:\n\n");
            System.out.println(TableManager.getInstance().toString());
        }
        
        if (displayParseTree) {
            System.out.println("\n------------------------------\nParse tree:\n\n");
            System.out.println(runner.getParseTree().toStringTree(runner.getParser()));
        }
//        
//        if (displayGUI) {
//            showGUI(runner.getParser(), runner.getParseTree());
//        }
        
        if (displayAST) {
            System.out.println("\n------------------------------\nAbstract Syntax Tree:\n\n");
            ASTPrinter printer = new ASTPrinter();
            System.out.println(runner.getAst().accept(printer));
        }
    }
    
    private void showGUI(CoolParser parser, ParserRuleContext tree) {
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
    
    private void showHelp()
    {
        System.out.println("Usage: coolc [ options ] [ sourcefiles ]\n"
            + "Options: \n"
            + "    -o outputdirectory\n"
            + "    -h\n"
            + "    -p phase (parse|ast|semantic)\n"
            + "    -d internals (pt|gui|ast|tables|source)");
    }
    
    /**
     * Parse the command line arguments.
     * @param args
     */
    private boolean parseArgs(String[] args)
    {
        boolean argsOK = true;
        boolean optionsDone = false;
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            switch (s) {
            case "-o":
                outputDirectory = args[++i];
                break;
            case "-h":
                showHelp();
                break;
            case "-p":
                switch (args[++i]) {
                    case "parse": phase = PARSE; break;
                    case "ast": phase = AST; break;
                    case "semantic": phase = SEMANTIC; break;
                    default: throw new RuntimeException("Invalid phase");
                }
                break;
            case "-d":
                switch (args[++i]) {
                    case "pt": displayParseTree = true; break;
                    case "gui": displayGUI = true; break;
                    case "ast": displayAST = true; break;
                    case "tables": displayTables = true; break;
                    case "source": displaySource = true; break;
                    default: throw new RuntimeException("Invalid display");
                }
                break;
            default: 
                fileNames.add(args[i]);
                break;
            }
        }
        return argsOK;
    }

    /**
     * Description
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
        Coolc tool = new Coolc();
        for (String s : args) {
        	System.out.println(s);
        }
        tool.executeTool(args);
    }

}
