/* Generated By:JJTree: Do not edit this line. ASTForSemicolon.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import parser.*;

import java.io.PrintWriter;

public
class ASTForSemicolon extends SimpleNode {
  public ASTForSemicolon(int id) {
    super(id);
  }

  public ASTForSemicolon(JDCParser p, int id) {
    super(p, id);
  }

  public void process(PrintWriter ostr) {
    ostr.print(";");
  }
}
/* JavaCC - OriginalChecksum=6dd1f30d923f431053fb3f6897d897c3 (do not edit this line) */
