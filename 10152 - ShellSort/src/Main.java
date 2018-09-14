import java.util.ArrayList;
import java.util.Scanner;

class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int k = in.nextInt();
        in.nextLine();

        while(k-->0) {
            ArrayList<String> end = new ArrayList<>(), cur = new ArrayList<>();

            int n = in.nextInt();
            in.nextLine();

            for (int i = 0; i < n; i++)
                cur.add(in.nextLine());

            for (int i = 0; i < n; i++)
                end.add(in.nextLine());

            for(int i = n - 1, j; i > 0; --i) {
                if((j = cur.indexOf(end.get(i-1))) > cur.indexOf(end.get(i))) {
                    System.out.println(cur.get(j));
                    cur.add(0, cur.remove(j));
                }
            }

            System.out.println();
        }
    }
}