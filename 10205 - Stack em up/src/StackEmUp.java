import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StackEmUp {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static final String[] cards = {
            "2 of Clubs",
            "3 of Clubs",
            "4 of Clubs",
            "5 of Clubs",
            "6 of Clubs",
            "7 of Clubs",
            "8 of Clubs",
            "9 of Clubs",
            "10 of Clubs",
            "Jack of Clubs",
            "Queen of Clubs",
            "King of Clubs",
            "Ace of Clubs",
            "2 of Diamonds",
            "3 of Diamonds",
            "4 of Diamonds",
            "5 of Diamonds",
            "6 of Diamonds",
            "7 of Diamonds",
            "8 of Diamonds",
            "9 of Diamonds",
            "10 of Diamonds",
            "Jack of Diamonds",
            "Queen of Diamonds",
            "King of Diamonds",
            "Ace of Diamonds",
            "2 of Hearts",
            "3 of Hearts",
            "4 of Hearts",
            "5 of Hearts",
            "6 of Hearts",
            "7 of Hearts",
            "8 of Hearts",
            "9 of Hearts",
            "10 of Hearts",
            "Jack of Hearts",
            "Queen of Hearts",
            "King of Hearts",
            "Ace of Hearts",
            "2 of Spades",
            "3 of Spades",
            "4 of Spades",
            "5 of Spades",
            "6 of Spades",
            "7 of Spades",
            "8 of Spades",
            "9 of Spades",
            "10 of Spades",
            "Jack of Spades",
            "Queen of Spades",
            "King of Spades",
            "Ace of Spades"
    };

    public static void main(String[] args) {
        String line;
        byte[][] shuffles;

        String[] newOrder = new String[52];
        String[] curOrder = new String[52];

        int numCases = in.nextInt();

        for (int test = 0; test < numCases; test++) {
            System.arraycopy(cards, 0, curOrder, 0, 52);
            byte numShuffles = in.nextByte();

            shuffles = new byte[numShuffles][52];

            for (int shuffle = 0; shuffle < numShuffles; shuffle++) {
                for (int card = 0; card < 52; card++) {
                    shuffles[shuffle][card] = in.nextByte();
                }
            }

            in.nextLine();

            line = in.nextLine();
            do {
                int shuffleIndex = Integer.parseInt(line) - 1;
                for (int newPosIndex = 0; newPosIndex < 52; newPosIndex++) {
                    newOrder[newPosIndex] = curOrder[shuffles[shuffleIndex][newPosIndex] - 1];
                }
                System.arraycopy(newOrder, 0, curOrder, 0, 52);
                try {
                    line = in.nextLine();
                } catch (NoSuchElementException ignored) {
                    break;
                }
            } while (line != null && !line.isEmpty());

            for (String card : curOrder) out.println(card);
            if (test < numCases - 1) out.println();
        }
    }
}