package unittests;

import org.junit.*;
import parser.ParseException;

import static org.junit.Assert.*;
/**
 * Tests for individual methods within parser.ParseException.
 */
public class TestParseException {

    @Test
    public void testIsReservedKeyword(){
        for (int i = 9; i < 59; i++){
            assertTrue(ParseException.isReservedKeyword(i));
        }
    }

    @Test
    public void testIsIdentifier(){
        assertTrue(ParseException.isIdentifier(67));
    }

    @Test
    public void testIsLiteral(){
        for (int i = 59; i<67; i++) {
            assertTrue(ParseException.isLiteral(i));
        }
    }

    @Test
    public void testIsPrimitive() {
        assertTrue(ParseException.isPrimitive(34));//int
        assertTrue(ParseException.isPrimitive(12));//byte
        assertTrue(ParseException.isPrimitive(15));//char
        assertTrue(ParseException.isPrimitive(21));//double
        assertTrue(ParseException.isPrimitive(27));//long
        assertTrue(ParseException.isPrimitive(45));//short




    }

}
