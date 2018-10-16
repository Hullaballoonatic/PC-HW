import java.util.*;

import static java.lang.Math.abs;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int n = in.nextInt();
    private static final int WIDTH = 4, OPEN = 0, LOWER_BOUND = 0, UPPER_BOUND = WIDTH - 1;

    private enum Action {
        U(-1,  0),
        D( 1,  0),
        L( 0, -1),
        R( 0,  1);

        int offR, offC;

        Action(int offR, int offC) {
            this.offR = offR;
            this.offC = offC;
        }
    }

    static class Puzzle {
        private int [][] tiles = new int[WIDTH][WIDTH];
        private int openR = 0, openC = 0;
        private String sequence;

        List<Action> actions() {
            return new ArrayList<Action>() {{
               if (openR > LOWER_BOUND) add(Action.U);
               if (openR < UPPER_BOUND) add(Action.D);
               if (openC > LOWER_BOUND) add(Action.L);
               if (openC < UPPER_BOUND) add(Action.R);
            }};
        }

        Puzzle() {
            sequence = "";
            for(int r = 0, tile; r < WIDTH; ++r)
                for(int c = 0; c < WIDTH; ++c) {
                    if ((tile = in.nextInt()) == OPEN) {
                        openR = r;
                        openC = c;
                    }
                    tiles[r][c] = tile;
                }
        }

        Puzzle(Puzzle copy) {
            openR = copy.openR;
            openC = copy.openC;
            sequence = copy.sequence;
            for(int i = 0; i < WIDTH; ++i)
                System.arraycopy(copy.tiles[i], 0, tiles[i], 0, WIDTH);
        }

        private Puzzle act(Action action) {
            sequence += action.name();
            int r = action.offR + openR, c = action.offC + openC;
            tiles[openR][openC] = tiles[r][c];
            tiles[r][c] = OPEN;
            openR = r;
            openC = c;
            return this;
        }

        int value() {
            int result = sequence.length();

            for (int r = 0; r < WIDTH; ++r)
                for (int c = 0; c < WIDTH; ++c)
                    if (tiles[r][c] != OPEN)
                        result += abs(c - ((tiles[r][c] - 1) % WIDTH)) + abs(r - ((tiles[r][c] - 1) / WIDTH));

            return result;
        }

        private boolean isSolution() {
            int tileNum = 1;

            for(int i = 0; i < WIDTH*WIDTH-1; ++i)
                if (tiles[i / WIDTH][i % WIDTH] != tileNum) return false;
                else tileNum++;

            return true;
        }
        
        long hashState() {
            int state = 0;

            for (int r = 0; r < WIDTH; r++)
                for (int c = 0; c < WIDTH; c++) {
                    state <<= WIDTH;
                    state += tiles[r][c];
                }

            return state;
        }

        private boolean hasNoSolution() {
            int inversions = 0;
            for(int i = 0; i < WIDTH*WIDTH; ++i)
                if (tiles[i / WIDTH][i % WIDTH] != 0)
                    for (int j = i + 1; j < WIDTH * WIDTH; ++j)
                        if (tiles[j / WIDTH][j % WIDTH] != 0)
                            if (tiles[i / WIDTH][i % WIDTH] > tiles[j / WIDTH][j % WIDTH])
                                inversions++;

            return inversions % 2 == (WIDTH - openR) % 2;
        }
    }

    private static String bfs(Puzzle root) {
        Set<Long> visited = new HashSet<>();

        PriorityQueue<Puzzle> q = new PriorityQueue<Puzzle>(Comparator.comparingInt(Puzzle::value)) {
            @Override
            public boolean add(Puzzle e) {
                if (visited.contains(e.hashState())) return false;
                else return super.add(e);
            }
        };

        if(root.hasNoSolution()) return "This puzzle is not solvable.";

        q.add(root);

        while(q.size() > 0) {
            Puzzle p = q.remove();

            visited.add(p.hashState());

            if(p.isSolution()) return p.sequence;

            for (Action action : p.actions())
                q.add(new Puzzle(p).act(action));

        }

        return "This puzzle is not solvable.";
    }

    public static void main(String[] args) {
        while(n-->0)
            out.println(bfs(new Puzzle()));
    }
}