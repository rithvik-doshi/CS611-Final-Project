package View;
import Model.Manager;
import Model.ManagerLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;

public class ManagerLoginPage extends JFrame {
    private JLabel titleLabel;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel keyLabel;
    private JTextField keyTextField;
    private JButton loginButton;
    private JButton backButton;
    private ManagerLogin managerLogin;

    public ManagerLoginPage() {
        // Set up the frame
        super("Manager Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the title label
        titleLabel = new JLabel("Manager Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Set up the login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));
        usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        keyLabel = new JLabel("Key:");
        keyTextField = new JTextField();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(keyLabel);
        loginPanel.add(keyTextField);

        // Add the login button and the back button to a panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        loginPanel.add(new JLabel(""));
        loginPanel.add(buttonPanel);

        // Add components to the frame
        getContentPane().add(titleLabel, BorderLayout.NORTH);
        getContentPane().add(loginPanel, BorderLayout.CENTER);

        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/Manager.txt";
        managerLogin = new ManagerLogin(currentPath);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                String managerKey = keyTextField.getText();

                if (managerLogin.checkLogin(username, password, managerKey)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    Manager manager = managerLogin.getManager();
                    ManagerPortfolioView managerPortfolioView = new ManagerPortfolioView(manager);
                    managerPortfolioView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username, password, or manager key. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Back button clicked
                ManagerLoginPage.this.setVisible(false);
                EntryInterface entryInterface = new EntryInterface();
            }
        });
    }

    public static void main(String[] args) {
        ManagerLoginPage ui = new ManagerLoginPage();
        ui.setVisible(true);
    }
}
