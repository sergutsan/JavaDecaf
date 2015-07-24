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
     * Encapsulate a "loose" block of Decaf code with main method declarations.
     * @param ostr - output stream writer defined in JDCParser
     */
  public void process (PrintWriter ostr) {
      String mainDec = "\n" + ASTUtils.INDENTATION + "public static void main(String[] args) {";
      ostr.print(mainDec);

      super.process(ostr);
  }

}
