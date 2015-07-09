package parser;

/**
 * Static utilities used by parser and associated classes.
 * @author Sophie Koonin
 */
public class JavaDecafUtils {

    /**
     * Replace all JavaDecaf method calls with the Java equivalents.
     * To avoid nesting when used with Java method calls, e.g.
     * System.out.System.out.println, check value of previous token
     * If it's a full stop, it's likely a Java method call, so no replacement.
     */
    public static void checkForSubstitutions(Token t, String prevtoken){
        if (prevtoken.equals(".")) {
            switch (t.image) {
                case "println":
                    t.image = "System.out.println";
                    break;
                case "print":
                    t.image = "System.out.print";
                    break;
                case "readLine":
                    t.image = "input.readLine"; //input is Scanner
                    break;
                case "readInt":
                    t.image = "input.readInt";
                    break;
            }
        }
    }
}
