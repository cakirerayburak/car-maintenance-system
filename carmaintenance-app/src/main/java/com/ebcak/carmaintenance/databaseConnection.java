package com.ebcak.carmaintenance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private static databaseConnection instance;
    private Connection connection;
    private String databaseUrl;

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
