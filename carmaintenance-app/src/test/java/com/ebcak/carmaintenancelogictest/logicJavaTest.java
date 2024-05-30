package com.ebcak.carmaintenancelogictest;

import com.ebcak.carmaintenance.DALExpenseReport;
import com.ebcak.carmaintenance.DALReminder;
import com.ebcak.carmaintenance.DALServiceRecord;
import com.ebcak.carmaintenance.DALUser;
import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class logicJavaTest {

    private logicJava.UserLogic userLogic;
    private logicJava.ServiceRecordLogic serviceRecordLogic;
    private logicJava.ReminderLogic reminderLogic;
    private logicJava.ExpenseReportLogic expenseReportLogic;

    @Before
    public void setUp() {
        userLogic = new logicJava.UserLogic();
        serviceRecordLogic = new logicJava.ServiceRecordLogic();
        reminderLogic = new logicJava.ReminderLogic();
        expenseReportLogic = new logicJava.ExpenseReportLogic();
    }

    @Test
    public void testUserLogin() {
        String username = "testuser";
        String password = "password";

        mock(DALUser.class);
        when(DALUser.loginUser(username, password)).thenReturn(true);

        boolean loginResult = userLogic.login(username, password);

        assertTrue(loginResult);
    }

    @Test
    public void testAddServiceRecord() {
        User mockUser = mock(User.class); // Mock the User object
        ServiceRecord serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 10000, 1, mockUser);

        mock(DALServiceRecord.class);
        when(DALServiceRecord.addServiceRecord(serviceRecord)).thenReturn(true);

        int addResult = serviceRecordLogic.add(serviceRecord);

        assertEquals(0, addResult);
    }

    @Test
    public void testAddReminder() {
        User mockUser = mock(User.class); // Mock the User object
        ServiceRecord serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 10000, 1, mockUser);
        Reminder reminder = new Reminder(1, new Date(System.currentTimeMillis()), "Oil Change", 1, serviceRecord);

        mock(DALReminder.class);
        when(DALReminder.addReminder(reminder)).thenReturn(true);

        int addResult = reminderLogic.add(reminder);

        assertEquals(0, addResult);
    }

    @Test
    public void testAddExpenseReport() {
        User mockUser = mock(User.class); // Mock the User object
        ServiceRecord serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 10000, 1, mockUser);
        ExpenseReport expenseReport = new ExpenseReport(1, new Date(System.currentTimeMillis()), 50.0, 200.0, 1000.0, 1, serviceRecord);

        mock(DALExpenseReport.class);
        when(DALExpenseReport.addExpenseReport(expenseReport)).thenReturn(true);

        int addResult = expenseReportLogic.add(expenseReport);

        assertEquals(0, addResult);
    }

    @Test
    public void testGetExpenseReportsByDriverName() {
        String driverName = "John Doe";
        User mockUser = mock(User.class); // Mock the User object
        ServiceRecord serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", driverName, "123456789", 10000, 1, mockUser);
        ExpenseReport expenseReport1 = new ExpenseReport(1, new Date(System.currentTimeMillis()), 50.0, 200.0, 1000.0, 1, serviceRecord);
        ExpenseReport expenseReport2 = new ExpenseReport(2, new Date(System.currentTimeMillis()), 60.0, 300.0, 1200.0, 1, serviceRecord);

        mock(DALServiceRecord.class);
        when(DALServiceRecord.getServiceRecordByDriverName(driverName)).thenReturn(serviceRecord);

        mock(DALExpenseReport.class);
        when(DALExpenseReport.getExpenseReportsByServiceRecordId(1)).thenReturn(Arrays.asList(expenseReport1, expenseReport2));

        List<ExpenseReport> expenseReports = expenseReportLogic.getExpenseReportsByDriverName(driverName);

        assertNotNull(expenseReports);
        assertEquals(2, expenseReports.size());
        assertEquals(expenseReport1, expenseReports.get(0));
        assertEquals(expenseReport2, expenseReports.get(1));
    }
}
