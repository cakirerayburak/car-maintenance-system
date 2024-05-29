package com.ebcak.carmaintenance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;

public class DALServiceRecordTest {

    private Connection connection;
    private static final String TEST_DB_URL = "jdbc:sqlite:./SQLite/test_carMaintenanceDatabase.db";

    @Before
    public void setUp() {
        connection = databaseConnection.getInstance(TEST_DB_URL).getConnection();
        try (Statement stmt = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS user (" +
                                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "username TEXT NOT NULL UNIQUE, " +
                                    "password TEXT NOT NULL, " +
                                    "email TEXT NOT NULL UNIQUE)";
            stmt.execute(createTableSQL);

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddServiceRecord() {
        String username = "testuser";
        String password = "password123";
        String email = "testuser@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);
        
        ServiceRecord serviceRecord = new ServiceRecord(0, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id(), user);
        boolean result = DALServiceRecord.addServiceRecord(serviceRecord);
        assertTrue("Service record addition should be successful", result);
    }

    @Test
    public void testUpdateServiceRecord() {
        String username = "testuser2";
        String password = "password123";
        String email = "testuser2@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);
        
        ServiceRecord serviceRecord = new ServiceRecord(0, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id(), user);
        DALServiceRecord.addServiceRecord(serviceRecord);
        
        serviceRecord = DALServiceRecord.getServiceRecordByDriverName("Jane Doe");
        serviceRecord.setWhatToDo("Brake Replacement");
        boolean result = DALServiceRecord.updateServiceRecord(serviceRecord);
        assertTrue("Service record update should be successful", result);
    }

    @Test
    public void testGetServiceRecordByDriverName() {
        String username = "testuser3";
        String password = "password123";
        String email = "testuser3@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "BMW", "Engine Check", "Alice Smith", "111222333", 70000, user.getUser_id(), user);
        DALServiceRecord.addServiceRecord(serviceRecord);

        ServiceRecord retrievedRecord = DALServiceRecord.getServiceRecordByDriverName("Alice Smith");
        assertNotNull("Service record should be retrieved successfully", retrievedRecord);
        assertEquals("Driver name should match", "Alice Smith", retrievedRecord.getDriverName());
    }

    @Test
    public void testSearchServiceRecords() {
        String username = "testuser4";
        String password = "password123";
        String email = "testuser4@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord1 = new ServiceRecord(0, "Ford", "Battery Replacement", "Bob Johnson", "444555666", 80000, user.getUser_id(), user);
        ServiceRecord serviceRecord2 = new ServiceRecord(0, "Audi", "Transmission Repair", "Carol White", "777888999", 90000, user.getUser_id(), user);
        DALServiceRecord.addServiceRecord(serviceRecord1);
        DALServiceRecord.addServiceRecord(serviceRecord2);

        List<ServiceRecord> records = DALServiceRecord.searchServiceRecords("Bob");
        assertNotNull("Search results should not be null", records);
        assertFalse("Search results should not be empty", records.isEmpty());
        assertEquals("Should find one record", 1, records.size());
    }

    @Test
    public void testDeleteServiceRecordByDriverName() {
        String username = "testuser5";
        String password = "password123";
        String email = "testuser5@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "Mercedes", "Paint Job", "David Brown", "000111222", 100000, user.getUser_id(), user);
        DALServiceRecord.addServiceRecord(serviceRecord);

        boolean result = DALServiceRecord.deleteServiceRecordByDriverName("David Brown");
        assertTrue("Service record deletion should be successful", result);

        ServiceRecord deletedRecord = DALServiceRecord.getServiceRecordByDriverName("David Brown");
        assertNull("Deleted service record should not be found", deletedRecord);
    }

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
