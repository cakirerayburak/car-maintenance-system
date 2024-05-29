package com.ebcak.carmaintenance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;

public class DALServiceRecord {

    private static Connection getConnection() {
        return databaseConnection.getInstance("jdbc:sqlite:./SQLite/carMaintenanceDatabase.db").getConnection();
    }

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
}
