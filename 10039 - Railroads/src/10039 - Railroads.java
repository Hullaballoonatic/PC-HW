import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.System.out;

class Connection {
    Stop from, to;

    Connection(Stop from, Stop to) {
        this.from = from;
        this.to = to;
    }
}

class Stop {
    int time;
    String city;

    Stop(int time, String city) {
        this.time = time;
        this.city = city.trim();
    }
}

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final String trivialSolutionString = "No connection\n";
    private static int latestFinish;

    public static void main(String[] args){
        for (int scenario = 1, numScenarios = in.nextInt(); scenario <= numScenarios; scenario++) {
            out.printf("Scenario %d\n", scenario);

            List<String> cities = new ArrayList<String>() {{
                for (int i = 0, numCities = parseInt(in.nextLine()); i < numCities; i++)
                    add(in.nextLine());
            }};

            latestFinish = 0;
            List<Connection> connections = new ArrayList<Connection>() {{
                for (int numTrains = in.nextInt(); numTrains > 0; numTrains--) {
                    for(int numStops = in.nextInt(); numStops > 0; numStops--) {
                        Stop from = new Stop(in.nextInt(), in.nextLine());
                        while(numStops-->0) {
                            Stop to = new Stop(in.nextInt(), in.nextLine());
                            add(new Connection(from, to));
                            latestFinish = max(latestFinish, to.time);
                            from = to;
                        }
                    }
                }
            }};

            int earliestStart = parseInt(in.nextLine());
            String start = in.nextLine(), destination = in.nextLine();

            // adjacency list
            Map<String, Map<Integer, Integer>> fastestTime = new HashMap<String, Map<Integer, Integer>>() {{
                for (String city : cities) put(city, new HashMap<>());
                for (Connection connection : connections) {
                    if (!get(connection.from).containsKey(connection.to.time))
                        get(connection.from).put(connection.to.time, -1);
                    if (!get(connection.from).containsKey(connection.from.time))
                        get(connection.from).put(connection.from.time, -1);

                    if (connection.from.city.equals(start) && connection.from.time >= earliestStart)
                        for (int time = connection.to.time - 1; time < latestFinish; time++)
                            if (get(connection.to).containsKey(time)) get(connection.to).replace(time, max(get(connection.to).get(time), connection.from.time));
                            else get(connection.to).put(time, connection.from.time);
                    else if (get(connection.from).get(connection.from.time) >= 0) {
                        for (int time = connection.to.time - 1; time < latestFinish; time++)
                            if (get(connection.to).containsKey(time)) get(connection.to).replace(time, max(get(connection.to).get(time), connection.from.time));
                            else get(connection.to).put(time, connection.from.time);
                    }
                }
            }};

            String solution = trivialSolutionString;

            for (int time = 0; time < latestFinish; time++)
                if (fastestTime.get(destination).get(time) >= 0) {
                    solution = String.format("Departure %04d %s\nArrival   %04d %s\n", fastestTime.get(destination).get(time), start, time + 1, destination);
                    break;
                }

            out.println(solution);
        }
    }
}