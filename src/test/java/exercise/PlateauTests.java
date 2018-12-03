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
    public void testParse() throws RoverException {
        Plateau p = Plateau.parse("4 9");

        assertEquals(5, p.getX());
        assertEquals(10, p.getY());
    }
}
