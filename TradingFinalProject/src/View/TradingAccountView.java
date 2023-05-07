package View;

import Model.Customer;
import Model.CustomerPersonalAccountSystem;
import Model.CustomerStockTradingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;

public class TradingAccountView extends JFrame {

    private JLabel greetingLabel;
    private JLabel titleLabel;
    private JLabel accountBalanceLabel;
    private JLabel transactionHistoryLabel;
    private CustomerProfitReportView customerProfitReportView;
    private Customer customer;
    private JButton backButton;
    private CustomerPersonalAccountSystem customerPersonalAccountSystem;
    private CustomerStockTradingSystem customerStockTradingSystem;
    private HashMap<String, Integer> stockHoldings = new HashMap<>();
    private double balance;

    private JPanel ownedStocksPanel;



    public TradingAccountView(CustomerPersonalAccountSystem customerPersonalAccountSystem) {
        super("Trading Account");
        this.customerPersonalAccountSystem = customerPersonalAccountSystem;
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        customerStockTradingSystem = new CustomerStockTradingSystem(customerPersonalAccountSystem.getCustomer(), customerPersonalAccountSystem.getCustomer().getPersonalAccount());
        stockHoldings = customerStockTradingSystem.getStockHoldings();
        balance = customerStockTradingSystem.getBalance();

        // Set up the greeting label
        greetingLabel = new JLabel("Hi !"+customerPersonalAccountSystem.getCustomer().getName());

        titleLabel = new JLabel("Manage Stocks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Set up the labels
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        accountBalanceLabel = new JLabel("Account Balance: $"+balance);
        transactionHistoryLabel = new JLabel("Transaction History: ");


        // Create and set up the Back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close this window
                TradingAccountView.this.dispose();
            }
        });

        ownedStocksPanel = new JPanel();
        ownedStocksPanel.setLayout(new BoxLayout(ownedStocksPanel, BoxLayout.Y_AXIS));

        updateStocksPanel();

        // Set up the panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(greetingLabel, BorderLayout.NORTH);
        panel.add(accountBalanceLabel, BorderLayout.WEST);
        panel.add(transactionHistoryLabel, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(ownedStocksPanel);
        panel.add(scrollPane, BorderLayout.CENTER);


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
    }
    private void updateStocksPanel() {

        ownedStocksPanel.removeAll();

        for (String stock : stockHoldings.keySet()) {
            JPanel stockPanel = new JPanel(new FlowLayout());
            stockPanel.add(new JLabel(stock));
            stockPanel.add(new JLabel(Integer.toString(stockHoldings.get(stock))));

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
                // Update the stock holdings
                if (customerStockTradingSystem.buyStock(stockName,quantityChange)){
                    stockHoldings.put(stockName, quantity + quantityChange);
                }
                else {
                    //generate a new window shows "Insufficient funds for this transaction."

                }

                customerStockTradingSystem.buyStock(stockName,quantityChange);
                // Update the stocks panel
                updateStocksPanel();

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

                // Check if the quantity to remove is less than or equal to the current quantity
                if (quantityChange <= quantity) {
                    // Update the stock holdings
                    stockHoldings.put(stockName, quantity - quantityChange);
                    customerStockTradingSystem.sellStock(stockName,quantityChange);
                    // Update the stocks panel
                    updateStocksPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid quantity. Cannot remove more than owned.");
                }

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
