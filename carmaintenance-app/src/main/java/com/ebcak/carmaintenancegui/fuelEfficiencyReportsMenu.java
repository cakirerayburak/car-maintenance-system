package com.ebcak.carmaintenancegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class fuelEfficiencyReportsMenu extends JFrame {

    public fuelEfficiencyReportsMenu() {
        setTitle("Fuel Efficiency Reports Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Uygulamanın arka plan rengini ayarla
        getContentPane().setBackground(new Color(0x023F5C));

        // Orta kısım: Menü butonları
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        menuPanel.setLayout(null);

        // Başlık paneli ekle
        roundedPanelUI titlePanel = new roundedPanelUI(30); // Yuvarlak köşe yarıçapı
        titlePanel.setBounds(20, 25, 950, 130);
        titlePanel.setBackground(new Color(24, 154, 180)); // Turkuaz
        titlePanel.setLayout(null);
        JLabel lblTitle = new JLabel("FUEL EFFICIENCY REPORTS MENU");
        lblTitle.setBounds(285, 45, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        menuPanel.add(titlePanel);

        // Menü butonları ekle
        JButton btnCreateReports = new JButton("CREATE REPORTS");
        btnCreateReports.setBounds(20, 230, 950, 60);
        btnCreateReports.setName("btnCreateReports"); // Set name for testing
        JButton btnBack = new JButton("BACK");
        btnBack.setBounds(20, 400, 950, 60);
        btnBack.setName("btnBack"); // Set name for testing

        btnCreateReports.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnCreateReports.setForeground(Color.WHITE);
        btnCreateReports.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCreateReports.setUI(new roundedButtonUI());

        btnBack.setBackground(new Color(238, 98, 3)); // Mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnBack.setUI(new roundedButtonUI());

        menuPanel.add(btnCreateReports);
        menuPanel.add(btnBack);

        getContentPane().add(menuPanel, BorderLayout.CENTER);

        // Butonlara tıklama işlemleri
        btnCreateReports.addActionListener(e -> {
            new createReportsMenu().setVisible(true);
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
                new fuelEfficiencyReportsMenu().setVisible(true);
            }
        });
    }
}
