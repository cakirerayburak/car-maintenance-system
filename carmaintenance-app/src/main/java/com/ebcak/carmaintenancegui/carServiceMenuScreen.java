package com.ebcak.carmaintenancegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class carServiceMenuScreen extends JFrame {

    public carServiceMenuScreen() {
        setTitle("Car Service Menu");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("CAR SERVICE MENU");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Orta kısım: Menü butonları
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Menü butonları ekle
        JButton btnFuelEfficiencyReports = new JButton("FUEL EFFICIENCY REPORTS MENU");
        JButton btnExpenseRecords = new JButton("EXPENSE RECORDS MENU");
        JButton btnServiceRecords = new JButton("SERVICE RECORDS MENU");
        JButton btnMaintenanceReminders = new JButton("MAINTENANCE REMINDERS MENU");

        btnFuelEfficiencyReports.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnFuelEfficiencyReports.setForeground(Color.WHITE);
        btnFuelEfficiencyReports.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnExpenseRecords.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnExpenseRecords.setForeground(Color.WHITE);
        btnExpenseRecords.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnServiceRecords.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnServiceRecords.setForeground(Color.WHITE);
        btnServiceRecords.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnMaintenanceReminders.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnMaintenanceReminders.setForeground(Color.WHITE);
        btnMaintenanceReminders.setFont(new Font("SansSerif", Font.BOLD, 16));

        menuPanel.add(btnFuelEfficiencyReports);
        menuPanel.add(btnExpenseRecords);
        menuPanel.add(btnServiceRecords);
        menuPanel.add(btnMaintenanceReminders);

        add(menuPanel, BorderLayout.CENTER);

        // Butonlara tıklama işlemleri
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new carServiceMenuScreen().setVisible(true);
            }
        });
    }
}

