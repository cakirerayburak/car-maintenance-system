package com.ebcak.carmaintenancelogictest;

import com.ebcak.carmaintenance.DALUser;
import com.ebcak.carmaintenance.databaseConnection;
import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenancelogiclayer.logicJava.UserLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ServiceRecordLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ReminderLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ExpenseReportLogic;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class logicJavaTest {

    private Connection connection;
    private static final String TEST_DB_URL = "jdbc:sqlite:./SQLite/test_carMaintenanceDatabase.db";
    private UserLogic userLogic;
    private ServiceRecordLogic serviceRecordLogic;
    private ReminderLogic reminderLogic;
    private ExpenseReportLogic expenseReportLogic;

    @Before
    public void setUp() {
        connection = databaseConnection.getInstance(TEST_DB_URL).getConnection();
        userLogic = new UserLogic();
        serviceRecordLogic = new ServiceRecordLogic();
        reminderLogic = new ReminderLogic();
        expenseReportLogic = new ExpenseReportLogic();

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

            String createExpenseReportTableSQL = "CREATE TABLE IF NOT EXISTS expense_report (" +
                                                 "report_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                 "report_date DATE NOT NULL, " +
                                                 "daily_fuel DOUBLE NOT NULL, " +
                                                 "annual_fuel DOUBLE NOT NULL, " +
                                                 "total_cost DOUBLE NOT NULL, " +
                                                 "service_record_id INTEGER NOT NULL, " +
                                                 "FOREIGN KEY(service_record_id) REFERENCES service_record(record_id))";
            stmt.execute(createExpenseReportTableSQL);

            // Clear any existing data to prevent UNIQUE constraint violations
            stmt.execute("DELETE FROM user");
            stmt.execute("DELETE FROM service_record");
            stmt.execute("DELETE FROM reminder");
            stmt.execute("DELETE FROM expense_report");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegister() {
        User user = new User(0, "testuser", "password123", "testuser@example.com");
        int result = userLogic.add(user);
        assertEquals("User registration should be successful", 0, result);

        // Try to register the same user again, which should fail
        result = userLogic.add(user);
        assertEquals("Duplicate user registration should fail", -1, result);
    }

    @Test
    public void testLogin() {
        User user = new User(0, "testlogin", "password123", "testlogin@example.com");
        userLogic.add(user);

        boolean loginResult = userLogic.login("testlogin", "password123");
        assertTrue("User login should be successful", loginResult);

        boolean wrongLoginResult = userLogic.login("wronguser", "password123");
        assertFalse("Login with wrong username should fail", wrongLoginResult);

        boolean wrongPasswordResult = userLogic.login("testlogin", "wrongpassword");
        assertFalse("Login with wrong password should fail", wrongPasswordResult);
    }

    @Test
    public void testAddAndRetrieveServiceRecord() {
        User user = new User(0, "testuser2", "password123", "testuser2@example.com");
        userLogic.add(user);
        int userId = DALUser.getUserIdByUsername(user.getUsername());
        user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id(), user);
        int addServiceResult = serviceRecordLogic.add(serviceRecord);
        assertEquals("Service record addition should be successful", 0, addServiceResult);

        ServiceRecord retrievedRecord = serviceRecordLogic.getServiceRecordByDriverName("John Doe");
        assertNotNull("Service record should be retrieved successfully", retrievedRecord);
        assertEquals("Service record driver name should match", "John Doe", retrievedRecord.getDriverName());
    }

    @Test
    public void testUpdateServiceRecord() {
        User user = new User(0, "testuser3", "password123", "testuser3@example.com");
        userLogic.add(user);
        int userId = DALUser.getUserIdByUsername(user.getUsername());
        user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id(), user);
        serviceRecordLogic.add(serviceRecord);

        ServiceRecord retrievedRecord = serviceRecordLogic.getServiceRecordByDriverName("Jane Doe");
        retrievedRecord.setCarBrand("Honda Updated");
        boolean updateResult = serviceRecordLogic.update(retrievedRecord);
        assertTrue("Service record update should be successful", updateResult);

        ServiceRecord updatedRecord = serviceRecordLogic.getServiceRecordByDriverName("Jane Doe");
        assertEquals("Service record car brand should be updated", "Honda Updated", updatedRecord.getCarBrand());
    }

    @Test
    public void testDeleteServiceRecordByDriverName() {
        User user = new User(0, "testuser4", "password123", "testuser4@example.com");
        userLogic.add(user);
        int userId = DALUser.getUserIdByUsername(user.getUsername());
        user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "BMW", "Engine Check", "Alice Smith", "111222333", 70000, user.getUser_id(), user);
        serviceRecordLogic.add(serviceRecord);

        boolean deleteResult = serviceRecordLogic.remove("Alice Smith");
        assertTrue("Service record deletion should be successful", deleteResult);

        ServiceRecord deletedRecord = serviceRecordLogic.getServiceRecordByDriverName("Alice Smith");
        assertNull("Service record should be deleted", deletedRecord);
    }

    @Test
    public void testAddAndRetrieveReminder() {
        User user = new User(0, "testuser5", "password123", "testuser5@example.com");
        userLogic.add(user);
        int userId = DALUser.getUserIdByUsername(user.getUsername());
        user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "Audi", "Brake Check", "Tom Jones", "222333444", 80000, user.getUser_id(), user);
        serviceRecordLogic.add(serviceRecord);
        serviceRecord = serviceRecordLogic.getServiceRecordByDriverName("Tom Jones");

        Reminder reminder = new Reminder(0, new Date(System.currentTimeMillis()), "Brake Check Reminder", serviceRecord.getRecord_id(), serviceRecord);
        int addReminderResult = reminderLogic.add(reminder);
        assertEquals("Reminder addition should be successful", 0, addReminderResult);

        List<Reminder> reminders = reminderLogic.getRemindersByServiceRecordId(serviceRecord.getRecord_id());
        assertNotNull("Reminders should be retrieved successfully", reminders);
        assertFalse("Reminders list should not be empty", reminders.isEmpty());
        assertEquals("Reminder type should match", "Brake Check Reminder", reminders.get(0).getReminderType());
    }

    @Test
    public void testAddAndRetrieveExpenseReport() {
        User user = new User(0, "testuser6", "password123", "testuser6@example.com");
        userLogic.add(user);
        int userId = DALUser.getUserIdByUsername(user.getUsername());
        user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "Mercedes", "Transmission Fix", "Jack Black", "555666777", 90000, user.getUser_id(), user);
        serviceRecordLogic.add(serviceRecord);
        serviceRecord = serviceRecordLogic.getServiceRecordByDriverName("Jack Black");

        boolean addExpenseReportResult = expenseReportLogic.addExpenseReport("Jack Black", 5.0, 2000, 2500);
        assertTrue("Expense report addition should be successful", addExpenseReportResult);

        List<ExpenseReport> expenseReports = expenseReportLogic.getExpenseReportsByDriverName("Jack Black");
        assertNotNull("Expense reports should be retrieved successfully", expenseReports);
        assertFalse("Expense reports list should not be empty", expenseReports.isEmpty());
        assertEquals("Expense report daily fuel should match", 5.0, expenseReports.get(0).getDailyFuel(), 0.01);
    }

    @After
    public void tearDown() {
        if (connection != null) {
            try (Statement stmt = connection.createStatement()) {
                // Clear tables to avoid UNIQUE constraint errors in future tests
                stmt.execute("DELETE FROM user");
                stmt.execute("DELETE FROM service_record");
                stmt.execute("DELETE FROM reminder");
                stmt.execute("DELETE FROM expense_report");

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
