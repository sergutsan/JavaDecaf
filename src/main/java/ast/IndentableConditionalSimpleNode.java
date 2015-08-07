package main.java.ast;

import main.java.parser.JDCParser;

import java.io.PrintWriter;

/**
 * Superclass of conditional loops which need indenting
 * @author Sophie Koonin
 */
public class IndentableConditionalSimpleNode extends ConditionalSimpleNode implements Indentable{
    public IndentableConditionalSimpleNode(int id) {
        super(id);
    }

    public IndentableConditionalSimpleNode(JDCParser p, int id){
        super(p, id);
    }

}
