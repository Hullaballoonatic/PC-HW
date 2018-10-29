import java.util.Scanner;

import static java.lang.System.out;


/**
 * UVa 10050 - Hartals
 *
 * T: Number of Cases
 * h: hartal parameters for each party
 * d: days over which we consider
 *
 * During a hartal, employees take off of work. Hartals are evenly spaced. If a hartal falls on a weekend, it doesn't count against work days.
 *
 *
 */
class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();
    private static final int FRI = 5, SAT = 6;

    private static int execute(int N, int[] h) {
        int result = 0;

        for (int day = 1, dayOfWeek = 0; day <= N; day++, dayOfWeek = (dayOfWeek + 1) % 7)
            if (dayOfWeek != FRI && dayOfWeek != SAT)
                for (int p : h)
                    if (day % p == 0) {
                        result++;
                        break;
                    }

        return result;
    }

    public static void main(String[] args) {
        while(T-->0) {
            int N = in.nextInt();
            int[] h = new int[in.nextInt()];
            for (int i = 0; i < h.length; i++)
                h[i] = in.nextInt();
            out.println(execute(N, h));
        }
    }
}