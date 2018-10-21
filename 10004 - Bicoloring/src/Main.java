import java.util.*;

import static java.lang.System.out;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static Vertex[] vertices;

    public static void main (String[] args) {
        int n;
        while ((n = in.nextInt()) != 0) {
            vertices = new Vertex[n];

            for (int i = 0; i < n; i++)
                vertices[i] = new Vertex();

            vertices[0].color = Color.RED;

            for (int l = in.nextInt(); l > 0; l--) {
                int a = in.nextInt(), b = in.nextInt();
                vertices[a].neighbors.add(vertices[b]);
                vertices[b].neighbors.add(vertices[a]);
            }

            out.println(attemptColor() ? "BICOLORABLE." : "NOT BICOLORABLE.");
        }
    }

    private static boolean attemptColor() {
        Stack<Vertex> s = new Stack<>();
        s.push(vertices[0]);

        while(!s.isEmpty()) {
            Vertex a = s.pop();

            for (Vertex b : a.neighbors)
                if (b.color == a.color)
                    return false;
                else if (b.color == null) {
                    b.color = a.otherColor();
                    s.push(b);
                }
        }

        return true;
    }
}

enum Color {
    RED,
    BLACK
}

class Vertex {
    Color color = null;
    List<Vertex> neighbors = new ArrayList<>();

    Color otherColor() {
        return color == Color.BLACK ? Color.RED : Color.BLACK;
    }
}