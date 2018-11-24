import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String... args) {
        long N;
        while((N = in.nextLong()) != 0) {
            long r = (long) Math.ceil(Math.sqrt(N)), x = r, y = r, b = r * r - r + 1, off = N - b;
            if (off > 0) {
                if (r % 2 == 0)
                    y -= off;
                else
                    x -= off;
            } else {
                if (r % 2 == 0)
                    x += off;
                else
                    y += off;
            }
            out.printf("%d %d\n", x, y);
        }
    }
}