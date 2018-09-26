import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger TWENTY_FOUR = BigInteger.valueOf(24);

    public static void main(String[] args) {
        int s = in.nextInt();

        while(s-->0) {
            BigInteger n = in.nextBigInteger();
            BigInteger nMinus1 = n.subtract(BigInteger.ONE);
            BigInteger nMinus2 = n.subtract(TWO);
            BigInteger nMinus3 = n.subtract(THREE);
            BigInteger nTimesNMinus1 = n.multiply(nMinus1);
            BigInteger l = nTimesNMinus1.divide(TWO);
            BigInteger q = nTimesNMinus1.multiply(nMinus2).multiply(nMinus3).divide(TWENTY_FOUR);

            System.out.println(BigInteger.ONE.add(l).add(q));
        }
    }
}
