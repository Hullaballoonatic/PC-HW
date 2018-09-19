import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.System.out;

class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int numTestCases = in.nextInt();

        while (numTestCases-->0) {
            int lowestSum = Integer.MAX_VALUE;

            int[] streetNums = new int[in.nextInt()];

            for (int i = 0; i < streetNums.length; i++)
                streetNums[i] = in.nextInt();

            for (int i : streetNums) {
                int currentSum = 0;

                for (int j : streetNums)
                    currentSum += abs(i - j);

                lowestSum = min(currentSum, lowestSum);
            }

            out.println(lowestSum);
        }
    }
}