import java.util.ArrayList;
import java.util.Scanner;

class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String stack;
        while (in.hasNextLine()) {
            stack = in.nextLine();
            ArrayList<Integer> nums = new ArrayList<>();

            String[] tmp = stack.split(" ");

            for (String n : tmp) nums.add(Integer.parseInt(n));

            System.out.println(stack);
            printList(doFlips(nums));
        }
    }

    private static void printList(ArrayList<Integer> li) {
        StringBuilder out = new StringBuilder();
        for (Integer f : li) out.append(f).append(" ");
        System.out.println(out.append(0));
    }

    private static ArrayList<Integer> doFlips(ArrayList<Integer> li) {
        ArrayList<Integer> result = new ArrayList<>();
        int size = li.size();

        if (size <= 1) return result;

        for (int cut_i = size, top_i, max_i; cut_i != 1; cut_i--) {
            if ((max_i = getMaxIndex(li, cut_i)) < (top_i = cut_i - 1)) {
                if (max_i > 0) {
                    result.add(size - max_i);
                    li = flip(li, max_i);
                }

                result.add(size - top_i);
                li = flip(li, top_i);
            }
        }

        return result;
    }

    private  static ArrayList<Integer> flip(ArrayList<Integer> li, int f) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = f; i >= 0; i--) {
            result.add(li.get(i));
        }
        for (int i = f + 1; i < li.size(); i++) {
            result.add(li.get(i));
        }
        return result;
    }

    private static int getMaxIndex(ArrayList<Integer> li, int cut) {
        int max = 0;
        int max_i = -1;
        for (int i = 0; i < cut; i++)
            if (li.get(i) > max) {
                max = li.get(i);
                max_i = i;
            }
        return max_i;
    }
}