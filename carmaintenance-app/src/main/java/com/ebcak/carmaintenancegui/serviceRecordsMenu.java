package com.ebcak.carmaintenancegui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class serviceRecordsMenu extends JFrame {

    public serviceRecordsMenu() {
        setTitle("Service Records Menu");
        setSize(500, 600); // Pencere boyutunu artırdım
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("SERVICE RECORDS MENU");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Orta kısım: Menü butonları
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton btnCreateServiceEntry = new JButton("CREATE SERVICE ENTRY");
        btnCreateServiceEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnCreateServiceEntry.setForeground(Color.WHITE);
        btnCreateServiceEntry.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCreateServiceEntry.setPreferredSize(new Dimension(250, 50)); // Boyutu artırdım
        btnCreateServiceEntry.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(btnCreateServiceEntry, gbc);

        JButton btnEditServiceEntry = new JButton("EDIT SERVICE ENTRY");
        btnEditServiceEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnEditServiceEntry.setForeground(Color.WHITE);
        btnEditServiceEntry.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEditServiceEntry.setPreferredSize(new Dimension(250, 50)); // Boyutu artırdım
        btnEditServiceEntry.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        menuPanel.add(btnEditServiceEntry, gbc);

        JButton btnSearchEntry = new JButton("SEARCH ENTRY");
        btnSearchEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSearchEntry.setForeground(Color.WHITE);
        btnSearchEntry.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSearchEntry.setPreferredSize(new Dimension(250, 50)); // Boyutu artırdım
        btnSearchEntry.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        menuPanel.add(btnSearchEntry, gbc);

        JButton btnDeleteServiceEntry = new JButton("DELETE SERVICE ENTRY");
        btnDeleteServiceEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnDeleteServiceEntry.setForeground(Color.WHITE);
        btnDeleteServiceEntry.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnDeleteServiceEntry.setPreferredSize(new Dimension(250, 50)); // Boyutu artırdım
        btnDeleteServiceEntry.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        menuPanel.add(btnDeleteServiceEntry, gbc);

        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(255, 0, 0)); // Kırmızı renk
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnExit.setPreferredSize(new Dimension(250, 50)); // Boyutu artırdım
        btnExit.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        menuPanel.add(btnExit, gbc);

        add(menuPanel, BorderLayout.CENTER);

        // Butonlara tıklama işlemleri
        btnCreateServiceEntry.addActionListener(e -> {
            dispose();
            new createServiceEntryMenu().setVisible(true);
        });

        btnEditServiceEntry.addActionListener(e -> {
            dispose();
            new editServiceEntryMenu().setVisible(true);
        });

        btnSearchEntry.addActionListener(e -> {
            dispose();
            new searchEntryMenu().setVisible(true);
        });

        btnDeleteServiceEntry.addActionListener(e -> {
            dispose();
            new deleteServiceEntryMenu().setVisible(true);
        });

        btnExit.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new serviceRecordsMenu().setVisible(true);
            }
        });
    }
}
