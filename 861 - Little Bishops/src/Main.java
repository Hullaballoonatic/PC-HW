import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    private static int n, k;
    private static boolean[][] board;
    private static int totalPositions() {
        return board.length * board.length;
    }
    private static boolean terminatingCondition(int occupiedPositions) {
        return occupiedPositions == totalPositions() || totalPositions() - occupiedPositions < k;
    }
    private static boolean isVulnerable(int row, int col) {
        int r = row, c = col;
        while(r-->0 && c-->0) if(board[r][c]) return true;
        while(row-->0 && col++<board.length) if(board[r][c]) return true;
        return false;
    }

    private static int backtrack(int occupiedPositions){
        // terminating condition
        if(terminatingCondition(occupiedPositions)) return 0;

        int result = 0, r = occupiedPositions/n, c = occupiedPositions%n;

        // default case
        result += backtrack(occupiedPositions+1);

        // special case
        if(!isVulnerable(r, c)) {
            board[r][c] = true;
            result += backtrack(occupiedPositions+1);
            board[r][c] = false;
        }

        return result;
    }

    public static void main(String[] args) {
        while(true) {
            n = in.nextInt();
            if((k = in.nextInt()) == 0) { // when k == 0 either terminate or trivial solution
                if(n == 0) return;
                else out.println(1);
            } else {
                board = new boolean[n][n];
                out.println(backtrack(0));
            }
        }
    }
}