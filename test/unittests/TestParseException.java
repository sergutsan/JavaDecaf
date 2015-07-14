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

}
