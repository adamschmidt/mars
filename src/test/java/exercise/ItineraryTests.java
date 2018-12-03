package exercise;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItineraryTests {

    @Test
    public void testParse() throws RoverException {
        Itinerary i = Itinerary.parse("MMMM");
        Itinerary.Instruction[] ins = i.getInstructions();

        assertEquals(4, ins.length);
        assertArrayEquals(
            new Itinerary.Instruction[]{
                Itinerary.Instruction.M,
                Itinerary.Instruction.M,
                Itinerary.Instruction.M,
                Itinerary.Instruction.M
            },
            ins);
    }

    @Test(expected = RoverException.class)
    public void testParseNull() throws RoverException {
        Itinerary.parse(null);
        fail("Parse on null should not work");
    }

    @Test
    public void testParseEmpty() throws RoverException {
        Itinerary i = Itinerary.parse("");
        Itinerary.Instruction[] ins = i.getInstructions();

        assertEquals(0, ins.length);
    }
}
