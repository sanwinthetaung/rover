import org.junit.Test;
import org.sanwin.exception.RoverCollideException;
import org.sanwin.utils.Direction;
import org.sanwin.utils.Planet;
import org.sanwin.utils.Position;
import org.sanwin.utils.Rover;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RoverTest {

    @Test
    public void moving_rover_one_success() {
        Position position = new Position(3, 4);
        Direction direction = Direction.NORTH;
        Rover rover = new Rover("R1", position, direction);
        String[] inst = "f,f,r,f,f".split(",");
        rover.process(inst, new Planet());
        String report = rover.report();
        assertEquals("5,6,E", report);
    }

    @Test
    public void moving_two_rovers_one_by_one_success() {
        String given = "3,4,N f,f,r,f,f 6,6,N f,f,r,f,f";
        String[] res = new String[] { "5,6,E", "8,8,E" };

        String[] args = given.split(" ");
        List<String> roverPosition = new ArrayList<>();
        List<String> roverInstruction = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0)
                roverPosition.add(args[i]);
            else
                roverInstruction.add(args[i]);
        }

        Planet planet = new Planet();
        for (int i = 0; i < roverPosition.size(); i++) {
            String[] pos = roverPosition.get(i).split(",");
            int x = Integer.parseInt(pos[0]);
            int y = Integer.parseInt(pos[1]);
            Direction direction = getDirection(pos[2]);
            Position position = new Position(x, y);
            planet.place(new Rover("R" + i, position, direction));
        }

        for (int i = 0; i < roverInstruction.size(); i++) {
            planet.getRoverList().get(i).process(roverInstruction.get(i).split(","), planet);
            assertEquals(res[i], planet.getRoverList().get(i).report());
        }
    }

    @Test
    public void moving_two_rover_with_same_position_throw() {
        try {
            String given = "3,4,N f,f,r,f,f 3,4,N f,f,r,f,f";
            String[] res = new String[] { "5,6,E", "5,7,E" };
            String[] args = given.split(" ");

            List<String> roverPosition = new ArrayList<>();
            List<String> roverInstruction = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if (i % 2 == 0)
                    roverPosition.add(args[i]);
                else
                    roverInstruction.add(args[i]);
            }

            Planet planet = new Planet();
            for (int i = 0; i < roverPosition.size(); i++) {
                String[] pos = roverPosition.get(i).split(",");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                Direction direction = getDirection(pos[2]);
                Position position = new Position(x, y);
                planet.place(new Rover("R" + i, position, direction));
            }

            for (int i = 0; i < roverInstruction.size(); i++) {
                planet.getRoverList().get(i).process(roverInstruction.get(i).split(","), planet);
                assertEquals(res[i], planet.getRoverList().get(i).report());
            }

        } catch (RoverCollideException e) {
            assertEquals("Another rover there", e.getMessage());
        }
    }

    public static Direction getDirection(String c) {
        switch (c) {
            case "N": return Direction.NORTH;
            case "E": return Direction.EAST;
            case "S": return Direction.SOUTH;
            case "W": return Direction.WEST;
            default: throw new RuntimeException("Direction not clear");
        }
    }
}
