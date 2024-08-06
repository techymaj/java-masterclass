import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class C2 {
    public static final String ORDER_DETAILS = """
            INSERT INTO storefront.order_details (quantity, item_description) VALUES (?, ?)
            """;
    public static final String ORDER_DATE = """
            INSERT INTO storefront.order (order_date) VALUES (?)
            """;
    static Map<String, HashMap<Integer, String>> orders = new LinkedHashMap<>();

    public static void main(String[] args) {

        var datasource = new MysqlDataSource();
        datasource.setServerName("localhost");
        datasource.setPort(3306);
        datasource.setDatabaseName("storefront");
        try {
            datasource.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        datasource.setUser(System.getenv("MYSQL_USER"));
        datasource.setPassword(System.getenv("MYSQL_PASS"));

        try (Connection connection = datasource.getConnection()) {
            System.out.println("Connected to storefront DB!");
//            alterTable(connection);
            PreparedStatement orderDetailsPS = connection.prepareStatement(ORDER_DETAILS);
            PreparedStatement datePS = connection.prepareStatement(ORDER_DATE);
            addDataFromCSV(connection, datePS, orderDetailsPS);
            datePS.executeBatch();
            orderDetailsPS.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void alterTable(Connection conn) throws SQLException {
        String sql = """
                ALTER TABLE storefront.order_details ADD quantity INT
                """;
        try {
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }
    }

    private static void addDataFromCSV(Connection connection, PreparedStatement detailsPS, PreparedStatement orderDetailsPS) {
        try {
            List<String> lines = Files.readAllLines(Path.of("Orders.csv"));
            for (String line : lines) {
                if (line.startsWith("order")) {
                    String date = line.split(",")[1].trim();
                    System.out.println("\n" + date);
                    System.out.println("-".repeat(50));
                    try {
                        connection.setAutoCommit(false);
                        addOrderDate(detailsPS, date);
                        connection.commit();
                        connection.setAutoCommit(true);
                    } catch (SQLException e) {
                        connection.rollback();
                        throw new RuntimeException(e);
                    }
                }
                if (line.startsWith("item")) {
                    String itemQty = line.split(",")[1].trim();
                    String itemDesc = line.split(",")[2].trim();
                    var qty = Integer.parseInt(itemQty);
                    System.out.printf("%d %s%n", qty, itemDesc);
                    try {
                        connection.setAutoCommit(false);
                        addOrderDetails(orderDetailsPS, qty, itemDesc);
                        connection.commit();
                        connection.setAutoCommit(true);
                    } catch (SQLException e) {
                        connection.rollback();
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addOrderDetails(PreparedStatement ps, int quantity, String description) {
        try {
            ps.setInt(1, quantity);
            ps.setString(2, description);
            ps.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addOrderDate(PreparedStatement ps, String date) {
        try {
            ps.setString(1, date);
            ps.addBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
