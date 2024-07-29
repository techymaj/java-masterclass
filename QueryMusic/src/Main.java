import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
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

        try(Connection connection = datasource.getConnection()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
