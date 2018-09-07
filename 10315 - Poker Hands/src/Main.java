import java.io.PrintStream;
import java.util.*;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;
    private static Card[] black = new Card[5];
    private static Card[] white = new Card[5];

    public static void main(String[] args) {
        String line;
        String[] tmp;
        try {
            while ((line = in.nextLine()) != null) {
                tmp = line.split(" ");
                for (int i = 0; i < 5; i++) {
                    black[i] = new Card(tmp[i]);
                }
                for (int i = 5; i < 10; i++) {
                    white[i - 5] = new Card(tmp[i]);
                }
                out.println(getWinner(new Hand("Black", black), new Hand("White", white)));
            }
        } catch (NoSuchElementException ignored) {
        }
    }

    private static String getWinner(Hand hand1, Hand hand2) {
        if (hand1.getValue() > hand2.getValue()) return hand1.toString() + " wins.";
        if (hand1.getValue() < hand2.getValue()) return hand2.toString() + " wins.";
        return "Tie.";
    }
}

enum Suit {
    Clubs,
    Diamonds,
    Hearts,
    Spades
}

class Card implements Comparable<Card> {
    int num;
    Suit suit;

    private static final String cardNums = "23456789TJQKA";

    Card(String card) {
        switch (card.charAt(1)) {
            case 'C':
                suit = Suit.Clubs;
            case 'D':
                suit = Suit.Diamonds;
            case 'H':
                suit = Suit.Hearts;
            case 'S':
                suit = Suit.Spades;
        }
        num = cardNums.indexOf(card.charAt(0));
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.num, o.num);
    }
}

class Hand {
    private HandType handType;

    private int getHandTypeMulti() {
        switch (handType) {
            case HIGH_CARD:
                return 1;
            case PAIR:
                return 10;
            case TWO_PAIR:
                return 100;
            case THREE_OF_A_KIND:
                return 1000;
            case STRAIGHT:
                return 10000;
            case FLUSH:
                return 100000;
            case FULL_HOUSE:
                return 1000000;
            case FOUR_OF_A_KIND:
                return 10000000;
        }
        return 100000000;
    }

    enum HandType {
        HIGH_CARD,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH
    }

    int getValue() {

    }

    private String owner;

    private List<Card> cards;

    Hand(String owner, Card[] in) {
        this.owner = owner;
        cards = Arrays.asList(in);
        Collections.sort(cards);

        List<Card> matchingCards = getMatchingCards();
        switch (matchingCards.size()) {
            case 5:
                this.handType = HandType.FULL_HOUSE;
            case 4:
                this.handType = checkFourOfAKind(matchingCards) ? HandType.FOUR_OF_A_KIND : HandType.TWO_PAIR;
            case 3:
                this.handType = HandType.THREE_OF_A_KIND;
            case 2:
                this.handType = HandType.PAIR;
        }

        boolean flush = checkFlush();
        boolean straight = checkStraight();

        if (flush && straight) this.handType = HandType.STRAIGHT_FLUSH;
        else if (flush) this.handType = HandType.FLUSH;
        else if (straight) this.handType = HandType.STRAIGHT;
        else this.handType = HandType.HIGH_CARD;
    }

    private boolean checkFourOfAKind(List<Card> matchingCards) {
        return matchingCards.get(0) == matchingCards.get(3);
    }

    private List<Card> getMatchingCards() {
        Set<Card> uniques = new HashSet<>();
        List<Card> duplicates = new ArrayList<>();
        for (Card card : cards) {
            if (!uniques.add(card)) {
                duplicates.add(card);
            }
        }
        return duplicates;
    }

    private boolean checkStraight() {
        ListIterator<Card> it = cards.listIterator(cards.size());
        while (it.hasNext()) {
            if (!it.hasPrevious()) continue;
            if (it.next().num != it.previous().num + 1) return false;
            it.next();
        }
        return true;
    }

    private boolean checkFlush() {
        Suit suit = cards.get(0).suit;
        for (int i = 1; i < 5; i++) {
            if (cards.get(i).suit != suit) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return owner;
    }
}