import java.util.Scanner;

class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (input.hasNext()) {
            int size = input.nextInt();
            int sequence[] = new int[size];
            boolean needed[] = new boolean[size-1];
            String jolly = "Jolly";

            for (int i = 0; i < size; i++) {
                sequence[i] = input.nextInt();
            }
            for (int i = 1; i < size; i++) {
                int difference = Math.abs(sequence[i] - sequence[i-1]) - 1;
                try {
                    needed[difference] = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    jolly = "Not jolly";
                    break;
                }
            }
            for (boolean b : needed) {
                if (!b) {
                    jolly = "Not jolly";
                    break;
                }
            }
            System.out.println(jolly);
        }
    }
}