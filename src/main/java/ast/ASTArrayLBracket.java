/* Generated By:JJTree: Do not edit this line. ASTArrayLBracket.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import parser.*;

import java.io.PrintWriter;

public
class ASTArrayLBracket extends SimpleNode {
  public ASTArrayLBracket(int id) {
    super(id);
  }

  public ASTArrayLBracket(JDCParser p, int id) {
    super(p, id);
  }

  public void process(PrintWriter ostr) {
    ostr.print("[");
  }
}
/* JavaCC - OriginalChecksum=c2476a806fe233cbc643c65837cb8a73 (do not edit this line) */
