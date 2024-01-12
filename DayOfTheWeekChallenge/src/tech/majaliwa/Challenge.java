package tech.majaliwa;

public class Challenge {
    public static void main(String[] args) {
        printDayOfTheWeek(9);
    }

    public static void printDayOfTheWeek(int day) {
        String dayOfTheWeek = switch (day) {
            case 0 -> "Sunday";
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "Invalid day";
        };

        if (day >= 0 && day < 7)
            System.out.println(day + " is a " + dayOfTheWeek);
        else
            System.out.println(day + " is an " + dayOfTheWeek);
    }
}
