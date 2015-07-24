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
        setIndentationLevel();

    /* if the parent node is a loop, check indentation of first token */
        Token t = begin;
        String prevToken = "";
        while (t != end) {
            if (t.specialToken != null && !t.specialToken.image.equals(" ")) {
                if (((jjtGetParent() instanceof ClosingBraceSimpleNode
                        && (!(jjtGetParent() instanceof ConditionalClosingBraceSimpleNode)))
                        && (!(jjtGetParent() instanceof ASTDecafMain)))
                        || jjtGetParent() instanceof BlockSimpleNode) {
                    ASTUtils.checkIndentation(parser, begin, this);
                }
            }
            t = ASTUtils.indent(t, this);

            t = ASTUtils.checkForSubstitutions(t, prevToken);
            print(t, ostr);
            prevToken = t.image;
            t = t.next;
        }
        //manually print last token
        print(end, ostr);

    }



}
