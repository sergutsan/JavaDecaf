package main.java.ast;

import main.java.parser.JDCParser;
import main.java.parser.Token;

import java.io.PrintWriter;

/**
 * This class deals with all the substitutions in the blocks of methods, loops etc.
 * @author Sophie Koonin
 */
public class StatementVariableSimpleNode extends SemicolonSimpleNode implements Indentable {
    public StatementVariableSimpleNode(int id) {
        super(id);
    }

    public StatementVariableSimpleNode(JDCParser p, int id){
        super(p, id);
    }

    /**
     * Check through tokens to see if any substitutions are needed, then call superclass method
     * to process content.
     * @param ostr the output stream to print to.
     */
    public void process(PrintWriter ostr) {
        /* Iterate through tokens checking each one  */
        Token t = begin;
        String prevToken = "";
        while (t != end) {
            t = ASTUtils.checkForSubstitutions(t, prevToken);
            prevToken = t.image;
            t = t.next;
        }
        super.process(ostr);

    }



}
