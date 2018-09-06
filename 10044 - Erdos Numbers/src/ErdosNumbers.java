import java.io.PrintStream;
import java.util.*;

class ErdosNumbers {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;
    private static final String goal = "Erdos, P.";

    public static void main(String[] args) {
        int numScenarios = in.nextInt();

        for (int s = 1; s <= numScenarios; s++) {
            Map<String, Author> graph = new HashMap<>();

            out.printf("Scenario %d\n", s);

            int numPapers = in.nextInt();
            int numRoots = in.nextInt();

            in.nextLine();
            for (int i = 0; i < numPapers; i++) {
                // parsing author names into temporary array
                // Names come in format: LastName, F., LastName, F., LastName, F. etc
                String[] tmp = in.nextLine().split(":")[0].split("\\., ");
                // Must replace the . after F for every name but last on the list
                for (int j = 0; j < tmp.length - 1; j++) {
                    tmp[j] += ".";
                }

                for (String name : tmp) {
                    if (graph.keySet().contains(name)) graph.get(name).coauthorNames.addAll(Arrays.asList(tmp));
                    else graph.put(name, new Author(Arrays.asList(tmp)));
                }
            }

            for (int i = 0; i < numRoots; i++) {
                String authorName = in.nextLine();
                out.println(authorName + " " + bfs(authorName, graph));
            }
        }

        out.println();
    }

    private static String bfs(String s, Map<String, Author> G) {
        Queue<String> q = new LinkedList<>();
        q.add(s);
        TreeSet<String> visited = new TreeSet<>();

        while (!q.isEmpty()) {
            String authorName = q.remove();
            Author author = G.get(authorName);
            if (authorName.equals(goal)) return Integer.toString(author.parent.getTreeSize());
            try {
                for (String coauthorName : author.coauthorNames) {
                    if (!visited.contains(coauthorName)) {
                        visited.add(coauthorName);
                        G.get(coauthorName).parent = author;
                        q.add(coauthorName);
                    }
                }
            } catch (NullPointerException e) {
                return "infinity";
            }
        }

        return "infinity";
    }
}

class Author {
    Set<String> coauthorNames = new HashSet<>();
    Author parent;

    Author(List<String> associations) {
        this.coauthorNames.addAll(associations);
    }

    int getTreeSize() {
        if (parent != null && parent != this) return 1 + parent.getTreeSize();
        else return 1;
    }
}