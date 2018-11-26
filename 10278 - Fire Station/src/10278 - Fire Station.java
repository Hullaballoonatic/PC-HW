import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;

class Edge {
    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();
    private static final int oo = (int) 1e24;
    private static LinkedList<Edge>[] G;
    private static final Set<Integer> fireStations = new HashSet<>();
    private static int[] intersections;

    private static int dijkstra(int start) {
        int[] distances = new int[intersections.length + 1];
        Arrays.fill(distances, oo);
        for (int fireStation : fireStations)
            distances[fireStation] = 0;
        distances[start] = 0;

        PriorityQueue<Integer> q = new PriorityQueue<Integer>(Comparator.comparingInt(a -> distances[a])) {{
            for (int intersection : intersections)
                add(intersection);
        }};

        Integer cur;

        while((cur = q.poll()) != null) {
            if (fireStations.contains(cur))
                return distances[cur];
            for (Edge e : G[cur])
                if (fireStations.contains(e.to)) {
                    return distances[cur] + e.weight;
                } else distances[e.to] = min(distances[e.to], distances[cur] + e.weight);
        }

        throw(new NullPointerException());
    }

    @SuppressWarnings("unchecked")
    public static void main(String... args) {
        while(T-->0) {
            int bestPlacement = 1, bestFurthest = oo;
            int numFireStations = in.nextInt(), numIntersections = in.nextInt();

            intersections = new int[numIntersections];
            for (int num = 0; num < intersections.length; num++)
                intersections[num] = num + 1;

            fireStations.clear();
            for(int it = 0; it < numFireStations; it++)
                fireStations.add(in.nextInt());

            if (numFireStations != numIntersections) {
                G = new LinkedList[numIntersections + 1];
                for (int ignored : intersections) {
                    int a = in.nextInt(), b = in.nextInt(), w = in.nextInt();
                    if (G[a] == null) G[a] = new LinkedList<>();
                    if (G[b] == null) G[b] = new LinkedList<>();
                    G[a].add(new Edge(b, w));
                    G[b].add(new Edge(a, w));
                }

                out.println(" fire station | intersection |   distance   \n" +
                            "--------------+--------------+--------------");
                for (int newFireStation : intersections)
                    if (fireStations.add(newFireStation)) {
                        int curFurthest = -1;

                        for (int intersection : intersections) {
                            int distance = dijkstra(intersection);
                            out.printf("            %d |            %d | %d\n", newFireStation, intersection, distance);
                            curFurthest = max(curFurthest, distance);
                        }
                        if (curFurthest < bestFurthest) {
                            bestFurthest = curFurthest;
                            bestPlacement = newFireStation;
                        }

                        fireStations.remove(newFireStation);
                    }
            }

            out.printf("%d\n%s", bestPlacement, T > 0 ? "\n" : "");
        }
    }
}