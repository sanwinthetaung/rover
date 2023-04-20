package org.sanwin.utils;

import lombok.*;
import org.sanwin.exception.RoverCollideException;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Planet {

    private List<Rover> roverList = new ArrayList<>();

    public void place(Rover rover) {
        if (isValidPosition(rover.getPosition())) {
            roverList.add(rover);
        } else {
            throw new RoverCollideException();
        }
    }

    public boolean isValidPosition(Position position) {
        for (Rover r: roverList) {
            if (r.getPosition().isEqual(position)) {
                return false;
            }
        }
        return true;
    }
}
