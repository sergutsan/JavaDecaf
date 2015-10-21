package ast;

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
	  ClosingBraceSimpleNode.closeBracket(ostr, indentationLevel);
    }

    public static void closeBracket(PrintWriter ostr, int indentLevel) {
        String indentation = "";

        for (int i = 0; i < indentLevel; i++) {
            indentation += NodeUtils.INDENTATION;
        }

        Token closingBrace = Token.newToken(73, indentation + "}");
        closingBrace.specialToken = new Token(0, NodeUtils.EOL);
        print(closingBrace,ostr);
    }

}
