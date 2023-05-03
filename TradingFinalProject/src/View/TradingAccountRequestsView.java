package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import Model.Request;

public class TradingAccountRequestsView extends JFrame {
    private JPanel requestsPanel;
    private JButton backButton;

    public TradingAccountRequestsView() {

        super("Trading Account Requests");
        setTitle("Trading Account Requests");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Test data
        ArrayList<Request> requests = new ArrayList<>();
        requests.add(new Request("John", "Pending"));
        requests.add(new Request("Jane", "Pending"));
        requests.add(new Request("Jack", "Pending"));   
        requests.add(new Request("Jill", "Pending"));
        requests.add(new Request("James", "Pending"));

        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        requestsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            TradingAccountRequestsView.this.dispose();
            ManagerPortfolioView ui = new ManagerPortfolioView();
            ui.setVisible(true);
        });
        add(backButton, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(requestsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(scrollPane);

        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        displayRequests(requests);
    }

    public void displayRequests(ArrayList<Request> requests) {
        requestsPanel.removeAll();

        for (Request request : requests) {
            JPanel requestPanel = new JPanel(new GridLayout(1, 3));
            requestPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel nameLabel = new JLabel(request.getSender());
            requestPanel.add(nameLabel);

            JLabel statusLabel = new JLabel(request.getStatus());
            requestPanel.add(statusLabel);

            JButton approveButton = new JButton("Approve");
            approveButton.addActionListener(e -> {
                request.approve();
                displayRequests(requests);
            });

            JButton rejectButton = new JButton("Reject");
            rejectButton.addActionListener(e -> {
                request.reject();
                displayRequests(requests);
            });

            if (request.getStatus().equals("Pending")) {
                requestPanel.add(approveButton);
                requestPanel.add(rejectButton);
            }

            requestsPanel.add(requestPanel);
        }

        requestsPanel.revalidate();
        requestsPanel.repaint();
    }

    public static void main(String[] args) {
        new TradingAccountRequestsView();
    }
}
