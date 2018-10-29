import java.util.*;

import static java.lang.Math.max;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int infinity = Integer.MAX_VALUE;
    private static int T = in.nextInt(), f, n;
    private static int[] distances;
    private static LinkedList<Edge>[] E;

    private static String postfix() {
        return T > 0 ? "\n\n" : "\n";
    }

    @SuppressWarnings("unchecked")
    private static void parseInput() {
        f = in.nextInt();
        n = in.nextInt();
        distances = new int[n + 1];
        E = new LinkedList[n + 1];

        for (int i = 1; i <= n; i++)
            E[i] = new LinkedList<>();

        for (int i = 1; i <= n; i++)
            distances[i] = i;

        for (int i = 1; i <= f; i++)
            distances[in.nextInt()] = 0;

        in.nextLine();

        while (in.hasNext()) {
            try {
                String[] edge = in.nextLine().split(" ");

                int a = Integer.parseInt(edge[0]);
                int b = Integer.parseInt(edge[1]);
                int weight = Integer.parseInt(edge[2]);

                E[a].add(new Edge(b, weight));
                E[b].add(new Edge(a, weight));
            } catch (NumberFormatException e) {
                break;
            }
        }
    }

    // returns the longest path to a fire station.
    // meaning there is no "goal"
    private static int dijkstra(int startNum, int[] G) {
        HashSet<Node> visited = new HashSet<>();
        PriorityQueue<Node> Q = new PriorityQueue<Node>(Comparator.comparingInt(o -> o.weight)) {{
            for (int i = 1; i < n; i++)
                if (distances[i] == 0)
                    add(new Node(distances[i], 0));
            add(new Node(startNum, 0));
        }};

        Node cur;

        // every vertex weight will be set to its shortest path
        while (!Q.isEmpty())
            if (visited.add(cur = Q.poll()) && E[cur.intersection] != null)
                for (Edge e : E[cur.intersection]) {
                    if (cur.weight + e.weight < G[e.to]) {
                        G[e.to] = cur.weight + e.weight;
                        Q.add(new Node(e.to, G[e.to]));
                    }
                }

        // get longest route to a fire station
        int max = G[1];
        for (int i = 2; i <= n; i++)
            max = max(max, G[i]);

        return max;
    }

    // run dijkstra on every possible position to place the new fireStation
    private static int solve() {
        int best = 0;
        int bestWeight = infinity;
        for (int intersection = 1; intersection <= n; intersection++)
            if (distances[intersection] != 0) {
                int weight = dijkstra(intersection, distances.clone());
                if (best == 0 || weight < bestWeight) {
                    best = intersection;
                    bestWeight = weight;
                }
            }
        return best;
    }

    public static void main(String[] args) {
        while (T-- > 0) {
            parseInput();
            out.printf("%d%s", f == n ? 1 : solve(), postfix());
        }
    }
}

class Edge {
    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Node {
    int intersection;
    int weight;
    Node(int intersection, int weight) {
        this.intersection = intersection;
        this.weight = weight;
    }
}