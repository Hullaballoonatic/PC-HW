import java.io.PrintStream;
import java.util.*;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        while(true) {
            int a = in.nextInt(), b = in.nextInt(), fibs = 0;
            if (a == 0 && b == 0) break;

            for(int n = a; n <= b; n++) {
                if (isFib(n)) fibs++;
            }

            out.println(fibs);
        }
    }

    private static boolean isFib(int n) {
        long foo = 5 * n * n;
        return isSquare(foo + 4) || isSquare(foo - 4);
    }

    private static boolean isSquare(long n) {
        int r = (int) Math.sqrt(n);
        return r == n;
    }
}