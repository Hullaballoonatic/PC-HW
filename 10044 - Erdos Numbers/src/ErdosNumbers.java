import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.*;

class ErdosNumbers {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static final AuthorComparator comp = new AuthorComparator();

    public static void main(String[] args) {
        int numScenarios = in.nextInt();

        for (int s = 1; s <= numScenarios; s++) {
            LinkedHashMap<String, Set<String>> graph = new LinkedHashMap<>();

            out.printf("Scenario %d\n", s);

            int numPapers = in.nextInt();
            int numRoots = in.nextInt();

            in.nextLine();
            for (int i = 0; i < numPapers; i++) {
                String[] tmp = in.nextLine().split(":")[0].split("\\., ");
                for (int j = 0; j < tmp.length - 1; j++) {
                    tmp[j] += ".";
                }
                List<String> tmpList = Arrays.asList(tmp);
                for (String author : tmpList) {
                    try {
                        graph.get(author).addAll(tmpList);
                    } catch (NullPointerException e) {
                        graph.put(author, new HashSet<>(tmpList));
                    }
                }
            }

            int[] erdosNums = new int[graph.size()];

            for (int i = 0; i < numRoots; i++) {
                String authorName = in.nextLine();
                out.println(authorName + " " + bfs(authorName, graph, erdosNums));
            }
        }

        out.println();
    }

    private static String bfs(String goal, LinkedHashMap<String,Set<String>> G, int[] nums) {
        Queue<String> q = new LinkedList<>();
        q.add("Erdos, P.");

        while (!q.isEmpty()) {
            String authorName = q.remove();
            Author author = G.get(authorName);
            if (authorName.equals(goal)) return Integer.toString(author.erdosNum);
            try {
                for (String coauthorName : author.coauthorNames) {
                    if (!visited.contains(coauthorName)) {
                        visited.add(coauthorName);
                        G.get(coauthorName).erdosNum = author.erdosNum + 1;
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