package ast;


/* JJT: 0.2.2 */
import parser.JDCParser;


import java.io.*;

public class ASTDecafMain extends ClosingBraceSimpleNode {

  ASTDecafMain(int id) {
    super(id);
  }

    public ASTDecafMain(JDCParser p, int id) {
        super(p, id);
    }

  public void process (PrintWriter ostr) {
      decafMain = true;
      String mainDec = "public static void main(String[] args) {";
      ostr.print(mainDec);

      super.process(ostr);
      decafMain = false;
  }

}
