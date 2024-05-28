package com.ebcak.carmaintenance;
/*
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

public class databaseConnectionTest {

    private Connection conn;

    @Before
    public void setUp() {
        // Test başlamadan önce veritabanı bağlantısını kur
        conn = databaseConnection.connect();
    }

    @Test
    public void testConnect() {
        // Bağlantının null olmadığını test et
        assertNotNull("Database connection should not be null", conn);
    }

    @Test
    public void testDisconnect() {
        // Bağlantıyı kes
        databaseConnection.disconnect();
        try {
            assertTrue("Database connection should be closed", conn.isClosed());
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        // Test sonrasında bağlantıyı kes
        databaseConnection.disconnect();
    }
}
*/