package main.java.ast;

import main.java.parser.JDCParser;
import main.java.parser.Token;

import java.io.PrintWriter;

/**
 * This class deals with all the substitutions and indentation in the blocks of methods,
 * loops etc.
 * @author Sophie Koonin
 */
public class StatementVariableSimpleNode extends SimpleNode {
    public StatementVariableSimpleNode(int id) {
        super(id);
    }

    public StatementVariableSimpleNode(JDCParser p, int id){
        super(p, id);
    }

    public void process(PrintWriter ostr) {
    /* if the parent node is a loop, check indentation of first token */
        Token t = begin;
        String prevToken = "";
        while (t != end) {
            if (t.specialToken != null && !t.specialToken.image.equals(" ")) {
                if (jjtGetParent() instanceof ClosingBraceSimpleNode) {
                    ASTUtils.checkIndentation(parser, begin, ASTUtils.getIndentationLevel(this));
                }
            }
            // t = ASTUtils.indent(t, this);

            t = ASTUtils.checkForSubstitutions(t, prevToken);
            print(t, ostr);
            prevToken = t.image;
            t = t.next;
        }
        //manually print last token
        print(end, ostr);
//    t.image=";";
//    print(t, ostr); //print last semicolon
    }



}
