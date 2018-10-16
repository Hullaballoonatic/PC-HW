import java.util.*;

import static java.lang.System.out;

/**
 * UVa # 10128
 * Queue
 *
 * N people of varying heights are standing in a queue.
 * We can't see any people shorter than someone who is behind them.
 * We can see P people from the front and R from the back.
 *
 * How many permutations of these people can result in those figures?
 */
class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();

    private static long backtrack(int n, int p, int r) {
        // We are counting permutations by removing the shortest person in line each time.
        // Since there is always a shortest person, we can always remove someone, thus we whittle down to a single tallest person.

        // There are no ways to see zero people in line.
        if (n == 0 || p == 0 || r == 0) return 0;
        if (p == 1 && r == 1)
            // There is only 1 way to see 1 person in line.
            if (n == 1) return 1;

            // There are no ways to see only 1 person from either end if the line contains more than 1 person. You could always see the tallest person.
            // If they are on one end and you view from the other, you could see all the people shorter than them.
            else        return 0;

                // Count permutations by removing the smallest person, updating N, P, R accordingly
                // Case 1: they are at the front; we can see 1 less person from the front
        return  backtrack(n-1, p-1, r) +
                // Case 2: same thing, but they are at the end
                backtrack(n-1, p, r-1) +
                // Case 3: they are in one of N-2 possible middle positions, so if we remove them, the number we can see from either end doesn't change
                backtrack(n-1, p, r) * (n-2);

    }

    public static void main(String[] args) {
        while(T-->0) out.println(backtrack(in.nextInt(), in.nextInt(), in.nextInt()));
    }
}