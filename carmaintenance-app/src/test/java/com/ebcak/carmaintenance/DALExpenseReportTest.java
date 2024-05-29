package com.ebcak.carmaintenance;

import com.ebcak.carmaintenanceumple.ExpenseReport;
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

public class DALExpenseReportTest {

    private Connection connection;
    private static final String TEST_DB_URL = "jdbc:sqlite:./SQLite/test_carMaintenanceDatabase.db";

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
    public void testAddExpenseReport() {
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

        ExpenseReport expenseReport = new ExpenseReport(0, new Date(System.currentTimeMillis()), 5.5, 2000, 2500, serviceRecord.getRecord_id(), serviceRecord);
        boolean result = DALExpenseReport.addExpenseReport(expenseReport);
        assertTrue("Expense report addition should be successful", result);
    }

    @Test
    public void testGetExpenseReportsByServiceRecordId() {
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

        ExpenseReport expenseReport = new ExpenseReport(0, new Date(System.currentTimeMillis()), 6.0, 2200, 2700, serviceRecord.getRecord_id(), serviceRecord);
        boolean addExpenseResult = DALExpenseReport.addExpenseReport(expenseReport);
        assertTrue("Expense report addition should be successful", addExpenseResult);

        List<ExpenseReport> expenseReports = DALExpenseReport.getExpenseReportsByServiceRecordId(serviceRecord.getRecord_id());
        assertNotNull("Expense reports should be retrieved successfully", expenseReports);
        assertFalse("Expense reports list should not be empty", expenseReports.isEmpty());
    }

    @Test
    public void testGetExpenseReportsByDriverName() {
        String username = "testuser3";
        String password = "password123";
        String email = "testuser3@example.com";

        DALUser.registerUser(username, password, email);
        int userId = DALUser.getUserIdByUsername(username);
        User user = DALUser.getUserById(userId);

        ServiceRecord serviceRecord = new ServiceRecord(0, "BMW", "Engine Check", "Alice Smith", "111222333", 70000, user.getUser_id(), user);
        boolean addServiceResult = DALServiceRecord.addServiceRecord(serviceRecord);
        assertTrue("Service record addition should be successful", addServiceResult);

        serviceRecord = DALServiceRecord.getServiceRecordByDriverName("Alice Smith");
        assertNotNull("Service record should be retrieved successfully", serviceRecord);

        ExpenseReport expenseReport1 = new ExpenseReport(0, new Date(System.currentTimeMillis()), 7.0, 2300, 2900, serviceRecord.getRecord_id(), serviceRecord);
        ExpenseReport expenseReport2 = new ExpenseReport(0, new Date(System.currentTimeMillis()), 7.5, 2400, 3000, serviceRecord.getRecord_id(), serviceRecord);
        boolean addExpenseResult1 = DALExpenseReport.addExpenseReport(expenseReport1);
        boolean addExpenseResult2 = DALExpenseReport.addExpenseReport(expenseReport2);
        assertTrue("Expense report 1 addition should be successful", addExpenseResult1);
        assertTrue("Expense report 2 addition should be successful", addExpenseResult2);

        List<ExpenseReport> expenseReports = DALExpenseReport.getExpenseReportsByDriverName("Alice Smith");
        assertNotNull("Expense reports should be retrieved successfully", expenseReports);
        assertFalse("Expense reports list should not be empty", expenseReports.isEmpty());
        assertEquals("Should find two reports", 2, expenseReports.size());
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
