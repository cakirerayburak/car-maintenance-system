/**
 * @file databaseConnection.java
 * @brief This file contains the singleton class for managing database connections.
 */

package com.ebcak.carmaintenance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private static databaseConnection instance;
    private Connection connection;
    private String databaseUrl;

    /**
     * @brief Private constructor to establish a connection to the database.
     * @param databaseUrl The URL of the database to connect to.
     */
    private databaseConnection(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(databaseUrl);
            System.out.println("Connected to: " + databaseUrl);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Retrieves the singleton instance of the database connection.
     * @param databaseUrl The URL of the database to connect to.
     * @return The singleton instance of the database connection.
     */
    public static databaseConnection getInstance(String databaseUrl) {
        if (instance == null) {
            synchronized (databaseConnection.class) {
                if (instance == null) {
                    instance = new databaseConnection(databaseUrl);
                }
            }
        }
        return instance;
    }

    /**
     * @brief Retrieves the current connection to the database.
     * @return The current Connection object to the database.
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(databaseUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
