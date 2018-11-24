import java.util.*;

import static java.lang.Math.*;
import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static int N = in.nextInt();

    public static void main(String... args) {
        while(N-->0) {
            Pos a = new Pos(in.nextDouble(), in.nextDouble()), b = new Pos(in.nextDouble(), in.nextDouble());
            Circle pillar = new Circle(in.nextDouble());
            boolean blocked = pillar.isBetween(a, b);
            out.printf("%s %.3f\n", blocked ? "T" : "F", blocked ? pillar.wrapLength(a, b) : a.distanceTo(b));
        }
    }
}

class Circle {
    private Pos origin = new Pos(0,0);
    private double r;

    boolean isBetween(Pos a, Pos b) {
        Line AB = new Line(a, b);
        double distToLine = abs(AB.c) / sqrt(pow(AB.a, 2) + pow(AB.b, 2));
        out.println(distToLine);
        out.println(AB.toString());
        Pos E = AB.getPos(0);
        Pos C = origin;
        Line CE = new Line(C, E);
        out.println(CE.toString());
        out.println(CE.length);
        double e = abs(atan(AB.m));
        distToLine = CE.length * sin(e);
        out.printf("%.3f : %.3f\n", distToLine, r);
        return origin.distanceTo(new Line(a, b)) < r;
    }

    double wrapLength(Pos a, Pos b) {
        // need to measure three parts: distance from a/b to the points where the rope first touches the pillar from either end (A, B), and then the length of the part wrapping around the pillar (S)
        // combine to get total rope length

        // these (as well as r) all form the relevant lines for all the trig
        Line oa = new Line(origin, a);
        Line ob = new Line(origin, b);
        Line ab = new Line(a, b);

        // the segments of the ropes that are tangent to the circle:
        // use pythagorean theorem:
        double A = sqrt(pow(oa.length, 2) - r*r);
        double B = sqrt(pow(ob.length, 2) - r*r);

        // angle between o_a and o_b
        // this is the hardest angle to determine
        double alpha = acos((pow(oa.length, 2) + pow(ob.length, 2) - pow(ab.length, 2)) / (2 * oa.length * ob.length));

        // the angle from origin to both ends of the arc
        // subtract the angles form origin to a/b and the points where the rope touches the circle from alpha
        double theta = alpha - acos(r / oa.length) - acos(r / ob.length);

        // arc is the length of the rope in touch with the pillar
        double S = r * theta;

        return A + S + B;
    }

    Circle(double R) {
        r = R;
    }
}

class Line {
    double a, b, c;
    double length;

    double m;
    double y_0;

    double getX(double y) {
        // y = mx + y_0
        // (y - y_0) / m = x
        return (y - y_0) / m;
    }

    double getY(double x) {
        return m * x + y_0;
    }

    Pos getPos(double y) {
        return new Pos(getX(y), y);
    }

    public String toString() {
        return String.format("y = %.3fx + %.3f", m, y_0);
    }

    Line(Pos p1, Pos p2) {
        double dy = p2.y - p1.y;
        double dx = p2.x - p1.x;
        m = dy / dx;
        y_0 = p1.y - m * p1.x;

        length = p1.distanceTo(p2);

        // convert to ax + by + c = 0
        this.c = -b;
        this.b = 1.0;
        this.a = -m;
    }
}

class Pos {
    double x, y;

    double distanceTo(Line to) {
        return abs(to.a * x + to.b * y + to.c) / sqrt(to.a * to.a + to.b * to.b);
    }

    double distanceTo(Pos to) {
        double dx = to.x - x, dy = to.y - y;
        return hypot(dx, dy);
    }

    Pos(double x, double y) {
        this.x = x;
        this.y = y;
    }
}