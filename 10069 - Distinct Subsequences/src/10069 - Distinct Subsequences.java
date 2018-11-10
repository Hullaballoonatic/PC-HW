import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int N = Integer.parseInt(in.nextLine());

    public static void main(String[] args) {
        while (N-->0) {
            char[] X = in.nextLine().trim().toCharArray(), Z = in.nextLine().trim().toCharArray();
            if (Z.length > X.length) out.println(0);
            else {
                BigInteger[] Seq = new BigInteger[X.length + X.length];
                Arrays.fill(Seq, 0, X.length, ONE);

                int pre = 0, cur = X.length;

                for (int i = 1; i <= Z.length; i++) {
                    for (int j = 0; j + i - 1 < X.length; j++) {
                        Seq[cur + j] = X[j + i - 1] == Z[i - 1] ? Seq[pre + j] : ZERO;
                        if (j > 0) Seq[cur + j] = Seq[cur + j].add(Seq[cur + j - 1]);
                    }

                    // swap
                    int tmp = cur;
                    cur = pre;
                    pre = tmp;
                }

                out.println(Seq[pre + X.length - Z.length]);
            }
        }
    }
}