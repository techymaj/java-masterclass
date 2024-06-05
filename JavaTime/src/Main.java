import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {

        LocalDate date = LocalDate.now();
        System.out.println("Hello World! Today is " + date);

        LocalDate five5 = LocalDate.of(2020, 5, 5);
        System.out.println("Cinco de Mayo is on " + five5);

        LocalDate may5th = LocalDate.of(2020, Month.MAY, 5);
        System.out.println("Cinco de Mayo is on " + may5th);

        LocalDate fourthOfJuly = LocalDate.ofYearDay(2020, 186);
        System.out.println("The 4th of July is on " + fourthOfJuly);

        LocalDate m5th = LocalDate.parse("2020-05-05");
        System.out.println("Cinco de Mayo is on " + m5th);

        System.out.println(fourthOfJuly.getMonth());
        System.out.println(fourthOfJuly.getMonthValue());
        System.out.println(fourthOfJuly.getDayOfMonth());
        System.out.println(fourthOfJuly.isLeapYear());

        System.out.println("-----------------");
        System.out.println(fourthOfJuly.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(fourthOfJuly.get(ChronoField.DAY_OF_MONTH));
        System.out.println(fourthOfJuly.get(ChronoField.YEAR));

        System.out.println("-----------------");
        System.out.println(fourthOfJuly.withDayOfMonth(5));
        System.out.println(fourthOfJuly.with(ChronoField.DAY_OF_MONTH, 5));
        System.out.println(fourthOfJuly.plusYears(4));
        System.out.println(fourthOfJuly.minus(1, ChronoUnit.MONTHS));

        System.out.println("-----------------");
        System.out.println("fourthOfJuly.isAfter(m5th) = " + fourthOfJuly.isAfter(m5th));
        System.out.println("fourthOfJuly.isBefore(m5th) = " + fourthOfJuly.isBefore(m5th));

        System.out.println("-----------------");
        m5th.datesUntil(fourthOfJuly).forEach(System.out::println);

        System.out.println("-----------------");
        // Period - Each date is a week later than the previous one
        m5th.datesUntil(fourthOfJuly.plusYears(1), Period.ofDays(7))
                .forEach(System.out::println);

        LocalTime time = LocalTime.now();
        System.out.println("The current time is " + time);

        LocalTime bedTime = LocalTime.of(22, 30);
        System.out.println("My bedtime is at " + bedTime);

        LocalTime wakeUp = LocalTime.parse("06:30");
        System.out.println("I wake up at " + wakeUp);

        LocalTime lunchTime = LocalTime.of(12, 0, 30);
        System.out.println("Lunch time is at " + lunchTime);

        System.out.println("Wake Up at AMPM_OF_DAY = " + wakeUp.get(ChronoField.AMPM_OF_DAY));
        System.out.println("Sleep at AMPM_OF_DAY = " + bedTime.get(ChronoField.AMPM_OF_DAY));

//        System.out.println(wakeUp.get(ChronoField.YEAR)); // UnsupportedTemporalTypeException

        System.out.println(bedTime.range(ChronoField.HOUR_OF_DAY));
        System.out.println(bedTime.range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(bedTime.range(ChronoField.MINUTE_OF_DAY));
        System.out.println(bedTime.range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(bedTime.range(ChronoField.MICRO_OF_SECOND));

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("The current date and time is " + dateTime);
        System.out.println(dateTime.format(DateTimeFormatter.ISO_WEEK_DATE));

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(dateTime.format(dtf));
        System.out.println("Day of week: " + dateTime.getDayOfWeek().toString().toLowerCase());
        System.out.println("Hour of day: " + dateTime.getHour());
        System.out.println("Minute of hour: " + dateTime.getMinute());
        System.out.println(System.currentTimeMillis());
    }
}
