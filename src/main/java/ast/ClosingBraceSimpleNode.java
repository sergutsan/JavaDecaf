package ast;

import ast.ASTUtils;
import ast.SimpleNode;
import parser.JDCParser;
import parser.Token;

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

        for (int i = 0; i<indentationLevel; i++) {
            indentation += ASTUtils.INDENTATION;
        }

        Token closingBrace = Token.newToken(73, indentation + "}");
        closingBrace.specialToken = new Token(0, ASTUtils.EOL);
        print(closingBrace,ostr);

    }

}
