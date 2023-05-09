package View;

import Controller.CustomerPersonalAccountSystem;
import Controller.CustomerStockTradingSystem;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockMarketView extends JFrame {
    private JTable stockTable;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JButton backButton;
    private CustomerPersonalAccountSystem customerPersonalAccountSystem;
    private CustomerStockTradingSystem customerStockTradingSystem;


    public StockMarketView(CustomerPersonalAccountSystem customerPersonalAccountSystem) {
        // Set up the frame
        super("Stock Market");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.customerPersonalAccountSystem = customerPersonalAccountSystem;
        customerStockTradingSystem = new CustomerStockTradingSystem(customerPersonalAccountSystem.getCustomer(),customerPersonalAccountSystem.getCustomer().getPersonalAccount());

        // Set up the table and scroll pane
        String[] columnNames = {"Price", "Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Initially no data, just column names
        stockTable = new JTable(model);
        scrollPane = new JScrollPane(stockTable);

        populateTableWithStocks(); // Populate the table with stock data

        stockTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = stockTable.getSelectedRow();
                if (selectedRow != -1) {
                    String itemName = (String) stockTable.getValueAt(selectedRow, 1);

                    int response = JOptionPane.showConfirmDialog(null,
                            "Do you want to buy " + itemName + "?",
                            "Buy Item",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        // Display a dialog box to enter the quantity to purchase
                        String num1 = JOptionPane.showInputDialog(null, "Enter quantity to purchase:");
                        int quantity = Integer.parseInt(num1);

                        // Add logic to handle purchasing the selected item
                        customerStockTradingSystem.buyStock(itemName, quantity);
                        TradingAccountView tradingAccountView = new TradingAccountView(customerPersonalAccountSystem);
                        tradingAccountView.setVisible(true);
                        this.dispose();

                    }
                }
            }
        });

        // Set up the buttons
        backButton = new JButton("Back");

        // Add action listeners to the buttons
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement back button functionality
//                TradingAccountView tradingAccountView = new TradingAccountView(customerPersonalAccountSystem);
                TradingAccountView tradingAccountView = new TradingAccountView(customerPersonalAccountSystem);
                tradingAccountView.setVisible(true);
                dispose();
            }
        });



        // Add components to the panel
        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to the frame
        getContentPane().add(panel);
    }

    private void populateTableWithStocks() {
        DefaultTableModel tableModel = (DefaultTableModel) stockTable.getModel();
        StockMarket stockMarketInstance = StockMarket.instance;

        // Loop through all stocks in the stock market instance
        for (MarketStock stock : stockMarketInstance.stocks) {
            // Format the stock price with two decimal places and add the stock to the table model
            tableModel.addRow(new Object[]{String.format("$%.2f", stock.getMoney()), stock.getName()});
        }
    }

    public static void main(String[] args) {
        Customer customer=new Customer(1,"sam","sam","123",1000);
        CustomerPersonalAccountSystem customerPersonalAccountSystem1 = new CustomerPersonalAccountSystem(customer);
        StockMarketView ui = new StockMarketView(customerPersonalAccountSystem1);
        ui.setVisible(true);
    }
}
