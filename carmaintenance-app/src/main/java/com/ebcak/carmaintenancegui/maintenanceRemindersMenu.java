/**
 * @file maintenanceRemindersMenu.java
 * @brief This file contains the GUI class for the Maintenance Reminders Menu in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class maintenanceRemindersMenu extends JFrame {

    /**
     * @brief Constructor for maintenanceRemindersMenu class to initialize the GUI components.
     */
    public maintenanceRemindersMenu() {
        setTitle("Maintenance Reminders Menu");
        setSize(1000, 700); // Pencere boyutunu artırabiliriz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Uygulamanın arka plan rengini ayarla
        getContentPane().setBackground(new Color(0x023F5C));

        // Başlık paneli ekle
        roundedPanelUI titlePanel = new roundedPanelUI(30); // Yuvarlak köşe yarıçapı
        titlePanel.setBounds(20, 25, 950, 130);
        titlePanel.setBackground(new Color(24, 154, 180)); // Turkuaz
        titlePanel.setLayout(null);
        JLabel lblTitle = new JLabel("MAINTENANCE REMINDERS MENU");
        lblTitle.setBounds(270, 44, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // Menü butonları ekle
        JButton btnSetReminder = new JButton("SET REMINDER");
        btnSetReminder.setBounds(20, 190, 950, 60);
        btnSetReminder.setName("btnSetReminder"); // Set name for testing
        btnSetReminder.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSetReminder.setForeground(Color.WHITE);
        btnSetReminder.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnSetReminder.setUI(new roundedButtonUI());
        formPanel.add(btnSetReminder);

        JButton btnViewReminder = new JButton("VIEW REMINDER");
        btnViewReminder.setBounds(20, 282, 950, 60);
        btnViewReminder.setName("btnViewReminder"); // Set name for testing
        btnViewReminder.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnViewReminder.setForeground(Color.WHITE);
        btnViewReminder.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnViewReminder.setUI(new roundedButtonUI());
        formPanel.add(btnViewReminder);

        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(20, 370, 950, 60);
        btnBack.setName("btnBack"); // Set name for testing
        btnBack.setBackground(new Color(238, 98, 3)); // Turuncu renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setUI(new roundedButtonUI());
        formPanel.add(btnBack);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Butonlara tıklama işlemleri
        btnSetReminder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new addReminder().setVisible(true);
            }
        });

        btnViewReminder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new viewReminder().setVisible(true);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new carServiceMenuScreen().setVisible(true);
            }
        });
    }

    /**
     * @brief Main method to run the maintenanceRemindersMenu GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new maintenanceRemindersMenu().setVisible(true);
            }
        });
    }
}
