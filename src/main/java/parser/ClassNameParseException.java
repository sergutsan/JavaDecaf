package main.java.parser;

/**
 * Exception thrown when classname is not acceptable according to Java convention.
 */
public class ClassNameParseException extends ParseException {
    protected static final String EOL = System.getProperty("line.separator", "\n");

    public ClassNameParseException(Token t){
        super(initMessage(t));
    }

    /**
     * Initialise the message for this exception with the name of the class
     * @param t the token of the classname
     * @return the error message
     */
    public static String initMessage(Token t) {
        String message = "Error: encountered class name " + t.image + " at line " + t.beginLine + ", column "+ t.beginColumn +
                ". Class names should begin with a capital letter, and be written in CamelCase. They should also be nouns. e.g.:" + EOL +
                "class Person" + EOL + "class FractionCalculator" + EOL + "class FlyingAnimal";
        return message;

    }

}
