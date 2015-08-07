package main.java.parser;

/**
 * Exception thrown when method name is not acceptable according to Java convention.
 */
public class MethodNameException extends Exception {
    protected static final String EOL = System.getProperty("line.separator", "\n");

    public MethodNameException(Token t){
        super(initMessage(t));
    }

    /**
     * Initialise the message for this exception with the name of the class
     * @param t the token of the classname
     * @return the error message
     */
    public static String initMessage(Token t) {
        String message = "Error: encountered method name " + t.image + " at line " + t.beginLine + ", column "+ t.beginColumn +
                ". Method names should begin with a lower case letter, with all subsequent words in CamelCase. They should also be verbs. e.g.:" + EOL +
                "getNumber()" + EOL + "update()" + EOL + "replaceAllEvenNumbers()" + EOL + "reverseString()" + EOL + "increment()";
        return message;

    }

}
