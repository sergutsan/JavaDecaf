package main.java.ast;


/* JJT: 0.2.2 */

import main.java.parser.JDCParser;
import main.java.parser.JDCParserConstants;
import main.java.parser.Token;

import java.io.*;

public class ASTDecafBlock extends ClosingBraceSimpleNode {

  ASTDecafBlock(int id) {
    super(id);
  }

    public ASTDecafBlock(JDCParser p, int id) {
        super(p, id);
    }
    /**
     * Encapsulate a "loose" block of Decaf code with class and main method declarations.
     * @param ostr - output stream writer defined in JDCParser
     */
  public void process (PrintWriter ostr) {
      String classDec = "import java.util.Scanner;\n" +  //Assign the class/main method encapsulation code
              "public class " + parser.getClassName() + " { \n" + ASTUtils.INDENTATION +
              "private Scanner input = new Scanner(System.in);\n"  + ASTUtils.INDENTATION +//init Scanner for reading from stdin
              "public static void main(String[] args) {";
      ostr.print(classDec);

      super.process(ostr);
      ostr.print("\n}"); //print final closing brace of class
  }

}
