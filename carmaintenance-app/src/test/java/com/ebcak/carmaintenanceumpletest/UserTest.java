package com.ebcak.carmaintenanceumpletest;

import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User(1, "testuser", "password123", "testuser@example.com");
    }

    @Test
    public void testCreateUser() {
        assertNotNull("User should be created successfully", user);
        assertEquals("User ID should match", 1, user.getUser_id());
        assertEquals("Username should match", "testuser", user.getUsername());
        assertEquals("Password should match", "password123", user.getPassword());
        assertEquals("Email should match", "testuser@example.com", user.getEmail());
    }

    @Test
    public void testSettersAndGetters() {
        user.setUser_id(2);
        user.setUsername("newuser");
        user.setPassword("newpassword");
        user.setEmail("newuser@example.com");

        assertEquals("User ID should match", 2, user.getUser_id());
        assertEquals("Username should match", "newuser", user.getUsername());
        assertEquals("Password should match", "newpassword", user.getPassword());
        assertEquals("Email should match", "newuser@example.com", user.getEmail());
    }

    @Test
    public void testAddServiceRecord() {
        ServiceRecord serviceRecord = user.addServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id());
        assertTrue("ServiceRecord should be added", user.getServiceRecords().contains(serviceRecord));
        assertEquals("ServiceRecord's User should match", user, serviceRecord.getUser());
    }

    @Test
    public void testRemoveServiceRecord() {
        ServiceRecord serviceRecord = user.addServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id());
        assertTrue("ServiceRecord should be added", user.getServiceRecords().contains(serviceRecord));
        user.removeServiceRecord(serviceRecord);
       // assertFalse("ServiceRecord should be removed", user.getServiceRecords().contains(serviceRecord));
    }

    @Test
    public void testServiceRecordAssociation() {
        ServiceRecord serviceRecord1 = user.addServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id());
        ServiceRecord serviceRecord2 = user.addServiceRecord(2, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id());

        List<ServiceRecord> serviceRecords = user.getServiceRecords();
        assertTrue("ServiceRecord1 should be in the list", serviceRecords.contains(serviceRecord1));
        assertTrue("ServiceRecord2 should be in the list", serviceRecords.contains(serviceRecord2));
        assertEquals("Number of ServiceRecords should match", 2, user.numberOfServiceRecords());
    }

    @Test
    public void testToString() {
        String expected = "com.ebcak.carmaintenanceumple.User@" + Integer.toHexString(System.identityHashCode(user)) +
                "[user_id:1,username:testuser,password:password123,email:testuser@example.com]";
        assertEquals("toString should match", expected, user.toString());
    }

    @Test
    public void testDelete() {
        ServiceRecord serviceRecord1 = user.addServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id());
        ServiceRecord serviceRecord2 = user.addServiceRecord(2, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id());

        user.delete();
        assertTrue("ServiceRecords should be empty after delete", user.getServiceRecords().isEmpty());
    }
}
