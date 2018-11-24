import java.util.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final String trivialSolutionString = "No common free time exists!\n\n";

    private static String output(Schedule.TimeFrame timeFrame) {
        return String.format("The longest free time starts at %s and will last for %s.\n\n", timeFrame.convertToTimeStamp(timeFrame.startingTime), timeFrame.toString());
    }

    public static void main(String... args) {
        Schedule A = new Schedule(in), B = new Schedule(in);
        Schedule.TimeFrame result = A.commonFreeTimes(B);
        System.out.println(result == null ? trivialSolutionString : output(result));
    }
}

class Schedule {
    class TimeFrame implements Comparable<TimeFrame> {
        int startingTime, endingTime;

        TimeFrame(String startingTime, String endingTime) {
            this.startingTime = convertToMinutes(startingTime);
            this.endingTime = convertToMinutes(endingTime);
        }

        TimeFrame(int startingTime, int endingTime) {
            this.startingTime = startingTime;
            this.endingTime = endingTime;
        }

        String convertToTimeStamp(int time) {
            int numMinutes = time%60;
            int numHrs = (time - numMinutes) / 60;
            return String.format("%s:%s", appendZero(numHrs), appendZero(numMinutes));
        }

        private int convertToMinutes(String timestamp) {
            String tmp[] = timestamp.split(":");
            return (Integer.parseInt(tmp[0]) * 60) + (Integer.parseInt(tmp[1]));
        }

        private String appendZero(int num) {
            return num <= 9 ? String.format("0%d", num) : Integer.toString(num);
        }

        private String pluralize(String s) {
            int num = Integer.parseInt(s);
            return num == 0 || num == 1 ? "" : "s";
        }

        @Override
        public String toString() {
            String timeStamp = convertToTimeStamp(endingTime - startingTime);
            String[] tmp = timeStamp.split(":");
            if (tmp[0].charAt(0) == '0') tmp[0] = Character.toString(tmp[0].charAt(1));
            return String.format("%s hour%s, and %s minute%s", tmp[0], pluralize(tmp[0]), tmp[1], pluralize(tmp[1]));
        }

        @Override
        public int compareTo(TimeFrame o) {
            return (endingTime - startingTime) - (o.endingTime - o.startingTime);
        }
    }

    private List<TimeFrame> busyTimes;

    private List<TimeFrame> freeTimes() {
        ArrayList<TimeFrame> result = new ArrayList<>();
        Iterator<TimeFrame> it = busyTimes.iterator();
        TimeFrame cur = it.next();
        while(it.hasNext()) {
            TimeFrame next = it.next();
            result.add(new TimeFrame(cur.endingTime, next.startingTime));
            cur = next;
        }
        return result;
    }

    TimeFrame commonFreeTimes(Schedule that) {
        List<TimeFrame> freeA = freeTimes(), freeB = that.freeTimes();
        TimeFrame longestSharedFreeTime = null;
        int a = 0, b = 0;
        while(a < freeA.size() && b < freeB.size()) {
            TimeFrame freeSlotA = freeA.get(a);
            TimeFrame freeSlotB = freeB.get(b);
            TimeFrame sharedFreeTime = null;
            if (freeSlotB.startingTime < freeSlotA.endingTime) {
                if (freeSlotB.endingTime < freeSlotA.endingTime) {
                    sharedFreeTime = freeSlotB;
                    b++;
                } else {
                    sharedFreeTime = new TimeFrame(freeSlotB.startingTime, freeSlotA.endingTime);
                    a++;
                }
            } else if (freeSlotA.startingTime > freeSlotB.startingTime) {
                if (freeSlotA.endingTime < freeSlotB.endingTime) {
                    sharedFreeTime = freeSlotA;
                    a++;
                } else {
                    sharedFreeTime = new TimeFrame(freeSlotA.startingTime, freeSlotB.endingTime);
                    b++;
                }
            }
            if (sharedFreeTime != null && (longestSharedFreeTime == null || sharedFreeTime.compareTo(longestSharedFreeTime) > 0))
                longestSharedFreeTime = sharedFreeTime;
        }
        return longestSharedFreeTime;
    }

    Schedule(Scanner in) {
        TimeFrame[] busyTimes = new TimeFrame[in.nextInt()];
        in.nextLine();
        for (int i = 0; i < busyTimes.length; i++) {
            String[] line = in.nextLine().split(" ");
            busyTimes[i] = new TimeFrame(line[0], line[1]);
        }
        this.busyTimes = Arrays.asList(busyTimes);
    }
}

