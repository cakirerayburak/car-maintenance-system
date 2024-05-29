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
        setSize(1000, 700); // Pencere boyutunu artırdım
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
        JLabel lblTitle = new JLabel("SERVICE RECORDS MENU");
        lblTitle.setBounds(300, 44, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // Menü butonları ekle
        JButton btnCreateServiceEntry = new JButton("CREATE SERVICE ENTRY");
        btnCreateServiceEntry.setBounds(20, 180, 950, 60);
        btnCreateServiceEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnCreateServiceEntry.setForeground(Color.WHITE);
        btnCreateServiceEntry.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnCreateServiceEntry.setUI(new roundedButtonUI());
        formPanel.add(btnCreateServiceEntry);

        JButton btnEditServiceEntry = new JButton("EDIT SERVICE ENTRY");
        btnEditServiceEntry.setBounds(20, 250, 950, 60);
        btnEditServiceEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnEditServiceEntry.setForeground(Color.WHITE);
        btnEditServiceEntry.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnEditServiceEntry.setUI(new roundedButtonUI());
        formPanel.add(btnEditServiceEntry);

        JButton btnSearchEntry = new JButton("SEARCH ENTRY");
        btnSearchEntry.setBounds(20, 320, 950, 60);
        btnSearchEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSearchEntry.setForeground(Color.WHITE);
        btnSearchEntry.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnSearchEntry.setUI(new roundedButtonUI());
        formPanel.add(btnSearchEntry);

        JButton btnDeleteServiceEntry = new JButton("DELETE SERVICE ENTRY");
        btnDeleteServiceEntry.setBounds(20, 390, 950, 60);
        btnDeleteServiceEntry.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnDeleteServiceEntry.setForeground(Color.WHITE);
        btnDeleteServiceEntry.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnDeleteServiceEntry.setUI(new roundedButtonUI());
        formPanel.add(btnDeleteServiceEntry);

        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(20, 460, 950, 60);
        btnBack.setBackground(new Color(238, 98, 3)); // Kırmızı renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnBack.setUI(new roundedButtonUI());
        formPanel.add(btnBack);

        getContentPane().add(formPanel, BorderLayout.CENTER);

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
        
        btnBack.addActionListener(e -> {
            dispose();
            new carServiceMenuScreen().setVisible(true);
        });
        
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
