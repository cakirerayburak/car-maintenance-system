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
        setSize(500, 500); // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık ve geri butonu
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 204, 0)); // Sarı renk
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
        JLabel lblTitle = new JLabel("EDIT SERVICE ENTRY");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        topPanel.add(lblTitle, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

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

        txtDriverName = new JTextField(20);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(txtDriverName, gbc);

        JButton btnShow = new JButton("Show");
        btnShow.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnShow.setForeground(Color.WHITE);
        btnShow.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnShow.setUI(new roundedButtonUI());
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(btnShow, gbc);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Driver Info"));
        infoPanel.setVisible(false); // İlk başta gizli

        JLabel lblCarBrand = new JLabel("Car Brand:");
        lblCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        infoPanel.add(lblCarBrand, gbc);

        txtCarBrand = new JTextField(20);
        txtCarBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(txtCarBrand, gbc);

        JLabel lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        infoPanel.add(lblWhatToDo, gbc);

        txtWhatToDo = new JTextField(20);
        txtWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        infoPanel.add(txtWhatToDo, gbc);

        JLabel lblDriverNameEdit = new JLabel("Driver Name:");
        lblDriverNameEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        infoPanel.add(lblDriverNameEdit, gbc);

        txtDriverNameEdit = new JTextField(20);
        txtDriverNameEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        infoPanel.add(txtDriverNameEdit, gbc);

        JLabel lblContactNum = new JLabel("Contact Num:");
        lblContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        infoPanel.add(lblContactNum, gbc);

        txtContactNum = new JTextField(20);
        txtContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        infoPanel.add(txtContactNum, gbc);

        JLabel lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        infoPanel.add(lblKilometer, gbc);

        txtKilometer = new JTextField(20);
        txtKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        infoPanel.add(txtKilometer, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(infoPanel, gbc);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnEdit.setUI(new roundedButtonUI());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(btnEdit, gbc);

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
        btnShow.addActionListener(e -> showDriverInfo());
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

