import java.util.*;

import static java.lang.Math.max;
import static java.lang.System.out;

/**
 * UVa 861
 * Little Bishops
 *
 * We have an n * n sized chess board with k bishops
 * How many ways can we place those bishops so that none can attack one another?
 *
 * 1 <= n <= 8
 * 0 <= k <= n*n
 *
 */

/*
 * bishops in black squares can never attack those in white squares, so lets consider two boards
 * then lets turn the diagonals to position up and down, then sort them by length. you'll always have
 * one board where all lengths are odd, and the other where they are even
 *
 * example 5x5 board
 *  XOXOX     X X X    O O
 *  OXOXO      X X    O O O
 *  XOXOX --> X X X +  O O
 *  OXOXO      X X    O O O
 *  XOXOX     X X X    O O
 *
 *              |       |
 *              V       V
 *
 *            X
 *            X       OO
 *            XXX     OO
 *            XXX     OOOO
 *            XXXXX   OOOO
 */
class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            Board.n = in.nextInt();
            int k = in.nextInt();

            if (Board.n == 0 && k == 0) return;
            if (Board.n <= 1) out.println(1);
            else {
                int[] white = new White().possibilities;
                int[] black = new Black().possibilities;

                long result = 0;

                for (int i = max(0, k - (black.length - 1)); i < white.length && i <= k; ++i)
                    result += white[i] * black[k - i];

                out.println(result);
            }
        }
    }
}

abstract class Board {
    static int n;

    abstract int colLength(int col);

    abstract int numCols();

    int[] possibilities;

    Board() {
        int[] colLengths = new int[numCols()];
        for (int col = 0; col < colLengths.length; ++col)
            colLengths[col] = colLength(col);

        possibilities = new int[colLengths.length + 1];
        possibilities[0] = 1;

        for (int col = 0; col < colLengths.length; ++col) {
            possibilities[col + 1] = 0;
            for (int j = col; j >= 0; --j)
                if (colLengths[col] > j)
                    possibilities[j+1] += possibilities[j] * (colLengths[col] - j);
        }
    }
}

class White extends Board {
    int colLength(int col) {
        return col / 2 * 2 + 1;
    }

    int numCols() {
        return n;
    }
}

class Black extends Board {
    int colLength(int col) {
        return col / 2 * 2 + 2;
    }

    int numCols() {
        return n - 1;
    }
}