package org.example.Repository;

import org.example.CustomerOrders;
import org.example.CustomerProfiles;
import org.example.Repository.IRepository;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CustomerProfiles_Repository extends PostgreSQLRepository implements IRepository<CustomerProfiles, Integer> {

    public CustomerProfiles_Repository() {
        super();  // calls PostgreSQLRepository constructor to setup connection
    }

    @Override
    public void create(CustomerProfiles entity) {
        String sql = "INSERT INTO sv.CustomerProfiles (customerId, name, email, createdDate) VALUES (?, ?, ?, ?);";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity.getCustomerId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getEmail());
            stmt.setTimestamp(4, new java.sql.Timestamp(entity.getCreatedDate().getTime()));
            stmt.executeUpdate();
            System.out.println("CustomerProfile created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lookup by email (returns null if not found)
    public CustomerProfiles getByEmail(String email) {
        String sql = "SELECT customerId, name, email, createdDate FROM sv.CustomerProfiles WHERE email = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CustomerProfiles p = new CustomerProfiles(
                            rs.getInt("customerId"),
                            rs.getString("name"),
                            rs.getString("email"),
                            new java.util.Date(rs.getTimestamp("createdDate").getTime())
                    );
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerProfiles get(Integer entity) {
        String sql = "SELECT customerId, name, email, createdDate FROM sv.CustomerOrders WHERE customerId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, entity);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int customerId = rs.getInt("customerId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    java.util.Date createdDate = new java.util.Date(rs.getTimestamp("createdDate").getTime());

                    // Create and return the CustomerProfile object
                    return new CustomerProfiles(customerId, name, email, createdDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if no record found or on error
        return null;
    }

    @Override
    public void update(CustomerProfiles entity) {
        String sql = "UPDATE sv.CustomerProfiles SET name = ?, email = ?, createdDate = ? WHERE customerId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(4, entity.getName());
            stmt.setString(4, entity.getEmail());
            stmt.setTimestamp(2, new java.sql.Timestamp(entity.getCreatedDate().getTime()));
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("CustomerProfile updated successfully!");
            } else {
                System.out.println("No CustomerProfile found with customerId: " + entity.getCustomerId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer integer) {
        String sql = "DELETE FROM sv.CustomerProfiles WHERE customerId = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, integer);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("CustomerProfile deleted successfully!");
            } else {
                System.out.println("No CustomerProfile found with customerId: " + integer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CustomerProfiles> findAll() {
        List<CustomerProfiles> profiles = new ArrayList<>();
        String sql = "SELECT customerId, name, email, createdDate FROM sv.CustomerProfiles";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int customerId = rs.getInt("customerId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                java.util.Date createdDate = new java.util.Date(rs.getTimestamp("createdDate").getTime());

                CustomerProfiles profile = new CustomerProfiles(customerId, name, email, createdDate);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    @Override
    public void saveAll(List<CustomerProfiles> entities) {
        // This could be implemented as batch insert/update, but here is a simple version:
        for (CustomerProfiles entity : entities) {
            if (get(entity.getCustomerId()) == null) {
                create(entity);
            } else {
                update(entity);
            }
        }
    }
}
