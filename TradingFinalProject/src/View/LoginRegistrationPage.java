package View;
import Model.CustomerLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;

public class LoginRegistrationPage extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private CustomerLogin customerLogin;


    public LoginRegistrationPage() {

        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/Customer.txt";
        customerLogin = new CustomerLogin(currentPath);

// Set up the frame
        setTitle("Login/Registration Page");
        setSize(700, 400); // Increase the size to make the window larger
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        // Create the login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints loginConstraints = new GridBagConstraints();
        loginConstraints.insets = new Insets(5, 5, 5, 5); // Add padding

        // Add Username label and text field
        loginConstraints.gridx = 0;
        loginConstraints.gridy = 0;
        loginConstraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(new JLabel("Username: "), loginConstraints);

        loginConstraints.gridx = 1;
        loginConstraints.gridy = 0;
        loginConstraints.anchor = GridBagConstraints.WEST;
        usernameTextField = new JTextField(15);
        loginPanel.add(usernameTextField, loginConstraints);

        // Add Password label and password field
        loginConstraints.gridx = 0;
        loginConstraints.gridy = 1;
        loginConstraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(new JLabel("Password: "), loginConstraints);

        loginConstraints.gridx = 1;
        loginConstraints.gridy = 1;
        loginConstraints.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(15);
        loginPanel.add(passwordField, loginConstraints);

        // Add Login button
        loginConstraints.gridx = 0;
        loginConstraints.gridy = 2;
        loginConstraints.gridwidth = 2;
        loginConstraints.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginPanel.add(loginButton, loginConstraints);

// Create the registration panel
        JPanel registrationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

// Add Username label and text field
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        registrationPanel.add(new JLabel("Username: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        JTextField registerUsernameTextField = new JTextField(15);
        registrationPanel.add(registerUsernameTextField, constraints);

// Add Email label and text field
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        registrationPanel.add(new JLabel("Email: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        JTextField registerEmailTextField = new JTextField(15);
        registrationPanel.add(registerEmailTextField, constraints);

// Add Password label and password field
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        registrationPanel.add(new JLabel("Password: "), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        JPasswordField registerPasswordField = new JPasswordField(15);
        registrationPanel.add(registerPasswordField, constraints);

// Add Register button
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        registerButton = new JButton("Register");
        registrationPanel.add(registerButton, constraints);

        // Create the tabbed pane and add the login and registration panels to it
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Registration", registrationPanel);

        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane);


        // Add action listeners to the buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Login button clicked
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the login is successful
                if (customerLogin.checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        String finalCurrentPath = currentPath;
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Register button clicked
                String name = registerUsernameTextField.getText();
                String email = registerEmailTextField.getText(); // You'll need to add a JTextField for email input
                String password = new String(registerPasswordField.getPassword());
                boolean success = customerLogin.registerNewCustomer(name, email, password, finalCurrentPath);
                if (success) {
                    JOptionPane.showMessageDialog(LoginRegistrationPage.this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginRegistrationPage.this, "Registration failed. Please try again.");
                }
            }

        });
    }

    public static void main(String[] args) {
        new LoginRegistrationPage();
    }
}
