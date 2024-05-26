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

public class searchEntryMenu extends JFrame {

    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblWhatToDo;
    private JLabel lblDriverName;
    private JLabel lblContactNum;
    private JLabel lblKilometer;
    private JPanel infoPanel;

    public searchEntryMenu() {
        setTitle("Search Entry Menu");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Geri butonu ve başlık
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton btnBack = new JButton("← Back");
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setUI(new roundedButtonUI());
        btnBack.addActionListener(e -> {
            dispose();
            new serviceRecordsMenu().setVisible(true);
        });
        topPanel.add(btnBack, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("SEARCH ENTRY MENU");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        topPanel.add(titlePanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Orta kısım: Form ve bilgi paneli
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblDriverNameLabel = new JLabel("Driver Name:");
        lblDriverNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblDriverNameLabel, gbc);

        txtDriverName = new JTextField(20);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(txtDriverName, gbc);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnSearch.setUI(new roundedButtonUI());
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(btnSearch, gbc);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // İlk başta gizli

        lblBrand = new JLabel("Brand:");
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        infoPanel.add(lblBrand, gbc);

        lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        infoPanel.add(lblWhatToDo, gbc);

        lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        infoPanel.add(lblDriverName, gbc);

        lblContactNum = new JLabel("Contact Num:");
        lblContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        infoPanel.add(lblContactNum, gbc);

        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        infoPanel.add(lblKilometer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(new JLabel(), gbc); // Dummy component to keep spacing

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        infoPanel.add(new JLabel(), gbc); // Dummy component to keep spacing

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        infoPanel.add(new JLabel(), gbc); // Dummy component to keep spacing

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        infoPanel.add(new JLabel(), gbc); // Dummy component to keep spacing

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        infoPanel.add(new JLabel(), gbc); // Dummy component to keep spacing

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(infoPanel, gbc);

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

        // Butonlara tıklama işlemleri
        btnSearch.addActionListener(e -> searchDriverInfo());
    }

    private void searchDriverInfo() {
        // Bu örnekte, bilgileri manuel olarak giriyoruz. Gerçek uygulamalarda bu bilgiler veritabanından veya başka bir kaynaktan alınabilir.
        String driverName = txtDriverName.getText();
        if (driverName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a driver name.");
            return;
        }

        // Örnek veriler
        String brand = "Toyota";
        String whatToDo = "Oil Change";
        String contactNum = "123-456-7890";
        String kilometer = "15000";

        lblBrand.setText("Brand: " + brand);
        lblWhatToDo.setText("What to do: " + whatToDo);
        lblDriverName.setText("Driver Name: " + driverName);
        lblContactNum.setText("Contact Num: " + contactNum);
        lblKilometer.setText("Kilometer: " + kilometer);

        infoPanel.setVisible(true); // Bilgileri göster
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new searchEntryMenu().setVisible(true);
            }
        });
    }
}
