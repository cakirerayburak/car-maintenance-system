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

public class editServiceEntryMenu extends JFrame {

    private JTextField txtDriverName;
    private JTextField txtCarBrand;
    private JTextField txtWhatToDo;
    private JTextField txtDriverNameEdit;
    private JTextField txtContactNum;
    private JTextField txtKilometer;
    private JPanel infoPanel;

    public editServiceEntryMenu() {
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
        lblDriverName.setBounds(239, 159, 108, 24);
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverName.setForeground(Color.WHITE);
        formPanel.add(lblDriverName);
        txtDriverName = new JTextField(20);
        txtDriverName.setBounds(367, 156, 286, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtDriverName);
        JButton btnShow = new JButton("Show");
        btnShow.setBounds(673, 154, 74, 34);
        btnShow.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnShow.setForeground(Color.WHITE);
        btnShow.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnShow.setUI(new roundedButtonUI());
        formPanel.add(btnShow);
        infoPanel = new JPanel();
        infoPanel.setBounds(239, 208, 508, 196);
        infoPanel.setBackground(new Color(204, 255, 204)); // Açık yeşil renk
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Driver Info"));
        infoPanel.setVisible(false); // Başlangıçta görünmez olacak
        formPanel.add(infoPanel);

        GridBagConstraints gbcInfo;

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        JLabel lblCarBrand = new JLabel("Car Brand:");
        lblCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblCarBrand, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 0;
        gbcInfo.gridwidth = 2;
        txtCarBrand = new JTextField(20);
        txtCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtCarBrand, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 1;
        gbcInfo.gridwidth = 1;
        JLabel lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblWhatToDo, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 1;
        gbcInfo.gridwidth = 2;
        txtWhatToDo = new JTextField(20);
        txtWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtWhatToDo, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 2;
        gbcInfo.gridwidth = 1;
        JLabel lblDriverNameEdit = new JLabel("Driver Name:");
        lblDriverNameEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblDriverNameEdit, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 2;
        gbcInfo.gridwidth = 2;
        txtDriverNameEdit = new JTextField(20);
        txtDriverNameEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtDriverNameEdit, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 3;
        gbcInfo.gridwidth = 1;
        JLabel lblContactNum = new JLabel("Contact Num:");
        lblContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblContactNum, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 3;
        gbcInfo.gridwidth = 2;
        txtContactNum = new JTextField(20);
        txtContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtContactNum, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 0;
        gbcInfo.gridy = 4;
        gbcInfo.gridwidth = 1;
        JLabel lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblKilometer, gbcInfo);

        gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.fill = GridBagConstraints.HORIZONTAL;
        gbcInfo.gridx = 1;
        gbcInfo.gridy = 4;
        gbcInfo.gridwidth = 2;
        txtKilometer = new JTextField(20);
        txtKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(txtKilometer, gbcInfo);
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(239, 435, 508, 34);
        btnEdit.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnEdit.setUI(new roundedButtonUI());
        formPanel.add(btnEdit);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(239, 492, 508, 34);
        btnBack.setBackground(new Color(238, 98, 3)); // Mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setUI(new roundedButtonUI());
        formPanel.add(btnBack);	
        
        getContentPane().add(formPanel, BorderLayout.CENTER);

        btnBack.addActionListener(e -> {
            dispose();
            new serviceRecordsMenu().setVisible(true);
        });
        
        // Butonlara tıklama işlemleri
        btnShow.addActionListener(e -> showDriverInfo());
        btnEdit.addActionListener(e -> {
            // Düzenleme işlemi burada gerçekleştirilecek
            JOptionPane.showMessageDialog(this, "Service entry edited.");
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
                new editServiceEntryMenu().setVisible(true);
            }
        });
    }
}
