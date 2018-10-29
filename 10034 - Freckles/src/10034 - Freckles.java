import java.util.*;

import static java.lang.Math.sqrt;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();
    private static final double infinity = Double.MAX_VALUE;

    public static void main(String ... args) {
	    while(T-->0) {
	        int n = in.nextInt();
            Pos[] G = new Pos[n];

            for (int i = 0; i < n; i++)
                G[i] = new Pos(i, in.nextDouble(), in.nextDouble());

            // robot judge said presentation error when there were two newlines after the final case, and said wrong answer when there were none...
            out.printf("%.2f%s", prim(G), T > 0 ? "\n\n" : "\n");
        }
    }

    private static double prim(Pos[] G) {
        double[][] M = createAdjMatrix(G);
        Set<Pos> S = new HashSet<>();
        Set<Pos> V = new HashSet<>(Arrays.asList(G));
        S.add(G[0]);
        V.remove(G[0]);
        double distance = 0.0;

        while (!V.isEmpty()) {
            List<Pos> vs = new ArrayList<>(V);
            Pos to = vs.get(0);
            double E = infinity;
            for (Pos s : S)
                for (Pos v : vs)
                    if (s.label != v.label) {
                        double e = M[s.label][v.label];
                        if (e < E) {
                            E = e;
                            to = v;
                        }
                    }
            S.add(to);
            V.remove(to);
            distance += E;
        }

        return distance;
    }

    private static double[][] createAdjMatrix(Pos[] G) {
        double[][] M = new double[G.length][G.length];
        for (int i = 0; i < G.length; i++)
            for (int j = 0; j < G.length; j++)
                M[i][j] = G[i].distanceTo(G[j]);
        return M;
    }
}

class Pos {
    private double x, y;
    int label;

    double distanceTo(Pos o) {
        double dx = x - o.x, dy = y - o.y;
        return sqrt(dx * dx + dy * dy);
    }

    Pos(int i, double x, double y) {
        label = i;
        this.x = x;
        this.y = y;
    }
}