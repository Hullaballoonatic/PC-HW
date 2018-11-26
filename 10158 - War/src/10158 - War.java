import java.util.*;

import static java.lang.System.out;

// I used the explanation here: http://www.algorithmist.com/index.php/UVa_10158
class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final DisjoinSet G = new DisjoinSet(in.nextInt());

    private static boolean areFriends(int x, int y) {
        return G.find(x) == G.find(y);
    }

    private static boolean areEnemies(int x, int y) {
        return G.enemy[G.find(x)].contains(G.find(y));
    }

    private static boolean setFriends(int x, int y) {
        if (areEnemies(x, y)) return false;
        G.union(x, y);
        return true;
    }

    private static boolean setEnemies(int x, int y) {
        if (areFriends((x = G.find(x)), (y = G.find(y)))) return false;
        G.enemy[x].add(y);
        G.enemy[y].add(x);
        for (int i : G.enemy[x])
            G.union(y, i);
        for (int i : G.enemy[y])
            G.union(y, i);
        return true;
    }

    public static void main(String... args) {
        while (true) {
            int c = in.nextInt(), x = in.nextInt(), y = in.nextInt();
            switch (c) {
                case 0: {
                    if (x == 0 && y == 0) return;
                    break;
                }
                case 1: {
                    if (!setFriends(x, y)) out.println(-1);
                    break;
                }
                case 2: {
                    if (!setEnemies(x, y)) out.println(-1);
                    break;
                }
                case 3: {
                    out.println(areFriends(x, y) ? 1 : 0);
                    break;
                }
                case 4: {
                    out.println(areEnemies(x, y) ? 1 : 0);
                    break;
                }
                default: break;
            }
        }
    }
}

// reference: https://en.wikipedia.org/wiki/Disjoint-set_data_structure
class DisjoinSet {
    private int[] parent, rank;
    Set<Integer>[] enemy; // enemy is a unique to this problem. I can't be fucked to make this a generic class like I would if this was a library or something, because one probably already exists...

    DisjoinSet(int n) {
        parent = new int[n];
        rank = new int[n];
        enemy = new HashSet[n];
        for (int i = 0; i < n; i++)
            makeSet(i);
    }

    private void makeSet(int i) {
        parent[i] = i;
        rank[i] = 1;
        enemy[i] = new HashSet<>();
    }

    int find(int i) {
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    void union(int a, int b) {
        int A = find(a), B = find(b);
        if (A != B) {
            if (rank[A] >= rank[B]) {
                parent[B] = A;
                for (int i : enemy[B])
                    enemy[A].add(i);
            } else {
                parent[A] = B;
                for (int i : enemy[A])
                    enemy[B].add(i);
            }
            if (rank[A] == rank[B])
                rank[A]++;
        }
    }
}