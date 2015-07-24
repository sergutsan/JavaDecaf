package main.java.ast;

import main.java.ast.ASTUtils;
import main.java.ast.SimpleNode;
import main.java.parser.JDCParser;
import main.java.parser.Token;

import java.io.PrintWriter;

/**
 * Subclass of SimpleNode which adds a closing brace to the end of anything printed.
 * @author Sophie Koonin
 */
public class ClosingBraceSimpleNode extends SimpleNode {

    public ClosingBraceSimpleNode(int i) {
        super(i);
    }
    public ClosingBraceSimpleNode(JDCParser p, int i) {
        super(p, i);
    }

    public void process(PrintWriter ostr) {
        super.process(ostr);

        String indentation = "";
//        int indentationLevel = this;
//        if (this instanceof ASTDecafBlock) {
//            indentationLevel = 1;
//        } else {
//            indentationLevel = ;
//            if (this instanceof ConditionalClosingBraceSimpleNode) {
//                indentationLevel-=1; //prevent for loop closing brace being too indented
//            }
//        }
        for (int i = 0; i<indentationLevel; i++) {
            indentation += ASTUtils.INDENTATION;
        }

        Token closingBrace = Token.newToken(73, indentation + "}");
        closingBrace.specialToken = new Token(0, "\n");
        print(closingBrace,ostr);

    }

}
