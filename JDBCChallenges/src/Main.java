import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String USE_SCHEMA = "USE storefront";
    private static int MYSQL_DB_NOT_FOUND = 1049;

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASS"));

        try (Connection connection = dataSource.getConnection()) {
//            connection.setAutoCommit(false);
            DatabaseMetaData meta = connection.getMetaData();
            System.out.println(meta.getSQLStateType());
            if (!checkSchema(connection)) {
                System.out.println("Storefront schema doesn't exist");
                setUpSchema(connection);
            }
//            insertOrder(connection);
//            insertOrderDetails(connection);
            deleteOrderAndDetails(connection, 1);
            deleteOrderAndDetails(connection, 2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkSchema(Connection conn) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            if (conn.getMetaData().getDatabaseProductName().equals("MySQL") && e.getErrorCode() == MYSQL_DB_NOT_FOUND) {
                return false;   // Schema doesn't exist
            } else throw e;
        }
        return true;
    }

    private static void setUpSchema(Connection conn) throws SQLException {

        String createSchema = "CREATE SCHEMA storefront";

        String createOrder = """
                CREATE TABLE storefront.order (
                    order_id INT NOT NULL AUTO_INCREMENT,
                    order_date DATETIME NOT NULL,
                    PRIMARY KEY (order_id)
                )
                """;

        String createOrderDetails = """
                CREATE TABLE storefront.order_details (
                    order_detail_id INT NOT NULL AUTO_INCREMENT,
                    item_description TEXT,
                    order_id INT DEFAULT NULL,
                    PRIMARY KEY (order_detail_id),
                    KEY FK_ORDERID (order_id),
                    CONSTRAINT FK_ORDERID FOREIGN KEY (order_id) REFERENCES storefront.order (order_id) ON DELETE CASCADE
                )
                """;

        try (Statement statement = conn.createStatement()) {
            System.out.println("Creating storefront database");
            statement.execute(createSchema);
            if (checkSchema(conn)) {
                statement.execute(createOrder);
                System.out.println("Successfully created order table");
                statement.execute(createOrderDetails);
                System.out.println("Successfully created order_details table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertOrder(Connection conn) {
        String queryInsert = """
                INSERT INTO storefront.order (order_date)
                VALUES(CURRENT_TIMESTAMP)
                """;
        try(Statement statement = conn.createStatement()) {
            statement.addBatch(queryInsert);
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertOrderDetails(Connection conn) {
        String orderDetail_1 = """
                INSERT INTO storefront.order_details (item_description, order_id)
                VALUES("My first order", 1)
                """;
        String orderDetail_2 = """
                INSERT INTO storefront.order_details (item_description, order_id)
                VALUES("My second order", 2)
                """;
        try(Statement statement = conn.createStatement()) {
            statement.addBatch(orderDetail_1);
            statement.addBatch(orderDetail_2);
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteOrderAndDetails(Connection conn, int orderId) {
        String orderDetail_1 = """
                DELETE FROM storefront.order WHERE order_id = '%d'
                """.formatted(orderId);
        String orderDetail_2 = """
                DELETE FROM storefront.order WHERE order_id = '%d'
                """.formatted(orderId);
        try(Statement statement = conn.createStatement()) {
            statement.addBatch(orderDetail_1);
            statement.addBatch(orderDetail_2);
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
