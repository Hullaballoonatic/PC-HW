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

            String[] name = new String[n];
            G = new boolean[n][n];
            HashMap<String,Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                name[i]=in.nextLine();
                map.put(name[i], i);
            }

            for (int i = 0, r = Integer.parseInt(in.nextLine()); i < r; i++) {
                String[] city = in.nextLine().split(" ");
                int a = map.getOrDefault(city[0],-1);
                int b = map.getOrDefault(city[1],-1);
                if (a != b && a >= 0 && a < n && b >= 0 && b < n) {
                    G[a][b]=true;
                    G[b][a]=true;
                }
            }

            String[] camera = new String [n];
            int count = 0;
            for (int i = 0, start = bfs(-1); i < n; i++)
                if (bfs(i)>start)
                    camera[count++] = name[i];

            Arrays.sort(camera, 0, count);

            out.printf("%sCity map #%d: %d camera(s) found\n", t > 1 ? "\n" : "", t++, count);
            for (String cam : camera) out.println(cam);
        }
    }
}