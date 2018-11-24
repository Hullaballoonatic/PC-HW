import java.util.*;

import static java.lang.System.out;

class Main {
    private static final String trivialSolutionString = "NO SOLUTION";
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while(in.hasNext()) {
            
        }
    }
}

enum Line {
    A('A'), B('B'), C('C'), D('D'), E('E'), F('F'), G('G'), H('H'), I('I'), J('J'), K('K'), L('L');

    String label;

    Line(char label) {
        this.label = Character.toString(label);
    }
}

class Puzzle {
    private static String[] cellIds = new String[]{
                                             "EL",
                                       "EK", "LE", "FL",
            "IA", "AI", "AJ", "AEJ", "AEK", "AFK", "AFL", "AGL", "AG", "AH", "HA",
                 "BI", "BEI", "BEJ", "BFJ", "BFK", "BGK", "BGL", "BHL", "BH",
                 "CE", "CEI", "CFI", "CFJ", "CGJ", "CGK", "CHK", "CHL", "CL",
            "DE", "ED", "DF", "DFI", "DGI", "DGJ", "DHJ", "DHK", "DK", "LD", "DL",
                                      "GI", "HI", "HJ",
                                             "IH"
    };
    private static Map<String, Integer> cells = new HashMap<String, Integer>() {{
        for (String id : cellIds) put(id, 0);
    }};
    private static Map<Line, Integer> highestValues;

    private static int[] getLine(Line line) {
        int[] result = new int[11];
        int i = 0;
        for (String key : cells.keySet())
            if (key.contains(line.label)) {
                result[i] = cells.get(key);
                i++;
            }
        return result;
    }

    Puzzle(Scanner in) {
        highestValues = new HashMap<Line, Integer>() {{
            put(Line.A, in.nextInt());
            put(Line.B, in.nextInt());
            put(Line.C, in.nextInt());
            put(Line.D, in.nextInt());
            put(Line.E, in.nextInt());
            put(Line.F, in.nextInt());
            put(Line.G, in.nextInt());
            put(Line.H, in.nextInt());
            put(Line.I, in.nextInt());
            put(Line.J, in.nextInt());
            put(Line.K, in.nextInt());
            put(Line.L, in.nextInt());
        }};
    }
}

enum Orientation {
    UP, DOWN;
}

class Cell {
    Line[] lines;
    Orientation orientation;

    Cell(Orientation orientation, Line... lines) {
        this.lines = lines;
        this.orientation = orientation;
    }
}