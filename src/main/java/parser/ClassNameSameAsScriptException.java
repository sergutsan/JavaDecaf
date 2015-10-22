package parser;

/**
 * Exception thrown when classname is not acceptable according to Java convention.
 */
public class ClassNameSameAsScriptException extends ParseException {
    protected static final String EOL = System.getProperty("line.separator", "\n");

    public ClassNameSameAsScriptException(Token t){
        super(initMessage(t));
    }

    public ClassNameSameAsScriptException(String message) {
        super(message);
    }
    /**
     * Initialise the message for this exception with the name of the class
     * @param t the token of the classname
     * @return the error message
     */
    public static String initMessage(Token t) {
        String message = "Error: encountered class name " + t.image + " at line " + t.beginLine + ", column "+ t.beginColumn +
		". Class names must be different from the name of the main program."+EOL+"They should also be nouns. e.g.:" + EOL +
		"VALID:   class Person (in a file called Company.jdc)" + EOL + 
		"INVALID: class Person (in a file called Person.jdc)" + EOL;
        return message;

    }

}
