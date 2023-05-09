package View;

import Controller.CustomerPersonalAccountSystem;
import Controller.CustomerStockTradingSystem;
import Model.*;

import javax.swing.text.html.HTMLEditorKit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class TradingAccountView extends JFrame {

    private JLabel greetingLabel;
    private JLabel titleLabel;
    private JLabel accountBalanceLabel;
    private JButton backButton;
    private JButton viewStockMarketButton;
    private CustomerStockTradingSystem customerStockTradingSystem;
    private HashMap<String, Integer> stockHoldings = new HashMap<>();
    private double balance;
    private StockMarketView stockMarketView;
    private ArrayList<String> transactionHistory;
    private TransactionData transactionData;

    private JTextPane transactionHistoryPane;
    private JScrollPane transactionHistoryScrollPane;

    private JPanel ownedStocksPanel;

    private JPanel panel = new JPanel(new BorderLayout());
    DecimalFormat df = new DecimalFormat("$#,##0.00");

    String currentPath = Paths.get("").toAbsolutePath() + "/TradingFinalProject/src/Database/DBFiles/CustomerStockHistory/";
    String path;

    public TradingAccountView(CustomerPersonalAccountSystem customerPersonalAccountSystem) {
        super("Trading Account");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        customerStockTradingSystem = new CustomerStockTradingSystem(customerPersonalAccountSystem.getCustomer(), customerPersonalAccountSystem.getCustomer().getPersonalAccount());
        stockHoldings = customerStockTradingSystem.getStockHoldings();
        balance = customerStockTradingSystem.getBalance();

        path = currentPath + customerPersonalAccountSystem.getCustomer().getID() + "_StockHistory.txt";
        transactionData = new TransactionData(path);
        transactionHistory = transactionData.getTransactionHistory(path);

        // Set up the greeting label
        greetingLabel = new JLabel("Hi " + customerPersonalAccountSystem.getCustomer().getName() + "!");

        titleLabel = new JLabel("Manage Stocks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Set up the labels
        accountBalanceLabel = new JLabel("Account Balance: " + df.format(balance));
        transactionHistoryPane = new JTextPane();
        transactionHistoryPane.setEditable(false);
        transactionHistoryPane.setEditorKit(new HTMLEditorKit());
        transactionHistoryScrollPane = new JScrollPane(transactionHistoryPane);
        updateTransactionHistoryLabel();

        // Create and set up the Back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close this window
                PersonalAccountView personalAccountView = new PersonalAccountView(customerPersonalAccountSystem);
                personalAccountView.setVisible(true);
                TradingAccountView.this.dispose();
            }
        });

        ownedStocksPanel = new JPanel();
        ownedStocksPanel.setLayout(new BoxLayout(ownedStocksPanel, BoxLayout.Y_AXIS));

        updateStocksPanel();

        // Set up the panel
        panel.add(greetingLabel, BorderLayout.NORTH);
        panel.add(accountBalanceLabel, BorderLayout.WEST);
        panel.add(transactionHistoryScrollPane, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        JScrollPane ownedStocksScrollPane = new JScrollPane(ownedStocksPanel);
        centerPanel.add(ownedStocksScrollPane);
        centerPanel.add(transactionHistoryScrollPane);
        panel.add(centerPanel, BorderLayout.CENTER);


        // Create and set up the viewStockMarketButton
        viewStockMarketButton = new JButton("View Stock Market");
        viewStockMarketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new StockMarketView and display it
                stockMarketView = new StockMarketView(customerPersonalAccountSystem);
                stockMarketView.setVisible(true);
                dispose();

            }
        });

        // Add the viewStockMarketButton to the panel
        panel.add(viewStockMarketButton, BorderLayout.SOUTH);

        // Add the Back button to the top-right corner
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        // Create a top panel for the title and the back button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Add the Back button to the top-right corner
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButtonPanel.add(backButton);
        topPanel.add(backButtonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        // Add panel to the frame
        getContentPane().add(panel);

        if (customerStockTradingSystem.canHaveDerivativeAccount()){

            JOptionPane.showMessageDialog(null, "You can now apply for a derivative account!", "Derivative Account", JOptionPane.PLAIN_MESSAGE);
        }

    }

    private void updateTransactionHistoryLabel() {
        StringBuilder transactionsStringBuilder = new StringBuilder("<html><body>");
        transactionHistory = transactionData.getTransactionHistory(path);
        System.out.println(transactionHistory);
        for (String transaction : transactionHistory) {
            transactionsStringBuilder.append(transaction);
            transactionsStringBuilder.append("<br/>");
        }

        transactionsStringBuilder.append("</body></html>");

        transactionHistoryPane.setText(transactionsStringBuilder.toString());
        transactionHistoryPane.revalidate();
        transactionHistoryPane.repaint();
    }

    private void updateStocksPanel() {

        ownedStocksPanel.removeAll();

        for (String stock : stockHoldings.keySet()) {
            JPanel stockPanel = new JPanel(new FlowLayout());
            stockPanel.add(new JLabel(stock));
            stockPanel.add(new JLabel(stockHoldings.get(stock) + "ct"));
            stockPanel.add(new JLabel(df.format(SMProxy.instance.getStockPrice(stock)) + "/ea"));

            JButton addOrRemoveStockButton = new JButton("+/-");
            addOrRemoveStockButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addOrRemoveStock(stock, stockHoldings.get(stock));
                }
            });
            stockPanel.add(addOrRemoveStockButton);

            ownedStocksPanel.add(stockPanel);
        }

        this.ownedStocksPanel.revalidate();
        this.ownedStocksPanel.repaint();
    }

    private void updateEnv(){
        updateStocksPanel();
        balance = customerStockTradingSystem.getBalance();
        System.out.println("Balance here: " + balance);
        panel.remove(accountBalanceLabel);
        accountBalanceLabel = new JLabel("Account Balance: " + df.format(balance));
        panel.add(accountBalanceLabel, BorderLayout.WEST);
        updateTransactionHistoryLabel();
        panel.revalidate();
        panel.repaint();
    }

    private void addOrRemoveStock(String stockName, int quantity) {
        JDialog addOrRemoveDialog = new JDialog(this, "Add or remove", true);

        JPanel dialogPanel = new JPanel(new GridLayout(2, 2));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a JLabel to display the stock name
        dialogPanel.add(new JLabel("Stock name: " + stockName + " at " + df.format(SMProxy.instance.getStockPrice(stockName)) + "/ea"));

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
                if (quantityChange == 0) {
                    addOrRemoveDialog.dispose();
                    return;
                }
                // Update the stock holdings
                if (customerStockTradingSystem.buyStock(stockName, quantityChange)) {
                    stockHoldings.put(stockName, quantity + quantityChange);
                    balance = customerStockTradingSystem.getBalance();
                } else {
                    // generate a new window shows "Insufficient funds for this transaction."
                    JOptionPane.showMessageDialog(null, "Insufficient funds for this transaction.");
                }

                // Update the stocks panel
                updateEnv();

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
                if (quantityChange == 0) {
                    addOrRemoveDialog.dispose();
                    return;
                }
                // Check if the quantity to remove is less than or equal to the current quantity
                if (quantityChange <= quantity) {
                    // Update the stock holdings
                    customerStockTradingSystem.sellStock(stockName, quantityChange);
                    balance = customerStockTradingSystem.getBalance();
                    // Update the stocks panel
                    updateStocksPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid quantity. Cannot remove more than owned.");
                }

                updateEnv();

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
}
