import java.util.*;

import static java.lang.System.out;

@SuppressWarnings("unchecked")
class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int numScenarios = in.nextInt();
    private static String[] cities;
    private static String goal;
    private static int curScenario = 0;
    private static List<Node> journey = new ArrayList<>();
    private static Map<String, Map<String, List<Integer>>> connections;
    private static final String failOutput = "No connection";

    private static void input() {
        cities = new String[in.nextInt()];
        in.nextLine();
        for (int i = 0; i < cities.length; i++)
            cities[i] = in.nextLine();

        connections = new HashMap<>();
        for (String city : cities) {
            if (!connections.keySet().contains(city)) connections.put(city, new HashMap<>());
            
        }

        journey.clear();
        journey.add(new Node(Integer.parseInt(in.nextLine()), in.nextLine()));
        goal = in.nextLine();
    }

    private static void output() {
        out.printf("Scenario %d\n", curScenario);

        out.println();
    }

    public static void main(String ... args) {
	    while (++curScenario<=numScenarios) {
	        input();
	        output();
        }
    }
}

class Line {
    List<Integer> fromStops, toStops;
}

class Node {
    int time;
    String city;

    Node(int time, String city) {
        this.time = time;
        this.city = city;
    }
}

class Train {
    int numStops;
    String[] cities;
    int[] times;

    Train(int numStops, Scanner in) {
        in.nextLine();

        this.numStops = numStops;

        cities = new String[numStops];
        times = new int[numStops];

        for (int i = 0; i < numStops; i++) {
            String[] tmp = in.nextLine().split(" ");
            times[i] = Integer.parseInt(tmp[0]);
            cities[i] = tmp[2];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Train\n");
        for (int i = 0; i < numStops; i++) {
            sb.append(String.format("%d %s\n", times[i], cities[i]));
        }
        return sb.append("\n").toString();
    }
}