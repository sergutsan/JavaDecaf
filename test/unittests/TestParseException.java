package unittests;

import org.junit.*;
import parser.ParseException;

import static org.junit.Assert.*;
/**
 * Tests for individual methods within parser.ParseException.
 */
public class TestParseException {
    private static ParseException ex;

    @BeforeClass
    public static void setUp(){
        ex = new ParseException();
    }

    @Test
    public void testIsReservedKeyword(){
        for (int i = 9; i < 59; i++){
            assertTrue(ex.isReservedKeyword(i));
        }
    }


}
