import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static boolean[][] G;

    private static void fill(int i) {
        if (!visited[i]) {
            visited[i] = true;
            for (int j = 0; j < G.length; j++) {
                if (G[i][j]) {
                    fill(j);
                }
            }
        }
    }

    private static int bfs(int start) {
        Set<Integer> v = new HashSet<Integer>() {{
            add(start);
        }};
        int count = 0;
        for (int i = 0; i < G.length; i++) {
            if (!v.add(i)) {
                fill(i);
                count++;
            }
        }
        return count;
    }

    public static void main(String ... args) {
        int t = 1, n;
        while ((n = in.nextInt()) != 0) {
            in.nextLine();

            G = new boolean[n][n];
            Set<String> cities = new TreeSet<String>() {{
                for (int i = 0; i < n; i++)
            }};
            for (int i = 0; i < n; i++)
                cities[i] = in.nextLine();

            for (int i = 0, r = Integer.parseInt(in.nextLine()); i < r; i++) {
                String[] tmp = in.nextLine().split(" ");
                int a = cities;
                int b = map.getOrDefault(tmp[1],-1);
                if (a != b && a >= 0 && a < n && b >= 0 && b < n) {
                    G[a][b]=true;
                    G[b][a]=true;
                }
            }

            Set<String> cameras = new TreeSet<String>(Comparator.naturalOrder()) {{
               for (int i = 0, start = bfs(-1); i < n; i++)
                   if (bfs(i) > start)
                       add(cities);
            }};
            int count = 0;
            for (int i = 0, start = bfs(-1); i < n; i++)
                if (bfs(i)>start)
                    camera[count++] = ;

            Arrays.sort(camera, 0, count);

            out.printf("%sCity map #%d: %d camera(s) found\n", t > 1 ? "\n" : "", t++, count);
            for (String cam : camera) out.println(cam);
        }
    }
}