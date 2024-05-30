/**
 * @file addReminder.java
 * @brief This file contains the GUI class for adding reminders in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenancelogiclayer.logicJava.ServiceRecordLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ReminderLogic;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class addReminder extends JFrame {

    private JTextField txtDriverName;
    private JTextField txtCarBrand;
    private JTextField txtWhatToDo;
    private JTextField txtDriverNameEdit;
    private JTextField txtContactNum;
    private JTextField txtKilometer;
    private JPanel infoPanel;
    private JTextField textField;
    private ServiceRecordLogic serviceRecordLogic;
    private ReminderLogic reminderLogic;

    /**
     * @brief Constructor for addReminder class to initialize the GUI components.
     */
    public addReminder() {
        serviceRecordLogic = new ServiceRecordLogic();
        reminderLogic = new ReminderLogic();
        setTitle("Edit Service Entry");
        setSize(1000, 700); // Pencere boyutu
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
        JLabel lblTitle = new JLabel("EDIT SERVICE ENTRY");
        lblTitle.setBounds(235, 44, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(null);
        JLabel lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setBounds(239, 80, 108, 24);
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverName.setForeground(Color.WHITE);
        formPanel.add(lblDriverName);
        txtDriverName = new JTextField(20);
        txtDriverName.setBounds(373, 80, 286, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtDriverName);
        JButton btnShow = new JButton("Show");
        btnShow.setBounds(673, 75, 74, 34);
        btnShow.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnShow.setForeground(Color.WHITE);
        btnShow.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnShow.setUI(new roundedButtonUI());
        formPanel.add(btnShow);
        infoPanel = new JPanel();
        infoPanel.setBounds(239, 144, 508, 217);
        infoPanel.setBackground(new Color(204, 255, 204));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Driver Info"));
        infoPanel.setVisible(false); // Başlangıçta görünmez olacak
        formPanel.add(infoPanel);

        GridBagConstraints gbcInfo;
        infoPanel.setLayout(null);
        JLabel lblCarBrand = new JLabel("Car Brand:");
        lblCarBrand.setBounds(92, 23, 87, 19);
        lblCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblCarBrand);
        txtCarBrand = new JTextField(20);
        txtCarBrand.setBounds(189, 20, 226, 25);
        txtCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtCarBrand);
        JLabel lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setBounds(92, 58, 87, 19);
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblWhatToDo);
        txtWhatToDo = new JTextField(20);
        txtWhatToDo.setBounds(189, 55, 226, 25);
        txtWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtWhatToDo);
        JLabel lblDriverNameEdit = new JLabel("Driver Name:");
        lblDriverNameEdit.setBounds(92, 93, 87, 19);
        lblDriverNameEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblDriverNameEdit);
        txtDriverNameEdit = new JTextField(20);
        txtDriverNameEdit.setBounds(189, 90, 226, 25);
        txtDriverNameEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtDriverNameEdit);
        JLabel lblContactNum = new JLabel("Contact Num:");
        lblContactNum.setBounds(92, 128, 87, 19);
        lblContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblContactNum);
        txtContactNum = new JTextField(20);
        txtContactNum.setBounds(189, 125, 226, 25);
        txtContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtContactNum);
        JLabel lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setBounds(92, 163, 87, 19);
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblKilometer);
        txtKilometer = new JTextField(20);
        txtKilometer.setBounds(189, 160, 226, 25);
        txtKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtKilometer);
        JButton btnEdit = new JButton("Add Reminder");
        btnEdit.setBounds(239, 440, 508, 34);
        btnEdit.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnEdit.setUI(new roundedButtonUI());
        formPanel.add(btnEdit);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(239, 484, 508, 34);
        btnBack.setBackground(new Color(238, 98, 3)); // Mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setUI(new roundedButtonUI());
        formPanel.add(btnBack);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        JLabel lblReminderTime = new JLabel("Reminder Time :");
        lblReminderTime.setForeground(Color.WHITE);
        lblReminderTime.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblReminderTime.setBounds(239, 385, 145, 24);
        formPanel.add(lblReminderTime);

        textField = new JTextField(20);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        textField.setBounds(394, 382, 286, 30);
        formPanel.add(textField);

        btnBack.addActionListener(e -> {
            dispose();
            new maintenanceRemindersMenu().setVisible(true);
        });

        // Butonlara tıklama işlemleri
        btnShow.addActionListener(e -> showDriverInfo());
        btnEdit.addActionListener(e -> addReminder());
    }

    /**
     * @brief Displays the driver information based on the entered driver name.
     */
    private void showDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        ServiceRecord serviceRecord = serviceRecordLogic.getServiceRecordByDriverName(driverName);
        if (serviceRecord == null) {
            JOptionPane.showMessageDialog(this, "Service record not found for the driver name.");
            return;
        }

        txtCarBrand.setText(serviceRecord.getCarBrand());
        txtWhatToDo.setText(serviceRecord.getWhatToDo());
        txtDriverNameEdit.setText(serviceRecord.getDriverName());
        txtContactNum.setText(serviceRecord.getDriverPhone());
        txtKilometer.setText(String.valueOf(serviceRecord.getKilometer()));

        infoPanel.setVisible(true); // Bilgileri göster
    }

    /**
     * @brief Adds a reminder for the service record based on the entered details.
     */
    private void addReminder() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        String reminderTimeText = textField.getText();
        if (reminderTimeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a reminder time.");
            return;
        }

        Date reminderDate;
        try {
            reminderDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(reminderTimeText).getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        ServiceRecord serviceRecord = serviceRecordLogic.getServiceRecordByDriverName(driverName);
        if (serviceRecord == null) {
            JOptionPane.showMessageDialog(this, "Service record not found for the driver name.");
            return;
        }

        Reminder reminder = new Reminder(0, reminderDate, "Service", serviceRecord.getRecord_id(), serviceRecord);

        boolean result = reminderLogic.add(reminder) == 0;
        if (result) {
            JOptionPane.showMessageDialog(this, "Reminder added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add reminder.");
        }
    }

    /**
     * @brief Main method to run the addReminder GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new addReminder().setVisible(true);
            }
        });
    }
}
