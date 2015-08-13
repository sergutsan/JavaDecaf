package ast;

import parser.JDCParser;

/**
 * Superclass of conditional loops.
 * @author Sophie Koonin
 */
public class ConditionalSimpleNode extends SimpleNode {
    public ConditionalSimpleNode(int id) {
        super(id);
    }

    public ConditionalSimpleNode(JDCParser p, int id){
        super(p, id);
    }

}
