import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        int fieldNum = 0;
        int numRows = 0;
        int numCols = 0;
        int curRow = 0;
        int curCol = 0;
        char[][] minefield = new char[0][0];

        while (sc.hasNextLine()) {
            if (curRow == numRows) {
                if (numRows > 0) {
                    printField(flagField(minefield));
                }
                fieldNum++;
                System.out.printf("\nField #%d:\n", fieldNum);
                numRows = sc.nextInt();
                numCols = sc.nextInt();
                minefield = new char[numRows][numCols];
                curRow = 0;
            } else {
                while (curCol < numCols) {
                    minefield[curRow][curCol] = sc.findInLine(".").charAt(curCol);
                    curCol++;
                }
                curRow++;
            }
        }
    }

    private static char[][] flagField(char[][] minefield) {
        //todo convert
        return minefield;
    }

    private static void printField(char[][] minefield) {
        for (char[] row : minefield) {
            StringBuilder sb = new StringBuilder();
            for (char col : row) {
                sb.append(col);
            }
            System.out.println(sb.toString());
        }
    }
}