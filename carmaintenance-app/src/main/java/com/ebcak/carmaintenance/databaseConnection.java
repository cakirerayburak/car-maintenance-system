package com.ebcak.carmaintenance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private static databaseConnection instance;
    private Connection connection;

    private databaseConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:./SQLite//carMaintenanceDatabase.db");
            System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } 
    }

    public static databaseConnection getInstance() {
        if (instance == null) {
            synchronized (databaseConnection.class) {
                if (instance == null) {
                    instance = new databaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:./SQLite//carMaintenanceDatabase.db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}