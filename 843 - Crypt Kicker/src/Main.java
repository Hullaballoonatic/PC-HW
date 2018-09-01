import java.io.PrintStream;
import java.util.*;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;
    private static final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static String[] unencryptedWords;

    public static void main(String[] args) {
        int numWords = Integer.parseInt(in.nextLine());
        unencryptedWords = new String[numWords];
        for (int i = 0; i < numWords; i++) {
            unencryptedWords[i] = in.nextLine();
        }

        while (in.hasNext()) {
            String line = in.nextLine();
            out.println(transform(line, deriveCipher(line)));
        }
    }

    private static String transform(String unencrypted, Map<Character,Character> cipher) {
        char[] result = unencrypted.toCharArray();

        for (int i = 0; i < unencrypted.length(); i++) {
            if (result[i] != ' ') result[i] = cipher.get(result[i]);
        }

        return Arrays.toString(result);
    }

    private static Map<Character, Character> createRandomCipher() {
        Map<Character, Character> cipher = new HashMap<>();

        for (char letter : alphabet) {
            char candidate;
            do {
                candidate = alphabet[(int) (Math.random() * 26)];
            } while (candidate == letter || cipher.containsValue(candidate));
            cipher.put(letter, candidate);
        }

        return cipher;
    }

    private static Map<Character, Character> deriveCipher(String encryptedMessage) {

        //bfs() {
        LinkedList<String> q = new LinkedList<>();
        ArrayList<Map<Character, Character>> checked = new ArrayList<>();

        //
    }

    class Node {
        String encryptedWord;
        String[] children;


    }
}