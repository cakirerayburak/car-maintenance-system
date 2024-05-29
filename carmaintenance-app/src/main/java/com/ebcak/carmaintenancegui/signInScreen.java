package com.ebcak.carmaintenancegui;

import javax.swing.*;
import java.awt.*;

public class signInScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private userControl userController;
    private boolean nextScreenVisible = false;
    private boolean signUpScreenVisible = false;

    // Varsayılan yapıcı (parametresiz)
    public signInScreen() {
        this(new userControl()); // Varsayılan userControl ile oluştur
    }

    // userControl parametresi alan yapıcı
    public signInScreen(userControl userController) {
        this.userController = userController;

        setTitle("Sign In");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Uygulamanın arka plan rengini ayarla
        getContentPane().setBackground(new Color(0x023F5C));

        // Orta kısım: Giriş formu
        JPanel formPanel = new JPanel();
        formPanel.setForeground(new Color(4, 2, 0));
        formPanel.setOpaque(false); // Arka planın görünmesini sağlamak için paneli şeffaf yap
        formPanel.setLayout(null);

        // lblSignIn ekle
        JLabel lblSignIn = new JLabel("          Sign In");
        lblSignIn.setBounds(385, 175, 204, 70);
        lblSignIn.setFont(new Font("SansSerif", Font.BOLD, 22)); // "Sign In" font boyutunu artırdım
        lblSignIn.setForeground(Color.WHITE);
        formPanel.add(lblSignIn);

        // lblUsername ekle
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(385, 255, 99, 35);
        lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 18)); // "Username" font boyutunu artırdım
        lblUsername.setForeground(Color.WHITE);
        formPanel.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(385, 300, 204, 30);
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        usernameField.setName("usernameField"); // Set name for testing
        formPanel.add(usernameField);

        // lblPassword ekle
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(385, 355, 204, 36);
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18)); // "Password" font boyutunu artırdım
        lblPassword.setForeground(Color.WHITE);
        formPanel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(385, 411, 204, 30);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        passwordField.setName("passwordField"); // Set name for testing
        formPanel.add(passwordField);

        // btnSignIn ekle
        JButton btnSignIn = new JButton("Sign in");
        btnSignIn.setBounds(385, 466, 204, 34);
        btnSignIn.setBackground(new Color(0, 51, 153)); // Mavi renk
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.setFont(new Font("SansSerif", Font.PLAIN, 18)); // btnSignIn font boyutunu artırdım
        btnSignIn.setUI(new roundedButtonUI());
        btnSignIn.setName("btnSignIn"); // Set name for testing
        formPanel.add(btnSignIn);

        // btnSignUp ekle
        JButton btnSignUp = new JButton("Sign up");
        btnSignUp.setBounds(385, 526, 204, 34);
        btnSignUp.setFont(new Font("SansSerif", Font.PLAIN, 18)); // btnSignUp font boyutunu artırdım
        btnSignUp.setForeground(new Color(0, 102, 204)); // Mavi renk
        btnSignUp.setBackground(new Color(173, 216, 230)); // Açık mavi arka plan rengi
        btnSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        btnSignUp.setUI(new roundedButtonUI());
        btnSignUp.setName("btnSignUp"); // Set name for testing
        formPanel.add(btnSignUp);

        getContentPane().add(formPanel, BorderLayout.CENTER);

        // Üst kısım: Başlık
        roundedPanelUI titlePanel = new roundedPanelUI(30); // Yuvarlak köşe yarıçapı
        titlePanel.setBounds(20, 25, 950, 130);
        formPanel.add(titlePanel);
        titlePanel.setBackground(new Color(24, 154, 180)); // Sarı renk
        titlePanel.setLayout(null);
        JLabel lblTitle = new JLabel("WELCOME TO THE CAR MAINTENANCE SERVICE");
        lblTitle.setBounds(213, 44, 573, 32);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24)); // Başlık font boyutunu artırdım
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        // Sign in butonuna tıklama işlemi
        btnSignIn.addActionListener(e -> {
            performSignIn();
        });

        // Sign up butonuna tıklama işlemi
        btnSignUp.addActionListener(e -> {
            performSignUp();
        });
    }

    private void performSignIn() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userController.loginUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            nextScreenVisible = true;
            dispose();
            new carServiceMenuScreen().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
            nextScreenVisible = false;
        }
    }

    private void performSignUp() {
        dispose();
        signUpScreenVisible = true;
        new signUpScreen().setVisible(true);
    }

    public boolean isNextScreenVisible() {
        return nextScreenVisible;
    }

    public boolean isSignUpScreenVisible() {
        return signUpScreenVisible;
    }

    public static void main(String[] args) {
        userControl userController = new userControl();
        SwingUtilities.invokeLater(() -> new signInScreen(userController).setVisible(true));
    }
}
