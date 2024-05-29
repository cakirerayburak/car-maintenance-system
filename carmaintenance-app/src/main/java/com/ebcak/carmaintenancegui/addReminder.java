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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class addReminder extends JFrame {

    private JTextField txtDriverName;
    private JTextField txtCarBrand;
    private JTextField txtWhatToDo;
    private JTextField txtDriverNameEdit;
    private JTextField txtContactNum;
    private JTextField txtKilometer;
    private JPanel infoPanel;
    private JTextField textField;

    public addReminder() {
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
        btnEdit.addActionListener(e -> {
            // Düzenleme işlemi burada gerçekleştirilecek
            JOptionPane.showMessageDialog(this, "Reminder Added.");
        });
    }

    private void showDriverInfo() {
        // Bu örnekte, bilgileri manuel olarak giriyoruz. Gerçek uygulamalarda bu bilgiler veritabanından veya başka bir kaynaktan alınabilir.
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        // Örnek veriler
        String carBrand = "Toyota";
        String whatToDo = "Oil Change";
        String driverNameEdit = driverName;
        String contactNum = "123-456-7890";
        String kilometer = "15000";
        txtCarBrand.setText(carBrand);
        txtWhatToDo.setText(whatToDo);
        txtDriverNameEdit.setText(driverNameEdit);
        txtContactNum.setText(contactNum);
        txtKilometer.setText(kilometer);

        infoPanel.setVisible(true); // Bilgileri göster
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new addReminder().setVisible(true);
            }
        });
    }
}
