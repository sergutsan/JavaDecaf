package main.java.ast;

import main.java.parser.JDCParser;

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
}
