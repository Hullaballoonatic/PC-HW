import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int t = in.nextInt();
        in.nextLine();
        while(t-->0) {
            String tmp = in.nextLine();
            int sum = 0, s = 0;
            for (char c : tmp.toCharArray())
                sum += (int) c;
            int n = Integer.parseInt(tmp);

            out.println(s);
        }
    }
}