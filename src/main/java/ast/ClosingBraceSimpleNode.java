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
    private static final String INDENTATION_SPACES = "    ";

    public ClosingBraceSimpleNode(int i) {
        super(i);
    }
    public ClosingBraceSimpleNode(JDCParser p, int i) {
        super(p, i);
    }

    public void process(PrintWriter ostr) {
        super.process(ostr);

        int indentationLevel = ASTUtils.getIndentationLevel(this);

        String indentation = "";
        for (int i = 0; i<indentationLevel; i++){
           indentation += INDENTATION_SPACES;
        }
        Token closingBrace = Token.newToken(73, indentation + "}");
        closingBrace.specialToken = new Token(0, "\n");
        print(closingBrace,ostr);

    }

}
