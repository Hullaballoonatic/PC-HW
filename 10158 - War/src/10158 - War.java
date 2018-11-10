import java.util.*;

import static java.lang.System.out;

/**
 * I worked on this a long time and eventually modeled my solution after
 * https://raw.githubusercontent.com/izharishaksa/UVa-Solution/master/src/datastructures/ownlib/Problem10158.java
 * because my solution was too slow.
 */

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int N = in.nextInt(), FAIL = -1, SUCCESS = 1, NEUTRAL = 0;
    private static final int[] parents = new int[N], degrees = new int[N];
    private static final List<Set<Integer>> G = new ArrayList<>();

    private static void union(int a, int b) {
        int x = parent(a), y = parent(b);

        if (x != y) {
            if (degrees[x] >= degrees[y]) {
                parents[y] = x;
                G.get(x).addAll(G.get(y));
            } else {
                parents[x] = y;
                G.get(y).addAll(G.get(x));
            }
            if (degrees[x] == degrees[y]) degrees[x]++;
        }
    }

    private static boolean setFriends(int a, int b) {
        if (areEnemies(a, b)) return true;

        union(a, b);

        return false;
    }

    private static boolean setEnemies(int a, int b) {
        int x = parent(a), y = parent(b);

        if (x == y) return true;

        G.get(x).add(y);
        G.get(y).add(x);

        for (int e : G.get(x)) union(y, e);
        for (int e : G.get(y)) union(x, e);

        return false;
    }

    private static boolean areFriends(int x, int y) {
        return parent(x) == parent(y);
    }

    private static boolean areEnemies(int x, int y) {
        return G.get(parent(x)).contains(parent(y));
    }

    private static int parent(int n) {
        return n == parents[n] ? parents[n] : parent(parents[n]);
    }

    public static void main(String ... args) {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
            degrees[i] = 1;
            G.add(new HashSet<>());
        }

        while (in.hasNextLine()) {
            int c = in.nextInt(), x = in.nextInt(), y = in.nextInt();

            if (c == 0 && x == 0 && y == 0) return;
            if (c == 1 && setFriends(x, y)) out.println(FAIL);
            if (c == 2 && setEnemies(x, y)) out.println(FAIL);
            if (c == 3)                     out.println(areFriends(x, y) ? SUCCESS : NEUTRAL);
            if (c == 4)                     out.println(areEnemies(x, y) ? SUCCESS : NEUTRAL);
        }
        
        in.close();
    }
}