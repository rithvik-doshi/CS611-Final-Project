package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model.Stock;
import Model.MarketStock;

public class ManageStockView extends JFrame {

    private JLabel titleLabel;
    private JPanel stockPanel;
    private JButton addStockButton;
    private JButton backButton;

    // List of stocks
    private ArrayList<MarketStock> Marketstocks;
    

    public ManageStockView() {
        super("Manage Stocks");
        //Test Proposed:
        Marketstocks = new ArrayList<>();
        Marketstocks.add(new MarketStock("AAPL", 100.00));
        Marketstocks.add(new MarketStock("GOOG", 200.00));
        Marketstocks.add(new MarketStock("MSFT", 300.00));


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Manage Stocks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        stockPanel = new JPanel();
        stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(stockPanel);
        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageStockView.this.dispose();
                ManagerPortfolioView ui = new ManagerPortfolioView();
                ui.setVisible(true);
            }
        });

        addStockButton = new JButton("Add Stock");
        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStock();
            }
        });
        add(addStockButton, BorderLayout.SOUTH);
        add(backButton, BorderLayout.WEST);
        displayStocks();

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStock() {
        JDialog addStockDialog = new JDialog(this, "Add Stock", true);
        addStockDialog.setLayout(new GridLayout(3, 2));

        JLabel stockNameLabel = new JLabel("Stock Name:");
        JTextField stockNameField = new JTextField();
        JLabel stockPriceLabel = new JLabel("Stock Price:");
        JTextField stockPriceField = new JTextField();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stockName = stockNameField.getText();
                String stockPriceString = stockPriceField.getText();

                if (!stockName.isEmpty() && !stockPriceString.isEmpty()) {
                    try {
                        double stockPrice = Double.parseDouble(stockPriceString);

                        MarketStock newStock = new MarketStock(stockName, stockPrice);
                        Marketstocks.add(newStock);
                        displayStocks();

                        addStockDialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(addStockDialog, "Please enter a valid price and quantity.");
                    }
                } else {
                    JOptionPane.showMessageDialog(addStockDialog, "Please enter a stock name, price, and quantity.");
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStockDialog.dispose();
            }
        });

        addStockDialog.add(stockNameLabel);
        addStockDialog.add(stockNameField);
        addStockDialog.add(stockPriceLabel);
        addStockDialog.add(stockPriceField);
        addStockDialog.add(addButton);
        addStockDialog.add(cancelButton);

        addStockDialog.pack();
        addStockDialog.setLocationRelativeTo(this);
        addStockDialog.setVisible(true);
    }

    private void displayStocks() {
        stockPanel.removeAll();

        for (MarketStock stock : Marketstocks) {
            JPanel stockPanel = new JPanel();
            stockPanel.setLayout(new GridLayout(1, 4));

            JLabel nameLabel = new
            JLabel(stock.getName());
            stockPanel.add(nameLabel);
    
            JLabel priceLabel = new JLabel(Double.toString(stock.getMoney()));
            stockPanel.add(priceLabel);
    
            JButton editPriceButton = new JButton("Edit Price");
            editPriceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editStockPrice(stock);
                }
            });
            stockPanel.add(editPriceButton);
    
    
            this.stockPanel.add(stockPanel);
        }
    
        this.stockPanel.revalidate();
        this.stockPanel.repaint();
    }
    
    private void editStockPrice(MarketStock stock) {
        JDialog editPriceDialog = new JDialog(this, "Edit Price", true);
        editPriceDialog.setLayout(new GridLayout(2, 2));
    
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(Double.toString(stock.getMoney()));
    
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String priceString = priceField.getText();
    
                if (!priceString.isEmpty()) {
                    try {
                        double price = Double.parseDouble(priceString);
                        stock.setMoney(price);
                        displayStocks();
    
                        editPriceDialog.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(editPriceDialog, "Please enter a valid price.");
                    }
                } else {
                    JOptionPane.showMessageDialog(editPriceDialog, "Please enter a price.");
                }
            }
        });
    
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPriceDialog.dispose();
            }
        });
    
        editPriceDialog.add(priceLabel);
        editPriceDialog.add(priceField);
        editPriceDialog.add(saveButton);
        editPriceDialog.add(cancelButton);
    
        editPriceDialog.pack();
        editPriceDialog.setLocationRelativeTo(this);
        editPriceDialog.setVisible(true);
    }



    public static void main(String[] args) {
        new ManageStockView();
    }
}
    