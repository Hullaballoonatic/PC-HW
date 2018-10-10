import java.io.PrintStream;
import java.util.*;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        long n;
        while((n = in.nextLong()) != 0) {
            long tmp = (long) Math.floor(Math.sqrt(n));

            out.println(tmp * tmp == n ? "yes" : "no");
        }
    }
}