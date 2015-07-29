/* Generated By:JJTree: Do not edit this line. ASTStatementExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package main.java.ast;

import main.java.parser.JDCParser;
import main.java.parser.Token;

import java.io.PrintWriter;

public
class ASTStatementExpression extends SimpleNode implements Indentable, LastTokenEater {
  public ASTStatementExpression(int id) {
    super(id);
  }

  public ASTStatementExpression(JDCParser p, int id) {
    super(p, id);
  }

  public void process(PrintWriter ostr) {
          /* Iterate through tokens checking each one  */
    Token t = begin;
    String prevToken = "";
    while (t != end) {
      t = ASTUtils.checkForSubstitutions(t, prevToken);
      prevToken = t.image;
      t = t.next;
    }


    super.process(ostr);
  }
}
/* JavaCC - OriginalChecksum=e873b876e7f466c17fe124d220aedcae (do not edit this line) */
