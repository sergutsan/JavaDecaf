package unittests;

import main.java.parser.*;
import org.junit.*;

/**
 * Unit tests for name exceptions in JDCParser
 */
public class TestNameExceptions {

    @Test(expected = MethodNameParseException.class)
    public void testCheckMethodName() throws MethodNameParseException {
        Token t = Token.newToken(0, "Method");
        JDCParser.checkMethodName(t);
    }

    @Test(expected = VariableNameParseException.class)
    public void testCheckVariableName() throws VariableNameParseException {
        Token t = Token.newToken(0, "Variable");
        JDCParser.checkVariableName(t);
    }

    @Test(expected = ClassNameParseException.class)
    public void testCheckClassName() throws ClassNameParseException {
        Token t = Token.newToken(0, "myclass");
        JDCParser.checkClassName(t);
    }

    @Test
    public void testCheckMethodNameWithLegalMethodName() throws MethodNameParseException {
        Token t = Token.newToken(0, "method");
        JDCParser.checkMethodName(t);
    }

    @Test
        public void testCheckVariableNameWithLegalVariableName() throws VariableNameParseException {
        Token t = Token.newToken(0, "variable");
        JDCParser.checkVariableName(t);
    }

    @Test
    public void testCheckVariableNameWithLegalConstantName() throws VariableNameParseException {
        Token t = Token.newToken(0, "VARIABLE");
        JDCParser.checkVariableName(t);
    }

    @Test
    public void testCheckClassNameWithLegalClassName() throws ClassNameParseException {
        Token t = Token.newToken(0, "MyClass");
        JDCParser.checkClassName(t);
    }

}
