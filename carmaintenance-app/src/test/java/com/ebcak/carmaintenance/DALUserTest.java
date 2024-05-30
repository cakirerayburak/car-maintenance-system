/**
 * @file DALUserTest.java
 * @brief This file contains unit tests for the DALUser class.
 */

package com.ebcak.carmaintenance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @class DALUserTest
 * @brief Unit tests for the DALUser class.
 */
public class DALUserTest {

    private Connection connection;
    private static final String TEST_DB_URL = "jdbc:sqlite:./SQLite/test_carMaintenanceDatabase.db";

    /**
     * @brief Set up the database connection and create tables before each test.
     */
    @Before
    public void setUp() {
        // Test başlamadan önce veritabanı bağlantısını kur ve test tablosunu oluştur
        connection = databaseConnection.getInstance(TEST_DB_URL).getConnection();
        try (Statement stmt = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS user (" +
                                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "username TEXT NOT NULL UNIQUE, " +
                                    "password TEXT NOT NULL, " +
                                    "email TEXT NOT NULL UNIQUE)";
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Test case for registering a user.
     */
    @Test
    public void testRegisterUser() {
        // Kullanıcı kaydı test et
        boolean result = DALUser.registerUser("testuser", "password123", "testuser@example.com");
        assertTrue("User registration should be successful", result);

        // Aynı kullanıcıyı tekrar kaydetmeye çalış ve başarısız olması gerektiğini kontrol et
        boolean duplicateResult = DALUser.registerUser("testuser", "password123", "testuser@example.com");
        assertFalse("Duplicate user registration should fail", duplicateResult);
    }

    /**
     * @brief Test case for logging in a user.
     */
    @Test
    public void testLoginUser() {
        // Kullanıcıyı kaydet ve giriş yapmayı test et
        DALUser.registerUser("testlogin", "password123", "testlogin@example.com");
        boolean loginResult = DALUser.loginUser("testlogin", "password123");
        assertTrue("User login should be successful", loginResult);

        // Yanlış kullanıcı adı veya parola ile giriş yapmayı test et
        boolean wrongLoginResult = DALUser.loginUser("wronguser", "password123");
        assertFalse("Login with wrong username should fail", wrongLoginResult);

        boolean wrongPasswordResult = DALUser.loginUser("testlogin", "wrongpassword");
        assertFalse("Login with wrong password should fail", wrongPasswordResult);
    }

    /**
     * @brief Tear down the database connection and delete the test database after each test.
     */
    @After
    public void tearDown() {
        // Test sonrasında bağlantıyı kapat ve test veritabanını sil
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
