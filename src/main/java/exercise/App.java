package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Mars Rover simulation app.
 */
public class App {

    /**
     * Runs the Rover simulation program, collecting input from the commandline. The format and order of input is
     * defined in README.md.
     *
     * @param args command line arguments
     * @throws IOException if the input stream cannot be read
     * @throws RoverException if there are any semantic issues with your command and coordinate input
     */
    public static void main(String[] args) throws IOException, RoverException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> ids = new ArrayList<>();
        String plateau = r.readLine();
        Controller controller = new Controller(Plateau.parse(plateau));

        String coord;

        while((coord = r.readLine()) != null) {
            if ("".equals(coord)) { // exit on empty line
                break;
            }

            Position position = Position.parse(coord);
            Itinerary itinerary = Itinerary.parse(r.readLine());
            Rover rover = new Rover(position, itinerary);

            int id = controller.deploy(rover);
            ids.add(id);
        }

        for(int id : ids) {
            Position end = controller.execute(id);
            System.out.println(end);
        }
    }
}
