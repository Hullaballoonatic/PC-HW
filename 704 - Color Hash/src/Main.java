import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.out;

class Wheels {
    private static final String COMPLETED_STATE = "034305650121078709A90121";

    String state;

    Wheels() {
        state = COMPLETED_STATE;
    }
    Wheels(String state) {
        this.state = state;
    }

    // Left Clockwise
    Wheels turn1() {
        return new Wheels(
                state.substring(10, 12) + state.substring(0, 10) + state.substring(12, 21) + state.substring(7, 10)
        );
    }

    // Right Clockwise
    Wheels turn2() {
        return new Wheels(
                state.substring(0, 9) + state.substring(23) + state.substring(12,14) + state.substring(14, 24) + state.substring(12, 14)
        );
    }

    // Left Counterclockwise
    Wheels turn3() {
        return new Wheels(
                state.substring(2, 12) + state.substring(0, 2) + state.substring(12, 21) + state.substring(11, 12) + state.substring(0, 2)
        );
    }

    // Right Counterclockwise
    Wheels turn4() {
        return new Wheels(
                state.substring(0, 9) + state.substring(19, 22) + state.substring(22) + state.substring(12, 22)
        );
    }

    boolean isGoalState() {
        return state.equals(COMPLETED_STATE);
    }
}

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int n = Integer.parseInt(in.nextLine());

    private static final Wheels GOAL_STATE = new Wheels();
    private static final int MAX_STEPS = 16;
    private static final String DONE = "PUZZLE ALREADY SOLVED", NO_SOLUTION_TEXT = "NO SOLUTION WAS FOUND IN 16 STEPS";
    private static final TreeMap<Wheels, String> pathsToGoal = new TreeMap<>(Comparator.comparing(o -> o.state));

    private static String minimalSequence;

    private static boolean isFailState(String sequence) {
        return sequence.length() > MAX_STEPS / 2;
    }

    private static boolean isGoalState(Wheels wheels) {
        return (pathsToGoal.containsKey(wheels));
    }

    private static boolean isSuperiorPath(String newPath, String oldPath) {
        if (oldPath.isEmpty()) return true;
        if (newPath.length() < oldPath.length()) return true;
        return newPath.length() == oldPath.length() && newPath.compareTo(oldPath) < 0;
    }

    private static void setMinimalSequence(String sequence) {
        if (minimalSequence.isEmpty() || sequence.length() < minimalSequence.length()) minimalSequence = sequence;
    }

    private static void backwardPathing(Wheels wheels, String sequence, int previousRotation) {
        if (isFailState(sequence))
            return;

        if (isSuperiorPath(sequence, pathsToGoal.getOrDefault(wheels, "")))
            pathsToGoal.put(wheels, sequence);

        if (previousRotation != 3) backwardPathing(wheels.turn1(), 3 + sequence, 1);
        if (previousRotation != 1) backwardPathing(wheels.turn3(), 1 + sequence, 3);
        if (previousRotation != 4) backwardPathing(wheels.turn2(), 4 + sequence, 2);
        if (previousRotation != 2) backwardPathing(wheels.turn4(), 2 + sequence, 4);
    }

    private static void forwardPathing(Wheels wheels, String sequence, int previousRotation) {
        if (isFailState(sequence))
            return;

        if (isGoalState(wheels))
            setMinimalSequence(sequence + pathsToGoal.get(wheels));

        // don't rotate back to previous state
        if (previousRotation != 3) forwardPathing(wheels.turn1(), sequence + 1, 1);
        if (previousRotation != 1) forwardPathing(wheels.turn3(), sequence + 3, 3);
        if (previousRotation != 4) forwardPathing(wheels.turn2(), sequence + 2, 2);
        if (previousRotation != 2) forwardPathing(wheels.turn4(), sequence + 4, 4);
    }

    public static void main(String[] args) {
        backwardPathing(GOAL_STATE, "", 0);

        while (n-->0) {
            minimalSequence = "";

            Wheels wheels = new Wheels(in.nextLine().replaceAll("10", "A").replaceAll(" ", ""));

            if (wheels.isGoalState()) out.println(DONE);
            else {
                forwardPathing(wheels, "", 0);
                out.println(minimalSequence.length() == 0 ? NO_SOLUTION_TEXT : minimalSequence);
            }
        }
    }
}