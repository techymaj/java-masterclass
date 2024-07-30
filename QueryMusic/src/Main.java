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
        try {
            datasource.setMaxRows(10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM music.artists";
//        String query = """
//            WITH RankedRows AS (
//                                SELECT *,
//                                ROW_NUMBER() OVER (ORDER BY artist_id) AS row_num
//                                FROM music.artists
//                            )
//                            SELECT *
//                                FROM RankedRows
//                            WHERE row_num <= 10""";
        try (
                Connection connection = datasource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            System.out.println("=".repeat(70));
            ResultSet query_two = statement.executeQuery(query);
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
