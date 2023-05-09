package View;

import Model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPortfolioView extends JFrame {

    public ManagerPortfolioView(Manager manager) {
        super("Manager Portfolio System");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to the Manager Portfolio System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton manageStocksButton = new JButton("Manage Stocks");
        JButton approveCustomersButton = new JButton("Approve New Customers");
        JButton trackCustomersButton = new JButton("Track Customers");
        JButton backButton = new JButton("Back");


        manageStocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle manage stocks button action
                ManageStockView ui = new ManageStockView(manager);
                ui.setVisible(true);
                ManagerPortfolioView.this.setVisible(false);
            }
        });

        approveCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle approve customers button action
                TradingAccountRequestsView ui = new TradingAccountRequestsView(manager);
                ui.setVisible(true);
                ManagerPortfolioView.this.setVisible(false);
            }
        });

        trackCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle track customers button action
                CustomerProfitReportView ui = new CustomerProfitReportView(manager);
                ui.setVisible(true);
                ManagerPortfolioView.this.setVisible(false);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManagerPortfolioView.this.setVisible(false);
                dispose();
            }
        });

        buttonPanel.add(manageStocksButton);
        buttonPanel.add(approveCustomersButton);
        buttonPanel.add(trackCustomersButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
