import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Locale.setDefault(Locale.FRANCE);
        System.out.println(STR."Locale default: \{Locale.getDefault()}");

        Locale en = new Locale("en", "US");
        System.out.println(STR."Locale en: \{en}");

        Locale enIN = new Locale.Builder().setLanguage("en").setRegion("IN").build();
        Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM).withLocale(en);

        for (var locale : List.of(en, enIN, enNZ, Locale.UK,
                Locale.FRANCE, Locale.GERMANY, Locale.ITALY, Locale.JAPAN,
                Locale.KOREA, Locale.CHINA, Locale.TAIWAN, Locale.CANADA,
                Locale.CANADA_FRENCH, Locale.US, Locale.ROOT)) {
            System.out.println(STR."Locale: \{locale}");
            System.out.println(STR."\{locale.getDisplayName()}= \{LocalDateTime.now()
                    .format(dtf.withLocale(locale))}");
        }

        DateTimeFormatter wdayMonth = DateTimeFormatter
                .ofPattern("EEEE, MMMM d, yyyy");

        LocalDate may5th = LocalDate.of(2021, 5, 5);

        System.out.println("--------------------");

        for (var locale : List.of(Locale.CANADA, Locale.CANADA_FRENCH,
                Locale.US, Locale.UK, Locale.FRANCE, Locale.GERMANY,
                Locale.ITALY, Locale.JAPAN, Locale.KOREA, Locale.CHINA,
                Locale.TAIWAN, Locale.of("en", "UG"))) {
            System.out.println(
                    STR."""
                        \{locale.getDisplayName()} : \{locale.getDisplayName(locale)}=
                        \t\{may5th.format(wdayMonth.withLocale(locale))}"""
            );

            NumberFormat nf = NumberFormat.getInstance(locale);
            nf.setMaximumFractionDigits(6);
            System.out.println(STR."Number: \{nf.format(1234567890.12345678)}");

            NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
            Currency localCurrency = Currency.getInstance(locale);
            System.out.println(STR."Currency: \{currency.format(1234567890.12345678)}\{localCurrency.getCurrencyCode()} \{localCurrency.getDisplayName(locale)}\{localCurrency.getDisplayName()}");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(STR."Enter a number: ");
        scanner.useLocale(Locale.FRANCE);
        String input = scanner.nextBigDecimal().toString();
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRANCE);
        System.out.println(STR."BigDecimal: \{nf.format(new BigDecimal(input))}");
    }
}