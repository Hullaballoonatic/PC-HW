import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final TeamComparator comp = new TeamComparator();
    private static ArrayList<Team> teams;

    public static void main(String[] args) {
        int numCases = in.nextInt();
        in.nextLine();

        while (numCases-->0) {
            teams = new ArrayList<>();
            String submission;
            while(!(submission = in.nextLine().trim()).isEmpty()) {
                String[] tmp = submission.split(" ");
                team(Integer.parseInt(tmp[0])).submission(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]), tmp[3]);
            }
            teams.sort(comp);

            for(Team team : teams) System.out.println(team.toString());
            System.out.println();
        }
    }

    private static Team team(int id) {
        for(Team team : teams) {
            if (team.id == id) return team;
        }
        Team newTeam = new Team(id);
        teams.add(newTeam);
        return newTeam;
    }
}

class TeamComparator implements Comparator<Team> {
    @Override
    public int compare(Team a, Team b) {
        if(a.numCorrect() > b.numCorrect()) return -1;
        if(a.numCorrect() < b.numCorrect()) return 1;
        if(a.penaltyTime() < b.penaltyTime()) return -1;
        if(a.penaltyTime() > b.penaltyTime()) return 1;
        return Integer.compare(a.id, b.id);
    }
}

class Team implements Comparable<Team>{
    int id;
    private Problem[] problems = new Problem[9];

    void submission(int number, int time, String L) {
        Problem p = problems[number - 1];

        if (p.startingTime == 0)
            p.startingTime = time;

        if (p.acceptedTime < 0)
            switch (L) {
                case "C": p.acceptedTime = time;
                case "I": p.incorrectSubmissions++;
            }
    }

    int numCorrect() {
        int n = 0;
        for (Problem p : problems)
            if (p.acceptedTime > 0) n++;

        return n;
    }

    int penaltyTime() {
        int t = 0;
        for (Problem p : problems)
            t += p.penaltyTime();

        return t;
    }

    Team(int id) {
        this.id = id;
        Arrays.fill(problems, new Problem());
    }

    @Override
    public int compareTo(Team o) {
        if(numCorrect() > o.numCorrect()) return -1;
        if(numCorrect() < o.numCorrect()) return 1;
        if(penaltyTime() < o.penaltyTime()) return -1;
        if(penaltyTime() > o.penaltyTime()) return 1;
        if(id < o.id) return -1;

        return 1;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d",id, numCorrect(), penaltyTime());
    }
}

class Problem {
    int startingTime = 0, acceptedTime = -1, incorrectSubmissions = 0;

    int penaltyTime() {
        return acceptedTime > 0 ? (acceptedTime - startingTime) + (20 * incorrectSubmissions) : 0;
    }
}