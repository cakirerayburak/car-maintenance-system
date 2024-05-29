package com.ebcak.carmaintenancegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class createServiceEntryMenu extends JFrame {

    private JTextField txtCarBrand;
    private JTextField txtWhatToDo;
    private JTextField txtDriverName;
    private JTextField txtDriverNum;
    private JTextField txtKilometer;
    private ServiceRecordControl serviceRecordControl;

    public createServiceEntryMenu() {
        setTitle("Create Service Entry");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        serviceRecordControl = new ServiceRecordControl();

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("CREATE SERVICE ENTRY");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Sol üst kısım: Back butonu
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnBack = new JButton("← Back");
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setUI(new roundedButtonUI());
        btnBack.addActionListener(e -> {
            dispose();
            new serviceRecordsMenu().setVisible(true);
        });
        backPanel.add(btnBack);
        add(backPanel, BorderLayout.NORTH);

        // Orta kısım: Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCarBrand = new JLabel("Car Brand:");
        lblCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblCarBrand, gbc);

        txtCarBrand = new JTextField(20);
        txtCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(txtCarBrand, gbc);

        JLabel lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(lblWhatToDo, gbc);

        txtWhatToDo = new JTextField(20);
        txtWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(txtWhatToDo, gbc);

        JLabel lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(lblDriverName, gbc);

        txtDriverName = new JTextField(20);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(txtDriverName, gbc);

        JLabel lblDriverNum = new JLabel("Driver Num:");
        lblDriverNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        formPanel.add(lblDriverNum, gbc);

        txtDriverNum = new JTextField(20);
        txtDriverNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(txtDriverNum, gbc);

        JLabel lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        formPanel.add(lblKilometer, gbc);

        txtKilometer = new JTextField(20);
        txtKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(txtKilometer, gbc);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnAdd.setUI(new roundedButtonUI());
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(btnAdd, gbc);
        btnAdd.addActionListener(e -> addServiceRecord());

        add(formPanel, BorderLayout.CENTER);

        // Alt kısım: Exit butonu
        JPanel exitPanel = new JPanel();
        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(255, 0, 0)); // Kırmızı renk
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnExit.setPreferredSize(new Dimension(100, 30));
        btnExit.setUI(new roundedButtonUI());
        btnExit.addActionListener(e -> dispose());
        exitPanel.add(btnExit);
        add(exitPanel, BorderLayout.SOUTH);
    }

    private void addServiceRecord() {
        String carBrand = txtCarBrand.getText();
        String whatToDo = txtWhatToDo.getText();
        String driverName = txtDriverName.getText();
        String driverPhone = txtDriverNum.getText();
        int kilometer = Integer.parseInt(txtKilometer.getText());

        boolean isAdded = serviceRecordControl.addServiceRecord(carBrand, whatToDo, driverName, driverPhone, kilometer);
        if (isAdded) {
            JOptionPane.showMessageDialog(this, "Service record added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new serviceRecordsMenu().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add service record", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new createServiceEntryMenu().setVisible(true);
            }
        });
    }
}
