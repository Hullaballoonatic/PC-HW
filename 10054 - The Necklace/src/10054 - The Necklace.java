import java.io.*;

import static java.lang.System.out;

class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final String trivialOutput = "some beads may be lost";
    private static final int NUM_COLORS = 50;
    private static int[][] G = new int[NUM_COLORS][NUM_COLORS];
    private static int[] numBeadsPerColor = new int[NUM_COLORS];
    private static int[] path = new int[1001];
    private static int it = 0;

    private static void createPath(int cur) {
        for (int nxt = 0; nxt < NUM_COLORS; nxt++) {
            if (G[cur][nxt] > 0) {
                G[cur][nxt]--;
                G[nxt][cur]--;
                createPath(nxt);
            }
        }

        path[it] = cur + 1;
        it++;
    }

    private static boolean input() throws IOException {
        int N = Integer.valueOf(in.readLine());
        for (int n = 0; n < N; n++) {
            String tmp[] = in.readLine().split(" ");
            int a = Integer.valueOf(tmp[0]) - 1, b = Integer.parseInt(tmp[1]) - 1;
            G[a][b]++;
            G[b][a]++;
            numBeadsPerColor[a]++;
            numBeadsPerColor[b]++;
        }
        for (int numBeads : numBeadsPerColor) if (numBeads % 2 == 1) return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        //PrintStream out = new PrintStream(new File("src\\out.txt"));
        for (int t = 1, T = Integer.valueOf(in.readLine()); t <= T; t++) {
            out.printf("%sCase #%d\n", t > 1 ? "\n" : "", t);
            if (input()) {
                it = 0;
                createPath(0);
                for (int cur = 0, nxt = 1; nxt < it; cur++, nxt++)
                    out.printf("%d %d\n", path[cur], path[nxt]);
            } else out.println(trivialOutput);
        }
        in.close();
    }
}