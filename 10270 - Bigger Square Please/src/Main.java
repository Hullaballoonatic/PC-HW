import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

class Square {
    private int size, l, x, y;
    private List<Square> pieces = new ArrayList<>();

    private boolean isWhole() {
        return pieces.isEmpty();
    }

    private int sumOfPieces() {
        int sum = 0;
        for (Square square : pieces) {
            sum += square.size;
        }
        return sum;
    }

    private void shatterEven() {
        for (int newX = 0; newX < l; newX+=l/2)
            for (int newY = 0; newY < l; newY+=l/2)
                pieces.add(new Square(l / 2, x + newX, y + newY));
    }

    private void shatterOdd() {

    }

    private void shatter(int limit) {
        if (l % 2 == 0)
            shatterEven();
        else
            shatterOdd();

    }

    private void combine() {

    }

    Square(int l) {
        x = 1;
        y = 1;
        this.l = l;
        size = l*l;
        pieces = shatter(l-1, 1);
    }

    private Square(int x, int y, int l) {
        this.x = x;
        this.y = y;
        this.l = l;
        this.size = l*l;
    }

    void print() {
        out.println(pieces.size());
        for (Square square : pieces)
            out.println(square.toString());
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", x, y, l);
    }
}

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();

    public static void main(String[] args) {
        while(T-->0)
            (new Square(in.nextInt())).print();
    }
}
