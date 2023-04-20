package org.sanwin;

import org.sanwin.utils.Direction;
import org.sanwin.utils.Planet;
import org.sanwin.utils.Position;
import org.sanwin.utils.Rover;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length % 2 != 0) {
            throw new RuntimeException("This program require pair argument, like \"3,4,N\" \"f,f,r,f,f\"");
        }

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

        // if want to execute command rover by rover, use below code.
        for (int i = 0; i < roverInstruction.size(); i++) {
            planet.getRoverList().get(i).process(roverInstruction.get(i).split(","), planet);
        }

        // if want to execute command rover by parallel, use below code but it require 2 pair of code
        // eg. "3,4,N" "f,f,r,f,f" "0,0,N" "f,f,r,f,f"
//        Runnable r1 = () -> {
//            planet.getRoverList().get(0).process(roverInstruction.get(0).split(","), planet);
//        };
//        Runnable r2 = () -> {
//            planet.getRoverList().get(1).process(roverInstruction.get(1).split(","), planet);
//        };
//        new Thread(r1).start();
//        new Thread(r2).start();
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
