package exercise;

/**
 * The series of instructions that can be issued to a Rover
 */
public class Itinerary {

    private final Instruction[] instructions;

    /**
     * The list of options that can be included in an itinerary
     */
    public enum Instruction {
        M, L, R
    }

    /**
     * Creates a new itinerary
     *
     * @param instructions the list of instructions
     */
    public Itinerary(Instruction[] instructions) {
        this.instructions = instructions;
    }

    /**
     * Gets the list of instructions in the itinerary
     *
     * @return list of instructions
     */
    public Instruction[] getInstructions() {
        return instructions;
    }

    /**
     * Parses an itinerary from a piece of text
     *
     * @param input itinerary data
     * @return an itinerary
     * @throws RoverException if there are any problems parsing the input
     */
    public static Itinerary parse(String input) throws RoverException {
        if (input == null)
            throw new RoverException("Execution plan is null");

        Instruction[] ins = new Instruction[input.length()];
        int i = 0;
        for(char c: input.toCharArray()){
            try {
                ins[i++] = Instruction.valueOf(String.valueOf(c));
            } catch (IllegalArgumentException iae) {
                throw new RoverException("Invalid itinerary data", iae);
            }

        }

        return new Itinerary(ins);
    }
}
