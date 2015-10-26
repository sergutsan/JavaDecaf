package ast;

import parser.JDCParser;
import parser.StyleWarnings;
import parser.Token;

/**
 * Utilities class for AST nodes.
 * @author Sophie Koonin
 * */
public class NodeUtils {
    /**
     * The number of spaces to indent
     */
    private static final int INDENTATION_SPACES = 4;
    /**
     * Standard 4-space indentation in String form
     */
    public static final String INDENTATION = "    ";

    /**
     * This system's newline separator - default to \n if none found
     */
    public static final String EOL = System.getProperty("line.separator", "\n");

    /**
     * Replaces all JavaDecaf method calls with the Java equivalents.
     * To avoid nesting when used with Java method calls such as
     * System.out.System.out.println, checks value of previous token.
     * If it's a full stop, it's likely a Java method call, so no replacement occurs.
     * @param currentToken the token in question
     * @param prevToken the previous token image
     * @return the token, substituted or otherwise
      */

    protected static Token checkForSubstitutions(Token currentToken, String prevToken) {
        if (!prevToken.equals(".")) {
            switch (currentToken.image) {
                case "println":
                    currentToken.image = "System.out.println";
                    break;
                case "print":
                    currentToken.image = "System.out.print";
                    break;
                default:
                    break;
            }
        }

        return currentToken;
    }

    /**
     * Indents a token the correct number of times if it appears within a JavaDecaf class
     * @param t the token to indent
     * @param node the node within which the token appears
     * @return the indented token
     */
    protected static Token indent(Token t, SimpleNode node) {
        if ((isNewline(t,node) && node.isDecafClass()) || isComment(t)) {

            int indentationLevel = node.getIndentationLevel();
            int timesToIndent = NodeUtils.INDENTATION_SPACES * indentationLevel;

            Token sT = Token.newToken(0, " ");
            t.specialToken = sT;
            //sT.next = t;
            for (int i = 0; i < timesToIndent; i++) {
                sT.specialToken = Token.newToken(0, " ");
                /*  Only assign next to sT if i is not the first special token -
                * this is so that the printer knows when to stop printing special tokens */
                if (i > 0) {
                    sT.specialToken.next = sT;
                }
                sT = sT.specialToken;
                if (i == timesToIndent - 1) {
                    sT.specialToken = new Token(0, EOL);
                    sT.specialToken.next = sT;
                    sT = sT.specialToken;
                }
            }

        }

        return t;

    }

    /**
     * Checks the indentation level and generate a warning if not correctly indented.
     * @param parser the parser in use
     * @param t the indented token
     * @param node the current node
     */
    public static void checkIndentation(JDCParser parser, Token t, SimpleNode node) {
        int expectedIndentation = node.getIndentationLevel();
        int indentationCount = 0;
        Token countToken = t;
        while (countToken != null && !isComment(countToken)) {
            if (countToken.specialToken != null) {
                if (countToken.specialToken.image.equals(" ")) {
                    indentationCount++;
                } else if (countToken.specialToken.image.equals("\t")) {
                    indentationCount += 4; //one tab = 4 spaces
                }
            }
            countToken = countToken.specialToken;
        }
        /*
        If this is JavaDecaf code, everything will be indented 1 less than normal Java in methods, or 2 less in main method
         - need to account for this to prevent unnecessary warnings
         */
        if (node.isDecafClass()) {
            if (node.isDecafMain()){
                expectedIndentation-=1;
            }
            expectedIndentation -= 1;
        }
        /* if the actual number of indented spaces doesn't match the indentation level multiplied by the number
        of spaces to indent, add this warning to the warning list */

        if (indentationCount != (expectedIndentation * INDENTATION_SPACES)) {
            String warning = " methods, loops and their contents should be indented by multiples of four spaces, e.g.:" +
                    "\nvoid isLessThan(int number1, int number2) {"+
                    "\n    if (x < y) {" +
                    "\n        println(x + \"is less than\" + y);" +
                    "\n    }" +
                    "\n}";
            StyleWarnings.generateWarning(t, parser, warning);
        }


    }

    /**
     * Version of isNewline that checks type of parent node before calling isNewline(Token)
     * @param t - the token in question
     * @param n - the node containing the token
     * @return true if the token is a new line and the parent is not a ForStatement
     */
    public static boolean isNewline(Token t, SimpleNode n){
        return (!(n.jjtGetParent() instanceof ASTForStatement)) && isNewline(t);
    }

    /**
     * Iterates through specialTokens to see if there is a newline at the end.
     * Will return true if specialToken is null
     * @param t the token in question
     * @return true if the token contains a newline specialToken or a null specialToken, otherwise false
     */
    public static boolean isNewline(Token t){
        if (t.specialToken == null) return true;
        Token sT = t;
        while (sT.specialToken != null){
            sT = sT.specialToken;
        }
        return (sT.image.equals(EOL) || sT.image.equals("\n") || sT.image.equals("\r"));
    }

    /**
     * Checks to see whether a special token is a comment
     * @param specialToken the special token in question
     * @return true if the token is a comment, otherwise false
     */
    public static boolean isComment(Token specialToken){
        return (specialToken != null
                && specialToken.image.length() > 1
                && (specialToken.image.substring(0,2).equals("//")
                || specialToken.image.substring(0,2).equals("/*")));
    }

    /**
     * Checks whether a token contains a comment in its special tokens.
     * @param token the token in question
     * @return true if there is a comment, otherwise false
     */
    public static boolean hasComment(Token token){
        Token tt = token.specialToken;
        while (tt != null) {
            if (isComment(tt)) return true;
            tt = tt.specialToken;
        }
        return false;
    }

    /**
     * Finds a token's attached comment and return it
     * @param token the token in question
     * @return the comment, or null if none is present
     */
    public static Token getComment(Token token, SimpleNode node){
        if (hasComment(token)) {
            Token tt = token;
            while (tt != null) {
                if (isComment(tt)) {
                    return indent(tt, node); //indent the comment
                }
                tt = tt.specialToken;
            }
        }
        return null;
    }
}
