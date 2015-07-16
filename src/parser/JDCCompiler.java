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
        JDCParser parser;
        String className;
        if (args.length == 1) {
            int index = args[0].indexOf("."); //get the index of the full stop for substring
            className = args[0].substring(0,index); //get the name of the class from the filename (before extension)
            try {
                parser = new JDCParser(new FileInputStream(args[0]));
                parser.parse();
            } catch (FileNotFoundException e) {
                System.out.println("File " + args[0] + " not found.");
            }
        } else {
            System.out.println("JavaDecaf Compiler:  Usage is \"javadecaf filename\"");
        }
    }

}

