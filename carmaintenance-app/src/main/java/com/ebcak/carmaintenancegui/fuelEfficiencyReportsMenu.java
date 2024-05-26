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

public class fuelEfficiencyReportsMenu extends JFrame {

    public fuelEfficiencyReportsMenu() {
        setTitle("Fuel Efficiency Reports Menu");
        setSize(500, 450); // Pencere boyutunu artır
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("FUEL EFFICIENCY REPORTS MENU");
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

        JButton btnCreateReports = new JButton("CREATE REPORTS");
        btnCreateReports.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnCreateReports.setForeground(Color.WHITE);
        btnCreateReports.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCreateReports.setPreferredSize(buttonSize);
        btnCreateReports.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(btnCreateReports, gbc);

        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnBack.setPreferredSize(new Dimension(100, 30)); // Küçük boyut
        btnBack.setUI(new roundedButtonUI());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(btnBack, gbc);

        add(menuPanel, BorderLayout.CENTER);

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
