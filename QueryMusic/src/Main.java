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

        String albumName = "Tapestry";
        String query = "SELECT * FROM music.artists";
        String queryView = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);

        try(
                Connection connection = datasource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.printf("%d %s %n",
                        resultSet.getInt(1),
                        resultSet.getString("artist_name")
                );
            }

            System.out.println("=".repeat(70));
            System.out.println(albumName);
            System.out.println("=".repeat(70));
            ResultSet resultSetView = statement.executeQuery(queryView);
            var resultMetaData = resultSetView.getMetaData();
            for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                System.out.printf("%d %s %s%n",
                        i,
                        resultMetaData.getColumnName(i),
                        resultMetaData.getColumnTypeName(i)
                );
            }
            System.out.println("-".repeat(70));
            for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                System.out.printf("%-15s", resultMetaData.getColumnName(i).toUpperCase());
            }
            System.out.println();
            while (resultSetView.next()) {
                for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSetView.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
