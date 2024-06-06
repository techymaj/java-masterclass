import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "America/Los_Angeles");
        Instant now = Instant.now();

        for (ZoneId z : List.of(
                ZoneId.of("Europe/Paris"),
                ZoneId.of("America/New_York"),
                ZoneId.of("Asia/Tokyo")
        )) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("z:zzzz");
            System.out.println(z);
            System.out.println("\t" + now.atZone(z).format(formatter));
            System.out.println("\t" + z.getRules().getDaylightSavings(now));
            System.out.println("\t" + z.getRules().isDaylightSavings(now));
        }

        Instant dobInstant = Instant.parse("2020-01-01T08:01:00Z");
        LocalDateTime dob = LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        System.out.println("Your DOB: " + dob.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }
}
