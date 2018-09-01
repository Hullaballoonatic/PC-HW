import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        int S = Integer.parseInt(in.readLine());

        for (int s = 1; s <= S; s++) {
            String[] nums = in.readLine().split(" ");

            out.printf("Scenario %d\n", s);
            out.println(
                    new Scenario(
                            Integer.parseInt(nums[0]),
                            Integer.parseInt(nums[1])
                    ).toString());
        }
    }
}

class Scenario {
    private Author[] authors;
    private Paper[] papers;

    Scenario(int p, int n) throws IOException {
        BufferedReader in = Main.in;

        this.papers = new Paper[p];
        for (int i = 0; i < p; i++) {
            papers[i] = new Paper(in.readLine());
        }

        this.authors = new Author[n];
        for (int i = 0; i < n; i++) {
            authors[i] = new Author(in.readLine(), null);
        }
    }

    private String bfs(Author root) {
        LinkedList<Author> q = new LinkedList<>();
        q.add(root);
        Set<String> visited = new TreeSet<>();

        while (!q.isEmpty()) {
            Author node = q.remove();
            System.out.println("pop");

            if (node.name.equals("Erdos, P.")) return Integer.toString(node.getTreeSize());

            if (node.children.length == 0) System.out.println("no children");
            else for (String child : node.children) {
                if (visited.contains(child)) continue;
                visited.add(child);
                q.add(new Author(child, node));
                System.out.println("push");
            }
        }

        return "infinity";
    }

    class Author {
        String name;
        Author parent;
        String[] children;

        int getTreeSize() {
            if (parent != null) return 1 + parent.getTreeSize();
            else return 1;
        }

        Author(String name, Author parent) {
            this.name = name;
            this.parent = parent;
            for (Paper paper : papers) {
                for (String author : paper.getAuthorNames()) {
                    if (author.equals(name)) {
                        children = paper.getAuthorNames();
                    }
                }
            }
            System.out.println(name + " --CHILDREN: " + Arrays.toString(children));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Author author : authors) {
            sb.append(author.name).append(" ").append(bfs(author)).append("\n");
        }

        return sb.toString();
    }
}

class Paper {
    private String[] authorNames;
    String[] getAuthorNames() {
        return authorNames;
    }

    Paper(String paper) {
        String[] names = paper.split(":")[0].split("\\., ");
        this.authorNames = new String[names.length];
        for (int i = 0; i < this.authorNames.length - 1; i++) {
            this.authorNames[i] = names[i] + '.';
        }
        this.authorNames[names.length - 1] = names[names.length - 1];
    }
}