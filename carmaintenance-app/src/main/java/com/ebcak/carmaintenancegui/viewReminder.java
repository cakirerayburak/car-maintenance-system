package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenancelogiclayer.logicJava.ServiceRecordLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ReminderLogic;
import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class viewReminder extends JFrame {

    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblDriver;
    private JLabel lblKilometer;
    private JLabel lblReminder;
    private JPanel infoPanel;
    private ServiceRecordLogic serviceRecordLogic;
    private ReminderLogic reminderLogic;

    public viewReminder() {
        serviceRecordLogic = new ServiceRecordLogic();
        reminderLogic = new ReminderLogic();

        setTitle("View Reminder Menu");
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
        JLabel lblTitle = new JLabel("VIEW REMINDER MENU");
        lblTitle.setBounds(385, 43, 280, 32);
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
        btnList.setBounds(625, 220, 100, 30);
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        formPanel.add(btnList);

        // Bilgi paneli ekle
        infoPanel = new JPanel();
        infoPanel.setBounds(385, 270, 340, 160);
        infoPanel.setBackground(new Color(204, 255, 204)); // Açık yeşil renk
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // İlk başta gizli

        lblBrand = new JLabel("Brand:");
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriver = new JLabel("Driver Name:");
        lblDriver.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblReminder = new JLabel("Reminder:");
        lblReminder.setFont(new Font("SansSerif", Font.PLAIN, 18));

        infoPanel.add(lblBrand);
        infoPanel.add(lblDriver);
        infoPanel.add(lblKilometer);
        infoPanel.add(lblReminder);

        formPanel.add(infoPanel);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Alt kısım: Back butonu
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(385, 463, 345, 56);
        btnBack.setBackground(new Color(238, 98, 3)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnBack.setUI(new roundedButtonUI());
        formPanel.add(btnBack);

        // Butonlara tıklama işlemleri
        btnList.addActionListener(e -> listDriverInfo());
        btnBack.addActionListener(e -> {
            dispose();
            new maintenanceRemindersMenu().setVisible(true);
        });
    }

    private void listDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        ServiceRecord serviceRecord = serviceRecordLogic.getServiceRecordByDriverName(driverName);
        if (serviceRecord == null) {
            JOptionPane.showMessageDialog(this, "No service record found for the given driver name.");
            return;
        }

        List<Reminder> reminders = reminderLogic.getRemindersByServiceRecordId(serviceRecord.getRecord_id());
        if (reminders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No reminders found for the given driver name.");
            return;
        }

        Reminder reminder = reminders.get(0); // İlk hatırlatmayı göster
        lblBrand.setText("Brand: " + serviceRecord.getCarBrand());
        lblDriver.setText("Driver Name: " + serviceRecord.getDriverName());
        lblKilometer.setText("Kilometer: " + serviceRecord.getKilometer());
        lblReminder.setText("Reminder: " + reminder.getReminderDate().toString());

        infoPanel.setVisible(true); // Bilgileri göster
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new viewReminder().setVisible(true);
            }
        });
    }
}
