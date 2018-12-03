package exercise;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlateauTests {

    @Test(expected = AssertionError.class)
    public void testConstructorWithInvalidHeight(){
        new Plateau(-1, 10);
    }

    @Test(expected = AssertionError.class)
    public void testConstructorWithInvalidWidth(){
        new Plateau(10, -1);
    }

    @Test
    public void testInvalidIsOccupiedCoordinates(){
        Plateau p = new Plateau(5, 5);

        try {
            p.isOccupied(-1, 3);
            fail("Should not accept negative x");
        } catch (RoverException e) {
        }

        try {
            p.isOccupied(30, 3);
            fail("Should not accept x beyond boundary");
        } catch (RoverException e) {
        }

        try {
            p.isOccupied(3, -1);
            fail("Should not accept negative y");
        } catch (RoverException e) {
        }

        try {
            p.isOccupied(0, 30);
            fail("Should not accept y beyond boundary");
        } catch (RoverException e) {
        }

    }

    @Test
    public void testInvalidIsOccupiedPositions(){
        Plateau p = new Plateau(5, 5);

        try {
            p.isOccupied(new Position(-1, 3, Position.Bearing.N));
            fail("Should not accept negative x");
        } catch (RoverException e) {
        }

        try {
            p.isOccupied(new Position(30, 3, Position.Bearing.N));
            fail("Should not accept x beyond boundary");
        } catch (RoverException e) {
        }

        try {
            p.isOccupied(new Position(3, -1, Position.Bearing.N));
            fail("Should not accept negative y");
        } catch (RoverException e) {
        }

        try {
            p.isOccupied(new Position(0, 30, Position.Bearing.N));
            fail("Should not accept y beyond boundary");
        } catch (RoverException e) {
        }

    }
    @Test
    public void testParse() throws RoverException {
        Plateau p = Plateau.parse("4 9");

        assertEquals(5, p.getX());
        assertEquals(10, p.getY());
    }

    @Test(expected = RoverException.class)
    public void testParseNull() throws RoverException {
        Plateau.parse(null);
        fail("Parse should fail");
    }

    @Test(expected = RoverException.class)
    public void testParseEmpty() throws RoverException {
        Plateau.parse("");
        fail("Parse should fail");
    }

    @Test(expected = RoverException.class)
    public void testParseTooFew() throws RoverException {
        Plateau.parse("1");
        fail("Parse should fail");
    }

    @Test(expected = RoverException.class)
    public void testParseTooMany() throws RoverException {
        Plateau.parse("1 2 3");
        fail("Parse should fail");
    }

    @Test(expected = RoverException.class)
    public void testParseNegativeX() throws RoverException {
        Plateau.parse("-1 2");
        fail("Parse should fail");
    }

    @Test(expected = RoverException.class)
    public void testParseNegativeY() throws RoverException {
        Plateau.parse("1 -2");
        fail("Parse should fail");
    }
}
