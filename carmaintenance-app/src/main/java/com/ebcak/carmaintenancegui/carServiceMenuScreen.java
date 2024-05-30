/**
 * @file carServiceMenuScreen.java
 * @brief This file contains the GUI class for the Car Service Menu in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class carServiceMenuScreen extends JFrame {

    /**
     * @brief Constructor for carServiceMenuScreen class to initialize the GUI components.
     */
    public carServiceMenuScreen() {
        setTitle("Car Service Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(0x023F5C));

        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(null);

        roundedPanelUI titlePanel = new roundedPanelUI(30);
        titlePanel.setBounds(20, 25, 950, 130);
        titlePanel.setBackground(new Color(24, 154, 180));
        titlePanel.setLayout(null);
        JLabel lblTitle = new JLabel("CAR SERVICE MENU");
        lblTitle.setBounds(360, 45, 280, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        menuPanel.add(titlePanel);

        JButton btnFuelEfficiencyReports = new JButton("FUEL EFFICIENCY REPORTS MENU");
        btnFuelEfficiencyReports.setBounds(20, 180, 950, 60);
        btnFuelEfficiencyReports.setName("btnFuelEfficiencyReports");
        JButton btnExpenseRecords = new JButton("EXPENSE RECORDS MENU");
        btnExpenseRecords.setBounds(20, 250, 950, 60);
        btnExpenseRecords.setName("btnExpenseRecords");
        JButton btnServiceRecords = new JButton("SERVICE RECORDS MENU");
        btnServiceRecords.setBounds(20, 320, 950, 60);
        btnServiceRecords.setName("btnServiceRecords");
        JButton btnMaintenanceReminders = new JButton("MAINTENANCE REMINDERS MENU");
        btnMaintenanceReminders.setBounds(20, 390, 950, 60);
        btnMaintenanceReminders.setName("btnMaintenanceReminders");
        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(20, 460, 950, 60);
        btnBack.setName("btnBack");

        btnFuelEfficiencyReports.setBackground(new Color(0, 51, 153));
        btnFuelEfficiencyReports.setForeground(Color.WHITE);
        btnFuelEfficiencyReports.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnFuelEfficiencyReports.setUI(new roundedButtonUI());

        btnExpenseRecords.setBackground(new Color(0, 51, 153));
        btnExpenseRecords.setForeground(Color.WHITE);
        btnExpenseRecords.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnExpenseRecords.setUI(new roundedButtonUI());

        btnServiceRecords.setBackground(new Color(0, 51, 153));
        btnServiceRecords.setForeground(Color.WHITE);
        btnServiceRecords.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnServiceRecords.setUI(new roundedButtonUI());

        btnMaintenanceReminders.setBackground(new Color(0, 51, 153));
        btnMaintenanceReminders.setForeground(Color.WHITE);
        btnMaintenanceReminders.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnMaintenanceReminders.setUI(new roundedButtonUI());

        btnBack.setBackground(new Color(238, 98, 3));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnBack.setUI(new roundedButtonUI());

        menuPanel.add(btnFuelEfficiencyReports);
        menuPanel.add(btnExpenseRecords);
        menuPanel.add(btnServiceRecords);
        menuPanel.add(btnMaintenanceReminders);
        menuPanel.add(btnBack);

        getContentPane().add(menuPanel, BorderLayout.CENTER);

        btnFuelEfficiencyReports.addActionListener(e -> {
            dispose();
            new fuelEfficiencyReportsMenu().setVisible(true);
        });

        btnExpenseRecords.addActionListener(e -> {
            dispose();
            new expenseRecordsMenu().setVisible(true);
        });

        btnServiceRecords.addActionListener(e -> {
            dispose();
            new serviceRecordsMenu().setVisible(true);
        });

        btnMaintenanceReminders.addActionListener(e -> {
            dispose();
            new maintenanceRemindersMenu().setVisible(true);
        });

        btnBack.addActionListener(e -> {
            dispose();
            new signInScreen().setVisible(true);
        });
    }

    /**
     * @brief Main method to run the carServiceMenuScreen GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new carServiceMenuScreen().setVisible(true));
    }
}
