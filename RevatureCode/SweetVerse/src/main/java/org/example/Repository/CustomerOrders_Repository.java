package org.example.Repository;

import org.example.CustomerOrders;
import org.example.Repository.IRepository;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CustomerOrders_Repository extends PostgreSQLRepository implements IRepository<CustomerOrders, Integer> {

    public CustomerOrders_Repository() {
        super();  // calls PostgreSQLRepository constructor to setup connection
    }

    @Override
    public void create(CustomerOrders entity) {
        String sql = "INSERT INTO sv.CustomerOrders (orderId, customerId, orderDate, totalCost, status) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity.getOrderId());
            stmt.setInt(2, entity.getCustomerId());
            stmt.setTimestamp(3, new java.sql.Timestamp(entity.getOrderDate().getTime()));
            stmt.setDouble(4, entity.getTotalCost());
            stmt.setString(5, entity.getStatus());
            stmt.executeUpdate();
            System.out.println("CustomerOrder created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomerOrders get(Integer entity) {
        String sql = "SELECT orderId, customerId, orderDate, totalCost, status FROM sv.CustomerOrders WHERE orderId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("orderId");
                    int customerId = rs.getInt("customerId");
                    java.util.Date orderDate = new java.util.Date(rs.getTimestamp("orderDate").getTime());
                    double totalCost = rs.getDouble("totalCost");
                    String status = rs.getString("status");

                    // Create and return the CustomerOrders object including name
                    return new CustomerOrders(id, customerId, orderDate, totalCost, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if no record found or on error
        return null;
    }


    @Override
    public void update(CustomerOrders entity) {
        String sql = "UPDATE sv.CustomerOrders SET customerId = ?, orderDate = ?, totalCost = ?, status = ? WHERE orderId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity.getCustomerId());
            stmt.setTimestamp(2, new java.sql.Timestamp(entity.getOrderDate().getTime()));
            stmt.setDouble(3, entity.getTotalCost());
            stmt.setString(4, entity.getStatus());
            stmt.setInt(5, entity.getOrderId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("CustomerOrder updated successfully!");
            } else {
                System.out.println("No CustomerOrder found with orderId: " + entity.getOrderId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer integer) {
        String sql = "DELETE FROM sv.CustomerOrders WHERE orderId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, integer);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("CustomerOrder deleted successfully!");
            } else {
                System.out.println("No CustomerOrder found with orderId: " + integer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CustomerOrders> findAll() {
        List<CustomerOrders> orders = new ArrayList<>();
        String sql = "SELECT orderId, customerId, orderDate, totalCost, status FROM sv.CustomerOrders";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("orderId");
                int customerId = rs.getInt("customerId");
                java.util.Date orderDate = new java.util.Date(rs.getTimestamp("orderDate").getTime());
                double totalCost = rs.getDouble("totalCost");
                String status = rs.getString("status");

                CustomerOrders order = new CustomerOrders(id, customerId, orderDate, totalCost, status);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void saveAll(List<CustomerOrders> entities) {
        // This could be implemented as batch insert/update, but here is a simple version:
        for (CustomerOrders entity : entities) {
            if (get(entity.getOrderId()) == null) {
                create(entity);
            } else {
                update(entity);
            }
        }
    }
}
