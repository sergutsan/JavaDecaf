package parser;

/**
 * Exception thrown in the event of a problem finding the system compiler.
 */
public class CompilerException extends Exception {
    public CompilerException(String message) {
        super(message);
    }

}
