package com.ebcak.carmaintenanceumpletest;

import com.ebcak.carmaintenanceumple.FuelEfficiencyReport;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * @class FuelEfficiencyReportTest
 * @brief Test class for testing FuelEfficiencyReport functionalities.
 */
public class FuelEfficiencyReportTest {

    private User user;
    private ServiceRecord serviceRecord;
    private FuelEfficiencyReport fuelEfficiencyReport;

    /**
     * @brief Setup method to initialize objects before each test.
     */
    @Before
    public void setUp() {
        user = new User(1, "testuser", "password123", "testuser@example.com");
        serviceRecord = new ServiceRecord(1, "Toyota", "Oil Change", "John Doe", "123456789", 50000, user.getUser_id(), user);
        fuelEfficiencyReport = new FuelEfficiencyReport(1, new Date(System.currentTimeMillis()), 25.5, 9.4, serviceRecord.getRecord_id(), serviceRecord);
    }

    /**
     * @brief Test method to verify the creation of a FuelEfficiencyReport object.
     */
    @Test
    public void testCreateFuelEfficiencyReport() {
        assertNotNull("FuelEfficiencyReport should be created successfully", fuelEfficiencyReport);
        assertEquals("Report ID should match", 1, fuelEfficiencyReport.getReport_id());
        assertEquals("MPG should match", 25.5, fuelEfficiencyReport.getMpg(), 0.01);
        assertEquals("L/100km should match", 9.4, fuelEfficiencyReport.getLper100km(), 0.01);
        assertEquals("Service Record ID should match", serviceRecord.getRecord_id(), fuelEfficiencyReport.getServiceRecord_id());
        assertEquals("Service Record should match", serviceRecord, fuelEfficiencyReport.getServiceRecord());
    }

    /**
     * @brief Test method to verify the creation of a FuelEfficiencyReport without a ServiceRecord.
     * @throws RuntimeException when a null ServiceRecord is provided.
     */
    @Test(expected = RuntimeException.class)
    public void testCreateFuelEfficiencyReportWithoutServiceRecord() {
        new FuelEfficiencyReport(2, new Date(System.currentTimeMillis()), 30.0, 8.0, 2, null);
    }

    /**
     * @brief Test method to verify getters and setters of FuelEfficiencyReport.
     */
    @Test
    public void testSettersAndGetters() {
        fuelEfficiencyReport.setReport_id(2);
        fuelEfficiencyReport.setReportDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
        fuelEfficiencyReport.setMpg(30.0);
        fuelEfficiencyReport.setLper100km(8.0);
        fuelEfficiencyReport.setServiceRecord_id(2);

        assertEquals("Report ID should match", 2, fuelEfficiencyReport.getReport_id());
        assertEquals("MPG should match", 30.0, fuelEfficiencyReport.getMpg(), 0.01);
        assertEquals("L/100km should match", 8.0, fuelEfficiencyReport.getLper100km(), 0.01);
        assertEquals("Service Record ID should match", 2, fuelEfficiencyReport.getServiceRecord_id());
    }

    /**
     * @brief Test method to verify the association between FuelEfficiencyReport and ServiceRecord.
     */
    @Test
    public void testServiceRecordAssociation() {
        ServiceRecord newServiceRecord = new ServiceRecord(2, "Honda", "Tire Change", "Jane Doe", "987654321", 60000, user.getUser_id(), user);
        fuelEfficiencyReport.setServiceRecord(newServiceRecord);

        assertEquals("Service Record should be updated", newServiceRecord, fuelEfficiencyReport.getServiceRecord());
        assertTrue("Old Service Record should not contain FuelEfficiencyReport", serviceRecord.getFuelEfficiencyReports().isEmpty());
        assertTrue("New Service Record should contain FuelEfficiencyReport", newServiceRecord.getFuelEfficiencyReports().contains(fuelEfficiencyReport));
    }

    /**
     * @brief Test method to verify the deletion of a FuelEfficiencyReport.
     */
    @Test
    public void testDeleteFuelEfficiencyReport() {
        fuelEfficiencyReport.delete();
        assertNull("Service Record should be null after delete", fuelEfficiencyReport.getServiceRecord());
        assertTrue("Service Record should not contain FuelEfficiencyReport after delete", serviceRecord.getFuelEfficiencyReports().isEmpty());
    }

    /**
     * @brief Test method to verify the toString method of FuelEfficiencyReport.
     */
    @Test
    public void testToString() {
        String expected = "FuelEfficiencyReport[report_id:1,mpg:25.5,lper100km:9.4,serviceRecord_id:1]\n" +
                          "  reportDate=" + fuelEfficiencyReport.getReportDate().toString() + "\n" +
                          "  serviceRecord = " + Integer.toHexString(System.identityHashCode(serviceRecord));
        assertEquals("toString should match", expected, fuelEfficiencyReport.toString());
    }
}
