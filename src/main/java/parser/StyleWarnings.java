package main.java.parser;

/**
 * Class containing static methods for throwing out style warnings.
 * @author Sophie Koonin
 */
public class StyleWarnings {
    private static final int INDENTATION_SPACES = 4;

    public static void checkIndentation(JDCParser p, Token t, int indentationLevel) {
        int indentationCount = 0;
        Token countToken = t;
        while (countToken != null) {
            if (countToken.specialToken.image.equals(" ")) {
                indentationCount++;
            }
            countToken = countToken.specialToken;
        }
        /* if the actual number of indented spaces doesn't match the indentation level multiplied by the number
        of spaces to indent, add this warning to the warning list */

        if (indentationCount != (indentationLevel * INDENTATION_SPACES)) {
            String warning = "Warning - line " + t.endLine + ", column " + t.endColumn +
                    ": contents of loop should be indented by four spaces. e.g.:" +
                    "\nif (x == y) {" +
                    "\n    println(x);"+
                    "\n}";
            p.addWarning(warning);
        }


    }

}
