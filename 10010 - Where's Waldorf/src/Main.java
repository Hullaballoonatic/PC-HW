import java.io.PrintStream;
import java.util.Scanner;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static byte m, n, r, c;
    private static char[][] table;
    private static String word;
    private static final int[][] offsets = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

    public static void main(String[] args) {
        int numCases = in.nextInt();

        while(numCases-->0) {
            m = in.nextByte();
            n = in.nextByte();
            table = new char[m][n];
            in.nextLine();
            for (r = 0; r < m; r++) {
                String tmp = in.nextLine();
                for (c = 0; c < n; c++) {
                    table[r][c] = Character.toLowerCase(tmp.charAt(c));
                }
            }

            byte k = in.nextByte();
            in.nextLine();
            for (byte i = 0; i < k; i++) {
                word = in.nextLine().toLowerCase();
                findCoords();
            }
            if (numCases > 0) out.println();
        }
    }

    private static void findCoords() {
        for (char letter : word.toCharArray()) {
            for (r = 0; r < m; r++) {
                for (c = 0; c < n; c++) {
                    if (table[r][c] == letter) {
                        for (int[] off : offsets) {
                            if (search(off)) {
                                out.println(Integer.toString(r+1) + " " + Integer.toString(c+1));
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean search(int[] off) {
        StringBuilder check = new StringBuilder();
        try {
            for (int i = 0; i < word.length(); i++) {
                char tmp = table[r + off[0]*i][c + off[1]*i];
                if (tmp != word.charAt(i)) break;
                if (check.append(tmp).toString().equals(word)) return true;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }
}