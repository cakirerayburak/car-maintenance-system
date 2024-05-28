package com.ebcak.carmaintenance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DALUser {

    private static Connection conn = databaseConnection.getInstance().getConnection();

    /**
     * Registers a new user with the provided username, password, and email.
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email address
     * @return true if the registration is successful, false otherwise
     */
    public static boolean registerUser(String username, String password, String email) {
        String sql = "INSERT INTO user(username, password, email) VALUES(?, ?, ?)";

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

    /**
     * Logs in a user with the provided username and password.
     * @param username the user's username
     * @param password the user's password
     * @return true if the login is successful, false otherwise
     */
    public static boolean loginUser(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

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
}
