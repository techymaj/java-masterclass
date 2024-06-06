import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) {
        System.setProperty("user.timezone", "America/Los_Angeles");
        System.out.println(ZoneId.systemDefault());
        System.out.println("Number of TZs: " + ZoneId.getAvailableZoneIds().size());
        ZoneId.getAvailableZoneIds().stream()
                .filter(zoneId -> zoneId.contains("Africa/K"))
                .sorted()
                .map(ZoneId::of)
                .forEach(z -> System.out.println(z + " " + z.getRules()));

        System.out.println("--------------------");
        Set<String> jdk8Zones = ZoneId.getAvailableZoneIds();
        String[] alternate = TimeZone.getAvailableIDs();
        Set<String> oldWay = new HashSet<>(Set.of(alternate));

        System.out.println(jdk8Zones.size() + " " + oldWay.size());

        oldWay.remove(jdk8Zones);
        System.out.println(oldWay);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        Instant nowInstant = Instant.now();
        System.out.println(nowInstant);
    }
}
