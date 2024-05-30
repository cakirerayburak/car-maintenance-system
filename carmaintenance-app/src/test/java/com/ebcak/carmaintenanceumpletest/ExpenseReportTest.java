package com.ebcak.carmaintenanceumpletest;

import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class ExpenseReportTest {

    private User user;
    private ServiceRecord serviceRecord;
    private ExpenseReport expenseReport;

    @Before
    public void setUp() {
        user = new User(1, "testuser", "password123", "testuser@example.com");
        serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id(), user);
        expenseReport = new ExpenseReport(1, new Date(System.currentTimeMillis()), 5.5, 2000, 2500, serviceRecord.getRecord_id(), serviceRecord);
    }

    @Test
    public void testCreateExpenseReport() {
        assertNotNull("ExpenseReport should be created successfully", expenseReport);
        assertEquals("Report ID should match", 1, expenseReport.getReport_id());
        assertEquals("Daily Fuel should match", 5.5, expenseReport.getDailyFuel(), 0.01);
        assertEquals("Annual Fuel should match", 2000, expenseReport.getAnnualFuel(), 0.01);
        assertEquals("Total Cost should match", 2500, expenseReport.getTotalCost(), 0.01);
        assertEquals("Service Record ID should match", serviceRecord.getRecord_id(), expenseReport.getServiceRecord_id());
        assertEquals("Service Record should match", serviceRecord, expenseReport.getServiceRecord());
    }

    @Test(expected = RuntimeException.class)
    public void testCreateExpenseReportWithoutServiceRecord() {
        new ExpenseReport(2, new Date(System.currentTimeMillis()), 6.0, 2100, 2600, 2, null);
    }

    @Test
    public void testSettersAndGetters() {
        expenseReport.setReport_id(2);
        expenseReport.setReportDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
        expenseReport.setDailyFuel(6.0);
        expenseReport.setAnnualFuel(2100);
        expenseReport.setTotalCost(2600);
        expenseReport.setServiceRecord_id(2);

        assertEquals("Report ID should match", 2, expenseReport.getReport_id());
        assertEquals("Daily Fuel should match", 6.0, expenseReport.getDailyFuel(), 0.01);
        assertEquals("Annual Fuel should match", 2100, expenseReport.getAnnualFuel(), 0.01);
        assertEquals("Total Cost should match", 2600, expenseReport.getTotalCost(), 0.01);
        assertEquals("Service Record ID should match", 2, expenseReport.getServiceRecord_id());
    }

    @Test
    public void testServiceRecordAssociation() {
        ServiceRecord newServiceRecord = new ServiceRecord(2, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id(), user);
        expenseReport.setServiceRecord(newServiceRecord);

        assertEquals("Service Record should be updated", newServiceRecord, expenseReport.getServiceRecord());
        assertTrue("Old Service Record should not contain ExpenseReport", serviceRecord.getExpenseReports().isEmpty());
        assertTrue("New Service Record should contain ExpenseReport", newServiceRecord.getExpenseReports().contains(expenseReport));
    }

    @Test
    public void testDeleteExpenseReport() {
        expenseReport.delete();
        assertNull("Service Record should be null after delete", expenseReport.getServiceRecord());
        assertTrue("Service Record should not contain ExpenseReport after delete", serviceRecord.getExpenseReports().isEmpty());
    }

    @Test
    public void testToString() {
        String expected = "ExpenseReport[report_id:1,dailyFuel:5.5,annualFuel:2000.0,totalCost:2500.0,serviceRecord_id:1]\n" +
                          "  reportDate=" + expenseReport.getReportDate().toString() + "\n" +
                          "  serviceRecord = " + Integer.toHexString(System.identityHashCode(serviceRecord));
        assertEquals("toString should match", expected, expenseReport.toString());
    }
}
