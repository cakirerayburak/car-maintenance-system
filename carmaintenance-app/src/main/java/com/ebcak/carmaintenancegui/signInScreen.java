package com.ebcak.carmaintenancegui;

import javax.swing.*;
import java.awt.*;

public class signInScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private userControl userController;

    public signInScreen() {
        userController = new userControl();

        setTitle("Sign In");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Üst kısım: Başlık
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 204, 0)); // Sarı renk
        JLabel lblTitle = new JLabel("WELCOME TO THE CAR MAINTENANCE SERVICE");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Orta kısım: Giriş formu
        JPanel formPanel = new JPanel(new GridBagLayout());

        // lblSignIn ekle
        JLabel lblSignIn = new JLabel("Sign In");
        lblSignIn.setFont(new Font("SansSerif", Font.BOLD, 18));
        GridBagConstraints gbcSignIn = new GridBagConstraints();
        gbcSignIn.insets = new Insets(10, 10, 10, 10);
        gbcSignIn.fill = GridBagConstraints.HORIZONTAL;
        gbcSignIn.gridx = 0;
        gbcSignIn.gridy = 0;
        gbcSignIn.gridwidth = 2;
        formPanel.add(lblSignIn, gbcSignIn);

        // lblUsername ekle
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 14));
        GridBagConstraints gbcUsername = new GridBagConstraints();
        gbcUsername.insets = new Insets(10, 10, 10, 10);
        gbcUsername.fill = GridBagConstraints.HORIZONTAL;
        gbcUsername.gridx = 0;
        gbcUsername.gridy = 1;
        formPanel.add(lblUsername, gbcUsername);

        usernameField = new JTextField();
        GridBagConstraints gbcUsernameField = new GridBagConstraints();
        gbcUsernameField.insets = new Insets(10, 10, 10, 10);
        gbcUsernameField.fill = GridBagConstraints.HORIZONTAL;
        gbcUsernameField.gridx = 1;
        gbcUsernameField.gridy = 1;
        formPanel.add(usernameField, gbcUsernameField);

        // lblPassword ekle
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
        GridBagConstraints gbcPassword = new GridBagConstraints();
        gbcPassword.insets = new Insets(10, 10, 10, 10);
        gbcPassword.fill = GridBagConstraints.HORIZONTAL;
        gbcPassword.gridx = 0;
        gbcPassword.gridy = 2;
        formPanel.add(lblPassword, gbcPassword);

        passwordField = new JPasswordField();
        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.insets = new Insets(10, 10, 10, 10);
        gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 2;
        formPanel.add(passwordField, gbcPasswordField);

        // btnSignIn ekle
        JButton btnSignIn = new JButton("Sign in");
        btnSignIn.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        GridBagConstraints gbcBtnSignIn = new GridBagConstraints();
        gbcBtnSignIn.insets = new Insets(10, 10, 10, 10);
        gbcBtnSignIn.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnSignIn.gridx = 1;
        gbcBtnSignIn.gridy = 3;
        formPanel.add(btnSignIn, gbcBtnSignIn);  

        // btnSignUp ekle
        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnSignUp.setForeground(new Color(0, 102, 204)); // Mavi renk
        btnSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcBtnSignUp = new GridBagConstraints();
        gbcBtnSignUp.insets = new Insets(10, 10, 10, 10);
        gbcBtnSignUp.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnSignUp.gridx = 1;
        gbcBtnSignUp.gridy = 4;
        formPanel.add(btnSignUp, gbcBtnSignUp);

        add(formPanel, BorderLayout.CENTER);

        // Sign in butonuna tıklama işlemi
        btnSignIn.addActionListener(e -> {
            performSignIn();
        });

        // Sign up butonuna tıklama işlemi
        btnSignUp.addActionListener(e -> {
            dispose();
            new signUpScreen().setVisible(true);
        });
    }

    private void performSignIn() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userController.loginUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Proceed to the next screen or application functionality
            dispose();
            new carServiceMenuScreen().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new signInScreen().setVisible(true);
            }
        });
    }
}
