import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int N, M;
    private static Map<Integer, List<Integer>> connections;
    private static boolean[] hasGas;
    private static int lowest;

    // using a dfs this time
    private static void backtrack(int k, int n, int t) {
        if (t >= lowest || k > n) return;
        if (n == N) {
            lowest = t;
            return;
        }
        for (int i = 1; i < k; i++) {
        }


    }

    public static void main(String[] args) {
        while(true) {
            N = in.nextInt();
            M = in.nextInt();
            if (N == 0 && M == 0) return;
            else {
                lowest = N;
                connections = new TreeMap<Integer, List<Integer>>(Comparator.comparingInt(a -> a)) {{
                    while(M-->0) {
                        int a = in.nextInt(), b = in.nextInt();

                        if (!connections.containsKey(a)) connections.put(a, new ArrayList<>());

                        connections.get(a).add(b);
                    }
                }};
                backtrack(N, 0, 1);
                out.println(lowest);
            }
        }
    }
}