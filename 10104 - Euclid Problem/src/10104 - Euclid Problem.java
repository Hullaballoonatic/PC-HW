import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while(in.hasNext()) {
            int A = in.nextInt(),
                B = in.nextInt(),
                X = 1, Y = 0,
                x = 0, y = 1;

            while(B != 0) {
                int q = A/B,
                    tmp = B;
                B = A%B;
                A = tmp;

                tmp = X - q * x;
                X = x;
                x = tmp;

                tmp = Y - q * y;
                Y = y;
                y = tmp;
            }

            out.printf("%d %d %d\n", X, Y, A);
        }
    }
}