import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String USE_SCHEMA = "USE storefront";

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASS"));

        try(
                Connection connection = dataSource.getConnection()
                ) {
            if (!checkSchema(connection)) {
                System.out.println("Storefront schema doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkSchema(Connection conn) {
        try(Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
