package org.sanwin.utils;

import lombok.*;
import org.sanwin.exception.RoverCollideException;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rover {

    private String name;
    private Position position;
    private Direction direction;

    private void turnLeft() {
        switch (direction) {
            case NORTH: setDirection(Direction.WEST); break;
            case EAST: setDirection(Direction.NORTH); break;
            case SOUTH: setDirection(Direction.EAST); break;
            case WEST: setDirection(Direction.SOUTH); break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case NORTH: setDirection(Direction.EAST); break;
            case EAST: setDirection(Direction.SOUTH); break;
            case SOUTH: setDirection(Direction.WEST); break;
            case WEST: setDirection(Direction.NORTH); break;
        }
    }

    private void moveForward(Planet planet) {
        if (this.position != null && this.direction != null) {
            if (Objects.equals(this.direction.getValue(), Direction.NORTH.getValue())) {
                int y = this.position.getY() > 0 ? this.position.getY() + 1 : this.position.getY() - 1;
                boolean check = planet.isValidPosition(new Position(this.position.getX(), y));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setY(y);
                }
            } else if (Objects.equals(this.direction.getValue(), Direction.EAST.getValue())) {
                int x = this.position.getX() > 0 ? this.position.getX() + 1 : this.position.getX() - 1;
                boolean check = planet.isValidPosition(new Position(x, this.position.getY()));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setX(x);
                }
            } else if (Objects.equals(this.direction.getValue(), Direction.SOUTH.getValue())) { // op north
                int y = this.position.getY() < 0 ? this.position.getY() + 1 : this.position.getY() - 1;
                boolean check = planet.isValidPosition(new Position(this.position.getX(), y));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setY(y);
                }
            } else if (Objects.equals(this.direction.getValue(), Direction.WEST.getValue())) { // op east
                int x = this.position.getX() < 0 ? this.position.getX() + 1 : this.position.getX() - 1;
                boolean check = planet.isValidPosition(new Position(x, this.position.getY()));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setX(x);
                }
            }
        }
    }

    public void moveBackward(Planet planet) {
        if (this.position != null && this.direction != null) {
            if (Objects.equals(this.direction.getValue(), Direction.NORTH.getValue())) {
                int y = this.position.getY() > 0 ? this.position.getY() - 1 : this.position.getY() + 1;
                boolean check = planet.isValidPosition(new Position(this.position.getX(), y));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setY(y);
                }
            } else if (Objects.equals(this.direction.getValue(), Direction.EAST.getValue())) {
                int x = this.position.getX() > 0 ? this.position.getX() - 1 : this.position.getX() + 1;
                boolean check = planet.isValidPosition(new Position(x, this.position.getY()));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setX(x);
                }
            } else if (Objects.equals(this.direction.getValue(), Direction.SOUTH.getValue())) { // op north
                int y = this.position.getY() < 0 ? this.position.getY() - 1 : this.position.getY() + 1;
                boolean check = planet.isValidPosition(new Position(this.position.getX(), y));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setY(y);
                }
            } else if (Objects.equals(this.direction.getValue(), Direction.WEST.getValue())) { // op east
                int x = this.position.getX() < 0 ? this.position.getX() - 1 : this.position.getX() + 1;
                boolean check = planet.isValidPosition(new Position(x, this.position.getY()));
                if (!check) {
                    throw new RoverCollideException();
                } else {
                    this.position.setX(x);
                }
            }
        }
    }

    public void process(String[] instruction, Planet planet) {
        for (String command: instruction) {
            move(command, this, planet);
        }
    }

    private void move(String command, Rover rover, Planet planet) {
        if (rover == null) {
            throw new RuntimeException("Rover location invalid");
        }

        switch (command) {
            case "f": rover.moveForward(planet); break;
            case "b": rover.moveBackward(planet); break;
            case "l": rover.turnLeft(); break;
            case "r": rover.turnRight(); break;
        }

        System.out.println("Command:" + command + " " + rover.getName()
                    + ", " + rover.getPosition().getX()
                    + ", " + rover.getPosition().getY()
                    + ", " + rover.getDirection().getValue()

        );
    }

    public String report() {
        return this.position.toString() + "," + this.direction.getValue();
    }
}
