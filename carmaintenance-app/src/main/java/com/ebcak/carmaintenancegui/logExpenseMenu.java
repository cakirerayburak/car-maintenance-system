package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenanceumple.ServiceRecord;

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

public class logExpenseMenu extends JFrame {

    private JTextField txtDriverName;
    private JTextField txtFuelCost;
    private JTextField txtYearlyCost;
    private JTextField txtYearlyRepairCost;
    private JLabel lblBrand;
    private JLabel lblDriver;
    private JLabel lblKilometer;
    private JPanel infoPanel;
    private userControl userControlInstance;
    private ServiceRecord currentServiceRecord;

    public logExpenseMenu() {
        userControlInstance = new userControl();

        setTitle("Log Expense Menu");
        setSize(1000, 1000);
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
        JLabel lblTitle = new JLabel("LOG EXPENSE MENU");
        lblTitle.setBounds(320, 44, 400, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // lblDriverName ekle
        JLabel lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setBounds(385, 182, 230, 30);
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverName.setForeground(Color.WHITE);
        formPanel.add(lblDriverName);
 
        txtDriverName = new JTextField();
        txtDriverName.setBounds(385, 239, 230, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtDriverName);

        // btnList ekle
        JButton btnList = new JButton("List");
        btnList.setBounds(385, 301, 230, 40);
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        formPanel.add(btnList);

        // infoPanel ekle
        infoPanel = new JPanel();
        infoPanel.setBounds(100, 194, 230, 180);
        infoPanel.setBackground(new Color(204, 255, 204)); // Açık yeşil renk
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // Başlangıçta görünmez olacak
        formPanel.add(infoPanel);

        lblBrand = new JLabel("Brand:");
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDriver = new JLabel("Driver Name:");
        lblDriver.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));

        infoPanel.add(lblBrand);
        infoPanel.add(lblDriver);
        infoPanel.add(lblKilometer);

        // lblFuelCost ekle
        JLabel lblFuelCost = new JLabel("Fuel Cost:");
        lblFuelCost.setBounds(385, 400, 230, 30);
        lblFuelCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblFuelCost.setForeground(Color.WHITE);
        formPanel.add(lblFuelCost);

        txtFuelCost = new JTextField();
        txtFuelCost.setBounds(385, 451, 230, 30);
        txtFuelCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtFuelCost);

        // lblYearlyCost ekle
        JLabel lblYearlyCost = new JLabel("Yearly Cost:");
        lblYearlyCost.setBounds(385, 505, 230, 30);
        lblYearlyCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblYearlyCost.setForeground(Color.WHITE);
        formPanel.add(lblYearlyCost);

        txtYearlyCost = new JTextField();
        txtYearlyCost.setBounds(385, 545, 230, 30);
        txtYearlyCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtYearlyCost);

        // lblYearlyRepairCost ekle
        JLabel lblYearlyRepairCost = new JLabel("Yearly Repair Cost:");
        lblYearlyRepairCost.setBounds(385, 585, 230, 30);
        lblYearlyRepairCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblYearlyRepairCost.setForeground(Color.WHITE);
        formPanel.add(lblYearlyRepairCost);

        txtYearlyRepairCost = new JTextField();
        txtYearlyRepairCost.setBounds(385, 625, 230, 30);
        txtYearlyRepairCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtYearlyRepairCost);

        // btnAdd ekle
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(385, 665, 230, 40);
        btnAdd.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnAdd.setUI(new roundedButtonUI());
        formPanel.add(btnAdd);

        getContentPane().add(formPanel, BorderLayout.CENTER);
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(385, 725, 230, 40);
        formPanel.add(btnBack);
        btnBack.setBackground(new Color(238, 98, 3)); // Kırmızı renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setPreferredSize(new Dimension(230, 40));
        btnBack.setUI(new roundedButtonUI());
        btnBack.addActionListener(e -> dispose());

        // Alt kısım: Back butonu
        JPanel backPanel = new JPanel();
        getContentPane().add(backPanel, BorderLayout.SOUTH);

        // Butonlara tıklama işlemleri
        btnList.addActionListener(e -> listDriverInfo());
        btnAdd.addActionListener(e -> addExpense());
    }

    private void listDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        currentServiceRecord = userControlInstance.getServiceRecordByDriverName(driverName);
        if (currentServiceRecord == null) {
            JOptionPane.showMessageDialog(this, "Service record not found for driver: " + driverName);
            return;
        }

        lblBrand.setText("Brand: " + currentServiceRecord.getCarBrand());
        lblDriver.setText("Driver Name: " + currentServiceRecord.getDriverName());
        lblKilometer.setText("Kilometer: " + currentServiceRecord.getKilometer());

        infoPanel.setVisible(true); // Bilgileri göster
    }

    private void addExpense() {
        if (currentServiceRecord == null) {
            JOptionPane.showMessageDialog(this, "Please list the driver information first.");
            return;
        }

        try {
            double fuelCost = Double.parseDouble(txtFuelCost.getText());
            double yearlyCost = Double.parseDouble(txtYearlyCost.getText());
            double yearlyRepairCost = Double.parseDouble(txtYearlyRepairCost.getText());

            boolean result = logicJava.addExpenseReport(currentServiceRecord.getDriverName(), fuelCost, yearlyCost, yearlyRepairCost);

            if (result) {
                JOptionPane.showMessageDialog(this, "Expense logged successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to log expense.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for costs.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new logExpenseMenu().setVisible(true);
            }
        });
    }
}
