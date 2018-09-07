import java.io.PrintStream;
import java.util.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;

    public static void main(String[] args) {
	    while(in.hasNextLine()) {
	        int[] a = new int[26], b = new int[26];

	        char[] line1 = in.nextLine().toCharArray();
	        char[] line2 = in.nextLine().toCharArray();

	        for (char c : line1) a[c-'a']++;
	        for (char c : line2) b[c-'a']++;

	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < 26; i++) {
	            int r = Math.min(a[i],b[i]);
	            for (int j = 0; j < r; j++) {
	                sb.append((char)('a' + i));
                }
            }

            out.println(sb);
        }
    }
}