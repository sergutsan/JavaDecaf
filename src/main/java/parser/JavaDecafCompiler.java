package parser;

import ast.ASTCompilationUnit;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.input.BOMInputStream;

/**
 * The main compiler class. Runs the precompiler on an input file then calls the system java compiler on it.
 * Some code modified from JavaCC original parser code
 * @author Sophie Koonin
 */
public class JavaDecafCompiler {
    private static final double VERSION = 1.2;
    private boolean debug = false;

    public static void main(String[] args) throws Exception {
        JavaDecafCompiler decaf = new JavaDecafCompiler();
        decaf.launch(args);
    }

    /**
     * Launcher class - checks command line parameters and calls parsing and
     * compiling methods
     * @param args  - the command line arguments
     */
    public void launch(String[] args){
        if (args.length == 0) {
            printUsage();
            return; //stop
        }
        Boolean parseOnly = false; //indicates Parse Only mode (no Java Compiler)
        /* Command line parameters */
        List<String> argsList = Arrays.asList(args);
        if (argsList.contains("-v") || argsList.contains("-version")) {
            System.out.println("JavaDecaf Compiler version " + VERSION);
            if (argsList.size() == 1) return; //if only arg is -v, don't try and compile
            //TODO change this to read from maven version?
        }
        if (argsList.contains("-p") || argsList.contains("-parse")) {
            parseOnly = true;
            System.out.println("JavaDecaf: Parse only mode enabled");
        }
        if (argsList.contains("-help")) {
            printUsage();
            return; //stop any further execution as there may not be a filename given
        }
        if (argsList.contains("-d") || argsList.contains("-debug")) {
            debug = true;
        }


        long startTime = System.nanoTime();
        String filename = args[args.length-1]; //last item in args is the filename
        File inputFile = new File(filename);
        PrintWriter ostr = null;

        if (argsList.contains("-c") || argsList.contains("-console")) {
            parseOnly = true;
            ostr = new PrintWriter(System.out);
        }
        System.out.println("JavaDecaf: Compiling file '" + filename + "'...");
        String javaFile = crossCompileFromJdcToJava(inputFile, ostr);
        long endTime = System.nanoTime();
        String returnMessage = "";
        if (javaFile != null) {
            if (parseOnly) { //print success message and finish
               returnMessage += "JavaDecaf: Parse completed in " + ((endTime - startTime) / 1000000) + " ms";
            } else {
                try {
                    compileJavaFile(javaFile); //call the java compiler
                    returnMessage += "JavaDecaf: Compilation finished in " + ((endTime - startTime) / 1000000) + " ms";  //only print if successfully compiled
                } catch (CompilerException e) {
                    returnMessage += e.getMessage();
                }
            }
        } else {
            returnMessage += "JavaDecaf: Compilation completed with errors";
        }
        System.out.println(returnMessage);


    }

    /**
     * Call the JavaCC parser on the file from args[0] to convert the JavaDecaf code to true Java.
     * Use the name of the JDC file for the name of the Java class, and check its validity as a Java classname.
     * @param inputFile the file to be used as input, to become Java class name
     * @param ostr the PrintWriter to print to (file or console)
     * @return the filename of the converted java file, null if something goes wrong
     */
    public String crossCompileFromJdcToJava(File inputFile, PrintWriter ostr) {
        JDCParser parser;
        ASTCompilationUnit parseTree;
        String className = "";
            try {
		    String filename = inputFile.getName();
		    int index = filename.indexOf("."); //get the index of the full stop for substring
		    className = filename.substring(0, index); //get the name of the class from the filename (before extension)
		    checkValidJavaDecafFileName(className);
                if (ostr == null) {
                    ostr = new PrintWriter(new FileWriter(className + ".java"));
                }
                parser = new JDCParser(new BOMInputStream(new FileInputStream(inputFile), false), className, "UTF-8");
                if (debug) {
                    parser.enable_tracing(); //enable debugging mode if requested
                }
                parseTree = parser.CompilationUnit();
                parseTree.process(ostr); //run the parser and write result on disk in the absence of errors
                if (parser.hasWarnings()) {
                    for (String warning: parser.getWarningList()) {
                        System.err.println(warning);
                    }
                }
                if (parser.hasErrors()) {
                    for (String ex: parser.getErrorList()) {
                        System.err.println(ex);
                    }
                    return null; //don't return successfully
                }
                ostr.close(); // FIXME: should this writer be closed before looking at the errors to ensure it is always closed? -- SG
                return className + ".java"; //return the finished filename to signal successful compilation
            } catch  (StringIndexOutOfBoundsException e) { //Bad filename
                System.out.println("Error: Please make sure your file has the extension .jdc");
            } catch (ParseException e) { //This shouldn't happen, but catch anyway
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                    System.out.println("Error: File " + inputFile + " not found.");
            } catch (IOException e) {
                System.out.println("Error creating file " + inputFile.getName() + ".java");
            }catch (TokenMgrError e) {
                System.out.println(e.getMessage());
                if (e.errorCode != TokenMgrError.LEXICAL_ERROR) e.printStackTrace(); //only print stack trace if error is not lexical (i.e. problem with compiler)
            }catch (Exception ex) {
                System.out.println("An internal error has occurred in the compiler:");
                ex.printStackTrace();
            }
        return null;
    }

    /**
     * Checks that the name of the file is adequate, e.g.:
     *   - Starts with a capital letter
     *   - Does not contain spaces
     * 
     * @throws ClassNameParseException if the name of the file is not valid. 
     */
    private void checkValidJavaDecafFileName(String s) throws ClassNameParseException {
	  if (Character.isDigit(s.charAt(0))) { //Check that first char of file name is not digit
		throw new ClassNameParseException("File names in JavaDecaf cannot begin with a number. " +
							    "Please rename the file.");
	  } 
	  if (Character.isLowerCase(s.charAt(0))) { //Check that first char is uppercase
		throw new ClassNameParseException("File names in JavaDecaf must begin with a capital letter. " +
							    "Please rename the file.");
	  }
	  for (char c: s.toCharArray()) {
		if (!isValidCharacterForJavaDecafFileName(c)) {
		    throw new ClassNameParseException("File names in JavaDecaf must be composed only of letters, " + 
								  "numbers, and underscores. Please rename the file.");
		}
	  }
    }

    /**
     * Returns true if the character is a letter, number, or underscore, false otherwise. 
     */
    private boolean isValidCharacterForJavaDecafFileName(char c) {
	  if (Character.isLetter(c) || Character.isDigit(c) || c == '_') {
		return true;
	  } else {
		return false;
	  }
    }

    /**
     * Run the system Java compiler on a java class.
     * @param filename the name of the file to compile
     */
    public void compileJavaFile(String filename) throws CompilerException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); //get the local system java compiler
        if (compiler == null) {
            System.setProperty("java.home",System.getenv("JAVA_HOME")); //this should work on BBK lab computers
            compiler = ToolProvider.getSystemJavaCompiler(); //set it again
            if (compiler == null) { //if still no compiler found
                 throw new CompilerException("Unable to find system Java compiler. Please check your environment variables, or use the alternative version of JavaDecaf provided.");
            }
        }
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null); //get file manager
        Iterable<? extends JavaFileObject> fileToCompile = fileMgr.getJavaFileObjects(filename); //init file to compile from filename
        List<String> argOptions = Arrays.asList("-cp", "."); //command line options - set classpath to current working directory
        JavaCompiler.CompilationTask compTask = compiler.getTask(null, fileMgr, null, null, null, fileToCompile); //init compilation task with file mgr and file to compile
        compTask.call(); //compile the file
        // TODO: if compTask.call() returns true, may execute resulting CLASS
	//       this may require to add another CMD flag for three possible behaviours: 
	//       compile and (if no errors) execute, only compile to CLASS, only pre-compile into Java
    }

    /**
     * Print the command line usage
     */
    public void printUsage(){
        String usage = "usage: javadecaf [options] filename" +
                "\n  options:"+
                "\n  -p, -parse      Parse-only mode - disable Java compiler stage" +
                "\n  -v, -version    Display version number" +
                "\n  -c, -console    Change output mode to console, not file (enables parse-only mode)" +
                "\n  -d, -debug      Enable debugging mode - show parser trace" +
                "\n  -help           Show help";

        System.out.println(usage);
    }

}

