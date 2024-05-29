package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenanceumple.ExpenseReport;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class viewExpenseMenu extends JFrame {

    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblDriver;
    private JLabel lblKilometer;
    private JLabel lblYearlyCost;
    private JLabel lblFuelCost;
    private JLabel lblYearlyRepairCost;
    private JPanel infoPanel;
    public userControl userCtrl; // Made public for testing purposes

    public viewExpenseMenu() {
        userCtrl = new userControl();

        setTitle("View Expense Menu");
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
        JLabel lblTitle = new JLabel("VIEW EXPENSE MENU");
        lblTitle.setBounds(375, 44, 280, 32);
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
        txtDriverName.setName("txtDriverName"); // Set name
        formPanel.add(txtDriverName);

        // btnList ekle
        JButton btnList = new JButton("List");
        btnList.setBounds(625, 220, 100, 30);
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        btnList.setName("btnList"); // Set name
        formPanel.add(btnList);

        // Bilgi paneli ekle
        infoPanel = new JPanel();
        infoPanel.setBounds(385, 270, 340, 300);
        infoPanel.setBackground(new Color(204, 255, 204)); // Açık yeşil renk
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // İlk başta gizli
        infoPanel.setName("infoPanel"); // Set name

        lblBrand = new JLabel("Brand:");
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblBrand.setName("lblBrand"); // Set name
        lblDriver = new JLabel("Driver Name:");
        lblDriver.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriver.setName("lblDriver"); // Set name
        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblKilometer.setName("lblKilometer"); // Set name
        lblYearlyCost = new JLabel("Yearly Cost:");
        lblYearlyCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblYearlyCost.setName("lblYearlyCost"); // Set name
        lblFuelCost = new JLabel("Fuel Cost:");
        lblFuelCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblFuelCost.setName("lblFuelCost"); // Set name
        lblYearlyRepairCost = new JLabel("Yearly Repair Cost:");
        lblYearlyRepairCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblYearlyRepairCost.setName("lblYearlyRepairCost"); // Set name

        infoPanel.add(lblBrand);
        infoPanel.add(lblDriver);
        infoPanel.add(lblKilometer);
        infoPanel.add(lblYearlyCost);
        infoPanel.add(lblFuelCost);
        infoPanel.add(lblYearlyRepairCost);

        formPanel.add(infoPanel);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Alt kısım: Back butonu
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(380, 580, 345, 56);
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnBack.setUI(new roundedButtonUI());
        btnBack.setName("btnBack"); // Set name
        formPanel.add(btnBack);

        // Butonlara tıklama işlemleri
        btnList.addActionListener(e -> listDriverInfo());
        btnBack.addActionListener(e -> {
            dispose();
            new expenseRecordsMenu().setVisible(true);
        });
    }

    private void listDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        List<ExpenseReport> expenseReports = userCtrl.getExpenseReportsByDriverName(driverName);
        if (expenseReports.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No expense reports found for the given driver name.");
            return;
        }

        ExpenseReport report = expenseReports.get(0); // Displaying the first report for simplicity
        lblBrand.setText("Brand: " + report.getServiceRecord().getCarBrand());
        lblDriver.setText("Driver Name: " + report.getServiceRecord().getDriverName());
        lblKilometer.setText("Kilometer: " + report.getServiceRecord().getKilometer());
        lblYearlyCost.setText("Yearly Cost: " + report.getTotalCost());
        lblFuelCost.setText("Fuel Cost: " + report.getDailyFuel());
        lblYearlyRepairCost.setText("Yearly Repair Cost: " + report.getAnnualFuel());

        infoPanel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new viewExpenseMenu().setVisible(true));
    }
}
