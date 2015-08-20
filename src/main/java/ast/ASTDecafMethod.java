/* Generated By:JJTree: Do not edit this line. ASTDecafMethod.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import parser.*;

import java.io.PrintWriter;

public
class ASTDecafMethod extends SimpleNode {
    public ASTDecafMethod(int id) {
        super(id);
    }

    public ASTDecafMethod(JDCParser p, int id) {
        super(p, id);
        indentationLevel = 1;
    }

    public void process(PrintWriter ostr){
        String methodDec = NodeUtils.EOL + NodeUtils.EOL + NodeUtils.INDENTATION + "private static";
        ostr.print(methodDec);
        super.process(ostr);
    }
}
/* JavaCC - OriginalChecksum=3b999602ca9d0709cb617b7e927cfe6f (do not edit this line) */
