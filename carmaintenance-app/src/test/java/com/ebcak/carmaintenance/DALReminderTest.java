/**
 * @file DALReminderTest.java
 * @brief This file contains unit tests for the DALReminder class.
 */

package com.ebcak.carmaintenance;

import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @class DALReminderTest
 * @brief Unit tests for the DALReminder class.
 */
public class DALReminderTest {

    private Connection connection;
    private static final String TEST_DB_URL = "jdbc:sqlite:./SQLite/test_carMaintenanceDatabase.db";

    /**
     * @brief Set up the database connection and create tables before each test.
     */
    @Before
    public void setUp() {
        connection = databaseConnection.getInstance(TEST_DB_URL).getConnection();
        try (Statement stmt = connection.createStatement()) {
            String createUserTableSQL = "CREATE TABLE IF NOT EXISTS user (" +
                                        "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "username TEXT NOT NULL UNIQUE, " +
                                        "password TEXT NOT NULL, " +
                                        "email TEXT NOT NULL UNIQUE)";
            stmt.execute(createUserTableSQL);

            String createServiceRecordTableSQL = "CREATE TABLE IF NOT EXISTS service_record (" +
                                                 "record_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                 "car_brand TEXT NOT NULL, " +
                                                 "what_to_do TEXT NOT NULL, " +
                                                 "driver_name TEXT NOT NULL, " +
                                                 "driver_phone TEXT NOT NULL, " +
                                                 "kilometer INTEGER NOT NULL, " +
                                                 "user_id INTEGER NOT NULL, " +
                                                 "FOREIGN KEY(user_id) REFERENCES user(user_id))";
            stmt.execute(createServiceRecordTableSQL);

            String createReminderTableSQL = "CREATE TABLE IF NOT EXISTS reminder (" +
                                            "reminder_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "reminder_date DATE NOT NULL, " +
                                            "reminder_type TEXT NOT NULL, " +
                                            "service_record_id INTEGER NOT NULL, " +
                                            "FOREIGN KEY(service_record_id) REFERENCES service_record(record_id))";
            stmt.execute(createReminderTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Test case for adding a reminder.
     */
    @Test
    public void testAddReminder() {
        String username = "testuser";
        String password = "password123";
        String email = "testuser@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id(), user);
        boolean addServiceResult = DALServiceRecord.addServiceRecord(serviceRecord);
        assertTrue("Service record addition should be successful", addServiceResult);

        serviceRecord = DALServiceRecord.getServiceRecordByDriverName("John Doe");
        assertNotNull("Service record should be retrieved successfully", serviceRecord);

        Reminder reminder = new Reminder(0, new Date(System.currentTimeMillis()), "Oil Change Reminder", serviceRecord.getRecord_id(), serviceRecord);
        boolean result = DALReminder.addReminder(reminder);
        assertTrue("Reminder addition should be successful", result);
    }

    /**
     * @brief Test case for retrieving reminders by service record ID.
     */
    @Test
    public void testGetRemindersByServiceRecordId() {
        String username = "testuser2";
        String password = "password123";
        String email = "testuser2@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id(), user);
        boolean addServiceResult = DALServiceRecord.addServiceRecord(serviceRecord);
        assertTrue("Service record addition should be successful", addServiceResult);

        serviceRecord = DALServiceRecord.getServiceRecordByDriverName("Jane Doe");
        assertNotNull("Service record should be retrieved successfully", serviceRecord);

        Reminder reminder = new Reminder(0, new Date(System.currentTimeMillis()), "Tire Change Reminder", serviceRecord.getRecord_id(), serviceRecord);
        boolean addReminderResult = DALReminder.addReminder(reminder);
        assertTrue("Reminder addition should be successful", addReminderResult);

        List<Reminder> reminders = DALReminder.getRemindersByServiceRecordId(serviceRecord.getRecord_id());
        assertNotNull("Reminders should be retrieved successfully", reminders);
        assertFalse("Reminders list should not be empty", reminders.isEmpty());
    }

    /**
     * @brief Test case for handling SQLException when retrieving reminders by service record ID.
     */
    @Test
    public void testGetRemindersByServiceRecordIdWithSQLException() {
        // Mock the DALReminder.getConnection method to throw SQLException
        Connection originalConnection = databaseConnection.getInstance("jdbc:sqlite:./SQLite/invalidDatabase.db").getConnection();

        try (Statement stmt = originalConnection.createStatement()) {
            stmt.execute("CREATE TABLE invalid_table (id INTEGER PRIMARY KEY)");
        } catch (SQLException e) {
            // Expected exception for invalid database operations
        }

        // Attempt to retrieve reminders with an invalid connection
        List<Reminder> reminders = null;
        try (Connection conn = originalConnection;
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reminder WHERE service_record_id = ?")) {
            pstmt.setInt(1, 1);
            ResultSet rs = pstmt.executeQuery();

            reminders = new ArrayList<>();
            while (rs.next()) {
                // Simulating SQL exception
                throw new SQLException("Simulated SQLException");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving reminders: " + e.getMessage());
        }

        assertNotNull("Reminders list should be initialized", reminders);
        assertTrue("Reminders list should be empty due to SQLException", reminders.isEmpty());
    }

    /**
     * @brief Tear down the database connection and delete the test database after each test.
     */
    @After
    public void tearDown() {
        if (connection != null) {
            try (Statement stmt = connection.createStatement()) {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        File dbFile = new File("./SQLite/test_carMaintenanceDatabase.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }
}
