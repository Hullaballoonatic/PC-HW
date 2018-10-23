import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int[][] ACTIONS = new int[][] { {0,1}, {0,9}, {1,1}, {1,9}, {2,1}, {2,9}, {3,1}, {3,9} };
    private static final int UNREACHABLE = -1;

    private static int N = in.nextInt();

    public static void main(String ... args) {
        while(N-->0)
            out.println(bfs(new Node(in), new Node(in)));
    }

    private static int bfs(Node root, Node goal) {
        Set<Node> visited = new TreeSet<Node>() {{
            int numForbiddenConfigs = in.nextInt();
            for (int i = 0; i < numForbiddenConfigs; i++)
                add(new Node(in));
        }};

        Queue<Node> q = new LinkedList<Node>() {{
            add(root);
        }};

        Node current, child;
        int[] tmp = new int[4];

        while(!q.isEmpty()) {
            if ((current = q.poll()).compareTo(goal) == 0) return current.height;
            else for (int[] action : ACTIONS) {
                for (int i = 0; i < 4; i++)
                    if (i == action[0])
                        tmp[i] = (current.wheels[i] + action[1]) % 10;
                    else tmp[i] = current.wheels[i];
                if (visited.add(child = new Node(tmp, current.height)))
                    q.add(child);
            }
        }

        return UNREACHABLE;
    }
}

class Node implements Comparable<Node> {
    int[] wheels = new int[4];
    int height = 0;

    Node(int[] wheels, int height) {
        this.height = height + 1;
        System.arraycopy(wheels, 0, this.wheels, 0, 4);
    }

    Node(Scanner in) {
        for (int i = 0; i < 4; i++)
            this.wheels[i] = in.nextInt();
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int wheel : wheels)
            sb.append(wheel).append(" ");
        return sb.toString().trim();
    }

    @Override
    public int compareTo(Node o) {
        for (int i = 0; i < 4; i++)
            if (wheels[i] != o.wheels[i]) return wheels[i] - o.wheels[i];
        return 0;
    }
}