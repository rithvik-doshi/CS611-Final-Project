package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Manager;
import Model.MarketStock;
import Model.SMProxy;

public class ManageStockView extends JFrame {

    private final JPanel stockPanel;

    // List of stocks
    private ArrayList<MarketStock> MarketStocks;

    private final Manager manager;
    

    public ManageStockView(Manager manager) {
        super("Manage Stocks");

        this.manager = manager;
        //Test Proposed:
        MarketStocks = SMProxy.instance.getAllStocks();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Manage Stocks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        stockPanel = new JPanel();
        stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(stockPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageStockView.this.dispose();
                ManagerPortfolioView ui = new ManagerPortfolioView(manager);
                ui.setVisible(true);
            }
        });

        JButton addStockButton = new JButton("Add Stock");
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

                        SMProxy.instance.addStock(stockName, stockPrice, manager.getID(), manager.getKey());
                        MarketStocks = SMProxy.instance.getAllStocks();
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

        for (MarketStock stock : MarketStocks) {
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

            JButton deleteStockButton = new JButton("Delete");

            deleteStockButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteStock(stock);
                }
            });
            stockPanel.add(deleteStockButton);

    
    
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
//                        stock.setMoney(price);
                        SMProxy.instance.setStockPrice(stock.getName(), price, manager.getID(), manager.getKey());
                        MarketStocks = SMProxy.instance.getAllStocks();
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

    private void deleteStock(MarketStock stock) {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove " + stock.getName() + " from the stock market?",
                "Delete Stock", JOptionPane.YES_NO_OPTION);

        // If the user clicked the "Yes" button, delete the stock
        if (result == JOptionPane.YES_OPTION) {
            // Call some method to delete the stock
            SMProxy.instance.removeStock(stock.getName(), manager.getID(), manager.getKey());
        }

        SMProxy.instance.getAllStocks();
        displayStocks();

    }

}
    