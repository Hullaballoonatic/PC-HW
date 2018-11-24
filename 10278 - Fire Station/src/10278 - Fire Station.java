import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;

class Main{
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();
    private static int f;
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String... args) {
        while(T-->0) {
            f = in.nextInt();
            int n = in.nextInt();

            Set<Integer> fireStations = new HashSet<Integer>() {{
                while(f-->0) add(in.nextInt());
            }};

            int[][] G = new int[n][n];

            for (int[] row : G)
                Arrays.fill(row, INFINITY);

            for (int it = 1; it < G.length; it++) {
                int a = in.nextInt() - 1, b = in.nextInt() - 1, w = in.nextInt();
                G[a][b] = G[b][a] = w;
                G[a][a] = G[b][b] = 0;
            }

            for (int a = 0; a < n; a++)
                for (int b = 0; b < n; b++)
                    for (int c = 0; c < n; c++)
                        G[b][c] = min(G[b][c], G[b][a] + G[a][c]);

            int[] dist = new int[n];
            Arrays.fill(dist, INFINITY);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if(fireStations.contains(j))
                        dist[i] = min(dist[i], G[i][j]);

            int ind = 0, max = INFINITY;
            for (int i = 0; i < n; i++) {
                int cur = 0;
                for (int j = 0; j < n; j++)
                    cur = max(cur, min(dist[j], G[i][j]));
                if(cur < max) {
                    max = cur;
                    ind = i+1;
                }
            }
            out.printf("%d\n%s", ind, T > 0 ? "\n" : "");
        }
    }
}