import java.util.Scanner;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String ... args) {
        int n = 0;
        while(true) {
            int w = in.nextInt(), h = in.nextInt();
            in.nextLine();
            if (w == 0 && h == 0) return;
            char[][] maze = new char[h][w];
            for (int r = 0; r < h; r++)
                maze[r] = in.nextLine().toCharArray();

            out.printf("Maze #%d\n", n++);
        }
    }
}
