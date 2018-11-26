import java.util.*;

import static java.lang.Math.max;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final List<Elephant> elephants = new ArrayList<Elephant>() {{
        String line;
        while(in.hasNextLine() && !(line = in.nextLine()).isEmpty()) {
            String[] tmp = line.split(" ");
            add(new Elephant(size() + 1, Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
        }
        sort((a, b) -> {
            if (a.iq > b.iq) return -1;
            if (a.iq < b.iq) return 1;
            return a.mass - b.mass;
        });
    }};
    private static final String DEBUG_HEADER = "    id    |    mass   |    iq    \n" +
                                               "----------+-----------+----------";

    private static Elephant[] LIS() {
        Elephant cur, pre; // pre-allocate
        int[] L = new int[elephants.size()]; // stores the longest increasing subsequence which ends in this elephant
        int longest = 0; // will need this for constructing the sequence later
        for (int i = 0; i < elephants.size(); i++) {
            L[i] = 1;
            cur = elephants.get(i);
            for (int j = 0; j < i; j++) { // check the lengths of all up to this one
                pre = elephants.get(j);
                if (cur.mass > pre.mass && cur.iq < pre.iq)
                    L[i] = max(L[j] + 1, L[i]);
            }
            longest = max(L[i], longest);
        }

        int previousMass = Integer.MAX_VALUE;
        int previousIQ = -1;
        Elephant[] result = new Elephant[longest];
        // now we can work backwards recursively grabbing any elephant whose LIS is of the length we're looking for, because they will automatically qualify as preceding
        for (int i = elephants.size() - 1; i >= 0 && longest > 0; i--) {
            cur = elephants.get(i);
            if (L[i] == longest && (cur.mass < previousMass && cur.iq > previousIQ)) { // gotta check again to make sure we don't use something equal in either category. reverse the comparisons because going backwards
                result[--longest] = cur;
                previousIQ = cur.iq;
                previousMass = cur.mass;
            }
        }

        return result;
    }

    public static void main(String... args) {
        boolean DEBUG = false;
        if (DEBUG) {
            out.println(DEBUG_HEADER);
            for (Elephant elephant : elephants)
                out.println(elephant);
        }
        Elephant[] lis = LIS();
        out.println(lis.length);
        for (Elephant elephant : lis) out.println(DEBUG ? elephant : elephant.id);
    }
}

class Elephant {
    int id, mass, iq;

    Elephant(int id, int mass, int iq) {
        this.id   = id;
        this.mass = mass;
        this.iq   = iq;
    }

    @Override
    public String toString() {
        return String.format("%9d | %9d | %9d", id, mass, iq);
    }
}