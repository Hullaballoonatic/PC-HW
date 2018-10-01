import java.io.PrintStream;
import java.util.*;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        int n;
        while((n = in.nextInt()) != 0) {

            boolean[] lights = new boolean[n+1];
            Arrays.fill(lights, false);

            for(int i = 1; i < lights.length; i++) {
                for(int j = 1; j < lights.length; j++) {
                    if (j%i==0) lights[j] = !lights[j];
                }
            }

            out.println(lights[n] ? "yes" : "no");
        }
    }
}