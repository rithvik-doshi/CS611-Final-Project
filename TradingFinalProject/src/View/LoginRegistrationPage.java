package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginRegistrationPage extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameTextField;
    private JPasswordField passwordField;

    public LoginRegistrationPage() {
        // Create the login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(new JLabel("Username: "));
        usernameTextField = new JTextField();
        loginPanel.add(usernameTextField);
        loginPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginButton = new JButton("Login");
        loginPanel.add(loginButton);

        // Create the registration panel
        JPanel registrationPanel = new JPanel(new GridLayout(3, 2));
        registrationPanel.add(new JLabel("Username: "));
        JTextField registerUsernameTextField = new JTextField();
        registrationPanel.add(registerUsernameTextField);
        registrationPanel.add(new JLabel("Password: "));
        JPasswordField registerPasswordField = new JPasswordField();
        registrationPanel.add(registerPasswordField);
        registrationPanel.add(new JLabel());
        registerButton = new JButton("Register");
        registrationPanel.add(registerButton);

        // Create the tabbed pane and add the login and registration panels to it
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Registration", registrationPanel);

        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane);

        // Set up the frame
        setTitle("Login/Registration Page");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Add action listeners to the buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Login button clicked
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                // TODO: Implement login functionality
            }
        });
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Register button clicked
                String username = registerUsernameTextField.getText();
                String password = new String(registerPasswordField.getPassword());
                // TODO: Implement registration functionality
            }
        });
    }

    public static void main(String[] args) {
        new LoginRegistrationPage();
    }
}
