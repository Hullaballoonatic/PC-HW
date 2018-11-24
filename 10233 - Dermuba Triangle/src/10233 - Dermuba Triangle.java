import java.util.*;

import static java.lang.Math.*;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String... args) {
        while(in.hasNext())
            out.printf("%.3f\n", new Pos(in.nextInt()).distanceTo(new Pos(in.nextInt())));
    }
}

class Pos {
    private double x, y;

    double distanceTo(Pos to) {
        double dx = to.x - x, dy = to.y - y;
        return hypot(dx, dy);
    }

    Pos(int n) {
        double d = floor(sqrt(n));
        x = (n - d * d - d) / 2.0;
        y = (d - 1) * sqrt(3) / 2.0 + (
                (d + n) % 2 != 0 ? sqrt(3) / 3.0 : sqrt(3) / 2.0
        );
    }
}