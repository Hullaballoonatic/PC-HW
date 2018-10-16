import java.util.*;

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
 */
class Main {
    private static final Scanner in = new Scanner(System.in);

    private static int n, k;

    private static boolean hasNoViablePositions(boolean[] board) {
        for (int i = 0; i < n*n; i++) if (!board[i]) return false;
        return true;
    }

    private static int changePos(int pos, int r, int c) {
        return pos + (r * n + c);
    }

    private static boolean[] placeBishop(boolean[] board, int pos) {
        boolean[] result = board.clone();
        for (int i = 0; i < n; i++)
            try {
                result[changePos(pos, -i, -i)] = true;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            } finally {
                try {
                    result[changePos(pos, -i, +i)] = true;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                } finally {
                    try {
                        result[changePos(pos, +i, -i)] = true;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    } finally {
                        try {
                            result[changePos(pos, +i, +i)] = true;
                        } catch (ArrayIndexOutOfBoundsException ignored) { }
                    }
                }
            }
        return result;
    }

    // position is index in our board if it was a single array with each row laid end to end. we can compute r and c from there
    private static int backtrack(boolean[] board, int pos, int numPlaced) {
        // We are counting by placing a single bishop every time
        // If we have placed every bishop, we found one way!
        if (numPlaced == k) return 1;
        // If we have more bishops to place than there are possible positions to place them, there are no viable permutations from here
        if (pos == n * n || hasNoViablePositions(board)) return 0;

        // each board position can result in multiple successful placements
        int result = 0;

        if (!board[pos])
            result += backtrack(placeBishop(board, pos), pos + 1,numPlaced+1);

        // we also need to check how it turns out if we don't place a bishop here
        result += backtrack(board, pos + 1, numPlaced);

        return result;
    }

    public static void main(String[] args) {
        while(true) {
            n = in.nextInt();
            k = in.nextInt();
            if (n == 0 && k == 0) return;
            else out.println(backtrack(new boolean[n*n], 0, 0));
        }
    }
}