/**
 * @file signUpScreen.java
 * @brief This file contains the GUI class for the Sign Up screen in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @class signUpScreen
 * @brief Class for the Sign Up screen GUI in the car maintenance application.
 */
public class signUpScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    /**
     * @brief Constructor that initializes the sign up screen.
     */
    public signUpScreen() {
        setTitle("Sign Up");
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
        JLabel lblTitle = new JLabel("SIGN UP MENU");
        lblTitle.setBounds(400, 50, 280, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);
        formPanel.add(titlePanel);

        // lblUsername ekle
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(385, 180, 230, 30);
        lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblUsername.setForeground(Color.WHITE);
        formPanel.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(385, 220, 230, 30);
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(usernameField);

        // lblPassword ekle
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(385, 260, 230, 30);
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblPassword.setForeground(Color.WHITE);
        formPanel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(385, 300, 230, 30);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(passwordField);

        // lblEmail ekle
        JLabel lblEmail = new JLabel("E-Mail");
        lblEmail.setBounds(385, 340, 230, 30);
        lblEmail.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblEmail.setForeground(Color.WHITE);
        formPanel.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(385, 380, 230, 30);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        formPanel.add(emailField);

        // btnSignUp ekle
        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.setBounds(385, 420, 230, 40);
        btnSignUp.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnSignUp.setUI(new roundedButtonUI());
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();

                userControl control = new userControl();
                int result = control.registerUser(username, password, email);

                if (result == 0) {
                    JOptionPane.showMessageDialog(signUpScreen.this, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new signInScreen().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(signUpScreen.this, "Sign up failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        formPanel.add(btnSignUp);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Alt kısım: Back butonu
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(385, 470, 230, 60);
        btnBack.setBackground(new Color(173, 216, 230)); // Açık mavi renk
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnBack.setUI(new roundedButtonUI());
        btnBack.addActionListener(e -> {
            dispose();
            new signInScreen().setVisible(true);
        });
        formPanel.add(btnBack);
    }

    /**
     * @brief Main method to run the signUpScreen GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new signUpScreen().setVisible(true);
            }
        });
    }
}
