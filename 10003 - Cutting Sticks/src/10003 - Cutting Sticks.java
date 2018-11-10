import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while(in.hasNext()) {
            // input
            int l = in.nextInt();
            if (l == 0) return;

            int n = in.nextInt(), numCuts = n + 2;
            int[] cutLocations = new int[numCuts];
            cutLocations[0] = 0;
            for (int i = 1; i < n-1; i++) cutLocations[i] = in.nextInt();
            cutLocations[numCuts - 1] = l;

            // solving
            /*
             * Let i be the point where the stick starts.
             *
             * Let j be the length of stick (in cuts). This means that dp[1][2] holds the cost of the stick that starts at point 1, and is 2 cuts long. For the above test case, that means the stick (4,7).
             *
             * dp[i][1] = 0
             * (A stick that's only one cut long doesn't need to be cut any more, and therefore doesn't cost anything).
             *
             * dp[i][j] = minimum across all k in range[1,j-1](dp[i][k] + dp[i+k][j-k]) + (a[i+j] - a[i])
             *
             * To clarify the recursive step a bit...
             * Iterate through all cuts between point i and point i+j, and find the one for which dp[i][k] + dp[i+k][j-k] is the minimum.
             * To this minimum, add (a[i+j]-a[i]) which is the length (and therefore cost) of the stick.
             */
            int cutAmt = 0;
            for (int startOfStick = 0; startOfStick < l; startOfStick++) {
                int cur = cutLocations[startOfStick];
                for (int lengthInCuts = 0; lengthInCuts < numCuts - startOfStick; lengthInCuts++) {
                    int stickCost = cutLocations[startOfStick + lengthInCuts] - cutLocations[cur];
                }
            }

            // output
            out.printf("The minimum cutting is %d\n", cutAmt);
        }
    }
}