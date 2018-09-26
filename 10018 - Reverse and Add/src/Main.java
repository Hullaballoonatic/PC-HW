import java.io.PrintStream;
import java.util.*;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        int times = in.nextInt();
        while(times-->0) {
            int adds = 0;
            long sum = in.nextLong();
            do {
                adds++;
                sum += Integer.parseInt(reverse(Long.toString(sum)));
            } while (!isPalindrome(Long.toString(sum)));
            out.printf("%d %d\n", adds, sum);
        }
    }

    private static String reverse(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            result.append(input.charAt(i));
        }
        return result.toString();
    }

    private static boolean isPalindrome(String in) {
        int mid = in.length() / 2;
        String[] parts = {in.substring(0, mid), reverse(in.substring(mid))};
        for(int c = 0; c < mid; c++)
            if (parts[0].charAt(c) != parts[1].charAt(c))
                return false;

        return true;
    }
}