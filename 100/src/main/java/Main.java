import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            int low  = num1 < num2 ? num1 : num2;
            int high = num2 > num1 ? num2 : num1;

            int max = 0;
            for (int i = low; i <= high; i++) {
                int curNum = i;
                int count = 1;
                while (curNum != 1) {
                    count++;
                    if (curNum % 2 == 0) {
                        curNum /= 2;
                    } else {
                        curNum = curNum * 3 + 1;
                    }
                }
                if (count > max) {
                    max = count;
                }
            }

            System.out.printf("%d %d %d\n", num1, num2, max);
        }
    }
}
