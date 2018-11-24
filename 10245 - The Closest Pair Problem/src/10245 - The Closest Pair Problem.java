import java.util.*;

import static java.lang.Math.min;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final String trivialSolutionString = "INFINITY";
    private static final int MAX = 10000;

   private static String findShortestLine(Pos[] positions) {
        double result = MAX;

        for (int i = 0; i < positions.length; i++) {
            Pos a = positions[i];
            for (int j = i + 1; j < positions.length; j++) {
                Pos b = positions[j];
                if (a.x + result < b.x)
                    break;
                result = min(a.distanceTo(b), result);
            }
        }

        return result < MAX ? String.format("%.4f", result) : trivialSolutionString;
    }

    public static void main(String... args) {
        int n;
        while((n = in.nextInt()) != 0) {
            Pos[] positions = new Pos[n];

            for (int i = 0; i < n; i++)
                positions[i] = new Pos(in.nextDouble(), in.nextDouble());

            Arrays.sort(positions, (a, b) -> (int) (a.x - b.x));

            out.println(findShortestLine(positions));
        }
    }
}

class Pos {
    double x;
    private double y;

    double distanceTo(Pos pos) {
        return Math.hypot(x - pos.x, y - pos.y);
    }

    Pos(double x, double y) {
        this.x = x;
        this.y = y;
    }
}