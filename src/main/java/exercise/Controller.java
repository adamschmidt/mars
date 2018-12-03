package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rover controller class which coordinates activity between one or more Rovers and the plateau.
 */
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    private Plateau plateau;

    /**
     * Creates a new controller with the specified plateau
     *
     * @param plateau the plateau that the Rovers can be deployed on
     */
    public Controller(Plateau plateau){
        this.plateau = plateau;
    }

    /**
     * Deploys a new Rover onto the plateau
     *
     * @param rover the Rover to deploy
     * @return the id of the deployed Rover
     * @throws RoverException if there are any issues deploying the Rover
     */
    public int deploy(Rover rover) throws RoverException {
        return plateau.addRover(rover);
    }

    /**
     * Executes a Rover
     *
     * @param roverId the identifier of the Rover on the plateau to kick into action
     * @return the final position
     * @throws RoverException if there are any issues executing the itinerary
     */
    public Position execute(int roverId) throws RoverException {
        Rover r = plateau.getRover(roverId);
        if(r == null)
            throw new RoverException(String.format("Unknown rover id: %s", roverId));

        log.debug("Starting plan execution for rover #{}", roverId);

        Position start = r.getPosition();
        Itinerary itinerary = r.getItinerary();

        for (Itinerary.Instruction i : itinerary.getInstructions()) {

            Position pos = r.getPosition();
            int x = pos.getX();
            int y = pos.getY();
            Position.Bearing b = pos.getBearing();

            switch(i){
                case M:
                    // if the instruction is to move, work out what changes to x/y to apply
                    switch(pos.getBearing()){
                        case N:
                            y += 1;
                            break;
                        case E:
                            x += 1;
                            break;
                        case S:
                            y -= 1;
                            break;
                        case W:
                            x -= 1;
                            break;
                    }
                    break;
                case L:
                case R:
                    // if the instruction is to turn, work out the new direction
                    switch(pos.getBearing()){
                        case N:
                            b = i == Itinerary.Instruction.L ? Position.Bearing.W : Position.Bearing.E;
                            break;
                        case E:
                            b = i == Itinerary.Instruction.L ? Position.Bearing.N : Position.Bearing.S;
                            break;
                        case S:
                            b = i == Itinerary.Instruction.L ? Position.Bearing.E : Position.Bearing.W;
                            break;
                        case W:
                            b = i == Itinerary.Instruction.L ? Position.Bearing.S : Position.Bearing.N;
                            break;
                    }
                    break;
                default:
                    // derp
                    log.warn("Unsupported instruction: {}", i);
                    break;
            }

            Position next = new Position(x, y, b);
            log.debug("Next position: {}", next);

            if (plateau.isValidCoordinate(next)) {
                r.setPosition(next);
            } else {
                r.setPosition(start);
                throw new RoverException("Plan execution would result in a crash. Aborting.");
            }

        }

        plateau.vacate(start.getX(), start.getX());
        plateau.setRoverLocation(r, r.getPosition());

        return r.getPosition();
    }
}
