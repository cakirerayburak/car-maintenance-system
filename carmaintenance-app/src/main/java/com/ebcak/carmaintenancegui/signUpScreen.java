package com.ebcak.carmaintenancegui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class signUpScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public signUpScreen() {
        setTitle("Sign Up");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("SIGN UP MENU");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Orta kısım: Giriş formu
        JPanel formPanel = new JPanel(new GridBagLayout());

        // lblUsername ekle
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
        GridBagConstraints gbcUsername = new GridBagConstraints();
        gbcUsername.insets = new Insets(10, 10, 10, 10);
        gbcUsername.fill = GridBagConstraints.HORIZONTAL;
        gbcUsername.gridx = 0;
        gbcUsername.gridy = 0;
        formPanel.add(lblUsername, gbcUsername);

        usernameField = new JTextField();
        GridBagConstraints gbcUsernameField = new GridBagConstraints();
        gbcUsernameField.insets = new Insets(10, 10, 10, 10);
        gbcUsernameField.fill = GridBagConstraints.HORIZONTAL;
        gbcUsernameField.gridx = 1;
        gbcUsernameField.gridy = 0;
        formPanel.add(usernameField, gbcUsernameField);

        // lblPassword ekle
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
        GridBagConstraints gbcPassword = new GridBagConstraints();
        gbcPassword.insets = new Insets(10, 10, 10, 10);
        gbcPassword.fill = GridBagConstraints.HORIZONTAL;
        gbcPassword.gridx = 0;
        gbcPassword.gridy = 1;
        formPanel.add(lblPassword, gbcPassword);

        passwordField = new JPasswordField();
        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.insets = new Insets(10, 10, 10, 10);
        gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 1;
        formPanel.add(passwordField, gbcPasswordField);

        // lblEmail ekle
        JLabel lblEmail = new JLabel("E-Mail");
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
        GridBagConstraints gbcEmail = new GridBagConstraints();
        gbcEmail.insets = new Insets(10, 10, 10, 10);
        gbcEmail.fill = GridBagConstraints.HORIZONTAL;
        gbcEmail.gridx = 0;
        gbcEmail.gridy = 2;
        formPanel.add(lblEmail, gbcEmail);

        emailField = new JTextField();
        GridBagConstraints gbcEmailField = new GridBagConstraints();
        gbcEmailField.insets = new Insets(10, 10, 10, 10);
        gbcEmailField.fill = GridBagConstraints.HORIZONTAL;
        gbcEmailField.gridx = 1;
        gbcEmailField.gridy = 2;
        formPanel.add(emailField, gbcEmailField);

        // btnSignUp ekle
        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFont(new Font("SansSerif", Font.PLAIN, 16));
        GridBagConstraints gbcBtnSignUp = new GridBagConstraints();
        gbcBtnSignUp.insets = new Insets(10, 10, 10, 10);
        gbcBtnSignUp.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnSignUp.gridx = 1;
        gbcBtnSignUp.gridy = 3;
        formPanel.add(btnSignUp, gbcBtnSignUp);

        add(formPanel, BorderLayout.CENTER);

        // Geri butonu ekle
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 14));
        backPanel.add(btnBack);
        add(backPanel, BorderLayout.SOUTH);

        // Butona tıklama işlemi ekleyin
        btnBack.addActionListener(e -> {
            // Geri butonuna basıldığında yapılacak işlemler
            // Örneğin, mevcut pencereyi kapatıp önceki pencereyi açma
            dispose();
            new signInScreen().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new signUpScreen().setVisible(true);
            }
        });
    }
}
