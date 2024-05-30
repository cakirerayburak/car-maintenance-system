package guitests;

import com.ebcak.carmaintenancegui.userControl;
import com.ebcak.carmaintenancelogiclayer.logicJava.ExpenseReportLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ReminderLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ServiceRecordLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.UserLogic;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class userControlTest {

    private userControl control;
    private UserLogic mockUserLogic;
    private ServiceRecordLogic mockServiceRecordLogic;
    private ExpenseReportLogic mockExpenseReportLogic;
    private ReminderLogic mockReminderLogic;

    @Before
    public void setUp() {
        mockUserLogic = mock(UserLogic.class);
        mockServiceRecordLogic = mock(ServiceRecordLogic.class);
        mockExpenseReportLogic = mock(ExpenseReportLogic.class);
        mockReminderLogic = mock(ReminderLogic.class);

        control = new userControl();
        control.setUserLogic(mockUserLogic);
        control.setServiceRecordLogic(mockServiceRecordLogic);
        control.setExpenseReportLogic(mockExpenseReportLogic);
        control.setReminderLogic(mockReminderLogic);
    }

    @Test
    public void testSearchServiceRecords() {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com");
        ServiceRecord serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "testuser", "123456789", 50000, 1, mockUser);

        when(mockServiceRecordLogic.searchServiceRecords("test")).thenReturn(List.of(serviceRecord));

        List<ServiceRecord> results = control.searchServiceRecords("test");
        assertEquals(1, results.size());
        assertEquals(serviceRecord, results.get(0));
    }

    @Test
    public void testGetExpenseReportsByDriverName() {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com");
        ServiceRecord mockServiceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "testuser", "123456789", 50000, 1, mockUser);
        ExpenseReport expenseReport = new ExpenseReport(1, new Date(System.currentTimeMillis()), 50.0, 1000.0, 2000.0, 1, mockServiceRecord);

        when(mockExpenseReportLogic.getExpenseReportsByDriverName("testuser")).thenReturn(List.of(expenseReport));

        List<ExpenseReport> results = control.getExpenseReportsByDriverName("testuser");
        assertEquals(1, results.size());
        assertEquals(expenseReport, results.get(0));
    }

    @Test
    public void testGetServiceRecordByDriverName() {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com");
        ServiceRecord serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "testuser", "123456789", 50000, 1, mockUser);

        when(mockServiceRecordLogic.getServiceRecordByDriverName("testuser")).thenReturn(serviceRecord);

        ServiceRecord result = control.getServiceRecordByDriverName("testuser");
        assertEquals(serviceRecord, result);
    }

    @Test
    public void testAddReminder() {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com");
        ServiceRecord mockServiceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "testuser", "123456789", 50000, 1, mockUser);
        Reminder reminder = new Reminder(1, new Date(System.currentTimeMillis()), "Oil Change Reminder", 1, mockServiceRecord);

        when(mockReminderLogic.add(any(Reminder.class))).thenReturn(0);

        boolean result = control.addReminder(reminder);
        assertTrue(result);
    }
}
