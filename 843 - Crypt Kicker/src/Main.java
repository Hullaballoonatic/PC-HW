import java.util.*;

import static java.lang.System.out;

class Main {
    private static Scanner in = new Scanner(System.in);
    private static final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final Map<Integer, List<char[]>> dictionaryByLength = new HashMap<Integer, List<char[]>>() {{
        int size = in.nextInt();
        in.nextLine();
        while(size-->0) {
            String word = in.nextLine();
            int length = word.length();
            if(!containsKey(length)) put(length, new ArrayList<>());
            get(length).add(word.toCharArray());
        }
    }};

    private static int largestWord;

    private static final Map<Integer, List<char[]>> uncheckedEncryptedWords = new HashMap<>();

    private static String decrypt(String sentence, Map<Character, Character> cipher) {
        for (char letter : alphabet)
            sentence = sentence.replace(letter, cipher.get(letter));
        return sentence;
    }

    private static final Map<Character, Character> IMPOSSIBLE_CIPHER = new HashMap<Character, Character>() {{
        for (char letter : alphabet)
            put(letter, '*');
    }};

    // recursive function
    private static boolean decprypt(int length, Map<Character, Character> cipher) {
        // create default cipher
        if (cipher == null) cipher = new HashMap<Character, Character>() {{
            for (char letter : alphabet) put(letter, null);
        }};

        List<char[]> possibleMatches = dictionaryByLength.get(length);

        char[] curWord = uncheckedEncryptedWords.get(length).remove(0);

        if(curWord == null && length == largestWord) return true;

        for(char[] possibleMatch : possibleMatches) {
            if (addMapping(curWord, possibleMatch, cipher)) {

            }
        }
    }

    private static boolean addMapping(char[] curWord, char[] possibleMatch, Map<Character, Character> cipher) {
        Map<Character, Character> possibleChanges = new HashMap<>();
        for(int i = 0; i < curWord.length; i++) {
            if(cipher.get(possibleMatch[i]) == null && !possibleChanges.containsKey(possibleMatch[i]))
                possibleChanges.put(possibleMatch[i], curWord[i]);

            else if (cipher.get(possibleMatch[i]) != curWord[i]) return false;
        }
        if (dictionaryByLength.get(curWord.length).contains(decrypt(new String(curWord), possibleChanges).toCharArray()))
            for (char letter : possibleChanges.keySet())
                cipher.replace(letter, possibleChanges.get(letter));
        return true;
    }

    public static void main(String[] args) {
        String encryptedSentence;
        while((encryptedSentence = in.nextLine()) != null) {
            String[] encryptedWords = encryptedSentence.split(" ");
            for (String encryptedWord : encryptedWords) {
                int length = encryptedWord.length();
                if (length > largestWord) largestWord = length;
                if (!uncheckedEncryptedWords.containsKey(length))
                    uncheckedEncryptedWords.put(length, new ArrayList<>());
                uncheckedEncryptedWords.get(length).add(encryptedWord.toCharArray());
            }
            out.println(decrypt(encryptedSentence, createCipher(0, null)));
        }
    }
}