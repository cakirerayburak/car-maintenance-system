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

public class deleteServiceEntryMenu extends JFrame {

    private JTextField txtDriverName;
    private JLabel lblBrand;
    private JLabel lblWhatToDo;
    private JLabel lblDriverName;
    private JLabel lblContactNum;
    private JLabel lblKilometer;
    private JPanel infoPanel;

    public deleteServiceEntryMenu() {
        setTitle("Delete Service Entry");
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
        JLabel lblTitle = new JLabel("DELETE SERVICE ENTRY");
        lblTitle.setBounds(315, 45, 480, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // lblDriverName ekle
        JLabel lblDriverNameLabel = new JLabel("Driver Name:");
        lblDriverNameLabel.setBounds(385, 180, 230, 30);
        lblDriverNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblDriverNameLabel.setForeground(Color.WHITE);
        formPanel.add(lblDriverNameLabel);

        txtDriverName = new JTextField();
        txtDriverName.setBounds(385, 220, 230, 30);
        txtDriverName.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(txtDriverName);

        // btnList ekle
        JButton btnList = new JButton("List");
        btnList.setBounds(385, 270, 230, 40);
        btnList.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnList.setForeground(Color.WHITE);
        btnList.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnList.setUI(new roundedButtonUI());
        formPanel.add(btnList);

        // infoPanel ekle
        infoPanel = new JPanel();
        infoPanel.setBounds(385, 330, 230, 180);
        infoPanel.setBackground(new Color(204, 255, 204));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Car and Kilometer Info"));
        infoPanel.setVisible(false); // Başlangıçta görünmez olacak
        formPanel.add(infoPanel);
        infoPanel.setLayout(null);

        lblBrand = new JLabel("Brand:");
        lblBrand.setBounds(0, 0, 0, 0);
        lblBrand.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblBrand);

        lblWhatToDo = new JLabel("What to do:");
        lblWhatToDo.setBounds(10, 22, 198, 19);
        lblWhatToDo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblWhatToDo);

        lblDriverName = new JLabel("Driver Name:");
        lblDriverName.setBounds(10, 51, 208, 19);
        lblDriverName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblDriverName);

        lblContactNum = new JLabel("Contact Num:");
        lblContactNum.setBounds(10, 80, 198, 19);
        lblContactNum.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblContactNum);

        lblKilometer = new JLabel("Kilometer:");
        lblKilometer.setBounds(232, 85, 64, 19);
        lblKilometer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        infoPanel.add(lblKilometer);

        getContentPane().add(formPanel, BorderLayout.CENTER);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(385, 520, 230, 40);
        formPanel.add(btnDelete);
        btnDelete.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnDelete.setPreferredSize(new Dimension(230, 40));
        btnDelete.setUI(new roundedButtonUI());
                
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(385, 585, 230, 40);
        formPanel.add(btnBack);
        btnBack.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnBack.setPreferredSize(new Dimension(230, 40));
        btnBack.setUI(new roundedButtonUI());
        
        btnBack.addActionListener(e -> {
            dispose();
            new serviceRecordsMenu().setVisible(true);
        });
        
	        btnDelete.addActionListener(e -> {
	                    // Silme işlemi burada gerçekleştirilecek
	                    JOptionPane.showMessageDialog(this, "Service entry deleted.");
	                    dispose();
	                    new serviceRecordsMenu().setVisible(true);
	                });

        // Alt kısım: Exit ve Delete butonları
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Butonlara tıklama işlemleri
        btnList.addActionListener(e -> listDriverInfo());
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
                new deleteServiceEntryMenu().setVisible(true);
            }
        });
    }
}