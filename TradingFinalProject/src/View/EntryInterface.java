package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EntryInterface extends JFrame {
    private JButton customerButton;
    private JButton managerButton;
    private JButton viewStocksButton;
    private JButton exitButton;
    private LoginRegistrationPage customerLoginRegistrationPage;
    private ManagerLoginPage managerLoginRegistrationPage;

    public EntryInterface() {
        // Create the panel and add the buttons to it
        JPanel panel = new JPanel(new GridLayout(4, 1));
        customerButton = new JButton("Customer");
        panel.add(customerButton);
        managerButton = new JButton("Manager");
        panel.add(managerButton);
        viewStocksButton = new JButton("View Market Stocks");
        panel.add(viewStocksButton);
        exitButton = new JButton("Exit");
        panel.add(exitButton);

        // Add the panel to the frame
        getContentPane().add(panel);

        // Set up the frame
        setTitle("Market App");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Add action listeners to the buttons
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Customer button clicked
                
                customerLoginRegistrationPage = new LoginRegistrationPage();
                EntryInterface.this.setVisible(false);;
                // JOptionPane.showMessageDialog(null, "You clicked the Customer button.");

            }
        });
        managerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Manager button clicked
                
                managerLoginRegistrationPage = new ManagerLoginPage();
                managerLoginRegistrationPage.setVisible(true);
                EntryInterface.this.setVisible(false);;
            }
        });
        viewStocksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // View Stocks button clicked
                JOptionPane.showMessageDialog(null, "You clicked the View Market Stocks button.");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exit button clicked
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new EntryInterface();
    }
}