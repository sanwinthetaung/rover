package org.sanwin.exception;

public class RoverCollideException extends RuntimeException {

    public RoverCollideException() {
        super("Another rover there");
    }
}
