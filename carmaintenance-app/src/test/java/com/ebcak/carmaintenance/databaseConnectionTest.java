package com.ebcak.carmaintenance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
 
public class databaseConnectionTest {
	private static final String DB_URL = "jdbc:sqlite:./SQLite/test_carMaintenanceDatabase.db";
    private Connection connection;

    @Before 
    public void setUp() {
        // Test başlamadan önce veritabanı bağlantısını kur
        connection = databaseConnection.getInstance(DB_URL).getConnection();
    }

    @Test
    public void testConnect() {
        // Bağlantının null olmadığını test et
        assertNotNull("Database connection should not be null", connection);
    }

    @Test
    public void testConnectionIsOpen() {
        try {
            // Bağlantının açık olduğunu test et
            assertFalse("Database connection should be open", connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            assertTrue("SQLException occurred: " + e.getMessage(), false);
        }
    }

    @After
    public void tearDown() {
        // Test sonrasında bağlantıyı kapat
        if (connection != null) {
            try {
                connection.close();
                assertTrue("Database connection should be closed", connection.isClosed());
            } catch (SQLException e) {
                e.printStackTrace();
                assertTrue("SQLException occurred: " + e.getMessage(), false);
            }
        }
    }
}
