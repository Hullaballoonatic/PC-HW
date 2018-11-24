import java.util.*;

import static java.lang.Math.pow;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String... args) {
        while(in.hasNext()) {
            long N = in.nextLong();
            Shape grid = new Shape(N, 2);
            Shape cube = new Shape(N, 3);
            Shape hyperCube = new Shape(N, 4);
            out.printf("%d %d %d %d %d %d\n", grid.S(), grid.R(), cube.S(), cube.R(), hyperCube.S(), hyperCube.R());
        }
    }
}

class Shape {
    private long N;
    private int k;

    long S() {
        long sum = 0;
        for (int n = 1; n <= N; n++) sum += pow(n, k);
        return sum;
    }

    long R() {
        return (long) pow((N + 1) * N / 2.0, k) - S();
    }

    Shape(long n, int k) {
        N = n;
        this.k = k;
    }
}