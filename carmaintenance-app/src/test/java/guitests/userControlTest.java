package guitests;

import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenance.DALUser;
import com.ebcak.carmaintenance.databaseConnection;
import com.ebcak.carmaintenancegui.userControl;

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

public class userControlTest {

    private Connection connection;
    private static final String TEST_DB_URL = "jdbc:sqlite:./SQLite/test_carMaintenanceDatabase.db";
    private userControl control;

    @Before
    public void setUp() {
        connection = databaseConnection.getInstance(TEST_DB_URL).getConnection();
        control = new userControl();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegisterUser() {
        int result = control.registerUser("testuser", "password123", "testuser@example.com");
        assertEquals("User registration should be successful", 0, result);

        // Try to register the same user again, which should fail
        result = control.registerUser("testuser", "password123", "testuser@example.com");
        assertEquals("Duplicate user registration should fail", -1, result);
    }

    @Test
    public void testLoginUser() {
        control.registerUser("testlogin", "password123", "testlogin@example.com");

        boolean loginResult = control.loginUser("testlogin", "password123");
        assertTrue("User login should be successful", loginResult);

        boolean wrongLoginResult = control.loginUser("wronguser", "password123");
        assertFalse("Login with wrong username should fail", wrongLoginResult);

        boolean wrongPasswordResult = control.loginUser("testlogin", "wrongpassword");
        assertFalse("Login with wrong password should fail", wrongPasswordResult);
    }

    @Test
    public void testAddServiceRecord() {
        control.registerUser("testuser2", "password123", "testuser2@example.com");

        boolean result = control.addServiceRecord("Toyota", "Oil Change", "testuser2", "123456789", 50000);
        assertTrue("Service record addition should be successful", result);

        ServiceRecord serviceRecord = control.getServiceRecordByDriverName("testuser2");
        assertNotNull("Service record should be retrieved successfully", serviceRecord);
        assertEquals("Service record car brand should match", "Toyota", serviceRecord.getCarBrand());
    }

    @Test
    public void testUpdateServiceRecord() {
        control.registerUser("testuser3", "password123", "testuser3@example.com");
        control.addServiceRecord("Honda", "Tire Change", "testuser3", "987654321", 60000);

        ServiceRecord serviceRecord = control.getServiceRecordByDriverName("testuser3");
        assertNotNull("Service record should be retrieved successfully", serviceRecord);

        boolean result = control.updateServiceRecord(serviceRecord.getRecord_id(), "Honda Updated", "Tire Change", "testuser3", "987654321", 60000);
        assertTrue("Service record update should be successful", result);

        ServiceRecord updatedRecord = control.getServiceRecordByDriverName("testuser3");
        assertEquals("Service record car brand should be updated", "Honda Updated", updatedRecord.getCarBrand());
    }

    @Test
    public void testDeleteServiceRecordByDriverName() {
        control.registerUser("testuser4", "password123", "testuser4@example.com");
        control.addServiceRecord("BMW", "Engine Check", "testuser4", "111222333", 70000);

        boolean deleteResult = control.deleteServiceRecordByDriverName("testuser4");
        assertTrue("Service record deletion should be successful", deleteResult);

        ServiceRecord deletedRecord = control.getServiceRecordByDriverName("testuser4");
        assertNull("Service record should be deleted", deletedRecord);
    }

    @Test
    public void testAddReminder() {
        control.registerUser("testuser5", "password123", "testuser5@example.com");
        control.addServiceRecord("Audi", "Brake Check", "testuser5", "222333444", 80000);
        ServiceRecord serviceRecord = control.getServiceRecordByDriverName("testuser5");

        Reminder reminder = new Reminder(0, new Date(System.currentTimeMillis()), "Brake Check Reminder", serviceRecord.getRecord_id(), serviceRecord);
        boolean result = control.addReminder(reminder);
        assertTrue("Reminder addition should be successful", result);

        List<Reminder> reminders = logicJava.getRemindersByServiceRecordId(serviceRecord.getRecord_id());
        assertNotNull("Reminders should be retrieved successfully", reminders);
        assertFalse("Reminders list should not be empty", reminders.isEmpty());
        assertEquals("Reminder type should match", "Brake Check Reminder", reminders.get(0).getReminderType());
    }

    @Test
    public void testGetExpenseReportsByDriverName() {
        control.registerUser("testuser6", "password123", "testuser6@example.com");
        control.addServiceRecord("Mercedes", "Transmission Fix", "testuser6", "555666777", 90000);
        logicJava.addExpenseReport("testuser6", 5.0, 2000, 2500);

        List<ExpenseReport> expenseReports = control.getExpenseReportsByDriverName("testuser6");
        assertNotNull("Expense reports should be retrieved successfully", expenseReports);
        assertFalse("Expense reports list should not be empty", expenseReports.isEmpty());
        assertEquals("Expense report daily fuel should match", 5.0, expenseReports.get(0).getDailyFuel(), 0.01);
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
