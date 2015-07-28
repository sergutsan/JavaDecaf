package main.java.ast;

import main.java.parser.JDCParser;

import java.io.PrintWriter;

/**
 * Superclass of conditional loops.
 */
public class ConditionalSimpleNode extends SimpleNode implements Indentable{
    public ConditionalSimpleNode(int id) {
        super(id);
    }

    public ConditionalSimpleNode(JDCParser p, int id){
        super(p, id);
    }

    public void process(PrintWriter ostr){
        setIndentationLevel();
        super.process(ostr);

    }
}
