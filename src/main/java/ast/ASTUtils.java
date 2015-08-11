package main.java.ast;

import main.java.parser.JDCParser;
import main.java.parser.StyleWarnings;
import main.java.parser.Token;

/**
 * Utilities class for AST nodes.
 * @author Sophie Koonin
 * */
public class ASTUtils {
    /**
     * The number of spaces to indent
     */
    private static final int INDENTATION_SPACES = 4;
    /**
     * Standard 4-space indentation in String form
     */
    public static final String INDENTATION = "    ";

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
                case "readLine":
                    currentToken.image = "input.next"; //input is Scanner
                    break;
                case "readInt":
                    currentToken.image = "input.nextInt";
                    break;
                case "readDouble":
                    currentToken.image = "input.nextDouble";
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
        if ((isNewline(t) && node.isDecafClass()) || isComment(t)) {

            int indentationLevel = node.getIndentationLevel();
            int timesToIndent = ASTUtils.INDENTATION_SPACES * indentationLevel;

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
                    sT.specialToken = new Token(0, "\n");
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
            if (countToken.specialToken != null && countToken.specialToken.image.equals(" ")) {
                indentationCount++;
            }
            countToken = countToken.specialToken;
        }
        /*
        If this is JavaDecaf code, everything will be indented 1 less than normal Java in methods, or 2 less in main method
         - need to account for this to prevent unnecessary warnings
         */
        if (node.isDecafClass()) {
            if (!node.isDecafMethod()){
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
     * Iterates through specialTokens to see if there is a newline at the end.
     * Will return true if specialToken is null (this should only happen at the beginning of a class or DecafBlock)
     * @param t the token in question
     * @return true if the token contains a newline specialToken or a null specialToken, otherwise false
     */
    public static boolean isNewline(Token t){
        if (t.specialToken == null) return true;
        Token sT = t;
        while (sT.specialToken != null){
            sT = sT.specialToken;
        }
        return (sT.image.equals("\n"));
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
