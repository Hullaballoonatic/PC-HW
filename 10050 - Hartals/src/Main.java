import java.util.Scanner;

import static java.lang.System.out;


/**
 * UVa 10050 - Hartals
 *
 * T: Number of Cases
 * N: Number of Days
 * P: Number of Parties
 *
 * During a hartal, employees take off of work. Hartals are evenly spaced. If a hartal falls on a weekend, it doesn't count against work days.
 *
 * To solve we need h_1 though h_P, each denoting the hartal parameter for each party
 */
class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();

    private static int execute(int N, int[] h) {
        int result = 0;

        for (int day = 1; day <= N; day++)
            if (day % 7 != 6 && day % 7 != 0) // ignore friday and saturday
                for (int param : h)
                    if (day % param == 0) // ignore days on which multiple hartals occur
                        result++;

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