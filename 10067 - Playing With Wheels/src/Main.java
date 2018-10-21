import java.util.*;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int NOT_REACHABLE = -1, MAX = 10000000;
    private static int N = in.nextInt();
    private static WheelConfiguration root, goal;

    public static void main(String[] args) {
        while(N-->0) {
            root = new WheelConfiguration();
            goal = new WheelConfiguration();
            for (int i = 0; i < 4; i++)
                root.wheelPositions[i] = in.nextInt();
            for (int i = 0; i < 4; i++)
                goal.wheelPositions[i] = in.nextInt();

            WheelConfiguration.forbiddenConfigurations = new ArrayList<WheelConfiguration>() {{
                int numForbidden = in.nextInt();
                while(numForbidden-->0) {
                    WheelConfiguration config = new WheelConfiguration();
                    for (int i = 0; i < 4; i++)
                        config.wheelPositions[i] = in.nextInt();
                    add(config);
                }
            }};

            out.println(BFS());
        }
    }

    private static int BFS() {
        int minConfigs = MAX;

        Queue<WheelConfiguration> q = new PriorityQueue<>();
        q.add(root);

        while(!q.isEmpty()) {
            WheelConfiguration node = q.remove();
            if (node == goal) minConfigs = Math.min(minConfigs, node.height);

            List<WheelConfiguration> children = node.children();

            for (WheelConfiguration child : children) {
                if (!s.contains(child)) {
                    s.push(child);
                }
            }
        }

        if (minConfigs == MAX) return NOT_REACHABLE;
        else return minConfigs;
    }
}

class WheelConfiguration implements Comparable<WheelConfiguration> {
    static List<WheelConfiguration> forbiddenConfigurations;
    int[] wheelPositions = new int[4];
    int height = 0;

    List<WheelConfiguration> children() {
        return new ArrayList<WheelConfiguration>() {{
            for (int wheel : wheelPositions) {
                WheelConfiguration potentialConfiguration = turn(wheel, Direction.L);
                if (!forbiddenConfigurations.contains(potentialConfiguration)) add(potentialConfiguration);
                potentialConfiguration = turn(wheel, Direction.R);
                if (!forbiddenConfigurations.contains(potentialConfiguration)) add(potentialConfiguration);
            }
        }};
    }

    private WheelConfiguration turn(int wheelNumber, Direction direction) {
        int newPosition = wheelPositions[wheelNumber] + direction.mod;
        if (newPosition == 10) newPosition = 0;
        if (newPosition == -1) newPosition = 9;
        WheelConfiguration child = new WheelConfiguration(this);
        child.wheelPositions[wheelNumber] = newPosition;
        return child;
    }

    private WheelConfiguration(WheelConfiguration parent) {
        height = parent.height + 1;
        System.arraycopy(parent.wheelPositions, 0, this.wheelPositions, 0, 4);
    }

    WheelConfiguration() {}

    enum Direction {
        L(-1), R(1);

        int mod;

        Direction(int m) {
            mod = m;
        }
    }

    @Override
    public int compareTo(WheelConfiguration o) {
        for (int i = 0; i < 4; i++)
            if (wheelPositions[i] != o.wheelPositions[i]) return wheelPositions[i] - o.wheelPositions[i];
        return 0;
    }
}