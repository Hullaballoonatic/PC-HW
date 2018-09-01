import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    private final static Scanner input = new Scanner(System.in);
    private static int curDigit;
    private static char[][] curLCD;
    private static int numCols;
    private static int numRows;

    private static final char HORIZONTAL_LINE = '-';
    private static final char VERTICAL_LINE =   '|';
    private static final char BLANK_SPACE =     ' ';

    private static boolean isNumContainedIn(int[] arr) {
        for (int num : arr) {
            if (num == curDigit) { return true; }
        }
        return false;
    }

    private static int[] horizontalRange;
    private static int[] upperRange;
    private static int[] lowerRange;
    private static int topRow   = 0;
    private static int midRow   = 0;
    private static int botRow   = 0;
    private static int leftCol  = 0;
    private static int rightCol = 0;

    private static final int[] topNums  = new int[]{    2, 3,    5, 6, 7, 8, 9, 0 };
    private static final int[] midNums  = new int[]{    2, 3, 4, 5, 6,    8, 9    };
    private static final int[] botNums  = new int[]{    2, 3,    5, 6,    8, 9, 0 };
    private static final int[] upLNums  = new int[]{          4, 5, 6,    8, 9, 0 };
    private static final int[] upRNums  = new int[]{ 1, 2, 3, 4,       7, 8, 9, 0 };
    private static final int[] lowLNums = new int[]{    2,          6,    8,    0 };
    private static final int[] lowRNums = new int[]{ 1,    3, 4, 5, 6, 7, 8, 9, 0 };

    private static boolean top() {
        return isNumContainedIn(topNums);
    }
    private static boolean mid() {
        return isNumContainedIn(midNums);
    }
    private static boolean bot() {
        return isNumContainedIn(botNums);
    }
    private static boolean upL() {
        return isNumContainedIn(upLNums);
    }
    private static boolean upR() {
        return isNumContainedIn(upRNums);
    }
    private static boolean lowL() {
        return isNumContainedIn(lowLNums);
    }
    private static boolean lowR() {
        return isNumContainedIn(lowRNums);
    }

    private static void setDrawLimits() {
        if (numRows > 0 && numCols > 0) {
            midRow   = (numRows - 1) / 2;
            botRow   = (numRows - 1);
            rightCol = (numCols - 1);
            horizontalRange = IntStream.rangeClosed(leftCol + 1, rightCol - 1).toArray();
            upperRange      = IntStream.rangeClosed(topRow  + 1, midRow   - 1).toArray();
            lowerRange      = IntStream.rangeClosed(midRow  + 1, botRow   - 1).toArray();
        }
    }

    private static void drawFigure() {
        for (char[] r : curLCD) { for (int i = 0; i < r.length; i++)
            r[i] = BLANK_SPACE;
        }

        if (top()) { for (int c : horizontalRange) {
            curLCD[topRow][c] = HORIZONTAL_LINE;
        }
        }
        if (mid()) { for (int c : horizontalRange) {
            curLCD[midRow][c]   = HORIZONTAL_LINE;
        }
        }
        if (bot()) { for (int c : horizontalRange) {
            curLCD[botRow][c]   = HORIZONTAL_LINE;
        }
        }
        if (upL()) { for (int r : upperRange) {
            curLCD[r][leftCol]  = VERTICAL_LINE;
        }
        }
        if (upR()) { for (int r : upperRange) {
            curLCD[r][rightCol] = VERTICAL_LINE;
        }
        }
        if (lowL()) { for (int r : lowerRange) {
            curLCD[r][leftCol]  = VERTICAL_LINE;
        }
        }
        if (lowR()) { for (int r : lowerRange) {
            curLCD[r][rightCol] = VERTICAL_LINE;
        }
        }
    }

    private static void printDigits(char[][][] digits) {
        for (int i = 0; i < numRows; i++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (char[][] digit : digits) {
                lineBuilder.append(digit[i]);
                if (digits[digits.length - 1] != digit) {
                    lineBuilder.append(BLANK_SPACE);
                }
            }
            System.out.println(lineBuilder.toString());
        }
    }

    public static void main(String[] args) {
        int s;
        String n;

        while (true) {
            s = input.nextInt();
            n = input.next();
            if (s == 0 && n.equals("0")) {
                return;
            } else {
                numRows = s * 2 + 3;
                numCols = s + 2;

                char[][][] digits = new char[n.length()][numRows][numCols];

                for(int d = 0; d < digits.length; d++) {
                    curDigit = n.charAt(d) - '0';
                    curLCD = new char[numRows][numCols];
                    setDrawLimits();
                    drawFigure();
                    digits[d] = curLCD;
                }

                printDigits(digits);
                System.out.println();
            }
        }
    }
}