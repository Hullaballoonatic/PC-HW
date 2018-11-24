import java.util.*;

import static java.lang.System.out;

enum Move {
    DL(-1, 1), UL(-1, 0), U(0, -1), UR(1, -1), DR(1, 0), D(0, 1);

    int dx, dy;

    Move(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}

class Cell {
    private int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Cell move(Move move) {
        return new Cell(x + move.dx, y + move.dy);
    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }
}

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final Move[] moves = new Move[] { Move.DL, Move.UL, Move.U, Move.UR, Move.DR, Move.D };
    private static final ArrayList<Cell> solution = new ArrayList<Cell>() {{
        Cell cur = new Cell(0, 0);
        int m = 0, step = 0, layer = 1, repeat = 99998;

        add(cur);
        add(cur.move(moves[5]));

        while (repeat-->0) {
            if (m == 0 && step == layer - 1 || step == layer) {
                m++;
                step = 0;
            }

            if (m == 6) {
                add(cur.move(moves[5]));

                m = 0;
                step = 0;
                layer++;
            }

            add(cur.move(moves[m]));
            step++;
        }
    }};

    public static void main(String... args) {
        while(true) {
            try {
                out.println(solution.get(in.nextInt() - 1).toString());
            } catch (Exception ignored) {
                return;
            }
        }
    }
}

