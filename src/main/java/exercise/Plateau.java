package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;

/**
 * The surface on which Rovers are deployed and executed
 */
public class Plateau {

    private static final Logger log = LoggerFactory.getLogger(Plateau.class);

    private Rover[][] grid = null;
    private HashMap<Integer, Rover> rovers = new HashMap<Integer, Rover>();

    /**
     * Creates a new plateau with the specified dimensions
     *
     * @param x the X coordinate of the top-right corner
     * @param y the Y coordinate of the top-right corner
     */
    public Plateau(int x, int y){
        assert x > 0;
        assert y > 0;

        grid = new Rover[x + 1][y + 1];
    }

    /**
     * Checks if a given coordinate is occupied
     *
     * @param p the position
     * @return true if vacant, otherwise false
     * @throws RoverException if there are any issues with the position being checked
     */
    public boolean isOccupied(Position p) throws RoverException {
        return isOccupied(p.getX(), p.getY());
    }

    /**
     * Checks if a given coordinate is occupied
     *
     * @param x X coordinate to check
     * @param y Y coordinate to check
     * @return true if vacant, otherwise false
     * @throws RoverException if there are any issues with the coordinates being checked
     */
    public boolean isOccupied(int x, int y) throws RoverException {
        if (!isValidCoordinate(x, y))
            throw new RoverException(String.format("Invalid coordinate: %s %s", x, y));

        return grid[x][y] != null;
    }

    /**
     * Validates a position against the plateau's size
     *
     * @param pos the position to check
     * @return true if the position falls within the boundaries of the plateau
     */
    public boolean isValidCoordinate(Position pos) {
        return isValidCoordinate(pos.getX(), pos.getY());
    }

    /**
     * Validates a specified coordinate
     *
     * @param x coordinate X position
     * @param y coordinate Y position
     * @return true if the position falls within the boundaries of the plateau
     */
    public boolean isValidCoordinate(int x, int y) {
        log.debug("Grid: {}x{} X: {} Y: {}", grid.length, grid[0].length, x, y);
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    /**
     * Empties the coordinate of any reference to a Rover. Does nothing if the coordinate is invalid.
     *
     * @param x coordinate X position
     * @param y coordinate Y position
     */
    public void vacate(int x, int y) {
        if(isValidCoordinate(x, y))
            grid[x][y] = null;
    }

    /**
     * Parses a plateau from a piece of text
     *
     * @param line plateau data
     * @return the plateau
     * @throws RoverException if there are any issues with the plateau data
     */
    public static Plateau parse(String line) throws RoverException {
        if (line != null && !"".equals(line)) {
            int[] coords = Arrays
                .stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            if(coords.length != 2)
                throw new RoverException(String.format("Invalid coordinate string: %s", line));

            return new Plateau(coords[0], coords[1]);

        } else {
            throw new RoverException("Plateau info cannot be null or empty");
        }
    }

    /**
     * Gets the top right X coordinate
     *
     * @return max X
     */
    public int getX() {
        return grid.length;
    }

    /**
     * Gets the top right Y coordinate
     *
     * @return max Y
     */
    public int getY() {
        return grid[0].length;
    }

    /**
     * Attempts to add a Rover to the plateau
     *
     * @param rover the rover to add
     * @return the Rover identifier
     * @throws RoverException if there are any issues with the Rover being deployed
     */
    public int addRover(Rover rover) throws RoverException {
        Position pos = rover.getPosition();

        if(isOccupied(pos.getX(), pos.getY()))
            throw new RoverException("Crash!");

        grid[pos.getX()][pos.getY()] = rover;

        int id = rovers.size();

        rovers.put(id, rover);

        return id;
    }

    /**
     * Gets a Rover by the specified ID from the plateau.
     *
     * @param roverId the ID of the Rover to get
     * @return the River if found, otherwise null
     */
    public Rover getRover(int roverId) {
        return rovers.get(roverId);
    }

    /**
     * Update the location of a specified Rover
     *
     * @param rover the Rover to update
     * @param position the position indicate as full
     */
    public void setRoverLocation(Rover rover, Position position) {
        grid[position.getX()][position.getY()] = rover;
    }
}
