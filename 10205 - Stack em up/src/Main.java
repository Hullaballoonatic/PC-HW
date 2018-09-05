import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        int numCases = in.nextInt();
        in.nextLine();
        in.nextLine();

        for (int i = 0; i < numCases; i++) {
            byte numShuffles = in.nextByte();

            byte[] cards;

            byte[][] shuffles = new byte[numShuffles][52];

            for (int shuffle = 0; shuffle < numShuffles; shuffle++) {
                for (int card = 0; card < 52; card++) {
                    shuffles[shuffle][card] = in.nextByte();
                }
            }

            cards = shuffles[in.nextInt()];
            in.nextLine();
            while(true) {
                try {
                    cards = shuffle(cards, shuffles[Integer.parseInt(in.nextLine())]);
                } catch (NumberFormatException ignored) {
                    break;
                }
            }

            printCards(cards);
        }
    }

    private static byte[] shuffle(byte[] cards, byte[] shuffle) {
        byte[] result = new byte[52];
        for (int i = 0; i < cards.length; i++) {
            result[shuffle[i]] = cards[i];
        }

        return result;
    }

    private static void printCards(byte[] cards) {
        for (byte card : cards) {
            String suit = " of ";
            switch (card%13) {
                case 0: suit += "Clubs";
                case 1: suit += "Diamonds";
                case 2: suit += "Hearts";
                case 3: suit += "Spades";
            }
        }
    }
}
