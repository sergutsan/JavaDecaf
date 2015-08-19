package unittests;

import parser.*;
import org.junit.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Unit tests for name exceptions in JDCParser
 */
public class TestNameExceptions {
    private JDCParser parser;

    @Before
    public void buildUp(){
        parser = new JDCParser(new BufferedInputStream(System.in));
    }

    @Test(expected = MethodNameParseException.class)
    public void testCheckMethodName() throws MethodNameParseException {
        Token t = Token.newToken(0, "Method");
        parser.checkMethodName(t);
    }

    @Test(expected = VariableNameParseException.class)
    public void testCheckVariableName() throws VariableNameParseException {
        Token t = Token.newToken(0, "Variable");
        parser.checkVariableName(t);
    }

    @Test(expected = ClassNameParseException.class)
    public void testCheckClassName() throws ClassNameParseException {
        Token t = Token.newToken(0, "myclass");
        parser.checkClassName(t);
    }

    @Test
    public void testCheckMethodNameWithLegalMethodName() throws MethodNameParseException {
        Token t = Token.newToken(0, "method");
        parser.checkMethodName(t);
    }

    @Test
        public void testCheckVariableNameWithLegalVariableName() throws VariableNameParseException {
        Token t = Token.newToken(0, "variable");
        parser.checkVariableName(t);
    }

    @Test
    public void testCheckVariableNameWithLegalConstantName() throws VariableNameParseException {
        Token t = Token.newToken(0, "VARIABLE");
        parser.checkVariableName(t);
    }

    @Test
    public void testCheckClassNameWithLegalClassName() throws ClassNameParseException {
        Token t = Token.newToken(0, "MyClass");
        parser.checkClassName(t);
    }

}
