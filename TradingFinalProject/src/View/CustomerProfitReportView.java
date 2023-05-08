package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import Model.*;

public class CustomerProfitReportView extends JFrame {
    private JPanel reportPanel;
    private JButton backButton;

    private Manager manager;
    private PortfolioManagementSystem portfolioManagementSystem;

    public CustomerProfitReportView(Manager manager) {
        setTitle("Customer Profit Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.manager = manager;
        portfolioManagementSystem = new PortfolioManagementSystem(manager);

        reportPanel = new JPanel();
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        reportPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            CustomerProfitReportView.this.dispose();
            ManagerPortfolioView ui = new ManagerPortfolioView(manager);
            ui.setVisible(true);
        });
        add(backButton, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(reportPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        setSize(800, 800);
        setLocationRelativeTo(null);
        displayReport(portfolioManagementSystem.getCustomerInfor());

    }

    public void displayReport(ArrayList<Customer> customers) {
        reportPanel.removeAll();

        for (Customer customer : customers) {
            JPanel customerPanel = new JPanel(new GridLayout(1, 3));
            customerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel nameLabel = new JLabel(customer.getName());
            customerPanel.add(nameLabel);

            TradingAccount tradingAccount = customer.getTradingAccount();

            double realizedProfit;
            JLabel realizedProfitLabel, unrealizedProfitLabel;

            if (tradingAccount != null) {
                realizedProfit = tradingAccount.getRealizedProfit();
                realizedProfitLabel = new JLabel("Realized Profit: " + realizedProfit);
                unrealizedProfitLabel = new JLabel("Unrealized Profit: " + customer.getTradingAccount().getUnrealizedProfit(SMProxy.instance));
            } else {
                realizedProfitLabel = new JLabel("Realized Profit: N/A (no trading account)");
                unrealizedProfitLabel = new JLabel("Unrealized Profit: N/A (no trading account)");
            }

            customerPanel.add(realizedProfitLabel);
            customerPanel.add(unrealizedProfitLabel);

            reportPanel.add(customerPanel);
        }

        reportPanel.revalidate();
        reportPanel.repaint();
    }

    public static void main(String[] args) {
//        new CustomerProfitReportView();
    }

}

