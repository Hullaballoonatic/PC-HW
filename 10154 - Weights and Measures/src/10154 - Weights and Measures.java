import java.util.*;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;

/**
 * 11.6.3 Weights and Measures
 * A turtle named Mack, to avoid being cracked, has enlisted your advice as to the order
 * in which turtles should be stacked to form Yertle the Turtle’s throne. Each of the 5,607
 * turtles ordered by Yertle has a different weight and strength. Your task is to build the
 * largest stack of turtles possible.
 */

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int n = 0;

    /**
     * Input
     * Standard input consists of several lines, each containing a pair of integers separated by
     * one or more space characters, specifying the weight and strength of a turtle. The weight
     * of the turtle is in grams. The strength, also in grams, is the turtle’s overall carrying
     * capacity, including its own weight. That is, a turtle weighing 300 g with a strength of
     * 1,000 g can carry 700 g of turtles on its back. There are at most 5,607 turtles.
     */
    private static Turtle[] input() {
        return new ArrayList<Turtle>() {{
            while(true)
                try {
                    add(new Turtle(in.nextInt(), in.nextInt()));
                } catch (Exception ignored) {
                    break;
                }
            sort(Comparator.comparingInt(o -> o.strength));
            n = size();
        }}.toArray(new Turtle[n]);
    }

    /**
     * Output
     * Your output is a single integer indicating the maximum number of turtles that can be
     * stacked without exceeding the strength of any one.
     */
    private static void output(int numTurtles) {
        out.println(numTurtles);
    }

    private static int solution(Turtle[] a) {
        int[] cur = new int[n + 1], pre = new int[n + 1];
        Arrays.fill(cur, MAX_VALUE);

        cur[1] = a[0].weight;
        pre[0] = MAX_VALUE;
        int numTurtles = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++)
                if (MAX_VALUE > (pre[j] = min(cur[j], a[i].carryCapacity >= cur[j - 1] ? cur[j - 1] + a[i].weight : MAX_VALUE)))
                    numTurtles = max(numTurtles, j);

            int[] tmp = cur;
            cur = pre;
            pre = tmp;
        }
        return numTurtles;
    }

    public static void main(String[] args) {
        output(solution(input()));
    }
}

class Turtle {
    int weight, strength, carryCapacity;

    Turtle(int weight, int strength) {
        this.weight = weight;
        this.strength = strength;
        carryCapacity = strength - weight;
    }
}