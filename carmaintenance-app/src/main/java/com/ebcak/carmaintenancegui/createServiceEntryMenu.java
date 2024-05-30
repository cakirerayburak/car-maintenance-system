package com.ebcak.carmaintenancegui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createServiceEntryMenu extends JFrame {

    private JTextField txtCarBrand;
    private JTextField txtWhatToDo;
    private JTextField txtDriverName;
    private JTextField txtDriverNum;
    private JTextField txtKilometer;
    public userControl userControlInstance;

    public createServiceEntryMenu() {
        userControlInstance = new userControl();
        
        setTitle("Create Service Entry");
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
        JLabel lblTitle = new JLabel("CREATE SERVICE ENTRY");
        lblTitle.setBounds(321, 46, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // lblCarBrand ekle
        JLabel lblCarBrand = new JLabel("Car Brand:");
        lblCarBrand.setBounds(385, 165, 230, 30);
        lblCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblCarBrand.setForeground(Color.WHITE);
        formPanel.add(lblCarBrand);

        txtCarBrand = new JTextField();
        txtCarBrand.setBounds(385, 205, 230, 30);
        txtCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtCarBrand.setName("txtCarBrand"); // Set name for testing
        formPanel.add(txtCarBrand);

        // lblWhatToDo ekle
        JLabel lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setBounds(385, 245, 230, 30);
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblWhatToDo.setForeground(Color.WHITE);
        formPanel.add(lblWhatToDo);

        txtWhatToDo = new JTextField();
        txtWhatToDo.setBounds(385, 285, 230, 30);
        txtWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtWhatToDo.setName("txtWhatToDo"); // Set name for testing
        formPanel.add(txtWhatToDo);

        // lblDriverName ekle
        JLabel lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setBounds(385, 325, 230, 30);
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverName.setForeground(Color.WHITE);
        formPanel.add(lblDriverName);

        txtDriverName = new JTextField();
        txtDriverName.setBounds(385, 365, 230, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtDriverName.setName("txtDriverName"); // Set name for testing
        formPanel.add(txtDriverName);

        // lblDriverNum ekle
        JLabel lblDriverNum = new JLabel("Driver Num:");
        lblDriverNum.setBounds(385, 405, 230, 30);
        lblDriverNum.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverNum.setForeground(Color.WHITE);
        formPanel.add(lblDriverNum);

        txtDriverNum = new JTextField();
        txtDriverNum.setBounds(385, 445, 230, 30);
        txtDriverNum.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtDriverNum.setName("txtDriverNum"); // Set name for testing
        formPanel.add(txtDriverNum);

        // lblKilometer ekle
        JLabel lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setBounds(385, 485, 230, 30);
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblKilometer.setForeground(Color.WHITE);
        formPanel.add(lblKilometer);

        txtKilometer = new JTextField();
        txtKilometer.setBounds(385, 525, 230, 30);
        txtKilometer.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtKilometer.setName("txtKilometer"); // Set name for testing
        formPanel.add(txtKilometer);

        // btnAdd ekle
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(385, 565, 230, 40);
        btnAdd.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnAdd.setUI(new roundedButtonUI());
        btnAdd.setName("btnAdd"); // Set name for testing
        formPanel.add(btnAdd);

        // btnBack ekle
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(385, 615, 230, 40); // btnBack butonunu farklı bir konuma yerleştir
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setUI(new roundedButtonUI());
        btnBack.setName("btnBack"); // Set name for testing
        formPanel.add(btnBack);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Back butonuna tıklama işlemi
        btnBack.addActionListener(e -> {
            dispose();
            new serviceRecordsMenu().setVisible(true);
        });

        // Add butonuna tıklama işlemi
        btnAdd.addActionListener(e -> {
            addServiceEntry();
        });
    }

    private void addServiceEntry() {
        String carBrand = txtCarBrand.getText();
        String whatToDo = txtWhatToDo.getText();
        String driverName = txtDriverName.getText();
        String driverNum = txtDriverNum.getText();
        int kilometer;

        try {
            kilometer = Integer.parseInt(txtKilometer.getText());
        } catch (NumberFormatException e) {
            showAutoClosingDialog("Kilometer must be a number.", 1500);
            return;
        }

        boolean isAdded = userControlInstance.addServiceRecord(carBrand, whatToDo, driverName, driverNum, kilometer);

        if (isAdded) {
            showAutoClosingDialog("Service entry added successfully.", 1500);
        } else {
            showAutoClosingDialog("Failed to add service entry.", 1500);
        }
    }

    private void showAutoClosingDialog(String message, int milliseconds) {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = optionPane.createDialog(this, "Message");

        Timer timer = new Timer(milliseconds, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start();
        dialog.setVisible(true);
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
