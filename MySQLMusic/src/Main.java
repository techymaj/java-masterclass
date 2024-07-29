import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class Main {

    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/music";

    public static void main(String[] args) {

        String username = JOptionPane.showInputDialog(
                null,
                "Enter DB username"
        );

        JPasswordField passwordField = new JPasswordField();
        int okCancel = JOptionPane.showConfirmDialog(
                null,
                passwordField,
                "Enter your password",
                JOptionPane.OK_CANCEL_OPTION
        );

        final char[] password = (okCancel == JOptionPane.OK_OPTION) ? passwordField.getPassword() : null;

        // using DataSource
        var dataSource = new MysqlDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(String.valueOf(Objects.requireNonNull(password)));
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");

        // using DriverManager
        try (
//                Connection connection = DriverManager.getConnection(
//                        CONN_STRING, username,
//                        String.valueOf(Objects.requireNonNull(password))
//                );
//                Connection connection = dataSource.getConnection(
//                        username,
//                        String.valueOf(Objects.requireNonNull(password))
//                )

                Connection connection = dataSource.getConnection();
        ) {
            System.out.println("Connection to database successful!");
            Arrays.fill(Objects.requireNonNull(password), ' ');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
