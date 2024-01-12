public class Challenge {
    public static void main(String[] args) {
        String time = getDurationString( 3950);
//        System.out.println(time);

        time = getDurationString( 65, 145);
        System.out.println(time);
    }
    static String getDurationString(int seconds) {

        if (!(seconds >= 0)) {
            return "Invalid seconds";
        }

        int minutes = seconds / 60;

        return getDurationString(minutes, seconds);
    }

    static String getDurationString(int minutes, int seconds) {

        if (!((minutes >= 0) && (seconds >= 0))) {
            return "Invalid Values";
        }

        int addedMins = 0;
        int currSecs = 0;
        if (seconds > 59) {
            addedMins = seconds / 60;
            currSecs = seconds % 60;
        }

        return  (minutes / 60) + "h " + ((minutes % 60) + addedMins) + "m " + currSecs % 60 + "s";
    }
}
