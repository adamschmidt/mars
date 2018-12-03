package exercise;

/**
 * A Rover
 */
public class Rover {

    private Position position;
    private Itinerary itinerary;

    /**
     * Creates a new Rover at a position with an itinerary
     *
     * @param position the position
     * @param itinerary the itinerary
     */
    public Rover(Position position, Itinerary itinerary) {
        this.position = position;
        this.itinerary = itinerary;
    }

    /**
     * Gets the position
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position
     *
     * @param position the position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Gets the itinerary
     *
     * @return the itinerary
     */
    public Itinerary getItinerary() {
        return itinerary;
    }

    /**
     * Sets the itinerary
     *
     * @param itinerary the itinerary
     */
    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
