package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TradingAccountView extends JFrame {

    private JLabel greetingLabel;
    private JLabel titleLabel;
    private JLabel accountBalanceLabel;
    private JLabel transactionHistoryLabel;
    private JButton depositButton;
    private JButton withdrawButton;

    private JPanel ownedStocksPanel;

    private String[] transactionHistory = {"Transaction 1", "Transaction 2", "Transaction 3"};

    public TradingAccountView() {
        super("Trading Account");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the greeting label
        greetingLabel = new JLabel("Hi Customer!");

        titleLabel = new JLabel("Manage Stocks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Set up the labels
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        accountBalanceLabel = new JLabel("Account Balance: 23");
        transactionHistoryLabel = new JLabel("Transaction History: ");

        // Set up the buttons
        depositButton = new JButton("Deposit from personal acct");
        withdrawButton = new JButton("Withdraw to personal acct");



        // Add action listeners to the buttons
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter amount to transfer:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        double accountBalance = amount;
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
                        if (amount > 45) {
                            JOptionPane.showMessageDialog(null, "Insufficient funds.");
                        } else {
                            accountBalanceLabel.setText("Account Balance: " + 45);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.");
                    }
                }
            }
        });

        ownedStocksPanel = new JPanel();
        ownedStocksPanel.setLayout(new BoxLayout(ownedStocksPanel, BoxLayout.Y_AXIS));
//        ownedStocksPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));



        updateStocksPanel();

        // Set up the panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(greetingLabel, BorderLayout.NORTH);
        panel.add(accountBalanceLabel, BorderLayout.WEST);
        panel.add(transactionHistoryLabel, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(ownedStocksPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to the frame
        getContentPane().add(panel);
    }

    private void updateStocksPanel() {

        ownedStocksPanel.removeAll();

        String[] stocks = {"AAPL", "GOOG", "MSFT", "AMZN", "FB", "AAPL", "GOOG", "MSFT", "AMZN", "FB", "AAPL", "GOOG", "MSFT", "AMZN", "FB", "AAPL", "GOOG", "MSFT", "AMZN", "FB"};
        int[] quantities = {100, 200, 300, 400, 500, 100, 200, 300, 400, 500, 100, 200, 300, 400, 500, 100, 200, 300, 400, 500};

        for (int i = 0; i < stocks.length; i++) {
            JPanel stockPanel = new JPanel(new FlowLayout());
            stockPanel.add(new JLabel(stocks[i]));
            stockPanel.add(new JLabel(Integer.toString(quantities[i])));

            JButton addOrRemoveStockButton = new JButton("+/-");
            int finalI = i;
            addOrRemoveStockButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addOrRemoveStock(stocks[finalI], quantities[finalI]);
                }
            });
            stockPanel.add(addOrRemoveStockButton);


            ownedStocksPanel.add(stockPanel);
        }

        this.ownedStocksPanel.revalidate();
        this.ownedStocksPanel.repaint();

    }

    private void addOrRemoveStock(String stockName, int quantity) {
        JDialog addOrRemoveDialog = new JDialog(this, "Add or remove", true);

        JPanel dialogPanel = new JPanel(new GridLayout(2, 2));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a JLabel to display the stock name
        dialogPanel.add(new JLabel("Stock name: " + stockName));


        // Create a JTextField to allow the user to enter the quantity change
        JTextField quantityField = new JTextField(Integer.toString(0));
        dialogPanel.add(quantityField);

        // Create a JButton to allow the user to add or remove stock
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        dialogPanel.add(addButton);
        dialogPanel.add(removeButton);

        // Add an ActionListener to the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Parse the entered quantity as an integer
                int quantityChange = Integer.parseInt(quantityField.getText());

                // Update the stock quantity in the table
//                int rowIndex = getRowIndex(stockName);
//                int currentQuantity = Integer.parseInt(stockTable.getValueAt(rowIndex, 1).toString());
//                int newQuantity = currentQuantity + quantityChange;
//                stockTable.setValueAt(newQuantity, rowIndex, 1);

                // Close the dialog
                addOrRemoveDialog.dispose();
            }
        });

        // Add an ActionListener to the remove button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Parse the entered quantity as an integer
                int quantityChange = Integer.parseInt(quantityField.getText());

                // Update the stock quantity in the table
//                int rowIndex = getRowIndex(stockName);
//                int currentQuantity = Integer.parseInt(stockTable.getValueAt(rowIndex, 1).toString());
//                int newQuantity = currentQuantity - quantityChange;
//                stockTable.setValueAt(newQuantity, rowIndex, 1);

                // Close the dialog
                addOrRemoveDialog.dispose();
            }
        });

        // Add the dialogPanel to the addOrRemoveDialog
        addOrRemoveDialog.add(dialogPanel);

        // Pack and display the dialog
        addOrRemoveDialog.pack();
        addOrRemoveDialog.setLocationRelativeTo(this);
        addOrRemoveDialog.setVisible(true);

    }

    public static void main(String[] args) {
        TradingAccountView ui = new TradingAccountView();
        ui.setVisible(true);
    }

}