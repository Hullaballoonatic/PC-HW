import java.util.Scanner;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int INFINITE = -1, N_MIN = 1, N_MAX = 50;
    private static final int[] answers = new int[N_MIN + N_MAX];
    private static int T = in.nextInt();

    public static void main(String ... args) {
        answers[0] = INFINITE;

        for (int i = N_MIN; i <= N_MAX; i+=2) {
            answers[i] = answers[i-1] + i + 1;
            answers[i+1] = answers[i] + i + 1;
        }

        while(T-->0)
            out.println(answers[in.nextInt()]);
    }
}