package View;

import Model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPortfolioView extends JFrame {

    private JLabel titleLabel;

    private JButton manageStocksButton;
    private JButton approveCustomersButton;
    private JButton trackCustomersButton;

    public ManagerPortfolioView() {
        super("Manager Portfolio System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Welcome to the Manager Portfolio System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        manageStocksButton = new JButton("Manage Stocks");
        approveCustomersButton = new JButton("Approve New Customers");
        trackCustomersButton = new JButton("Track Customers");


        manageStocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle manage stocks button action
                ManageStockView ui = new ManageStockView(new Manager(1, "Rithvik Doshi", "bruh@buh.com", "password"));
                ui.setVisible(true);
                ManagerPortfolioView.this.setVisible(false);
            }
        });

        approveCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle approve customers button action
                TradingAccountRequestsView ui = new TradingAccountRequestsView();
                ui.setVisible(true);
                ManagerPortfolioView.this.setVisible(false);
            }
        });

        trackCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle track customers button action
                CustomerProfitReportView ui = new CustomerProfitReportView();
                ui.setVisible(true);
                ManagerPortfolioView.this.setVisible(false);
            }
        });

        buttonPanel.add(manageStocksButton);
        buttonPanel.add(approveCustomersButton);
        buttonPanel.add(trackCustomersButton);
        add(buttonPanel, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        ManagerPortfolioView ui = new ManagerPortfolioView();
    }

}
