package org.example.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgreSQLRepository {
    // Fields
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/sweetverse_db";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_PW = "my_secure_pass";
    private Connection connection;

    // Constructor
    public PostgreSQLRepository() {
        try {
            connection = DriverManager.getConnection(Postgre_URL, Postgre_User, Postgre_PW);
            createSchema();
            createCustomerOrdersTable();
//            createOrderItemsTable();
//            createOrderItemToppingsTable();
//            createStockInventoryTable();
//            createStoreOrdersTable();
            System.out.println("Database setup complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methods
    protected Connection getConnection() {
        return connection;
    }

    private void createSchema() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS sv;");
        }
    }

    private void createCustomerOrdersTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS sv.CustomerOrders (" +
                    "orderId SERIAL PRIMARY KEY," +
                    "customerId INT NOT NULL," +
                    "orderDate TIMESTAMP NOT NULL," +
                    "totalCost FLOAT NOT NULL," +
                    "status VARCHAR(20) NOT NULL" +
                    ");";
            stmt.execute(sql);
        }
    }

}
