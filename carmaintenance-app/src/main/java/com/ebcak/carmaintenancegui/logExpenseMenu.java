/**
 * @file logExpenseMenu.java
 * @brief This file contains the GUI class for logging an expense in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.ServiceRecord;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class logExpenseMenu extends JFrame {

    private JTextField txtDriverName;
    private JTextField txtFuelCost;
    private JTextField txtYearlyCost;
    private JTextField txtYearlyRepairCost;
    private JLabel lblBrand;
    private JLabel lblDriver;
    private JLabel lblKilometer;
    private JPanel infoPanel;
    public userControl userControlInstance; // Public for testing purposes
    private static logicJava.ExpenseReportLogic logicJavaInstance; // Static reference to logicJava

    /**
     * @brief Constructor for logExpenseMenu class to initialize the GUI components.
     */
    public logExpenseMenu() {
        userControlInstance = new userControl();

        setTitle("Log Expense Menu");
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
        txtDriverName.setName("txtDriverName");
        txtDriverName.setBounds(385, 239, 230, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtDriverName);

        // btnList ekle
        JButton btnList = new JButton("List");
        btnList.setName("btnList");
        btnList.setBounds(385, 301, 230, 40);
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        formPanel.add(btnList);

        // infoPanel ekle
        infoPanel = new JPanel();
        infoPanel.setName("infoPanel");
        infoPanel.setBounds(100, 194, 230, 180);
        infoPanel.setBackground(new Color(204, 255, 204)); // Açık yeşil renk
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // Başlangıçta görünmez olacak
        formPanel.add(infoPanel);

        lblBrand = new JLabel("Brand:");
        lblBrand.setName("lblBrand");
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDriver = new JLabel("Driver Name:");
        lblDriver.setName("lblDriver");
        lblDriver.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setName("lblKilometer");
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
        txtFuelCost.setName("txtFuelCost");
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
        txtYearlyCost.setName("txtYearlyCost");
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
        txtYearlyRepairCost.setName("txtYearlyRepairCost");
        txtYearlyRepairCost.setBounds(385, 625, 230, 30);
        txtYearlyRepairCost.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtYearlyRepairCost);

        // btnAdd ekle
        JButton btnAdd = new JButton("Add");
        btnAdd.setName("btnAdd");
        btnAdd.setBounds(385, 665, 230, 40);
        btnAdd.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnAdd.setUI(new roundedButtonUI());
        formPanel.add(btnAdd);

        getContentPane().add(formPanel, BorderLayout.CENTER);
        JButton btnBack = new JButton("Back");
        btnBack.setName("btnBack");
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

    /**
     * @brief Lists the driver information based on the entered driver name.
     */
    private void listDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            showAutoClosingDialog("Please enter a driver name.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ServiceRecord currentServiceRecord = userControlInstance.getServiceRecordByDriverName(driverName);
        if (currentServiceRecord == null) {
            showAutoClosingDialog("Service record not found for driver: " + driverName, "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        lblBrand.setText("Brand: " + currentServiceRecord.getCarBrand());
        lblDriver.setText("Driver Name: " + currentServiceRecord.getDriverName());
        lblKilometer.setText("Kilometer: " + currentServiceRecord.getKilometer());

        infoPanel.setVisible(true); // Bilgileri göster
    }

    /**
     * @brief Adds an expense based on the entered details.
     */
    private void addExpense() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            showAutoClosingDialog("Please enter a driver name.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ServiceRecord currentServiceRecord = userControlInstance.getServiceRecordByDriverName(driverName);
        if (currentServiceRecord == null) {
            showAutoClosingDialog("Please list the driver information first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double fuelCost = Double.parseDouble(txtFuelCost.getText());
            double yearlyCost = Double.parseDouble(txtYearlyCost.getText());
            double yearlyRepairCost = Double.parseDouble(txtYearlyRepairCost.getText());

            boolean result = logicJavaInstance.addExpenseReport(currentServiceRecord.getDriverName(), fuelCost, yearlyCost, yearlyRepairCost);

            if (result) {
                showAutoClosingDialog("Expense logged successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showAutoClosingDialog("Failed to log expense.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            showAutoClosingDialog("Please enter valid numeric values for costs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @brief Shows a dialog that automatically closes after a specified time.
     * @param message The message to display in the dialog.
     * @param title The title of the dialog.
     * @param messageType The type of message to display.
     */
    private void showAutoClosingDialog(String message, String title, int messageType) {
        final JDialog dialog = new JOptionPane(message, messageType).createDialog(this, title);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Timer timer = new Timer(1500, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    /**
     * @brief Sets the logicJava instance for expense report operations.
     * @param instance The logicJava.ExpenseReportLogic instance.
     */
    public static void setLogicJavaInstance(logicJava.ExpenseReportLogic instance) {
        logicJavaInstance = instance;
    }

    /**
     * @brief Main method to run the logExpenseMenu GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new logExpenseMenu().setVisible(true));
    }
}
