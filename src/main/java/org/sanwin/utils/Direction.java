package org.sanwin.utils;


public enum Direction {
    NORTH("N")
    , EAST("E")
    , SOUTH("S")
    , WEST("W");

    private String value;

    Direction(String n) {
        this.value = n;
    }

    public String getValue() {
        return value;
    }

}
