package View;

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
        String[] columnNames = {"Price", "Name", "ID", "Date"};
        Object[][] data = {{"$10.00", "Apple Inc.", "AAPL", "2023-04-27"},
                           {"$20.00", "Microsoft Corporation", "MSFT", "2023-04-27"},
                           {"$30.00", "Amazon.com, Inc.", "AMZN", "2023-04-27"},
                           {"$40.00", "Facebook, Inc.", "FB", "2023-04-27"}};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        stockTable = new JTable(model);
        scrollPane = new JScrollPane(stockTable);

        // Set up the buttons
        backButton = new JButton("Back");
        customerLoginButton = new JButton("Customer Login");
        managerLoginButton = new JButton("Manager Login");

        // Add action listeners to the buttons
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement back button functionality
            }
        });
        customerLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement customer login button functionality
            }
        });
        managerLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement manager login button functionality
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

    public static void main(String[] args) {
        StockMarketView ui = new StockMarketView();
        ui.setVisible(true);
    }
}
