import java.util.*;

import static java.lang.Math.max;
import static java.lang.System.out;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final List<Elephant> X = new ArrayList<Elephant>() {{
        int i = 1;
        while (true)
            try {
                String[] line = in.nextLine().split(" ");
                add(new Elephant(i++, Integer.parseInt(line[0]), Integer.parseInt(line[1])));
            } catch (Exception ignored) {
                break;
            }
        sort(new EleComp());
    }};
    private static final int n = X.size();

    public static void main(String... args) {
        boolean isRoot = true;
        int L = 0;
        int[] lengths = new int[n];
        Arrays.fill(lengths, 1);
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (X.get(i).isSuccessor(X.get(j))) {
                    lengths[i] = max(lengths[j] + 1, lengths[i]);
                    L = max(lengths[i], L);
                }

        out.println(L);
        int[] solution = new int[L];

        for (int i = n - 1, preId = 0; i >= 0 && L > 0; i--)
            if (lengths[i] == L && (isRoot || preId < X.get(i).iq)) {
                solution[--L] = (preId = X.get(i).id);
                isRoot = false;
            }

        for (int id : solution) out.println(id);
    }
}

class EleComp implements Comparator<Elephant> {
    @Override
    public int compare(Elephant a, Elephant b) {
        if (a.mass == b.mass) return b.iq - a.iq;
        else return a.mass - b.mass;
    }
}

class Elephant {
    int mass, iq, id;

    boolean isSuccessor(Elephant candidate) {
        return iq < candidate.iq && mass > candidate.mass;
    }

    Elephant(int id, int mass, int iq) {
        this.id = id;
        this.mass = mass;
        this.iq = iq;
    }
}