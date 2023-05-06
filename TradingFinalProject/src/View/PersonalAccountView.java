package View;

import Model.CustomerPersonalAccountSystem;
import Model.PersonalTransaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PersonalAccountView extends JFrame {
    private JLabel greetingLabel;
    private JLabel accountBalanceLabel;
    private JLabel transactionHistoryLabel;
    private JButton saveButton;
    private JButton withdrawButton;
    private JButton tradingAccountButton;
    private JButton requestTradingAccountButton;
    private CustomerPersonalAccountSystem customerPersonalAccountSystem;

    private double accountBalance = 0.0;
    private ArrayList<String[]> PersonalTransactionHistory;

    public PersonalAccountView(CustomerPersonalAccountSystem customerPersonalAccountSystem) {
        // Set up the frame
        super("Personal Account");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.customerPersonalAccountSystem = customerPersonalAccountSystem;
        accountBalance = customerPersonalAccountSystem.getCustomer().getBalance();
        //Personal Accout History should be like [["Deposit", "1000"], ["Withdraw", "1000"]]
        PersonalTransactionHistory = customerPersonalAccountSystem.readPersonalTransactionHistoryFromDB();


        // Set up the greeting label
        greetingLabel = new JLabel("Hi " + customerPersonalAccountSystem.getCustomer().getName()+ "!");

        // Set up the labels
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        accountBalanceLabel = new JLabel("Account Balance: " + customerPersonalAccountSystem.getCustomer().getBalance());
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
                        customerPersonalAccountSystem.saveMoney(amount);
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
                            customerPersonalAccountSystem.withdrawMoney(amount);
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
                if (!customerPersonalAccountSystem.getCustomer().checkTradingAccountExit()) {
                    // Create a custom dialog with message and Yes/No buttons
                    int result = JOptionPane.showOptionDialog(PersonalAccountView.this,
                            "You do not have a trading account. Would you like to apply for one?",
                            "Apply for Trading Account",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, null, null);

                    if (result == JOptionPane.YES_OPTION) {
                        // Proceed with the trading account application process
                        customerPersonalAccountSystem.sendOpenTradingAccountRequest();
                    }
                }
                else {

                }
            }
        });
        requestTradingAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerPersonalAccountSystem.sendOpenTradingAccountRequest();
                JOptionPane.showMessageDialog(null, "Request Send");
                // TODO: Implement request trading account button functionality
            }
        });

        // Set up the panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(greetingLabel, BorderLayout.NORTH);
        panel.add(accountBalanceLabel, BorderLayout.CENTER);
        panel.add(transactionHistoryLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(tradingAccountButton);
        if(!customerPersonalAccountSystem.ifCustomerHasTradingAccount()){
        buttonPanel.add(requestTradingAccountButton);}
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to the frame
        getContentPane().add(panel);
    }

}
