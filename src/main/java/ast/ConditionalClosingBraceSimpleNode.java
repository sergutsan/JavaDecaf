package main.java.ast;

import main.java.parser.JDCParser;

import java.io.PrintWriter;

/**
 * Superclass of conditional loops.
 */
public class ConditionalClosingBraceSimpleNode extends ClosingBraceSimpleNode implements Indentable{
    public ConditionalClosingBraceSimpleNode(int id) {
        super(id);
    }

    public ConditionalClosingBraceSimpleNode(JDCParser p, int id){
        super(p, id);
    }

    public void process(PrintWriter ostr){
        setIndentationLevel();
        super.process(ostr);

    }
}
