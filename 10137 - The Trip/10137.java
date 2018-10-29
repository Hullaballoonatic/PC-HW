import java.util.Scanner;

class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            int numStudents = sc.nextInt();
            if(numStudents == 0) {
                return;
            }

            double[] expenses = new double[numStudents];
            double totalExpenses = 0.00;

            for (int i = 0; i < numStudents; i++) {
                expenses[i] = sc.nextDouble();
                totalExpenses += expenses[i];
            }

            double average = Math.round((totalExpenses / numStudents) * 100.00) / 100.00;
            double totHigh = 0;
            double totLow = 0;

            for (double expense : expenses) {
                if (expense < average) {
                    totLow += average - expense;
                } else if (expense > average) {
                    totHigh += expense - average;
                }
            }

            double totalExchanged = totLow < totHigh ? totLow : totHigh;

            System.out.printf("$%.2f\n", totalExchanged);
        }
    }
}