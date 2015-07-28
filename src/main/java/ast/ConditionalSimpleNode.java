package main.java.ast;

import main.java.parser.JDCParser;

import java.io.PrintWriter;

/**
 * Superclass of conditional loops.
 * @author Sophie Koonin
 */
public class ConditionalSimpleNode extends SimpleNode implements Indentable{
    public ConditionalSimpleNode(int id) {
        super(id);
    }

    public ConditionalSimpleNode(JDCParser p, int id){
        super(p, id);
    }

}
