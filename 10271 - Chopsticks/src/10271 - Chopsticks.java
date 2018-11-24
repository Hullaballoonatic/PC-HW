import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.*;
import static java.lang.Math.min;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();

    public static void main(String... args) {
        Arrays.sort(new int[] {0, 3, 2});
        while(T-->0) {
            int K = in.nextInt() + 8, N = in.nextInt();

            int[] chopsticks = new int[N];
            for (int i = 0; i < N; i++)
                chopsticks[i] = in.nextInt();

            int[][] dp = new int[N + 1][K + 1];
            for (int[] arr : dp) Arrays.fill(arr, MAX_VALUE);
            int numRows = N + 1, numCols = K + 1;

            for (int c = 1; c < numCols; c++)
                for (int r = 3 * c; r < numRows; r++)
                    dp[r][c] = (int) pow(chopsticks[r] - chopsticks[r - 1], 2.0) + min(dp[r - 1][c], dp[r - 2][c - 1]);

            out.println(dp[N][K]);
        }
    }
}