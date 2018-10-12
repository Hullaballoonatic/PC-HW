import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int T = in.nextInt();

    private static int countTrue(boolean[] bools) {
        int count = 0;
        for (boolean bool : bools)
            if(bool) count++;
        return count;
    }

    private static int execute(int N, List<Integer> P) {
        boolean[] calendar = new boolean[N];

        //mark days on which a hartal occurs
        for(int p : P)
            for (int day = p-1; day < N; day+=p)
                calendar[day] = true;

        //mark off fridays and saturdays on which a hartal occurs
        for (int day = 5; day+1 < N; day+=7) {
            calendar[day] = false;
            calendar[day+1] = false;
        }

        //count working days in which a hartal occurs
        return countTrue(calendar);
    }

    public static void main(String[] args) {
        while(T-->0) out.println(execute(in.nextInt(), new ArrayList<Integer>() {{
            int numParameters = in.nextInt();
            for(int paramIndex = 0; paramIndex < numParameters; paramIndex++)
                add(in.nextInt());
        }}));
    }
}