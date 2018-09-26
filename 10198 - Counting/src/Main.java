import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int[] nums = {1,1,2,3};
    private static BigInteger[] waysPerNumber = new BigInteger[1001];

    public static void main(String[] args) {
        Arrays.fill(waysPerNumber, ZERO);

        waysPerNumber[1] = BigInteger.valueOf(2);
        waysPerNumber[2] = BigInteger.valueOf(5);
        waysPerNumber[3] = BigInteger.valueOf(13);
        waysPerNumber[4] = BigInteger.valueOf(33);
        waysPerNumber[5] = BigInteger.valueOf(84);

        for(int i = 6; i < waysPerNumber.length; i++) {
            for (int num : nums) {
                waysPerNumber[i] = waysPerNumber[i].add(waysPerNumber[i-num]);
            }
        }

        while (in.hasNextInt()) {
            System.out.println(waysPerNumber[in.nextInt()]);
        }
    }
}