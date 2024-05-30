/**
 * @file DALReminder.java
 * @brief This file contains data access layer methods for Reminder operations.
 */

package com.ebcak.carmaintenance;

import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DALReminder {

    /**
     * @brief Retrieves a connection to the database.
     * @return A Connection object to the database.
     */
    private static Connection getConnection() {
        return databaseConnection.getInstance("jdbc:sqlite:./SQLite/carMaintenanceDatabase.db").getConnection();
    }

    /**
     * @brief Adds a new reminder to the database.
     * @param reminder The reminder to be added.
     * @return true if the reminder is successfully added, false otherwise.
     */
    public static boolean addReminder(Reminder reminder) {
        String sql = "INSERT INTO reminder(reminder_date, reminder_type, service_record_id) VALUES(?, ?, ?)";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, new Timestamp(reminder.getReminderDate().getTime()));
            pstmt.setString(2, reminder.getReminderType());
            pstmt.setInt(3, reminder.getServiceRecord_id());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("An error occurred during adding reminder: " + e.getMessage());
            return false;
        }
    }

    /**
     * @brief Retrieves a list of reminders associated with a given service record ID.
     * @param serviceRecordId The ID of the service record.
     * @return A list of reminders associated with the given service record ID.
     */
    public static List<Reminder> getRemindersByServiceRecordId(int serviceRecordId) {
        List<Reminder> reminders = new ArrayList<>();
        String sql = "SELECT * FROM reminder WHERE service_record_id = ?";
        Connection conn = getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, serviceRecordId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int reminderId = rs.getInt("reminder_id");
                Timestamp reminderDate = rs.getTimestamp("reminder_date");
                String reminderType = rs.getString("reminder_type");
                int serviceRecord_id = rs.getInt("service_record_id");

                ServiceRecord serviceRecord = DALServiceRecord.getServiceRecordById(serviceRecord_id);
                Reminder reminder = new Reminder(reminderId, new java.sql.Date(reminderDate.getTime()), reminderType, serviceRecord_id, serviceRecord);
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving reminders: " + e.getMessage());
        }

        return reminders;
    }
}
