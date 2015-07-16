package parser;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The main compiler class. Runs the precompiler on an input file then calls the system java compiler on it.
 */
public class JDCCompiler {
    private JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); //get the local system java compiler

    public static void main(String[] args) {

    }

}

