package com.ebcak.carmaintenancegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class expenseRecordsMenu extends JFrame {

    public expenseRecordsMenu() {
        setTitle("Expense Records Menu");
        setSize(500, 450); // Pencere boyutunu artırabiliriz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("EXPENSE RECORDS MENU");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Orta kısım: Menü butonları
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension buttonSize = new Dimension(250, 40); // Buton genişliğini artır

        JButton btnLogExpense = new JButton("LOG EXPENSE");
        btnLogExpense.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnLogExpense.setForeground(Color.WHITE);
        btnLogExpense.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLogExpense.setPreferredSize(buttonSize);
        btnLogExpense.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(btnLogExpense, gbc);

        JButton btnViewExpense = new JButton("VIEW EXPENSE");
        btnViewExpense.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnViewExpense.setForeground(Color.WHITE);
        btnViewExpense.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnViewExpense.setPreferredSize(buttonSize);
        btnViewExpense.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(btnViewExpense, gbc);

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnBack.setPreferredSize(new Dimension(100, 30)); // Küçük boyut
        btnBack.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(btnBack, gbc);

        add(menuPanel, BorderLayout.CENTER);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new expenseRecordsMenu().setVisible(true);
            }
        });
    }
}

