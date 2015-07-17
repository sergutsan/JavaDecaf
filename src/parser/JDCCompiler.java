package parser;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main compiler class. Runs the precompiler on an input file then calls the system java compiler on it.
 * Some code modified from JavaCC original parser code
 * @author Sophie Koonin
 */
public class JDCCompiler {
    @Parameter(description="Filename") //Final parameter - the name of the jdc file
    private List<String> filename = new ArrayList<>();

    @Parameter(names = "-p", description = "Parse only mode - no compilation")
    private boolean parseOnly = false;

    @Parameter(names = "-v", description = "Show version")
    private boolean versionEnabled = false;

    @Parameter(names= "-help", description = "Show usage")
    private boolean help;

    private static final double VERSION = 1.0;

    public static void main(String[] args) throws Exception {
        JDCCompiler jdcc = new JDCCompiler();

        String inputFile;
        if (args.length > 0) {
            JCommander paramParser = new JCommander(jdcc,args); //parse command line parameters using JCommander
            jdcc.launch(args[args.length-1]);
        } else {
            System.out.println("Usage: \"javadecaf filename\"");
        }
    }

    /**
     * Launcher class
     * @param inputFile  - the filename from the command line
     */
    public void launch(String inputFile){
        if (versionEnabled) {
            System.out.println("JavaDecaf Compiler version " + VERSION);
        }
        if (parseOnly) {
            System.out.println("Parse only mode enabled");
        }

        long startTime = System.nanoTime();
        String precompiledClass = precompile(inputFile);
        if (precompiledClass != null && !parseOnly) {
            compileJava(precompiledClass);
            long endTime = System.nanoTime();
            System.out.println("JavaDecaf: Compilation finished in " + ((endTime - startTime) / 1000000) + " ms"); //only print if successfully compiled
        }

    }

    /**
     * Call the JavaCC parser on the file from args[0] to convert the JavaDecaf code to true Java.
     * Use the name of the JDC file for the name of the Java class, and check its validity as a Java classname.
     * @param inputFile the name of the file to be used as input, to become Java class name
     * @return the filename of the converted java file, null if something goes wrong
     */
    public String precompile(String inputFile) {
        JDCParser parser;
        ASTCompilationUnit node;
        String className = "";
            try {
                if (Character.isDigit(inputFile.charAt(0))) { //Check that first char of file name is not digit
                    throw new ParseException("Class names in Java cannot begin with a digit. "+
                        "Please rename the file.");
                } else if (Character.isLowerCase(inputFile.charAt(0))) { //Check that first char is uppercase
                    throw new ParseException("Class names in Java must begin with a capital letter. " +
                    "Please rename the file.");
                }
                parser = new JDCParser(new FileInputStream(inputFile));
                int index = inputFile.indexOf("."); //get the index of the full stop for substring
                className = inputFile.substring(0, index); //get the name of the class from the filename (before extension)
                node = parser.CompilationUnit();
                PrintWriter ostr = new PrintWriter(new FileWriter(className+".java"));
                node.process(ostr, className);
                ostr.close();
                return className + ".java"; //return the finished filename to signal successful compilation
            } catch (ParseException e) {
                System.out.println("Errors during compilation: ");
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                    System.out.println("File " + inputFile + " not found.");
            } catch (IOException e) {
                System.out.println("Error creating file " + className + ".java");
            } catch (TokenMgrError e) {
                System.out.println(e.getMessage());
                if (e.errorCode != TokenMgrError.LEXICAL_ERROR) e.printStackTrace(); //only print stack trace if error is not lexical (i.e. problem with compiler)
            }
        return null;
    }

    /**
     * Run the system Java compiler on a java class.
     * @param filename the name of the file to compile
     */
    public void compileJava(String filename) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); //get the local system java compiler
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null); //get file manager
        Iterable<? extends JavaFileObject> fileToCompile = fileMgr.getJavaFileObjects(filename); //init file to compile from filename
        List<String> argOptions = Arrays.asList("-cp", "."); //command line options - set classpath to current working directory
        JavaCompiler.CompilationTask compTask = compiler.getTask(null, fileMgr, null, null, null, fileToCompile); //init compilation task with file mgr and file to compile
        compTask.call(); //compile the file
    }


}

