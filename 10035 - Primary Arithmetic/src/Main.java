import java.io.PrintStream;
import java.util.Scanner;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        while (true) {
            int numCarries = 0, curCarry = 0, sumOfDigit = 0, num1 = in.nextInt(), num2 = in.nextInt();
            if (num1 == 0 && num2 == 0) break;

            while (num1 != 0 || num2 != 0) {
                if ((sumOfDigit = num1 % 10 + num2 % 10 + curCarry) >= 10) numCarries++;
                curCarry = sumOfDigit / 10;
                num1 /= 10;
                num2 /= 10;
            }

            if (numCarries == 0) out.println("No carry operation.");
            else if (numCarries == 1) out.println("1 carry operation.");
            else out.printf("%d carry operations.\n", numCarries);
        }
    }
}