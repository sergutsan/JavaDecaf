package main.java.parser;

/**
 * Class containing static methods for throwing out style warnings.
 * @author Sophie Koonin
 */
public class StyleWarnings {

    /**
     * Create a warning message with the token image, line and column of the token, and add it to the parser's
     * warning list.
     * @param t - the offending token
     * @param parser - the parser in use
     * @param message - the warning message
     */
    public static void generateWarning(Token t, JDCParser parser, String message) {
        String warning = "Warning: encountered \"" + t.image + "\" at line " + t.endLine + ", column " + t.endColumn +
                ":" + message;
        parser.addWarning(warning);
    }

}
