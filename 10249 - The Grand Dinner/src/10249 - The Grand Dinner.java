import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.System.out;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String... args) {
        while (true) {
            int numTeams = in.nextInt(), numTables = in.nextInt();
            if (numTeams == 0)
                break;

            Team[] teams = new Team[numTeams];
            for (int i = 0; i < numTeams; i++)
                teams[i] = new Team(i + 1, in.nextInt());

            Table[] tables = new Table[numTables];
            int totalCapacity = 0;
            for (int i = 0; i < numTables; i++) {
                tables[i] = new Table(i + 1, in.nextInt());
                if (tables[i].capacity > 0)
                    totalCapacity++;
            }
            Arrays.sort(teams);

            boolean possible = true;

            for (Team team : teams) {
                if (team.numMembers > totalCapacity) {
                    possible = false;
                    break;
                }

                Arrays.sort(tables);

                for (int teamMemberIndex = 0; teamMemberIndex < team.numMembers; teamMemberIndex++)
                    if (tables[teamMemberIndex].capacity > 0) {
                        team.assign(tables[teamMemberIndex]);
                        if (tables[teamMemberIndex].capacity == 0) totalCapacity--;
                    }
            }

            if (possible) {
                out.println(1);

                Arrays.sort(teams, Comparator.comparingInt(o -> o.index));

                for (Team team : teams)
                    out.println(team.toString());
            } else out.println(0);
        }
    }
}

class Team implements Comparable<Team> {
    int index, numMembers;
    private int numAssigned = 0;
    private Table[] tables;

    Team(int index, int numMembers) {
        this.index = index;
        this.numMembers = numMembers;
        tables = new Table[numMembers];
    }

    void assign(Table table) {
        tables[numAssigned++] = table;
        table.capacity--;
    }

    @Override
    public int compareTo(Team o) {
        return o.numMembers - this.numMembers;
    }

    @Override
    public String toString() {
        Arrays.sort(tables);
        StringBuilder sb = new StringBuilder();
        for (Table table : tables)
            sb.append(table.index).append(" ");
        return sb.toString().trim();
    }
}

class Table implements Comparable<Table> {
    int index, capacity;

    Table(int index, int capacity) {
        this.index = index;
        this.capacity = capacity;
    }

    @Override
    public int compareTo(Table o) {
        return o.capacity - capacity;
    }
}