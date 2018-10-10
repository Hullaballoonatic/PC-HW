import java.util.*;

import static java.lang.Math.sqrt;
import static java.lang.System.out;

class Main {
    private static final Scanner in  = new Scanner(System.in);
    private static final int     MAX = 1000000000;

    public static void main(String[] args) {
        int t = in.nextInt();

        while(t-->0) {
            int s = in.nextInt();

            while(true) {
                s++;
                String num = Integer.toString(s);
                String primes = prime_digits(s);
                if (!num.equals(primes) && sumDigits(num) == sumDigits(primes))
                    break;
            }

            out.println(s);
        }
    }

    private static String prime_digits(int num) {
        StringBuilder primeDigits = new StringBuilder();

        // while number is even
        while (num%2==0) {
            num /= 2;
            primeDigits.append(2);
        }

        // number is odd
        for(int i = 3; i < sqrt(num); i += 2) {
            while (num%i==0) {
                num /= i;
                primeDigits.append(i);
            }
        }

        if(num > 2)
            primeDigits.append(num);

        return primeDigits.toString();
    }

    private static int sumDigits(String nums) {
        int sum = 0;
        for (char c : nums.toCharArray())
            sum += c - '0';
        return sum;
    }


}