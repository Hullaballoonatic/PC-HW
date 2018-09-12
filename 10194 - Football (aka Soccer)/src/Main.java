import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;


class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int numTournaments = in.nextInt();
        in.nextLine();

        while (numTournaments-- > 0) new Tournament(in).print();
    }
}

class Tournament {
    private String name;
    private TreeSet<Team> teams;

    Tournament(Scanner in) {
        name = in.nextLine();
        int numTeams = in.nextInt();
        in.nextLine();

        teams = new TreeSet<>(new TeamComparator());

        for (int i = 0; i < numTeams; i++)
            teams.add(new Team(in.nextLine()));

        int numMatches = in.nextInt();
        in.nextLine();
        while (numMatches-- > 0) {
            String[] tmp = in.nextLine().split("@");
            String teamAstr = tmp[0];
            String teamBstr = tmp[1];
            tmp = teamAstr.split("#");
            String teamAname = tmp[0];
            Team a = null;
            for (Team team : teams) {
                if (team.name.equals(teamAname)) {
                    a = team;
                    a.gamesPlayed++;
                    break;
                }
            }
            int teamAgoals = Integer.parseInt(tmp[1]);

            assert a != null;
            a.goalsScored += teamAgoals;

            tmp = teamBstr.split("#");
            String teamBname = tmp[1];
            Team b = null;
            for (Team team : teams) {
                if (team.name.equals(teamBname)) {
                    b = team;
                    b.gamesPlayed++;
                    break;
                }
            }
            int teamBgoals = Integer.parseInt(tmp[0]);

            assert b != null;
            b.goalsScored += teamBgoals;

            b.goalsAgainst += teamAgoals;
            a.goalsAgainst += teamBgoals;

            int goalDifference = teamAgoals - teamBgoals;

            if (goalDifference == 0) {
                a.points++;
                a.numTies++;
                b.points++;
                b.numTies++;
            } else if (goalDifference > 0) {
                a.numWins++;
                b.numLoss++;
                a.points += 3;
                a.highestGoalDifference = Math.max(a.highestGoalDifference, goalDifference);
            } else {
                a.numLoss++;
                b.numWins++;
                b.points += 3;
                b.highestGoalDifference = Math.max(a.highestGoalDifference, -1 * goalDifference);
            }
        }

        sortTeams();
    }

    private void sortTeams() {

    }

    static class TeamComparator implements Comparator<Team> {
        @Override
        public int compare(Team a, Team b) {
            if (a.points > b.points) return 1;
            if (a.points < b.points) return -1;
            if (a.numWins > b.numWins) return 1;
            if (a.numWins < b.numWins) return -1;
            if (a.highestGoalDifference > b.highestGoalDifference) return 1;
            if (a.highestGoalDifference < b.highestGoalDifference) return -1;
            if (a.goalsScored > b.goalsScored) return 1;
            if (a.goalsScored < b.goalsScored) return -1;
            if (a.gamesPlayed < b.gamesPlayed) return 1;
            if (a.gamesPlayed > b.gamesPlayed) return -1;
            String aName = a.name.toLowerCase();
            String bName = b.name.toLowerCase();
            int length = Math.min(aName.length(), bName.length());
            for (int i = 0; i < length; i++) {
                if (aName.charAt(i) < bName.charAt(i)) return 1;
                if (aName.charAt(i) > bName.charAt(i)) return -1;
            }
            return Integer.compare(bName.length(), aName.length());
        }
    }

    class Team {
        String name;
        int points = 0;
        int numWins = 0;
        int numTies = 0;
        int numLoss = 0;
        int goalsScored = 0;
        int goalsAgainst = 0;
        int highestGoalDifference;
        int gamesPlayed;

        Team(String name) {
            this.name = name;
        }
    }

    void print() {
        System.out.println(name);
        int numTeams = teams.size();
        for (int i = 1; i == numTeams; i++) {
            Team team = teams.pollFirst();
            assert team != null;
            System.out.printf("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n", i + 1, team.name, team.points, team.gamesPlayed, team.numWins, team.numTies, team.numLoss, team.highestGoalDifference, team.goalsScored, team.goalsAgainst);
        }
    }
}

