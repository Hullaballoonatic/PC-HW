import java.util.*;

import static java.lang.Math.*;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    private static final String trivialSolutionString = "The gopher cannot escape.";

    private static String output(Pos solution) {
        return solution != null ? String.format("The gopher can escape through the hole at %s.", solution.toString()) : trivialSolutionString;
    }

    public static void main(String... args) {
        while(in.hasNext()) {
            Pos[] holes = new Pos[in.nextInt()];
            Actor gopher = new Actor(in.nextFloat(), in.nextFloat(), 1f), dog = new Actor(in.nextFloat(), in.nextFloat(), 2f);
            Pos solution = null;

            for (int i = 0; i < holes.length; i++)
                holes[i] = new Pos(in.nextFloat(), in.nextFloat());

            for (Pos hole : holes) {
                if (gopher.isOn(hole) || gopher.timeToReachPos(hole) <= dog.timeToReachPos(hole)) {
                    solution = hole;
                    break;
                }
            }

            out.println(output(solution));
        }
    }
}

class Actor {
    Pos pos;
    private float speed;

    double timeToReachPos(Pos to) {
        return pos.distanceTo(to) / speed;
    }

    boolean isOn(Pos spot) {
        return pos.x == spot.x && pos.y == spot.y;
    }

    Actor(float x, float y, float speed) {
        pos = new Pos(x, y);
        this.speed = speed;
    }
}

class Pos {
    float x, y;

    Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    double distanceTo(Pos to) {
        return hypot(abs(x - to.x), abs(y - to.y));
    }

    @Override
    public String toString() {
        return String.format("(%.3f,%.3f)", x, y);
    }
}