package com.ebcak.carmaintenance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ebcak.carmaintenanceumple.User;

public class DALUser {

    private static Connection getConnection() {
        return databaseConnection.getInstance("jdbc:sqlite:./SQLite/carMaintenanceDatabase.db").getConnection();
    }

    public static boolean registerUser(String username, String password, String email) {
        String sql = "INSERT INTO user(username, password, email) VALUES(?, ?, ?)";
        Connection conn = getConnection();
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
            return false;
        }
    }

    public static boolean loginUser(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        Connection conn = getConnection();
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next(); // If a matching record is found, return true
        } catch (SQLException e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            return false;
        }
    }
    
    public static int getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM user WHERE username = ?";
        Connection conn = getConnection();
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the user ID: " + e.getMessage());
        }
        return -1; // Return -1 if the user is not found
    }

    public static User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        Connection conn = getConnection();
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                return new User(userId, username, password, email);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the user: " + e.getMessage());
        }
        return null; // Return null if the user is not found
    }
}
