import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int MIN = -500, MAX = 500;

    public static void main(String[] args) throws Exception {
        int n;
        while((n = in.nextInt()) != 0) {
            Pos[] cherries = new Pos[2 * n];
            for (int i = 0; i < 2 * n; i++)
                cherries[i] = new Pos(in.nextInt(), in.nextInt());

            Line line = findEvenDividingLine(cherries);
            out.printf("%d %d\n", line.A, line.B);
        }
    }

    private static Line findEvenDividingLine(Pos[] cherries) throws Exception {
        for (int A = MIN; A <= MAX; A++) {
            for (int B = MIN; B <= MAX; B++) {
                // just try every A and B until one works
                // A and B work if the number of cherries above the line is the same as below it
                int numTop = 0, numBot = 0;

                boolean noneOnLine = true;

                for (Pos cherry : cherries) {
                    int C = A * cherry.x + B * cherry.y;
                    if (C == 0) {
                        noneOnLine = false;
                        break;
                    } else
                        if (C > 0) numTop++;
                        else       numBot++;
                }

                if (numTop == numBot && noneOnLine) return new Line(A, B);
            }
        }
        throw new Exception("No cherries found");
    }
}

class Line {
    int A, B;

    Line(int a, int b) {
        A = a;
        B = b;
    }
}

class Pos {
    int x, y;

    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}