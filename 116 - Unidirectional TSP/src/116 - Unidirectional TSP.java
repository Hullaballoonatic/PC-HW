import java.util.Scanner;

class Main {
    private static final Scanner in = new Scanner(System.in);


    public static void main(String ... args) {
	    while(in.hasNext()) {
	        Matrix cur = new Matrix(in.nextInt(), in.nextInt(), in);
        }
    }
}

class Matrix {
    int m, n;
    int[][] data;

    int[] possibleMoves(int r, int c) {
        return new int[] { data[(r+1)%m][c],data[r][c],data[(r-1)%m][c] };
    }

    int pathWeight(int[] rows, int[] cols) {
        int result = 0;
        for (int i = 0; i < rows.length; i++)
            result += data[rows[i]][cols[i]];
        return result;
    }

    private int minIndex(int[] values) {
        int curMinValue = values[0];
        int curMinIndex = 0;
        for (int i = 0; i < values.length; i++)
            if (values[i] < curMinValue) {
                curMinIndex = i;
                curMinValue = values[i];
            }
        return curMinIndex;
    }

    private int[] findPath() {
        int[] pathRow = new int[n], pathCol = new int[n];
        for(int c = 1; c < n; c++) {
            int minValueIndex = minIndex(data[c])
        }
    }

    Matrix(int m, int n, Scanner in) {
        this.m = m;
        this.n = n;
        data = new int[m][n];
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                data[r][c] = in.nextInt();
    }
}