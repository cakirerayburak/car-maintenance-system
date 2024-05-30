/**
 * @file deleteServiceEntryMenu.java
 * @brief This file contains the GUI class for deleting a service entry in the car maintenance application.
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteServiceEntryMenu extends JFrame {

    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblWhatToDo;
    private JLabel lblDriverName;
    private JLabel lblContactNum;
    private JLabel lblKilometer;
    private JPanel infoPanel;
    public userControl userControlInstance;
    private ServiceRecord currentServiceRecord;

    /**
     * @brief Constructor for deleteServiceEntryMenu class to initialize the GUI components.
     */
    public deleteServiceEntryMenu() {
        userControlInstance = new userControl();

        setTitle("Delete Service Entry");
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
        JLabel lblTitle = new JLabel("DELETE SERVICE ENTRY");
        lblTitle.setBounds(315, 45, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // lblDriverName ekle
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

        // btnList ekle
        JButton btnList = new JButton("List");
        btnList.setName("btnList");
        btnList.setBounds(385, 270, 230, 40);
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        formPanel.add(btnList);

        // infoPanel ekle
        infoPanel = new JPanel();
        infoPanel.setName("infoPanel");
        infoPanel.setBounds(385, 330, 230, 180);
        infoPanel.setBackground(new Color(204, 255, 204));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // Başlangıçta görünmez olacak
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

        JButton btnDelete = new JButton("Delete");
        btnDelete.setName("btnDelete");
        btnDelete.setBounds(385, 520, 230, 40);
        formPanel.add(btnDelete);
        btnDelete.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnDelete.setPreferredSize(new Dimension(230, 40));
        btnDelete.setUI(new roundedButtonUI());
                
        JButton btnBack = new JButton("Back");
        btnBack.setName("btnBack");
        btnBack.setBounds(385, 585, 230, 40);
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

        btnDelete.addActionListener(e -> deleteDriverInfo());

        // Alt kısım: Exit ve Delete butonları
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Butonlara tıklama işlemleri
        btnList.addActionListener(e -> listDriverInfo());
    }

    /**
     * @brief Lists the driver information based on the entered driver name.
     */
    private void listDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            showAutoClosingDialog("Please enter a driver name.", 1500);
            return;
        }

        currentServiceRecord = userControlInstance.getServiceRecordByDriverName(driverName);
        if (currentServiceRecord == null) {
            showAutoClosingDialog("Service record not found for driver: " + driverName, 1500);
            return;
        }

        lblBrand.setText("Brand: " + currentServiceRecord.getCarBrand());
        lblWhatToDo.setText("What to do: " + currentServiceRecord.getWhatToDo());
        lblDriverName.setText("Driver Name: " + currentServiceRecord.getDriverName());
        lblContactNum.setText("Contact Num: " + currentServiceRecord.getDriverPhone());
        lblKilometer.setText("Kilometer: " + currentServiceRecord.getKilometer());

        infoPanel.setVisible(true); // Bilgileri göster
    }

    /**
     * @brief Deletes the service entry based on the current service record.
     */
    private void deleteDriverInfo() {
        if (currentServiceRecord == null) {
            showAutoClosingDialog("Please list the driver information first.", 1500);
            return;
        }

        boolean result = userControlInstance.deleteServiceRecordByDriverName(currentServiceRecord.getDriverName());

        if (result) {
            showAutoClosingDialog("Service entry deleted successfully.", 1500);
            infoPanel.setVisible(false);
        } else {
            showAutoClosingDialog("Failed to delete service entry.", 1500);
        }
    }

    /**
     * @brief Displays an auto-closing dialog with the specified message.
     * @param message The message to display in the dialog.
     * @param milliseconds The duration for which the dialog should be displayed.
     */
    private void showAutoClosingDialog(String message, int milliseconds) {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = optionPane.createDialog(this, "Message");

        Timer timer = new Timer(milliseconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start();
        dialog.setVisible(true);
    }

    /**
     * @brief Main method to run the deleteServiceEntryMenu GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new deleteServiceEntryMenu().setVisible(true));
    }
}
