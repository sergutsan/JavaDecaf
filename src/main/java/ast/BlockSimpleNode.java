package main.java.ast;

import main.java.parser.JDCParser;

/**
 * Superclass of Block nodes
 */
public class BlockSimpleNode extends SimpleNode {

    public BlockSimpleNode(int i) {
        super(i);
    }

    public BlockSimpleNode(JDCParser p, int i) {
        super(p, i);
        indent = false;
    }
}
