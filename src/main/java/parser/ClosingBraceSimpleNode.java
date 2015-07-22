package main.java.parser;

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
        int indentationLevel = -1;
        Node node = this;
        while(node.jjtGetParent() != null) {
            indentationLevel++;
            node = node.jjtGetParent(); //iterate through parents to find out how indented this should be
        }
        String indentation = "";
        for (int i = 0; i<indentationLevel; i++){
            indentation += INDENTATION_SPACES;
        }
        Token closingBrace = Token.newToken(73, indentation + "}");
        closingBrace.specialToken = new Token(0, "\n");
        print(closingBrace,ostr);

    }

}
