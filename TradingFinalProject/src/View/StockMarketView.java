package View;

import Model.StockMarket;
import Model.MarketStock;

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
    private JButton customerLoginButton;
    private JButton managerLoginButton;

    public StockMarketView() {
        // Set up the frame
        super("Stock Market");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the table and scroll pane
        String[] columnNames = {"Price", "Name"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Initially no data, just column names
        stockTable = new JTable(model);
        scrollPane = new JScrollPane(stockTable);

        populateTableWithStocks(); // Populate the table with stock data

        // Set up the buttons
        backButton = new JButton("Back");
        customerLoginButton = new JButton("Customer Login");
        managerLoginButton = new JButton("Manager Login");

        // Add action listeners to the buttons
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement back button functionality
                EntryInterface entryInterface = new EntryInterface();
                StockMarketView.this.setVisible(false);
            }
        });

        // Add action listeners to the buttons
        customerLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Customer button clicked
                LoginRegistrationPage customerLoginRegistrationPage = new LoginRegistrationPage();
                StockMarketView.this.setVisible(false); // Set the current frame to be invisible

            }
        });
        managerLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Customer button clicked
                ManagerLoginPage managerLoginRegistrationPage = new ManagerLoginPage();
                managerLoginRegistrationPage.setVisible(true);
                StockMarketView.this.setVisible(false); // Set the current frame to be invisible
            }
        });

        // Add components to the panel
        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(customerLoginButton);
        buttonPanel.add(managerLoginButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panel to the frame
        getContentPane().add(panel);
    }

    private void populateTableWithStocks() {
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        StockMarket stockMarket = StockMarket.instance;
        for (MarketStock stock : stockMarket.stocks) {
            model.addRow(new Object[]{String.format("$%.2f", stock.getMoney()), stock.getName(), "Unknown", "Unknown"});
        }
    }

    public static void main(String[] args) {
        StockMarketView ui = new StockMarketView();
        ui.setVisible(true);
    }
}
