package parser;

/**
 * Exception thrown when method name is not acceptable according to Java convention.
 */
public class MethodNameTakenException extends ParseException {
    protected static final String EOL = System.getProperty("line.separator", "\n");

    public MethodNameTakenException(Token t){
        super(initMessage(t));
    }

    /**
     * Initialise the message for this exception with the name of the class
     * @param t the token of the classname
     * @return the error message
     */
    public static String initMessage(Token t) {
        String message = "Error: encountered method name " + t.image + "() at line " + t.beginLine + ", column "+ t.beginColumn + "." + 
		EOL + "The following method names are reserved by Java and cannot be used: " + EOL +
		"clone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait." + EOL; 
        return message;

    }

}
