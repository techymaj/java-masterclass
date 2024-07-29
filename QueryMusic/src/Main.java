import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            properties.load(
                    Files.newInputStream(
                            Path.of("music.properties"),
                            StandardOpenOption.READ
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var datasource = new MysqlDataSource();
        datasource.setServerName(properties.getProperty("serverName"));
        datasource.setPort(Integer.parseInt(properties.getProperty("port")));
        datasource.setDatabaseName(properties.getProperty("databaseName"));
        datasource.setUser(properties.getProperty("user"));
        datasource.setPassword(System.getenv("MYSQL_PASS"));

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter an artist id: ");

            String artist_id = scanner.nextLine().trim();
            int artistid;

            if (artist_id.equalsIgnoreCase("exit")){
                break;
            }

            try {
                artistid = Integer.parseInt(artist_id);
            } catch (NumberFormatException ignored) {
                System.out.println("Wrong artist_id, defaulting to artist_id = 1");
                artistid = 1;
            }

            String queryView = "SELECT * FROM music.artists WHERE artist_id=%d".formatted(artistid);

            try(
                    Connection connection = datasource.getConnection();
                    Statement statement = connection.createStatement()
            ) {
                System.out.println("=".repeat(70));
                System.out.println(artist_id);
                System.out.println("=".repeat(70));
                ResultSet query_two = statement.executeQuery(queryView);
                var meta = query_two.getMetaData();

                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-30s", meta.getColumnName(i).toUpperCase());
                }
                System.out.println();
                while (query_two.next()) {
                    for (int i = 1; i <= meta.getColumnCount(); i++) {
                        System.out.printf("%-30s", query_two.getString(i));
                    }
                    System.out.println();
                }
                System.out.println();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }
}
