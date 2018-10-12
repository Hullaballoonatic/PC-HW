import java.util.Scanner;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    private static long sumDigits(long num) {
        long sum = 0;
        while(num > 0) {
            sum += num%10;
            num /= 10;
        }
        return sum;
    }

    private static long sumDigits(String n) {
        long sum = 0;
        for (char c : n.toCharArray())
            sum += c - '0';
        return sum;
    }

    private static String primeDigits(long n) {
        StringBuilder digits = new StringBuilder();
        long f = 2;
        while (f * f <= n) {
            if (n % f == 0) {
                digits.append(f);
                n /= f;
            } else f++;
        }
        return digits.append(n).toString();
    }

    public static void main(String[] args) {
        int t = in.nextInt();

        while(t-->0) {
            long n = in.nextLong();
            while(n++ > 0) {
                String primeDigits = primeDigits(n);
                if (!primeDigits.equals(Long.toString(n)) && sumDigits(primeDigits) == sumDigits(n)) {
                    out.println(n);
                    break;
                }
            }
        }
    }
}