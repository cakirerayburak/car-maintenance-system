/**
 * @file viewExpenseMenu.java
 * @brief This file contains the class for viewing expense reports.
 */

package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenanceumple.ExpenseReport;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @class viewExpenseMenu
 * @brief Class for viewing expense reports of a driver.
 */
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

    /**
     * @brief Constructor for initializing the view expense menu.
     */
    public viewExpenseMenu() {
        userCtrl = new userControl();

        setTitle("View Expense Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Set background color
        getContentPane().setBackground(new Color(0x023F5C));

        // Add title panel
        roundedPanelUI titlePanel = new roundedPanelUI(30); // Rounded corners with radius 30
        titlePanel.setBounds(20, 25, 950, 130);
        titlePanel.setBackground(new Color(24, 154, 180)); // Turquoise color
        titlePanel.setLayout(null);
        JLabel lblTitle = new JLabel("VIEW EXPENSE MENU");
        lblTitle.setBounds(375, 44, 280, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Transparent background
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // Add driver name label
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

        // Add list button
        JButton btnList = new JButton("List");
        btnList.setBounds(625, 220, 100, 30);
        btnList.setBackground(new Color(0, 51, 153)); // Blue color
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        btnList.setName("btnList"); // Set name
        formPanel.add(btnList);

        // Add info panel
        infoPanel = new JPanel();
        infoPanel.setBounds(385, 270, 340, 300);
        infoPanel.setBackground(new Color(204, 255, 204)); // Light green color
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // Initially hidden
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

        // Add back button
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(380, 580, 345, 56);
        btnBack.setBackground(new Color(173, 216, 230)); // Light blue color
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnBack.setUI(new roundedButtonUI());
        btnBack.setName("btnBack"); // Set name
        formPanel.add(btnBack);

        // Button action listeners
        btnList.addActionListener(e -> listDriverInfo());
        btnBack.addActionListener(e -> {
            dispose();
            new expenseRecordsMenu().setVisible(true);
        });
    }

    /**
     * @brief Lists driver information and expense reports.
     */
    private void listDriverInfo() {
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            showAutoClosingDialog("Please enter a driver name.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<ExpenseReport> expenseReports = userCtrl.getExpenseReportsByDriverName(driverName);
        if (expenseReports.isEmpty()) {
            showAutoClosingDialog("No expense reports found for the given driver name.", "Information", JOptionPane.INFORMATION_MESSAGE);
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
     * @brief Main method to run the view expense menu.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new viewExpenseMenu().setVisible(true));
    }
}
