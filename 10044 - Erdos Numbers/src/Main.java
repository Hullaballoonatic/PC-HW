import java.io.PrintStream;
import java.util.*;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    private static final String goal = "Erdos, P.";

    public static void main(String[] args) {
        int numScenarios = in.nextInt();

        for (int s = 1; s <= numScenarios; s++) {
            out.printf("Scenario %d\n", s);
            int numPapers = in.nextInt();
            int numRoots = in.nextInt();
            in.nextLine();

            Map<String, Node> graph = new HashMap<>();
            for (int i = 0; i < numPapers; i++) {
                // Creating array of author names
                String[] authors = in.nextLine().split(":")[0].split("\\., ");
                for (int j = 0; j < authors.length - 1; j++) {
                    authors[j] += ".";
                }

                for (String author : authors) {
                    if (graph.containsKey(author)) {
                        graph.get(author).associations.addAll(Arrays.asList(authors));
                    } else {
                        graph.put(author, new Node(author, Arrays.asList(authors)));
                    }
                }
            }

            for (int i = 0; i < numRoots; i++) {
                String name = in.nextLine();
                out.println(name + " " + bfs(graph.get(name), graph));
            }
        }
    }

    private static String bfs(Node s, Map<String, Node> G) {
        Queue<Node> q = new LinkedList<>();
        q.add(s);
        TreeSet<String> visited = new TreeSet<>();

        while(!q.isEmpty()) {
            Node curNode = q.remove();
            if (curNode == null) return "infinity";
            if (curNode.name.equals(goal)) return Integer.toString(curNode.getParent().getTreeSize());
            for (String coauthor : curNode.associations) {
                if (!visited.contains(coauthor)) {
                    visited.add(coauthor);
                    q.add(G.get(coauthor).setParent(curNode));
                }
            }
        }

        return "infinity";
    }
}

class Node {
    String name;
    private Node parent = null;
    Node getParent() {
        return this.parent;
    }
    Node setParent(Node value) {
        this.parent = value;
        return this;
    }
    Set<String> associations = new HashSet<>();

    Node(String name, List<String> associations) {
        this.name = name;
        this.associations.addAll(associations);
    }

    int getTreeSize() {
        if (parent != null && !parent.name.equals(name)) return 1 + parent.getTreeSize();
        else return 1;
    }
}