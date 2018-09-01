import java.util.Scanner;

class Main {
    private final static Scanner input = new Scanner(System.in);

    private static int[][] minefield;
    private final static int MINE = -10;
    private static Boolean isMine(int r, int c) {
        return minefield[r][c] < 0;
    }

    private static int fieldNum = 0;

    private static void incrementNeighbors(int r, int c) {
        Boolean above = r > 0;
        Boolean below = r < minefield.length - 1;
        Boolean left = c > 0;
        Boolean right = c < minefield[0].length - 1;
        if (above) minefield[r - 1][c]++;
        if (below) minefield[r + 1][c]++;
        if (left) minefield[r][c - 1]++;
        if (right) minefield[r][c + 1]++;
        if (above && left) minefield[r - 1][c - 1]++;
        if (above && right) minefield[r - 1][c + 1]++;
        if (below && left) minefield[r + 1][c - 1]++;
        if (below && right) minefield[r + 1][c + 1]++;
    }

    private static void printField() {
        if (fieldNum != 0) {
            System.out.println();
        }
        fieldNum++;
        System.out.printf("Field #%d:\n", fieldNum);
        for (int r = 0; r < minefield.length; r++) {
            StringBuilder output = new StringBuilder();
            for (int c = 0; c < minefield[0].length; c++) {
                if (isMine(r,c)) {
                    output.append('*');
                } else {
                    output.append(minefield[r][c]);
                }
            }
            System.out.println(output.toString());
        }
    }

    public static void main(String[] args) {
        while (input.hasNextLine()) {
            if (input.hasNextInt()) {
                minefield = new int[input.nextInt()][input.nextInt()];
                input.nextLine();
            } else if (minefield.length > 0 && minefield[0].length > 0) {
                for(int r = 0; r < minefield.length; r++) {
                    String line = input.nextLine();
                    for(int c = 0; c < minefield[0].length; c++) {
                        if(line.charAt(c) == '*') {
                            minefield[r][c] = MINE;
                            incrementNeighbors(r, c);
                        }
                    }
                }
                printField();
            }
        }
    }
}