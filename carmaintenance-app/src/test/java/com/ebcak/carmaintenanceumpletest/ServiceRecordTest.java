package com.ebcak.carmaintenanceumpletest;

import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.FuelEfficiencyReport;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * @class ServiceRecordTest
 * @brief Test class for testing ServiceRecord functionalities.
 */
public class ServiceRecordTest {

    private User user;
    private ServiceRecord serviceRecord;

    /**
     * @brief Setup method to initialize objects before each test.
     */
    @Before
    public void setUp() {
        user = new User(1, "testuser", "password123", "testuser@example.com");
        serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id(), user);
    }

    /**
     * @brief Test method to verify the creation of a ServiceRecord object.
     */
    @Test
    public void testCreateServiceRecord() {
        assertNotNull("ServiceRecord should be created successfully", serviceRecord);
        assertEquals("Record ID should match", 1, serviceRecord.getRecord_id());
        assertEquals("Car Brand should match", "Toyota", serviceRecord.getCarBrand());
        assertEquals("What To Do should match", "Oil Change", serviceRecord.getWhatToDo());
        assertEquals("Driver Name should match", "John Doe", serviceRecord.getDriverName());
        assertEquals("Driver Phone should match", "123456789", serviceRecord.getDriverPhone());
        assertEquals("Kilometer should match", 50000, serviceRecord.getKilometer());
        assertEquals("User ID should match", user.getUser_id(), serviceRecord.getUser_id());
        assertEquals("User should match", user, serviceRecord.getUser());
    }

    /**
     * @brief Test method to verify the creation of a ServiceRecord without a User.
     * @throws RuntimeException when a null User is provided.
     */
    @Test(expected = RuntimeException.class)
    public void testCreateServiceRecordWithoutUser() {
        new ServiceRecord(2, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, 2, null);
    }

    /**
     * @brief Test method to verify getters and setters of ServiceRecord.
     */
    @Test
    public void testSettersAndGetters() {
        serviceRecord.setRecord_id(2);
        serviceRecord.setCarBrand("Honda");
        serviceRecord.setWhatToDo("Brake Check");
        serviceRecord.setDriverName("Jane Smith");
        serviceRecord.setDriverPhone("987654321");
        serviceRecord.setKilometer(75000);
        serviceRecord.setUser_id(2);

        assertEquals("Record ID should match", 2, serviceRecord.getRecord_id());
        assertEquals("Car Brand should match", "Honda", serviceRecord.getCarBrand());
        assertEquals("What To Do should match", "Brake Check", serviceRecord.getWhatToDo());
        assertEquals("Driver Name should match", "Jane Smith", serviceRecord.getDriverName());
        assertEquals("Driver Phone should match", "987654321", serviceRecord.getDriverPhone());
        assertEquals("Kilometer should match", 75000, serviceRecord.getKilometer());
        assertEquals("User ID should match", 2, serviceRecord.getUser_id());
    }

    /**
     * @brief Test method to verify the association between ServiceRecord and Reminder.
     */
    @Test
    public void testReminderAssociation() {
        Reminder reminder = new Reminder(1, new Date(System.currentTimeMillis()), "Check Engine", serviceRecord.getRecord_id(), serviceRecord);
        serviceRecord.addReminder(reminder);

        assertTrue("ServiceRecord should contain Reminder", serviceRecord.getReminders().contains(reminder));
        assertEquals("Reminder's ServiceRecord should match", serviceRecord, reminder.getServiceRecord());
    }

    /**
     * @brief Test method to verify the association between ServiceRecord and FuelEfficiencyReport.
     */
    @Test
    public void testFuelEfficiencyReportAssociation() {
        FuelEfficiencyReport report = new FuelEfficiencyReport(1, new Date(System.currentTimeMillis()), 25.5, 9.4, serviceRecord.getRecord_id(), serviceRecord);
        serviceRecord.addFuelEfficiencyReport(report);

        assertTrue("ServiceRecord should contain FuelEfficiencyReport", serviceRecord.getFuelEfficiencyReports().contains(report));
        assertEquals("FuelEfficiencyReport's ServiceRecord should match", serviceRecord, report.getServiceRecord());
    }

    /**
     * @brief Test method to verify the association between ServiceRecord and ExpenseReport.
     */
    @Test
    public void testExpenseReportAssociation() {
        ExpenseReport report = new ExpenseReport(1, new Date(System.currentTimeMillis()), 5.5, 2000, 2500, serviceRecord.getRecord_id(), serviceRecord);
        serviceRecord.addExpenseReport(report);

        assertTrue("ServiceRecord should contain ExpenseReport", serviceRecord.getExpenseReports().contains(report));
        assertEquals("ExpenseReport's ServiceRecord should match", serviceRecord, report.getServiceRecord());
    }

    /**
     * @brief Test method to verify the deletion of a ServiceRecord.
     */
    @Test
    public void testDeleteServiceRecord() {
        serviceRecord.delete();
        assertNull("User should be null after delete", serviceRecord.getUser());
        assertTrue("Reminders should be empty after delete", serviceRecord.getReminders().isEmpty());
        assertTrue("FuelEfficiencyReports should be empty after delete", serviceRecord.getFuelEfficiencyReports().isEmpty());
        assertTrue("ExpenseReports should be empty after delete", serviceRecord.getExpenseReports().isEmpty());
    }

    /**
     * @brief Test method to verify the toString method of ServiceRecord.
     */
    @Test
    public void testToString() {
        String expected = "com.ebcak.carmaintenanceumple.ServiceRecord@"
                + Integer.toHexString(System.identityHashCode(serviceRecord))
                + "[record_id:1,carBrand:Toyota,whatToDo:Oil Change,driverName:John Doe,driverPhone:123456789,kilometer:50000,user_id:1]"
                + System.lineSeparator() + "  user = " + Integer.toHexString(System.identityHashCode(user));
        assertEquals("toString should match", expected, serviceRecord.toString());
    }
}
