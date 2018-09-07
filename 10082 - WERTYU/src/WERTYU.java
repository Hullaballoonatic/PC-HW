import java.util.NoSuchElementException;
import java.util.Scanner;

public class WERTYU {
    private static final char[] QWERTY = "`1234567890-QWERTYUIOP[]ASDFGHJKL;ZXCVBNM,.".toCharArray();
    private static final String WERTYU = "1234567890-=WERTYUIOP[]\\SDFGHJKL;'XCVBNM,./";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        char[] tmp;
        try {
            while ((line = in.nextLine()) != null) {
                tmp = line.toCharArray();
                StringBuilder fixed = new StringBuilder();
                for (char c : tmp) {
                    if (c == ' ') fixed.append(' ');
                    else {
                        fixed.append(QWERTY[WERTYU.indexOf(c)]);
                    }
                }
                System.out.println(fixed);
            }
        } catch (NoSuchElementException ignored) {
        }
    }
}

/*

W S X
=\'/

 */