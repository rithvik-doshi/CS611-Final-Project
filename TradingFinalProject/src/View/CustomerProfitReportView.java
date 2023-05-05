package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import Model.Customer;
import Model.SMProxy;

public class CustomerProfitReportView extends JFrame {
    private JPanel reportPanel;
    private JButton backButton;

    public CustomerProfitReportView() {
        setTitle("Customer Profit Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        reportPanel = new JPanel();
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        reportPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            CustomerProfitReportView.this.dispose();
            ManagerPortfolioView ui = new ManagerPortfolioView();
            ui.setVisible(true);
        });
        add(backButton, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(reportPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        setSize(400, 600);
        setLocationRelativeTo(null);

    }

    public void displayReport(ArrayList<Customer> customers) {
        reportPanel.removeAll();

        for (Customer customer : customers) {
            JPanel customerPanel = new JPanel(new GridLayout(1, 3));
            customerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel nameLabel = new JLabel(customer.getName());
            customerPanel.add(nameLabel);

            JLabel unrealizedProfitLabel = new JLabel("Unrealized Profit: " + customer.getTradingAccount().getUnrealizedProfit(SMProxy.instance));
            customerPanel.add(unrealizedProfitLabel);

            JLabel realizedProfitLabel = new JLabel("Realized Profit: " + customer.getTradingAccount().getRealizedProfit());
            customerPanel.add(realizedProfitLabel);

            reportPanel.add(customerPanel);
        }

        reportPanel.revalidate();
        reportPanel.repaint();
    }

    public static void main(String[] args) {
        new CustomerProfitReportView();
    }

}

