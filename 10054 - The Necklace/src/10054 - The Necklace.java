import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final String trivialOutput = "some beads may be lost";
    private static final int T = in.nextInt(), NUM_COLORS = 50;
    private static int t = 0;
    private static int[][] G = new int[NUM_COLORS][NUM_COLORS];
    private static int[] numBeadsPerColor = new int[NUM_COLORS];
    private static ArrayList<Integer> necklace = new ArrayList<>();

    private static boolean isOdd(int num) {
        return num % 2 == 1;
    }

    private static boolean parseTest() {
        int n = in.nextInt();
        for (int i = 0; i < NUM_COLORS; i++) {
            numBeadsPerColor[i] = 0;
            for (int j = 0; j < NUM_COLORS; j++)
                G[i][j] = 0;
        }
        for (int i = 0; i < n; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            G[a][b]++;
            G[b][a]++;
            numBeadsPerColor[a]++;
            numBeadsPerColor[b]++;
        }
        for (int numBeads : numBeadsPerColor)
            if (isOdd(numBeads))
                return false;

        return true;
    }

    private static void path(int pre) {
        for (int cur = 0; cur < NUM_COLORS; cur++)
            if (G[pre][cur] > 0) {
                G[pre][cur]--;
                G[cur][pre]--;
                path(cur);
            }
        necklace.add(pre + 1);
    }

    public static void main(String ... args) {
        while(++t<=T) {
            out.printf("%sCase #%d\n", t > 1 ? "\n" : "", t);

            if(!parseTest())
                out.println(trivialOutput);
            else {
                path(0);

                for (int pre = 0, cur = 1; cur < necklace.size(); pre++, cur++)
                    out.printf("%d %d\n", necklace.get(pre), necklace.get(cur));

                necklace.clear();
            }
        }

        in.close();
    }
}