package com.ebcak.carmaintenancegui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class viewExpenseMenu extends JFrame {

    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblDriver;
    private JLabel lblKilometer;
    private JLabel lblYearlyCost;
    private JLabel lblFuelCost;
    private JLabel lblYearlyRepairCost;
    private JPanel infoPanel;

    public viewExpenseMenu() {
        setTitle("View Expense Menu");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("VIEW EXPENSE MENU");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Orta kısım: Form ve bilgi paneli
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblDriverName, gbc);

        txtDriverName = new JTextField(20); // Genişliği artır
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(txtDriverName, gbc);

        JButton btnList = new JButton("List");
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnList.setUI(new roundedButtonUI());
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(btnList, gbc);

        infoPanel = new JPanel();
        infoPanel.setBackground(new Color(204, 255, 204)); // Açık yeşil renk
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // İlk başta gizli

        lblBrand = new JLabel("Brand:");
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDriver = new JLabel("Driver Name:");
        lblDriver.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblYearlyCost = new JLabel("Yearly Cost:");
        lblYearlyCost.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblFuelCost = new JLabel("Fuel Cost:");
        lblFuelCost.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblYearlyRepairCost = new JLabel("Yearly Repair Cost:");
        lblYearlyRepairCost.setFont(new Font("SansSerif", Font.PLAIN, 14));

        infoPanel.add(lblBrand);
        infoPanel.add(lblDriver);
        infoPanel.add(lblKilometer);
        infoPanel.add(lblYearlyCost);
        infoPanel.add(lblFuelCost);
        infoPanel.add(lblYearlyRepairCost);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(infoPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Alt kısım: Back butonu
        JPanel backPanel = new JPanel();
        JButton btnBack = new JButton("Exit");
        btnBack.setBackground(new Color(255, 0, 0)); // Kırmızı renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnBack.setPreferredSize(new Dimension(100, 30));
        btnBack.setUI(new roundedButtonUI());
        backPanel.add(btnBack);
        add(backPanel, BorderLayout.SOUTH);

        // Butonlara tıklama işlemleri
        btnList.addActionListener(e -> listDriverInfo());
        btnBack.addActionListener(e -> dispose());
    }

    private void listDriverInfo() {
        // Bu örnekte, bilgileri manuel olarak giriyoruz. Gerçek uygulamalarda bu bilgiler veritabanından veya başka bir kaynaktan alınabilir.
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        // Örnek veriler
        String brand = "Toyota";
        String kilometer = "12000 km";
        String yearlyCost = "1000 USD";
        String fuelCost = "500 USD";
        String yearlyRepairCost = "200 USD";

        lblBrand.setText("Brand: " + brand);
        lblDriver.setText("Driver Name: " + driverName);
        lblKilometer.setText("Kilometer: " + kilometer);
        lblYearlyCost.setText("Yearly Cost: " + yearlyCost);
        lblFuelCost.setText("Fuel Cost: " + fuelCost);
        lblYearlyRepairCost.setText("Yearly Repair Cost: " + yearlyRepairCost);

        infoPanel.setVisible(true); // Bilgileri göster
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new viewExpenseMenu().setVisible(true);
            }
        });
    }
}
