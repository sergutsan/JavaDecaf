package main.java.ast;

import main.java.parser.JDCParser;

import java.io.PrintWriter;

/**
 * Superclass of conditional loops.
 */
public class ConditionalClosingBraceSimpleNode extends ClosingBraceSimpleNode implements IndentationContainer {
    public ConditionalClosingBraceSimpleNode(int id) {
        super(id);
    }

    public ConditionalClosingBraceSimpleNode(JDCParser p, int id){
        super(p, id);
    }

    public void process(PrintWriter ostr){
        setIndentationLevel();
        if (jjtGetParent() instanceof IndentationContainer){
            begin = ASTUtils.indent(begin, this);
        }
        super.process(ostr);

    }
}
