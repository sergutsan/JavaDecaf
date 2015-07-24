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

        /* Start the loop at 1 to prevent closing braces being too indented */
        for (int i = 1; i<indentationLevel; i++) {
            indentation += ASTUtils.INDENTATION;
        }

        Token closingBrace = Token.newToken(73, indentation + "}");
        closingBrace.specialToken = new Token(0, "\n");
        print(closingBrace,ostr);

    }

}
