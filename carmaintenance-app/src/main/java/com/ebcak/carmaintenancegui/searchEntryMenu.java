/**
 * @file searchEntryMenu.java
 * @brief This file contains the GUI class for searching a service entry in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenanceumple.ServiceRecord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class searchEntryMenu extends JFrame {

    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblWhatToDo;
    private JLabel lblDriverName;
    private JLabel lblContactNum;
    private JLabel lblKilometer;
    private JPanel infoPanel;
    public userControl userControlInstance;

    /**
     * @brief Constructor for searchEntryMenu class to initialize the GUI components.
     */
    public searchEntryMenu() {
        userControlInstance = new userControl();

        setTitle("Search Entry Menu");
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
        JLabel lblTitle = new JLabel("SEARCH ENTRY MENU");
        lblTitle.setBounds(345, 43, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // lblDriverNameLabel ekle
        JLabel lblDriverNameLabel = new JLabel("Driver Name:");
        lblDriverNameLabel.setBounds(385, 180, 230, 30);
        lblDriverNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverNameLabel.setForeground(Color.WHITE);
        formPanel.add(lblDriverNameLabel);

        txtDriverName = new JTextField();
        txtDriverName.setName("txtDriverName");
        txtDriverName.setBounds(385, 220, 230, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtDriverName);

        // btnSearch ekle
        JButton btnSearch = new JButton("Search");
        btnSearch.setName("btnSearch");
        btnSearch.setBounds(385, 270, 230, 40);
        btnSearch.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnSearch.setUI(new roundedButtonUI());
        formPanel.add(btnSearch);

        // infoPanel ekle
        infoPanel = new JPanel();
        infoPanel.setName("infoPanel");
        infoPanel.setBounds(385, 330, 230, 180);
        infoPanel.setBackground(new Color(204, 255, 204));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // İlk başta gizli
        formPanel.add(infoPanel);
        infoPanel.setLayout(null);
        
        lblBrand = new JLabel("Brand:");
        lblBrand.setName("lblBrand");
        lblBrand.setBounds(10, 17, 210, 19);
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblBrand);

        lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setName("lblWhatToDo");
        lblWhatToDo.setBounds(10, 43, 210, 19);
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblWhatToDo);

        lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setName("lblDriverName");
        lblDriverName.setBounds(10, 72, 210, 19);
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblDriverName);

        lblContactNum = new JLabel("Contact Num:");
        lblContactNum.setName("lblContactNum");
        lblContactNum.setBounds(10, 101, 210, 19);
        lblContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblContactNum);

        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setName("lblKilometer");
        lblKilometer.setBounds(10, 130, 210, 19);
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblKilometer);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        JButton btnBack = new JButton("Back");
        btnBack.setName("btnBack");
        btnBack.setBounds(385, 531, 230, 40);
        formPanel.add(btnBack);
        btnBack.setBackground(new Color(238, 98, 3)); // Kırmızı renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setPreferredSize(new Dimension(230, 40));
        btnBack.setUI(new roundedButtonUI());
        
        btnBack.addActionListener(e -> { 
            dispose();
            new serviceRecordsMenu().setVisible(true);
        });

        // Alt kısım: Exit butonu
        JPanel exitPanel = new JPanel();
        getContentPane().add(exitPanel, BorderLayout.SOUTH);

        // Butonlara tıklama işlemleri
        btnSearch.addActionListener(e -> searchDriverInfo());
    }

    /**
     * @brief Searches for the driver information based on the entered driver name.
     */
    private void searchDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            showAutoClosingDialog("Please enter a driver name.");
            return;
        }

        ServiceRecord serviceRecord = userControlInstance.getServiceRecordByDriverName(driverName);
        if (serviceRecord == null) {
            showAutoClosingDialog("Service record not found for driver: " + driverName);
            return;
        }

        lblBrand.setText("Brand: " + serviceRecord.getCarBrand());
        lblWhatToDo.setText("What to do: " + serviceRecord.getWhatToDo());
        lblDriverName.setText("Driver Name: " + serviceRecord.getDriverName());
        lblContactNum.setText("Contact Num: " + serviceRecord.getDriverPhone());
        lblKilometer.setText("Kilometer: " + serviceRecord.getKilometer());

        infoPanel.setVisible(true); // Bilgileri göster
    }

    /**
     * @brief Shows a dialog that closes automatically after a specified time.
     * @param message The message to be displayed in the dialog.
     */
    private void showAutoClosingDialog(String message) {
        final JDialog dialog = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE).createDialog(this, "Message");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Timer timer = new Timer(1500, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    /**
     * @brief Main method to run the searchEntryMenu GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new searchEntryMenu().setVisible(true);
            }
        });
    }
}
