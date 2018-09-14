import java.util.Arrays;
import java.util.Scanner;


class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int numTournaments = in.nextInt();
        in.nextLine();

        while (numTournaments --> 0) new Tournament(in).print();
    }
}

class Tournament {
    private String name;
    private Team[] teams;

    Tournament(Scanner in) {
        name = in.nextLine();
        teams = new Team[in.nextInt()];

        in.nextLine();

        for (int i = 0; i < teams.length; i++)
            teams[i] = new Team(in.nextLine());

        int numMatches = in.nextInt();
        in.nextLine();
        while (numMatches --> 0) {
            /*
                format
                [teamAname]#[teamAgoals]@[teamBgoals]#[teamBname]
            */
            Team a = null;
            Team b = null;
            String teamAname;
            String teamBname;
            int teamAgoals;
            int teamBgoals;

            String[] tmp = in.nextLine().split("@");
            String teamAstr = tmp[0];
            String teamBstr = tmp[1];
            
            //parse team A
            tmp = teamAstr.split("#");
            teamAname = tmp[0];
            teamAgoals = Integer.parseInt(tmp[1]);
            
            for (Team team : teams) {
                if (team.name.equals(teamAname)) {
                    a = team;
                    break;
                }
            }

            //parse team B
            tmp = teamBstr.split("#");
            teamBname = tmp[1];
            teamBgoals = Integer.parseInt(tmp[0]);
            
            for (Team team : teams) {
                if (team.name.equals(teamBname)) {
                    b = team;
                    break;
                }
            }
            
            //add goal values
            assert a != null;
            a.goalsScored += teamAgoals;
            a.goalsAgainst += teamBgoals;

            assert b != null;
            b.goalsScored += teamBgoals;
            b.goalsAgainst += teamAgoals;

            //add wins/ties/losses
            if (teamAgoals == teamBgoals) {
                a.numTies++;
                b.numTies++;
            } else if (teamAgoals > teamBgoals) {
                a.numWins++;
                b.numLoss++;
            } else {
                a.numLoss++;
                b.numWins++;
            }
        }

        Arrays.sort(teams);
    }

    void print() {
        System.out.println(name);

        for (int i = 0; i < teams.length; i++) {
            Team team = teams[i];
            System.out.printf("%d) %s\n", i, team.toString());
        }

        System.out.println();
    }

    class Team implements Comparable<Team> {

        Team(String name) {
            this.name = name;
        }

        String name;
        int numWins = 0;
        int numTies = 0;
        int numLoss = 0;
        int goalsScored = 0;
        int goalsAgainst = 0;

        int points() {
            return 3 * numWins + numTies;
        }

        int gamesPlayed() {
            return numWins + numTies + numLoss;
        }
        
        int goalDifference() {
            return goalsScored - goalsAgainst;
        }

        public String toString() {
            return String.format("%s %dp, %dg (%d-%d-%d), %dgd (%d-%d)",
                    name,
                    points(),
                    gamesPlayed(),
                    numWins,
                    numTies,
                    numLoss,
                    goalDifference(),
                    goalsScored,
                    goalsAgainst
            );
        }

        @Override
        public int compareTo(Team o) {
            if (points() > o.points()) return 1;
            if (points() < o.points()) return -1;

            if (numWins > o.numWins) return 1;
            if (numWins < o.numWins) return -1;

            if (goalDifference() > o.goalDifference()) return 1;
            if (goalDifference() < o.goalDifference()) return -1;

            if (goalsScored > o.goalsScored) return 1;
            if (goalsScored < o.goalsScored) return -1;

            if (gamesPlayed() < o.gamesPlayed()) return -1;
            if (gamesPlayed() > o.gamesPlayed()) return 1;

            return name.compareToIgnoreCase(o.name);
        }
    }
}