package exercise;

import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerTests {
    @Test(expected = RoverException.class)
    public void testCrash() throws RoverException {
        Controller c = new Controller(new Plateau(3, 3));
        c.deploy(new Rover(new Position(2, 2, Position.Bearing.S), null));
        c.deploy(new Rover(new Position(2, 2, Position.Bearing.S), null));
    }

    @Test
    public void testDeploy() throws RoverException {
        Controller c = new Controller(new Plateau(3, 3));
        c.deploy(new Rover(new Position(2, 2, Position.Bearing.S), null));
        c.deploy(new Rover(new Position(2, 1, Position.Bearing.S), null));
    }

    @Test(expected = RoverException.class)
    public void testDeployOutOfBounds() throws RoverException {
        Controller c = new Controller(new Plateau(3, 3));
        c.deploy(new Rover(new Position(2, 5, Position.Bearing.S), null));
    }

    @Test
    public void testDocumentedExpectations() throws RoverException {
        Controller c = new Controller(Plateau.parse("5 5"));

        Rover r = new Rover(Position.parse("1 2 N"), Itinerary.parse("LMLMLMLMM"));

        int r1 = c.deploy(r);
        r = new Rover(Position.parse("3 3 E"), Itinerary.parse("MMRMMRMRRM"));
        int r2 = c.deploy(r);

        assertTrue(r1 != r2);

        Position p1 = c.execute(r1);
        Position p2 = c.execute(r2);

        assertEquals("1 3 N", p1.toString());
        assertEquals("5 1 E", p2.toString());
    }

    @Test(expected = RoverException.class)
    public void testMoveOutOfBounds() throws RoverException {
        Controller c = new Controller(new Plateau(2, 2));
        Rover r = new Rover(Position.parse("1 1 S"), Itinerary.parse("MMM"));
        int r1 = c.deploy(r);
        c.execute(r1);
    }


}
