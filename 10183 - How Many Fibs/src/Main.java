import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final BigInteger TWO = new BigInteger("2"), MAX = new BigInteger("10").pow(100);
    private static ArrayList<BigInteger> fibs = new ArrayList<BigInteger>() {{
        add(ONE);
        add(TWO);
        int i = 2;
        do {
            add(get(i-1).add(get(i-2)));
            i++;
        } while(get(i-1).compareTo(MAX) <= 0);
    }};

    public static void main(String[] args) {
        while(true) {
            BigInteger A = in.nextBigInteger(), B = in.nextBigInteger();
            int a = 0, b = 0;

            if (A.equals(ZERO) && B.equals(ZERO)) break;

            while(fibs.get(a).compareTo(A) < 0) a++;
            b = a;
            while(fibs.get(b).compareTo(B) <= 0) b++;

            System.out.println(b-a);
        }
    }
}