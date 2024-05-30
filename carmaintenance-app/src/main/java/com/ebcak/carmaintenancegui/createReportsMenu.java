/**
 * @file createReportsMenu.java
 * @brief This file contains the GUI class for creating reports in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class createReportsMenu extends JFrame {

    private JTextField txtDriverName;
    private JPanel infoPanel;

    /**
     * @brief Constructor for createReportsMenu class to initialize the GUI components.
     */
    public createReportsMenu() {
        setTitle("Create Reports Menu");
        setSize(1000, 700);
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
        JLabel lblTitle = new JLabel("CREATE REPORTS MENU");
        lblTitle.setBounds(320, 44, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // lblDriverName ekle
        JLabel lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setBounds(385, 180, 230, 30);
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverName.setForeground(Color.WHITE);
        formPanel.add(lblDriverName);

        txtDriverName = new JTextField();
        txtDriverName.setBounds(385, 220, 230, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtDriverName);

        // btnList ekle
        JButton btnList = new JButton("List");
        btnList.setBounds(385, 270, 230, 40);
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        formPanel.add(btnList);

        // infoPanel ekle
        infoPanel = new JPanel();
        infoPanel.setBounds(385, 330, 230, 130);
        infoPanel.setBackground(new Color(204, 255, 204)); // Açık yeşil renk
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // Başlangıçta görünmez olacak
        formPanel.add(infoPanel);

        JLabel lblBrand = new JLabel("Brand:");
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel lblDriver = new JLabel("Driver Name:");
        lblDriver.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));

        infoPanel.add(lblBrand);
        infoPanel.add(lblDriver);
        infoPanel.add(lblKilometer);

        getContentPane().add(formPanel, BorderLayout.CENTER);
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(385, 573, 230, 40);
        formPanel.add(btnBack);
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setPreferredSize(new Dimension(230, 40));
        btnBack.setUI(new roundedButtonUI());
        btnBack.addActionListener(e -> {
            dispose();
            new carServiceMenuScreen().setVisible(true);
        });

        // Alt kısım: Back butonu
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        getContentPane().add(backPanel, BorderLayout.SOUTH);

        // Butonlara tıklama işlemleri
        btnList.addActionListener(e -> listDriverInfo(lblBrand, lblDriver, lblKilometer));
    }

    /**
     * @brief Lists the driver information based on the entered driver name.
     * @param lblBrand JLabel to display the brand information.
     * @param lblDriver JLabel to display the driver name.
     * @param lblKilometer JLabel to display the kilometer information.
     */
    private void listDriverInfo(JLabel lblBrand, JLabel lblDriver, JLabel lblKilometer) {
        // Bu örnekte, bilgileri manuel olarak giriyoruz. Gerçek uygulamalarda bu bilgiler veritabanından veya başka bir kaynaktan alınabilir.
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        // Örnek veriler
        String brand = "---";
        String kilometer = "---";

        lblBrand.setText("Brand: " + brand);
        lblDriver.setText("Driver Name: " + driverName);
        lblKilometer.setText("Kilometer: " + kilometer);

        infoPanel.setVisible(true); // "List" butonuna tıklandığında paneli görünür yap
    }

    /**
     * @brief Main method to run the createReportsMenu GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new createReportsMenu().setVisible(true);
            }
        });
    }
}
