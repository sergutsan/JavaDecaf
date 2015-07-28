package main.java.ast;

import main.java.parser.JDCParser;

import java.io.PrintWriter;

/**
 * Subclass of nodes which need a final semicolon printing at the end
 */
public class SemicolonSimpleNode extends SimpleNode {

    public SemicolonSimpleNode(int i) {
        super(i);
    }

    public SemicolonSimpleNode(JDCParser p, int i) {
        super(p, i);
    }

    public void process(PrintWriter ostr) {
        super.process(ostr);

        ostr.print(";");
    }
}
