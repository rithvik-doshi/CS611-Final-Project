package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class PersonalAccountView extends JFrame {
    private JLabel accountBalanceLabel;
    private JLabel transactionHistoryLabel;
    private JButton saveButton;
    private JButton withdrawButton;
    private JButton tradingAccountButton;
    private JButton requestTradingAccountButton;

    private double accountBalance = 0.0;
    private String[] transactionHistory = {"Transaction 1", "Transaction 2", "Transaction 3"};

    public PersonalAccountView() {
        // Set up the frame
        super("Personal Account");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the labels
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        accountBalanceLabel = new JLabel("Account Balance: " + df.format(accountBalance));
        transactionHistoryLabel = new JLabel("Transaction History: ");

        // Set up the buttons
        saveButton = new JButton("Save Money");
        withdrawButton = new JButton("Withdraw Money");
        tradingAccountButton = new JButton("Trading Account");
        requestTradingAccountButton = new JButton("Request Trading Account");

        // Add action listeners to the buttons
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to save:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        accountBalance += amount;
                        accountBalanceLabel.setText("Account Balance: " + df.format(accountBalance));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.");
                    }
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        if (amount > accountBalance) {
                            JOptionPane.showMessageDialog(null, "Insufficient funds.");
                        } else {
                            accountBalance -= amount;
                            accountBalanceLabel.setText("Account Balance: " + df.format(accountBalance));
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.");
                    }
                }
            }
        });
        tradingAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement trading account button functionality
            }
        });
        requestTradingAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement request trading account button functionality
            }
        });

        // Set up the panel
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(tradingAccountButton);
        buttonPanel.add(requestTradingAccountButton);
        panel.add(accountBalanceLabel, BorderLayout.NORTH);
        panel.add(transactionHistoryLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to the frame
        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        PersonalAccountView ui = new PersonalAccountView();
        ui.setVisible(true);
    }
}
