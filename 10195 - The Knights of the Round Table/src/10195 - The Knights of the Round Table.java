import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    // I watched this video and then completed this problem
    // https://www.youtube.com/watch?v=RQV8mgUq_Ps&ab_channel=Mr.T%27sMathVideos

    public static void main(String[] args) {
        while(in.hasNext()) {
            double a = in.nextDouble(), b = in.nextDouble(), c = in.nextDouble(), r;

            if (a == 0.0 && b == 0.0 && c == 0.0) r = 0.0;
            else {
                // A = 0.5 * r * p
                double p = a + b + c;
                // Heron's Formula:
                // A = root( s(s-a)(s-b)(s-c) )
                // s = (a + b + c) / 2
                double s = p / 2.0;
                double A = Math.sqrt(s * (s - a) * (s - b) * (s - c));
                // r = 2A/p
                r = 2.0 * A / p;
            }
            out.printf("The radius of the round table is: %.3f\n", r);
        }
    }
}