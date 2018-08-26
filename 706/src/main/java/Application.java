import java.util.Scanner;
import java.util.stream.IntStream;

class Application {
    private final static Scanner input = new Scanner(System.in);
    private static int numCols;
    private static int numRows;

    private static final char HORIZONTAL_LINE = '-';
    private static final char VERTICAL_LINE =   '|';
    private static final char BLANK_SPACE =     ' ';

    private static int[] horizontalRange;
    private static int[] upperRange;
    private static int[] lowerRange;
    private static int topRow   = 0;
    private static int midRow   = 0;
    private static int botRow   = 0;
    private static int leftCol  = 0;
    private static int rightCol = 0;

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

    static class Digit {
        private int number;

        private char[][] figure;
        char[][] getFigure() {
            return figure;
        }

        private boolean isNumContainedIn(int[] arr) {
            for (int num : arr) {
                if (num == number) { return true; }
            }
            return false;
        }

        private final int[] topNums  = new int[]{    2, 3,    5, 6, 7, 8, 9, 0 };
        private final int[] midNums  = new int[]{    2, 3, 4, 5, 6,    8, 9    };
        private final int[] botNums  = new int[]{    2, 3,    5, 6,    8, 9, 0 };
        private final int[] upLNums  = new int[]{          4, 5, 6,    8, 9, 0 };
        private final int[] upRNums  = new int[]{ 1, 2, 3, 4,       7, 8, 9, 0 };
        private final int[] lowLNums = new int[]{    2,          6,    8,    0 };
        private final int[] lowRNums = new int[]{ 1,    3, 4, 5, 6, 7, 8, 9, 0 };

        private boolean top() {
            return isNumContainedIn(topNums);
        }
        private boolean mid() {
            return isNumContainedIn(midNums);
        }
        private boolean bot() {
            return isNumContainedIn(botNums);
        }
        private boolean upL() {
            return isNumContainedIn(upLNums);
        }
        private boolean upR() {
            return isNumContainedIn(upRNums);
        }
        private boolean lowL() {
            return isNumContainedIn(lowLNums);
        }
        private boolean lowR() {
            return isNumContainedIn(lowRNums);
        }

        private void drawFigure() {
            for (char[] r : figure) { for (int i = 0; i < r.length; i++)
                r[i] = BLANK_SPACE;
            }

            if (top()) { for (int c : horizontalRange) {
                    figure[topRow][c] = HORIZONTAL_LINE;
                }
            }
            if (mid()) { for (int c : horizontalRange) {
                    figure[midRow][c]   = HORIZONTAL_LINE;
                }
            }
            if (bot()) { for (int c : horizontalRange) {
                    figure[botRow][c]   = HORIZONTAL_LINE;
                }
            }
            if (upL()) { for (int r : upperRange) {
                    figure[r][leftCol]  = VERTICAL_LINE;
                }
            }
            if (upR()) { for (int r : upperRange) {
                    figure[r][rightCol] = VERTICAL_LINE;
                }
            }
            if (lowL()) { for (int r : lowerRange) {
                    figure[r][leftCol]  = VERTICAL_LINE;
                }
            }
            if (lowR()) { for (int r : lowerRange) {
                    figure[r][rightCol] = VERTICAL_LINE;
                }
            }
        }

        Digit(int num) {
            number = num;
            figure = new char[numRows][numCols];
            setDrawLimits();
            drawFigure();
        }
    }

    private static void printDigits(Digit[] digits) {
        for (int i = 0; i < numRows; i++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (Digit digit : digits) {
                lineBuilder.append(digit.getFigure()[i]);
                if (digits[digits.length - 1] != digit) {
                    lineBuilder.append(BLANK_SPACE);
                }
            }
            System.out.println(lineBuilder.toString());
        }
    }

    public static void main(String[] args) {
        Digit[] digits;

        while (input.hasNextLine()) {
            try {
                String[] line = input.nextLine().split(" ");

                int digitSize = Integer.parseInt(line[0]);

                numRows = digitSize * 2 + 3;
                numCols = digitSize + 2;

                String number = line[1];
                digits = new Digit[number.length()];

                if (digitSize > 0) {
                    for (int i = 0; i < number.length(); i++) {
                        digits[i] = new Digit(Character.getNumericValue(number.charAt(i)));
                    }

                    printDigits(digits);
                    System.out.println();
                }
            } catch (Exception ignored) { }
        }
    }
}