package org.sanwin.utils;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Position {
    private int x;
    private int y;

    public boolean isEqual(Position other) {
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
}
