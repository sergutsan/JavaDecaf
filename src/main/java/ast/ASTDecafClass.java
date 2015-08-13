/* Generated By:JJTree: Do not edit this line. ASTDecafClass.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package ast;

import parser.*;

import java.io.PrintWriter;
import static ast.ASTUtils.EOL;

public
class ASTDecafClass extends ClosingBraceSimpleNode {
    public ASTDecafClass(int id) {
        super(id);
    }

    public ASTDecafClass(JDCParser p, int id) {
        super(p, id);
        indent = false;
        decafClass = true;
    }

    public void process(PrintWriter ostr) {
        String classDec = EOL + "import java.util.Scanner;" + EOL + EOL +  //Assign the class/main method encapsulation code
                "public class " + parser.getClassName() + " { " + EOL + ASTUtils.INDENTATION +
                "private static Scanner input = new Scanner(System.in);" + EOL + EOL  + ASTUtils.INDENTATION; //init Scanner for reading from stdin

        ostr.print(classDec);
        super.process(ostr);
    }


}
/* JavaCC - OriginalChecksum=ba592f10a30b87d2e827995b8d4e8c28 (do not edit this line) */
