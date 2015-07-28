package main.java.ast;

import main.java.parser.JDCParser;
import main.java.parser.Token;

import java.io.PrintWriter;

/**
 * This class deals with all the substitutions and indentation in the blocks of methods,
 * loops etc.
 * @author Sophie Koonin
 */
public class StatementVariableSimpleNode extends SimpleNode implements Indentable {
    public StatementVariableSimpleNode(int id) {
        super(id);
    }

    public StatementVariableSimpleNode(JDCParser p, int id){
        super(p, id);
    }

    /**
     *
     * @param ostr the output stream to print to.
     */
    public void process(PrintWriter ostr) {
        setIndentationLevel();

    /* if the parent node is a loop, check indentation of first token */
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
