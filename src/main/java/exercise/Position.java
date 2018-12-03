package exercise;

/**
 * Representation of a position on the plateau, including the direction faced
 */
public class Position {

    /**
     * Cardinal compass points
     */
    public enum Bearing {
        N, E, S, W
    }

    private final int x;
    private final int y;
    private final Bearing bearing;

    /**
     * Creates a position with the specified location and bearing
     *
     * @param x coordinate X position
     * @param y coordinate Y position
     * @param bearing compass bearing
     */
    public Position(int x, int y, Bearing bearing) {
        this.x = x;
        this.y = y;
        this.bearing = bearing;
    }

    /**
     * Gets the X coordinate
     *
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate
     *
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the bearing
     *
     * @return bearing
     */
    public Bearing getBearing() {
        return bearing;
    }

    /**
     * Returns a string in the format of "[x] [y] [bearing]"
     *
     * @return position string
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", this.x, this.y, this.bearing);
    }

    /**
     * Parses a position from a piece of text
     *
     * @param line position data
     * @return the position
     * @throws RoverException if there are any issues with the position data
     */
    public static Position parse(String line) throws RoverException {
        if (line == null || "".equals(line)) {
            throw new RoverException("Position info cannot be null or empty");
        }

        String[] params = line.split(" ");

        if(params.length != 3)
            throw new RoverException(String.format("Invalid coordinate string: %s", line));

        try {
            return new Position(
                Integer.parseInt(params[0]),
                Integer.parseInt(params[1]),
                Bearing.valueOf(params[2])
            );
        } catch (Throwable t) {
            throw new RoverException("Position info is not valid", t);
        }

    }
}
