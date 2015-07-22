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
            if (countToken.specialToken != null && countToken.specialToken.image.equals(" ")) {
                indentationCount++;
            }
            countToken = countToken.specialToken;
        }
        /* if the actual number of indented spaces doesn't match the indentation level multiplied by the number
        of spaces to indent, add this warning to the warning list */

        if (indentationCount != (indentationLevel * INDENTATION_SPACES)) {
            String warning = "Warning - \":" + t.image + "\", line " + t.endLine + ", column " + t.endColumn +
                    ": methods, loops and their contents should be indented by multiples of four spaces, e.g.:" +
                    "\nvoid isLessThan(int number1, int number2) {"+
                    "\n    if (x < y) {" +
                    "\n        println(x + \"is less than\" + y);" +
                    "\n    }" +
                    "\n}";
            p.addWarning(warning);
        }


    }

}
