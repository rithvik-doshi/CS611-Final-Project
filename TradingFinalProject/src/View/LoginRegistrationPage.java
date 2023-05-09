package View;

import Model.CustomerLogin;
import Controller.CustomerPersonalAccountSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;

public class LoginRegistrationPage extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private static JButton backButton;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private CustomerLogin customerLogin;
    private EntryInterface entryInterface;
    private JButton backButtonLogin;
    private JButton backButtonRegister;
    private CustomerPersonalAccountSystem customerPersonalAccountSystem;



    public LoginRegistrationPage() {

        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/Customer.txt";
        customerLogin = new CustomerLogin(currentPath);

        setTitle("Login/Registration Page");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints loginConstraints = new GridBagConstraints();
        loginConstraints.insets = new Insets(5, 5, 5, 5);

        loginConstraints.gridx = 0;
        loginConstraints.gridy = 0;
        loginConstraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(new JLabel("Username: "), loginConstraints);

        loginConstraints.gridx = 1;
        loginConstraints.gridy = 0;
        loginConstraints.anchor = GridBagConstraints.WEST;
        usernameTextField = new JTextField(15);
        loginPanel.add(usernameTextField, loginConstraints);

        loginConstraints.gridx = 0;
        loginConstraints.gridy = 1;
        loginConstraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(new JLabel("Password: "), loginConstraints);

        loginConstraints.gridx = 1;
        loginConstraints.gridy = 1;
        loginConstraints.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(15);
        loginPanel.add(passwordField, loginConstraints);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        buttonPanel.add(loginButton);


        loginConstraints.gridx = 0;
        loginConstraints.gridy = 2;
        loginConstraints.gridwidth = 2;
        loginConstraints.anchor = GridBagConstraints.CENTER;
        loginPanel.add(buttonPanel, loginConstraints);

        JPanel registrationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        registrationPanel.add(new JLabel("Username: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        JTextField registerUsernameTextField = new JTextField(15);
        registrationPanel.add(registerUsernameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        registrationPanel.add(new JLabel("Email: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        JTextField registerEmailTextField = new JTextField(15);
        registrationPanel.add(registerEmailTextField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        registrationPanel.add(new JLabel("Password: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        JPasswordField registerPasswordField = new JPasswordField(15);
        registrationPanel.add(registerPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        registerButton = new JButton("Register");
        registrationPanel.add(registerButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Registration", registrationPanel);
        // tabbedPane.addTab("back", backButtonLogin);

        getContentPane().add(tabbedPane);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EntryInterface entryInterface = new EntryInterface();
                LoginRegistrationPage.this.setVisible(false);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());

                usernameTextField.setText("");
                passwordField.setText("");

                if (customerLogin.checkLogin(username, password)) {
//                    System.out.println("successful");
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    customerPersonalAccountSystem = customerLogin.getCustomerPersonalAccountSystem();
                    PersonalAccountView personalAccountView = new PersonalAccountView(customerPersonalAccountSystem);
                    personalAccountView.setVisible(true);


                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        String finalCurrentPath = currentPath;
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = registerUsernameTextField.getText();
                String email = registerEmailTextField.getText();
                String password = new String(registerPasswordField.getPassword());

                registerUsernameTextField.setText("");
                registerEmailTextField.setText("");
                registerPasswordField.setText("");

                int success = customerLogin.registerNewCustomer(name, email, password, finalCurrentPath);
                if (success == 1) {
                    JOptionPane.showMessageDialog(LoginRegistrationPage.this, "Registration successful!");
                } else if (success == -1) {
                    JOptionPane.showMessageDialog(LoginRegistrationPage.this, "Registration failed. Please try again.");
                } else if (success == 0) {
                    JOptionPane.showMessageDialog(LoginRegistrationPage.this, "This email has already been registered. Please try again, or log in via the login tab.");
                }
            }
        });

        backButtonLogin = new JButton("Back");
        backButtonLogin.addActionListener(e -> {
            LoginRegistrationPage.this.dispose();
            EntryInterface ui = new EntryInterface();
            ui.setVisible(true);
        });
        loginPanel.add(backButtonLogin);

        backButtonRegister = new JButton("Back");
        backButtonRegister.addActionListener(e -> {
            LoginRegistrationPage.this.dispose();
            EntryInterface ui = new EntryInterface();
            ui.setVisible(true);
        });
        registrationPanel.add(backButtonRegister);
    }

    public static void main(String[] args) {
        new LoginRegistrationPage();
    }
}

