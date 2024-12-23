package com.ebcak.carmaintenanceumpletest;

import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * @class ReminderTest
 * @brief Test class for testing Reminder functionalities.
 */
public class ReminderTest {

    private User user;
    private ServiceRecord serviceRecord;
    private Reminder reminder;

    /**
     * @brief Setup method to initialize objects before each test.
     */
    @Before
    public void setUp() {
        user = new User(1, "testuser", "password123", "testuser@example.com");
        serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id(), user);
        reminder = new Reminder(1, new Date(System.currentTimeMillis()), "Check Engine", serviceRecord.getRecord_id(), serviceRecord);
    }

    /**
     * @brief Test method to verify the creation of a Reminder object.
     */
    @Test
    public void testCreateReminder() {
        assertNotNull("Reminder should be created successfully", reminder);
        assertEquals("Reminder ID should match", 1, reminder.getReminder_id());
        assertEquals("Reminder Type should match", "Check Engine", reminder.getReminderType());
        assertEquals("Service Record ID should match", serviceRecord.getRecord_id(), reminder.getServiceRecord_id());
        assertEquals("Service Record should match", serviceRecord, reminder.getServiceRecord());
    }

    /**
     * @brief Test method to verify the creation of a Reminder without a ServiceRecord.
     * @throws RuntimeException when a null ServiceRecord is provided.
     */
    @Test(expected = RuntimeException.class)
    public void testCreateReminderWithoutServiceRecord() {
        new Reminder(2, new Date(System.currentTimeMillis()), "Check Oil", 2, null);
    }

    /**
     * @brief Test method to verify getters and setters of Reminder.
     */
    @Test
    public void testSettersAndGetters() {
        reminder.setReminder_id(2);
        reminder.setReminderDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
        reminder.setReminderType("Check Brakes");
        reminder.setServiceRecord_id(2);

        assertEquals("Reminder ID should match", 2, reminder.getReminder_id());
        assertEquals("Reminder Type should match", "Check Brakes", reminder.getReminderType());
        assertEquals("Service Record ID should match", 2, reminder.getServiceRecord_id());
    }

    /**
     * @brief Test method to verify the association between Reminder and ServiceRecord.
     */
    @Test
    public void testServiceRecordAssociation() {
        ServiceRecord newServiceRecord = new ServiceRecord(2, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id(), user);
        reminder.setServiceRecord(newServiceRecord);

        assertEquals("Service Record should be updated", newServiceRecord, reminder.getServiceRecord());
        assertTrue("Old Service Record should not contain Reminder", serviceRecord.getReminders().isEmpty());
        assertTrue("New Service Record should contain Reminder", newServiceRecord.getReminders().contains(reminder));
    }

    /**
     * @brief Test method to verify the deletion of a Reminder.
     */
    @Test
    public void testDeleteReminder() {
        reminder.delete();
        assertNull("Service Record should be null after delete", reminder.getServiceRecord());
        assertTrue("Service Record should not contain Reminder after delete", serviceRecord.getReminders().isEmpty());
    }

    /**
     * @brief Test method to verify the toString method of Reminder.
     */
    @Test
    public void testToString() {
        String expected = "Reminder[reminder_id:1,reminderType:Check Engine,serviceRecord_id:1]\n" +
                          "  reminderDate=" + reminder.getReminderDate().toString() + "\n" +
                          "  serviceRecord = " + Integer.toHexString(System.identityHashCode(serviceRecord));
        assertEquals("toString should match", expected, reminder.toString());
    }
}
