package View;

import Controller.CustomerPersonalAccountSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class PersonalAccountView extends JFrame {
    private JLabel greetingLabel;
    private JLabel accountBalanceLabel;
    private JButton saveButton;
    private JButton withdrawButton;
    private JButton tradingAccountButton;
    private JButton personalAccountHistory;

    private double accountBalance;
    DecimalFormat df = new DecimalFormat("$#,##0.00");
    public PersonalAccountView(CustomerPersonalAccountSystem customerPersonalAccountSystem) {
        // Set up the frame
        super("Personal Account");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the greeting label
        greetingLabel = new JLabel("Hi " + customerPersonalAccountSystem.getCustomer().getName()+ "!");

        accountBalance = customerPersonalAccountSystem.getPersonalAccountBalance();
//        System.out.println("Account Balance at init: " + accountBalance);

        // Set up the labels
        accountBalanceLabel = new JLabel("Account Balance: " + df.format(customerPersonalAccountSystem.getPersonalAccountBalance()));

        // Set up the buttons
        saveButton = new JButton("Deposit Money");
        withdrawButton = new JButton("Withdraw Money");
        tradingAccountButton = new JButton("Trading Account");
        personalAccountHistory = new JButton("View Personal Account History");

        // Add action listeners to the buttons
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to deposit:");
                if (input != null) {
                    try {
                        if (Double.parseDouble(input) < 0){
                            throw new NumberFormatException();
                        }
                        double amount = Double.parseDouble(input);
                        accountBalance += amount;
                        customerPersonalAccountSystem.saveMoney(amount);
//                        System.out.println("Account Balance after deposit: " + customerPersonalAccountSystem.getPersonalAccountBalance());
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
                        if (Double.parseDouble(input) < 0){
                            throw new NumberFormatException();
                        }
                        double amount = Double.parseDouble(input);
                        if (amount > (accountBalance = Math.ceil(accountBalance * 100) / 100.0)) {
                            JOptionPane.showMessageDialog(null, "Insufficient funds.");
                        } else {
                            accountBalance -= amount;
                            customerPersonalAccountSystem.withdrawMoney(amount);
//                            System.out.println("Account Balance after withdraw: " + customerPersonalAccountSystem.getPersonalAccountBalance());
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

                    TradingAccountView tradingAccountView = new TradingAccountView(customerPersonalAccountSystem);
                    tradingAccountView.setVisible(true);
                    dispose();
                }
            }
        });
        personalAccountHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement request trading account button functionality

                StringBuilder sb = new StringBuilder();

                sb.append("History: \n");
                sb.append(customerPersonalAccountSystem.getHistory());

                JOptionPane.showMessageDialog(null, sb.toString(), "Profits", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // Set up the panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(greetingLabel, BorderLayout.NORTH);
        panel.add(accountBalanceLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(saveButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(tradingAccountButton);
        buttonPanel.add(personalAccountHistory);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to the frame
        getContentPane().add(panel);



    }

    public static void main(String[] args) {
//        PersonalAccountView ui = new PersonalAccountView();
//        ui.setVisible(true);
    }
}