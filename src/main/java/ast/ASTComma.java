/* Generated By:JJTree: Do not edit this line. ASTComma.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import parser.*;

import java.io.PrintWriter;

public
class ASTComma extends SimpleNode {
  public ASTComma(int id) {
    super(id);
  }

  public ASTComma(JDCParser p, int id) {
    super(p, id);
  }

  public void process(PrintWriter ostr) {
    ostr.print(",");
  }
}
/* JavaCC - OriginalChecksum=c6c47175b999c621eaa750f0554ca2d5 (do not edit this line) */
