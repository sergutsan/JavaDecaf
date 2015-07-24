package main.java.ast;

import main.java.parser.JDCParser;

import java.io.PrintWriter;

/**
 * Superclass of conditional loops.
 */
public class ConditionalClosingBraceSimpleNode extends ClosingBraceSimpleNode {
    public ConditionalClosingBraceSimpleNode(int id) {
        super(id);
    }

    public ConditionalClosingBraceSimpleNode(JDCParser p, int id){
        super(p, id);
    }

    public void process(PrintWriter ostr){
        setIndentationLevel();
        if (jjtGetParent() instanceof ClosingBraceSimpleNode || jjtGetParent() instanceof BlockSimpleNode){
            begin = ASTUtils.indent(begin, this);
        }
        super.process(ostr);

    }
}
