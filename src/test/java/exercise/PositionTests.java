package exercise;

import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTests {
    @Test
    public void testToString() {
        assertEquals("1 1 N", new Position(1, 1, Position.Bearing.N).toString());
    }

    @Test(expected = RoverException.class)
    public void testParseNull() throws RoverException {
        Position.parse(null);
    }

    @Test(expected = RoverException.class)
    public void testParseEmpty() throws RoverException {
        Position.parse("");
    }

    @Test(expected = RoverException.class)
    public void testParseTooFewParams() throws RoverException {
        Position.parse("1 2");
    }

    @Test(expected = RoverException.class)
    public void testParseTooManyParams() throws RoverException {
        Position.parse("1 2 N Q");
    }

    @Test(expected = RoverException.class)
    public void testParseInvalidX() throws RoverException {
        Position.parse("A 2 N");
    }

    @Test(expected = RoverException.class)
    public void testParseInvalidY() throws RoverException {
        Position.parse("1 A N");
    }

    @Test(expected = RoverException.class)
    public void testParseInvalidBearing() throws RoverException {
        Position.parse("1 1 X");
    }

    @Test
    public void testParse() throws RoverException {
        Position p = Position.parse("1 1 N");
        assertEquals(1, p.getX());
        assertEquals(1, p.getY());
        assertEquals(Position.Bearing.N, p.getBearing());
    }
}
