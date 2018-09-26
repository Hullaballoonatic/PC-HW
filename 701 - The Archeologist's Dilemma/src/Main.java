import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.Math.log;

class Main {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        while (in.hasNext()) {
            long a, b, x = in.nextLong();
            int d = 1 + Long.toString(x).length();

            while (true) {
                a = (long) (d * log(10) / log(2) + log(x) / log(2));
                b = (long) (d * log(10) / log(2) + log(x + 1) / log(2));
                if (a < b) break;
                d++;
            }

            out.println(a + 1);
        }
    }

}