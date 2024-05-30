/**
 * @file expenseRecordsMenu.java
 * @brief This file contains the GUI class for the Expense Records Menu in the car maintenance application.
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

public class expenseRecordsMenu extends JFrame {

    /**
     * @brief Constructor for expenseRecordsMenu class to initialize the GUI components.
     */
    public expenseRecordsMenu() {
        setTitle("Expense Records Menu");
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
        JLabel lblTitle = new JLabel("EXPENSE RECORDS MENU");
        lblTitle.setBounds(310, 50, 400, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // Menü butonları ekle
        JButton btnLogExpense = new JButton("LOG EXPENSE");
        btnLogExpense.setBounds(20, 195, 950, 60);
        btnLogExpense.setName("btnLogExpense"); // Set name for testing
        btnLogExpense.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnLogExpense.setForeground(Color.WHITE);
        btnLogExpense.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnLogExpense.setUI(new roundedButtonUI());
        formPanel.add(btnLogExpense);

        JButton btnViewExpense = new JButton("VIEW EXPENSE");
        btnViewExpense.setBounds(20, 295, 950, 60);
        btnViewExpense.setName("btnViewExpense"); // Set name for testing
        btnViewExpense.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnViewExpense.setForeground(Color.WHITE);
        btnViewExpense.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnViewExpense.setUI(new roundedButtonUI());
        formPanel.add(btnViewExpense);

        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(20, 400, 950, 60);
        btnBack.setName("btnBack"); // Set name for testing
        btnBack.setBackground(new Color(238, 98, 3)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setUI(new roundedButtonUI());
        formPanel.add(btnBack);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Butonlara tıklama işlemleri
        btnLogExpense.addActionListener(e -> {
            new logExpenseMenu().setVisible(true);
        });

        btnViewExpense.addActionListener(e -> {
            new viewExpenseMenu().setVisible(true);
        });

        btnBack.addActionListener(e -> {
            dispose();
            new carServiceMenuScreen().setVisible(true);
        });
    }

    /**
     * @brief Main method to run the expenseRecordsMenu GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new expenseRecordsMenu().setVisible(true);
            }
        });
    }
}
