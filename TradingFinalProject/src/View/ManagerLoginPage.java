package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerLoginPage extends JFrame {
    private JLabel titleLabel;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    public ManagerLoginPage() {
        // Set up the frame
        super("Manager Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the title label
        titleLabel = new JLabel("Manager Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Set up the login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement login functionality
                JOptionPane.showMessageDialog(null, "Login successful!");
            }
        });
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);

        // Add components to the frame
        getContentPane().add(titleLabel, BorderLayout.NORTH);
        getContentPane().add(loginPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ManagerLoginPage ui = new ManagerLoginPage();
        ui.setVisible(true);
    }
}
