/**
 * @file DALServiceRecord.java
 * @brief This file contains data access layer methods for ServiceRecord operations.
 */

package com.ebcak.carmaintenance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;

public class DALServiceRecord {

    /**
     * @brief Retrieves a connection to the database.
     * @return A Connection object to the database.
     */
    private static Connection getConnection() {
        return databaseConnection.getInstance("jdbc:sqlite:./SQLite/carMaintenanceDatabase.db").getConnection();
    }

    /**
     * @brief Adds a new service record to the database.
     * @param serviceRecord The service record to be added.
     * @return true if the service record is successfully added, false otherwise.
     */
    public static boolean addServiceRecord(ServiceRecord serviceRecord) {
        String sql = "INSERT INTO service_record(car_brand, what_to_do, driver_name, driver_phone, kilometer, user_id) VALUES(?, ?, ?, ?, ?, ?)";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serviceRecord.getCarBrand());
            pstmt.setString(2, serviceRecord.getWhatToDo());
            pstmt.setString(3, serviceRecord.getDriverName());
            pstmt.setString(4, serviceRecord.getDriverPhone());
            pstmt.setInt(5, serviceRecord.getKilometer());
            pstmt.setInt(6, serviceRecord.getUser_id());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("An error occurred during adding service record: " + e.getMessage());
            return false;
        }
    }

    /**
     * @brief Updates an existing service record in the database.
     * @param serviceRecord The service record to be updated.
     * @return true if the service record is successfully updated, false otherwise.
     */
    public static boolean updateServiceRecord(ServiceRecord serviceRecord) {
        String sql = "UPDATE service_record SET car_brand = ?, what_to_do = ?, driver_name = ?, driver_phone = ?, kilometer = ?, user_id = ? WHERE record_id = ?";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serviceRecord.getCarBrand());
            pstmt.setString(2, serviceRecord.getWhatToDo());
            pstmt.setString(3, serviceRecord.getDriverName());
            pstmt.setString(4, serviceRecord.getDriverPhone());
            pstmt.setInt(5, serviceRecord.getKilometer());
            pstmt.setInt(6, serviceRecord.getUser_id());
            pstmt.setInt(7, serviceRecord.getRecord_id());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("An error occurred during updating service record: " + e.getMessage());
            return false;
        }
    }

    /**
     * @brief Retrieves a service record by driver name.
     * @param driverName The name of the driver.
     * @return A ServiceRecord object if found, null otherwise.
     */
    public static ServiceRecord getServiceRecordByDriverName(String driverName) {
        String sql = "SELECT * FROM service_record WHERE driver_name = ?";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, driverName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int recordId = rs.getInt("record_id");
                String carBrand = rs.getString("car_brand");
                String whatToDo = rs.getString("what_to_do");
                String driverPhone = rs.getString("driver_phone");
                int kilometer = rs.getInt("kilometer");
                int userId = rs.getInt("user_id");
                User user = DALUser.getUserById(userId);
                return new ServiceRecord(recordId, carBrand, whatToDo, driverName, driverPhone, kilometer, userId, user);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the service record: " + e.getMessage());
        }
        return null;
    }

    /**
     * @brief Searches service records by a given search term.
     * @param searchTerm The search term to be used.
     * @return A list of service records matching the search term.
     */
    public static List<ServiceRecord> searchServiceRecords(String searchTerm) {
        List<ServiceRecord> serviceRecords = new ArrayList<>();
        String sql = "SELECT * FROM service_record WHERE driver_name LIKE ?";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchTerm + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int recordId = rs.getInt("record_id");
                String carBrand = rs.getString("car_brand");
                String whatToDo = rs.getString("what_to_do");
                String driverName = rs.getString("driver_name");
                String driverPhone = rs.getString("driver_phone");
                int kilometer = rs.getInt("kilometer");
                int userId = rs.getInt("user_id");
                User user = DALUser.getUserById(userId);
                ServiceRecord serviceRecord = new ServiceRecord(recordId, carBrand, whatToDo, driverName, driverPhone, kilometer, userId, user);
                serviceRecords.add(serviceRecord);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while searching for service records: " + e.getMessage());
        }
        return serviceRecords;
    }

    /**
     * @brief Deletes a service record by driver name.
     * @param driverName The name of the driver.
     * @return true if the service record is successfully deleted, false otherwise.
     */
    public static boolean deleteServiceRecordByDriverName(String driverName) {
        String sql = "DELETE FROM service_record WHERE driver_name = ?";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, driverName);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the service record: " + e.getMessage());
            return false;
        }
    }

    /**
     * @brief Retrieves a service record by record ID.
     * @param recordId The ID of the service record.
     * @return A ServiceRecord object if found, null otherwise.
     */
    public static ServiceRecord getServiceRecordById(int recordId) {
        String sql = "SELECT * FROM service_record WHERE record_id = ?";
        Connection conn = databaseConnection.getInstance("jdbc:sqlite:./SQLite/carMaintenanceDatabase.db").getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, recordId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("record_id");
                String carBrand = rs.getString("car_brand");
                String whatToDo = rs.getString("what_to_do");
                String driverName = rs.getString("driver_name");
                String driverPhone = rs.getString("driver_phone");
                int kilometer = rs.getInt("kilometer");
                int userId = rs.getInt("user_id");

                // Retrieve associated User
                User user = DALUser.getUserById(userId);

                return new ServiceRecord(id, carBrand, whatToDo, driverName, driverPhone, kilometer, userId, user);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the service record: " + e.getMessage());
        }
        return null;
    }

}
